package com.hexaware.loanmanagement.dao;
/*@Author:Rajeshwari

 * 
 */
import java.util.List;

import com.hexaware.loanmanagement.entity.Loan;

public interface ILoanDao {
	boolean validateLoanId(int loanId);
    int applyLoan(Loan loan);
    double calculateInterest(int loanId);
    double calculateInterest(double principalAmount,double interestRate,int loanTerm);
    int loanStatus(int loanId);
    int updateLoanStatus(int loanId,String status);
    double calculateEMI(int loanId);
    String loanRepayment(int loanId, double amount);
    List<Loan> getAllLoan();
    Loan getLoanById(int loanId);
}
