package com.github.phillipkruger.jcache;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
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
    
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        Config config = new ClasspathXmlConfig(configurationFileName);
        
        Hazelcast.newHazelcastInstance(config);
        
        log.log(Level.INFO, " ### Hazelcast started as JCache IMDG");
        log.log(Level.INFO, " ### Using [{0}]", configurationFileName);    
    }
    

}
