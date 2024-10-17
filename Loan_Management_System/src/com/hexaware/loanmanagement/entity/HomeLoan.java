package com.hexaware.loanmanagement.entity;
/*@Author:Rajeshwari
 * 
 */
public class HomeLoan extends Loan {
    private String propertyAddress;
    private int propertyValue;

    // Default constructor
    public HomeLoan() {}

    // Parameterized constructor
    public HomeLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String propertyAddress, int propertyValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, "HomeLoan");
        this.propertyAddress = propertyAddress;
        this.propertyValue = propertyValue;
    }

    // Getters and Setters
    public String getPropertyAddress() { 
    	return propertyAddress;
    	}
    public void setPropertyAddress(String propertyAddress) { 
    	this.propertyAddress = propertyAddress;
    	}

    public int getPropertyValue() { 
    	return propertyValue;
    	}
    public void setPropertyValue(int propertyValue) { 
    	this.propertyValue = propertyValue;
    	}

	@Override
	public String toString() {
		return "HomeLoan [propertyAddress=" + propertyAddress + ", propertyValue=" + propertyValue + "]";
	}

    
    
}
