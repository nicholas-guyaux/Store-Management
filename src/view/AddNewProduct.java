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
import javax.swing.JTextField;

import controller.Program;
import model.Product;


public class AddNewProduct  extends Screen {

	private JLabel mNameLabel;
	private JLabel mPriceLabel;
	
	private JTextField mNameEditField = new JTextField(10);
	private JTextField mPriceLabelField = new JTextField(10);

	private JButton mSaveButton = new JButton("Save");
	private JButton mCancelButton = new JButton("Cancel");
	
	private JPanel mainPanel = new JPanel();
	 
	public AddNewProduct(JFrame frame) {
		super(frame);
		
		createView();
		
		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}
	
	/** creates view */
	private void createView() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		addPanel(mainPanel);
		
		
		JPanel NamePanel = new JPanel();
		NamePanel.setLayout(new BoxLayout(NamePanel, BoxLayout.X_AXIS));
		mainPanel.add(NamePanel);
		
		mNameLabel = new JLabel("Name: ");
		mNameLabel.setMaximumSize(new Dimension(900, 600));
		mNameLabel.setFont(new Font("Arial", Font.BOLD, 42));
		NamePanel.add(mNameLabel);

		NamePanel.add(Box.createHorizontalStrut(30));
		NamePanel.add(Box.createHorizontalGlue());
		  
		mNameEditField.setMaximumSize(new Dimension(900, 600));
		mNameEditField.setFont(new Font("Arial", Font.BOLD, 42));
		NamePanel.add(mNameEditField);
		

		mainPanel.add(Box.createVerticalStrut(30));
		
		
		JPanel UserNamePanel = new JPanel();
		UserNamePanel.setLayout(new BoxLayout(UserNamePanel, BoxLayout.X_AXIS));
		mainPanel.add(UserNamePanel);

		mPriceLabel = new JLabel("Price: ");
		mPriceLabel.setMaximumSize(new Dimension(900, 600));
		mPriceLabel.setFont(new Font("Arial", Font.BOLD, 42));
		UserNamePanel.add(mPriceLabel);

		UserNamePanel.add(Box.createHorizontalStrut(30));		  
		UserNamePanel.add(Box.createHorizontalGlue());
		  
		mPriceLabelField.setMaximumSize(new Dimension(900, 600));
		mPriceLabelField.setFont(new Font("Arial", Font.BOLD, 42));
		UserNamePanel.add(mPriceLabelField);
		
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
				Save();
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

		mainPanel.getRootPane().setDefaultButton(mSaveButton);
		mNameEditField.requestFocusInWindow(); 
	}
	
	/** checks fields and if they are valid saves the product into the system */
	private void Save() {
		if(mNameEditField.getText().compareTo("") == 0 || mPriceLabelField.getText().compareTo("") == 0 ){
			JOptionPane.showMessageDialog(mMainFrame, "must fill in all fields");
			return;
		}
		if(mPriceLabelField.getText().matches("[0-9]+(.[0-9]{2})?") == false){
			JOptionPane.showMessageDialog(mMainFrame, "price field must be in a correct format (ddddd or ddddd.dd)");
			return;
		}
		int i = 1;
		for(i = 1;i<Integer.MAX_VALUE;i++){
			if(Program.getInstance().getDataAccess().getProductById(i) == null)
				break;
		}
		Program.getInstance().getDataAccess().addProduct(new Product(i, mNameEditField.getText(), Double.parseDouble(mPriceLabelField.getText())));

		removePanel(mainPanel);
		new InventoryScreen(mMainFrame);
	}

	/** cancels the creation of a new product */
	private void cancel() {
		removePanel(mainPanel);
		new InventoryScreen(mMainFrame);
	}
}
