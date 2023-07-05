package com.crud.library.service;

import com.crud.library.controller.BookCopyNotAvailable;
import com.crud.library.controller.BookCopyNotFoundException;
import com.crud.library.controller.ReaderNotFoundException;
import com.crud.library.domain.*;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.LoanRepository;
import com.crud.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public long countCopiesAvailable(Long id) {
        return bookRepository.findById(id)
                .map(Book::getCopies)
                .orElse(new ArrayList<>())
                .stream()
                .filter(copy -> copy.getStatus().name().equals("AVAILABLE"))
                .count();
    }

    public BookCopy saveBookCopy(final BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Book getBookWithId(long id) {
        return bookRepository.findById(id).get();
    }

    public List<BookCopy> getAllCopies() {
        return bookCopyRepository.findAll();
    }

    public Reader getReader(long id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }

    public BookCopy getBookCopy(long id) throws BookCopyNotFoundException {
        return bookCopyRepository.findById(id).orElseThrow(BookCopyNotFoundException::new);
    }

    public Loan saveLoan(final Loan loan) {
        return loanRepository.save(loan);
    }

    public void updateBookCopyStatus(long id) throws BookCopyNotAvailable {
        bookCopyRepository.findById(id)
                .filter(bookCopy -> bookCopy.getStatus() == Status.AVAILABLE)
                .orElseThrow(BookCopyNotAvailable::new)
                .setStatus(Status.BORROWED);
    }
}
