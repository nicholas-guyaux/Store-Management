package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ManagerScreen  extends JPanel {
	
	public JPanel bottom = new JPanel();

	public JButton LogOutButton = new JButton("Logout");
	
	public JButton CheckoutButton = new JButton("Checkout");

	public JButton EditAccountButton = new JButton("Edit Account");
	
	public JButton InventoryButton = new JButton("Inventory");
	
	public JButton ReturnsButton = new JButton("Returns");
	
	public JButton EditAccountsButton = new JButton("Edit Accounts");
	
	 
	public ManagerScreen() {
		createLogin();
	}

	public static void main(String[] args){
		JFrame frame = new JFrame("Store Management");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel containerPanel = new JPanel(new GridBagLayout() );
		
		ManagerScreen login = new ManagerScreen();
		
		containerPanel.removeAll();
		containerPanel.add(login, new GridBagConstraints());
		frame.add(containerPanel);
		frame.revalidate();
		frame.repaint();
		
		frame.setVisible(true);
	}
	
	
	public void createLogin()
	{
		bottom.removeAll();
		this.removeAll();
		
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
				 
		CheckoutButton.setMaximumSize(new Dimension(900, 600));
		CheckoutButton.setFont(new Font("Arial", Font.BOLD, 90));
		bottom.add(CheckoutButton);

		  
		bottom.add(Box.createHorizontalStrut(30));
		  
		EditAccountButton.setMaximumSize(new Dimension(900, 600));
		EditAccountButton.setFont(new Font("Arial", Font.BOLD, 90));
		bottom.add(EditAccountButton);
		

		JPanel managerOptions = new JPanel();
		managerOptions.setLayout(new BoxLayout(managerOptions, BoxLayout.X_AXIS));
		
		InventoryButton.setMaximumSize(new Dimension(900, 600));
		InventoryButton.setFont(new Font("Arial", Font.BOLD, 90));
		managerOptions.add(InventoryButton);


		managerOptions.add(Box.createHorizontalStrut(30));
		  
		ReturnsButton.setMaximumSize(new Dimension(900, 600));
		ReturnsButton.setFont(new Font("Arial", Font.BOLD, 90));
		managerOptions.add(ReturnsButton);
		
		managerOptions.add(Box.createHorizontalStrut(30));
		  
		EditAccountsButton.setMaximumSize(new Dimension(900, 600));
		EditAccountsButton.setFont(new Font("Arial", Font.BOLD, 90));
		managerOptions.add(EditAccountsButton);
		
		
		
		
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		
		JLabel welcome = new JLabel("Hi Name!");
		welcome.setFont(new Font("Arial", Font.BOLD, 90));
		welcome.setAlignmentX(RIGHT_ALIGNMENT);
		top.add(welcome);
		
		top.add(Box.createHorizontalStrut(300));

		LogOutButton.setMaximumSize(new Dimension(300, 200));
		LogOutButton.setFont(new Font("Arial", Font.BOLD, 42));
		top.add(LogOutButton);

		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		pane.add(top);
		pane.add(Box.createVerticalStrut(200));
		pane.add(bottom);
		pane.add(Box.createVerticalStrut(30));
		pane.add(managerOptions);
		  
		add(pane);
	}
}
