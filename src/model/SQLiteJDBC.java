package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC implements IDataAccess {

	public Connection c = null;
	
    public Statement stmt = null;
	
	public SQLiteJDBC(String fileName) {
	    try {
	      connectToDatabase();
	      createTables(fileName);
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	
	public void connectToDatabase() {
	    Class.forName("org.sqlite.JDBC");
	    c = DriverManager.getConnection("jdbc:sqlite:store.db");
		System.out.println("Opened database successfully");
	}
	
	
	//will put this in setup file
	public void createTables(String fileName) {
		//Create Products table
		stmt = c.createStatement();
		String sql = "CREATE TABLE Products " +
                   "(	`ProductID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + 
                   		"`Name`	TEXT NOT NULL, " + 
                   		"`Quantity`	INTEGER NOT NULL, " +
                   		"`Price`	REAL NOT NULL, " +
                   		"`Discount`	INTEGER NOT NULL DEFAULT 0)"; 
		stmt.executeUpdate(sql);
		stmt.close();
		
		//Query insert to table product with 5 value
		String query = "INSERT INTO Products VALUES(?, ?, ?, ?, ?)";
		//Create prepare statement
		PreparedStatement preparedStatement = c.prepareStatement(query);
		
		//Get list product from file text
		ArrayList<Product> listProduct = getListProductFromTextFile(fileName);
		//Insert list to db
		for(int i =0; i< listProducts.size(); i++) {
			preparedStatement.setInt(1,  listProduct.get(i).getId());
			preparedStatement.setString(2,  listProduct.get(i).getName());
			preparedStatement.setInt(3,  listProduct.get(i).getQuantity());
			preparedStatement.setDouble(4,  listProduct.get(i).getUnitPrice());
			preparedStatement.setInt(5,  listProduct.get(i).getDiscount());
			
			preparedStatement.executeUpdate();
			System.out.println("Insert success record:" + (i + 1));
		}
		/*
		//Query insert to table product with 5 value
		String query = "INSERT INTO Employees VALUES(?, ?, ?, ?)";
		//Create prepare statement
		PreparedStatement preparedStatement = c.prepareStatement(query);
		
		//Get list product from file text
		ArrayList<Employee> listEmployee = getListProductFromTextFile("/doc/employees.txt");
		//Insert list to db
		for(int i =0; i< listProducts.size(); i++) {
			preparedStatement.setInt(1,  listProduct.get(i).getId());
			preparedStatement.setString(2,  listProduct.get(i).getName());
			preparedStatement.setInt(3,  listProduct.get(i).getQuantity());
			preparedStatement.setDouble(4,  listProduct.get(i).getUnitPrice());
			preparedStatement.setInt(5,  listProduct.get(i).getDiscount());
			
			preparedStatement.executeUpdate();
			System.out.println("Insert success record:" + (i + 1));
		}
		*/
		//Create Employee table
		stmt = c.createStatement();
		String sql = "CREATE TABLE Employees " +
                   "(	`EmployeeID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + 
                   		"`UserName`	TEXT NOT NULL UNIQUE, " + 
                   		"`EmployeeName`	TEXT NOT NULL, " +
                   		"`EmployeePassword`	TEXT NOT NULL, " +
                   		"`IsManager`	INTEGER NOT NULL DEFAULT 0)"; 
		stmt.executeUpdate(sql);
		stmt.close();
		
		//Query insert to table product with 5 value
		String query = "INSERT INTO Employees VALUES(?, ?, ?, ?, ?)";
		//Create prepare statement
		PreparedStatement preparedStatement = c.prepareStatement(query);
		
		//Get list product from file text
		//Insert list to db
		preparedStatement.setInt(1,  1);
		preparedStatement.setString(2,  "admin");
		preparedStatement.setString(3,  "John Smith");
		preparedStatement.setString(4,  "password");
		preparedStatement.setInt(5,  1);
			
		preparedStatement.executeUpdate();
		System.out.println("Insert manager successful");
		}
		
		//Create Contains table
		stmt = c.createStatement();
		String sql = "CREATE TABLE Contains " +
                   "(	 `OrderID`	INTEGER NOT NULL, " + 
                   		"`ProductID`	INTEGER NOT NULL, " + 
                   		"`Quantity`	INTEGER NOT NULL, " +
                   		"`Price`	REAL NOT NULL, " +
                   		"`PRIMARY KEY(`OrderID`,`ProductID`)"; 
		stmt.executeUpdate(sql);
		stmt.close();
		
		//Create Customers table
		stmt = c.createStatement();
		String sql = "CREATE TABLE Customers " +
                   "(	`CustomerID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + 
                   		"`CustomerName`	TEXT NOT NULL, " + 
                   		"`LoyaltyPoints`	INTEGER NOT NULL DEFAULT 0)"; 
		stmt.executeUpdate(sql);
		stmt.close();
		
		//Create Orders table
		stmt = c.createStatement();
		String sql = "CREATE TABLE Orders " +
                   "(	`OrderID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + 
                   		"`CustomerID`	INTEGER, " + 
                   		"`TotalPrice`	REAL NOT NULL, " +
                   		"`OrderDate`	INTEGER NOT NULL, " +
                   		"`EmployeeID`	INTEGER NOT NULL)"; 
		stmt.executeUpdate(sql);
		stmt.close();
		
		
		c.close();
	    System.out.println("Tables created successfully");
	}
	
	public void insertOperation() {
		
	}

	@Override
	public Product getProductById(int ProductID) {
		String query = "SELECT * FROM PRODUCTS WHERE ID = ?";
		PreparedStatement preparedStatement = c.prepareStatement(query)
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		while ( rs.next() ) {
			int prodID = rs.getInt("ProductID");
			String  name = rs.getString("Name");
			int quantity  = rs.getInt("Quantity");
			float  price = rs.getFloat("Price");
			int discount = rs.getInt("Discount");
	    }
		Product temp = new Product(prodID, name, quantity, price, discount);
		rs.close();
		stmt.close();
      	c.close();
      	return temp;
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
		String query = "INSERT INTO Products VALUES(?, ?, ?, ?, ?)";
		//Create prepare statement
		PreparedStatement preparedStatement = c.prepareStatement(query);
		
		preparedStatement.setInt(1,  p.getId());
		preparedStatement.setString(2,  p.getName());
		preparedStatement.setInt(3,  p.getQuantity());
		preparedStatement.setDouble(4,  p.getUnitPrice());
		preparedStatement.setInt(5,  p.getDiscount());
		
		preparedStatement.executeUpdate();
		System.out.println("Insert product success");
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
		String query = "SELECT * FROM Orders WHERE ID = ?";
		PreparedStatement preparedStatement = c.prepareStatement(query)
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		while ( rs.next() ) {
			int orderID = rs.getInt("OrderID");
			int  custID = rs.getInt("CustomerID");
			float totalPrice  = rs.getFloat("TotalPrice");
			int  orderDate = rs.getint("OrderDate");
			int emplID = rs.getInt("EmployeeID");
	    }
		Order temp = new Order(prodID, custID, totalPrice, orderDate, emplID);
		rs.close();
		stmt.close();
      	c.close();
      	return temp;
	}
	}

	@Override
	public int getNextOrderId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static ArrayList<Product> getListProductFromTextFile(String filePath) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader  bReader = null;
		ArrayList<Product> listResult = new ArrayList<Product>();
		try {
			fis = new FileInputStream(filePath);
			isr = new InputStreamReader(fis);
			bReader = new BufferedReader(isr);
			//String save line get from text file
			String line = null;
			//Array save product
			String[]strProduct = null;
			
			//Loop and get all data in text file
			while(true) {
				//Get 1 line
				line = bReader.readLine();
				//Check line get empty, exit loop
				if(line==null) {
					break;
				} else {
					strProduct = line.split(",");
					listResult.add(new Product(Integer.parseInt(strProduct[0]), strProduct[1], Integer.parseInt(strProduct[2]), Double.parseDouble(strProduct[3]), Integer.parseInt(strProduct[4])));
				}
			}
		} catch (Exception e) {
			System.out.println("Read file error");
			e.printStackTrace();
		} finally {
			//close file
			try{
				bReader.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return listResult;
	}

}
