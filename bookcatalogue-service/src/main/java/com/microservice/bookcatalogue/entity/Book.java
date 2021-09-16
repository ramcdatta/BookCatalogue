package com.microservice.bookcatalogue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String bookTitle;
    @ElementCollection
    @CollectionTable(name = "authors", joinColumns = @JoinColumn (name = "bookId"))
    @Column(name = "author")
    private List<String> author;
    private String isbn;
    private Date publicationDate;
}
