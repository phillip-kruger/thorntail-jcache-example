package com.github.phillipkruger.quoteservice;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.cache.event.CacheEntryEvent;
import lombok.extern.java.Log;

@ApplicationScoped
@Log
public class EventReceiver {

//    private void entryRemovedFromCache(@Observes CacheEntryCreatedEvent event) {
//        
//    }
//
//    
//    private void cacheStarted(@Observes CacheStartedEvent event) {
//        
//    }
    
    
    private void cacheStarted(@Observes CacheEntryEvent event) {
        log.severe("BLA BLA BLA >>>>>>>>>>>> " + event);
    }
    
}
