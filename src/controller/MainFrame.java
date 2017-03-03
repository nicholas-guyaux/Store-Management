package controller;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import database_console.DBConnect;
import controller.MainScreen;
import controller.CheckoutScreen;
import controller.InventoryScreen;
import controller.PaymentScreen;

public class MainFrame implements ActionListener {
	
	private JFrame frame = new JFrame("Store Management");
	
	private MainScreen mainScreen = new MainScreen();
	
	private CheckoutScreen checkoutScreen = new CheckoutScreen();
	
	private PaymentScreen paymentScreen = new PaymentScreen();
	
	private InventoryScreen inventoryScreen = new InventoryScreen();
	
	static JPanel containerPanel = new JPanel(new GridBagLayout() );
	
	public ArrayList<Product> allProducts = null;
	
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
	    ArrayList<Product> productList = new ArrayList<>();
	    while(rst.next())
		{
			Product product = new Product();
			product.setProductID(rst.getInt("productID"));
			product.setDescription(rst.getString("description"));
			product.setPrice(rst.getFloat("price"));
			product.setQuantity(rst.getInt("quantity"));
			product.setExpDate(rst.getDate("expDate"));
			product.setSupplierID(rst.getInt("supplierID"));
		}
	    this.allProducts = productList;
	}
	
	public void initProductList() throws ClassNotFoundException, SQLException
	{
		getAllProduct();
		  String col [] = {"ProductID", "Description", "Price", "Quantity", "ExpDate", "SupplierID"};
		  
		  DefaultTableModel tableModel = new DefaultTableModel(col,0);
		  
		  
		  
		
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
		
		JTable table = new JTable(tableModel);
		inventoryScreen.setScrollPane(table);
			
		  
	}
	
	public static void main (String[] args){ 
		
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
        else if ("Add Item".equals(command)) {
            // do something
        	//checkoutScreen.addItem();
        }
        else if ("Remove Item".equals(command)) {
            // do something
        	//checkoutScreen.removeItem();
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
        	showCheckoutScreen();
        }
	}
}
