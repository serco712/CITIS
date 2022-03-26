package app.view;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ImageIcon _img;
	
	public ImagePanel(String img) {
		_img = new ImageIcon(img);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(_img.getImage(), 10, 10, this.getWidth() - 20, this.getHeight() - 20, this);
		
		this.setOpaque(false);
		super.paint(g);
	}

}
