package controller;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
	
	private MainFrame()
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
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		showMainScreen();
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
	
	public static void main (String[] args){ 
		
		new MainFrame();
			
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
        	checkoutScreen.addItem();
        }
        else if ("Remove Item".equals(command)) {
            // do something
        	checkoutScreen.removeItem();
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
        else if ("Update Inventory".equals(command)) {
            // do something
        	showCheckoutScreen();
        }
	}
}
