package model;

public class Product {
	private int mId;
	private String mName;
	private int mQuantity;
	private double mUnitPrice;
	private int mDiscount;
	
	
	public Product(int mId, String mName, int mQuantity, double mUnitPrice, int mDiscount) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mQuantity = mQuantity;
		this.mUnitPrice = mUnitPrice;
		this.mDiscount = mDiscount;
	}

	public int getId() {
		return mId;
	}
	
	public void setId(int id) {
		this.mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name){
		mName = name;
	}
	
	public int getQuantity() {
		return mQuantity;
	}
	
	public void setQuantity(int quantity) {
		this.mQuantity = quantity;
	}
	
	public double getUnitPrice() {
		return mUnitPrice;
	}
	
	public void setPrice(double price){
		mUnitPrice = price;
	}
	
	public int getDiscount() {
		return mDiscount;
	}
	
	public void setDiscount(int discount) {
		this.mDiscount = discount;
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
