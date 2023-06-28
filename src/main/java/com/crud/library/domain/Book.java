package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "ISSUE_YEAR")
    private int issueYear;

//    @OneToMany(
//            targetEntity = BookCopy.class,
//            mappedBy = "book",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private List<BookCopy> bookCopies;
    public Book(String title, String author, int issueYear) {
        this.title = title;
        this.author = author;
        this.issueYear = issueYear;
    }
}
