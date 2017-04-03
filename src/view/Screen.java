package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class Screen {
	protected JFrame mMainFrame;

	public Screen(JFrame mainFrame) {
		mMainFrame = mainFrame;
	}

	protected void addPanel(JPanel panel) {
		mMainFrame.getContentPane().add(panel);
	}

	protected void removePanel(JPanel panel) {
		mMainFrame.getContentPane().remove(panel);
	}
	
	protected void openUserMainMenu(){
		try {
			if(Program.getInstance().getDataAccess().getCurrentUser().isManager())
				new ManagerScreen(mMainFrame);
			else
				new CashierScreen(mMainFrame);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
