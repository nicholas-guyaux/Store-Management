package database_console;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class InventoryManagement extends JFrame implements ActionListener {

	Product products[];
	
	JButton addButton, CancelButton;
	public InventoryManagement(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = this.getContentPane();
	
		pane.setPreferredSize(new Dimension(500, 200));
		pane.setLayout(new BorderLayout());
	
		
		JPanel screen = new JPanel();
		
		screen.setLayout(new GridLayout());
		
		
		JPanel itemList = new JPanel();
		
		itemList.setLayout(new BorderLayout());
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		JTable table = new JTable(data, columnNames);
		
		JPanel buttons = new JPanel();
		
		buttons.setLayout(new FlowLayout());
		
		addButton = new JButton("Add");
		addButton.addActionListener(this); 
		buttons.add(addButton);
		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(this); 
		buttons.add(CancelButton);
		
		itemList.add(table.getTableHeader(), BorderLayout.PAGE_START);
		itemList.add(table, BorderLayout.CENTER);
		itemList.add(buttons,BorderLayout.PAGE_END);
		
		
		screen.add(itemList);
		pane.add(screen);	
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	void AddItem(){
		
	}
	
	void UpdateScreen(){
		
	}
	void UpdateItem(){
		
	}
	void DEleteItem(){
		
	}
	void UpdateDatabase(int productID){
		
	}
	void ReturnToMain(){
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
