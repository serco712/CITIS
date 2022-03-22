package app.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.model.CITISMap;

public class InitWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private CITISMap cm;
	
	public InitWindow (CITISMap cm) {
		super("CITIS");
		this.cm = cm;
		this.setLayout(new BorderLayout());
		InitGUI();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void InitGUI() {
		this.setSize(500, 400);
		CITISBackground c = new CITISBackground();
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

		InitSesionPanel jp = new InitSesionPanel();
		initPanel.add(jp);
		initPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JLabel t = new JLabel("No tienes cuenta aun? Registrate ahora");
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
	
	private Image loadImage(String img) {
		Image i = null;
		try {
			Image im =  ImageIO.read(new File("resources/" + img));
			return im;
		} catch (IOException e) {
		}
		return i;
	}
}
