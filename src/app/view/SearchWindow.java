package app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SearchWindow extends JFrame {
	
	public SearchWindow() {
		super("CITIS");
		initGUI();
	}
	
	private void initGUI() {
		this.setMinimumSize(new Dimension(600, 400));
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		mainPanel.add(new SearchControlPanel(), BorderLayout.PAGE_START);
		
		JPanel secPanel = new JPanel();
		secPanel.setLayout(new GridLayout(2, 1));
		mainPanel.add(secPanel, BorderLayout.CENTER);
		
		JPanel upPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					g.drawImage(ImageIO.read(new File("resources/check.jpg")), 100, 100, 60, 60, secPanel);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		upPanel.setLayout(new GridLayout(1, 2));
		upPanel.setPreferredSize(new Dimension(1000, 400));
		
		upPanel.add(new SearchPanel());
		upPanel.repaint();
		secPanel.add(upPanel);

		JPanel downPanel = new JPanel();
		secPanel.add(downPanel);
		
		
		this.setVisible(true);
	}
}
