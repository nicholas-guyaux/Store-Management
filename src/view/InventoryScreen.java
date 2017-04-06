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

import model.Employee;
import model.Product;

public class InventoryScreen extends Screen {
	// View
	private JButton mSearchButton;
	private JButton mAddButton;
	private JButton mCancelButton;

	private JMenuItem mEditMenuProduct;
	private JMenuItem mRemoveMenuProduct;

	private JList<Product> mProductList;

	// Controller
	public InventoryScreen(JFrame frame) {
		super(frame);

		createView(frame);
		
		try {
			updateList();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		JLabel title = new JLabel("Inventory");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		JPopupMenu popupMenu = new JPopupMenu();
		mEditMenuProduct = new JMenuItem("Edit");
		mEditMenuProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Product selected = mProductList.getSelectedValue();
	
				removePanel(mainPanel);
				new EditProductScreen(frame,selected);
			}
		});
		popupMenu.add(mEditMenuProduct);
		popupMenu.add(new JPopupMenu.Separator());
		mRemoveMenuProduct = new JMenuItem("Remove");
		mRemoveMenuProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Remove Product confirmation", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					Product selected = mProductList.getSelectedValue();
					try {
						Program.getInstance().getDataAccess().removeProductById(selected.getId());
						updateList();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
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
				Product p=null;
				try {
					p = askForProductId();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				if (p != null) {
					try {
						updateList(p.toString());
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		buttonPanel.add(mSearchButton);

		mAddButton = new JButton("Add Product");
		mAddButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mAddButton.getMinimumSize().height));
		mAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new AddNewProduct(frame);
			}
		});
		buttonPanel.add(mAddButton);


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

	private void updateList() throws ClassNotFoundException {
		updateList("");
	}
	private void updateList(String search) throws ClassNotFoundException {
		DefaultListModel<Product> ProductListModel = new DefaultListModel<Product>();
		for (Product Product : Program.getInstance().getDataAccess().getInventoryList(search)) {
			ProductListModel.addElement(Product);
		}
		mProductList.setModel(ProductListModel);
	}
}
