package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ManagerScreen  extends Screen {

	public JButton mLogOutButton = new JButton("Logout");
	
	public JButton mCheckoutButton = new JButton("Checkout");

	public JButton mEditAccountButton = new JButton("Edit Account");
	
	public JButton mInventoryButton = new JButton("Inventory");
	
	public JButton mReturnsButton = new JButton("Returns");
	
	public JButton mEditAccountsButton = new JButton("Edit Accounts");
	
	 
	public ManagerScreen(JFrame frame){
		super(frame);
	
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addPanel(mainPanel);
		
		
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		mainPanel.add(top);
		
		String name = "";
		try {
			name = "Hi " + Program.getInstance().getDataAccess().getCurrentUser().getName();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		JLabel welcome = new JLabel(name);
		welcome.setFont(new Font("Arial", Font.BOLD, 90));
		welcome.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
		top.add(welcome);
		
		top.add(Box.createHorizontalGlue());

		mLogOutButton.setMaximumSize(new Dimension(300, 200));
		mLogOutButton.setFont(new Font("Arial", Font.BOLD, 42));
		mLogOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Program.getInstance().getDataAccess().logOut();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				removePanel(mainPanel);
				new LoginScreen(frame);
			}
		});
		top.add(mLogOutButton);
		

		mainPanel.add(Box.createVerticalStrut(200));
		
		
		JPanel mid = new JPanel();
		mid.setLayout(new BoxLayout(mid, BoxLayout.X_AXIS));
		mainPanel.add(mid);
				 
		mCheckoutButton.setMaximumSize(new Dimension(900, 600));
		mCheckoutButton.setFont(new Font("Arial", Font.BOLD, 90));
		mCheckoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new CheckoutScreen(frame,null);
			}
		});
		mid.add(mCheckoutButton);
		  
		mid.add(Box.createHorizontalStrut(30));
		  
		mEditAccountButton.setMaximumSize(new Dimension(900, 600));
		mEditAccountButton.setFont(new Font("Arial", Font.BOLD, 90));
		mEditAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				try {
					new EditAccountScreen(frame,Program.getInstance().getDataAccess().getCurrentUser());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mid.add(mEditAccountButton);
		

		mainPanel.add(Box.createVerticalStrut(30));
		

		JPanel managerOptions = new JPanel();
		managerOptions.setLayout(new BoxLayout(managerOptions, BoxLayout.X_AXIS));
		mainPanel.add(managerOptions);
		
		mInventoryButton.setMaximumSize(new Dimension(900, 600));
		mInventoryButton.setFont(new Font("Arial", Font.BOLD, 90));
		mInventoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new InventoryScreen(frame,true);
			}
		});
		managerOptions.add(mInventoryButton);

		managerOptions.add(Box.createHorizontalStrut(30));
		  
		mReturnsButton.setMaximumSize(new Dimension(900, 600));
		mReturnsButton.setFont(new Font("Arial", Font.BOLD, 90));
		mReturnsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "not implemented yet");
			}
		});
		managerOptions.add(mReturnsButton);
		
		managerOptions.add(Box.createHorizontalStrut(30));
		  
		mEditAccountsButton.setMaximumSize(new Dimension(900, 600));
		mEditAccountsButton.setFont(new Font("Arial", Font.BOLD, 90));
		mEditAccountsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new EditAccountsScreen(frame);
			}
		});
		managerOptions.add(mEditAccountsButton);
		
		
		frame.pack();
		frame.setVisible(true);
	}
}
