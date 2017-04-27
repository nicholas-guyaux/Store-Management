package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MockDataAccess implements IDataAccess {

	private Map<Integer, Product> mProducts;
	private Map<Integer, Employee> mEmployees;
	private Map<Integer, Order> mOrders;
	Employee currentUser = null;

	public MockDataAccess() {
		mProducts = new HashMap<>();
		mProducts.put(1, new Product(1, "Apple", 12, (float) 3.99, 0));
		mProducts.put(2, new Product(2, "Apple Pie", 10, (float) 6.99, 0));
		mProducts.put(3, new Product(3, "Apple Juice", 12, (float) 2.99, 0));
		mProducts.put(4, new Product(4, "Apple Watch", 10, (float) 399.99, 0));
		

		mEmployees = new HashMap<>();
		mEmployees.put(1, new Employee(1, "jordan","Jordan Knudsen", "password", false));
		mEmployees.put(2, new Employee(2,"nick","Nicholas Guyaux",  "password", false));
		mEmployees.put(3, new Employee(3,"smith","Mr. Smith",  "password", true));
		
		mOrders = new HashMap<>();
	}

	public Product getProductById(int id) {
		return mProducts.get(id);
	}

	public List<Product> getInventoryList(String search) {
		List<Product> list = new ArrayList<Product>();
		if(search != ""){
			if(mProducts.get(Integer.parseInt(search)) == null)
				return null;
			list.add(mProducts.get(Integer.parseInt(search)));
			return list;			
		}
		//list.addAll((Product) mProducts.values().toArray());
		
		return list;
	}

	@Override
	public void removeProductById(int id) {
		mProducts.remove(id);
	}

	@Override
	public void modifyProductById(int id, Product p) {
		mProducts.put(id, p);
	}

	@Override
	public void addProduct(Product p) {
		mProducts.put(p.getId(),p);
	}

	@Override
	public boolean login(String user, String pass) {
		Iterator<Employee> itr = mEmployees.values().iterator();
		while(itr.hasNext()){
			Employee next = itr.next();
			if(next.getUsername().equals(user) && next.getPassword().equals(pass)){
				currentUser = next;
			}
		}
		if(currentUser != null)
			return true;
		else
			return false;
	}

	@Override
	public Employee getCurrentUser() {
		return currentUser;
	}

	@Override
	public void logOut() {
		currentUser = null;		
	}
	
	@Override
	public Employee getEmployeeById(int id){
		return mEmployees.get(id);
	}
	
	@Override
	public List<Employee> getEmployeeList() {
		return  (List<Employee>) mEmployees.values().toArray(new Employee[0]);
	}

	@Override
	public void removeEmployeeById(int id) {
		mEmployees.remove(id);		
	}

	@Override
	public void modifyEmployeeById(int id, Employee p) {
		mEmployees.put(id, p);
	}

	@Override
	public void addEmployee(Employee p) {
		mEmployees.put(p.getId(),p);
	}

	@Override
	public void SaveOrder(Order o) {
		mOrders.put(o.getId(), o);
	}

	@Override
	public Order getOrderById(int id) {
		return  mOrders.get(id);
	}

	@Override
	public int getNextOrderId() {
		int i = 1;
		for(i = 1;i<Integer.MAX_VALUE;i++){
			if(getOrderById(i) == null)
				break;
		}
		return i;
	}
}
