package com.microservice.bookcatalogue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.bookcatalogue.entity.Author;
import com.microservice.bookcatalogue.entity.Book;
import com.microservice.bookcatalogue.repository.BookRepository;
import com.microservice.bookcatalogue.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(value = BookCatalogueController.class)
public class BookCatalogueControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    Book book_1 = new Book(Long.valueOf(1),"GEO",getAuthors(),"1111111",
            new Date(Long.valueOf("20140910")));
    Book book_2 = new Book(Long.valueOf(2),"SCI",getAuthors(),"2222222",
            new Date(Long.valueOf("20140911")));


    @Test
    public void saveBook_Test() throws Exception {


        Book book = Book.builder()
                .bookId(Long.valueOf(1))
                .bookTitle("GEO")
                .isbn("1111111111111")
                .author(getAuthors())
                .publicationDate(new Date(Long.valueOf("20140910")))
                .build();

        Mockito.when(bookService.saveBook(book)).thenReturn(new Book(Long.valueOf(1),"GEO",getAuthors(),
                "1111111",new Date(Long.valueOf("20140910"))));
        Mockito.when(bookService.saveBook(book)).thenReturn(new Book(Long.valueOf(1),"GEO",getAuthors(),
                "1111111",new Date(Long.valueOf("20140910"))));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/bookCatalogues/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(book));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());

    }

    private List getAuthors() {
        List authors = new ArrayList<>();
        Author author = new Author(null,"author",null);
        Author author2 = new Author(null,"author1",null);

        authors.add(author);
        authors.add(author2);
        return authors;
    }

    @Test
    public void getBookByIdTest() throws Exception {

        Mockito.when(bookRepository.findById(book_1.getBookId())).thenReturn(java.util.Optional.of(book_1));
        Mockito.when(bookService.findByBookId(book_1.getBookId())).thenReturn(book_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bookCatalogues/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void getBookByTitleTest() throws Exception {
        List<Book> books = new ArrayList<>(Arrays.asList(book_1,book_2));

        Mockito.when(bookRepository.findByBookTitle(book_1.getBookTitle())).thenReturn(books);
        Mockito.when(bookService.findByBookTitle(book_1.getBookTitle())).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bookCatalogues/title/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void getBookByIsbnTest() throws Exception {
        List<Book> books = new ArrayList<>(Arrays.asList(book_1,book_2));

        Mockito.when(bookRepository.findByIsbn(book_1.getIsbn())).thenReturn(book_1);
        Mockito.when(bookService.findByIsbn(book_1.getIsbn())).thenReturn(book_1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bookCatalogues/isbn/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void getBookByAuthorTest() throws Exception {
        List<Book> books = new ArrayList<>(Arrays.asList(book_1,book_2));

        Mockito.when(bookRepository.findByAuthor(getAuthors())).thenReturn(Arrays.asList(book_1,book_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/bookCatalogues/author/author1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void updateBookTest() throws Exception {
        Book book = Book.builder()
                .bookId(Long.valueOf(1))
                .bookTitle("GEO")
                .isbn("1111111111111")
                .author(getAuthors())
                .publicationDate(new Date(Long.valueOf("20140910")))
                .build();

        Mockito.when(bookService.saveBook(book)).thenReturn(new Book(Long.valueOf(1),"GEO",getAuthors(),
                "1111111",new Date(Long.valueOf("20140910"))));
        Mockito.when(bookService.saveBook(book)).thenReturn(new Book(Long.valueOf(1),"GEO",getAuthors(),
                "1111111",new Date(Long.valueOf("20140910"))));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/bookCatalogues/update")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(book));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }
    @Test
    public void deleteBook() throws Exception {
        Mockito.when(bookRepository.findById(book_2.getBookId())).thenReturn(Optional.of(book_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/bookCatalogues/delete/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
