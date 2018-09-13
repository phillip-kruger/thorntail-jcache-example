# Thorntail jcache (backed by Infinispan) example
Example app to show JCache on Thorntail with Inifispan


## Getting started

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
