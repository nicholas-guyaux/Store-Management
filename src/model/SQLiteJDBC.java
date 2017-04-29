package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;

public class SQLiteJDBC implements IDataAccess {

	public Connection c = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
    public Statement stmt = null;
    
	Employee currentUser = null;
	
	public SQLiteJDBC() {
	    try {
	    	File f = new File("store.db");
	    	if(!f.exists()) {
	    		System.out.println("Database does not exist");
	    	    f.createNewFile();
	    		System.out.println("Created database");
	    		System.out.println("Database exists");
		    	connectToDatabase();
		    	System.out.println("Connected to database");
	    	 	createTables("doc/products.xlsx");
	    		System.out.println("Created tables");
	    	} else {
	    		System.out.println("Database exists");
		    	connectToDatabase();
		    	System.out.println("Connected to database");
	    	}   	
	    	
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
		
			String sql = "CREATE TABLE IF NOT EXISTS Products " +
	                   "(	`ProductID`	INTEGER NOT NULL PRIMARY KEY, " + 
	                   		"`Name`	TEXT NOT NULL, " + 
	                   		"`Quantity`	INTEGER NOT NULL, " +
	                   		"`Price`	REAL NOT NULL, " +
	                   		"`Discount`	INTEGER NOT NULL DEFAULT 0)"; 
			stmt.execute(sql);
			System.out.println("create Products");
			stmt.close();
			
			//Query insert to table product with 5 value
			String query = "INSERT INTO Products VALUES(?, ?, ?, ?, ?)";
			//Create prepare statement
			PreparedStatement preparedStatement = c.prepareStatement(query);
			
			//Get list product from file text
			//ArrayList<Product> listProduct = getListProductFromTextFile(fileName);
			ArrayList<Product> listProduct = getProductListFromXLS(fileName);
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
			sql = "CREATE TABLE IF NOT EXISTS Employees " +
	                   "(	`EmployeeID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + 
	                   		"`UserName`	TEXT NOT NULL UNIQUE, " + 
	                   		"`EmployeeName`	TEXT NOT NULL, " +
	                   		"`EmployeePassword`	TEXT NOT NULL, " +
	                   		"`IsManager`	INTEGER NOT NULL DEFAULT 0)"; 
			stmt.execute(sql);
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
			sql = "CREATE TABLE IF NOT EXISTS Contains " +
	                   "(	 `OrderID`	INTEGER NOT NULL, " + 
	                   		"`ProductID`	INTEGER NOT NULL, " + 
	                   		"`Quantity`	INTEGER NOT NULL, " +
	                   		"`Price`	REAL NOT NULL, " +
	                   		"PRIMARY KEY(`OrderID`,`ProductID`) )"; 
			stmt.execute(sql);
			stmt.close();
			
			System.out.println("Contains created successfully");
			
			//Create Customers table
			stmt = c.createStatement();
			sql = "CREATE TABLE IF NOT EXISTS Customers " +
	                   "(	`CustomerID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + 
	                   		"`CustomerName`	TEXT NOT NULL, " + 
	                   		"`LoyaltyPoints`	INTEGER NOT NULL DEFAULT 0)"; 
			stmt.execute(sql);
			stmt.close();
			
			//Query insert to table product with 5 value
			query = "INSERT INTO Customers VALUES(?, ?, ?)";
			//Create prepare statement
			preparedStatement = c.prepareStatement(query);
			
			//Get list product from file text
			//Insert list to db
			preparedStatement.setInt(1,  1);
			preparedStatement.setString(2,  "Billy Joel");
			preparedStatement.setInt(3,  10000);
				
			preparedStatement.executeUpdate();
			System.out.println("Insert manager successful");
			
			//Create Orders table
			stmt = c.createStatement();
			sql = "CREATE TABLE IF NOT EXISTS Orders " +
	                   "(	`OrderID`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + 
	                   		"`CustomerID`	INTEGER, " + 
	                   		"`TotalPrice`	REAL NOT NULL, " +
	                   		"`OrderDate`	INTEGER NOT NULL, " +
	                   		"`EmployeeID`	INTEGER NOT NULL)"; 
			stmt.execute(sql);
			stmt.close();
			
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
		String query = "SELECT * FROM Employees WHERE EmployeeID = " + id;
		Employee temp = null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
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
		String query = "DELETE FROM Employees WHERE EmployeeID = " + id;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.executeUpdate();
			System.out.println("Employee removed sucessfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
      	return;
	}

	@Override
	public void modifyEmployeeById(int id, Employee p) {
		String query = "UPDATE Employees SET UserName = ?, EmployeeName = ?, EmployeePassword = ?, isManager = ? WHERE EmployeeID = " + id;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setString(1, p.getUsername());
			preparedStatement.setString(2, p.getName());
			preparedStatement.setString(3, p.getPassword());
			preparedStatement.setBoolean(4, p.isManager());
			
			preparedStatement.executeUpdate();
			System.out.println("Employee update successful");
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
		String query = "insert or replace into Orders (OrderID, CustomerID, TotalPrice, OrderDate, EmployeeID) VALUES(?, ?, ?, ?, ?)";
		//Create prepare statement
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.setInt(1,  o.getId());
			preparedStatement.setInt(2,  o.getmCustomerID());
			preparedStatement.setFloat(3,  o.getTotal());
			preparedStatement.setString(4, o.getDate());
			preparedStatement.setInt(5,  currentUser.getId());
			
			preparedStatement.executeUpdate();
			System.out.println("Insert order success");
			query = "insert or replace into Contains (OrderID, ProductID, Quantity, Price) VALUES(?, ?, ?, ?)";
			preparedStatement = c.prepareStatement(query);
			//Insert list to db
			for(int i =0; i< o.getItemList().size(); i++) {
				preparedStatement.setInt(1,  o.getId());
				preparedStatement.setInt(2,  o.getItemList().get(i).getProductID());
				preparedStatement.setInt(3,  o.getItemList().get(i).getQuantity());
				preparedStatement.setFloat(4,  o.getItemList().get(i).getmPrice());
				
				preparedStatement.executeUpdate();
				System.out.println("Insert success record:" + (i + 1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;

	}

	@Override
	public Order getOrderById(int id) {
		String query = "SELECT * FROM Orders WHERE OrderID = ?";
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
		PreparedStatement preparedStatement;
		String query = "SELECT * FROM Orders";
		int max = 0;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				int id = rs.getInt("OrderID");
				if(id>max)max=id;
			}
			rs.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return max + 1;
	}
	
	@Override
	public boolean checkCustomerById(int id) {
		String query = "SELECT * FROM Customers WHERE CustomerID = " + id;
		boolean temp = false;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				int custID = rs.getInt("CustomerID");
				if(custID == id) temp = true;
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
      	return temp;
	}
	
	


	@Override
	public String getCustomerNameById(int custID) {
		String query = "SELECT * FROM Customers WHERE CustomerID = " + custID;
		String temp = null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				String name = rs.getString("CustomerName");
				temp = name;
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
      	return temp;
	}
	
	@Override
	public int getLoyaltyPointsById(int custID) {
		String query = "SELECT * FROM Customers WHERE CustomerID = " + custID;
		int temp = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				int points = rs.getInt("LoyaltyPoints");
				temp = points;
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
      	return temp;
	}
	
	@Override
	public void addLoyaltyPoints(int custID, int points) {
		String query = "UPDATE Customers SET LoyaltyPoints = " + (getLoyaltyPointsById(custID) + points) +" WHERE CustomerID = " + custID;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);			
			preparedStatement.executeUpdate();
			System.out.println("LoyaltyPoint update successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	
	@Override
	public void setLoyaltyPoints(int custID, int points) {
		String query = "UPDATE Customers SET LoyaltyPoints = " + points +" WHERE CustomerID = " + custID;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);			
			preparedStatement.executeUpdate();
			System.out.println("LoyaltyPoint update successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	
	
	@Override
	public ArrayList<Item> getItemsByOrderID (int orderID) {
		ArrayList<Item> itemList = new ArrayList<Item> ();
		int prodID;
		int quantity;
		float price;
		String query = "SELECT * FROM Contains WHERE OrderID = " + orderID;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				prodID = rs.getInt("ProductID");
				quantity = rs.getInt("Quantity");
				price = rs.getInt("Price");
				itemList.add(new Item(orderID, prodID, quantity, price));
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return itemList;
		
	}
	
	@Override
	public void removeItemsByOrderID (int orderID) {
		String query = "DELETE FROM Contains WHERE OrderID = " + orderID;
		System.out.println("trying to remove from contains");
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return;
	}
	
	
	

	public ArrayList<Product> getProductListFromXLS (String fileName) {
		ArrayList<Product> listResult = new ArrayList<Product>();
		int prodID = 0;
		String name = null;
		int quantity = 0;
		float price = 0;
		int discount = 0;
		try {
			
	        FileInputStream inputStream = new FileInputStream(new File(fileName));
	        Workbook workbook = new XSSFWorkbook(inputStream);
	        Sheet firstSheet = workbook.getSheetAt(0);
	        Iterator<Row> iterator = firstSheet.iterator();
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	             
	            while (cellIterator.hasNext()) {
	                Cell nextCell = cellIterator.next();
	                int columnIndex = nextCell.getColumnIndex();
	                
	                switch (columnIndex) {
	                case 0:
	                	prodID = (int) nextCell.getNumericCellValue();
	                	break;
	                case 1:
	                	name = nextCell.getStringCellValue();
	                	break;
	                case 2:
	                	quantity = (int) nextCell.getNumericCellValue();
	                	break;
	                case 3:
	                	price = (float) nextCell.getNumericCellValue();
	                	break;
	                case 4:
	                	discount = (int) nextCell.getNumericCellValue();
	                	break;
	                }
	            }
	            listResult.add(new Product(prodID, name, quantity, price, discount));
	        }
	         
       
			workbook.close();
		    inputStream.close();
		} catch (IOException e) {
		
    	// TODO Auto-generated catch block
		e.printStackTrace();
		}
        
        return listResult;
    }

	

	@Override
	public ArrayList<CustomerReport> getCustomerReportList(int range) {
		ArrayList<CustomerReport> customerList = new ArrayList<CustomerReport> ();
		int orderID;
		int custID;
		float total;
		String date;
		int employID;
		String query = "SELECT * FROM Orders WHERE OrderDate BETWEEN " + getBackDate(range) + " AND " + getBackDate(-1);
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				orderID = rs.getInt("OrderID");
				custID = rs.getInt("CustomerID");
				total = rs.getFloat("TotalPrice");
				date = rs.getString("OrderDate");
				employID = rs.getInt("EmployeeID");
				if(custID != 0) {
					customerList.add(new CustomerReport(orderID, custID, total, date, employID));
				}
				
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return customerList;
	}

	@Override
	public ArrayList<OrderReport> getOrderReportList(int range) {
		ArrayList<OrderReport> orderList = new ArrayList<OrderReport> ();
		int orderID;
		int custID;
		float total;
		String date;
		int employID;
		String query = "SELECT * FROM Orders WHERE OrderDate BETWEEN " + getBackDate(range) + " AND " + getBackDate(-1);
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				orderID = rs.getInt("OrderID");
				custID = rs.getInt("CustomerID");
				total = rs.getFloat("TotalPrice");
				date = rs.getString("OrderDate");
				employID = rs.getInt("EmployeeID");
				orderList.add(new OrderReport(orderID, custID, total, date, employID));
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return orderList;
	}

	@Override
	public ArrayList<ProductReport> getProductReportList(int range) {
		ArrayList<ProductReport> productList = new ArrayList<ProductReport> ();
		int orderID;
		int custID;
		float total;
		String date;
		int employID;
		String query = "SELECT * FROM Orders WHERE OrderDate BETWEEN " + getBackDate(range) + " AND " + getBackDate(-1);
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			while ( rs.next() ) {
				orderID = rs.getInt("OrderID");
				custID = rs.getInt("CustomerID");
				total = rs.getFloat("TotalPrice");
				date = rs.getString("OrderDate");
				employID = rs.getInt("EmployeeID");
				productList.add(new ProductReport(orderID, custID, total, date, employID));
		    }
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return productList;
	}
		
	public String getBackDate(int range) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, range);
	
		return sdf.format(c.getTime());
	}

}
