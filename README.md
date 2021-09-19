# BookCatalogue
### There are 2 component bookcatalogue-service and bookcatalogue-kafka
### bookcatalogue-service -
   This module is to perform all CRUD operation on book catalogue. Please find the operations 
   and sample API.
     
Add book, - http://localhost:9001/bookCatalogues/ 
       
update book, - http://localhost:9001/bookCatalogues/update

getBookByID, - http://localhost:9001/bookCatalogues/1
     
getBookByTitle, - http://localhost:9001/bookCatalogues/title/science
 
getBookByAuthor, - http://localhost:9001/bookCatalogues/author/a1,a2

getBookByISBN, http://localhost:9001/bookCatalogues/isbn/11111111

DeleteBook. - http://localhost:9001/bookCatalogues/delete/1

### bookcatalogue-kafka - 
   This module to a gateway to communicate with kafka in case of any event like 
   Add book, Update book and delete book.
    
1. producer - /bookCatalogue-kafka/producer?event=<message>

# SERVICE-REGISTRY


### Guides
The following guides illustrate how to use some features concretely:

* As name suggest this is the registry of all services.
* This is to abstract the low level information of microservices like host port etc.
* It is running in 8761 which is default port of EUREKA netflix service registry
* To invoke the service only service/application nam is enough, no need to remember host and port.
* This will avoid any changes in the client in case of any port or host change.
* Services need to register as EUREKA client inorder to access registered microservices

# API-GATEWAY


### Guides
The following guides illustrate how to use some features concretely:

* This service is an api-gateway for all the service. That means any service call happen through api-gateway
* Port is 9191
* Even though kafka-service is running in 9002 and book-catalogue-service in 9002 those service has to access through 9191
* This is single entry for all microservices.

# Starting the servers 

### Sequence services should start with port
 Name                Port
* service-registry : 8761
* config-server : 9296
* api-gateway : 9191
* bookcatalogue-service : 9001
* book-catalogue-kafka : 9002