package app.view;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import app.control.Controller;

public class InitSesionPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);

	private Controller _ctrl;
	
	public InitSesionPanel(Controller ctrl) {
		_ctrl = ctrl;
		InitGUI();
	}

	private void InitGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.WHITE);
		JPanel data = new JPanel();
		data.setLayout(new GridLayout(4, 1));
		
		JPanel user_panel = new JPanel();
		user_panel.setBackground(Color.WHITE);
		user_panel.setLayout(new FlowLayout());
		JLabel user = new JLabel("Usuario");
		user_panel.add(user);
		data.add(user_panel);
		
		JPanel usert_panel = new JPanel();
		usert_panel.setBackground(Color.WHITE);
		usert_panel.setLayout(new FlowLayout());
		JTextField jt = new JTextField(15);
		jt.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		jt.setMaximumSize(new Dimension(120, 20));
		jt.setMinimumSize(new Dimension(120, 20));
		usert_panel.add(jt);
		data.add(usert_panel);
		
		JPanel pas_panel = new JPanel();
		pas_panel.setBackground(Color.WHITE);
		pas_panel.setLayout(new FlowLayout());
		JLabel password = new JLabel("Contraseña");
		
		pas_panel.add(password);
		data.add(pas_panel);
		
		JPanel past_panel = new JPanel();
		past_panel.setBackground(Color.WHITE);
		past_panel.setLayout(new FlowLayout());
		JPasswordField pf = new JPasswordField(15);
		pf.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		pf.setMaximumSize(new Dimension(120, 20));
		pf.setMinimumSize(new Dimension(120, 20));
		past_panel.add(pf);
		data.add(past_panel);
		
		data.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(data);		
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JPanel jp = new JPanel();
		jp.setBackground(Color.WHITE);
		jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
		JButton entrarInvitado = new JButton("Entrar como Invitado");
		entrarInvitado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO enter as a guest
				//Window jf = SwingUtilities.getWindowAncestor(InitSesionPanel.this);
				//jf.setVisible(false);
				//new MapWindow((Frame) jf, _ctrl);
			}
			
		});
		jp.add(entrarInvitado);
		jp.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton initSes = new JButton("Iniciar Sesión");
		initSes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO check data
				Window jf = SwingUtilities.getWindowAncestor(InitSesionPanel.this);
				jf.setVisible(false);
				new SearchWindow(_ctrl);
			}
			
		});
		jp.add(initSes);
		this.add(jp);
		this.setVisible(true);
	}
}
