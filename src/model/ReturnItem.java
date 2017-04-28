package model;

import controller.Program;

public class ReturnItem {

	private Order mOrder;
	private Product mProduct;
	private int mQuantity;
	private float mPrice;

	public ReturnItem(int orderID, int prodID) {
		setmOrder(Program.getInstance().getDataAccess().getOrderById(orderID));
		mProduct = Program.getInstance().getDataAccess().getProductById(prodID);
		mQuantity = Program.getInstance().getDataAccess().getQuantityByOrderAndProdId(orderID, prodID);
		mPrice = Program.getInstance().getDataAccess().getPriceByOrderAndProdId(orderID, prodID);
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

	public Order getmOrder() {
		return mOrder;
	}

	public void setmOrder(Order mOrder) {
		this.mOrder = mOrder;
	}
}