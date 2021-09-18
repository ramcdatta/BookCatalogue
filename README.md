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