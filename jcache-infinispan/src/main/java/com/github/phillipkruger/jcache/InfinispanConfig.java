package com.github.phillipkruger.jcache;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import javax.enterprise.event.Observes;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachelistener.event.*;

/**
 * Configure Infinispan
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 */
@Log
@ApplicationScoped
public class InfinispanConfig {
    
    @Inject @ConfigProperty(name = "infinispan.configuration.file", defaultValue = "infinispan.xml")
    private String configurationFileName;
    
    private EmbeddedCacheManager manager;
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            CachingProvider cachingProvider = Caching.getCachingProvider();
            
            
            CacheManager manager = cachingProvider.getCacheManager(
                    getClass().getResource(configurationFileName).toURI(),
                    getClass().getClassLoader());
            
            
            log.log(Level.INFO, " ### Infinispan started as JCache IMDG");    
            log.log(Level.INFO, " ### Using [{0}]", configurationFileName);
        } catch (URISyntaxException ex) {
            log.log(Level.SEVERE, null, ex);
        }
        
        
        
        
//        try {
//            manager = new DefaultCacheManager(configurationFileName);
//        } catch (IOException ex) {
//            log.log(Level.SEVERE, "Could not configure Infinispan [{0}]", ex.getMessage());
//            manager = new DefaultCacheManager();
//        }
//        manager.addListener(new InfinispanListener());
//        
//        log.log(Level.INFO, " ### Infinispan started as JCache IMDG");
//        log.log(Level.INFO, " ### Using [{0}]", configurationFileName);  
    }
    
    @Produces
    @ApplicationScoped
    public EmbeddedCacheManager defaultClusteredCacheManager() {
        return manager;
    }
}
