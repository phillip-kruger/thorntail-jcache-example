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
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Configure Hazelcast
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 */
@Log
@ApplicationScoped
public class HazelcastConfig {
    
    @Inject @ConfigProperty(name = "hazelcast.configuration.file", defaultValue = "hazelcast.xml")
    private String configurationFileName;
    
    @Produces
    @ApplicationScoped
    @Getter
    private CacheManager manager;
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        log.log(Level.INFO, " ### Hazelcast started as JCache IMDG");
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
            URI resource = cl.getResource(configurationFileName).toURI();
            log.log(Level.INFO, "Using [{0}] as configuration", resource);
            return resource;
        }catch (URISyntaxException ex) {
            log.log(Level.WARNING, "No config file provided, using default");
            return cachingProvider.getDefaultURI();
        }
    }
}
