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

import model.Product;


public class EditProductScreen  extends Screen {

	public JLabel mNameLabel;
	public JLabel mPriceLabel;
	
	public JButton mNameEditButton = new JButton("Edit");
	public JButton mPriceLabelButton = new JButton("Edit");
	
	public JButton mBackButton = new JButton("Back");
	
	 
	public EditProductScreen(JFrame frame, Product product) {
		super(frame);
		
		createView(frame, product);
		
		updateAccount(product);
		
		frame.pack();
		frame.setVisible(true);
	}


	private void updateAccount(Product product) {	
		mNameLabel.setText("Name: " + product.getName());
		mPriceLabel.setText("Price: $" + String.format("%.2f", product.getUnitPrice()) ); // TODO
	}


	private void createView(JFrame frame, Product product) {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addPanel(mainPanel);
		

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
				String input = (String) JOptionPane.showInputDialog(
		                frame,
		                "Name:",
		                "Name Edit dialog",
		                JOptionPane.PLAIN_MESSAGE,
		                null,
		                null,
		                "Jordan Knudsen");
				//product.setName(input); // TODO
				updateAccount(product);
			}
		});
		NamePanel.add(mNameEditButton);
		

		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel UserNamePanel = new JPanel();
		UserNamePanel.setLayout(new BoxLayout(UserNamePanel, BoxLayout.X_AXIS));
		mainPanel.add(UserNamePanel);

		mPriceLabel = new JLabel("Username: ");
		mPriceLabel.setMaximumSize(new Dimension(900, 600));
		mPriceLabel.setFont(new Font("Arial", Font.BOLD, 42));
		UserNamePanel.add(mPriceLabel);

		UserNamePanel.add(Box.createHorizontalStrut(30));		  
		UserNamePanel.add(Box.createHorizontalGlue());
		  
		mPriceLabelButton.setMaximumSize(new Dimension(900, 600));
		mPriceLabelButton.setFont(new Font("Arial", Font.BOLD, 42));
		//mUserNameEditButton.addActionListener(this);
		try {
			if(Program.getInstance().getDataAccess().getCurrentUser().isManager()){
				UserNamePanel.add(mPriceLabelButton);
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
				new InventoryScreen(frame);
			}
		});
		BackButtonPanel.add(mBackButton);
	}
}
