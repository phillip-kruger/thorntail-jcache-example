package com.github.phillipkruger.quoteservice.listener;

/**
 *
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
public class CacheEntryListenerFactory implements javax.cache.configuration.Factory {

    @Override
    public Object create() {
        return new CacheEntryListener();
    }
    
}
