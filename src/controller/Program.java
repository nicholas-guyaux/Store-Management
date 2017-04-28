package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.IDataAccess;
//import model.MockDataAccess;
import model.Product;
import model.SQLiteJDBC;
import view.LoginScreen;


public class Program {
	public static void main(String[] args) throws ClassNotFoundException {
		new LoginScreen(Program.init().getMainFrame());
	}

	private static Program mInstance;

	private JFrame mMainFrame;

	private IDataAccess mDataAccess;

	private Program() throws ClassNotFoundException {
		mDataAccess = new SQLiteJDBC();

		mMainFrame = new JFrame("Mr Smith's shop");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mMainFrame.setLocation(dim.width / 2 - 300 / 2, dim.height / 2 - 600 / 2);
		mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static Program init() throws ClassNotFoundException {
		if (mInstance == null)
			mInstance = new Program();
		return mInstance;
	}

	public static Program getInstance(){
		return mInstance;
	}

	public JFrame getMainFrame() {
		return mMainFrame;
	}

	public IDataAccess getDataAccess() {
		return mDataAccess;
	}
		
	
}
