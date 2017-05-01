package model;

public class ProductReport {
	private String mDate;
	private int mProductID;
	private int quantitySold;
	
	
	public ProductReport(String date, int prodID, int quantSold) {
		setDate(date);
		setProductID(prodID);
		setQuantSold(quantSold);
	}

	
	@Override
	public String toString() {
		return getDate() + "    ProductID: " + getProductID() + "    QuantitySold: " + getQuantSold();		
	}



	public String getDate() {
		return mDate;
	}


	public void setDate(String mDate) {
		this.mDate = mDate;
	}


	public int getProductID() {
		return mProductID;
	}


	public void setProductID(int mProductID) {
		this.mProductID = mProductID;
	}


	public int getQuantSold() {
		return quantitySold;
	}


	public void setQuantSold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
}
