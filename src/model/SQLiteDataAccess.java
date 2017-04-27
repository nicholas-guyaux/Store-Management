package model;

public class SQLiteDataAccess implements IDataAccess {

	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product[] getInventoryList(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeProductById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyProductById(int id, Product p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addProduct(Product p) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean login(String user, String pass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee[] getEmployeeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeEmployeeById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyEmployeeById(int id, Employee p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addEmployee(Employee p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void SaveOrder(Order o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Order getOrderById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNextOrderId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
