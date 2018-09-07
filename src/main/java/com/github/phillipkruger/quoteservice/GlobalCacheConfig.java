package com.github.phillipkruger.quoteservice;

import java.io.IOException;
import java.util.logging.Level;
import javax.cache.CacheManager;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

/**
 * Configure Infinispan
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 */
@Log
public class GlobalCacheConfig {
    
    @Inject @ConfigProperty(name = "jcache.timeout.seconds", defaultValue = "10")
    private int timeout;
    
    @Inject @ConfigProperty(name = "jcache.cluster.name", defaultValue = "InfinispanCluster")
    private String clusterName;
    
    @Inject @ConfigProperty(name = "infinispan.configuration.file", defaultValue = "infinispan.xml")
    private String configurationFileName;
    
    @Produces
    @ApplicationScoped
    public EmbeddedCacheManager defaultClusteredCacheManager() {
        //new CacheManager();
        
        
        try {
            EmbeddedCacheManager manager = new DefaultCacheManager(configurationFileName);
            return manager;
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Could not configure Infinispan [{0}]", ex.getMessage());
        }
        return new DefaultCacheManager();
    }

}
