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

}
