package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JLabel mTotalLabel;

	// Controller
	private Order mOrder;
	public CheckoutScreen(JFrame frame, Order order) {
		super(frame);

		createView(frame);

		if (order == null) {
			mOrder = new Order();
		} else {
			mOrder = order;
			updateOrder();
		}

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private void createView(JFrame frame) {
		JPanel mainPanel = new JPanel();
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
				Integer q = askForQuantity();
				if (q != null) {
					Item selected = mItemList.getSelectedValue();
					mOrder.editItem(selected.getProduct(), q);
					updateOrder();
				}
			}
		});
		popupMenu.add(mEditMenuItem);
		popupMenu.add(new JPopupMenu.Separator());
		mRemoveMenuItem = new JMenuItem("Remove");
		mRemoveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Remove item confirmation", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					Item selected = mItemList.getSelectedValue();
					mOrder.removeItem(selected.getProduct());
					updateOrder();
				}
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

		mTotalLabel = new JLabel("$ 0", SwingConstants.RIGHT);
		mTotalLabel.setFont(new Font("Serif", Font.BOLD, 18));
		mTotalLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, mTotalLabel.getMinimumSize().height));
		listPanel.add(mTotalLabel);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		mainPanel.add(buttonPanel);

		mAddOneButton = new JButton("Add a product");
		mAddOneButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mAddOneButton.getMinimumSize().height));
		mAddOneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product p=null;
				try {
					p = askForProductId();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				if (p != null) {
					mOrder.addItem(p, 1);
					updateOrder();
				}
			}
		});
		buttonPanel.add(mAddOneButton);

		mAddButton = new JButton("Add products");
		mAddButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mAddButton.getMinimumSize().height));
		mAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product p=null;
				try {
					p = askForProductId();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				if (p != null) {
					Integer q = askForQuantity();
					if (q != null) {
						mOrder.addItem(p, q);
						updateOrder();
					}
				}
			}
		});
		buttonPanel.add(mAddButton);

		mPaymentButton = new JButton("Pay");
		mPaymentButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPaymentButton.getMinimumSize().height));
		mPaymentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new PaymentScreen(frame, mOrder);
			}
		});
		buttonPanel.add(mPaymentButton);

		mCancelButton = new JButton("Cancel");
		mCancelButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mCancelButton.getMinimumSize().height));
		mCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				openUserMainMenu();
			}
		});
		buttonPanel.add(mCancelButton);
	}

	private Product askForProductId() throws ClassNotFoundException {
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

	private void updateOrder() {
		DefaultListModel<Item> itemListModel = new DefaultListModel<Item>();
		for (Item item : mOrder.getOrderList()) {
			itemListModel.addElement(item);
		}
		mItemList.setModel(itemListModel);
		mTotalLabel.setText("$ " + String.format("%.2f", mOrder.getTotal()));
	}
}
