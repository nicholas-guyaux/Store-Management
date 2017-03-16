package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class PaymentScreen  extends JPanel {
	public JLabel totalLabel;
	
	public JButton cashButton = new JButton("Cash");
	
	public JButton creditButton = new JButton("Credit");
	
	public JButton cancelButton = new JButton("Cancel Payment");
	
	private static float total = 0;
	
	public PaymentScreen() {
		
		initScreen();
	}
	
	public void initTotalLabel(float total)
	{
		totalLabel = new JLabel("Total: $" + String.format("%.2f", getTotal()));
	}
	
	public void initScreen()
	{
		initTotalLabel(getTotal());
		this.removeAll();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		totalLabel.setFont(new Font("Arial", Font.BOLD, 90));
		add(totalLabel);
		
		add(Box.createVerticalStrut(50));
		
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

	public static float getTotal() {
		return total;
	}
	
	public static boolean enoughCash(float cash)
	{
		if(getTotal()>cash)return false;
		return true;
	}
	
	public static float payWithCash(float cash)
	{
		float change = cash - getTotal();
		return change;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
