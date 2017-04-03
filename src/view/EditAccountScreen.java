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


public class EditAccountScreen  extends Screen {

	public JLabel mAccountTypeLabel = new JLabel("Account Type: Cashier");
	
	public JLabel mNameLabel = new JLabel("Name: Jordan Knudsen");
	
	public JLabel mUserNameLabel = new JLabel("Username: jordan");

	public JLabel mPasswordLabel = new JLabel("Password: password");
	
	public JButton mAccountTypeEditButton = new JButton("Edit");
	
	public JButton mNameEditButton = new JButton("Edit");

	public JButton mUserNameEditButton = new JButton("Edit");
	
	public JButton mPasswordEditButton = new JButton("Edit");
	
	public JButton mBackButton = new JButton("Back");
	
	 
	public EditAccountScreen(JFrame frame) {
		super(frame);
	
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addPanel(mainPanel);
		
		
		JPanel AccountTypePanel = new JPanel();
		AccountTypePanel.setLayout(new BoxLayout(AccountTypePanel, BoxLayout.X_AXIS));
		mainPanel.add(AccountTypePanel);
		
		mAccountTypeLabel.setMaximumSize(new Dimension(900, 600));
		mAccountTypeLabel.setFont(new Font("Arial", Font.BOLD, 42));
		AccountTypePanel.add(mAccountTypeLabel);

		AccountTypePanel.add(Box.createHorizontalStrut(30));
		AccountTypePanel.add(Box.createHorizontalGlue());
		  
		mAccountTypeEditButton.setMaximumSize(new Dimension(900, 600));
		mAccountTypeEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		//mAccountTypeEditButton.addActionListener(this);
		//AccountTypePanel.add(AccountTypeEditButton);
		

		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel NamePanel = new JPanel();
		NamePanel.setLayout(new BoxLayout(NamePanel, BoxLayout.X_AXIS));
		mainPanel.add(NamePanel);
		
		mNameLabel.setMaximumSize(new Dimension(900, 600));
		mNameLabel.setFont(new Font("Arial", Font.BOLD, 42));
		NamePanel.add(mNameLabel);

		NamePanel.add(Box.createHorizontalStrut(30));
		NamePanel.add(Box.createHorizontalGlue());
		  
		mNameEditButton.setMaximumSize(new Dimension(900, 600));
		mNameEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		mNameEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(
		                frame,
		                "Name:",
		                "Name Edit dialog",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "Jordan Knudsen");
			}
		});
		NamePanel.add(mNameEditButton);
		

		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel UserNamePanel = new JPanel();
		UserNamePanel.setLayout(new BoxLayout(UserNamePanel, BoxLayout.X_AXIS));
		mainPanel.add(UserNamePanel);
		
		mUserNameLabel.setMaximumSize(new Dimension(900, 600));
		mUserNameLabel.setFont(new Font("Arial", Font.BOLD, 42));
		UserNamePanel.add(mUserNameLabel);

		UserNamePanel.add(Box.createHorizontalStrut(30));		  
		UserNamePanel.add(Box.createHorizontalGlue());
		  
		mUserNameEditButton.setMaximumSize(new Dimension(900, 600));
		mUserNameEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		//mUserNameEditButton.addActionListener(this);
		//UserNamePanel.add(UserNameEditButton);
		

		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel PasswordPanel = new JPanel();
		PasswordPanel.setLayout(new BoxLayout(PasswordPanel, BoxLayout.X_AXIS));
		mainPanel.add(PasswordPanel);
		
		mPasswordLabel.setMaximumSize(new Dimension(900, 600));
		mPasswordLabel.setFont(new Font("Arial", Font.BOLD, 42));
		PasswordPanel.add(mPasswordLabel);

		PasswordPanel.add(Box.createHorizontalStrut(30));
		PasswordPanel.add(Box.createHorizontalGlue());
		  
		mPasswordEditButton.setMaximumSize(new Dimension(900, 600));
		mPasswordEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		mPasswordEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showInputDialog(
		                frame,
		                "Password:",
		                "Password Edit dialog",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "password");
			}
		});
		PasswordPanel.add(mPasswordEditButton);
		
		
		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel BackButtonPanel = new JPanel();
		BackButtonPanel.setLayout(new BoxLayout(BackButtonPanel, BoxLayout.X_AXIS));
		mainPanel.add(BackButtonPanel);

		BackButtonPanel.add(Box.createHorizontalGlue());
		  
		mBackButton.setMaximumSize(new Dimension(900, 600));
		mBackButton.setFont(new Font("Arial", Font.BOLD, 90));
		mBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				openUserMainMenu();
			}
		});
		BackButtonPanel.add(mBackButton);
		
		
		frame.pack();
		frame.setVisible(true);
	}
}
