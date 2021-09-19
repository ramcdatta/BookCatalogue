# SERVICE-REGISTRY


### Guides
The following guides illustrate how to use some features concretely:

* As name suggest this is the registry of all services. 
* This is to abstract the low level information of microservices like host port etc.
* It is running in 8761 which is default port of EUREKA netflix service registry
* To invoke the service only service/application nam is enough, no need to remember host and port.
* This will avoid any changes in the client in case of any port or host change.
* Services has to register as EUREKA client inorder to access registered microservices