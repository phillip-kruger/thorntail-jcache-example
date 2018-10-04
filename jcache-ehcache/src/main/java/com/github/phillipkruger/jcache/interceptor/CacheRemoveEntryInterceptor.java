package com.github.phillipkruger.jcache.interceptor;

import javax.annotation.Priority;
import javax.cache.annotation.CacheRemove;
import javax.interceptor.Interceptor;

@CacheRemove
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CacheRemoveEntryInterceptor extends org.jsr107.ri.annotations.cdi.CacheRemoveEntryInterceptor {
    
}
