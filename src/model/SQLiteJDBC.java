package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class SQLiteJDBC implements IDataAccess {

	public Connection c = null;
	
    public Statement stmt = null;
    
	Employee currentUser = null;
	
	public SQLiteJDBC() {
	    try {
	    	File f = new File("score.db");
	    	if(!f.exists()) {
	    		System.out.println("Database does not exist");
	    	    f.createNewFile();
	    		System.out.println("Created database");
	    	 	createTables("doc/products.txt");
	    		System.out.println("Created tables");
	    	}
	    	System.out.println("Database exists");
	    	connectToDatabase();
	    	System.out.println("Connected to database");
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	}
	
	public void connectToDatabase() {
	    try {
			Class.forName("org.sqlite.JDBC");
		
			c = DriverManager.getConnection("jdbc:sqlite:store.db");
	    } catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Opened database successfully");
	}
	
	
	//will put this in setup file
	public void createTables(String fileName) {
		//Create Products table
		try {
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
			for(int i =0; i< listProduct.size(); i++) {
				preparedStatement.setInt(1,  listProduct.get(i).getId());
				preparedStatement.setString(2,  listProduct.get(i).getName());
				preparedStatement.setInt(3,  listProduct.get(i).getQuantity());
				preparedStatement.setDouble(4,  listProduct.get(i).getUnitPrice());
				preparedStatement.setInt(5,  listProduct.get(i).getDiscount());
				
				preparedStatement.executeUpdate();
				System.out.println("Insert success record:" + (i + 1));
			}
			
			//Create Employee table
			stmt = c.createStatement();
			sql = "CREATE TABLE Employees " +
	                   "(	`EmployeeID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + 
	                   		"`UserName`	TEXT NOT NULL UNIQUE, " + 
	                   		"`EmployeeName`	TEXT NOT NULL, " +
	                   		"`EmployeePassword`	TEXT NOT NULL, " +
	                   		"`IsManager`	INTEGER NOT NULL DEFAULT 0)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			
			//Query insert to table product with 5 value
			query = "INSERT INTO Employees VALUES(?, ?, ?, ?, ?)";
			//Create prepare statement
			preparedStatement = c.prepareStatement(query);
			
			//Get list product from file text
			//Insert list to db
			preparedStatement.setInt(1,  1);
			preparedStatement.setString(2,  "admin");
			preparedStatement.setString(3,  "John Smith");
			preparedStatement.setString(4,  "password");
			preparedStatement.setInt(5,  1);
				
			preparedStatement.executeUpdate();
			System.out.println("Insert manager successful");
			
			
			//Create Contains table
			stmt = c.createStatement();
			sql = "CREATE TABLE Contains " +
	                   "(	 `OrderID`	INTEGER NOT NULL, " + 
	                   		"`ProductID`	INTEGER NOT NULL, " + 
	                   		"`Quantity`	INTEGER NOT NULL, " +
	                   		"`Price`	REAL NOT NULL, " +
	                   		"`PRIMARY KEY(`OrderID`,`ProductID`)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			
			//Create Customers table
			stmt = c.createStatement();
			sql = "CREATE TABLE Customers " +
	                   "(	`CustomerID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + 
	                   		"`CustomerName`	TEXT NOT NULL, " + 
	                   		"`LoyaltyPoints`	INTEGER NOT NULL DEFAULT 0)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			
			//Create Orders table
			stmt = c.createStatement();
			sql = "CREATE TABLE Orders " +
	                   "(	`OrderID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + 
	                   		"`CustomerID`	INTEGER, " + 
	                   		"`TotalPrice`	REAL NOT NULL, " +
	                   		"`OrderDate`	INTEGER NOT NULL, " +
	                   		"`EmployeeID`	INTEGER NOT NULL)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			
			
			
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    System.out.println("Tables created successfully");
	}


	@Override
	public Product getProductById(int id) {
		String query = "SELECT * FROM Products WHERE ProductID = " + id;
		PreparedStatement preparedStatement;
		Product temp = null;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next() ) {
				int prodID = rs.getInt("ProductID");
				String  name = rs.getString("Name");
				int quantity  = rs.getInt("Quantity");
				float  price = rs.getFloat("Price");
				int discount = rs.getInt("Discount");
				temp = new Product(prodID, name, quantity, price, discount);
		    }
			rs.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      	return temp;
	}

	@Override
	public List<Product> getInventoryList(String search) {
		String query;
		System.out.println("getting inventory");
		if(search == "")
		{
			query = "SELECT * FROM Products";
		} else {
			query = "SELECT * FROM Products WHERE ProductID = " + search;
		}
		System.out.println(query);
		List<Product> inventory = new ArrayList<Product>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while ( rs.next() ) {
				int prodID = rs.getInt("ProductID");
				String  name = rs.getString("Name");
				int quantity  = rs.getInt("Quantity");
				float  price = rs.getFloat("Price");
				int discount = rs.getInt("Discount");
				Product temp = new Product(prodID, name, quantity, price, discount);
				inventory.add(temp);
				System.out.println(temp.toString());
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("return inventory");
      	return inventory;
	}

	@Override
	public void removeProductById(int id) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM Products WHERE ProductID = " + id;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			//preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			System.out.println("Product removed sucessfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      	return;
	}

	@Override
	public void modifyProductById(int id, Product p) {
		String query = "UPDATE Products SET Name = ?, Quantity = ?, Price = ?, Discount = ? WHERE ProductID = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setString(1, p.getName());
			preparedStatement.setInt(2, p.getQuantity());
			preparedStatement.setFloat(3, p.getUnitPrice());
			preparedStatement.setInt(4, p.getDiscount());
			preparedStatement.setInt(5, p.getId());
			
			preparedStatement.executeUpdate();
			System.out.println("Product update successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return;
	}

	@Override
	public void addProduct(Product p) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO Products (Name, Quantity, Price, Discount) VALUES(?, ?, ?, ?)";
		//Create prepare statement
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setString(1,  p.getName());
			preparedStatement.setInt(2,  p.getQuantity());
			preparedStatement.setDouble(3,  p.getUnitPrice());
			preparedStatement.setInt(4,  p.getDiscount());
			
			preparedStatement.executeUpdate();
			System.out.println("Insert product success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}

	@Override
	public boolean login(String user, String pass) {
		boolean success = false;
		System.out.println("Trying login");
		Employee temp = null;
		String query = "SELECT EmployeeID, UserName, EmployeeName, EmployeePassword, IsManager FROM Employees WHERE UserName = ?";// + user;
		try {
			System.out.println("login try block");
			PreparedStatement preparedStatement = c.prepareStatement(query);
			preparedStatement.setString(1, user);
			System.out.println("Looking user up in database");
			ResultSet rs = preparedStatement.executeQuery();
			
			while ( rs.next() ) {
				int emploID = rs.getInt("EmployeeID");
				String  userName = rs.getString("UserName");
				String name  = rs.getString("EmployeeName");
				String  password = rs.getString("EmployeePassword");
				boolean isManage = rs.getInt("IsManager") != 0;
				temp = new Employee(emploID, userName, name, password, isManage);
				System.out.println("User exists");
			}
			System.out.println(pass);
			if(temp != null && pass.equals(temp.getPassword())) {
				currentUser = temp;
				System.out.println(temp.isManager());
				success = true;
			} 
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
      	return success;
	}

	@Override
	public Employee getCurrentUser() {
		// TODO Auto-generated method stub
		return currentUser;
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub
		currentUser = null;
	}

	@Override
	public Employee getEmployeeById(int id) {
		String query = "SELECT * FROM Employees WHERE EmployeeID = ?";
		Employee temp = null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				int emplID = rs.getInt("EmployeeID");
				String  userName = rs.getString("UserName");
				String name  = rs.getString("EmployeeName");
				String  pass = rs.getString("EmployeePassword");
				boolean isManage = rs.getBoolean("IsManager");
				temp = new Employee(emplID, userName, name, pass, isManage);
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      	return temp;
	}

	@Override
	public List<Employee> getEmployeeList() {
		String query = "SELECT * FROM Employees";
		List<Employee> staff = new ArrayList<Employee>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			staff = new ArrayList<Employee>();
			while ( rs.next() ) {
				int emplID = rs.getInt("EmployeeID");
				String  userName = rs.getString("UserName");
				String name  = rs.getString("EmployeeName");
				String  pass = rs.getString("EmployeePassword");
				boolean isManage = rs.getBoolean("IsManager");
				Employee temp = new Employee(emplID, userName, name, pass, isManage);
				
				staff.add(temp);
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      	return staff;
	}

	@Override
	public void removeEmployeeById(int id) {
		// TODO Auto-generated method stub
		String query = "DELETE * FROM Employees WHERE EmployeeID = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Employee removed sucessfully");
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      	return;
	}

	@Override
	public void modifyEmployeeById(int id, Employee p) {
		String query = "UPDATE Employees SET UserName = ?, EmployeeName = ?, EmployeePassword = ?, isManager = ? WHERE EmployeeID = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setString(1, p.getUsername());
			preparedStatement.setString(2, p.getName());
			preparedStatement.setString(3, p.getPassword());
			preparedStatement.setBoolean(4, p.isManager());
			preparedStatement.setInt(5, p.getId());
			
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("Employee update successful");;
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return;

	}

	@Override
	public void addEmployee(Employee p) {
		String query = "INSERT INTO Employees (UserName, EmployeeName, EmployeePassword, IsManager) VALUES(?, ?, ?, ?)";
		//Create prepare statement
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setString(1,  p.getUsername());
			preparedStatement.setString(2,  p.getName());
			preparedStatement.setString(3,  p.getPassword());
			preparedStatement.setBoolean(4,  p.isManager());
			
			preparedStatement.executeUpdate();
			System.out.println("Insert employee success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;

	}

	@Override
	public void SaveOrder(Order o) {
		String query = "INSERT INTO Orders (CustomerID, TotalPrice, OrderDate, EmployeeID) VALUES(?, ?, ?, ?, ?)";
		//Create prepare statement
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setInt(1,  o.getmCustomerID());
			preparedStatement.setFloat(2,  o.getTotal());
			preparedStatement.setString(3, new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
			preparedStatement.setInt(4,  currentUser.getId());
			
			preparedStatement.executeUpdate();
			System.out.println("Insert order success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	


	}

	@Override
	public Order getOrderById(int id) {
		String query = "SELECT * FROM Orders WHERE ID = ?";
		Order temp = null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				int orderID = rs.getInt("OrderID");
				int  custID = rs.getInt("CustomerID");
				float totalPrice  = rs.getFloat("TotalPrice");
				temp = new Order(orderID, custID, totalPrice);
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
      	return temp;
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
					listResult.add(new Product(Integer.parseInt(strProduct[0]), strProduct[1], Integer.parseInt(strProduct[2]), Float.parseFloat(strProduct[3]), Integer.parseInt(strProduct[4])));
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
