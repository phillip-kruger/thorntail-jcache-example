package com.github.phillipkruger.quoteservice.listener;

import javax.enterprise.inject.spi.CDI;

/**
 *
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
public class CacheEntryListenerFactory implements javax.cache.configuration.Factory {

    @Override
    public Object create() {
        return CDI.current().select(CacheEntryListener.class).get();
    }
    
}
