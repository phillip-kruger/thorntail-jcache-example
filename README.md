# Thorntail jcache (backed by Infinispan) example
Example app to show JCache on Thorntail with Inifispan


## Getting started

    git clone https://github.com/phillip-kruger/thorntail-jcache-example.git
    cd thorntail-jcache-example/
    mvn clean install -Pthorntail-run
    

This will start 2 separate thorntail servers, one on 8080 and one on 8180.

The infinispan grid in the background will share a cache over these 2 server.

Go to http://localhost:8080/thorntail-jcache/ and http://localhost:8180/thorntail-jcache/

They will show the same quote. After 1 min the cache will expire.
