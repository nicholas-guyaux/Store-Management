package model;

import controller.Program;

public class Item {

	private int mOrderID;
	private int mProductID;
	private int mQuantity;
	private float mPrice;

	public Item(int orderID, int prodID, int quantity, float price) {
		mOrderID = orderID;
		mProductID = prodID;
		mQuantity = quantity;
		mPrice = price;
	}

	public int getProductId() {
		return mProductID;
	}

	public int getProductID() {
		return mProductID;
	}
	
	public int getQuantity(){
		return mQuantity;
	}
	
	public void setQuantity(int quant){
		this.mQuantity = quant;
	}

	@Override
	public String toString() {
		return Program.getInstance().getDataAccess().getProductById(mProductID).getName() + "        " + getmPrice() + "        " + getQuantity() + "        " + String.format("%.2f", getmPrice() * mQuantity);
	}

	public float getmPrice() {
		return mPrice;
	}

	public void setmPrice(float mPrice) {
		this.mPrice = mPrice;
	}

	public int getmOrderID() {
		return mOrderID;
	}

	public void setmOrderID(int orderID) {
		this.mOrderID = orderID;
	}
}