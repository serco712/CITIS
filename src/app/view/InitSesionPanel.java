package app.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel user = new JLabel("Usuario");
		user.setAlignmentX(RIGHT_ALIGNMENT);
		this.add(user);
		JTextField jt = new JTextField(15);
		jt.setMaximumSize(new Dimension(120, 20));
		jt.setMinimumSize(new Dimension(120, 20));
		this.add(jt);
		JLabel password = new JLabel("Contrasena");
		password.setAlignmentX(RIGHT_ALIGNMENT);
		this.add(password);
		JPasswordField pf = new JPasswordField(15);
		pf.setMaximumSize(new Dimension(120, 20));
		pf.setMinimumSize(new Dimension(120, 20));
		this.add(pf);
		
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
		JButton entrarInvitado = new JButton("Entrar como Invitado");
		entrarInvitado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO enter as a guest
			}
			
		});
		jp.add(entrarInvitado);
		jp.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton initSes = new JButton("Iniciar Sesion");
		initSes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO check data
			}
			
		});
		jp.add(initSes);
		this.add(jp);
		this.setVisible(true);
	}

	/*
	private void InitGUI() {
		JTextField user = new JTextField(10);
		user.setMaximumSize(new Dimension(120, 20));
		user.setMinimumSize(new Dimension(120, 20));
		JTextField password = new JPasswordField(20);
		password.setMaximumSize(new Dimension(120, 20));
		password.setMinimumSize(new Dimension(120, 20));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel("Usuario", JLabel.TRAILING));
		this.add(user);
		this.add(new JLabel("Contrasena", JLabel.TRAILING));
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
		confirm.add(Box.createHorizontalGlue());
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
	*/
}
