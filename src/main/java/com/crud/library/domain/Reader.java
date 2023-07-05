package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "READERS")
public class Reader {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DATE_CREATED")
    private LocalDate dateCreated = LocalDate.now();
    @OneToMany(
            targetEntity = Loan.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Loan> loanList;

    public Reader(String name, LocalDate dateCreated) {
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public Reader(String name) {
        this.name = name;
    }

    public Reader(long id, String name, LocalDate dateCreated) {
    }
}
