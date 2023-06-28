package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
    @Override
    BookCopy save(BookCopy bookCopy);

}
