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


public class CashierScreen  extends Screen {

	public JButton mLogOutButton = new JButton("Logout");
	
	public JButton mCheckoutButton = new JButton("Checkout");
	
	public JButton mEditAccountButton = new JButton("Edit Account");
	
	 
	public CashierScreen(JFrame frame){
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
		
		
		JPanel mBottom = new JPanel();
		mBottom.setLayout(new BoxLayout(mBottom, BoxLayout.X_AXIS));
		mainPanel.add(mBottom);
		 
		mCheckoutButton.setMaximumSize(new Dimension(900, 600));
		mCheckoutButton.setFont(new Font("Arial", Font.BOLD, 90));
		mCheckoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new CheckoutScreen(frame,null);
			}
		});
		mBottom.add(mCheckoutButton);

		  
		mBottom.add(Box.createHorizontalStrut(30));
		  
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
		mBottom.add(mEditAccountButton);
		

		frame.pack();
		frame.setVisible(true);
	}
}
