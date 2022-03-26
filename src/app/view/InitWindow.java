package app.view;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.control.Controller;
import app.model.CITISMap;

public class InitWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private CITISMap cm;
	
	private Controller _ctrl;
	
	public InitWindow (CITISMap cm, Controller ctrl) {
		super("CITIS");
		_ctrl = ctrl;
		try {
			_ctrl.loadData();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		this.cm = cm;
		this.setLayout(new BorderLayout());
		InitGUI();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(
						InitWindow.this, "¿Estás seguro de que quieres cerrar la aplicación?",
						"Confirmación de cierre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (option == JOptionPane.YES_OPTION) {
						try {
							_ctrl.saveData();
						}
						catch(Exception a) {
							System.out.println(a.getMessage());
						}
						
						System.exit(0);
					}		
			}
		});
		this.setVisible(true);
	}
	
	private void InitGUI() {
		this.setSize(500, 400);
		CITISBackground c = new CITISBackground("resources/CITIS.jpg");
		this.setContentPane(c);
		c.setLayout(new GridBagLayout());
		
		JPanel initPanel = new JPanel();
		initPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		initPanel.setLayout(new BoxLayout(initPanel, BoxLayout.Y_AXIS));
		initPanel.setSize(new Dimension(400, 300));
		
		JLabel u = new JLabel("Bienvenido a CITIS");
		u.setAlignmentX(CENTER_ALIGNMENT);
		initPanel.add(u);
		
		initPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		InitSesionPanel jp = new InitSesionPanel(_ctrl);
		initPanel.add(jp);
		initPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JLabel t = new JLabel("¿No tienes cuenta aún? Regístrate ahora");
		t.setAlignmentX(CENTER_ALIGNMENT);
		initPanel.add(t);
		
		JButton register = new JButton("Registrarse");
		register.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterWindow(cm);
			}
			
		});
		
		register.setAlignmentX(CENTER_ALIGNMENT);
		initPanel.add(t);
		initPanel.add(register);
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					c.add(initPanel);
				}
				else {
					c.add(new JLabel());
				}
			}
	}
}
