package controller;

import java.time.LocalDate;
import java.util.Date;

public class Product {
	private int productID;
	private String description;
	private int quantity;
	private float price;
	private Date expDate;
	
	public LocalDate localDate = LocalDate.now();
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		if(quantity > 0)
		{
			this.quantity = quantity;
		}
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		if (price > 0)
		{
			this.price = price;
		}
	}
		
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

}
