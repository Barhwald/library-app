package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "LOANS")
public class Loan {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "READER_ID")
    private int readerId;
    @Column(name = "LOAN_DATE")
    private LocalDate loanDate;
    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;
}
