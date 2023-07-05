package com.crud.library.mapper;

import com.crud.library.repository.BookRepository;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookCopyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookCopyMapper {

    private final BookRepository bookRepository;

    public BookCopy mapToBookCopy(final BookCopyDto bookCopyDto) {
        return new BookCopy(bookCopyDto.getId(),
                bookRepository.findById(bookCopyDto.getBookId()).get(),
                bookCopyDto.getStatus());
    }

    public BookCopyDto mapToBookCopyDto(final BookCopy bookCopy) {
        return new BookCopyDto(bookCopy.getId(), bookCopy.getBook().getId(), bookCopy.getStatus());
    }

    public List<BookCopyDto> mapToBookCopyDtoList(final List<BookCopy> copiesList) {
        return copiesList.stream()
                .map(this::mapToBookCopyDto)
                .toList();
    }

}
