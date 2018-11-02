package com.github.phillipkruger.quoteservice.listener;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import lombok.extern.java.Log;

@Log
@ApplicationScoped
public class EventLogWriter {

    public void receiveMovement(@Observes JCacheEntryEvent event){
        log.severe("###### " + event);
    }
    
}
