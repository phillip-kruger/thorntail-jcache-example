package com.github.phillipkruger.quoteservice;

import lombok.extern.java.Log;

import javax.cache.event.CacheEntryEvent;
//import javax.cache.event.CacheEntryListenerException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

import org.infinispan.notifications.cachemanagerlistener.event.CacheStartedEvent;
import org.infinispan.notifications.cachelistener.event.*;

/**
 *
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@RequestScoped
public class MyCacheEntryListener {
    
    public void any(@Observes CacheEntryEvent event) {
        log.severe("> > > > > > > > > > Received a any [" + event + "]");
    }

//    private void cacheStarted(@Observes CacheStartedEvent event) {
//        log.severe("Received a cacheStarted [" + event + "]");
//    }
    
}

