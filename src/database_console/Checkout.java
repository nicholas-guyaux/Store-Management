package database_console;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Checkout extends JFrame implements ActionListener {
	Order order;
	
	JButton addButton,removeButton,finishAndPayButton,cancelButton;
	public Checkout(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = this.getContentPane();
	
		pane.setPreferredSize(new Dimension(500, 400));
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
		
		itemList.add(table.getTableHeader(), BorderLayout.PAGE_START);
		itemList.add(table, BorderLayout.CENTER);
		
		JPanel buttons = new JPanel();
		
		buttons.setLayout(new GridLayout(4,1));	

		addButton = new JButton("Add");
		addButton.addActionListener(this); 
		buttons.add(addButton);
		removeButton = new JButton("Remove");
		removeButton.addActionListener(this); 
		buttons.add(removeButton);
		finishAndPayButton = new JButton("Finish and Pay");
		finishAndPayButton.addActionListener(this); 
		buttons.add(finishAndPayButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this); 
		buttons.add(cancelButton);
			
		
		
		pane.add(itemList,BorderLayout.WEST);
		pane.add(buttons,BorderLayout.EAST);
		pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	void CreateOrder(){
		
	}
	void ReturnToMain(){
		
	}
	void UpdateCheckout(int OrderID){
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
