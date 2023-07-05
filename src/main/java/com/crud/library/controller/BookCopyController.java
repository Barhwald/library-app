package com.crud.library.controller;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookCopyDto;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/copies")
@RequiredArgsConstructor
public class BookCopyController {

    private final DbService dbService;
    private final BookCopyMapper bookCopyMapper;

    @GetMapping
    public ResponseEntity<List<BookCopyDto>> getBookCopies() {
        List<BookCopy> copies = dbService.getAllCopies();
        return ResponseEntity.ok(bookCopyMapper.mapToBookCopyDtoList(copies));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        dbService.saveBookCopy(bookCopy);
        return ResponseEntity.ok().build();
    }

}
