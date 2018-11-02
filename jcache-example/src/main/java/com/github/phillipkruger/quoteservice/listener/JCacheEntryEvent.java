package com.github.phillipkruger.quoteservice.listener;

import javax.cache.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Pojo that represents a event
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class JCacheEntryEvent {
    private EventType eventType;
    private Object key;
    private Object newValue;
    private Object oldValue;
}
