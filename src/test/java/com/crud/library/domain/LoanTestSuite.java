package com.crud.library.domain;

import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class LoanTestSuite {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    //wypożyczenie książki
    @Test
    void loanABookCopy() {
        //given
        Book book = new Book("Game of Thrones", "G. Martin", 1996);
        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);
        Reader reader = new Reader("Regis", LocalDate.of(2018, 11, 13));
        Loan loan = new Loan(bookCopy, reader, LocalDate.now());

        //when
        bookCopyRepository.save(bookCopy);
        loanRepository.save(loan);

        //then
        long bookCopyId = bookCopy.getId();
        long loanId = loan.getId();

        System.out.println("Status before: " + bookCopyRepository.findById(bookCopyId).get().getStatus());
        bookCopyRepository.updateBookCopyStatus(bookCopyId, Status.BORROWED);
        System.out.println("Status after: " + bookCopyRepository.findById(bookCopyId).get().getStatus());

        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(bookCopyId);
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        assertTrue(optionalLoan.isPresent());
        assertEquals(Status.BORROWED, optionalBookCopy.get().getStatus());

    }

    //zwrot książki
    @Test
    void returnABookCopy() {
        //given
        Book book = new Book("Game of Thrones", "G. Martin", 1996);
        BookCopy bookCopy = new BookCopy(book, Status.BORROWED);
        Reader reader = new Reader("Regis", LocalDate.of(2018, 11, 13));
        Loan loan = new Loan(bookCopy, reader, LocalDate.of(2023, 6,10));

        //when
        bookCopyRepository.save(bookCopy);
        loanRepository.save(loan);

        //then
        long loanId = loan.getId();
        long bookCopyId = bookCopy.getId();
        bookCopyRepository.updateBookCopyStatus(bookCopyId, Status.AVAILABLE);
        loanRepository.setReturnDate(loanId, LocalDate.now());
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(bookCopyId);
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        System.out.println(optionalLoan.get().getReturnDate());
        System.out.println(optionalBookCopy.get().getStatus());

        //then
        assertNotEquals(null, optionalLoan.get().getReturnDate());
        assertEquals(Status.AVAILABLE, optionalBookCopy.get().getStatus());
    }
}
