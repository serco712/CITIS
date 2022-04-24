package app.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import app.model.business.station.ASStation;

public class StationWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private ASStation _st;
	
	public StationWindow (ASStation st) {
		super(new JFrame(), "Estacion " + st.getName(), true);
		_st = st;
		initGUI();
	}

	private void initGUI() {
		this.setMinimumSize(new Dimension(500, 300));
		JPanel jp = new JPanel();
		jp.setBackground(Color.cyan);
		this.setContentPane(jp);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
}
