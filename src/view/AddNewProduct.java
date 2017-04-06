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

import model.Employee;
import model.Product;


public class AddNewProduct  extends Screen {

	public JLabel mNameLabel;
	public JLabel mPriceLabel;
	
	public JTextField mNameEditField = new JTextField(10);
	public JTextField mPriceLabelField = new JTextField(10);

	public JButton mSaveButton = new JButton("Save");
	public JButton mCancelButton = new JButton("Cancel");
	
	 
	public AddNewProduct(JFrame frame) {
		super(frame);
		
		createView(frame);
		
		frame.pack();
		frame.setVisible(true);
	}

	private void createView(JFrame frame) {

		JPanel mainPanel = new JPanel();
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
				int i = 1;
				for(i = 1;i<Integer.MAX_VALUE;i++){
					try {
						if(Program.getInstance().getDataAccess().getProductById(i) == null)
							break;
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				try {
					Program.getInstance().getDataAccess().addProduct(new Product(i, mNameEditField.getText(), Double.parseDouble(mPriceLabelField.getText())));
				} catch (NumberFormatException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				removePanel(mainPanel);
				new InventoryScreen(frame);
			}
		});
		BackButtonPanel.add(mSaveButton);
		
		mCancelButton.setMaximumSize(new Dimension(900, 600));
		mCancelButton.setFont(new Font("Arial", Font.BOLD, 90));
		mCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new InventoryScreen(frame);
			}
		});
		BackButtonPanel.add(mCancelButton);
	}
}
