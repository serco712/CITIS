package app.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CITISBackground extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void paint (Graphics g) {
		ImageIcon image = new ImageIcon("resources/CITIS.jpg");
		g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
		
		setOpaque(false);
		super.paint(g);
	}
}
