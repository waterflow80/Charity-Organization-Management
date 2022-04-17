package entities;

import java.time.Year;

public class Budget {
	private int budgetId;
	private Float amountOfMoney;
	private Year year; // The season year
	public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Budget(int budgetId, Float amountOfMoney, Year year) {
		super();
		this.budgetId = budgetId;
		this.amountOfMoney = amountOfMoney;
		this.year = year;
	}
	public int getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(int budgetId) {
		this.budgetId = budgetId;
	}
	public Float getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Float amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	@Override
	public String toString() {
		return "Budget [budgetId=" + budgetId + ", amountOfMoney=" + amountOfMoney + ", year=" + year + "]";
	}
	
	
}
