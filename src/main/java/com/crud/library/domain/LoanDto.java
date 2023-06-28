package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoanDto {
    private long id;
    private long readerId;
    private LocalDate loanDate;
    private LocalDate returnDate;
}
