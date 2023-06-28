package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Loan;
import com.crud.library.domain.Reader;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.LoanRepository;
import com.crud.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final LoanRepository loanRepository;

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

//    public int getNumberOfCopies(final Book book) {
//        return bookRepository.countByBookCopies(book);
//    }

    public BookCopy saveBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReader(long id) throws
    public Loan saveLoan(final Loan loan) {
        return loanRepository.save(loan);
    }
}
