package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import model.Item;
import model.Order;

public class PaymentScreen extends Screen {
	// View
	private JButton mPayCash;
	private JButton mPayCreditCard;
	private JButton mPayDebitCard;
	private JButton mCancelButton;

	private JList<Item> mItemList;
	private JLabel mTotalLabel;

	// Controller
	private Order mOrder;

	public PaymentScreen(JFrame frame, Order order) {
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

		JLabel title = new JLabel("Payment");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		mItemList = new JList<>();
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

		mPayCash = new JButton("Cash");
		mPayCash.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPayCash.getMinimumSize().height));
		mPayCash.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "This functionality has not been implemented yet!!!");
			}
		});
		buttonPanel.add(mPayCash);

		mPayCreditCard = new JButton("Credit Card");
		mPayCreditCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPayCreditCard.getMinimumSize().height));
		mPayCreditCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "This functionality has not been implemented yet!!!");
			}
		});
		buttonPanel.add(mPayCreditCard);

		mPayDebitCard = new JButton("Debit Card");
		mPayDebitCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPayDebitCard.getMinimumSize().height));
		mPayDebitCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "This functionality has not been implemented yet!!!");
			}
		});
		buttonPanel.add(mPayDebitCard);

		mCancelButton = new JButton("Cancel");
		mCancelButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mCancelButton.getMinimumSize().height));
		mCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new CheckoutScreen(frame, mOrder);
			}
		});
		buttonPanel.add(mCancelButton);
	}

	private void updateOrder() {
		DefaultListModel<Item> itemListModel = new DefaultListModel<Item>();
		for (Item item : mOrder.getOrderList()) {
			itemListModel.addElement(item);
		}
		mItemList.setModel(itemListModel);

		mTotalLabel.setText("$ " + Double.toString(mOrder.getTotal()));
	}
}
