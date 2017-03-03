package controller;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;


public class InventoryScreen  extends JPanel {
	
	public InventoryScreen() {
		  JButton AddProductButton = new JButton("Checkout");
		  AddProductButton.setMaximumSize(new Dimension(800, 500));
		  AddProductButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(AddProductButton);

		  add(Box.createVerticalStrut(30));
		  
		  JButton EditProduct = new JButton("Update Inventory");
		  EditProduct.setMaximumSize(new Dimension(800, 500));
		  EditProduct.setFont(new Font("Arial", Font.BOLD, 90));
		  add(EditProduct);
		  
		  add(Box.createVerticalStrut(30));
		  
		  JButton CancelButton = new JButton("Cancel");
		  CancelButton.setMaximumSize(new Dimension(800, 500));
		  CancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(CancelButton);
	}

}
