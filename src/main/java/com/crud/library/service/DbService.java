package com.crud.library.service;

import com.crud.library.controller.ReaderNotFoundException;
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

    public long countCopiesPerTitle(Long id) {
        return bookRepository.findById(id)
                .map(Book::getCopies)
                .orElse(new ArrayList<>())
                .stream()
                .filter(copy -> copy.getStatus().equals("AVAILABLE"))
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

    public Reader getReader(long id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }
    public Loan saveLoan(final Loan loan) {
        return loanRepository.save(loan);
    }
}
