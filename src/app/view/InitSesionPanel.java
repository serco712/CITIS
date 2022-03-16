package app.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InitSesionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public InitSesionPanel() {
		InitGUI();
	}

	private void InitGUI() {
		JTextField user = new JTextField();
		user.setMaximumSize(new Dimension(120, 20));
		user.setMinimumSize(new Dimension(120, 20));
		JTextField password = new JPasswordField();
		password.setMaximumSize(new Dimension(120, 20));
		password.setMinimumSize(new Dimension(120, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel("Usuario"));
		this.add(user);
		this.add(new JLabel("Contrasena"));
		this.add(password);
		JPanel confirm = new JPanel();
		confirm.setLayout(new BoxLayout(confirm, BoxLayout.X_AXIS));
		JButton entrarInvitado = new JButton("Entrar como Invitado");
		entrarInvitado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO enter as a guest
			}
			
		});
		confirm.add(entrarInvitado, BoxLayout.X_AXIS);
		confirm.add(new JLabel("         "), BoxLayout.X_AXIS);
		JButton initSes = new JButton("Iniciar Sesion");
		initSes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO check data
				
			}
			
		});
		
		confirm.add(initSes, BoxLayout.X_AXIS);
		confirm.setVisible(true);
		this.add(confirm);
		this.setVisible(true);
	}
	
	
}
