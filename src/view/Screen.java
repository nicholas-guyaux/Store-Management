package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.Program;

public abstract class Screen {
	protected JFrame mMainFrame;
	protected static Program mController;

	public Screen(JFrame mainFrame) {
		mMainFrame = mainFrame;
	}

	protected void addPanel(JPanel panel) {
		mMainFrame.getContentPane().add(panel);
	}

	protected void removePanel(JPanel panel) {
		mMainFrame.getContentPane().remove(panel);
	}
	
	public static void setController(Program controller){
		mController = controller;
	}
	
	protected void openUserMainMenu(){
		if(mController.getDataAccess().getCurrentUser().isManager())
			new ManagerScreen(mMainFrame);
		else
			new CashierScreen(mMainFrame);
	}
}
