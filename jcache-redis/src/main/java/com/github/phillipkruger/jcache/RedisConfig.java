package com.github.phillipkruger.jcache;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import lombok.Getter;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.redisson.RedissonNode;
import org.redisson.config.Config;
import org.redisson.config.RedissonNodeConfig;

/**
 * Configure Redis
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * 
 */
@Log
@ApplicationScoped
public class RedisConfig {
    
    @Inject @ConfigProperty(name = "redis.configuration.file", defaultValue = "redis.yml")
    private String configurationFileName;
    
    @Produces
    @ApplicationScoped
    @Getter
    private CacheManager manager;
    
    private RedissonNode node = null;
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            CachingProvider cachingProvider = Caching.getCachingProvider();
            URI resource = getConfigFile(cachingProvider);
            
            Config config = Config.fromYAML(resource.toURL());
            
            RedissonNodeConfig nodeConfig = new RedissonNodeConfig(config);
            
            node = RedissonNode.create(nodeConfig);
            
            node.start();
            
            log.log(Level.INFO, " ### Redis started as JCache IMDG");
            manager = cachingProvider.getCacheManager(resource,getClass().getClassLoader());
            
            for(String name:manager.getCacheNames()) {
                log.log(Level.INFO, " ###   Found cache [{0}]", name);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
          
    }
    
    public void shutdown(@Observes @Destroyed(ApplicationScoped.class) Object init) {
        if(node!=null)node.shutdown();
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
