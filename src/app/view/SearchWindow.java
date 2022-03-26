package app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import app.control.Controller;
import app.model.CITISMap;
import app.model.CITISObject;
import app.model.CITISObserver;
import app.model.TransportType;

@SuppressWarnings("serial")
public class SearchWindow extends JFrame implements CITISObserver {
	
	private CITISMap _cm;
	
	public SearchWindow(Controller ctrl) {
		super("CITIS");
		ctrl.addObserver(this);
		initGUI();
	}
	
	private void initGUI() {
		this.setMinimumSize(new Dimension(600, 400));
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		//mainPanel.add(new SearchControlPanel(), BorderLayout.PAGE_START);
		
		MiMenuListener menul = new MiMenuListener();
		JMenuBar jmb = new JMenuBar();
		
		JMenu check_table = new JMenu("Consultar");
		JMenuItem station_table = new JMenuItem("Listado de estaciones");
		JMenuItem transport_table = new JMenuItem("Listado de transportes");
		JMenuItem line_table = new JMenuItem("Listado de líneas");
		
		check_table.add(station_table);
		check_table.add(transport_table);
		check_table.add(line_table);
		jmb.add(check_table);
		
		station_table.addActionListener(new TableListener());
		transport_table.addActionListener(new TableListener());
		line_table.addActionListener(new TableListener());
		
		JMenu add_obj = new JMenu("Añadir");
		JMenuItem add_station = new JMenuItem("Estación");
		JMenuItem add_transport = new JMenuItem("Transporte");
		JMenuItem add_line = new JMenuItem("Línea");
		
		add_obj.add(add_station);
		add_obj.add(add_transport);
		add_obj.add(add_line);
		jmb.add(add_obj);
		
		add_station.addActionListener(menul);
		add_transport.addActionListener(menul);
		add_line.addActionListener(menul);
		
		this.setJMenuBar(jmb);
		
		
		
		JPanel secPanel = new JPanel();
		secPanel.setLayout(new GridLayout(2, 1));
		mainPanel.add(secPanel, BorderLayout.CENTER);
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new GridLayout(1, 2));
		upPanel.setPreferredSize(new Dimension(1000, 400));
		
		upPanel.add(new ImagePanel("resources/check.jpg"));
		upPanel.add(new SearchPanel());
		upPanel.repaint();
		secPanel.add(upPanel);

		JPanel downPanel = new JPanel(new GridLayout(1, 2));
		downPanel.setBorder(new TitledBorder("Con�cenos"));
		downPanel.add(new ImagePanel("resources/error.jpg"));
		downPanel.add(new JLabel("Datos de la empresa..."));
		secPanel.add(downPanel);
		
		
		this.setVisible(true);
	}
	
	private class TableListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmnd = e.getActionCommand();
			if(cmnd.equals("Listado de estaciones"))
				new TableWindow(new StationTable(_cm.getStations()), cmnd, SearchWindow.this);
			else if(cmnd.contentEquals("Listado de transportes"))
				new TableWindow(new TransportTable(_cm.getTransports()), cmnd, SearchWindow.this);
			else
				new TableWindow(new LineTable(_cm.getLines()), cmnd, SearchWindow.this);
		}
	}
	
	private class MiMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(
					SearchWindow.this, 
					"Se ha pulsado en la opcion de menu: " + e.getActionCommand(), 
					"Informacion del menu", 
					JOptionPane.DEFAULT_OPTION);			
		}
	}
	
	@Override
	public void registerObserver(CITISMap cm) {
		_cm = cm;
	}

	@Override
	public void CITISObjectAdded(CITISMap cm, CITISObject co) {
		_cm = cm;
	}

	@Override
	public void CITISObjectModified(CITISMap cm, CITISObject co) {
		_cm = cm;
	}

	@Override
	public void CITISObjectDeleted(CITISMap cm, CITISObject co) {
		_cm = cm;
	}
}
