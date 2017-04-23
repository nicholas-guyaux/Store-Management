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

public class EditAccountsScreen extends Screen {
	
	private JButton mAddButton;
	private JButton mBackButton;

	private JMenuItem mEditMenuItem;
	private JMenuItem mRemoveMenuItem;

	private JList<Employee> mItemList;
	
	private JPanel mainPanel = new JPanel();
	
	public EditAccountsScreen(JFrame frame) {
		super(frame);

		createView();

		updateAccounts();
		
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

		JLabel title = new JLabel("Edit Employees");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		listPanel.add(title);

		JPopupMenu popupMenu = new JPopupMenu();
		mEditMenuItem = new JMenuItem("Edit");
		mEditMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditSelectedAccount();
			}
		});
		popupMenu.add(mEditMenuItem);
		popupMenu.add(new JPopupMenu.Separator());
		mRemoveMenuItem = new JMenuItem("Remove");
		mRemoveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeSelectedAccount();
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
				AddAccount();
			}
		});
		buttonPanel.add(mAddButton);

		mBackButton = new JButton("Cancel");
		mBackButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, mBackButton.getMinimumSize().height));
		mBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnToMainScreen();
			}
		});
		buttonPanel.add(mBackButton);
	}

	/** updates view for changes */
	private void updateAccounts(){
		DefaultListModel<Employee> itemListModel = new DefaultListModel<Employee>();
		for (Employee item : mController.getDataAccess().getEmployeeList()) {
			itemListModel.addElement(item);
		}
		mItemList.setModel(itemListModel);
	}
	
	/** opens the edit account screen for the selected account */
	private void EditSelectedAccount() {
		Employee selected = mItemList.getSelectedValue();

		removePanel(mainPanel);
		new EditAccountScreen(mMainFrame,selected);
	}

	/** removes the current selected account */
	private void removeSelectedAccount() {
		Employee selected = mItemList.getSelectedValue();
		if(selected.getId() == mController.getDataAccess().getCurrentUser().getId()){
			JOptionPane.showMessageDialog(mMainFrame, "Cannot remove your own account");
			return;
		}
		int n = JOptionPane.showConfirmDialog(mMainFrame, "remove " + selected.getUsername() +" from employees", "Remove Employee confirmation", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			mController.getDataAccess().removeEmployeeById(selected.getId());
			updateAccounts();
		}
	}
	
	/** opens the add new account screen */
	private void AddAccount() {
		removePanel(mainPanel);
		new AddNewAccount(mMainFrame);
	}

	/** returns to the main screen */
	private void returnToMainScreen() {
		removePanel(mainPanel);
		openUserMainMenu();
	}
}
