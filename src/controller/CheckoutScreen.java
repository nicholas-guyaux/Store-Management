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
import javax.swing.table.DefaultTableModel;


public class CheckoutScreen  extends JPanel {
	
	public JPanel leftSide = new JPanel();
	
	public JPanel rightSide = new JPanel();
	
	public DefaultTableModel tableModel;
	
	public JTable tab = new JTable(tableModel);	
	
	public JScrollPane spTable = new JScrollPane(tab);
	
	public JButton AddItemButton = new JButton("Add Item");
	 
	public JButton RemoveItemButton = new JButton("Remove Item");
	 
	public JButton PaymentButton = new JButton("Finish and Pay");
	
	public JButton CancelButton = new JButton("Cancel");
	 
	private float total = 0;
	 
	public CheckoutScreen() {
		
		leftSide.add(spTable);
		
		rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));
				 
		AddItemButton.setMaximumSize(new Dimension(800, 500));
		AddItemButton.setFont(new Font("Arial", Font.BOLD, 90));
		rightSide.add(AddItemButton);

		rightSide.add(Box.createVerticalStrut(30));
		    
		RemoveItemButton.setMaximumSize(new Dimension(800, 500));
		RemoveItemButton.setFont(new Font("Arial", Font.BOLD, 90));
		rightSide.add(RemoveItemButton);
		  
		rightSide.add(Box.createVerticalStrut(30));
		  
		PaymentButton.setMaximumSize(new Dimension(800, 500));
		PaymentButton.setFont(new Font("Arial", Font.BOLD, 90));
		rightSide.add(PaymentButton);
		  
		rightSide.add(Box.createVerticalStrut(30));
		  
		CancelButton.setMaximumSize(new Dimension(800, 500));
		CancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		rightSide.add(CancelButton);
		  
		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSide, rightSide);
		  
		add(pane);
	}
	
	public boolean getNewItem(int productID)
	{
		if(productID != 0) return true;
		else
			return false;
	}
	
	public void addItem(int quant, float price)
	{
		if ((quant >0)&&(price > 0))
		{
			setTotal(getTotal()+(quant*price));
		}
	}
	
	public void removeItem(int quant, float price)
	{
		if ((getTotal()-(quant*price))>0)
		{
			setTotal(getTotal()-(quant*price));
		}
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
	
	


}
