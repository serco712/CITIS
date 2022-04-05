package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import app.control.Controller;
import app.model.business.CITISMap;
import app.model.business.CITISObject;
import app.model.business.CITISObserver;
import app.model.business.TransportType;

public class SearchWindow extends JFrame implements CITISObserver {
	
	private static final long serialVersionUID = 1L;

	private CITISMap _cm;
	
	private Controller _ctrl;
	
	public SearchWindow(Controller ctrl) {
		super("CITIS");
		_ctrl = ctrl;
		ctrl.addObserver(this);
		initGUI();
	}
	
	private void initGUI() {
		this.setMinimumSize(new Dimension(600, 400));
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		this.setContentPane(mainPanel);
		
		//mainPanel.add(new SearchControlPanel(), BorderLayout.PAGE_START);
		
		MiMenuListener menul = new MiMenuListener();
		JMenuBar jmb = new JMenuBar();
		
		JMenu check_table = new JMenu("Consultar");
		JMenuItem station_table = new JMenuItem("Listado de estaciones");
		JMenuItem transport_table = new JMenuItem("Listado de transportes");
		JMenuItem line_table = new JMenuItem("Listado de lineas");
		
		check_table.add(station_table);
		check_table.add(transport_table);
		check_table.add(line_table);
		jmb.add(check_table);
		
		station_table.addActionListener(new TableListener());
		transport_table.addActionListener(new TableListener());
		line_table.addActionListener(new TableListener());
		
		JMenu add_obj = new JMenu("Anadir");
		JMenuItem add_station = new JMenuItem("Estacion");
		JMenuItem add_transport = new JMenuItem("Transporte");
		JMenuItem add_line = new JMenuItem("Linea");
		
		add_obj.add(add_station);
		add_obj.add(add_transport);
		add_obj.add(add_line);
		jmb.add(add_obj);
		
		add_station.addActionListener(menul);
		add_transport.addActionListener(menul);
		add_line.addActionListener(menul);
		
		JMenu map_menu = new JMenu("Mapas");
		JMenuItem map_train = new JMenuItem("Trenes");
		JMenuItem map_bus = new JMenuItem("Autobuses");
		JMenuItem map_subway = new JMenuItem("Metro");
		
		map_menu.add(map_train);
		map_menu.add(map_bus);
		map_menu.add(map_subway);
		jmb.add(map_menu);
		
		map_train.addActionListener(new MapListener());
		map_bus.addActionListener(new MapListener());
		map_subway.addActionListener(new MapListener());
		
		jmb.add(Box.createHorizontalGlue());
		
		JButton miUser = new JButton();
		miUser.setToolTipText("Mi usuario");
		miUser.setIcon(loadImage("resources/error.png")); //AQUI IRIA USER.PNG PERO NO CONSIGO PONERLA
		miUser.addActionListener(new UserListener());
		jmb.add(miUser);
		
		
		this.setJMenuBar(jmb);
		
		
		
		JPanel secPanel = new JPanel();
		secPanel.setLayout(new GridLayout(2, 1));
		mainPanel.add(secPanel, BorderLayout.CENTER);
		
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new GridLayout(1, 2));
		upPanel.setPreferredSize(new Dimension(1000, 400));
		
		upPanel.add(new ImagePanel("resources/map.jpg"));
		upPanel.add(new SearchPanel());
		upPanel.repaint();
		secPanel.add(upPanel);

		JPanel downPanel = new JPanel(new GridLayout(1, 2));
		downPanel.setBorder(new TitledBorder("Quienes somos?"));
		downPanel.add(new ImagePanel("resources/logoCITIS.png"));
		downPanel.add(new JLabel("<html><p>Somos una empresa dedicada a la gestion de <br>"
				+ " transportes que tiene como principal objetivo facilitar a los ciudadanos<br>"
				+ " el uso de la red de transporte publico.</p></html>"));
		secPanel.add(downPanel);
		
		
		this.setVisible(true);
	}
	
	protected ImageIcon loadImage(String path) {
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(path)); 
	}	
	
	private class UserListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
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
	
	private class MapListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmnd = e.getActionCommand();
			if(cmnd.equals("Trenes"))
				new MapWindow(SearchWindow.this, _ctrl, TransportType.TRAIN);
			else if(cmnd.equals("Autobuses"))
				new MapWindow(SearchWindow.this, _ctrl, TransportType.BUS);
			else
				new MapWindow(SearchWindow.this, _ctrl, TransportType.SUBWAY);
		}
	}
	
	private class MiMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AddStationDialog(SearchWindow.this);
			/*
			JOptionPane.showMessageDialog(
					SearchWindow.this, 
					"Se ha pulsado en la opcion de menu: " + e.getActionCommand(), 
					"Informacion del menu", 
					JOptionPane.DEFAULT_OPTION);	
					*/		
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
