package com.hexaware.loanmanagement.service;
/*@Author:Rajeshwari
 * 
 */
import java.util.List;

import com.hexaware.loanmanagement.entity.Loan;

public interface ILoanService {
    int applyLoan(Loan loan);
    double calculateInterest(int loanId);
    double calculateInterest(double principalAmount,double interestRate,int loanTerm);
    int updateLoanStatus(int loanId,String status);
    int loanStatus(int loanId);
    double calculateEMI(int loanId);
    String loanRepayment(int loanId, double amount);
    List<Loan> getAllLoan();
    Loan getLoanById(int loanId);
}
