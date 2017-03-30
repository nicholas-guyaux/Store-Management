package model;

public class Item {
	private Product mProduct;
	private int mQuantity;

	public Item(Product product, int quantity) {
		mProduct = product;
		mQuantity = quantity;
	}

	public int getId() {
		return mProduct.getId();
	}

	public Product getProduct() {
		return mProduct;
	}

	@Override
	public String toString() {
		return mProduct.getName() + "        " + mProduct.getUnitPrice() + "        " + mQuantity + "        " + String.format("%.2f", mProduct.getUnitPrice() * mQuantity);
	}
}