package com.crud.library.domain;

import com.crud.library.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class ReaderTestSuite {

    @Autowired
    private ReaderRepository readerRepository;

    //dodanie czytelnika
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
}
