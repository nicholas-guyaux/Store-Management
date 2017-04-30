package setup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import setup.location.LocationScreen;

public class MainScreen extends Screen {
	// View
	private JButton mNext;

	public MainScreen(JFrame frame) {
		super(frame);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 10));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		addPanel(mainPanel);

		JLabel title = new JLabel("Shop assistance system Setup");
		title.setFont(new Font("Serif", Font.BOLD, 22));
		title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height));
		mainPanel.add(title, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

		centerPanel.add(new JLabel("This is the setup wizard for Shop assistance system."));

		mainPanel.add(centerPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.TRAILING, 10, 10));
		mainPanel.add(panel, BorderLayout.SOUTH);

		mNext = new JButton("Next");
		mNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removePanel(mainPanel);
				new LocationScreen(frame, new Installation());
			}
		});
		panel.add(mNext);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
