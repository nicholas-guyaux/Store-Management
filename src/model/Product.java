package model;

public class Product {
	private int mId;
	private String mName;
	private int mQuantity;
	private float mUnitPrice;
	private int mDiscount;
	
	
	public Product(int mId, String mName, int mQuantity, float mUnitPrice, int mDiscount) {
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
	
	public float getUnitPrice() {
		return mUnitPrice;
	}
	
	public void setPrice(float price){
		mUnitPrice = price;
	}
	
	public int getDiscount() {
		return mDiscount;
	}
	
	public void setDiscount(int discount) {
		this.mDiscount = discount;
	}

	
	@Override
	public String toString() {
		return "ID:" + mId + "    " + getName() + "    Price:" + getUnitPrice() + "    Quantity:" + mQuantity + "    Discount:" + mDiscount + "%";
	}
}
