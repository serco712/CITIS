package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import app.control.Controller;
import app.model.CITISMap;

public class InitWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private CITISMap cm;
	
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);
	
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
					else 
						dispose();
			}
		});
		
		this.setVisible(true);
	}
	
	private void InitGUI() {
		this.setSize(500, 400);
		CITISBackground c = new CITISBackground("resources/fondoApp.png");
		this.setContentPane(c);
		c.setLayout(new GridBagLayout());
		
		JPanel initPanel = new JPanel();
		
		initPanel.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		initPanel.setBackground(Color.WHITE);
		initPanel.setLayout(new BoxLayout(initPanel, BoxLayout.Y_AXIS));
		initPanel.setSize(new Dimension(400, 300));
		
		initPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JLabel u = new JLabel("Bienvenido a CITIS");
		u.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel icon = new JLabel(new ImageIcon(new ImageIcon("resources/logoCITIS.png").getImage().getScaledInstance(84, 50, Image.SCALE_DEFAULT)));
		initPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		icon.setAlignmentX(CENTER_ALIGNMENT);
		initPanel.add(icon);
				
		initPanel.add(u);
		
		initPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		InitSesionPanel jp = new InitSesionPanel(_ctrl);
		initPanel.add(jp);
		initPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JLabel t = new JLabel("¿No tienes cuenta aún? Regístrate ahora");
		t.setAlignmentX(CENTER_ALIGNMENT);
		initPanel.add(t);
		
		JButton register = new JButton("Registrarse");
		register.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		register.setBackground(Color.WHITE);
		
		register.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterWindow(cm);
			}
		});
		
		register.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				register.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.WHITE));
			}
			public void mouseExited(MouseEvent e) {
				register.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
			}
		});
				
		register.setAlignmentX(CENTER_ALIGNMENT);
		initPanel.add(t);
		initPanel.add(register);
		
		initPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
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
