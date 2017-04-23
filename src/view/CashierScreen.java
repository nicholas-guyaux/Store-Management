package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Program;


public class CashierScreen  extends Screen {

	private JButton mLogOutButton = new JButton("Logout");
	
	private JButton mCheckoutButton = new JButton("Checkout");
	private JButton mEditAccountButton = new JButton("Edit Account");
	
	private JPanel mainPanel = new JPanel();
	
	 
	public CashierScreen(JFrame frame){
		super(frame);
		
		createView();
		
		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}
	
	/** creates view */
	private void createView() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addPanel(mainPanel);
		
		
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		mainPanel.add(top);
		
		String name = "Hi " + mController.getDataAccess().getCurrentUser().getName();
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
				logout();
			}
		});
		top.add(mLogOutButton);
		
		
		mainPanel.add(Box.createVerticalStrut(200));
		
		
		JPanel mBottom = new JPanel();
		mBottom.setLayout(new BoxLayout(mBottom, BoxLayout.X_AXIS));
		mainPanel.add(mBottom);
		 
		mCheckoutButton.setMaximumSize(new Dimension(900, 600));
		mCheckoutButton.setFont(new Font("Arial", Font.BOLD, 90));
		mCheckoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openCheckout();
			}
		});
		mBottom.add(mCheckoutButton);
		
		  
		mBottom.add(Box.createHorizontalStrut(30));
		  
		mEditAccountButton.setMaximumSize(new Dimension(900, 600));
		mEditAccountButton.setFont(new Font("Arial", Font.BOLD, 90));
		mEditAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openEditAccount();
			}
		});
		mBottom.add(mEditAccountButton);
	}

	/** logs out */
	private void logout(){
		mController.getDataAccess().logOut();
		removePanel(mainPanel);
		new LoginScreen(mMainFrame);
	}
	
	/** opens checkout screen */
	private void openCheckout(){
		removePanel(mainPanel);
		new CheckoutScreen(mMainFrame,null);
	}
	
	/** opens edit account screen */
	private void openEditAccount(){
		removePanel(mainPanel);
		new EditAccountScreen(mMainFrame,mController.getDataAccess().getCurrentUser());
	}
}
