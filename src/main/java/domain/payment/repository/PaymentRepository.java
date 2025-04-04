package com.loanpayment.domain.payment.repository;

import com.loanpayment.domain.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
   // Find all payments for a specific loan
    List<Payment> findByLoanId(Long loanId);
    
    // Find payments within a date range
    List<Payment> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    // Find payments greater than a certain amount
    List<Payment> findByPaymentAmountGreaterThan(BigDecimal amount);
    
    // Get the total payments made for a specific loan
    @Query("SELECT SUM(p.paymentAmount) FROM Payment p WHERE p.loanId = :loanId")
    BigDecimal getTotalPaymentsForLoan(@Param("loanId") Long loanId);
    
    // Get the last payment made for a loan
    @Query("SELECT p FROM Payment p WHERE p.loanId = :loanId ORDER BY p.paymentDate DESC LIMIT 1")
    Optional<Payment> findLastPaymentForLoan(@Param("loanId") Long loanId);
    
    // Find payments made on a specific date
    @Query("SELECT p FROM Payment p WHERE DATE(p.paymentDate) = DATE(:date)")
    List<Payment> findByPaymentDate(@Param("date") LocalDateTime date);
}