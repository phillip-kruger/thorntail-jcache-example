package com.github.phillipkruger.jcache;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.ehcache.core.EhcacheManager;

/**
 * Configure Hazelcast
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 */
@Log
@ApplicationScoped
public class EhcacheConfig {
    
    @Inject @ConfigProperty(name = "ehcache.configuration.file", defaultValue = "ehcache.xml")
    private String configurationFileName;
    
    private CacheManager manager;
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        
        try {
            CachingProvider cachingProvider = Caching.getCachingProvider(getClass().getClassLoader());
            
            ClassLoader cl = getClass().getClassLoader();
            
             
            URI resource = cl.getResource(configurationFileName).toURI();
            manager = cachingProvider.getCacheManager();
//            manager = cachingProvider.getCacheManager(resource,cl);
            
//            Cache<Object, Object> cache = manager.getCache("quoteCache");
            

            log.log(Level.INFO, " ### Ehcache started as JCache IMDG");    
            //log.log(Level.INFO, " ### Using [{0}]", resource);
            
            for(String name:manager.getCacheNames()) {
                log.log(Level.INFO, " ###   Found cache [{0}]", name);
            }
            
        } catch (URISyntaxException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }
    
//    @Produces
//    @ApplicationScoped
//    public EhcacheManager defaultCacheManager() {
//        log.log(Level.SEVERE, " ### FOK MY");    
//        return new EhcacheManager(manager;
//    }

}
