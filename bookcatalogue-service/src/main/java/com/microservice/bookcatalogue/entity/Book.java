package com.microservice.bookcatalogue.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String bookTitle;
    @OneToMany(targetEntity = Author.class,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name ="ab_fk",referencedColumnName = "bookId")
    private List<Author> author;
    @Column(unique = true)
    private String isbn;
    private Date publicationDate;
}
