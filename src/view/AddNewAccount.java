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


public class AddNewAccount  extends Screen {

	private JLabel mAccountTypeLabel;
	private JLabel mNameLabel;
	private JLabel mUserNameLabel;
	private JLabel mPasswordLabel;
	
	private JCheckBox mAccountTypeEditCheckBox = new JCheckBox("is Manager");
	private JTextField mNameEditTextField = new JTextField(10);
	private JTextField mUserNameEditTextField = new JTextField(10);
	private JTextField mPasswordEditTextField = new JPasswordField(10);

	private JButton mSaveButton = new JButton("Save");
	private JButton mCancelButton = new JButton("Cancel");
	
	private JPanel mainPanel = new JPanel();
	 
	public AddNewAccount(JFrame frame) {
		super(frame); 
		
		createView();
		
		mMainFrame.pack();
		mMainFrame.setVisible(true);
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
		  
		mAccountTypeEditCheckBox.setMaximumSize(new Dimension(900, 600));
		mAccountTypeEditCheckBox.setFont(new Font("Arial", Font.BOLD, 42));
		AccountTypePanel.add(mAccountTypeEditCheckBox);

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
		  
		mNameEditTextField.setMaximumSize(new Dimension(900, 600));
		mNameEditTextField.setFont(new Font("Arial", Font.BOLD, 42));
		NamePanel.add(mNameEditTextField);
		

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
		  
		mUserNameEditTextField.setMaximumSize(new Dimension(900, 600));
		mUserNameEditTextField.setFont(new Font("Arial", Font.BOLD, 42));
		UserNamePanel.add(mUserNameEditTextField);
		
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
		  
		mPasswordEditTextField.setMaximumSize(new Dimension(900, 600));
		mPasswordEditTextField.setFont(new Font("Arial", Font.BOLD, 42));
		PasswordPanel.add(mPasswordEditTextField);
		
		
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
				saveEmployee();
			}
		});
		BackButtonPanel.add(mSaveButton);
		
		mCancelButton.setMaximumSize(new Dimension(900, 600));
		mCancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		mCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		BackButtonPanel.add(mCancelButton);

		mNameEditTextField.requestFocusInWindow(); 
		mainPanel.getRootPane().setDefaultButton(mSaveButton);
	}
	
	/** checks for empty fields, if all fields are filled out, a new user will be created and saved 
	 *  The program will then return the the edit accounts screen */
	private void saveEmployee() {
		if(mNameEditTextField.getText().compareTo("") == 0 || mUserNameEditTextField.getText().compareTo("") == 0 || mPasswordEditTextField.getText().compareTo("") == 0 ){
			JOptionPane.showMessageDialog(mMainFrame, "must fill out all fields");
		}
		else{
			int i = 1;
			for(i = 1;i<Integer.MAX_VALUE;i++){
				if(Program.getInstance().getDataAccess().getEmployeeById(i) == null)
					break;
			}
			Program.getInstance().getDataAccess().addEmployee(new Employee(i,mNameEditTextField.getText(), mUserNameEditTextField.getText(), mPasswordEditTextField.getText(), mAccountTypeEditCheckBox.isSelected()));
			removePanel(mainPanel);
			new EditAccountsScreen(mMainFrame);	
		}
	}
	
	/** cancels the creation of a new user */
	private void cancel() {
		removePanel(mainPanel);
		new EditAccountsScreen(mMainFrame);
	}
}
