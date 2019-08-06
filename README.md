# Thorntail jcache example

Example app to show JCache on Thorntail with Inifispan or Hazelcast


## Getting started

You need to clone and build this locally:

    https://github.com/phillip-kruger/jcache-cdi

then do:

    git clone https://github.com/phillip-kruger/thorntail-jcache-example.git
    cd thorntail-jcache
    mvn clean install

Then start either the Infinispan or the Hazelcast backed Thorntail servers:

    cd jcache-example/ 

### For Infinispan
    mvn clean install -Pinfinispan-run
    
### For Hazelcast
    mvn clean install -Phazelcast-run    


## Testing

This will start 2 separate Thorntail servers, one on 8080 and one on 8180.

The Infinispan/Hazelcast grid in the background will share a cache over these 2 server.

Go to http://localhost:8080/jcache-example/ and http://localhost:8180/jcache-example/

They will show the same quote. After 30 seconds the cache will expire.

![](https://raw.githubusercontent.com/phillip-kruger/thorntail-jcache-example/master/infinispan_logo.png)

![](https://raw.githubusercontent.com/phillip-kruger/thorntail-jcache-example/master/hazelcast_logo.png)

## TODO

* Events on Infinispan
* Add Ehcache and/or Redis
* 2nd Level cache on JPA / Hibernate ? Writes ?
* CDI Remote events
* Explicit cache (@Cache injection)
* What about when not Java ?
* Offload to disk ?