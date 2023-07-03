package com.crud.library.domain;

import com.crud.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class BookTestsSuite {

    @Autowired
    private BookRepository bookRepository;

    //dodanie tytułu
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
    }
}
