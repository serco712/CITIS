package app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import app.control.Controller;
import app.model.CITISMap;

public class MapWindow extends JFrame {
	
	private Controller _ctrl;
	
	private Frame _parent;
	
	public MapWindow(Frame parent, Controller ctrl) {
		super("Mapa de Estaciones");
		_ctrl = ctrl;
		_parent = parent;
		InitGUI();
	}
	
	private void InitGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		JToolBar jtb = new JToolBar();
		JButton goAhead = new JButton("Tira pa trás");
		goAhead.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MapWindow.this.setVisible(false);
				_parent.setVisible(true);
			}
			
		});
		
		jtb.add(goAhead);
		mainPanel.add(jtb, BorderLayout.NORTH);
		JPanel np = new StationMap(_ctrl);
		mainPanel.add(np, BorderLayout.CENTER);
		this.setSize(new Dimension(500, 400));
		this.setVisible(true);
	}
}