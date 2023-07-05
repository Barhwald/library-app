package com.crud.library.controller;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookCopyDto;
import com.crud.library.domain.Status;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BookRepository;
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
    private final BookRepository bookRepository;
    private final BookCopyRepository bookCopyRepository;

    @GetMapping
    public ResponseEntity<List<BookCopyDto>> getBookCopies() {
        List<BookCopy> copies = dbService.getAllCopies();
        return ResponseEntity.ok(bookCopyMapper.mapToBookCopyDtoList(copies));
    }

    @GetMapping(value = "{bookId}")
    public ResponseEntity<List<BookCopyDto>> getBookCopiesWithId(@PathVariable Long bookId) {
        List<BookCopy> copiesPerId = bookRepository.findById(bookId).get().getCopies().stream().toList();
        return ResponseEntity.ok(bookCopyMapper.mapToBookCopyDtoList(copiesPerId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        BookCopy bookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);
        dbService.saveBookCopy(bookCopy);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{bookCopyId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookCopyDto> changeBookCopyStatus(@RequestBody BookCopyDto bookCopyDto, @PathVariable Long bookCopyId) {
        Status newStatus = bookCopyDto.getStatus();
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).get();
        bookCopy.setStatus(newStatus);
        BookCopy savedBookCopy = dbService.saveBookCopy(bookCopy);
        return ResponseEntity.ok(bookCopyMapper.mapToBookCopyDto(savedBookCopy));
    }

}
