package app.view;

import java.awt.BorderLayout;
import java.awt.Component;
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

public class InitWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public InitWindow () {
		super("CITIS");
		InitGUI();
	}
	
	private void InitGUI() {
		this.setSize(500, 400);
		JPanel initPanel = new JPanel(new BorderLayout());
		
		JPanel msg = new JPanel();
		JLabel u = new JLabel("Bienvenido a CITIS");
		
		msg.add(u, SwingConstants.CENTER);
		msg.setVisible(true);
		initPanel.add(msg, BorderLayout.NORTH);
		
		InitSesionPanel jp = new InitSesionPanel();
		initPanel.add(jp, BorderLayout.CENTER);
		
		JPanel msgfi = new JPanel();
		msgfi.setLayout(new BoxLayout(msgfi, BoxLayout.Y_AXIS));
		JLabel t = new JLabel("No tienes cuenta aun? Registrate ahora");
		JButton register = new JButton("Registrarse");
		register.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new RegisterWindow();
			}
			
		});
		msgfi.add(t);
		msgfi.add(register);
		msgfi.setVisible(true);
		initPanel.add(msgfi, BorderLayout.SOUTH);
		
		this.add(initPanel);
		this.setVisible(true);
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
