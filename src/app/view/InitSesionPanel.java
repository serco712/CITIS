package app.view;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import app.control.Controller;
import app.model.business.user.DTOUser;

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
		JLabel password = new JLabel("Contrasena");
		
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
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel jp = new JPanel();
	
		jp.setBackground(Color.WHITE);
		jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
		
		JButton entrarInvitado = new JButton("Entrar como Invitado");
		entrarInvitado.setPreferredSize(new Dimension(135, 25));
		formatButton(entrarInvitado);
		entrarInvitado.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DTOUser dto = new DTOUser();
				dto.setRole(2);
				_ctrl.registerUser(dto);
				Window jf = SwingUtilities.getWindowAncestor(InitSesionPanel.this);
				jf.setVisible(false);
				new SearchWindow(_ctrl);
			}
			
		});
		
		JButton initSes = new JButton("Iniciar Sesion");
		initSes.setPreferredSize(new Dimension(95, 25));
		formatButton(initSes);
		initSes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String password = "";
				for(char c : pf.getPassword())
					password += c;
				if(!jt.getText().isEmpty() && !password.contentEquals("")) {
					String [] data = {jt.getText(), password};
					if(_ctrl.checkData(1, data)) {
						_ctrl.registerUser((DTOUser) _ctrl.findData(1, jt.getText()));
						Window jf = SwingUtilities.getWindowAncestor(InitSesionPanel.this);
						jf.setVisible(false);
						new SearchWindow(_ctrl);
					}
					else { // El usuario no existe
						ImageIcon icon = new ImageIcon("resources/error.png");
						JOptionPane.showMessageDialog(null, "El usuario introducido no esta registrado", "Iniciar sesion",
								JOptionPane.DEFAULT_OPTION, icon);
					}
				}
				else { //Si esta vacio
					ImageIcon icon = new ImageIcon("resources/error.png");
					JOptionPane.showMessageDialog(null, "Faltan algunos datos requeridos", "Iniciar sesion",
							JOptionPane.DEFAULT_OPTION, icon);
				}
				//TODO falta si se introduce un usuario, pero es incorrecto
				
			}
			
		});
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonsPanel.setBackground(Color.WHITE);
	
		buttonsPanel.add(entrarInvitado);
		
		buttonsPanel.add(Box.createRigidArea(new Dimension(7, 0)));
		
		buttonsPanel.add(initSes);
		
		this.add(buttonsPanel);
		
		this.add(jp);
		this.setVisible(true);
	}
	
	private void formatButton(JButton b) {
		b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		b.setBackground(Color.WHITE);
				
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
