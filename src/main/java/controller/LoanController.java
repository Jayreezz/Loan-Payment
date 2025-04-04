package com.loanpayment.controller;

import com.loanpayment.domain.loan.model.Loan;
import com.loanpayment.domain.loan.model.LoanDto;
import com.loanpayment.domain.loan.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanDto> createLoan(@RequestBody LoanDto loanDto) {
        try {
            LoanDto createdLoan = loanService.createLoan(loanDto);
            return new ResponseEntity<>(createdLoan, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDto> getLoan(@PathVariable Long loanId) {
        try {
            LoanDto loanDto = loanService.getLoanById(loanId);
            return new ResponseEntity<>(loanDto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}