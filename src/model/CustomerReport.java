package model;

import controller.Program;

public class CustomerReport {
	private int mOrderID;
	private int mCustomerID;
	private float mTotal;
	private String mOrderDate;
	private int mEmployeeID;
	
	
	public CustomerReport(int orderId, int custID, float total, String orderDate, int employID) {
		setOrderID(orderId);
		setCustomerID(custID);
		setTotal(total);
		setOrderDate(orderDate);
		setEmployeeID(employID);
	}

	
	@Override
	public String toString() {
		return "Order:" + getOrderID() + "    Customer:" + Program.getInstance().getDataAccess().getCustomerNameById(getCustomerID()) + "    Total:" + getTotal() + "    OrderDate:" + getOrderDate() + "    Employee:" + Program.getInstance().getDataAccess().getEmployeeById(getEmployeeID()).getName();		
	}


	public int getOrderID() {
		return mOrderID;
	}


	public void setOrderID(int mOrderID) {
		this.mOrderID = mOrderID;
	}


	public int getCustomerID() {
		return mCustomerID;
	}


	public void setCustomerID(int mCustomerID) {
		this.mCustomerID = mCustomerID;
	}


	public float getTotal() {
		return mTotal;
	}


	public void setTotal(float mTotal) {
		this.mTotal = mTotal;
	}


	public String getOrderDate() {
		return mOrderDate;
	}


	public void setOrderDate(String mOrderDate) {
		this.mOrderDate = mOrderDate;
	}


	public int getEmployeeID() {
		return mEmployeeID;
	}


	public void setEmployeeID(int mEmployeeID) {
		this.mEmployeeID = mEmployeeID;
	}
}