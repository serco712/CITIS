package app.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainWindow extends JFrame {
	
	public MainWindow() {
		super ("CITIS");
		initGUI();
	}
	
	private void initGUI() {
		this.setSize(600, 600);
		JPanel mainPanel = new JPanel();
		JTextField jtf = new JTextField(20);
		JPasswordField jpf = new JPasswordField(20);
		
		mainPanel.add(jtf);
		mainPanel.add(jpf);
		
		this.add(mainPanel);
		
		
		this.setVisible(true);
	}
}
