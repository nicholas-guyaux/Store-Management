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

import model.Order;


public class ManagerScreen  extends Screen {

	private JButton mLogOutButton = new JButton("Logout");
	
	private JButton mCheckoutButton = new JButton("Checkout");
	private JButton mEditAccountButton = new JButton("Edit Account");
	
	private JButton mInventoryButton = new JButton("Inventory");
	private JButton mReturnsButton = new JButton("Returns");
	private JButton mEditAccountsButton = new JButton("Edit Accounts");
	
	private JPanel mainPanel = new JPanel();
	
	 
	public ManagerScreen(JFrame frame){
		super(frame);
	
		createView();
		
		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}
	
	/** creates view */
	private void createView(){
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addPanel(mainPanel);
		
		
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		mainPanel.add(top);
		
		String name = "";
		name = "Hi " + mController.getDataAccess().getCurrentUser().getName();

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
		
		
		JPanel mid = new JPanel();
		mid.setLayout(new BoxLayout(mid, BoxLayout.X_AXIS));
		mainPanel.add(mid);
				 
		mCheckoutButton.setMaximumSize(new Dimension(900, 600));
		mCheckoutButton.setFont(new Font("Arial", Font.BOLD, 90));
		mCheckoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openCheckout();
			}
		});
		mid.add(mCheckoutButton);
		  
		mid.add(Box.createHorizontalStrut(30));
		  
		mEditAccountButton.setMaximumSize(new Dimension(900, 600));
		mEditAccountButton.setFont(new Font("Arial", Font.BOLD, 90));
		mEditAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openEditAccount();
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
				openInventoryScreen();
			}
		});
		managerOptions.add(mInventoryButton);

		managerOptions.add(Box.createHorizontalStrut(30));
		  
		mReturnsButton.setMaximumSize(new Dimension(900, 600));
		mReturnsButton.setFont(new Font("Arial", Font.BOLD, 90));
		mReturnsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openReturnsScreen();
			}
		});
		managerOptions.add(mReturnsButton);
		
		managerOptions.add(Box.createHorizontalStrut(30));
		  
		mEditAccountsButton.setMaximumSize(new Dimension(900, 600));
		mEditAccountsButton.setFont(new Font("Arial", Font.BOLD, 90));
		mEditAccountsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openEditAccountsScreen();
			}
		});
		managerOptions.add(mEditAccountsButton);
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
	
	/** opens inventory screen */
	private void openInventoryScreen() {
		removePanel(mainPanel);
		new InventoryScreen(mMainFrame);
	}
	
	/** opens return screen */
	private void openReturnsScreen() {
		String input = (String) JOptionPane.showInputDialog(
				mMainFrame,
                "Order ID:",
                "Order ID dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
		if(input == null)
			return;
		if(input.compareTo("") == 0){
			JOptionPane.showMessageDialog(mMainFrame, "must enter a value");
			return;
		}
		if(input.matches("[0-9]+") == false){
			JOptionPane.showMessageDialog(mMainFrame, "order id must have the correct format (ddddd)");
			return;
		}
		int id = Integer.parseInt(input);
		Order o = mController.getDataAccess().getOrderById(id);
		if(o == null){
			JOptionPane.showMessageDialog(mMainFrame, "the order id "+ id + " does not exist");
			return;
		}		
		removePanel(mainPanel);
		new ReturnScreen(mMainFrame, o);
	}
	
	/** opens edit accounts screen */
	private void openEditAccountsScreen() {
		removePanel(mainPanel);
		new EditAccountsScreen(mMainFrame);
	}
}
