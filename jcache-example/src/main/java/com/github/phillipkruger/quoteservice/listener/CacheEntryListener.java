package com.github.phillipkruger.quoteservice.listener;

import java.io.Serializable;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryUpdatedListener;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import lombok.extern.java.Log;

/**
 * JCache listener
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 * see https://docs.oracle.com/middleware/1213/coherence/develop-applications/jcache_events.htm#COHDG5848
 * @param <K>
 * @param <V>
 */
@Log
@Dependent
public class CacheEntryListener<K, V> implements 
        CacheEntryCreatedListener<K, V>,
        CacheEntryRemovedListener<K, V>,
        CacheEntryExpiredListener<K, V>,
        CacheEntryUpdatedListener<K, V>,
        Serializable {
    
    private static final long serialVersionUID = 97238465976927345L;
 
    @Inject
    private Event<JCacheEntryEvent> broadcaster;
    
    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends K, ? extends V> event : events) {
            broadcaster.fire(toJCacheEntryEvent(event));
        }
    }

    @Override
    public void onRemoved(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends K, ? extends V> event : events) {
            broadcaster.fire(toJCacheEntryEvent(event));
        }
    }

    @Override
    public void onExpired(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends K, ? extends V> event : events) {
            broadcaster.fire(toJCacheEntryEvent(event));
        }
    }

    @Override
    public void onUpdated(Iterable<CacheEntryEvent<? extends K, ? extends V>> events) throws CacheEntryListenerException {
        for (CacheEntryEvent<? extends K, ? extends V> event : events) {
            broadcaster.fire(toJCacheEntryEvent(event));
        }
    }
    
    private JCacheEntryEvent toJCacheEntryEvent(CacheEntryEvent cacheEntryEvent){
        return new JCacheEntryEvent(cacheEntryEvent.getEventType(), cacheEntryEvent.getKey(), cacheEntryEvent.getValue(), cacheEntryEvent.getOldValue());
    }
}