package app.view;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import app.control.Controller;
import app.model.business.CITISMap;
import app.model.business.CITISObject;
import app.model.business.CITISObserver;
import app.model.business.TransportType;
import app.model.business.line.ASLine;
import app.model.business.station.ASStation;

public class StationMap extends JPanel implements CITISObserver {

	private static final long serialVersionUID = 1L;
	
	private static final int _JRADIUS = 10;
	private static final int _JRADIUS_MIN = 5;

	private static final Color STATIONS_BORDER = Color.BLACK;
	private static final Color STATIONS_CENTRE = Color.WHITE;
	private static final Color STATIONS_NAME = Color.RED;
	
	private CITISMap _map;
	
	private TransportType _tp;
	
	public StationMap (Controller ctrl, TransportType tp) {
		ctrl.addObserver(this);
		_tp = tp;
		initGUI();
	}
	
	private void initGUI() {
		setPreferredSize(new Dimension(300, 200));
		this.setBorder(new TitledBorder("Mapa de " + _tp.getTranslation()));
	}
	
	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawMap(g);
	}
	
	private void drawMap(Graphics g) {
		
		for(ASStation s : _map.getStations()) {
			if(s.getTransport().equals(_tp)) {
				g.setColor(STATIONS_BORDER);
				g.fillOval(s.getX() - _JRADIUS / 2, s.getY() - _JRADIUS / 2, _JRADIUS, _JRADIUS);
				
				g.setColor(STATIONS_CENTRE);
				g.fillOval(s.getX() - _JRADIUS_MIN / 2, s.getY() - _JRADIUS_MIN / 2, _JRADIUS_MIN, _JRADIUS_MIN);
				
				g.setColor(STATIONS_NAME);
				g.drawString(s.getName(), s.getX() - 5, s.getY() - 10);
			}
		}
		
		for(ASLine l : _map.getLines()) {
			if(l.getTransport().equals(_tp)) {
				List<String> ls = l.getStations();
				g.setColor(l.getColorLine());
				for(int i = 0; i < l.getNumStops() - 1; i++) {
					int x1 = _map.searchStation(ls.get(i)).getX(); 
					int x2 = _map.searchStation(ls.get(i + 1)).getX();
					int y1 = _map.searchStation(ls.get(i)).getY(); 
					int y2 = _map.searchStation(ls.get(i + 1)).getY();
					g.drawLine(x1, y1, x2, y2);
				}
			}
		}
	}
	
	@Override
	public void paint (Graphics g) {
		ImageIcon image = new ImageIcon("resources/map.jpg");
		g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
		
		setOpaque(false);
		super.paint(g);
	}

	@Override
	public void registerObserver(CITISMap cm) {
		_map = cm;
	}

	@Override
	public void CITISObjectAdded(CITISMap cm, CITISObject co) {
		_map = cm;
	}

	@Override
	public void CITISObjectModified(CITISMap cm, CITISObject co) {
		_map = cm;
	}

	@Override
	public void CITISObjectDeleted(CITISMap cm, CITISObject co) {
		_map = cm;
	}
}
