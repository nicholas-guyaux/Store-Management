package database_console;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Checkout extends JFrame {
	Order order;
	
	public Checkout(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = this.getContentPane();
	
		pane.setPreferredSize(new Dimension(500, 200));
		pane.setLayout(new BorderLayout());
	
		
		JPanel screen = new JPanel();
		
		screen.setLayout(new GridLayout());
		
		screen.add(new JLabel("inventory"));
		pane.add(screen);	
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	void CreateOrder(){
		
	}
	void ReturnToMain(){
		
	}
	void UpdateCheckout(int OrderID){
		
	}
}
