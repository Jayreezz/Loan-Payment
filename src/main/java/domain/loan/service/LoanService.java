package com.loanpayment.domain.loan.service;

import com.loanpayment.domain.loan.model.Loan;
import com.loanpayment.domain.loan.model.LoanDto;
import com.loanpayment.domain.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Transactional
    public LoanDto createLoan(LoanDto loanDto) {
        validateLoanDto(loanDto);
        
        Loan loan = new Loan(loanDto.getLoanAmount(), loanDto.getTerm());
        Loan savedLoan = loanRepository.save(loan);
        
        return mapToDto(savedLoan);
    }

    public LoanDto getLoanById(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan not found with id: " + loanId));
        
        return mapToDto(loan);
    }

    @Transactional
    public void updateRemainingAmount(Long loanId, BigDecimal amount) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("Loan not found with id: " + loanId));
        
        loan.setRemainingAmount(amount);
        loanRepository.save(loan);
    }

    private void validateLoanDto(LoanDto loanDto) {
        if (loanDto.getLoanAmount() == null || loanDto.getLoanAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive");
        }
        if (loanDto.getTerm() == null || loanDto.getTerm() <= 0) {
            throw new IllegalArgumentException("Term must be positive");
        }
    }

    private LoanDto mapToDto(Loan loan) {
        LoanDto dto = new LoanDto();
        dto.setLoanId(loan.getLoanId());
        dto.setLoanAmount(loan.getLoanAmount());
        dto.setRemainingAmount(loan.getRemainingAmount());
        dto.setTerm(loan.getTerm());
        dto.setCreationDate(loan.getCreationDate());
        return dto;
    }
}
