package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IDataAccess {
	public Product getProductById(int id);
	public List<Product> getInventoryList(String search);
	public void removeProductById(int id);
	public void modifyProductById(int id, Product p);
	public void addProduct(Product p);
	
	public boolean login(String user,String pass);
	public Employee getCurrentUser();
	public void logOut();

	public Employee getEmployeeById(int id);
	public List<Employee> getEmployeeList();
	public void removeEmployeeById(int id);
	public void modifyEmployeeById(int id, Employee p);
	public void addEmployee(Employee p);
	
	public void SaveOrder(Order o);
	public Order getOrderById(int id);
	public int getNextOrderId();
	
	public boolean checkCustomerById(int id);
	public Map<Product, Integer> getOrderAndProducts(int mOrderID);
	public String getCustomerNameById(int custID);
	int getLoyaltyPointsById(int custID);
	void addLoyaltyPoints(int custID, int points);
	void setLoyaltyPoints(int custID, int points);
	public int getQuantityByOrderAndProdId(int orderID, int prodID);
	public float getPriceByOrderAndProdId(int orderID, int prodID);
	public ArrayList<Item> getItemsByOrderID(int orderID);
	public void removeItemsByOrderID(int orderID);
	
}
