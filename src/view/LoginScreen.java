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


public class LoginScreen  extends JPanel {
	
	public JPanel bottom = new JPanel();

	public JTextField username = new JTextField();
	
	public JTextField password = new JTextField();
	
	public JButton LogInButton = new JButton("Login");
	
	public JButton CancelButton = new JButton("Cancel");
	 
	public LoginScreen() {
		createLogin();
	}

	public static void main(String[] args){
		JFrame frame = new JFrame("Store Management");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JPanel containerPanel = new JPanel(new GridBagLayout() );
		
		LoginScreen login = new LoginScreen();
		
		containerPanel.removeAll();
		containerPanel.add(login, new GridBagConstraints());
		frame.add(containerPanel);
		frame.revalidate();
		frame.repaint();
		
		frame.setVisible(true);
	}
	
	
	public void createLogin()
	{
		bottom.removeAll();
		this.removeAll();
		
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
				 
		LogInButton.setMaximumSize(new Dimension(800, 500));
		LogInButton.setFont(new Font("Arial", Font.BOLD, 90));
		bottom.add(LogInButton);

		  
		bottom.add(Box.createHorizontalStrut(30));
		  
		CancelButton.setMaximumSize(new Dimension(800, 500));
		CancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		bottom.add(CancelButton);
		
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		

		username.setMaximumSize(new Dimension(800, 500));
		username.setFont(new Font("Arial", Font.BOLD, 90));
		JLabel usernameText = new JLabel("Username:");
		usernameText.setFont(new Font("Arial", Font.BOLD, 90));
		usernameText.setAlignmentX(RIGHT_ALIGNMENT);
		pane.add(usernameText);
		pane.add(username);

		pane.add(Box.createVerticalStrut(30));
		
		password.setMaximumSize(new Dimension(800, 500));
		password.setFont(new Font("Arial", Font.BOLD, 90));
		JLabel passwordText = new JLabel("Password:");
		passwordText.setFont(new Font("Arial", Font.BOLD, 90));
		passwordText.setAlignmentX(RIGHT_ALIGNMENT);
		pane.add(passwordText);
		pane.add(password);

		pane.add(Box.createVerticalStrut(30));
		
		pane.add(bottom);
		  
		add(pane);
	}
}
