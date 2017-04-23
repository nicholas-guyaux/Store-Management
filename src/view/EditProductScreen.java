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

	private JLabel mNameLabel;
	private JLabel mPriceLabel;
	
	private JButton mNameEditButton = new JButton("Edit");
	private JButton mPriceLabelButton = new JButton("Edit");
	
	private JButton mBackButton = new JButton("Back");
	
	private JPanel mainPanel = new JPanel();
	
	private Product product;
	
	public EditProductScreen(JFrame frame, Product product) {
		super(frame);
		
		this.product = product;
		
		createView();
		
		updateAccount();
		
		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}

	/** updates view for changes */
	private void updateAccount() {	
		mNameLabel.setText("Name: " + product.getName());
		mPriceLabel.setText("Price: $" + String.format("%.2f", product.getUnitPrice()) ); // TODO
	}

	/** creates view */
	private void createView() {
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
				EditName();
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
		mPriceLabelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditPrice();
			}
		});
		UserNamePanel.add(mPriceLabelButton);
		
		
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
	
	/** opens a edit dialog for the name */
	private void EditName() {
		String input = (String) JOptionPane.showInputDialog(
				mMainFrame,
                "Name:",
                "Name Edit dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                product.getName());
		if(input == null)
			return;
		if(input.compareTo("") == 0){
			JOptionPane.showMessageDialog(mMainFrame, "must enter a value");
			return;
		}
		product.setName(input);
		updateAccount();
	}

	/** opens a edit dialog for the price */
	private void EditPrice() {
		String input = (String) JOptionPane.showInputDialog(
				mMainFrame,
                "Name:",
                "Name Edit dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                product.getUnitPrice());
		if(input == null)
			return;
		if(input.compareTo("") == 0){
			JOptionPane.showMessageDialog(mMainFrame, "must enter a value");
			return;
		}
		if(input.matches("[0-9]+(.[0-9]{2})?") == false){
			JOptionPane.showMessageDialog(mMainFrame, "price field must be in a correct format (ddddd or ddddd.dd)");
			return;
		}
		product.setPrice(Double.parseDouble(input));
		updateAccount();
	}

	/** returns to the previous screen */
	private void returnToPreviousScreen() {
		removePanel(mainPanel);
		new InventoryScreen(mMainFrame);
	}
}
