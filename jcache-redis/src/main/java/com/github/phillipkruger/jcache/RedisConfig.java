package com.github.phillipkruger.jcache;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Configure Redis
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 */
@Log
@ApplicationScoped
public class RedisConfig {
    
    @Inject @ConfigProperty(name = "redis.configuration.file", defaultValue = "redis.xml")
    private String configurationFileName;
    
    @Produces
    @ApplicationScoped
    @Getter
    private CacheManager manager;
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        
        log.log(Level.INFO, " ### Redis started as JCache IMDG");    
        CachingProvider cachingProvider = Caching.getCachingProvider();
        URI resource = getConfigFile(cachingProvider);
        manager = cachingProvider.getCacheManager(resource,getClass().getClassLoader());

        for(String name:manager.getCacheNames()) {
            log.log(Level.INFO, " ###   Found cache [{0}]", name);
        }
          
    }
    
    private URI getConfigFile(CachingProvider cachingProvider){
        try {
            ClassLoader cl = getClass().getClassLoader();
            URL url = cl.getResource(configurationFileName);
            if(url!=null){
                URI resource = url.toURI();
                log.log(Level.INFO, "Using [{0}] as configuration", resource);
                return resource;
            }else{
                log.log(Level.WARNING, "No config file provided, using default");
                return cachingProvider.getDefaultURI();
            }
        }catch (URISyntaxException ex) {
            log.log(Level.WARNING, "No config file provided, using default [{0}]", ex.getMessage());
            return cachingProvider.getDefaultURI();
        }
    }
    
    
    
    
}
