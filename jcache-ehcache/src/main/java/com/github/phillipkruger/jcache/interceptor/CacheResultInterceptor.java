package com.github.phillipkruger.jcache.interceptor;

import javax.annotation.Priority;
import javax.cache.annotation.CacheResult;
import javax.interceptor.Interceptor;

@CacheResult
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CacheResultInterceptor extends org.jsr107.ri.annotations.cdi.CacheResultInterceptor {
    
}
