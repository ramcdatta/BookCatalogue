package com.microservice.bookcatalogue.repository;

import com.microservice.bookcatalogue.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByBookId(Long bookId);

    List<Book> findByBookTitle(String bookTitle);

   @Query("SELECT book FROM Book book WHERE book.author IN (:author)")     // 2. Spring JPA In cause using @Query
   List<Book> findByAuthor(@Param("author") String author);

    Book findByIsbn(String isbn);

    void deleteById(Long bookId);
/*

    @Query(nativeQuery =true,value = "SELECT * FROM Book as book WHERE book.employeeName NOT IN (:names)")   // Spring JPA In cause using native query
    List<Employee> findByEmployeeNameNot(@Param("names") List<String> names);
*/

    //Book findByAuthor(String author);
}
