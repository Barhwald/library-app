package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookCopyDto {
    private long id;
    private long bookId;
    private Status status;

}
