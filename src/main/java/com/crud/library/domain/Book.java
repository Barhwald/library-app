package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "ISSUE_YEAR")
    private int issueYear;
    @OneToMany(
            targetEntity = BookCopy.class,
            mappedBy =
    )
    @JoinColumn(name = "COPIES")
    private BookCopy bookCopy;

}
