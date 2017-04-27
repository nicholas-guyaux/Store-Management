package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Order {
	private Map<Product, Integer> mItemsList;
	private Map<Product, Integer> mReturnList;

	private int mOrderID;
	private int mCustomerID;
	private float mTotal;
	private int mOrderDate;
	private int mEmployeeID;
	private double mReturnTotal;

	public Order(int id) {
		mId = id;
		mItemsList = new HashMap<Product, Integer>();
		mReturnList = new HashMap<Product, Integer>();
		mTotal = 0;
		mReturnTotal = 0;
	}
	
	/** makes an copy of copy */
	public Order(Order copy){
		this(copy.getId());
		for (Item i : copy.getReturnList()){
			if(i.getQuantity() > 0)
				addItem(i.getProduct(), i.getQuantity());
			else
				returnProduct(i.getProduct(),i.getQuantity());
		}
		
	}

	/** adds a product to the order */
	public void addItem(Product product, int quantity) {
		Integer q = mItemsList.get(product);
		if (q == null) {
			q = quantity;
		} else {
			q = q + quantity;
		}
		mItemsList.put(product, q);
		mTotal = mTotal + product.getUnitPrice() * quantity;
	}

	public void editItem(Product product, int quantity) {
		Integer q = mItemsList.get(product);
		if (q != null) {
			mItemsList.put(product, quantity);
			mTotal = mTotal - product.getUnitPrice() * (q - quantity);
		}
	}

	public void removeItem(Product product) {
		Integer q = mItemsList.get(product);
		if (q != null) {
			mItemsList.remove(product);
			mTotal = mTotal - product.getUnitPrice() * q;
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

	public double getTotal() {
		return mTotal;
	}
	
	public int getId(){
		return mId;
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
	public void returnProduct(Product product, int quantity){
		Integer q = mReturnList.get(product);
		if (q == null) {
			q = quantity;
		} else {
			q = q + quantity;
		}
		mReturnList.put(product, q);
		mReturnTotal = mReturnTotal + product.getUnitPrice() * quantity;
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
