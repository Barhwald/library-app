package com.crud.library.repository;


import com.crud.library.domain.Loan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    @Override
    Loan save(Loan loan);

    @Modifying(clearAutomatically = true)
    @Query("update LOANS set returnDate = :date where id = :id")
    void setReturnDate(@Param("id") long id, @Param("date")LocalDate date);
}
