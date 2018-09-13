package com.github.phillipkruger.jcache.interceptor;

import javax.annotation.Priority;
import javax.cache.annotation.CacheRemove;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import org.infinispan.jcache.annotation.CacheKeyInvocationContextFactory;
import org.infinispan.jcache.annotation.InjectedCacheResolver;

@CacheRemove
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CacheRemoveEntryInterceptor extends org.infinispan.jcache.annotation.InjectedCacheRemoveEntryInterceptor {
    
    @Inject
    public CacheRemoveEntryInterceptor(InjectedCacheResolver cacheResolver, CacheKeyInvocationContextFactory contextFactory) {
        super(cacheResolver, contextFactory);
    }
    
}
