package database_console;

public class Product {

	private float price;
	private int quantity;
	private int expDate;  //timestamp
	private int ProductID;
	
	float price(){
		return price;
	}
	void price(float price){
		if(price < 0) return;
		this.price = price;
	}
	int quantity(){
		return quantity;
	}
	void quantity(int quantity){
		if(quantity < 0) return;
		this.quantity = quantity;
	}
	int expDate(){
		return expDate;
	}
	void expDate(int expDate){
		this.expDate = expDate;
	}
	int getProductID(){
		return ProductID;
	}
}
