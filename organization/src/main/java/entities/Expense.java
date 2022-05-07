package entities;

public class Expense {
	private int expenseId;
	private Float amountOfMoney;
	
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Expense(int expenseId, Float amountOfMoney) {
		super();
		this.expenseId = expenseId;
		this.amountOfMoney = amountOfMoney;
	}
	
	
	/**
	 * constructor without expenseId*/
	public Expense(Float amountOfMoney) {
		super();
		this.amountOfMoney = amountOfMoney;
	}

	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public Float getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(Float amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", amountOfMoney=" + amountOfMoney + "]";
	}
	
	
	
	

}
