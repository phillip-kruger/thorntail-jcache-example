package com.github.phillipkruger.quoteservice.listener.jcache;

import javax.enterprise.context.RequestScoped;
import lombok.extern.java.Log;
import javax.enterprise.event.Observes;
import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryExpiredEvent;

@Log @RequestScoped
public class CacheListener {

    public void cacheEntryCreatedEvent(@Observes CacheEntryCreatedEvent event) {
        System.out.println(">>>>>>> JCACHE >> New entry " + event.getKey() + " created in the cache");
    }

    public void cacheEntryEvent(@Observes CacheEntryEvent event) {
        System.out.println(">>>>>>> JCACHE >> entry event " + event.getKey() + " [" + event + "]");
    }
    
    public void cacheEntryExpiredEvent(@Observes CacheEntryExpiredEvent event) {
        System.out.println(">>>>>>> JCACHE >> Old entry " + event.getKey() + " expiring");
    }
    
    public void cacheStartedEvent(@Observes CacheStartedEvent event) {
        System.out.println(">>>>>>> JCACHE >> New cache " + event.getCacheName() + " started");
    }
}
