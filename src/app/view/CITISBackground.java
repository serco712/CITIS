package app.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CITISBackground extends JPanel {
	
	public CITISBackground(){
		
	}
	
	@Override
	public void paint (Graphics g) {
		ImageIcon image = new ImageIcon("resources/ari.jpg");
		g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
		
		setOpaque(false);
		super.paint(g);
	}
}
