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

public class ReturnScreen extends Screen {
	// View
	private JButton mReturnButton;
	private JButton mPayoutButton;
	private JButton mCancelButton;

	private JMenuItem mEditMenuItem;
	private JMenuItem mRemoveMenuItem;
	private JMenuItem mReturnMenuItem;

	private JList<Item> mItemList;
	private JLabel mSubTotalLabel;
	private JLabel mTaxLabel;
	private JLabel mTotalLabel;
	private JLabel mPriceDifferenceLabel;

	private Order mOrder;
	
	private JPanel mainPanel = new JPanel();

	public ReturnScreen(JFrame frame, Order order) {
		super(frame);

		createView();

		mOrder = new Order(order);
		updateOrder();
		
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

		JLabel title = new JLabel("Returns");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		JPopupMenu popupMenu = new JPopupMenu();
		mReturnMenuItem = new JMenuItem("Return");
		mReturnMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReturnItem();
			}
		});
		popupMenu.add(mReturnMenuItem);
		
		JPopupMenu returnPopupMenu = new JPopupMenu();
		mEditMenuItem = new JMenuItem("Edit");
		mEditMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditReturn();
			}
		});
		returnPopupMenu.add(mEditMenuItem);
		returnPopupMenu.add(new JPopupMenu.Separator());
		mRemoveMenuItem = new JMenuItem("Remove");
		mRemoveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveReturn();
			}
		});
		returnPopupMenu.add(mRemoveMenuItem);

		mItemList = new JList<>();
		mItemList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (SwingUtilities.isRightMouseButton(me) && !mItemList.isSelectionEmpty() && mItemList.locationToIndex(me.getPoint()) == mItemList.getSelectedIndex()) {
					if(mItemList.getSelectedValue().getQuantity() > 0)
						popupMenu.show(mItemList, me.getX(), me.getY());
					else
						returnPopupMenu.show(mItemList, me.getX(), me.getY());	
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
		
		JPanel PriceDifferencePanel = new JPanel();
		PriceDifferencePanel.setLayout(new BoxLayout(PriceDifferencePanel, BoxLayout.X_AXIS));
		listPanel.add(PriceDifferencePanel);
		
		JLabel PriceDifferenceText = new JLabel("Return Total:", SwingConstants.LEFT);
		PriceDifferenceText.setFont(new Font("Serif", Font.BOLD, 18));
		PriceDifferenceText.setMaximumSize(new Dimension(Integer.MAX_VALUE, PriceDifferenceText.getMinimumSize().height));
		PriceDifferencePanel.add(PriceDifferenceText);

		PriceDifferencePanel.add(Box.createHorizontalGlue());
		
		mPriceDifferenceLabel = new JLabel("$0.00", SwingConstants.RIGHT);
		mPriceDifferenceLabel.setFont(new Font("Serif", Font.BOLD, 18));
		mPriceDifferenceLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPriceDifferenceLabel.getMinimumSize().height));
		PriceDifferencePanel.add(mPriceDifferenceLabel);
		
		

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		mainPanel.add(buttonPanel);

		mReturnButton = new JButton("Return Product");
		mReturnButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mReturnButton.getMinimumSize().height));
		mReturnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnProduct();
			}
		});
		buttonPanel.add(mReturnButton);

		mPayoutButton = new JButton("Payout");
		mPayoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPayoutButton.getMinimumSize().height));
		mPayoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openPayoutScreen();
			}
		});
		buttonPanel.add(mPayoutButton);

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
	private int askForProductId() {
		String s = JOptionPane.showInputDialog(mMainFrame, "Enter the id of the product that you want:", "Product Id", JOptionPane.PLAIN_MESSAGE);

		if ((s != null) && (s.length() > 0)) {
			try {
				int id = Integer.parseInt(s);
				int p = id;
				if (mOrder.getProdPosition(mOrder.getmItemList(), id)>=0) {
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
		return -1;
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
		for (Item returnItem : mOrder.getReturnList()) {
			itemListModel.addElement(returnItem);
		}
		mItemList.setModel(itemListModel);
		mSubTotalLabel.setText("$" + String.format("%.2f", mOrder.getTotal()));
		mTaxLabel.setText("$" + String.format("%.2f", (mOrder.getTotal() * .06)));
		mTotalLabel.setText("$" + String.format("%.2f", (mOrder.getTotal() * 1.06)));
		mPriceDifferenceLabel.setText("$" + String.format("%.2f", (mOrder.getReturnPrice())));
	}

	/** changes the current product's quantity*/
	private void EditReturn() {
		Integer q = askForQuantity();
		if (q != null) {
			if(q < 0)
				q *= -1;
			Item selected = mItemList.getSelectedValue();
			if(q > mOrder.getQuantityOfProduct(selected.getProductID()) - selected.getQuantity()){
				JOptionPane.showMessageDialog(mMainFrame, "Cannot return a quantity more than the items that were bought");
				return;
			}
			mOrder.editReturn(selected.getProductID(), q);
			updateOrder();
		}
	}
	
	/** removes the item selected */
	private void RemoveReturn() {
		int n = JOptionPane.showConfirmDialog(mMainFrame, "Are you sure?", "Remove item confirmation", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			Item selected = mItemList.getSelectedValue();
			mOrder.removeReturn(selected.getProductID());
			updateOrder();
		}
	}
	
	private void ReturnItem(){
		Item selected = mItemList.getSelectedValue();
		mOrder.returnProduct(selected.getProductID(), mOrder.getQuantityOfProduct(selected.getProductID()));
		updateOrder();
	}
	
	/** Adds a quantity of an item to the checkout */
	private void returnProduct() {
		int p = askForProductId();
		if (p != 0) {
			int q = mOrder.getProdPosition(mOrder.getmItemList(), p);
			if(q <= 0){
				JOptionPane.showMessageDialog(mMainFrame, "can only return products that are left in the current order");
				return;
			}
			if(q > 1){
				Integer rq = askForQuantity();
				if (rq != null) {
					if(rq > q){
						JOptionPane.showMessageDialog(mMainFrame, "can only return up to the quantity of items that are left in the order");
						return;
					}
					mOrder.returnProduct(p, rq);
					updateOrder();
				}
			}
			else{
				mOrder.returnProduct(p, 1);
				updateOrder();
			}
		}
	}
	
	/** proceeds the checkout process to payment */
	private void openPayoutScreen() {
		removePanel(mainPanel);
		new PayoutScreen(mMainFrame, mOrder);
	}
	
	/** cancels the current order and returns to the Main Screen for the current user */
	private void CancelCheckout() {
		removePanel(mainPanel);
		openUserMainMenu();
	}
}
