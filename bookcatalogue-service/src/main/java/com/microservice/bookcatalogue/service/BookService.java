package com.microservice.bookcatalogue.service;

import com.microservice.bookcatalogue.entity.Author;
import com.microservice.bookcatalogue.entity.Book;
import com.microservice.bookcatalogue.repository.AuthorRepository;
import com.microservice.bookcatalogue.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    private static String BOOK_ADD_EVENT = "BOOK_CREATED";

    private static String BOOK_DELETE_EVENT = "BOOK_DELETED";

    private static String BOOK_UPDATE_EVENT = "BOOK_UPDATED";

    private static String KAFKA_SERVICE = "http://localhost:8080/bookCatalogue-kafka/producer?event=";

    public Book saveBook(Book book) {
        log.info("Inside saveBook method of BookCatalogueRepository....");
        if (null != book.getAuthor() && !book.getAuthor().isEmpty()) {
            book.getAuthor().forEach(author -> author.setBook(book));
        }
        Book resultBook = bookRepository.save(book);
        if(null!=resultBook){
            restTemplate().getForObject(KAFKA_SERVICE + book.getBookTitle() +"_"+ BOOK_ADD_EVENT
                    ,String.class);
        }
        return bookRepository.save(book);
    }

    public Book findByBookId(Long bookId) {
        return bookRepository.findByBookId(bookId);
    }

    public List<Book> findByBookTitle(String bookTitle) {
        log.info("Inside findByBookTitle method of BookCatalogueRepository....");
        return bookRepository.findByBookTitle(bookTitle);
    }


    public List<Book> findByAuthor(String author) {
        log.info("Inside findByAuthor method of BookCatalogueRepository....");
        List<String> authors = Arrays.asList(author.split(","));
        return bookRepository.findByAuthor(authors);
    }

    public Book findByIsbn(String isbn) {
        log.info("Inside findByAuthor method of BookCatalogueRepository....");
        return bookRepository.findByIsbn(isbn);
    }

    public void deleteById(Long bookId) {
        log.info("Inside deleteByBookId method of BookCatalogueRepository....");
        try {
            bookRepository.deleteById(bookId);

            restTemplate().getForObject(KAFKA_SERVICE + BOOK_DELETE_EVENT
                        ,String.class);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void updateBook(Book book) {
        log.info("Inside updateBook method of BookCatalogueRepository....");
        Book bookFromDB = bookRepository.findByBookId(book.getBookId());
        final List<Author> authors = bookFromDB.getAuthor();
        authors.forEach(author -> authorRepository.delete(author));
        // If user exists then updated
        if (null != book.getAuthor() && !book.getAuthor().isEmpty()) {
            book.getAuthor().forEach(author -> author.setBook(book));
        }
        bookRepository.save(book);

        restTemplate().getForObject(KAFKA_SERVICE + book.getBookTitle() +"_"+ BOOK_UPDATE_EVENT
                ,String.class);
    }
}
