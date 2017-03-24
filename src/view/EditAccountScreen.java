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


public class EditAccountScreen  extends JPanel {

	public JLabel AccountTypeLabel = new JLabel("Account Type: Cashier");
	
	public JLabel NameLabel = new JLabel("Name: Jordan Knudsen");
	
	public JLabel UserNameLabel = new JLabel("Username: jordan");

	public JLabel PasswordLabel = new JLabel("Password: password");
	
	public JButton AccountTypeEditButton = new JButton("Edit");
	
	public JButton NameEditButton = new JButton("Edit");

	public JButton UserNameEditButton = new JButton("Edit");
	
	public JButton PasswordEditButton = new JButton("Edit");
	
	public JButton BackButton = new JButton("Back");
	
	 
	public EditAccountScreen() {
		createLogin();
	}

	public static void main(String[] args){
		JFrame frame = new JFrame("Store Management");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel containerPanel = new JPanel(new GridBagLayout() );
		
		EditAccountScreen login = new EditAccountScreen();
		
		containerPanel.removeAll();
		containerPanel.add(login, new GridBagConstraints());
		frame.add(containerPanel);
		frame.revalidate();
		frame.repaint();
		
		frame.setVisible(true);
	}
	
	
	public void createLogin()
	{
		JPanel AccountTypePanel = new JPanel();
		AccountTypePanel.setLayout(new BoxLayout(AccountTypePanel, BoxLayout.X_AXIS));

		AccountTypeLabel.setMaximumSize(new Dimension(900, 600));
		AccountTypeLabel.setFont(new Font("Arial", Font.BOLD, 42));
		AccountTypePanel.add(AccountTypeLabel);

		AccountTypePanel.add(Box.createHorizontalStrut(30));
		AccountTypePanel.add(Box.createHorizontalGlue());
		  
		AccountTypeEditButton.setMaximumSize(new Dimension(900, 600));
		AccountTypeEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		AccountTypePanel.add(AccountTypeEditButton);
		
		
		JPanel NamePanel = new JPanel();
		NamePanel.setLayout(new BoxLayout(NamePanel, BoxLayout.X_AXIS));
		
		NameLabel.setMaximumSize(new Dimension(900, 600));
		NameLabel.setFont(new Font("Arial", Font.BOLD, 42));
		NamePanel.add(NameLabel);

		NamePanel.add(Box.createHorizontalStrut(30));
		NamePanel.add(Box.createHorizontalGlue());
		  
		NameEditButton.setMaximumSize(new Dimension(900, 600));
		NameEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		NamePanel.add(NameEditButton);
		
		
		JPanel UserNamePanel = new JPanel();
		UserNamePanel.setLayout(new BoxLayout(UserNamePanel, BoxLayout.X_AXIS));
		
		UserNameLabel.setMaximumSize(new Dimension(900, 600));
		UserNameLabel.setFont(new Font("Arial", Font.BOLD, 42));
		UserNamePanel.add(UserNameLabel);

		UserNamePanel.add(Box.createHorizontalStrut(30));		  
		UserNamePanel.add(Box.createHorizontalGlue());
		  
		UserNameEditButton.setMaximumSize(new Dimension(900, 600));
		UserNameEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		UserNamePanel.add(UserNameEditButton);
		
		JPanel PasswordPanel = new JPanel();
		PasswordPanel.setLayout(new BoxLayout(PasswordPanel, BoxLayout.X_AXIS));
		
		PasswordLabel.setMaximumSize(new Dimension(900, 600));
		PasswordLabel.setFont(new Font("Arial", Font.BOLD, 42));
		PasswordPanel.add(PasswordLabel);

		PasswordPanel.add(Box.createHorizontalStrut(30));
		PasswordPanel.add(Box.createHorizontalGlue());
		  
		PasswordEditButton.setMaximumSize(new Dimension(900, 600));
		PasswordEditButton.setFont(new Font("Arial", Font.BOLD, 42));
		PasswordPanel.add(PasswordEditButton);
		
		
		JPanel BackButtonPanel = new JPanel();
		BackButtonPanel.setLayout(new BoxLayout(BackButtonPanel, BoxLayout.X_AXIS));

		BackButtonPanel.add(Box.createHorizontalGlue());
		  
		BackButton.setMaximumSize(new Dimension(900, 600));
		BackButton.setFont(new Font("Arial", Font.BOLD, 90));
		BackButtonPanel.add(BackButton);

		
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		pane.add(AccountTypePanel);
		pane.add(Box.createVerticalStrut(30));
		pane.add(NamePanel);
		pane.add(Box.createVerticalStrut(30));
		pane.add(UserNamePanel);
		pane.add(Box.createVerticalStrut(30));
		pane.add(PasswordPanel);
		pane.add(Box.createVerticalStrut(30));
		pane.add(BackButtonPanel);
		  
		add(pane);
	}
}
