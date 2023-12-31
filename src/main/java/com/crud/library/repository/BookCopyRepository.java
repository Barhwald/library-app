package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookCopyRepository extends CrudRepository<BookCopy, Long> {
    @Override
    BookCopy save(BookCopy bookCopy);

    @Modifying(clearAutomatically = true)
    @Query("update BOOK_COPIES" +
            " set status = :newStatus " +
            " where id=:id")
    void updateBookCopyStatus(@Param("id") long id, @Param("newStatus") Status newStatus);

    @Override
    List<BookCopy> findAll();

}
