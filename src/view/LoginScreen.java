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
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginScreen  extends Screen {

	public JTextField mUsername = new JTextField();
	
	public JTextField mPassword = new JTextField();
	
	public JButton mLogInButton = new JButton("Login");
	
	public JButton mCancelButton = new JButton("Cancel");
	 
	public LoginScreen(JFrame frame) {
		super(frame);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addPanel(mainPanel);
		
		mUsername.setMaximumSize(new Dimension(800, 500));
		mUsername.setFont(new Font("Arial", Font.BOLD, 90));
		JLabel usernameText = new JLabel("Username:");
		usernameText.setFont(new Font("Arial", Font.BOLD, 90));
		usernameText.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
		mainPanel.add(usernameText);
		mainPanel.add(mUsername);

		mainPanel.add(Box.createVerticalStrut(30));
		
		mPassword.setMaximumSize(new Dimension(800, 500));
		mPassword.setFont(new Font("Arial", Font.BOLD, 90));
		JLabel passwordText = new JLabel("Password:");
		passwordText.setFont(new Font("Arial", Font.BOLD, 90));
		passwordText.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
		mainPanel.add(passwordText);
		mainPanel.add(mPassword);

		mainPanel.add(Box.createVerticalStrut(30));
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		mainPanel.add(bottom);
		
		mLogInButton.setMaximumSize(new Dimension(800, 500));
		mLogInButton.setFont(new Font("Arial", Font.BOLD, 90));
		mLogInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: make authentication
				removePanel(mainPanel);
				new CashierScreen(frame);
			}
		});
		bottom.add(mLogInButton);
		  
		bottom.add(Box.createHorizontalStrut(30));
		  
		mCancelButton.setMaximumSize(new Dimension(800, 500));
		mCancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		mCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bottom.add(mCancelButton);	

		frame.pack();
		frame.setVisible(true);
	}
}



