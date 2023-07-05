package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final DbService dbService;

    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        List<Book> books = dbService.getAllBooks();
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(books));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToBook(bookDto);
        dbService.saveBook(book);
        return ResponseEntity.ok().build();
    }

}
