package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.Program;
import model.Item;
import model.Order;
import model.Product;

public class CheckoutScreen extends Screen {
	// View
	private JButton mAddButton;
	private JButton mAddOneButton;
	private JButton mPaymentButton;
	private JButton mCancelButton;

	private JMenuItem mEditMenuItem;
	private JMenuItem mRemoveMenuItem;

	private JList<Item> mItemList;
	private JLabel mSubTotalLabel;
	private JLabel mTaxLabel;
	private JLabel mTotalLabel;

	private Order mOrder;
	private int customerID;
	private JPanel mainPanel = new JPanel();

	public CheckoutScreen(JFrame frame, Order order) {
		super(frame);
		
		
		createView();

		if (order == null) {
			int id = Program.getInstance().getDataAccess().getNextOrderId();
			customerID = 0;
			String loyalty = JOptionPane.showInputDialog(mMainFrame, "Enter the Loyalty ID of the customer", "Loyalty ID", JOptionPane.PLAIN_MESSAGE);
			if(loyalty != null  && loyalty.compareTo("") != 0 && Program.getInstance().getDataAccess().checkCustomerById(Integer.parseInt(loyalty))) {
				customerID = Integer.parseInt(loyalty);
				System.out.println(customerID);
			}
			mOrder = new Order(id, customerID, 0);
		} else {
			mOrder = order;
			updateOrder();
		}


		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}
	
	/** creates view */
	private void createView() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		addPanel(mainPanel);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		mainPanel.add(listPanel);

