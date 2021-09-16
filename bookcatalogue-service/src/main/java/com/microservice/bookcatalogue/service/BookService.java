package com.microservice.bookcatalogue.service;

import com.microservice.bookcatalogue.entity.Author;
import com.microservice.bookcatalogue.entity.Book;
import com.microservice.bookcatalogue.repository.AuthorRepository;
import com.microservice.bookcatalogue.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Service
@Slf4j
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book saveBook(Book book) {
        log.info("Inside saveBook method of BookCatalogueRepository....");
        if (null != book.getAuthor() && !book.getAuthor().isEmpty()) {
            book.getAuthor().forEach(author -> author.setBook(book));
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
    }
}
