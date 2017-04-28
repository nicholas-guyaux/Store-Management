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
import javax.swing.SwingUtilities;

import controller.Program;
import model.Product;

public class InventoryScreen extends Screen {
	private JButton mSearchButton;
	private JButton mAddButton;
	private JButton mCancelButton;

	private JMenuItem mEditMenuProduct;
	private JMenuItem mRemoveMenuProduct;

	private JList<Product> mProductList;

	private JPanel mainPanel = new JPanel();
	
	public InventoryScreen(JFrame frame) {
		super(frame);

		createView();
		
		updateList();

		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}

	private void createView() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		addPanel(mainPanel);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		mainPanel.add(listPanel);

		JLabel title = new JLabel("Inventory");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		JPopupMenu popupMenu = new JPopupMenu();
		mEditMenuProduct = new JMenuItem("Edit");
		mEditMenuProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditSelectedProduct();
			}
		});
		popupMenu.add(mEditMenuProduct);
		popupMenu.add(new JPopupMenu.Separator());
		mRemoveMenuProduct = new JMenuItem("Remove");
		mRemoveMenuProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RemoveSelectedProduct();
			}
		});
		popupMenu.add(mRemoveMenuProduct);

		mProductList = new JList<>();
		mProductList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				if (SwingUtilities.isRightMouseButton(me) && !mProductList.isSelectionEmpty() && mProductList.locationToIndex(me.getPoint()) == mProductList.getSelectedIndex()) {
					popupMenu.show(mProductList, me.getX(), me.getY());
				}
			}
		});
		JScrollPane scrolPane = new JScrollPane(mProductList);
		scrolPane.setPreferredSize(new Dimension(300, 500));
		listPanel.add(scrolPane);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		mainPanel.add(buttonPanel);
		
		
		mSearchButton = new JButton("Search");
		mSearchButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mSearchButton.getMinimumSize().height));
		mSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Search();
			}
		});
		buttonPanel.add(mSearchButton);

		mAddButton = new JButton("Add Product");
		mAddButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mAddButton.getMinimumSize().height));
		mAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddNewProduct();
			}
		});
		buttonPanel.add(mAddButton);


		mCancelButton = new JButton("Cancel");
		mCancelButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mCancelButton.getMinimumSize().height));
		mCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnToMainScreen();
			}
		});
		buttonPanel.add(mCancelButton);
	}
	
	/** gets a product's id */
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

	/** updates list with a search query of "" */
	private void updateList(){
		updateList("");
	}
	
	/** updates list with search as the search query 
	 * @param search string to search for 
	 * */
	private void updateList(String search){
		DefaultListModel<Product> ProductListModel = new DefaultListModel<Product>();
		for (Product Product : Program.getInstance().getDataAccess().getInventoryList(search)) {
			ProductListModel.addElement(Product);
		}
		mProductList.setModel(ProductListModel);
	}
	
	/** Edit the currently selected product */
	private void EditSelectedProduct() {
		Product selected = mProductList.getSelectedValue();
		System.out.println("get selected product");
		removePanel(mainPanel);
		new EditProductScreen(mMainFrame,selected);
	}
	
	/** removes the currently selected product */
	private void RemoveSelectedProduct() {
		int n = JOptionPane.showConfirmDialog(mMainFrame, "Are you sure?", "Remove Product confirmation", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			Product selected = mProductList.getSelectedValue();
			Program.getInstance().getDataAccess().removeProductById(selected.getId());
			updateList();
		}
	}
	
	/** shows a dialog asking for the id to search for, then updates the list for that search */
	private void Search() {
		Product p=null;
		try {
			p = askForProductId();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (p != null) {
			updateList(p.toString());
		} else {
			JOptionPane.showMessageDialog(mMainFrame, "Invalid PrductID", "INVALID ID", JOptionPane.ERROR_MESSAGE);
		}
	}

	/** displays the add product screen */
	private void AddNewProduct() {
		removePanel(mainPanel);
		new AddNewProduct(mMainFrame);
	}

	/** returns to the main screen */
	private void returnToMainScreen() {
		removePanel(mainPanel);
		openUserMainMenu();
	}
}
