package com.github.phillipkruger.quoteservice;

import java.io.StringReader;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Retry;

@Path("/")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML}) @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@RequestScoped
@Log
public class QuoteService {

    @GET
    @Retry(retryOn = javax.json.stream.JsonParsingException.class,maxRetries = 3,delay = 10, durationUnit = ChronoUnit.SECONDS)
    @CacheResult(cacheName = "quoteCache")
    public Quote getQuote(){
        WebTarget target = client.target(endpoint);

        Response response = target
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .buildPost(Entity.form(form)).invoke();
        
        if(response.getStatus()==Response.Status.OK.getStatusCode()){
            String json = response.readEntity(String.class);
            if(json!=null && !json.isEmpty()){
                return toQuote(json);
            }else{
                throw new NullPointerException("Error while getting qoute from Forismatic [null message]");
            }
        }else { 
            throw new RuntimeException("Error while getting qoute from Forismatic [" + response.getStatusInfo().getReasonPhrase() + "]");
        }
    }
    
//    @Inject Cache<String, String> cache;
//    
//    @GET 
//    @Path("/printCache")
//    public void printCache(){
//        log.severe(">>>>> " + cache.getName());
//    }
    
    private Quote toQuote(String json){
        log.info(json);
        Quote quote = new Quote();
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            JsonObject jsonObject = reader.readObject();
            
            String text = jsonObject.getString(KEY_TEXT);
            if(text!=null)text = text.trim();
            quote.setText(text);
            
            String author = jsonObject.getString(KEY_AUTHOR);
            if(author!=null)author = author.trim();
            quote.setAuthor(author);
            
            quote.setTime(LocalTime.now());
        }
        
        return quote;
    }
    
    @PostConstruct
    public void init(){
        
        this.client = ClientBuilder.newClient();
        
        this.form = new Form()
            .param("method", method)
            .param("lang", lang)
            .param("format", "json");
    }
    
    @PreDestroy
    public void destroy(){
        this.client.close();
        this.client = null;
    }
    
    private Client client;
    private Form form;
    
    private static final String KEY_TEXT = "quoteText";
    private static final String KEY_AUTHOR = "quoteAuthor";
    
    @Inject @ConfigProperty(name = "forismatic.endpoint", defaultValue = "http://api.forismatic.com/api/1.0/")
    private String endpoint;
    
    @Inject @ConfigProperty(name = "forismatic.method", defaultValue = "getQuote")
    private String method;
    
    @Inject @ConfigProperty(name = "forismatic.lang", defaultValue = "en")
    private String lang;
    
}