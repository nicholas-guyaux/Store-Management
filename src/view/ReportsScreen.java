package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Program;
import model.Item;
import model.Product;

public class ReportsScreen extends Screen {
	// View
	private JButton mProductButton;
	private JButton mCustomerButton;
	private JButton mOrderButton;
	private JButton mCancelButton;

	private JList<Item> mItemList;
	
	private JPanel mainPanel = new JPanel();

	public ReportsScreen(JFrame frame) {
		super(frame);
		
		
		createView();

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

		JLabel title = new JLabel("Reports");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		mItemList = new JList<>();
		
		JScrollPane scrolPane = new JScrollPane(mItemList);
		scrolPane.setPreferredSize(new Dimension(300, 500));
		listPanel.add(scrolPane);		
		

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		mainPanel.add(buttonPanel);

		mCustomerButton = new JButton("Product Report");
		mCustomerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mCustomerButton.getMinimumSize().height));
		mCustomerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				productReport();
			}
		});
		buttonPanel.add(mCustomerButton);

		mProductButton = new JButton("Customer Report");
		mProductButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mProductButton.getMinimumSize().height));
		mProductButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				customerReport();
			}
		});
		buttonPanel.add(mProductButton);

		mOrderButton = new JButton("Order Report");
		mOrderButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mOrderButton.getMinimumSize().height));
		mOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				orderReport();
			}
		});
		buttonPanel.add(mOrderButton);

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
	
	private String askForDate(){
		String[] dates = { "2 weeks", "1 month", "Quarter" };
		
	    String dateRange = (String) JOptionPane.showInputDialog(mMainFrame, 
	        "Date Range:",
	        "Date Range selector",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        dates, 
	        dates[0]);

	    System.out.printf("Date Range: %s.\n", dateRange);
	    return dateRange;
	}
	
	private void productReport() {
		// TODO Auto-generated method stub
		askForDate();
	}

	private void orderReport() {
		// TODO Auto-generated method stub
		askForDate();
	}

	private void customerReport() {
		// TODO Auto-generated method stub
		askForDate();
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

	
	
	
	
	
	/** cancels the current order and returns to the Main Screen for the current user */
	private void CancelCheckout() {
		removePanel(mainPanel);
		openUserMainMenu();
	}
}
