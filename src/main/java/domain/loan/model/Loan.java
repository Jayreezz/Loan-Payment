package com.loanpayment.domain.loan.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @Column(nullable = false)
    private BigDecimal loanAmount;

    @Column(nullable = false)
    private Integer term; // in months

    @Column(nullable = false)
    private BigDecimal remainingAmount;

    @Column(nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    // No-args constructor (required by JPA)
    public Loan() {
    }

    // All-args constructor (for convenience)
    public Loan(BigDecimal loanAmount, Integer term) {
        this.loanAmount = loanAmount;
        this.remainingAmount = loanAmount;
        this.term = term;
    }

    // Getters and Setters
    public Long getLoanId() {
        return loanId;
    }

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

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(loanId, loan.loanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId);
    }

    // toString()
    @Override
    public String toString() {
        return "Loan{" +
               "loanId=" + loanId +
               ", loanAmount=" + loanAmount +
               ", term=" + term +
               ", remainingAmount=" + remainingAmount +
               ", creationDate=" + creationDate +
               '}';
    }
}