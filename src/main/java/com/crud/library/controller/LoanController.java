package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Loan;
import com.crud.library.domain.LoanDto;
import com.crud.library.mapper.LoanMapper;
import com.crud.library.repository.LoanRepository;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final DbService dbService;
    private final LoanMapper loanMapper;
    private final LoanRepository loanRepository;

    @GetMapping
    public ResponseEntity<List<LoanDto>> getLoans() {
        List<Loan> loans = dbService.getAllLoans();
        return ResponseEntity.ok(loanMapper.mapToLoanDtoList(loans));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createLoan(@RequestBody LoanDto loanDto) throws BookCopyNotAvailable, BookCopyNotFoundException {
        Loan loan = loanMapper.mapToLoan(loanDto);
        loan.setLoanDate(LocalDate.now());
        dbService.saveLoan(loan);
        BookCopy bookCopy = dbService.getBookCopy(loanDto.getBookCopyId());
        dbService.updateBookCopyStatus(bookCopy.getId());
        dbService.saveBookCopy(bookCopy);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{loanId}")
    public ResponseEntity<LoanDto> finishLoan(@PathVariable Long loanId) throws BookCopyNotFoundException {
        Loan loan = loanRepository.findById(loanId).get();
        loan.setReturnDate(LocalDate.now());
        Loan savedLoan = dbService.saveLoan(loan);
        return ResponseEntity.ok(loanMapper.mapToLoanDto(savedLoan));
    }

}
