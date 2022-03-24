package app.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import app.model.CITISMap;

public class MapWindow extends JFrame {
	
	private CITISMap _map;
	
	public MapWindow(CITISMap map) {
		super("Mapa de Estaciones");
		_map = map;
		InitGUI();
	}
	
	private void InitGUI() {
		this.setContentPane(new StationMap(_map));
		this.setSize(new Dimension(500, 400));
		this.setVisible(true);
	}
}
