package controller;

import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MainFrame;

public class MainScreen extends JPanel {
	
	public JButton CheckoutButton = new JButton("Checkout");
	
	public JButton InventoryButton = new JButton("Update Inventory");
	
	public MainScreen()
	{
		  
		  setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		  
		  CheckoutButton.setMaximumSize(new Dimension(800, 500));
		  CheckoutButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(CheckoutButton);

		  add(Box.createVerticalStrut(30));
		  
		  InventoryButton.setMaximumSize(new Dimension(800, 500));
		  InventoryButton.setFont(new Font("Arial", Font.BOLD, 90));
		  add(InventoryButton);
		  
		  
		}
	
}

