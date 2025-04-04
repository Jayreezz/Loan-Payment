package com.loanpayment.domain.loan.model;

import java.math.BigDecimal;
import java.util.Objects;

public class LoanDto {
    private BigDecimal loanAmount;
    private Integer term;

    // No-args constructor
    public LoanDto() {
    }

    // All-args constructor
    public LoanDto(BigDecimal loanAmount, Integer term) {
        this.loanAmount = loanAmount;
        this.term = term;
    }

    // Getters and Setters
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanDto loanDto = (LoanDto) o;
        return Objects.equals(loanAmount, loanDto.loanAmount) && 
               Objects.equals(term, loanDto.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanAmount, term);
    }

    // toString()
    @Override
    public String toString() {
        return "LoanDto{" +
               "loanAmount=" + loanAmount +
               ", term=" + term +
               '}';
    }
}