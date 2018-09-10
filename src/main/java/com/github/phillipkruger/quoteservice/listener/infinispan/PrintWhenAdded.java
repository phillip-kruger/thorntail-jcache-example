package com.github.phillipkruger.quoteservice.listener.infinispan;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;

/**
 * see http://infinispan.org/docs/8.2.x/user_guide/user_guide.html#_Listeners_and_notifications_section
 */
@Listener (clustered = true)
public class PrintWhenAdded {
    @CacheEntryCreated
    public void print(CacheEntryCreatedEvent event) {
        System.out.println(">>>>>>> INFINISPAN >> New entry " + event.getKey() + " created in the cache");
    } 
}
