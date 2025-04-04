package com.loanpayment.domain.payment.model;

import java.math.BigDecimal;
import java.util.Objects;

public class PaymentDto {
    private Long loanId;
    private BigDecimal paymentAmount;

    // No-args constructor
    public PaymentDto() {
    }

    // All-args constructor
    public PaymentDto(Long loanId, BigDecimal paymentAmount) {
        this.loanId = loanId;
        this.paymentAmount = paymentAmount;
    }

    // Getters and Setters
    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    // equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDto that = (PaymentDto) o;
        return Objects.equals(loanId, that.loanId) && 
               Objects.equals(paymentAmount, that.paymentAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, paymentAmount);
    }

    // toString()
    @Override
    public String toString() {
        return "PaymentDto{" +
               "loanId=" + loanId +
               ", paymentAmount=" + paymentAmount +
               '}';
    }
}