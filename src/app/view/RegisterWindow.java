package app.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import app.misc.SpringUtilities;

public class RegisterWindow extends JFrame {
	private static final long serialVersionUID = 1L; 
	
	private static final int numWords = 5;
	
	public RegisterWindow() {
		super("Registrarse");
		InitGUI();
		this.setSize(500, 200);
		this.setVisible(true);
	}

	private void InitGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
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
		JTextField contraT = new JPasswordField(15);
		contra.setLabelFor(contraT);
		p.add(contraT);
		
		JLabel rcontra = new JLabel("Repetir contrasena: ", JLabel.TRAILING);
		p.add(rcontra);
		JTextField rcontraT = new JPasswordField(15);
		rcontra.setLabelFor(rcontraT);
		p.add(rcontraT);
		
		SpringUtilities.makeCompactGrid(p, numWords, 2, 6, 6, 6, 6);
		
		mainPanel.add(p);
		
		JButton acept = new JButton("Aceptar");
		acept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!nomT.getText().equals("") && !apeT.getText().equals("") && !emaT.getText().equals("") && 
						!contraT.getText().equals("") && !rcontraT.getText().equals("")) {
					if (contraT.getText().equals(rcontraT.getText())) {
						ImageIcon icon = new ImageIcon("resources/check.jpg");
				        JOptionPane.showMessageDialog(null, "Los datos introducidos son correctos", 
				        		"Registrarse", JOptionPane.DEFAULT_OPTION, icon);
				        // TODO save data in data-base.
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
