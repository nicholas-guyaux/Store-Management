package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Product;


public class AddNewAccount  extends Screen {

	public JLabel mAccountTypeLabel;
	public JLabel mNameLabel;
	public JLabel mUserNameLabel;
	public JLabel mPasswordLabel;
	
	public JCheckBox mAccountTypeEditButton = new JCheckBox("is Manager");
	public JTextField mNameEditButton = new JTextField(10);
	public JTextField mUserNameEditButton = new JTextField(10);
	public JTextField mPasswordEditButton = new JTextField(10);

	public JButton mSaveButton = new JButton("Save");
	public JButton mCancelButton = new JButton("Cancel");
	
	 
	public AddNewAccount(JFrame frame) {
		super(frame);
		
		createView(frame);
		
		frame.pack();
		frame.setVisible(true);
	}

	private void createView(JFrame frame) {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addPanel(mainPanel);
		
		
		JPanel AccountTypePanel = new JPanel();
		AccountTypePanel.setLayout(new BoxLayout(AccountTypePanel, BoxLayout.X_AXIS));
		mainPanel.add(AccountTypePanel);
		
		mAccountTypeLabel = new JLabel("Account Type: ");
		mAccountTypeLabel.setMaximumSize(new Dimension(900, 600));
		mAccountTypeLabel.setFont(new Font("Arial", Font.BOLD, 42));
		AccountTypePanel.add(mAccountTypeLabel);

		AccountTypePanel.add(Box.createHorizontalStrut(30));
		AccountTypePanel.add(Box.createHorizontalGlue());
		  
		mAccountTypeEditButton.setMaximumSize(new Dimension(900, 600));
		mAccountTypeEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		AccountTypePanel.add(mAccountTypeEditButton);

		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel NamePanel = new JPanel();
		NamePanel.setLayout(new BoxLayout(NamePanel, BoxLayout.X_AXIS));
		mainPanel.add(NamePanel);
		
		mNameLabel = new JLabel("Name: ");
		mNameLabel.setMaximumSize(new Dimension(900, 600));
		mNameLabel.setFont(new Font("Arial", Font.BOLD, 42));
		NamePanel.add(mNameLabel);

		NamePanel.add(Box.createHorizontalStrut(30));
		NamePanel.add(Box.createHorizontalGlue());
		  
		mNameEditButton.setMaximumSize(new Dimension(900, 600));
		mNameEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		NamePanel.add(mNameEditButton);
		

		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel UserNamePanel = new JPanel();
		UserNamePanel.setLayout(new BoxLayout(UserNamePanel, BoxLayout.X_AXIS));
		mainPanel.add(UserNamePanel);

		mUserNameLabel = new JLabel("Username: ");
		mUserNameLabel.setMaximumSize(new Dimension(900, 600));
		mUserNameLabel.setFont(new Font("Arial", Font.BOLD, 42));
		UserNamePanel.add(mUserNameLabel);

		UserNamePanel.add(Box.createHorizontalStrut(30));		  
		UserNamePanel.add(Box.createHorizontalGlue());
		  
		mUserNameEditButton.setMaximumSize(new Dimension(900, 600));
		mUserNameEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		UserNamePanel.add(mUserNameEditButton);
		
		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel PasswordPanel = new JPanel();
		PasswordPanel.setLayout(new BoxLayout(PasswordPanel, BoxLayout.X_AXIS));
		mainPanel.add(PasswordPanel);

		mPasswordLabel = new JLabel("Password: ");
		mPasswordLabel.setMaximumSize(new Dimension(900, 600));
		mPasswordLabel.setFont(new Font("Arial", Font.BOLD, 42));
		PasswordPanel.add(mPasswordLabel);

		PasswordPanel.add(Box.createHorizontalStrut(30));
		PasswordPanel.add(Box.createHorizontalGlue());
		  
		mPasswordEditButton.setMaximumSize(new Dimension(900, 600));
		mPasswordEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		PasswordPanel.add(mPasswordEditButton);
		
		
		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel BackButtonPanel = new JPanel();
		BackButtonPanel.setLayout(new BoxLayout(BackButtonPanel, BoxLayout.X_AXIS));
		mainPanel.add(BackButtonPanel);

		BackButtonPanel.add(Box.createHorizontalGlue());
		  
		mSaveButton.setMaximumSize(new Dimension(900, 600));
		mSaveButton.setFont(new Font("Arial", Font.BOLD, 90));
		mSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "not implemented yet");
			}
		});
		BackButtonPanel.add(mSaveButton);
		
		mCancelButton.setMaximumSize(new Dimension(900, 600));
		mCancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		mCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new EditAccountsScreen(frame);
			}
		});
		BackButtonPanel.add(mCancelButton);
	}
}
