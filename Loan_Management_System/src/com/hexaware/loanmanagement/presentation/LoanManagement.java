package com.hexaware.loanmanagement.presentation;
/*@Author:Rajeshwari
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.hexaware.*;
import com.hexaware.loanmanagement.service.LoanServiceImp;
import com.hexaware.loanmanagement.entity.Customer;
import com.hexaware.loanmanagement.entity.Loan;
import com.hexaware.loanmanagement.service.ILoanService;

public class LoanManagement {
	static Scanner scanner = new Scanner(System.in);

		public static void main(String[] args) {
			
			ILoanService service=new LoanServiceImp();
			boolean flag = true;
			while(flag) {
				System.out.println("****LOAN MANAGEMENT SYSTEM****");
				System.out.println("1.Apply Loan");
				System.out.println("2.Calculate Interest");
				System.out.println("3.Loan Status");
//				System.out.println("4.calculate EMI");
				System.out.println("4.Loan Repayment");
				System.out.println("5.Get All Loan");
				System.out.println("6.Get Loan By Eid");
				System.out.println("0.Exit");
				System.out.println("Enter your Choice");
				int choice=scanner.nextInt();
				switch(choice) {
				case 1:
					System.out.println("Enter Loan Id");
					int loanId=scanner.nextInt();
					System.out.println("Enter Customer Id");
					int customerId=scanner.nextInt();
					Customer customer=new Customer();
					customer.setCustomerId(customerId);
					System.out.println("Enter pricipal amount");
					double principalAmount=scanner.nextDouble();
					System.out.println("enter interest rate");
					double interestRate=scanner.nextDouble();
					System.out.println("Enter loan term");
					int loanTerm=scanner.nextInt();
					System.out.println("enter loan type");
					String loanType=scanner.next();
					String loanStatus="Pending";
					System.out.println("enter YES to continue NO to drop the process");
					String approval=scanner.next();
					if(approval.equals("YES")) {
					Loan loan=new Loan(loanId,customer,principalAmount,interestRate,loanTerm,loanType);
					int insert=service.applyLoan(loan);
					double interest=service.calculateInterest(principalAmount, interestRate, loanTerm);
					System.out.println("Interest is "+interest);
					}
					else {
						System.out.println("NOT APPROVED");
					}
					break;
				case 2:
					System.out.println("Enter Load Id");
					int calculateInterestLoanId=scanner.nextInt();
					boolean checkLoanId=LoanServiceImp.validateLoanId(calculateInterestLoanId);
					if(checkLoanId) {
					double interest=service.calculateInterest(calculateInterestLoanId);
					System.out.println("CALCULATED INTEREST IS "+interest);
					}
					break;
				case 3:
					System.out.println("Enter Loan Id");
					int statusLoanId=scanner.nextInt();
					int creditScore=service.loanStatus(statusLoanId);
					if(creditScore>650) {
						String status="Approved";
						System.out.println(creditScore);
						System.out.println(status);
						int updateStatus=service.updateLoanStatus(statusLoanId, status);
						if(updateStatus>0) {
							System.out.println("Approved status updated in Db");
						}
					}
					 else {
							String rejectStatus="Rejected";
							System.out.println(creditScore);
							System.err.println(rejectStatus);
							int rejectUpdateStatus=service.updateLoanStatus(statusLoanId, rejectStatus);
							if(rejectUpdateStatus>0) {
								System.out.println("Rjected statusUpdated in Db");
							}
						}
					break;
					
					
//				case 4:
//					System.out.println("Enter Loan Id");
//					int emiLoanId=scanner.nextInt();
//					boolean checkEmiLoanId=LoanServiceImp.validateLoanId(emiLoanId);
//					if(checkEmiLoanId) {
//						double calculateEmi=service.calculateEMI(emiLoanId);
//						System.out.println("Calculated EMI is "+calculateEmi);
//					}
//					break;
					
				case 4:
					System.out.println("Enter loan Id");
					int repaymentLoanId=scanner.nextInt();
					boolean checksEmiLoanId=LoanServiceImp.validateLoanId(repaymentLoanId);
					System.out.println("Enter repayment amount");
					double amount=scanner.nextDouble();
					if(checksEmiLoanId) {
					double checkEmi=service.calculateEMI(repaymentLoanId);
					System.out.println("EMI is "+checkEmi);
					double division=amount/checkEmi;
					if(division>=1) {
						String string=service.loanRepayment(repaymentLoanId, amount);
						System.out.println(string);
						
					}
					else {
						System.err.println("Repayment rejected Because Repayment amount is less than singleEMI");
					}
					}
					break;
				case 5:
					List<Loan> list= new ArrayList<Loan>();
					list=service.getAllLoan();
					for(Loan loan:list) {
						System.out.println(loan);
					}
					break;
				case 6:
					System.out.println("Enter Loan Id to know detils");
					int detailLoanId=scanner.nextInt();
					boolean checkDetailLoanId=LoanServiceImp.validateLoanId(detailLoanId);
					if(checkDetailLoanId) {
						
						Loan lists=service.getLoanById(detailLoanId);
						System.out.println(lists);
						
					}
					break;
				case 0:
					flag=false;
					System.out.println("Existed from loan Management System");
					break;
				default:
					System.out.println("Invalid Choice");
					
					
				}
			}

	}

}
