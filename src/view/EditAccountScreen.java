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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Program;
import model.Employee;


public class EditAccountScreen  extends Screen {

	private JLabel mAccountTypeLabel;
	private JLabel mNameLabel;
	private JLabel mUserNameLabel;
	private JLabel mPasswordLabel;
	
	private JButton mAccountTypeEditButton = new JButton("Edit");
	private JButton mNameEditButton = new JButton("Edit");
	private JButton mUserNameEditButton = new JButton("Edit");
	private JButton mPasswordEditButton = new JButton("Edit");
	
	private JButton mBackButton = new JButton("Back");
	
	private JPanel mainPanel = new JPanel();
	
	private Employee account;
	 
	public EditAccountScreen(JFrame frame, Employee account) {
		super(frame);
		
		this.account = account;
		
		createView();
		
		updateAccount();
		
		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}

	/** updates view for any changes */
	private void updateAccount() {
		Program.getInstance().getDataAccess().modifyEmployeeById(account.getId(), account);
		mAccountTypeLabel.setText("Account Type: " + ((account.isManager())?"Manager":"Cashier"));
		mNameLabel.setText("Name: " + account.getName());
		mUserNameLabel.setText("Username: " + account.getUsername());
		mPasswordLabel.setText("Password: ******");
	}

	/** creates view */
	private void createView() {
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
		//mAccountTypeEditButton.addActionListener(this);
		if(Program.getInstance().getDataAccess().getCurrentUser().getId() != account.getId()){
			AccountTypePanel.add(mAccountTypeEditButton);
			mAccountTypeEditButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					EditAccountType();
				}
			});
		}

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
		mNameEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditName();
			}
		});
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
		if(Program.getInstance().getDataAccess().getCurrentUser().isManager()){
			UserNamePanel.add(mUserNameEditButton);
			mUserNameEditButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					EditUserName();
				}
			});
		}

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
		mPasswordEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditPassword();
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
				returnToPreviousScreen();
			}
		});
		BackButtonPanel.add(mBackButton);
	}
	private void EditAccountType() {
		JCheckBox isManagerCheckBox = new JCheckBox();
		isManagerCheckBox.setSelected(account.isManager());
        Object[] ob = {new JLabel("is Manager:"),isManagerCheckBox};
		int input = JOptionPane.showConfirmDialog(
				mMainFrame,
                ob,
                "Account Type Edit dialog",
                JOptionPane.OK_CANCEL_OPTION);
		if(input == JOptionPane.OK_OPTION){
			account.setIsManager(isManagerCheckBox.isSelected());
			updateAccount();
		}
	}
	private void EditName() {
		String input = (String) JOptionPane.showInputDialog(
				mMainFrame,
                "Name:",
                "Name Edit dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                account.getName());
		if(input == null)
			return;
		if(input.compareTo("") == 0){
			JOptionPane.showMessageDialog(mMainFrame, "must enter a value");
			return;
		}
		account.setName(input);
		updateAccount();
	}

	private void EditUserName() {
		String input = (String) JOptionPane.showInputDialog(
				mMainFrame,
                "Username:",
                "Username Edit dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                account.getUsername());
		if(input == null)
			return;
		if(input.compareTo("") == 0){
			JOptionPane.showMessageDialog(mMainFrame, "must enter a value");
			return;
		}
		account.setUsername(input);
		updateAccount();
	}

	private void EditPassword() {
		JTextField password = new JPasswordField();
        Object[] ob = {new JLabel("Password:"),password};
		int input = JOptionPane.showConfirmDialog(
				mMainFrame,
                ob,
                "Password Edit dialog",
                JOptionPane.OK_CANCEL_OPTION);
		if(input == JOptionPane.OK_OPTION){
			if(password.getText().compareTo("") == 0){
				JOptionPane.showMessageDialog(mMainFrame, "must enter a value");
				return;
			}
			account.setPassword(password.getText());
			updateAccount();
		}
	}
	private void returnToPreviousScreen() {
		removePanel(mainPanel);
		if(Program.getInstance().getDataAccess().getCurrentUser().getId() != account.getId())
			new EditAccountsScreen(mMainFrame);
		else
			openUserMainMenu();
	}
}
