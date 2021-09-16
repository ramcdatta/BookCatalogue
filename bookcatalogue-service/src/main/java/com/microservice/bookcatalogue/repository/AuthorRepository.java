package com.microservice.bookcatalogue.repository;

import com.microservice.bookcatalogue.entity.Author;
import com.microservice.bookcatalogue.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a where a.name = :name")
    List<Author> findAuthorByName(String name);
}
