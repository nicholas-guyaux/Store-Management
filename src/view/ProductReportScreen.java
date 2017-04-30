
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
import model.ProductReport;

public class ProductReportScreen extends Screen {



	// View
	private JButton mProductButton;
	private JButton mCustomerButton;
	private JButton mCancelButton;

	private JList<ProductReport> mProductList;
	
	private JPanel mainPanel = new JPanel();

	public ProductReportScreen(JFrame frame, int range) {
		super(frame);
		
		
		createView(range);
		
		

		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}
	
	/** creates view */
	private void createView(int range) {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		addPanel(mainPanel);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		mainPanel.add(listPanel);

		JLabel title = new JLabel("Reports");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		mProductList = new JList<>();
				
		JScrollPane scrolPane1 = new JScrollPane(mProductList);
		scrolPane1.setPreferredSize(new Dimension(300, 500));
		listPanel.add(scrolPane1);	
		
		DefaultListModel<ProductReport> ProductListModel = new DefaultListModel<ProductReport>();
		for (ProductReport product : Program.getInstance().getDataAccess().getProductReportList(range)) {
			ProductListModel.addElement(product);
		}
		mProductList.setModel(ProductListModel);
		
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
		String range = askForDate();
		if ("2 weeks".equals(range)) {
			removePanel(mainPanel);
			new ProductReportScreen(mMainFrame, -14);
		} else if ("1 month".equals(range)) {
			removePanel(mainPanel);
			new ProductReportScreen(mMainFrame, -30);
		} else if ("Quarter".equals(range)) {
			removePanel(mainPanel);
			new ProductReportScreen(mMainFrame, -90);
		}
	}


	private void customerReport() {
		// TODO Auto-generated method stub
		String range = askForDate();
		if ("2 weeks".equals(range)) {
			removePanel(mainPanel);
			new CustomerReportScreen(mMainFrame, -14);
		} else if ("1 month".equals(range)) {
			removePanel(mainPanel);
			new CustomerReportScreen(mMainFrame, -30);
		} else if ("Quarter".equals(range)) {
			removePanel(mainPanel);
			new CustomerReportScreen(mMainFrame, -90);
		}
	}

	
	/** cancels the current order and returns to the Main Screen for the current user */
	private void CancelCheckout() {
		removePanel(mainPanel);
		openUserMainMenu();
	}
}