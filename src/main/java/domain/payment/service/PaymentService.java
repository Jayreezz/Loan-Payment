package com.loanpayment.domain.payment.service;

import com.loanpayment.domain.loan.service.LoanService;
import com.loanpayment.domain.payment.model.Payment;
import com.loanpayment.domain.payment.model.PaymentDto;
import com.loanpayment.domain.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final LoanService loanService;

    public PaymentService(PaymentRepository paymentRepository, LoanService loanService) {
        this.paymentRepository = paymentRepository;
        this.loanService = loanService;
    }

    @Transactional
    public PaymentDto processPayment(PaymentDto paymentDto) {
        validatePaymentDto(paymentDto);
        
        // Process payment and update loan balance
        BigDecimal newBalance = calculateNewBalance(paymentDto);
        loanService.updateRemainingAmount(paymentDto.getLoanId(), newBalance);
        
        // Record the payment
        Payment payment = new Payment(paymentDto.getLoanId(), paymentDto.getPaymentAmount());
        Payment savedPayment = paymentRepository.save(payment);
        
        return mapToDto(savedPayment);
    }

    private BigDecimal calculateNewBalance(PaymentDto paymentDto) {
        LoanDto loan = loanService.getLoanById(paymentDto.getLoanId());
        BigDecimal newBalance = loan.getRemainingAmount().subtract(paymentDto.getPaymentAmount());
        return newBalance.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : newBalance;
    }

    private void validatePaymentDto(PaymentDto paymentDto) {
        if (paymentDto.getLoanId() == null) {
            throw new IllegalArgumentException("Loan ID must be provided");
        }
        if (paymentDto.getPaymentAmount() == null || paymentDto.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
    }

    private PaymentDto mapToDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setPaymentId(payment.getPaymentId());
        dto.setLoanId(payment.getLoanId());
        dto.setPaymentAmount(payment.getPaymentAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        return dto;
    }
}