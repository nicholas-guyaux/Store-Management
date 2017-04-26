package model;

public class Product {
	private int mId;
	private String mName;
	private double mUnitPrice;
	
	public Product(int id, String name, double unitPrice) {
		mId = id;
		mName = name;
		mUnitPrice = unitPrice;
	}
	
	public int getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name){
		mName = name;
	}
	
	public void setPrice(double price){
		mUnitPrice = price;
	}

	public double getUnitPrice() {
		return mUnitPrice;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Product){
			Product otherProduct = (Product) other;
			return otherProduct.mId == mId;
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return new Integer(mId).hashCode();
	}
	@Override
	public String toString() {
		return getName() + "        " + getUnitPrice() + "        " + mId ;
	}
}