		JLabel title = new JLabel("Check out");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		JPopupMenu popupMenu = new JPopupMenu();
		mEditMenuItem = new JMenuItem("Edit");
		mEditMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditItem();
			}
		});
		popupMenu.add(mEditMenuItem);
		popupMenu.add(new JPopupMenu.Separator());
		mRemoveMenuItem = new JMenuItem("Remove");
		mRemoveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveCurrentItem();
			}
		});
		popupMenu.add(mRemoveMenuItem);

		mItemList = new JList<>();
		mItemList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (SwingUtilities.isRightMouseButton(me) && !mItemList.isSelectionEmpty() && mItemList.locationToIndex(me.getPoint()) == mItemList.getSelectedIndex()) {
					popupMenu.show(mItemList, me.getX(), me.getY());
				}
			}
		});
		JScrollPane scrolPane = new JScrollPane(mItemList);
		scrolPane.setPreferredSize(new Dimension(300, 500));
		listPanel.add(scrolPane);

		JPanel subtotalPanel = new JPanel();
		subtotalPanel.setLayout(new BoxLayout(subtotalPanel, BoxLayout.X_AXIS));
		listPanel.add(subtotalPanel);

		JPanel taxPanel = new JPanel();
		taxPanel.setLayout(new BoxLayout(taxPanel, BoxLayout.X_AXIS));
		listPanel.add(taxPanel);

		JPanel totalPanel = new JPanel();
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.X_AXIS));
		listPanel.add(totalPanel);
		

		
		
		JLabel subTotalText = new JLabel("Sub-Total:", SwingConstants.LEFT);
		subTotalText.setFont(new Font("Serif", Font.BOLD, 18));
		subTotalText.setMaximumSize(new Dimension(Integer.MAX_VALUE, subTotalText.getMinimumSize().height));
		subtotalPanel.add(subTotalText);
		
		subtotalPanel.add(Box.createHorizontalGlue());

		mSubTotalLabel = new JLabel("$0.00", SwingConstants.RIGHT);
		mSubTotalLabel.setFont(new Font("Serif", Font.BOLD, 18));
		mSubTotalLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, mSubTotalLabel.getMinimumSize().height));
		subtotalPanel.add(mSubTotalLabel);

		
		JLabel TaxText = new JLabel("Tax:", SwingConstants.LEFT);
		TaxText.setFont(new Font("Serif", Font.BOLD, 18));
		TaxText.setMaximumSize(new Dimension(Integer.MAX_VALUE, TaxText.getMinimumSize().height));
		taxPanel.add(TaxText);
		
		taxPanel.add(Box.createHorizontalGlue());
		
		mTaxLabel = new JLabel("$0.00", SwingConstants.RIGHT);
		mTaxLabel.setFont(new Font("Serif", Font.BOLD, 18));
		mTaxLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, mTaxLabel.getMinimumSize().height));
		taxPanel.add(mTaxLabel);
		
		
		JLabel TotalText = new JLabel("Total:", SwingConstants.LEFT);
		TotalText.setFont(new Font("Serif", Font.BOLD, 18));
		TotalText.setMaximumSize(new Dimension(Integer.MAX_VALUE, TotalText.getMinimumSize().height));
		totalPanel.add(TotalText);

		totalPanel.add(Box.createHorizontalGlue());
		
		mTotalLabel = new JLabel("$0.00", SwingConstants.RIGHT);
		mTotalLabel.setFont(new Font("Serif", Font.BOLD, 18));
		mTotalLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, mTotalLabel.getMinimumSize().height));
		totalPanel.add(mTotalLabel);
		
		

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		mainPanel.add(buttonPanel);

		mAddOneButton = new JButton("Add a product");
		mAddOneButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mAddOneButton.getMinimumSize().height));
		mAddOneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddProduct();
			}
		});
		buttonPanel.add(mAddOneButton);

		mAddButton = new JButton("Add products");
		mAddButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mAddButton.getMinimumSize().height));
		mAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddProducts();
			}
		});
		buttonPanel.add(mAddButton);

		mPaymentButton = new JButton("Pay");
		mPaymentButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPaymentButton.getMinimumSize().height));
		mPaymentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openPayment();
			}
		});
		buttonPanel.add(mPaymentButton);

		mCancelButton = new JButton("Cancel");
		mCancelButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mCancelButton.getMinimumSize().height));
		mCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CancelCheckout();
			}
		});
		buttonPanel.add(mCancelButton);
	}
	
	/** shows a input dialog asking for a product id */
	private Product askForProductId() {
		String s = JOptionPane.showInputDialog(mMainFrame, "Enter the id of the product that you want:", "Product Id", JOptionPane.PLAIN_MESSAGE);

		if ((s != null) && (s.length() > 0)) {
			try {
				int id = Integer.parseInt(s);
				Product p = Program.getInstance().getDataAccess().getProductById(id);
				if (p != null) {
					return p;
				} else {
					JOptionPane.showMessageDialog(mMainFrame, "Product id is not found");
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(mMainFrame, "You need to enter an integer");
			}
		} else {
			JOptionPane.showMessageDialog(mMainFrame, "You need to enter something");
		}
		return null;
	}
	/** shows a input dialog asking for the quantity of the desired item */
	private Integer askForQuantity() {
		String s = JOptionPane.showInputDialog(mMainFrame, "Enter the quantity of the product:", "Quantity", JOptionPane.PLAIN_MESSAGE);

		if ((s != null) && (s.length() > 0)) {
			try {
				int q = Integer.parseInt(s);
				return q;
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(mMainFrame, "You need to enter an integer");
			}
		} else {
			JOptionPane.showMessageDialog(mMainFrame, "You need to enter something");
		}
		return null;
	}
	
	/** updates view for any changes that was made */
	private void updateOrder() {
		
		
		DefaultListModel<Item> itemListModel = new DefaultListModel<Item>();
		for (Item item : mOrder.getItemList()) {
			itemListModel.addElement(item);
		}
		mItemList.setModel(itemListModel);
		mSubTotalLabel.setText("$" + String.format("%.2f", mOrder.getTotal()));
		mTaxLabel.setText("$" + String.format("%.2f", (mOrder.getTotal() * .06)));
		mTotalLabel.setText("$" + String.format("%.2f", (mOrder.getTotal() * 1.06)));
	}

	/** changes the current product's quantity*/
	private void EditItem() {
		Integer q = askForQuantity();
		if (q != null) {
			Item selected = mItemList.getSelectedValue();
			if(q == 0){
				mOrder.removeItem(selected.getProductId());
				updateOrder();
				return;
			}
			if(q < 0){
				JOptionPane.showMessageDialog(mMainFrame, "Quantity must be a positive number");
				return;
			}
			mOrder.editItem(selected.getProductId(), q, selected.getmPrice());
			updateOrder();
		}
	}
	
	/** removes the item selected */
	private void RemoveCurrentItem() {
		int n = JOptionPane.showConfirmDialog(mMainFrame, "Are you sure?", "Remove item confirmation", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			Item selected = mItemList.getSelectedValue();
			mOrder.removeItem(selected.getProductId());
			updateOrder();
		}
	}
	
	/** Adds a product to the checkout */
	private void AddProduct() {
		Product p = askForProductId();
		if (p != null) {
			if(customerID!=0) {
				mOrder.addItem(p.getId(), 1, p.getUnitPrice()*(100-p.getDiscount())/100);
				updateOrder();
			} else {
			mOrder.addItem(p.getId(), 1, p.getUnitPrice());
			updateOrder();
			}
		}
	}
	
	/** Adds a quantity of an item to the checkout */
	private void AddProducts() {
		Product p = askForProductId();
		if (p != null) {
			Integer q = askForQuantity();
			if (q != null) {
				if(q == 0){
					return;
				}
				if(q < 0){
					JOptionPane.showMessageDialog(mMainFrame, "Quantity must be a positive number");
					return;
				}
				if(customerID!=0) {
					mOrder.addItem(p.getId(), q, p.getUnitPrice()*(100-p.getDiscount())/100);
					updateOrder();
				} else {
				mOrder.addItem(p.getId(), q, p.getUnitPrice());
				updateOrder();
				}
			}
		}
	}
	
	/** proceeds the checkout process to payment */
	private void openPayment() {
		removePanel(mainPanel);
		new PaymentScreen(mMainFrame, mOrder);
	}
	
	/** cancels the current order and returns to the Main Screen for the current user */
	private void CancelCheckout() {
		removePanel(mainPanel);
		openUserMainMenu();
	}
}
