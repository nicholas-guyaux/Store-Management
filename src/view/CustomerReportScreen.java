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

import controller.Program;
import model.CustomerReport;

public class CustomerReportScreen extends Screen {



	// View
	private JButton mProductButton;
	private JButton mCustomerButton;
	private JButton mOrderButton;
	private JButton mCancelButton;

	private JList<CustomerReport> mCustomerList;
	
	private JPanel mainPanel = new JPanel();

	public CustomerReportScreen(JFrame frame, int range) {
		super(frame);
		
		
		createView();
		
		updateScreen(range);
		
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

		mCustomerList = new JList<>();
						
		JScrollPane scrolPane1 = new JScrollPane(mCustomerList);
		scrolPane1.setPreferredSize(new Dimension(300, 500));
		listPanel.add(scrolPane1);	
		
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
		if ("2 weeks".equals(askForDate())) {
			removePanel(mainPanel);
			new ProductReportScreen(mMainFrame, -14);
		} else if ("1 month".equals(askForDate())) {
			removePanel(mainPanel);
			new ProductReportScreen(mMainFrame, -30);
		} else if ("Quarter".equals(askForDate())) {
			removePanel(mainPanel);
			new ProductReportScreen(mMainFrame, -90);
		}
	}


	private void customerReport() {
		// TODO Auto-generated method stub
		if ("2 weeks".equals(askForDate())) {
			removePanel(mainPanel);
			new CustomerReportScreen(mMainFrame, -14);
		} else if ("1 month".equals(askForDate())) {
			removePanel(mainPanel);
			new CustomerReportScreen(mMainFrame, -30);
		} else if ("Quarter".equals(askForDate())) {
			removePanel(mainPanel);
			new CustomerReportScreen(mMainFrame, -90);
		}
	}


	private void updateScreen(int range){
		DefaultListModel<CustomerReport> CustomerListModel = new DefaultListModel<CustomerReport>();
		for (CustomerReport customer : Program.getInstance().getDataAccess().getCustomerReportList(range)) {
			CustomerListModel.addElement(customer);
		}
		mCustomerList.setModel(CustomerListModel);
	}
	
	
	
	
	
	/** cancels the current order and returns to the Main Screen for the current user */
	private void CancelCheckout() {
		removePanel(mainPanel);
		openUserMainMenu();
	}
}