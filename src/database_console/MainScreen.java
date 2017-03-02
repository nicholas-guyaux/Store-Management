package database_console;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainScreen extends JFrame implements ActionListener {

	Product products[];
	
	JButton inventory, checkout;
	public static void main(String[] args) {
		new MainScreen();
	}
	
	public MainScreen(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = this.getContentPane();
	
		pane.setPreferredSize(new Dimension(500, 200));
		pane.setLayout(new BorderLayout());
	
		
		JPanel screen = new JPanel();
		
		screen.setLayout(new GridLayout());

		inventory = new JButton("Inventory");
		inventory.addActionListener(this); 
		screen.add(inventory);
		checkout = new JButton("Checkout");
		checkout.addActionListener(this); 
		screen.add(checkout);
		
		pane.add(screen);	
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	void startCheckout(){
		new Checkout();
	}
	void openInventoryManager(){
		new InventoryManagement();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == inventory)
			openInventoryManager();
		else if(e.getSource() == checkout)
			startCheckout();
		
	}

}
