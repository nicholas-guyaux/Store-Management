package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
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

import controller.Program;
import model.Item;
import model.Order;

public class PayoutScreen extends Screen {
	// View
	private JButton mPayCash;
	private JButton mPayCreditCard;
	private JButton mPayDebitCard;
	private JButton mCancelButton;

	private JList<Item> mItemList;
	private JLabel mSubTotalLabel;
	private JLabel mTaxLabel;
	private JLabel mTotalLabel;
	private JLabel mPriceDifferenceLabel;

	private Order mOrder;
	
	private JPanel mainPanel = new JPanel();

	public PayoutScreen(JFrame frame, Order order) {
		super(frame);

		createView();

		mOrder = order;
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

		JLabel title = new JLabel("Payment");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		mItemList = new JList<>();
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

		mPayCash = new JButton("Cash");
		mPayCash.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPayCash.getMinimumSize().height));
		mPayCash.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				payWithCash();
			}
		});
		buttonPanel.add(mPayCash);

		mPayCreditCard = new JButton("Credit Card");
		mPayCreditCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPayCreditCard.getMinimumSize().height));
		mPayCreditCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PayWithCreditCard();
			}
		});
		buttonPanel.add(mPayCreditCard);

		mPayDebitCard = new JButton("Debit Card");
		mPayDebitCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, mPayDebitCard.getMinimumSize().height));
		mPayDebitCard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PayWithDebitCard();
			}
		});
		buttonPanel.add(mPayDebitCard);

		mCancelButton = new JButton("Cancel");
		mCancelButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mCancelButton.getMinimumSize().height));
		mCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CancelPayment();
			}
		});
		buttonPanel.add(mCancelButton);
	}

	/** updates the view for changes in the order */
	private void updateOrder() {
		DefaultListModel<Item> itemListModel = new DefaultListModel<Item>();
		for (Item item : mOrder.getReturnList()) {
			itemListModel.addElement(item);
		}
		mItemList.setModel(itemListModel);
		mSubTotalLabel.setText("$" + String.format("%.2f", mOrder.getTotal()));
		mTaxLabel.setText("$" + String.format("%.2f", (mOrder.getTotal() * .06)));
		mTotalLabel.setText("$" + String.format("%.2f", (mOrder.getTotal() * 1.06)));
		mPriceDifferenceLabel.setText("$" + String.format("%.2f", (mOrder.getReturnPrice())));
	}
	
	/** processes payment with cash */
	private void payWithCash() {
		double due = mOrder.getReturnPrice();
		int confirmation = JOptionPane.showConfirmDialog(null,
                "give $"+ String.format("%.2f", due) + " back to the customer", "confirm?",
                JOptionPane.YES_NO_OPTION);
		if(confirmation==0){
			PaymentSuccess();
		}
	}
	
	/** processes payment with a credit card */
	private void PayWithCreditCard() {
		double due = mOrder.getReturnPrice();
		int confirmation = JOptionPane.showConfirmDialog(null,
                "Amount to give back: $"+ String.format("%.2f", due) + "\n Was transaction successful?", "confirm?",
                JOptionPane.YES_NO_OPTION);
		if(confirmation==0){
			PaymentSuccess();
		}
	}

	/** processes payment with a debit card */
	private void PayWithDebitCard() {
		double due = mOrder.getReturnPrice();
		int confirmation = JOptionPane.showConfirmDialog(null,
                "Amount to give back: $"+ String.format("%.2f", due) + "\n Was transaction successful?", "confirm?",
                JOptionPane.YES_NO_OPTION);
		if(confirmation==0){
			PaymentSuccess();
		}
	}
	
	/** saves the order and returns to main screen for the current user */
	private void PaymentSuccess(){
		mOrder.saveReturn();
		Program.getInstance().getDataAccess().SaveOrder(mOrder);
		removePanel(mainPanel);
		openUserMainMenu();
	}

	/** Cancel's the payment, and returns to the checkout screen for the same order */
	private void CancelPayment() {
		removePanel(mainPanel);
		new ReturnScreen(mMainFrame, mOrder);
	}
}
