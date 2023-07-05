package com.crud.library.domain;

import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
import com.crud.library.service.DbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class BookCopyTestSuite {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DbService dbService;

    //dodanie egzemplarza
    @Test
    void saveBookCopy() {
        //given
        Book book = new Book("Forest and lumber", "G.Chapman", 2009);
        BookCopy bookCopy = new BookCopy(book, Status.AVAILABLE);

        //when
        bookCopyRepository.save(bookCopy);

        //then
        long id = bookCopy.getId();
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(id);
        assertTrue(optionalBookCopy.isPresent());
    }

    //zmiana statusu książki
    @Test
    void changeBookCopyStatus() {
        //given
        Book book = new Book("The Hobbit", "J.R.R. Tolkien", 1960);
        BookCopy bookCopy = new BookCopy(book, Status.LOST);

        //when
        bookCopyRepository.save(bookCopy);

        //then
        long id = bookCopy.getId();
        System.out.println(id);
        System.out.println("Status before: " + bookCopyRepository.findById(id).get().getStatus());
        bookCopyRepository.updateBookCopyStatus(id, Status.BORROWED);
        System.out.println("Status after: " + bookCopyRepository.findById(id).get().getStatus());
        Optional<BookCopy> optionalBookCopy = bookCopyRepository.findById(id);
        assertEquals(Status.BORROWED, optionalBookCopy.get().getStatus());

        //cleanUp
        bookCopyRepository.deleteById(id);
    }

    //sprawdzenie ilości egzemplarzy danego tytułu do wypożyczenia
    @Test
    void checkHowManyBookCopiesAreAvailable() {
        //given
        Book book = new Book("Game of Thrones", "G. Martin", 1996);
        BookCopy bookCopy1 = new BookCopy(book, Status.AVAILABLE);
        BookCopy bookCopy2 = new BookCopy(book, Status.AVAILABLE);
        BookCopy bookCopy3 = new BookCopy(book, Status.DESTROYED);
        BookCopy bookCopy4 = new BookCopy(book, Status.LOST);
        BookCopy bookCopy5 = new BookCopy(book, Status.AVAILABLE);
        BookCopy bookCopy6 = new BookCopy(book, Status.BORROWED);
        BookCopy bookCopy7 = new BookCopy(book, Status.AVAILABLE);
        book.getCopies().add(bookCopy1);
        book.getCopies().add(bookCopy2);
        book.getCopies().add(bookCopy3);
        book.getCopies().add(bookCopy4);
        book.getCopies().add(bookCopy5);
        book.getCopies().add(bookCopy6);
        book.getCopies().add(bookCopy7);
        System.out.println("There's " + book.getCopies().size() + " copies");

        //when
        bookRepository.save(book);
        bookCopyRepository.save(bookCopy1);
        bookCopyRepository.save(bookCopy2);
        bookCopyRepository.save(bookCopy3);
        bookCopyRepository.save(bookCopy4);
        bookCopyRepository.save(bookCopy5);
        bookCopyRepository.save(bookCopy6);
        bookCopyRepository.save(bookCopy7);
        long id = book.getId();
        System.out.println();

        //then
        long count = dbService.countCopiesAvailable(id);
        long av = bookRepository.findById(id).get().getCopies().size();
        assertEquals(7, av);
        assertEquals(4, count);

    }
}
