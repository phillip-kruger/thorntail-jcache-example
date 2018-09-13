package com.github.phillipkruger.jcache.interceptor;

import javax.annotation.Priority;
import javax.cache.annotation.CacheRemoveAll;
import javax.interceptor.Interceptor;

@CacheRemoveAll
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CacheRemoveAllInterceptor extends org.jsr107.ri.annotations.cdi.CacheRemoveAllInterceptor {
    
}
