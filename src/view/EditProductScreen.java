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

import controller.Program;
import model.Product;


public class EditProductScreen  extends Screen {

	private JLabel mNameLabel;
	private JLabel mPriceLabel;
	private JLabel mQuantityLabel;
	private JLabel mDiscountLabel;
	
	private JButton mNameEditButton = new JButton("Edit");
	private JButton mPriceLabelButton = new JButton("Edit");
	private JButton mQuantityLabelButton = new JButton("Edit");
	private JButton mDiscountLabelButton = new JButton("Edit");
	
	private JButton mBackButton = new JButton("Back");
	
	private JPanel mainPanel = new JPanel();
	
	private Product product;
	
	public EditProductScreen(JFrame frame, Product product) {
		super(frame);
		
		this.product = product;
		
		createView();
		
		updateProduct();
		
		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}

	/** updates view for changes */
	private void updateProduct() {	
		System.out.println("update product");
		Program.getInstance().getDataAccess().modifyProductById(product.getId(), product);
		mNameLabel.setText("Name: " + product.getName());
		mPriceLabel.setText("Price: $" + String.format("%.2f", product.getUnitPrice()) );
		mQuantityLabel.setText("Quantity: " + product.getQuantity());
		mDiscountLabel.setText("Discount: " + product.getDiscount() + "%");
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
		
		
		JPanel PricePanel = new JPanel();
		PricePanel.setLayout(new BoxLayout(PricePanel, BoxLayout.X_AXIS));
		mainPanel.add(PricePanel);

		mPriceLabel = new JLabel("Price: ");
		mPriceLabel.setMaximumSize(new Dimension(900, 600));
		mPriceLabel.setFont(new Font("Arial", Font.BOLD, 42));
		PricePanel.add(mPriceLabel);

		PricePanel.add(Box.createHorizontalStrut(30));		  
		PricePanel.add(Box.createHorizontalGlue());
		  
		mPriceLabelButton.setMaximumSize(new Dimension(900, 600));
		mPriceLabelButton.setFont(new Font("Arial", Font.BOLD, 42));
		mPriceLabelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditPrice();
			}
		});
		PricePanel.add(mPriceLabelButton);
		
		
		mainPanel.add(Box.createVerticalStrut(30));
		
		JPanel QuantityPanel = new JPanel();
		QuantityPanel.setLayout(new BoxLayout(QuantityPanel, BoxLayout.X_AXIS));
		mainPanel.add(QuantityPanel);
		
		mQuantityLabel = new JLabel("Quantity: ");
		mQuantityLabel.setMaximumSize(new Dimension(900, 600));
		mQuantityLabel.setFont(new Font("Arial", Font.BOLD, 42));
		QuantityPanel.add(mQuantityLabel);

		QuantityPanel.add(Box.createHorizontalStrut(30));		  
		QuantityPanel.add(Box.createHorizontalGlue());
		  
		mQuantityLabelButton.setMaximumSize(new Dimension(900, 600));
		mQuantityLabelButton.setFont(new Font("Arial", Font.BOLD, 42));
		mQuantityLabelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditQuantity();
			}
		});
		QuantityPanel.add(mQuantityLabelButton);
		
		mainPanel.add(Box.createVerticalStrut(30));
		

		JPanel DiscountPanel = new JPanel();
		DiscountPanel.setLayout(new BoxLayout(DiscountPanel, BoxLayout.X_AXIS));
		mainPanel.add(DiscountPanel);
		
		mDiscountLabel = new JLabel("Discount: ");
		mDiscountLabel.setMaximumSize(new Dimension(900, 600));
		mDiscountLabel.setFont(new Font("Arial", Font.BOLD, 42));
		DiscountPanel.add(mDiscountLabel);

		DiscountPanel.add(Box.createHorizontalStrut(30));		  
		DiscountPanel.add(Box.createHorizontalGlue());
		  
		mDiscountLabelButton.setMaximumSize(new Dimension(900, 600));
		mDiscountLabelButton.setFont(new Font("Arial", Font.BOLD, 42));
		mDiscountLabelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditDiscount();
			}
		});
		DiscountPanel.add(mDiscountLabelButton);
		
		
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
		updateProduct();
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
		product.setPrice(Float.parseFloat(input));
		updateProduct();
	}
	
	private void EditQuantity() {
		String input = (String) JOptionPane.showInputDialog(
				mMainFrame,
                "Quantity:",
                "Quantity Edit dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                product.getQuantity());
		if(input == null)
			return;
		if(input.compareTo("") == 0){
			JOptionPane.showMessageDialog(mMainFrame, "must enter a value");
			return;
		}
		if(input.matches("0*[1-9][0-9]*?") == false){
			JOptionPane.showMessageDialog(mMainFrame, "quantity field must be in a correct format (ddddd) and non negative");
			return;
		}
		product.setQuantity(Integer.parseInt(input));
		updateProduct();
	}
	
	private void EditDiscount() {
		String input = (String) JOptionPane.showInputDialog(
				mMainFrame,
                "Discount:",
                "Discount Edit dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                product.getDiscount());
		if(input == null)
			return;
		if(input.compareTo("") == 0){
			JOptionPane.showMessageDialog(mMainFrame, "must enter a value");
			return;
		}
		if(input.matches("[0-9][0-9]?") == false){
			JOptionPane.showMessageDialog(mMainFrame, "discount field must be in a correct format (dd) and < 100 ");
			return;
		}
		product.setDiscount(Integer.parseInt(input));
		updateProduct();
	}

	/** returns to the previous screen */
	private void returnToPreviousScreen() {
		removePanel(mainPanel);
		new InventoryScreen(mMainFrame);
	}
}
