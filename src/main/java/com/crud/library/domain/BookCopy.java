package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "BOOK_COPIES")
public class BookCopy {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "BOOK_ID")
    private int bookId;
    @Column(name = "STATUS")
    private Status status;
//    @ManyToOne
//    @JoinColumn(name = "BOOK_ID")
//    private Book book;

}
