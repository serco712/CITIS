package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import app.control.Controller;
import app.model.business.CITISMap;
import app.model.business.CITISObject;
import app.model.business.CITISObserver;
import app.model.business.TransportType;
import app.model.business.user.ASUser;

public class SearchWindow extends JFrame implements CITISObserver {
	
	private static final long serialVersionUID = 1L;

	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);
	
	private CITISMap _cm;
	
	private Controller _ctrl;
	
	public SearchWindow(Controller ctrl) {
		super("CITIS");
		_ctrl = ctrl;
		ctrl.addObserver(this);
		initGUI();
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new OptionPaneWindow(_ctrl);
			}
		});
	}
	
	private void initGUI() {
		this.setMinimumSize(new Dimension(600, 400));
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		this.setContentPane(mainPanel);
		
		//mainPanel.add(new SearchControlPanel(), BorderLayout.PAGE_START);
		
		MiMenuListener menul = new MiMenuListener();
		JMenuBar jmb = new JMenuBar();
		jmb.setBackground(Color.WHITE);
		jmb.setPreferredSize(new Dimension(600, 30));
		
		JLabel icon = new JLabel(new ImageIcon("resources/menu.png"));
		jmb.add(icon);
		
		JMenu check_table = new JMenu("Consultar");
		check_table.setBorder(BorderFactory.createMatteBorder(2,2,2,0,Color.black));
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
		
		if(_ctrl.checkData(8, new String[1])) {
			JMenu add_obj = new JMenu("Anadir");
			add_obj.setBorder(BorderFactory.createMatteBorder(2,1,2,0,Color.black));
			JMenuItem add_station = new JMenuItem("Estacion");
			JMenuItem add_transport = new JMenuItem("Transporte");
			JMenuItem add_line = new JMenuItem("Linea");
			JMenuItem add_schedule = new JMenuItem("Horario");
			add_obj.add(add_station);
			add_obj.add(add_transport);
			add_obj.add(add_line);
			add_obj.add(add_schedule);
			jmb.add(add_obj);
			
			add_station.addActionListener(new MiMenuListener());
			add_transport.addActionListener(new MiMenuListener());
			add_line.addActionListener(new MiMenuListener());
			add_schedule.addActionListener(new MiMenuListener());
		}
		
		JMenu map_menu = new JMenu("Mapas");
		map_menu.setBorder(BorderFactory.createMatteBorder(2,1,2,2,Color.black));
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
		miUser.setPreferredSize(new Dimension(32,32));
		miUser.setToolTipText("Mi usuario");
		miUser.setIcon(loadImage("resources/user.png")); 
		miUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserWindow(_cm);
			}
		});
		jmb.add(miUser);
		
		JButton logOut = new JButton();
		logOut.setPreferredSize(new Dimension(32,32));
		logOut.setToolTipText("Desconectarse");
		logOut.setIcon(loadImage("resources/log_out.png")); 
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_ctrl.deleteData(1, new String[1]);
				dispose();
				new InitWindow(null, _ctrl);
			}
		});
		jmb.add(logOut);
						
		this.setJMenuBar(jmb);
		
		JPanel secPanel = new JPanel();
		secPanel.setLayout(new GridLayout(2, 1));
		mainPanel.add(secPanel, BorderLayout.CENTER);
		
		JPanel upPanel = new JPanel();
		upPanel.setBackground(Color.WHITE);
		upPanel.setLayout(new GridLayout(1, 2));
		upPanel.setPreferredSize(new Dimension(1000, 400));
		
		upPanel.add(new ImagePanel("resources/map.jpg"));
		upPanel.add(new SearchPanel(_ctrl));
		upPanel.repaint();
		secPanel.add(upPanel);

		JPanel downPanel = new JPanel(new GridLayout(1, 2));
		downPanel.setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Quienes somos?", TitledBorder.LEFT,TitledBorder.TOP));
		downPanel.setBackground(Color.WHITE);
		downPanel.add(new ImagePanel("resources/logoCITIS.png"));
		downPanel.add(new JLabel("<html><p>Somos una empresa dedicada a la gestion de <br>"
				+ " transportes que tiene como principal objetivo facilitar a los ciudadanos<br>"
				+ " el uso de la red de transporte publico.</p></html>"));
		secPanel.add(downPanel);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	protected ImageIcon loadImage(String path) {
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(path)); 
	}	
	
	private class TableListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmnd = e.getActionCommand();
			if(cmnd.equals("Listado de estaciones"))
				_ctrl.tableOption(2);
			else if(cmnd.contentEquals("Listado de transportes"))
				_ctrl.tableOption(3);
			else
				_ctrl.tableOption(1);
		}
	}
	
	private class MapListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmnd = e.getActionCommand();
			if(cmnd.equals("Trenes"))
				new MapWindow(_ctrl, TransportType.TRAIN);
			else if(cmnd.equals("Autobuses"))
				new MapWindow(_ctrl, TransportType.BUS);
			else
				new MapWindow(_ctrl, TransportType.SUBWAY);
		}
	}
	
	private class MiMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmnd = e.getActionCommand();
			if(cmnd.equals("Transporte"))
				new MapWindow(_ctrl, TransportType.TRAIN);
			else if(cmnd.equals("Linea"))
				new AddLineDialog(_ctrl);
			else if(cmnd.equals("Horario"))
				new AddScheduleDialog(_ctrl);
			else
				new AddStationDialog(_ctrl);
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
