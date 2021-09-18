package com.microservice.bookcatalogue.controller;

import com.microservice.bookcatalogue.entity.Book;
import com.microservice.bookcatalogue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/bookCatalogues")
@Slf4j
public class BookCatalogueController {

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook (@Valid @RequestBody  Book book){
        log.info("Inside saveBook method of BookCatalogueController....");
        return bookService.saveBook(book);
    }
    @GetMapping("/{bookId}")
    public Book findByBookId(@PathVariable("bookId") Long bookId) {
        log.info("Inside findBookById method of BookCatalogueController...");
        return bookService.findByBookId(bookId);
    }
    @GetMapping("/title/{bookTitle}")
    public List<Book> findByBookTitle(@PathVariable("bookTitle") String bookTitle) {
        log.info("Inside findByBookTitle method of BookCatalogueController...");
        return bookService.findByBookTitle(bookTitle);
    }
    @GetMapping("/author/{author}")
    public List<Book> findByAuthor(@PathVariable("author") String author) {
        log.info("Inside findByAuthor method of BookCatalogueController...");
        return bookService.findByAuthor(author);
    }
    @GetMapping("/isbn/{isbn}")
    public Book findByIsbn(@PathVariable("isbn") String isbn) {
        log.info("Inside findByIsbn method of BookCatalogueController...");
        return bookService.findByIsbn(isbn);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        try {
            bookService.updateBook(book);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(NoSuchElementException ex){
            log.error(ex.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteById(@PathVariable("bookId") Long bookId) {
        log.info("Inside deleteByBookId method of BookCatalogueController...");
        try {
            bookService.deleteById(bookId);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(RuntimeException ex){
            log.error(ex.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    }

