package com.microservice.bookcatalogue.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


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
    @NotNull
    @Size(min=13, message="ISBN need to be unique")
    private String isbn;
    private Date publicationDate;
}
