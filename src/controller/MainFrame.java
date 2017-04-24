package controller;

public class MainFrame /*implements ActionListener*/ {
	/*
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/store_management";
	
	// Database credentials
	static final String USER = "username";
	static final String PASS = "password";
	
	private JFrame frame = new JFrame("Store Management");
	
	private MainScreen mainScreen = new MainScreen();
	
	private CheckoutScreenold checkoutScreen = new CheckoutScreenold();
	
	private PaymentScreenold paymentScreen = new PaymentScreenold();
	
	private InventoryScreenold inventoryScreen = new InventoryScreenold();
	
	static JPanel containerPanel = new JPanel(new GridBagLayout() );
	
	public DefaultTableModel tableModel;
	
	public ArrayList<Productold> allProducts = null;
	
	private MainFrame() throws ClassNotFoundException, SQLException
	{
		mainScreen.CheckoutButton.addActionListener(this);
		mainScreen.InventoryButton.addActionListener(this);
		checkoutScreen.AddItemButton.addActionListener(this);
		checkoutScreen.RemoveItemButton.addActionListener(this);
		checkoutScreen.CancelButton.addActionListener(this);
		checkoutScreen.PaymentButton.addActionListener(this);
		paymentScreen.cashButton.addActionListener(this);
		paymentScreen.creditButton.addActionListener(this);
		paymentScreen.cancelButton.addActionListener(this);
		inventoryScreen.addProductButton.addActionListener(this);
		inventoryScreen.updateButton.addActionListener(this);
		inventoryScreen.cancelButton.addActionListener(this);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		showMainScreen();
		initProductList();
		frame.setVisible(true);
	};
	
	
	
	public void showMainScreen()
	{
		containerPanel.removeAll();
		containerPanel.add(mainScreen, new GridBagConstraints());
		frame.add(containerPanel);
		frame.revalidate();
		frame.repaint();
	}
	
	public void showCheckoutScreen()
	{
		
		containerPanel.removeAll();
		containerPanel.add(checkoutScreen, new GridBagConstraints());
		frame.add(containerPanel);
		frame.revalidate();
		frame.repaint();
	}
	
	public void showInventoryScreen()
	{
		frame.getContentPane().remove(containerPanel);
		containerPanel.removeAll();
		containerPanel.add(inventoryScreen, new GridBagConstraints());
		frame.add(containerPanel);
		frame.revalidate();
		frame.repaint();
	}
	
	public void showPaymentScreen()
	{
		frame.getContentPane().remove(containerPanel);
		containerPanel.removeAll();
		containerPanel.add(paymentScreen, new GridBagConstraints());
		frame.add(containerPanel);
		frame.revalidate();
		frame.repaint();
	}
	
	public void getAllProduct() throws SQLException, ClassNotFoundException {
	    Connection conn = DBConnect.getDBConnection();
	    PreparedStatement stm = conn.prepareStatement("select * from products");
	    ResultSet rst = stm.executeQuery();
	    ArrayList<Productold> productList = new ArrayList<>();
	    while(rst.next())
		{
			Productold product = new Productold();
			product.setProductID(rst.getInt("ProductID"));
			product.setDescription(rst.getString("Description"));
			product.setPrice(rst.getFloat("Price"));
			product.setQuantity(rst.getInt("Quantity"));
			product.setExpDate(rst.getDate("ExpDate"));
			product.setSupplierID(rst.getInt("SupplierID"));
			productList.add(product);
		}
	    this.allProducts = productList;
	    conn.close();
	}
	
	public void initProductList() throws ClassNotFoundException, SQLException
	{
		getAllProduct();
		  String col [] = {"ProductID", "Description", "Price", "Quantity", "ExpDate", "SupplierID"};
		  
		  tableModel = new DefaultTableModel(col,0);
		  
		  
		  
		
		for(int i =0; i < allProducts.size(); i++)
		  {
			  int prodID = allProducts.get(i).getProductID();
			  String desc = allProducts.get(i).getDescription();
			  float price = allProducts.get(i).getPrice();
			  int quant = allProducts.get(i).getQuantity();
			  Date exp = allProducts.get(i).getExpDate();
			  int supID = allProducts.get(i).getSupplierID();
			  
			  Object[] data = {prodID, desc, price, quant, exp, supID};
			  tableModel.addRow(data);
		  }
		
		inventoryScreen.setTable(tableModel);
			
		  
	}
	
	public void addProduct()
	{
		Object[] data = {};
		  tableModel.addRow(data);
	}
	
	public static void main (String[] args){ 
		Connection conn = null;
		Statement stmt = null;
		try {
			new MainFrame();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		System.out.println(command);
        if ("Checkout".equals(command)) {
            // do something
        	showCheckoutScreen();
        }
        else if ("Update Inventory".equals(command)) {
            // do something
        	showInventoryScreen();
        }
        else if ("Add Product".equals(command)) {
        	//inventory screen add product button
        	inventoryScreen.addProductRow();
        	showInventoryScreen();
        }
        else if("Update Database".equals(command)) {
        
        		
        	
            PreparedStatement ps;
			try {
				Connection conn = DBConnect.getDBConnection();
				ps = conn.prepareStatement("select * from products where ProductID=?");
			
	            for(int row = 1; row < inventoryScreen.tab.getModel().getRowCount(); row++)
	            {
	            	/*
	            	DefaultTableModel model = (DefaultTableModel)inventoryScreen.tab.getModel();
	                int selected = (int) model.getValueAt(row, 0);
	                if(selected != 0)
	                {
	                
						ps.setInt(1, selected);
					
						ResultSet rs;
					
						rs = ps.executeQuery();
					
						if(rs.isBeforeFirst()) // means record is already available (i.e. Update record)
						{
						      //update your record here 
							String sql = "UPDATE products SET Description=?, Price=?, Quantity=?, ExpDate=?, SupplierID=? WHERE ProductID=?";
							PreparedStatement statement = conn.prepareStatement(sql);
							statement.setString(1, model.getValueAt(row, 1));
							statement.setFloat(2, model.getValueAt(row, 2));
							statement.setInt(3), x);
							
									//+ " " +model.getValueAt(row, 1) + ", Price = "+model.getValueAt(row, 2) +", Quantity = "+model.getValueAt(row, 3)+", ExpDate = "+model.getValueAt(row, 4)+", SupplierID = "+model.getValueAt(row, 5)+" WHERE ProductID="+model.getValueAt(row, 0);
							
							preparedStmt.execute();
							conn.close();
							
						
						}
						else            // means no record available (i.e. insert a record)
						{
	 
						    String sql = "INSERT INTO products (ProductID, Description, Price, Quantity, ExpDate, SupplierID)" +
									" VALUES (" + model.getValueAt(row, 0) + ", " + model.getValueAt(row, 1) + ", " + model.getValueAt(row, 2) + ", " + model.getValueAt(row, 3) + ", " + model.getValueAt(row, 4) + ", " + model.getValueAt(row, 5) + ")";
							
							PreparedStatement preparedStmt = conn.prepareStatement(sql);
							preparedStmt.execute();
							conn.close();
						
						}
						
	            	}
	              	* /				
	            conn.close();
	            }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			try {
				initProductList();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			showInventoryScreen();
        }
        
        
        else if ("Add Item".equals(command)) {
            // do something
        	//checkoutScreen.addItem();
        	String prodID = JOptionPane.showInputDialog(frame, "Enter Product ID");
        	if(checkoutScreen.getNewItem(Integer.parseInt(prodID)))
    		{
    		
    		}
        	else
        	{
        		JOptionPane.showMessageDialog(frame, "No product with " + prodID + "  as the ID was found in store.", "ProductID Error", JOptionPane.ERROR_MESSAGE);
        	}
        }
        else if ("Remove Item".equals(command)) {
            // do something
        	//checkoutScreen.removeItem();
        	String prodID = JOptionPane.showInputDialog(frame, "Enter Product ID to remove");
        	if(checkoutScreen.getNewItem(Integer.parseInt(prodID)))
    		{
    		
    		}
        	else
        	{
        		JOptionPane.showMessageDialog(frame, "No product with " + prodID + "  as the ID was found in order.", "ProductID Error", JOptionPane.ERROR_MESSAGE);
        	}
        }
        else if ("Finish and Pay".equals(command)) {
            // do something
        	paymentScreen.setTotal(checkoutScreen.getTotal());
        	showPaymentScreen();
        }
        else if ("Cancel".equals(command)) {
            // do something
        	showMainScreen();
        }
       
        else if ("Cancel Payment".equals(command)) {
            // do something
        	//payment screen cancel payment
        	showCheckoutScreen();
        }
        
        else if ("Cash".equals(command)) {
        	//payment screen cash button
        	String cash = JOptionPane.showInputDialog(frame, "Enter cash amount");
        	if(PaymentScreenold.enoughCash(Float.parseFloat(cash)))
        	{
        		float change = PaymentScreenold.payWithCash(Float.parseFloat(cash));
        		JOptionPane.showMessageDialog(frame, "You get $" + String.format("%.2f", change) + " back.");
        		showMainScreen();
        	}
        	else
        	{
        		paymentScreen.setTotal(paymentScreen.getTotal() - Float.parseFloat(cash));
        		paymentScreen.initScreen();
        	}
        	
        }
        
        else if ("Credit".equals(command)) {
        	//payment screen credit
        	int input = JOptionPane.showConfirmDialog(frame, "Did the card process?");
        	if(input == 0)
        	{
        		JOptionPane.showMessageDialog(frame, "Thank you, payment processed sucessfully.");
        		showMainScreen();
        	}
        	else if(input == 1)
        	{
        		JOptionPane.showMessageDialog(frame, "Card payment unsuccessful", "Credit Error", JOptionPane.ERROR_MESSAGE);	
        	}
       
        	
        }
       
	}*/
}
