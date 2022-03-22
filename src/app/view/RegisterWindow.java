package app.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import app.misc.SpringUtilities;
import app.model.CITISMap;
import app.model.User;

public class RegisterWindow extends JFrame {
	private static final long serialVersionUID = 1L; 
	
	private static final int numWords = 5;
	
	private CITISMap cm;
	
	public RegisterWindow(CITISMap cm) {
		super("Registrarse");
		InitGUI();
		this.cm = cm;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 300);
		this.setVisible(true);
	}

	private void InitGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mainPanel.add(new JLabel("Introduzca sus datos:"));
		JPanel p = new JPanel(new SpringLayout());
		
		JLabel nom = new JLabel("Nombre: ", JLabel.TRAILING);
		p.add(nom);
		JTextField nomT = new JTextField(10);
		nom.setLabelFor(nomT);
		p.add(nomT);
		
		JLabel ape = new JLabel("Apellidos: ", JLabel.TRAILING);
		p.add(ape);
		JTextField apeT = new JTextField(20);
		ape.setLabelFor(apeT);
		p.add(apeT);
		
		JLabel ema = new JLabel("E-mail: ", JLabel.TRAILING);
		p.add(ema);
		JTextField emaT = new JTextField(15);
		ema.setLabelFor(emaT);
		p.add(emaT);
		
		JLabel contra = new JLabel("Contrasena: ", JLabel.TRAILING);
		p.add(contra);
		JPasswordField contraT = new JPasswordField(15);
		contra.setLabelFor(contraT);
		p.add(contraT);
		
		JLabel rcontra = new JLabel("Repetir contrasena: ", JLabel.TRAILING);
		p.add(rcontra);
		JPasswordField rcontraT = new JPasswordField(15);
		rcontra.setLabelFor(rcontraT);
		p.add(rcontraT);
		JCheckBox jc = new JCheckBox();
		p.add(Box.createVerticalGlue());
		jc.addActionListener(new ActionListener() {
			
			private boolean ctrl = true;
			
			private char def = contraT.getEchoChar();
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(ctrl) {
					contraT.setEchoChar((char) 0);
					rcontraT.setEchoChar((char) 0);
					ctrl = false;
				}
				else {
					contraT.setEchoChar(def);
					rcontraT.setEchoChar(def);
					ctrl = true;
				}
			}
			
		});
		jc.setName("Mostrar contrasena");
		p.add(jc);
		
		SpringUtilities.makeCompactGrid(p, numWords, 2, 6, 6, 6, 6);
		
		mainPanel.add(p);
		
		JButton acept = new JButton("Aceptar");
		acept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!nomT.getText().equals("") && !apeT.getText().equals("") && !emaT.getText().equals("") && 
						!contraT.getPassword().equals("") && !rcontraT.getPassword().equals("")) {
					String str1 = "", str2 = "";
					for(char c : contraT.getPassword())
						str1 += c;
					for(char c : rcontraT.getPassword())
						str2 += c;
					if (str1.contentEquals(str2)) {
						ImageIcon icon = new ImageIcon("resources/check.jpg");
				        JOptionPane.showMessageDialog(null, "Los datos introducidos son correctos", 
				        		"Registrarse", JOptionPane.DEFAULT_OPTION, icon);
				        cm.addUser(new User(nomT.getText(), apeT.getText(),
				        		emaT.getText(), str1));
				        RegisterWindow.this.setVisible(false);
					}
					else {
						ImageIcon icon = new ImageIcon("resources/error.jpg");
						JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden", 
				        		"Registrarse", JOptionPane.DEFAULT_OPTION, icon);
					}
				}
				else {
					ImageIcon icon = new ImageIcon("resources/error.jpg");
					JOptionPane.showMessageDialog(null, "Faltan algunos datos requeridos", 
			        		"Registrarse", JOptionPane.DEFAULT_OPTION, icon);
				}
			}
			
		});
		
		mainPanel.add(acept);
		this.add(mainPanel);
	}
	
}
