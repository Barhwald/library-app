package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private int id;
    private String name;
    private LocalDate dateCreated;

}
