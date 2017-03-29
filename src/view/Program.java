package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Program {
	public static void main(String[] args) throws ClassNotFoundException {
		new LoginScreen(Program.getInstance().getMainFrame());
	}

	private static Program mInstance;

	private JFrame mMainFrame;

	//private IDataAccess mDataAccess;

	private Program() throws ClassNotFoundException {
		//mDataAccess = new SQLiteDataAccess();

		mMainFrame = new JFrame("Mr Smith's shop");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mMainFrame.setLocation(dim.width / 2 - 300 / 2, dim.height / 2 - 600 / 2);
		mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static Program getInstance() throws ClassNotFoundException {
		if (mInstance == null)
			mInstance = new Program();
		return mInstance;
	}

	public JFrame getMainFrame() {
		return mMainFrame;
	}

	//public IDataAccess getDataAccess() {
	//	return mDataAccess;
	//}
}
