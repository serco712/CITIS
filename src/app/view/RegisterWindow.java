package app.view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import app.control.Controller;
import app.model.business.CITISMap;
import app.model.business.user.ASUser;
import app.model.business.user.DTOUser;

public class RegisterWindow extends JDialog {
	
	private static final long serialVersionUID = 1L; 
	
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);
	
	private int _status;
	
	private Controller _ctrl;
	
	public RegisterWindow(Controller ctrl) {
		super(new JFrame(), "Registrarse", true);
		_ctrl = ctrl;
		InitGUI();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 500);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void InitGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		mainPanel.setBackground(Color.WHITE);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JLabel welc = new JLabel("Introduzca sus datos:");
		welc.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(welc);
		
		JPanel p = new JPanel(new GridLayout(12, 1));
		p.setBackground(Color.WHITE);
		
		JPanel nom_panel = new JPanel();
		nom_panel.setBackground(Color.WHITE);
		nom_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel nom = new JLabel("Nombre: ", JLabel.TRAILING);
		nom_panel.add(nom);
		p.add(nom_panel);
		
		JPanel nomT_panel = new JPanel();
		nomT_panel.setBackground(Color.WHITE);
		nomT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextField nomT = new JTextField(40);
		nomT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		nom.setLabelFor(nomT);
		nomT_panel.add(nomT);
		p.add(nomT_panel);
		
		JPanel ape_panel = new JPanel();
		ape_panel.setBackground(Color.WHITE);
		ape_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ape = new JLabel("Apellidos: ", JLabel.TRAILING);
		ape_panel.add(ape);
		p.add(ape_panel);
		
		JPanel apeT_panel = new JPanel();
		apeT_panel.setBackground(Color.WHITE);
		apeT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextField apeT = new JTextField(40);
		apeT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		ape.setLabelFor(apeT);
		apeT_panel.add(apeT);
		p.add(apeT_panel);
		
		JPanel ema_panel = new JPanel();
		ema_panel.setBackground(Color.WHITE);
		ema_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ema = new JLabel("E-mail: ", JLabel.TRAILING);
		ema_panel.add(ema);
		p.add(ema_panel);
		
		JPanel emaT_panel = new JPanel();
		emaT_panel.setBackground(Color.WHITE);
		emaT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JTextField emaT = new JTextField(40);
		emaT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		ema.setLabelFor(emaT);
		emaT_panel.add(emaT);
		p.add(emaT_panel);
		
		JPanel contra_panel = new JPanel();
		contra_panel.setBackground(Color.WHITE);
		contra_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel contra = new JLabel("Contrasena: ", JLabel.TRAILING);
		contra_panel.add(contra);
		p.add(contra_panel);
		
		JPanel contraT_panel = new JPanel();
		contraT_panel.setBackground(Color.WHITE);
		contraT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPasswordField contraT = new JPasswordField(40);
		contraT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		contra.setLabelFor(contraT);
		contraT_panel.add(contraT);
		p.add(contraT_panel);
		
		JPanel rcontra_panel = new JPanel();
		rcontra_panel.setBackground(Color.WHITE);
		rcontra_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel rcontra = new JLabel("Repetir contrasena: ", JLabel.TRAILING);
		rcontra_panel.add(rcontra);
		p.add(rcontra_panel);
		
		JPanel rcontraT_panel = new JPanel();
		rcontraT_panel.setBackground(Color.WHITE);
		rcontraT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPasswordField rcontraT = new JPasswordField(40);
		rcontraT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		rcontra.setLabelFor(rcontraT);
		rcontraT_panel.add(rcontraT);
		p.add(rcontraT_panel);
		
		JPanel showContra = new JPanel();
		showContra.setBackground(Color.WHITE);
		showContra.setLayout(new FlowLayout(FlowLayout.LEFT));
		JCheckBox jc = new JCheckBox();
		jc.setBackground(Color.WHITE);
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
		acceptTerms.setBackground(Color.WHITE);
		acceptTerms.setLayout(new FlowLayout(FlowLayout.LEFT));
		JCheckBox cb = new JCheckBox();
		cb.setBackground(Color.WHITE);
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
		formatButton(acept);
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
						String[] data = {emaT.getText()};
						if(!_ctrl.checkData(2, data)) {
							DTOUser du = new DTOUser();
							du.setName(nomT.getText());
							du.setSurname(apeT.getText());
							du.setEmail(emaT.getText());
							du.setPassword(str1);
							_ctrl.addData(1, du);
							ImageIcon icon = new ImageIcon("resources/check.jpg");
					        JOptionPane.showMessageDialog(null, "Los datos introducidos son validos", 
					        		"Registrarse", JOptionPane.DEFAULT_OPTION, icon);
					        RegisterWindow.this.setVisible(false);
						}
						else {
							ImageIcon icon = new ImageIcon("resources/error.png");
							JOptionPane.showMessageDialog(null, "El email ya esta utilizado", 
					        		"Registrarse", JOptionPane.DEFAULT_OPTION, icon);
						}
					}
					else {
						ImageIcon icon = new ImageIcon("resources/error.png");
						JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden", 
				        		"Registrarse", JOptionPane.DEFAULT_OPTION, icon);
					}
				}
				else {
					ImageIcon icon = new ImageIcon("resources/error.png");
					JOptionPane.showMessageDialog(null, "Faltan algunos datos requeridos", 
			        		"Registrarse", JOptionPane.DEFAULT_OPTION, icon);
				}
			}
			
		});
		
		JButton cancel = new JButton("Cancelar");
		formatButton(cancel);
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonsPanel.setBackground(Color.WHITE);
		buttonsPanel.add(acept);
		buttonsPanel.add(Box.createRigidArea(new Dimension(7, 0)));
		buttonsPanel.add(cancel);
		
		mainPanel.add(buttonsPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
				
		this.add(mainPanel);
	}
	
	private void formatButton(JButton b) {
		b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		b.setBackground(Color.WHITE);
		b.setPreferredSize(new Dimension(65, 25));		
		
		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.WHITE));
			}
			public void mouseExited(MouseEvent e) {
				b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
			}
		});	
	}	
}
