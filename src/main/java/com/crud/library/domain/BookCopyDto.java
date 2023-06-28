package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCopyDto {
    private int id;
    private int titleId;
    private Status status;
}
