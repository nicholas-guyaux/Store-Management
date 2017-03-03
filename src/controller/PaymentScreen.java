package controller;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;




public class PaymentScreen  extends JPanel {
	
	public JButton cashButton = new JButton("Cash");
	
	public JButton creditButton = new JButton("Credit");
	
	public JButton cancelButton = new JButton("Cancel");
	
	private float total = 0;
	
	public PaymentScreen() {
		cashButton.setMaximumSize(new Dimension(800, 500));
		cashButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(cashButton);

		  add(Box.createVerticalStrut(30));
		  
		  
		  creditButton.setMaximumSize(new Dimension(800, 500));
		  creditButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(creditButton);
		  
		  add(Box.createVerticalStrut(30));
		  
		
		  cancelButton.setMaximumSize(new Dimension(800, 500));
		  cancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(cancelButton);
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
