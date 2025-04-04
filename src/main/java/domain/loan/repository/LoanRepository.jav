package com.loanpayment.domain.loan.repository;

import com.loanpayment.domain.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
      // Find loans with remaining balance greater than specified amount
    List<Loan> findByRemainingAmountGreaterThan(BigDecimal amount);
    
    // Find loans created after a certain date
    List<Loan> findByCreationDateAfter(LocalDateTime date);
    
    // Find loans by term range
    List<Loan> findByTermBetween(Integer minTerm, Integer maxTerm);
    
    // Count active loans (with remaining balance > 0)
    long countByRemainingAmountGreaterThan(BigDecimal zero);
    
    // Find loans with remaining balance less than specified amount (for nearing completion)
    @Query("SELECT l FROM Loan l WHERE l.remainingAmount > 0 AND l.remainingAmount < :threshold")
    List<Loan> findLoansNearingCompletion(@Param("threshold") BigDecimal threshold);
    
    // Find loans with payments made (using exists in payments table)
    @Query("SELECT l FROM Loan l WHERE EXISTS (SELECT 1 FROM Payment p WHERE p.loanId = l.loanId)")
    List<Loan> findLoansWithPayments();
}