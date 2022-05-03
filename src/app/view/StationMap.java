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
import app.model.business.CITISObject;
import app.model.business.TransportType;
import app.model.business.line.ASLine;
import app.model.business.station.ASStation;

public class StationMap extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private TransportType _tp;
	
	private Controller _ctrl;
	
	public StationMap (Controller ctrl, TransportType tp) {
		_ctrl = ctrl;
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
		
		g.setColor(Color.red);
		g.drawString("Esta funcion no esta disponible todavia. Mantente atento a nuestras redes sociales para ver futuras actualizaciones!", getWidth() / 2 - 50, getHeight() / 2);
	}
}
