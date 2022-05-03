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
import app.model.business.CITISObject;
import app.model.business.TransportType;

public class SearchWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);
	
	private Controller _ctrl;
	
	public SearchWindow(Controller ctrl) {
		super("CITIS");
		_ctrl = ctrl;
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
		
		JMenuBar jmb = new JMenuBar();
		jmb.setBackground(Color.WHITE);
		jmb.setPreferredSize(new Dimension(600, 30));
		
		JLabel icon = new JLabel(new ImageIcon("resources/menu.png"));
		jmb.add(icon);
		
		JMenu check_table = new JMenu("Consultar");
		check_table.setBorder(BorderFactory.createMatteBorder(2,2,2,0,Color.black));
		JMenuItem station_table = new JMenuItem("Listado de estaciones");
		JMenuItem line_table = new JMenuItem("Listado de lineas");
		
		check_table.add(station_table);
		check_table.add(line_table);
		jmb.add(check_table);
		
		station_table.addActionListener(new TableListener());
		line_table.addActionListener(new TableListener());
		
		if(_ctrl.checkData(8, new String[1])) {
			JMenu add_obj = new JMenu("Anadir");
			add_obj.setBorder(BorderFactory.createMatteBorder(2,1,2,0,Color.black));
			JMenuItem add_station = new JMenuItem("Estacion");
			JMenuItem add_line = new JMenuItem("Linea");
			JMenu add_schedule = new JMenu("Horario");
			JMenuItem add_sptr = new JMenuItem("Nuevo Viaje Especifico");
			JMenuItem add_exsptr = new JMenuItem("Viaje Especifico Existente");
			add_schedule.add(add_sptr);
			add_schedule.add(add_exsptr);
			add_obj.add(add_station);

			add_obj.add(add_line);
			add_obj.add(add_schedule);
			jmb.add(add_obj);
			
			add_station.addActionListener(new MiMenuListener());
			add_line.addActionListener(new MiMenuListener());
			add_sptr.addActionListener(new MiMenuListener());
			add_exsptr.addActionListener(new MiMenuListener());
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
		
		if(_ctrl.checkData(8, new String[1])) {
			JButton adminConfig = new JButton();
			adminConfig.setPreferredSize(new Dimension(32,32));
			adminConfig.setToolTipText("Configurar horario(s)");
			adminConfig.setIcon(loadImage("resources/config.png")); 
			adminConfig.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					new AdminScheduleWindow(_ctrl.listStations(), _ctrl, "Configurar horario(s)");
				}
			});
			jmb.add(adminConfig);
		}
		
		JButton miUser = new JButton();
		miUser.setPreferredSize(new Dimension(32,32));
		miUser.setToolTipText("Mi usuario");
		miUser.setIcon(loadImage("resources/user.png")); 
		miUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(_ctrl.checkData(10, null))
					new RegisterWindow(_ctrl);
				else
					new UserWindow(_ctrl);
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
				_ctrl.deleteData(1);
				dispose();
				new InitWindow(_ctrl);
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
			else
				_ctrl.tableOption(1);
		}
	}
	
	private class MapListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmnd = e.getActionCommand();
			if(cmnd.equals("Autobuses"))
				new MapWindow(_ctrl, TransportType.BUS);
			else
				new MapWindow(_ctrl, TransportType.SUBWAY);
		}
	}
	
	private class MiMenuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmnd = e.getActionCommand();
			if(cmnd.equals("Linea"))
				new AddLineDialog(_ctrl);
			else if(cmnd.equals("Nuevo Viaje Especifico"))
				new AddScheduleDialog(_ctrl, 0);
			else if(cmnd.equals("Viaje Especifico Existente"))
				new AddExistingScheduleDialog(_ctrl);
			else
				new AddStationDialog(_ctrl);
		}
	}
}
