package com.github.phillipkruger.jcache.interceptor;

import javax.annotation.Priority;
import javax.cache.annotation.CachePut;
import javax.interceptor.Interceptor;

@CachePut
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CachePutInterceptor extends org.jsr107.ri.annotations.cdi.CachePutInterceptor {
    
}
