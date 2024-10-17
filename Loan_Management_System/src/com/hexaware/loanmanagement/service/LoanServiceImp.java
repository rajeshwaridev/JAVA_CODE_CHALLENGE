package com.hexaware.loanmanagement.service;
/*@Author:Rajeshwari
 * 
 */
import java.sql.Connection;
import java.util.List;

import com.hexaware.loan.exception.InvalidLoanException;
import com.hexaware.loanmanagement.dao.ILoanDao;
import com.hexaware.loanmanagement.dao.LoanDaoImp;
import com.hexaware.loanmanagement.entity.Loan;
import com.hexaware.loanmanagement.util.DBUtil;

public class LoanServiceImp implements ILoanService {

	private static ILoanDao dao;
	public LoanServiceImp() {
		dao=new LoanDaoImp();
	}
	
   public static boolean validateLoanId(int loanId) {
	   boolean flag=dao.validateLoanId(loanId);
	   if(flag==false) {
		   try {
		   throw new InvalidLoanException();
		   }
		   catch(InvalidLoanException e) {
			   e.printStackTrace();
		   }
		   finally {
			   System.err.println("Loan Id not Exist");
		   }
		   
	   }
	   return flag;
   }
	@Override
	public int applyLoan(Loan loan) {
		
		return dao.applyLoan(loan);
	}

	@Override
	public double calculateInterest(int loanId) {
		
		
		return dao.calculateInterest(loanId);
	}


	@Override
	public double calculateInterest(double principalAmount, double interestRate, int loanTerm) {
		return dao.calculateInterest(principalAmount, interestRate, loanTerm);
	}

	@Override
	public int loanStatus(int loanId) {
		return dao.loanStatus(loanId);
		
	}
	

	@Override
	public int updateLoanStatus(int loanId, String status) {
		return dao.updateLoanStatus(loanId, status);
	}

	@Override
	public double calculateEMI(int loanId) {
		return dao.calculateEMI(loanId);
	}

	
	@Override
	public String loanRepayment(int loanId, double amount) {
		return dao.loanRepayment(loanId, amount);
	}

	@Override
	public List<Loan> getAllLoan() {
		return dao.getAllLoan();
	}

	@Override
	public Loan getLoanById(int loanId) {
		return dao.getLoanById(loanId);
	}

}
