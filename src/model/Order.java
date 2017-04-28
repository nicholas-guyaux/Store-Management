package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import controller.Program;

public class Order {
	private Map<Product, Integer> mItemsList;
	private Map<Product, Integer> mReturnList;

	private int mOrderID;
	private int mCustomerID;
	private float mTotal;
	private int mEmployeeID;
	private double mReturnTotal;

	public Order(int id, int custID, float totalPrice) {
		mOrderID = id;
		mItemsList = new HashMap<Product, Integer>();
		mReturnList = new HashMap<Product, Integer>();
		mTotal = totalPrice;
		mReturnTotal = 0;
		mCustomerID = custID;
	}
	
	public Order(Order o) {
		this.mOrderID = o.getId();
		this.mCustomerID = o.getmCustomerID();
		this.mTotal = o.getTotal();
		mReturnTotal = 0;
		mItemsList = Program.getInstance().getDataAccess().getOrderAndProducts(mOrderID);
	}


	public int getmEmployeeID() {
		return mEmployeeID;
	}
	
	public Map<Product, Integer> getMItemsList() {
		return mItemsList;
	}


	public void setmEmployeeID(int mEmployeeID) {
		this.mEmployeeID = mEmployeeID;
	}



	public int getmCustomerID() {
		return mCustomerID;
	}



	public void setmCustomerID(int mCustomerID) {
		this.mCustomerID = mCustomerID;
	}



	/** makes an copy of copy */
//	public Order(Order copy){
//		this(copy.getId());
//		for (Item i : copy.getReturnList()){
//			if(i.getQuantity() > 0)
//				addItem(i.getProduct(), i.getQuantity());
//			else
//				returnProduct(i.getProduct(),i.getQuantity());
//		}
//		
//	}

	/** adds a product to the order */
	public void addItem(Product product, int quantity) {
		Integer q = mItemsList.get(product);
		if (q == null) {
			q = quantity;
		} else {
			q = q + quantity;
		}
		mItemsList.put(product, q);
		if(mCustomerID == 0) {
			mTotal = mTotal + product.getUnitPrice() * quantity;
		} else {
			mTotal = mTotal + (product.getUnitPrice()*(100-product.getDiscount())/100) * quantity;
		}
		
	}

	public void editItem(Product product, int quantity) {
		Integer q = mItemsList.get(product);
		if (q != null) {
			mItemsList.put(product, quantity);
			if(mCustomerID == 0) {
				mTotal = mTotal - product.getUnitPrice() * (q - quantity);
			} else {
				mTotal = mTotal - (product.getUnitPrice()*(100-product.getDiscount())/100 )* (q - quantity);
			}
		}
	}

	public void removeItem(Product product) {
		Integer q = mItemsList.get(product);
		if (q != null) {
			mItemsList.remove(product);
			mTotal = mTotal - product.getUnitPrice() * q;
			if(mCustomerID == 0) {
				mTotal = mTotal - product.getUnitPrice() * q;
			} else {
				mTotal = mTotal - (product.getUnitPrice()*(100-product.getDiscount())/100 )* q;
			}
		}
	}

	public List<Item> getOrderList() {
		List<Item> itemList = new LinkedList<>();
		for (Entry<Product, Integer> entry : mItemsList.entrySet()) {
			Product p = entry.getKey();
			Integer q = entry.getValue();
			itemList.add(new Item(p, q));
		}
		return itemList;
	}

	public float getTotal() {
		return mTotal;
	}
	
	public int getCustomerID() {
		return mCustomerID;
	}
	
	public int getId(){
		return mOrderID;
	}
	
	public int getQuantityOfProduct(Product p){
		if(!mItemsList.containsKey(p))
			return 0;
		if(!mReturnList.containsKey(p))
			return mItemsList.get(p);
		return mItemsList.get(p)-mReturnList.get(p);
	}
	
	public List<Item> getReturnList(){
		List<Item> itemList = new LinkedList<>();
		for (Entry<Product, Integer> entry : mItemsList.entrySet()) {
			Product p = entry.getKey();
			Integer q = entry.getValue();
			itemList.add(new Item(p, q));
		}
		for (Entry<Product, Integer> entry : mReturnList.entrySet()) {
			Product p = entry.getKey();
			Integer q = entry.getValue();
			itemList.add(new Item(p, -q));
		}
		return itemList;
	}
	
	public double getReturnPrice(){
		return (mTotal*1.06) - ((mTotal - mReturnTotal) * 1.06);
	}
	public void returnProduct(Product product, int quantity, int cust){
		Integer q = mReturnList.get(product);
		if (q == null) {
			q = quantity;
		} else {
			q = q + quantity;
		}
		mReturnList.put(product, q);
		if(cust!=0) {
			mReturnTotal = mReturnTotal + (product.getUnitPrice()*(100-product.getDiscount())/100) * quantity;
		} else {
			mReturnTotal = mReturnTotal + product.getUnitPrice() * quantity;
		}
	}
	public void editReturn(Product product, int quantity) {
		Integer q = mReturnList.get(product);
		if (q != null) {
			mReturnList.put(product, quantity);
			mReturnTotal = mReturnTotal - product.getUnitPrice() * (q - quantity);
		}
	}

	public void removeReturn(Product product) {
		Integer q = mReturnList.get(product);
		if (q != null) {
			mReturnList.remove(product);
			mReturnTotal = mReturnTotal - product.getUnitPrice() * q;
		}
	}
	
	public void saveReturn(){
		for (Entry<Product, Integer> entry : mReturnList.entrySet()) {
			Product p = entry.getKey();
			Integer q = entry.getValue();
			if(q == mItemsList.get(p))
				removeItem(p);
			else
				editItem(p, mItemsList.get(p) - q);
		}
		mReturnList = new HashMap<Product, Integer>();
		mReturnTotal = 0;
	}
	
}
