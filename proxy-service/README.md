# proxy-service
Published in (http://trabajosdesisifo.blogspot.com.es/2016/04/camel-cxf-spring-boot-web-service-proxy.html)

This app publishes an consumes web service by using Apache CXF.

When the route receives a message, it redirects it to another endpoint.

There is a resources folder in /src/dev which contains a SoapUI example project, the trust store and the external config file.

In order to launch the program just type **java -jar proxy-service-0.0.1-SNAPSHOT.jar --propertyFile=/route/to/envConfig.properties**

[http://trabajosdesisifo.blogspot.com.es/2016/04/camel-cxf-spring-boot-web-service-proxy.html]: http://trabajosdesisifo.blogspot.com.es/2016/04/camel-cxf-spring-boot-web-service-proxy.html