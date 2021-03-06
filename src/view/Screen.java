package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Program;

public abstract class Screen {
	protected JFrame mMainFrame;

	public Screen(JFrame mainFrame) {
		mMainFrame = mainFrame;
	}
	
	/** Adds panel to the MainFrame
	 * @param panel The panel to be added
	 */
	protected void addPanel(JPanel panel) {
		mMainFrame.getContentPane().add(panel);
	}

	/** Removes panel to the MainFrame
	 * @param panel The panel to be removed
	 */
	protected void removePanel(JPanel panel) {
		mMainFrame.getContentPane().remove(panel);
	}
	
	/** opens up either the Cashier main screen or the Manager main screen depending on which is logged in 	 */
	protected void openUserMainMenu(){
		if(Program.getInstance().getDataAccess().getCurrentUser() == null){
			return;
		}
		if(Program.getInstance().getDataAccess().getCurrentUser().isManager())
			new ManagerScreen(mMainFrame);
		else
			new CashierScreen(mMainFrame);
	}
}
