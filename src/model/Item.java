package model;

import controller.Program;

public class Item {

	private int mOrderID;
	private Product mProduct;
	private int mQuantity;
	private float mPrice;

	public Item(int orderID, int prodID, int quantity, float price) {
		mOrderID = orderID;
		mProduct = Program.getInstance().getDataAccess().getProductById(prodID);
		mQuantity = quantity;
		mPrice = price;
	}

	public int getId() {
		return mProduct.getId();
	}

	public Product getProduct() {
		return mProduct;
	}
	
	public int getQuantity(){
		return mQuantity;
	}

	@Override
	public String toString() {
		return mProduct.getName() + "        " + getmPrice() + "        " + getQuantity() + "        " + String.format("%.2f", getmPrice() * mQuantity);
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