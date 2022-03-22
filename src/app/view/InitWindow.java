package app.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
		c.setLayout(new BorderLayout());
		JPanel initPanel = new JPanel();
		initPanel.setLayout(new BoxLayout(initPanel, BoxLayout.Y_AXIS));
		
		JPanel msg = new JPanel();
		JLabel u = new JLabel("Bienvenido a CITIS", JLabel.TRAILING);
		
		msg.add(u, SwingConstants.CENTER);
		msg.setVisible(true);
		initPanel.add(msg, BorderLayout.NORTH);
		
		InitSesionPanel jp = new InitSesionPanel();
		initPanel.add(jp, BorderLayout.CENTER);
		
		JPanel msgfi = new JPanel();
		msgfi.setLayout(new BoxLayout(msgfi, BoxLayout.Y_AXIS));
		JLabel t = new JLabel("No tienes cuenta aun? Registrate ahora", JLabel.TRAILING);
		JButton register = new JButton("Registrarse");
		register.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterWindow(cm);
			}
			
		});
		msgfi.add(t);
		msgfi.add(register);
		initPanel.add(msgfi);
		//initPanel.setMaximumSize(new Dimension(150, 150));
		//initPanel.setMinimumSize(new Dimension(150, 150));
		c.add(initPanel, BorderLayout.SOUTH);
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
