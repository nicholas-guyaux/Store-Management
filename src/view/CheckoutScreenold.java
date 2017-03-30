package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class CheckoutScreenold  extends JPanel {
	
	public JPanel leftSide = new JPanel();
	
	public JLabel taxAmount;
	
	public JLabel subtotalAmount;
	
	public JLabel totalAmount;
	
	public JPanel rightSide = new JPanel();
	
	public DefaultTableModel tableModel;
	
	public JTable tab = new JTable(tableModel);	
	
	public JScrollPane spTable = new JScrollPane(tab);
	
	public JButton AddItemButton = new JButton("Add Item");
	 
	public JButton RemoveItemButton = new JButton("Remove Item");
	 
	public JButton PaymentButton = new JButton("Finish and Pay");
	
	public JButton CancelButton = new JButton("Cancel");
	 
	private float total = 0;
	
	private float tax = 0;
	
	public void updateSubTotalLabel()
	{
		subtotalAmount = new JLabel("Subtotal: $" + String.format("%.2f", getTotal()));
	}
	
	public void updateTotalLabel()
	{
		totalAmount = new JLabel("Total: $" + String.format("%.2f", (getTotal()+getTax())));
	}
	
	public void updateTaxLabel()
	{
		subtotalAmount = new JLabel("Tax: $" + String.format("%.2f", getTax()));
	}
	 
	public CheckoutScreenold() {
		
		updateCheckout();
		
	
	}
	
	
	public void updateCheckout()
	{
		leftSide.removeAll();
		rightSide.removeAll();
		this.removeAll();
		
		updateSubTotalLabel();
		updateTaxLabel();
		updateTotalLabel();
		
		leftSide.setLayout(new BoxLayout(leftSide, BoxLayout.Y_AXIS));
		
		leftSide.add(spTable);
		
		leftSide.add(Box.createVerticalStrut(30));
		
		subtotalAmount.setFont(new Font("Arial", Font.BOLD, 30));
		leftSide.add(subtotalAmount);
		
		leftSide.add(Box.createVerticalStrut(20));
		
		taxAmount.setFont(new Font("Arial", Font.BOLD, 30));
		leftSide.add(taxAmount);
		
		leftSide.add(Box.createVerticalStrut(20));
		
		totalAmount.setFont(new Font("Arial", Font.BOLD, 50));
		leftSide.add(totalAmount);
		
		
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

	public float getTax() {
		return tax;
	}

	public void updateTax() {
		this.tax = (float) (getTotal() * 0.06);
	}
	
	


}
