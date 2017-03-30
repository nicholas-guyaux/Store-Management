package model;

import java.util.HashMap;
import java.util.Map;

public class MockDataAccess implements IDataAccess {

	private Map<Integer, Product> mProducts;

	public MockDataAccess() {
		mProducts = new HashMap<>();
		mProducts.put(1, new Product(1, "Apple", 3.99));
		mProducts.put(2, new Product(2, "Apple Pie", 6.99));
		mProducts.put(3, new Product(3, "Apple Juice", 2.99));
		mProducts.put(4, new Product(4, "Apple Watch", 399.99));
	}

	public Product getProductById(int id) {
		return mProducts.get(id);
	}
}
