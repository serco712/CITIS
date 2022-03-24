package app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import app.model.CITISMap;
import app.model.Line;
import app.model.Station;

public class StationMap extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final long SEED = 100;
	
	private static final int _JRADIUS = 10;
	private static final int _JRADIUS_MIN = 5;
	
	private static final Color _BG_COLOR = Color.WHITE;
	private static final Color STATIONS_BORDER = Color.BLACK;
	private static final Color STATIONS_CENTRE = Color.WHITE;
	private static final Color STATIONS_NAME = Color.RED;
	
	private CITISMap _map;

	public StationMap (CITISMap map) {
		_map = map;
		initGUI();
	}
	
	private void initGUI() {
		setPreferredSize(new Dimension(300, 200));
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.setColor(_BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());

		drawMap(g);
	}
	
	private void drawMap(Graphics g) {
		
		for(Station s : _map.getStations()) {
			
			g.setColor(STATIONS_BORDER);
			g.fillOval(s.getX() - _JRADIUS / 2, s.getY() - _JRADIUS / 2, _JRADIUS, _JRADIUS);
			
			g.setColor(STATIONS_CENTRE);
			g.fillOval(s.getX() - _JRADIUS_MIN / 2, s.getY() - _JRADIUS_MIN / 2, _JRADIUS_MIN, _JRADIUS_MIN);
			
			g.setColor(STATIONS_NAME);
			g.drawString(s.getName(), s.getX() - 5, s.getY() - 10);
		}
		
		for(Line l : _map.getLines()) {
			List<Station> ls = l.getStations();
			g.setColor(l.getColor());
			for(int i = 0; i < l.getNumStops() - 1; i++) {
				int x1 = ls.get(i).getX(); 
				int x2 = ls.get(i + 1).getX();
				int y1 = ls.get(i).getY(); 
				int y2 = ls.get(i + 1).getY();
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}
	
	/*
	private void drawStation(Graphics g, List<Vehicle> v, int x1, int x2, int y) {
		for (Vehicle ve : v) {
			if (ve.getStatus() != VehicleStatus.ARRIVED) {
				
				float x = (ve.getLocation()*(x2 - x1)) / ve.getRoad().getLength();

				// Choose a color for the vehcile's label and background, depending on its
				// contamination class
				int vLabelColor = (int) (25.0 * (10.0 - (double) ve.getContClass()));
				g.setColor(new Color(0, vLabelColor, 0));

				// draw an image of a car (with circle as background) and it identifier
				g.drawImage(_car, (int) (x1 + x), y - 6, 12, 12, this);
				g.drawString(ve.getId(), (int) (x1 + x), y - 6);
			}
		}
	}
	
	private void drawImage(Graphics g, Road r, int x, int y) {
		g.drawImage(r.getWeather().getImage(), x + 20, y - 18, 32, 32, this);
		int C = (int) Math.floor(Math.min((double) r.getTotalCO2()/(1.0 + (double) r.getContLimit()),1.0) /
			0.19);
		Image i = img[C];
		g.drawImage(i, x + 60, y - 18, 32, 32, this);
	}
	
	// loads an image from a file
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}
	*/
}
