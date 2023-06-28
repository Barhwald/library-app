package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoanDto {
    private int id;
    private int readerId;
    private LocalDate loanDate;
    private LocalDate returnDate;
}
