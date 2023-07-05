package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "BOOK_COPIES")
public class BookCopy {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(
            targetEntity = Loan.class,
            mappedBy = "bookCopy",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Loan> loanList = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID")
    private Book book;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    public BookCopy(Book book, Status status) {
        this.book = book;
        this.status = status;
    }

    public BookCopy(long id, Book book, Status status) {
        this.id = id;
        this.book = book;
        this.status = status;
    }
}
