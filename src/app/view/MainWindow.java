package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel user = new JPanel();
		
		JLabel title = new JLabel("¡Bienvenido a CITIS!");
		title.setHorizontalAlignment(10);
		user.setLayout(new GridLayout(10,3,20,20));
		user.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		user.add(new JLabel("Usuario"));
		user.add(new JTextField());
		user.add(new JLabel("Contraseña"));
		user.add(new JPasswordField());
		
		mainPanel.add(title, BorderLayout.NORTH);
		mainPanel.add(user, BorderLayout.CENTER);
		
		this.getContentPane().add(mainPanel);
		
		this.setVisible(true);
	}
}
