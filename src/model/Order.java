package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Order {
	private Map<Product, Integer> mItemsList;

	private double mTotal;

	public Order() {
		mItemsList = new HashMap<Product, Integer>();
		mTotal = 0;
	}

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
}
