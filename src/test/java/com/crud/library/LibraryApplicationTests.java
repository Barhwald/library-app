package com.crud.library;

import com.crud.library.domain.*;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.LoanRepository;
import com.crud.library.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class LibraryApplicationTests {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private LoanRepository loanRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void saveBook() {
        //given
        Book book = new Book("The Champion", "Paulo Laraz", 1986);

        //when
        bookRepository.save(book);

        //then
        long id = book.getId();
        Optional<Book> readBook = bookRepository.findById(id);
        assertTrue(readBook.isPresent());

        //cleanUp
        bookRepository.deleteById(id);
    }

    @Test
    void saveReader() {
        //given
        Reader reader = new Reader("Ergis", LocalDate.now());

        //when
        readerRepository.save(reader);

        //then
        long id = reader.getId();
        Optional<Reader> optionalReader = readerRepository.findById(id);
        assertTrue(optionalReader.isPresent());

        //cleanUp
        readerRepository.deleteById(id);
    }

    @Test
    void changeBookCopyStatus() {
        //given
        Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1960);
        BookCopy bookCopy = new BookCopy(book, String.valueOf(Status.LOST));

        //when
        bookCopyRepository.save(bookCopy);

        //then
        long id = bookCopy.getId();
        System.out.println(id);
        bookCopyRepository.updateBookCopyStatus(id, String.valueOf(Status.BORROWED));
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(id);
        assertEquals(Status.BORROWED.name(), optionalBookCopy.get().getStatus());

        //cleanUp
        bookCopyRepository.deleteById(id);
    }

    @Test
    void loanABookCopy() {
        //given
        Book book = new Book("Game of Thrones", "G. Martin", 1996);
        BookCopy bookCopy = new BookCopy(book, String.valueOf(Status.AVAILABLE));
        Reader reader = new Reader("Regis", LocalDate.of(2018, 11, 13));
        Loan loan = new Loan(bookCopy, reader, LocalDate.now());

        //when
        bookRepository.save(book);
        bookCopyRepository.save(bookCopy);
        readerRepository.save(reader);
        loanRepository.save(loan);

        //then
        long bookId = book.getId();
        long bookCopyId = bookCopy.getId();
        long loanId = loan.getId();
        long readerId = reader.getId();
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        assertTrue(optionalLoan.isPresent());

        //cleanUp
        bookRepository.deleteById(bookId);
        bookCopyRepository.deleteById(bookCopyId);
        readerRepository.deleteById(readerId);
        loanRepository.deleteById(loanId);

    }

}
