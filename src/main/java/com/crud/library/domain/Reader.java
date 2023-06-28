package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "READERS")
public class Reader {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DATE_CREATED")
    private LocalDate dateCreated;

}
