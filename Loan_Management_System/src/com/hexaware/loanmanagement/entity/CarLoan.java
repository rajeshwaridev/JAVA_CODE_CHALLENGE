package com.hexaware.loanmanagement.entity;
/*@Author:Rajeshwari*/
public class CarLoan extends Loan {
    private String carModel;
    private int carValue;

    // Default constructor
    public CarLoan() {}

    // Parameterized constructor
    public CarLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String carModel, int carValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, "CarLoan");
        this.carModel = carModel;
        this.carValue = carValue;
    }

    // Getters and Setters
    public String getCarModel() { 
    	return carModel;
    	}
    public void setCarModel(String carModel) {
    	this.carModel = carModel; 
    	}

    public int getCarValue() {
    	return carValue;
    	}
    public void setCarValue(int carValue) {
    	this.carValue = carValue;
    	}

	@Override
	public String toString() {
		return "CarLoan [carModel=" + carModel + ", carValue=" + carValue + "]";
	}

   
}
