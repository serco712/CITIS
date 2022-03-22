package app.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

import app.model.CITISMap;
import app.model.User;

public class RegisterWindow extends JFrame {
	
	private static final long serialVersionUID = 1L; 
	
	private CITISMap cm;
	
	private int _status;
	
	public RegisterWindow(CITISMap cm) {
		super("Registrarse");
		InitGUI();
		this.cm = cm;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setVisible(true);
	}

	private void InitGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JLabel welc = new JLabel("Introduzca sus datos:");
		welc.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(welc);
		
		JPanel p = new JPanel(new GridLayout(13, 1));
		
		JPanel nom_panel = new JPanel();
		nom_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel nom = new JLabel("Nombre: ", JLabel.TRAILING);
		nom_panel.add(nom);
		p.add(nom_panel);
		
		JPanel nomT_panel = new JPanel();
		nomT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextField nomT = new JTextField(10);
		nom.setLabelFor(nomT);
		nomT_panel.add(nomT);
		p.add(nomT_panel);
		
		JPanel ape_panel = new JPanel();
		ape_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ape = new JLabel("Apellidos: ", JLabel.TRAILING);
		ape_panel.add(ape);
		p.add(ape_panel);
		
		JPanel apeT_panel = new JPanel();
		apeT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextField apeT = new JTextField(20);
		ape.setLabelFor(apeT);
		apeT_panel.add(apeT);
		p.add(apeT_panel);
		
		JPanel ema_panel = new JPanel();
		ema_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ema = new JLabel("E-mail: ", JLabel.TRAILING);
		ema_panel.add(ema);
		p.add(ema_panel);
		
		JPanel emaT_panel = new JPanel();
		emaT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextField emaT = new JTextField(15);
		ema.setLabelFor(emaT);
		emaT_panel.add(emaT);
		p.add(emaT_panel);
		
		JPanel contra_panel = new JPanel();
		contra_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel contra = new JLabel("Contrasena: ", JLabel.TRAILING);
		contra_panel.add(contra);
		p.add(contra_panel);
		
		JPanel contraT_panel = new JPanel();
		contraT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPasswordField contraT = new JPasswordField(15);
		contra.setLabelFor(contraT);
		contraT_panel.add(contraT);
		p.add(contraT_panel);
		
		JPanel rcontra_panel = new JPanel();
		rcontra_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel rcontra = new JLabel("Repetir contrasena: ", JLabel.TRAILING);
		rcontra_panel.add(rcontra);
		p.add(rcontra_panel);
		
		JPanel rcontraT_panel = new JPanel();
		rcontraT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPasswordField rcontraT = new JPasswordField(15);
		rcontra.setLabelFor(rcontraT);
		rcontraT_panel.add(rcontraT);
		p.add(rcontraT_panel);
		
		JPanel showContra = new JPanel();
		showContra.setLayout(new FlowLayout(FlowLayout.LEFT));
		JCheckBox jc = new JCheckBox();
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
		
		showContra.add(jc);
		showContra.add(Box.createRigidArea(new Dimension(5, 0)));
		showContra.add(new JLabel("Mostrar Contrasena"));
		p.add(showContra);
		
		JPanel acceptTerms = new JPanel();
		acceptTerms.setLayout(new FlowLayout(FlowLayout.LEFT));
		JCheckBox cb = new JCheckBox();
		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				_status = (_status + 1) % 2;
			}
			
		});
		
		acceptTerms.add(cb);
		acceptTerms.add(Box.createRigidArea(new Dimension(5, 0)));
		acceptTerms.add(new JLabel("Acepto la politica de privacidad"));
		p.add(acceptTerms);
		
		mainPanel.add(p);
		
		JButton acept = new JButton("Aceptar");
		acept.setAlignmentX(CENTER_ALIGNMENT);
		acept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!nomT.getText().equals("") && !apeT.getText().equals("") && !emaT.getText().equals("") && 
						!contraT.getPassword().equals("") && !rcontraT.getPassword().equals("") &&
						_status == 1) {
					String str1 = "", str2 = "";
					for(char c : contraT.getPassword())
						str1 += c;
					for(char c : rcontraT.getPassword())
						str2 += c;
					if (str1.contentEquals(str2)) {
						cm.addUser(new User(nomT.getText(), apeT.getText(),
				        		emaT.getText(), str1));
						ImageIcon icon = new ImageIcon("resources/check.jpg");
				        JOptionPane.showMessageDialog(null, "Los datos introducidos son correctos", 
				        		"Registrarse", JOptionPane.DEFAULT_OPTION, icon);
				        RegisterWindow.this.setVisible(false);
					}
					else {
						ImageIcon icon = new ImageIcon("reerror.jpg");
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
