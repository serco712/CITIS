package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import app.control.Controller;
import app.model.business.TransportType;

public class MapWindow extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private Controller _ctrl;
	
	
	private TransportType _tp;
	
	public MapWindow(Controller ctrl, TransportType tp) {
		super(new JFrame(), "Mapa de Estaciones", true);
		_ctrl = ctrl;
		_tp = tp;
		InitGUI();
	}
	
	private void InitGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		JToolBar jtb = new JToolBar();
		jtb.setBorder(null);
		jtb.setBackground(Color.WHITE);
		JButton goAhead = new JButton();
		goAhead.setPreferredSize(new Dimension(30,32));
		goAhead.setToolTipText("Retroceder");
		goAhead.setIcon(new ImageIcon("resources/back.png")); 
	
		goAhead.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}			
		});
		
		jtb.add(goAhead);
		mainPanel.add(jtb, BorderLayout.NORTH);
		JPanel np = new StationMap(_ctrl, _tp);
		mainPanel.add(np, BorderLayout.CENTER);
		this.setSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
