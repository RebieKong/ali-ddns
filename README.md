## HOW TO USE
### INSTALL
It should start in linux,windows is ok but I do not test it
1. create dir like this
```
|--|config
|  |--application-product.properties
|--m-dns.jar
|--start.sh
```
content in application-product.properties
```
com.aliyun.rebie.ak=#place your ak#
com.aliyun.rebie.aks=#place your ak secure#
com.aliyun.rebie.ddns.domain=#place your domain#
com.aliyun.rebie.ddns.prefix=#place your ddns prefix,by default is ddns#
com.aliyun.rebie.ddns.keys.d1=#d1 key#
com.aliyun.rebie.ddns.keys.d2=#d2 key#
server.port=#server port#
```

content in start.sh
```
#!/bin/bash

kill -9 `jps -m |grep ddns | awk '{print $1}'`
nohup java -jar m-dns.jar --spring.profiles.active=product --appName=ddns &
```

use start.sh to start your service.

### UPDATE DDNS

the ddns is `d1.prefix.domain`

`curl some.domain.com/update/ddns?key=#d1 key#`

then ddns will locate to the ip that you request

***Remember,dns has ttl that may not effect everywhere immediately.***

### OTHER

when using nginx to proxy please do this
```
add under text to application-properties

server.use-forward-headers=true
server.tomcat.remote-ip-header=X-Real-IP
server.tomcat.protocol-header=X-Forwarded-Proto

add under text to nginx config

proxy_set_header X-Real-IP $remote_addr;
proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
```
