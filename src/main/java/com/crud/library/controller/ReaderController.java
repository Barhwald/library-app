package com.crud.library.controller;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final DbService dbService;
    private final ReaderMapper readerMapper;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReaders() {
        List<Reader> readers = dbService.getAllReaders();
        return ResponseEntity.ok(readerMapper.mapToReaderDtoList(readers));
    }

    @GetMapping(value = "{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Long readerId) {
        return ResponseEntity.ok(readerMapper.mapToReaderDto(dbService.getReader(readerId)));
    }
}
