package com.microservice.bookcatalogue.repository;

import com.microservice.bookcatalogue.entity.Book;
import com.microservice.bookcatalogue.entity.Author;
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

    @Query("SELECT distinct b FROM Book b inner join Author a on b.id = a.book.id where a.name IN (:authorNames)")     // 2. Spring JPA In cause using @Query
    List<Book> findByAuthor(@Param("authorNames") List <String> authorNames);

    Book findByIsbn(String isbn);

    void deleteById(Long bookId);

//    @Query("SELECT * FROM Book book JOIN author.author author")
//    public List<Book> getJoinInformation();
/*

    @Query(nativeQuery =true,value = "SELECT * FROM Book as book WHERE book.employeeName NOT IN (:names)")   // Spring JPA In cause using native query
    List<Employee> findByEmployeeNameNot(@Param("names") List<String> names);
*/

    //Book findByAuthor(String author);
}
