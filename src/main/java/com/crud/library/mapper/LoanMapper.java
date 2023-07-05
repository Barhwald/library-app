package com.crud.library.mapper;

import com.crud.library.domain.Loan;
import com.crud.library.domain.LoanDto;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoanMapper {

    private final ReaderRepository readerRepository;
    private final BookCopyRepository bookCopyRepository;

    public Loan mapToLoan(final LoanDto loanDto) {
        return new Loan(loanDto.getId(), readerRepository.findById(loanDto.getReaderId()).get(),
                bookCopyRepository.findById(loanDto.getBookCopyId()).get(),
                loanDto.getLoanDate(), loanDto.getReturnDate());
    }

    public LoanDto mapToLoanDto(final Loan loan) {
        return new LoanDto(loan.getId(), loan.getReader().getId(), loan.getBookCopy().getId(),
                loan.getLoanDate(), loan.getReturnDate());
    }

    public List<LoanDto> mapToLoanDtoList(final List<Loan> loansList) {
        return loansList.stream()
                .map(this::mapToLoanDto)
                .toList();
    }

}
