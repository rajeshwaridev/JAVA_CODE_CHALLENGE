package com.hexaware.loanmanagement.dao;
/*@Author:Rajeshwari
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.loanmanagement.entity.Customer;
import com.hexaware.loanmanagement.entity.Loan;
import com.hexaware.loanmanagement.util.DBUtil;

public class LoanDaoImp implements ILoanDao{
	private Connection conn;
	

	public LoanDaoImp() {
		super();
		conn=DBUtil.getDBConnection();
	}

	
	@Override
	public boolean validateLoanId(int loanId) {
		String string="select loanid from loan";
		try {
			PreparedStatement pstmt=conn.prepareStatement(string);
			ResultSet resultSet=pstmt.executeQuery();
			while(resultSet.next()) {
				int loanIds=resultSet.getInt(1);
				if(loanIds==loanId) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public int applyLoan(Loan loan) {
		String string="insert into loan values(?,?,?,?,?,?,'Pending')";
		int count=0;
	try {
		PreparedStatement pstmt=conn.prepareStatement(string);
		pstmt.setInt(1, loan.getLoanId());
		pstmt.setInt(2, loan.getCustomer().getCustomerId());
		pstmt.setDouble(3, loan.getPrincipalAmount());
		pstmt.setDouble(4, loan.getInterestRate());
		pstmt.setInt(5, loan.getLoanTerm());
		pstmt.setString(6,loan.getLoanType());
		count=pstmt.executeUpdate();
		if(count>0) {
			System.out.println("Data inserted");
		}
		else {
			System.err.println("NOt inserted");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return count	;
	}

	@Override
	public double calculateInterest(int loanId) {
		
		String string="select principalamount,interestrate,loanTerm from loan where loanid=?";
		double interest=0.0;
		try {
			PreparedStatement pstmt=conn.prepareStatement(string);
			pstmt.setInt(1, loanId);
			ResultSet resultSet=pstmt.executeQuery();
			while(resultSet.next()) {
				double principalAmount=resultSet.getDouble(1);
				double interestRate=resultSet.getDouble(2);
				double loanTerm=resultSet.getInt(3);
				interest=(principalAmount*interestRate*loanTerm)/12;
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return interest;
	}

	

	@Override
	public double calculateInterest(double principalAmount, double interestRate, int loanTerm) {
		
		return (principalAmount*interestRate*loanTerm)/12;
	}


	@Override
	public int loanStatus(int loanId) {
		
		String string="select creditscore from customer where customerid=(select customerid from loan where loanid=?)";
		int creditScore=0;
		try {
			PreparedStatement pstmt=conn.prepareStatement(string);
			pstmt.setInt(1, loanId);
			ResultSet resultSet=pstmt.executeQuery();
			while(resultSet.next()) {
				creditScore=resultSet.getInt(1);
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return creditScore;
		
	}
	

	@Override
	public int updateLoanStatus(int loanId, String status) {
		int count=0;
		String string="update loan set loanstatus=? where loanid=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(string);
			pstmt.setString(1, status);
			pstmt.setInt(2, loanId);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}


	@Override
	public double calculateEMI(int loanId) {
		String string="select principalamount,interestrate,loanterm from loan where loanid=?";
		double emi=0.0;
		try {
			PreparedStatement pstmt=conn.prepareStatement(string);
			pstmt.setInt(1, loanId);
			ResultSet resultSet=pstmt.executeQuery();
			while(resultSet.next()) {
				double principalAmount=resultSet.getDouble(1);
				double interestRate=resultSet.getDouble(2);
				interestRate=interestRate/12/100;
				int loanTerm=resultSet.getInt(3);
		        emi = (principalAmount * interestRate * Math.pow(1 +interestRate, loanTerm)) 
	                     / (Math.pow(1 + interestRate, loanTerm) - 1);
				
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return emi;
	}

	

	@Override
	public String loanRepayment(int loanId, double amount) {
		
		return "Repayment Accepted";
	}

	@Override
	public List<Loan> getAllLoan() {
		List <Loan> list=new ArrayList<Loan>();
		String string="select * from loan";
		try {
			PreparedStatement pstmt=conn.prepareStatement(string);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int loanId=rs.getInt(1);
				int customerId=rs.getInt(2);
				double principalAmount=rs.getDouble(3);
				double interestRate=rs.getDouble(4);
				int loanTerm=rs.getInt(5);
				String loanType=rs.getString(6);
				String status=rs.getString(7);
				Customer customer=new Customer();
				customer.setCustomerId(customerId);
				Loan loan=new Loan(loanId,customer,principalAmount,interestRate,loanTerm,loanType);
				list.add(loan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Loan getLoanById(int loanId) {
		String string="Select * from loan where loanid=?";
		Loan loans=new Loan();
		
		try {
			PreparedStatement pstmt=conn.prepareStatement(string);
			pstmt.setInt(1, loanId);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int loansId=rs.getInt(1);
				int customerId=rs.getInt(2);
				double principalAmount=rs.getDouble(3);
				double interestRate=rs.getDouble(4);
				int loanTerm=rs.getInt(5);
				String loanType=rs.getString(6);
				String status=rs.getString(7);
				Customer customer=new Customer();
				customer.setCustomerId(customerId);
//				Loan loan=new Loan(loansId,customer,principalAmount,interestRate,loanTerm,loanType);
				loans.setLoanId(loanId);
				loans.setCustomer(customer);
				loans.setPrincipalAmount(principalAmount);
				loans.setInterestRate(interestRate);
				loans.setLoanTerm(loanTerm);
				loans.setLoanType(loanType);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loans;
	}

}
