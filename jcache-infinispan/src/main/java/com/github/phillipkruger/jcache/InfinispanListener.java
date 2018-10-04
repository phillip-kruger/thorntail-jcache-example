package com.github.phillipkruger.jcache;

import lombok.extern.java.Log;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryExpired;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryRemoved;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryExpiredEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryRemovedEvent;

@Log
@Listener//(clustered = true)
public class InfinispanListener {
    
    @CacheEntryCreated
    public void cacheEntryCreated (CacheEntryCreatedEvent event) {
        log.severe(">>>>> Boom create ! " + event);
    }
    
    @CacheEntryRemoved
    public void print(CacheEntryRemovedEvent event) {
        log.severe(">>>>> Boom Removed ! " + event);
    }
    
    @CacheEntryExpired
    public void print(CacheEntryExpiredEvent event) {
        log.severe(">>>>> Boom Expired ! " + event);
    }
    
    @CacheEntryModified
    public void print(CacheEntryModifiedEvent event) {
        log.severe(">>>>> Boom Modified ! " + event);
    }

}
