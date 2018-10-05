package com.github.phillipkruger.jcache;

import java.net.URI;
import java.net.URISyntaxException;
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
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.events.CacheEvent;
import org.apache.ignite.events.EventType;
import org.apache.ignite.lang.IgnitePredicate;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Configure Ignite
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 */
@Log
@ApplicationScoped
public class IgniteConfig {
    
    @Inject @ConfigProperty(name = "ignite.configuration.file", defaultValue = "ignite.xml")
    private String configurationFileName;
    
    @Produces
    @ApplicationScoped
    @Getter
    private CacheManager manager;
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        
        log.log(Level.INFO, " ### Apache Ignite started as JCache IMDG");    
        CachingProvider cachingProvider = Caching.getCachingProvider();
        URI resource = getConfigFile(cachingProvider);
        manager = cachingProvider.getCacheManager(resource,getClass().getClassLoader());

        for(String name:manager.getCacheNames()) {
            log.log(Level.INFO, " ###   Found cache [{0}]", name);
        }
        
        
        
        
        Ignite ignite = Ignition.ignite();

        // Local listener that listenes to local events.
        IgnitePredicate<CacheEvent> locLsnr = evt -> {
          log.log(Level.SEVERE, "Received event [evt={0}, key={1}, oldVal={2}, newVal={3}", new Object[]{evt.name(), evt.key(), evt.oldValue(), evt.newValue()});
          return true; // Continue listening.
        };

        // Subscribe to specified cache events occuring on local node.
        ignite.events().localListen(locLsnr,
                EventType.EVT_CACHE_OBJECT_PUT,
                EventType.EVT_CACHE_OBJECT_READ,
                EventType.EVT_CACHE_OBJECT_REMOVED,
                EventType.EVT_CACHE_OBJECT_EXPIRED);
          
    }
    
    private URI getConfigFile(CachingProvider cachingProvider){
        try {
            ClassLoader cl = getClass().getClassLoader();
            URI resource = cl.getResource(configurationFileName).toURI();
            log.log(Level.INFO, "Using [{0}] as configuration", resource);
            return resource;
        }catch (URISyntaxException ex) {
            log.log(Level.WARNING, "No config file provided, using default [" + ex.getMessage() + "]");
            return cachingProvider.getDefaultURI();
        }
    }
    
    
    
    
}
