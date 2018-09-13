package com.github.phillipkruger.jcache;

import java.io.IOException;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
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
@ApplicationScoped
public class InfinispanConfig {
    
    @Inject @ConfigProperty(name = "infinispan.configuration.file", defaultValue = "infinispan.xml")
    private String configurationFileName;
    
    @Produces
    @ApplicationScoped
    public EmbeddedCacheManager defaultClusteredCacheManager() {
        
        log.log(Level.INFO, " ### Infinispan started as JCache IMDG");
        log.log(Level.INFO, " ### Using [{0}]", configurationFileName);
        
        try {
            EmbeddedCacheManager manager = new DefaultCacheManager(configurationFileName);
            return manager;
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Could not configure Infinispan [{0}]", ex.getMessage());
        }
        return new DefaultCacheManager();
    }

}
