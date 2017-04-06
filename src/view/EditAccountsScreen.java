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
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import controller.Program;
import model.Employee;
import model.Item;
import model.Order;
import model.Product;

public class EditAccountsScreen extends Screen {
	// View
	private JButton mAddButton;
	private JButton mBackButton;

	private JMenuItem mEditMenuItem;
	private JMenuItem mRemoveMenuItem;

	private JList<Employee> mItemList;

	// Controller
	public EditAccountsScreen(JFrame frame) {
		super(frame);

		createView(frame);

		updateAccounts();
		
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

		JLabel title = new JLabel("Edit Employees");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		JPopupMenu popupMenu = new JPopupMenu();
		mEditMenuItem = new JMenuItem("Edit");
		mEditMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee selected = mItemList.getSelectedValue();

				removePanel(mainPanel);
				new EditAccountScreen(frame,selected);
				
				// TODO open edit window
				//mOrder.editItem(selected.getProduct(), q);
				updateAccounts();
			}
		});
		popupMenu.add(mEditMenuItem);
		popupMenu.add(new JPopupMenu.Separator());
		mRemoveMenuItem = new JMenuItem("Remove");
		mRemoveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "not implemented");
				/*int n = JOptionPane.showConfirmDialog(frame, "Are you sure?", "Remove item confirmation", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					Employee selected = mItemList.getSelectedValue();
					// TODO remove code
					//mOrder.removeItem(selected.getProduct());
					updateAccounts();
				}*/
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
		

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		mainPanel.add(buttonPanel);

		mAddButton = new JButton("Add");
		mAddButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mAddButton.getMinimumSize().height));
		mAddButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new AddNewAccount(frame);
			}
		});
		buttonPanel.add(mAddButton);

		mBackButton = new JButton("Cancel");
		mBackButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mBackButton.getMinimumSize().height));
		mBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				openUserMainMenu();
			}
		});
		buttonPanel.add(mBackButton);
	}

	private void updateAccounts(){
		DefaultListModel<Employee> itemListModel = new DefaultListModel<Employee>();
		for (Employee item : mController.getDataAccess().getEmployeeList()) {
			itemListModel.addElement(item);
		}
		mItemList.setModel(itemListModel);
	}
}
