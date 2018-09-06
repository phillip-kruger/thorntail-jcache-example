package com.github.phillipkruger.quoteservice;

import java.util.concurrent.TimeUnit;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;

/**
 * Configure Infinispan
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 */
//@ApplicationScoped
@Log
public class GlobalCacheConfig {
    
    @Inject @ConfigProperty(name = "jcache.timeout.seconds", defaultValue = "10")
    private int timeout;
    
    //@Produces
    public Configuration globalCacheConfiguration() {
        
        log.warning(">>>>>>> Nee man !");
        return new ConfigurationBuilder()
                .expiration().lifespan(timeout, TimeUnit.SECONDS)
                .build();
    }

}
