package com.github.phillipkruger.jcache;

import java.io.IOException;
import java.util.logging.Level;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.enterprise.event.Observes;
import lombok.Getter;
import org.infinispan.jcache.embedded.JCachingProvider;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

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
    
    @Produces
    @ApplicationScoped
    @Getter
    private CacheManager manager;
    
    @Produces
    @ApplicationScoped
    private EmbeddedCacheManager infinispanmanager;
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        
        try {
            infinispanmanager = new DefaultCacheManager(configurationFileName);
            
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Could not configure Infinispan [{0}]", ex.getMessage());
            infinispanmanager = new DefaultCacheManager();
        }
        
        
        log.log(Level.INFO, " ### Infinispan started as JCache IMDG");    
        JCachingProvider cachingProvider = (JCachingProvider)Caching.getCachingProvider();
        
        log.log(Level.SEVERE, " ### " + cachingProvider.getDefaultURI());    
        
        
        //URI resource = getConfigFile(cachingProvider);
        manager = cachingProvider.getCacheManager();//resource,getClass().getClassLoader());

        for(String name:manager.getCacheNames()) {
            log.log(Level.INFO, " ###   Found cache [{0}]", name);
        }
    }
    
//    private URI getConfigFile(CachingProvider cachingProvider){
//        try {
//            ClassLoader cl = getClass().getClassLoader();
//            URI resource = cl.getResource(configurationFileName).toURI();
//            log.log(Level.INFO, "Using [{0}] as configuration", resource);
//            return resource;
//        }catch (URISyntaxException ex) {
//            log.log(Level.WARNING, "No config file provided, using default");
//            return cachingProvider.getDefaultURI();
//        }
//    }
}
