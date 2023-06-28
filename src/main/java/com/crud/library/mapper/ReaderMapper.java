package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(readerDto.getId(),
                readerDto.getName(),
                readerDto.getDateCreated());
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(reader.getId(),
                reader.getName(),
                reader.getDateCreated());
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(this::mapToReaderDto)
                .toList();
    }

}
