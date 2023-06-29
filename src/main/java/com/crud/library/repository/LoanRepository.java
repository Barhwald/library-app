package com.crud.library.repository;


import com.crud.library.domain.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    @Override
    Loan save(Loan loan);
}
