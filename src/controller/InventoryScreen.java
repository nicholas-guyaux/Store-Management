package controller;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;


public class InventoryScreen  extends JPanel {
	
	public JButton addProductButton = new JButton("Add Product");
	
	public JButton editProductButton = new JButton("Edit Product");
	
	public JButton updateButton = new JButton("Update Database");
	
	public JButton cancelButton = new JButton("Cancel");
	
	public JPanel leftSide = new JPanel();
	
	public JPanel rightSide = new JPanel();
	
	public JTable tab = new JTable();
	
	public JScrollPane spTable = new JScrollPane(tab);
	
	public void setScrollPane(JTable table)
	{
		this.spTable = new JScrollPane(table);
		initInventoryScreen();
	}
	
	
	public void initInventoryScreen()
	{
		this.removeAll();
		leftSide.removeAll();
		rightSide.removeAll();
		
		leftSide.add(spTable);
		
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
		
		  addProductButton.setMaximumSize(new Dimension(800, 500));
		  addProductButton.setFont(new Font("Arial", Font.BOLD, 90));
		  rightSide.add(addProductButton);

		  rightSide.add(Box.createVerticalStrut(30));
		  
		  editProductButton.setMaximumSize(new Dimension(800, 500));
		  editProductButton.setFont(new Font("Arial", Font.BOLD, 90));
		  rightSide.add(editProductButton);
		  
		  rightSide.add(Box.createVerticalStrut(30));
		  
		  updateButton.setMaximumSize(new Dimension(800, 500));
		  updateButton.setFont(new Font("Arial", Font.BOLD, 90));
		  rightSide.add(updateButton);
		  
		  rightSide.add(Box.createVerticalStrut(30));
		  
		  cancelButton.setMaximumSize(new Dimension(800, 500));
		  cancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		  rightSide.add(cancelButton);
		  
		  JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSide, rightSide);
		  
		  add(pane);
	}
	
	public InventoryScreen() {
		  initInventoryScreen();
	
	}

}
