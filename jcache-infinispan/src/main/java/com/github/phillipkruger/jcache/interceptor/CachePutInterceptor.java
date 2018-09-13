package com.github.phillipkruger.jcache.interceptor;

import javax.annotation.Priority;
import javax.cache.annotation.CachePut;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import org.infinispan.jcache.annotation.CacheKeyInvocationContextFactory;
import org.infinispan.jcache.annotation.InjectedCacheResolver;

@CachePut
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CachePutInterceptor extends org.infinispan.jcache.annotation.InjectedCachePutInterceptor {
    
    @Inject
    public CachePutInterceptor(InjectedCacheResolver cacheResolver, CacheKeyInvocationContextFactory contextFactory) {
        super(cacheResolver, contextFactory);
    }
    
}
