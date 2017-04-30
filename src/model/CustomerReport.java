package model;

public class CustomerReport {

	private String mOrderDate;
	private int mCustomerID;
	private double mMoneySpent;
	
	
	public CustomerReport(String date, int custID, double moneySpent) {
		setOrderDate(date);
		setCustomerID(custID);
		setMoneySpent(moneySpent);
	}

	
	@Override
	public String toString() {
		return getOrderDate() + "   CustomerID: " + getCustomerID() + "   MoneySpent: $" + String.format("%.2f", getMoneySpent());		
	}


	public int getCustomerID() {
		return mCustomerID;
	}


	public void setCustomerID(int mCustomerID) {
		this.mCustomerID = mCustomerID;
	}


	public String getOrderDate() {
		return mOrderDate;
	}


	public void setOrderDate(String mOrderDate) {
		this.mOrderDate = mOrderDate;
	}


	public double getMoneySpent() {
		return mMoneySpent;
	}


	public void setMoneySpent(double mMoneySpent) {
		this.mMoneySpent = mMoneySpent;
	}
}