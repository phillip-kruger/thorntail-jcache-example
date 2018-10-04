package com.github.phillipkruger.jcache.interceptor;

import javax.annotation.Priority;
import javax.cache.annotation.CacheResult;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.infinispan.jcache.annotation.AbstractCacheResultInterceptor;
import org.infinispan.jcache.annotation.CacheKeyInvocationContextFactory;
import org.infinispan.jcache.annotation.InjectedCacheResolver;
import org.infinispan.commons.logging.LogFactory;
import org.infinispan.jcache.logging.Log;
import org.jboss.logging.Logger;

@CacheResult
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class CacheResultInterceptor //extends org.jsr107.ri.annotations.cdi.CacheResultInterceptor {
 extends AbstractCacheResultInterceptor {
    
    private static final Log log = LogFactory.getLog(CacheResultInterceptor.class, Log.class);
    
    @Override
    protected org.infinispan.jcache.logging.Log getLog() {
        return log;
    }
    
    @Inject
    public CacheResultInterceptor(InjectedCacheResolver cacheResolver,
         CacheKeyInvocationContextFactory contextFactory) {
      super(cacheResolver, contextFactory);
   }

   @AroundInvoke
   public Object cacheResult(InvocationContext invocationContext) throws Exception {
        Object o = super.cacheResult(invocationContext);
        log.log(Logger.Level.WARN, "Just cached " + o);
        return o;
   }
}