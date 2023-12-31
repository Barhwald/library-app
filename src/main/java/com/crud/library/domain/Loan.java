package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "LOANS")
public class Loan {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "READER_ID")
    private Reader reader;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_COPY_ID")
    private BookCopy bookCopy;
    @Column(name = "LOAN_DATE")
    private LocalDate loanDate;
    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;

    public Loan(BookCopy bookCopy, Reader reader, LocalDate loanDate) {
        this.bookCopy = bookCopy;
        this.reader = reader;
        this.loanDate = loanDate;
    }

    public Loan(long id, Reader reader, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.reader = reader;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }
}
