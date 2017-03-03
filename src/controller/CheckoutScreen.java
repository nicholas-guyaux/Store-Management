package controller;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class CheckoutScreen  extends JPanel {
	
	 public JButton AddItemButton = new JButton("Add Item");
	 
	 public JButton RemoveItemButton = new JButton("Remove Item");
	 
	 public JButton PaymentButton = new JButton("Finish and Pay");
	
	 public JButton CancelButton = new JButton("Cancel");
	 
	 private float total = 0;
	 
	public CheckoutScreen() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		 
		  AddItemButton.setMaximumSize(new Dimension(800, 500));
		  AddItemButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(AddItemButton);

		  add(Box.createVerticalStrut(30));
		  
		  
		  RemoveItemButton.setMaximumSize(new Dimension(800, 500));
		  RemoveItemButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(RemoveItemButton);
		  
		  add(Box.createVerticalStrut(30));
		  
		  PaymentButton.setMaximumSize(new Dimension(800, 500));
		  PaymentButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(PaymentButton);
		  
		  add(Box.createVerticalStrut(30));
		  
		
		  CancelButton.setMaximumSize(new Dimension(800, 500));
		  CancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(CancelButton);
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
