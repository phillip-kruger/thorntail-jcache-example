package com.github.phillipkruger.jcache.interceptor;

import javax.annotation.Priority;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import org.infinispan.jcache.annotation.CacheKeyInvocationContextFactory;
import org.infinispan.jcache.annotation.InjectedCacheResolver;

@CacheResult
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CacheResultInterceptor extends org.infinispan.jcache.annotation.InjectedCacheResultInterceptor {
    
    @Inject
    public CacheResultInterceptor(InjectedCacheResolver cacheResolver, CacheKeyInvocationContextFactory contextFactory) {
        super(cacheResolver, contextFactory);
    }
    
}
