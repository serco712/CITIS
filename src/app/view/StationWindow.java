package app.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import app.control.Controller;
import app.misc.Pair;
import app.misc.TimeADT;
import app.model.business.line.ASLine;
import app.model.business.station.ASStation;

public class StationWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private ASStation _st;
		
	private Controller _ctrl;
	
	private boolean _config;
	
	private JTable schedule;
	
	private ScheduleTable sch;
	
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);

	public StationWindow (ASStation st, Controller ctrl, boolean config) {
		super(new JFrame(), "Estacion " + st.getName(), true);
		_st = st;
		_ctrl = ctrl;
		_config = config;
		initGUI();
	}

	private void initGUI() {
		this.setMinimumSize(new Dimension(600, 600));
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		this.setContentPane(mainPanel);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(null);
		toolBar.setBackground(Color.WHITE);
		this.add(toolBar, BorderLayout.PAGE_START);
		
		JButton goBackButton = new JButton();
		goBackButton.setBorder(null);
		goBackButton.setToolTipText("Retroceder");
		goBackButton.setIcon(new ImageIcon("resources/back.png")); 
		goBackButton.setBackground(Color.WHITE);
		goBackButton.setAlignmentX(LEFT_ALIGNMENT);
		goBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}			
		});
		
		goBackButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				goBackButton.setIcon(new ImageIcon("resources/back_click.png"));
			}
			public void mouseExited(MouseEvent e) {
				goBackButton.setIcon(new ImageIcon("resources/back.png"));
			}
		});	
		
		toolBar.add(goBackButton);
		toolBar.add(Box.createRigidArea(new Dimension(50, 0)));
		
		JLabel stationId = new JLabel("Id Parada: " + _st.getId().substring(2, _st.getId().length()));
		stationId.setAlignmentX(CENTER_ALIGNMENT);
		toolBar.add(stationId);
		toolBar.add(Box.createRigidArea(new Dimension(25, 0)));
		
		JLabel stationName = new JLabel("Nombre Parada: " + _st.getName());
		stationName.setAlignmentX(CENTER_ALIGNMENT);
		toolBar.add(stationName);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBorder(_defaultBorder);
		centerPanel.setBackground(Color.WHITE);
		mainPanel.add(centerPanel);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 2));
		p1.setBackground(Color.WHITE);
		centerPanel.add(p1);
		
		JPanel photoPanel = new ImagePanel("resources/map.jpg");
		photoPanel.setBackground(Color.WHITE);
		p1.add(photoPanel);
		
		List<ASLine> ls = _ctrl.searchStationLines(_st.getId());
		JTable t1 = new JTable(new StationLinesTable(ls));
		t1.setBackground(Color.WHITE);
		JPanel lineTablePanel = createViewPanel(t1, "Lineas");
		lineTablePanel.setForeground(Color.WHITE);
		p1.add(lineTablePanel);
		
		List<Pair<Pair<ASLine, TimeADT>, Pair<String, String>>> lc = _ctrl.getScheduleList(_st.getId());
		sch = new ScheduleTable(lc);
		schedule = new JTable(sch);
		schedule.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		if (_config) {
			toolBar.add(Box.createHorizontalGlue());
			
			JButton deleteButton = new JButton();
			deleteButton.setBorder(null);
			deleteButton.setToolTipText("Eliminar");
			deleteButton.setIcon(new ImageIcon("resources/delete_v2.png")); 
			deleteButton.setBackground(Color.WHITE);
			deleteButton.setAlignmentX(LEFT_ALIGNMENT);
			deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (schedule.getSelectedRow() == -1) {
						ImageIcon icon = new ImageIcon("resources/error.png");
						JOptionPane.showMessageDialog(null, "No se ha seleccionado el horario",
								"Error", JOptionPane.DEFAULT_OPTION, icon);
					} else {
						sch.operations(_ctrl, 2, schedule.getSelectedRow());
					}
				}			
			});
			
			deleteButton.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					deleteButton.setIcon(new ImageIcon("resources/delete_clickv2.png"));
				}
				public void mouseExited(MouseEvent e) {
					deleteButton.setIcon(new ImageIcon("resources/delete_v2.png"));
				}
			});	
			toolBar.add(deleteButton);
			
			JButton modifyButton = new JButton();
			modifyButton.setBorder(null);
			modifyButton.setToolTipText("Modificar");
			modifyButton.setIcon(new ImageIcon("resources/edit.png")); 
			modifyButton.setBackground(Color.WHITE);
			modifyButton.setAlignmentX(LEFT_ALIGNMENT);
			modifyButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (schedule.getSelectedRow() == -1) {
						ImageIcon icon = new ImageIcon("resources/error.png");
						JOptionPane.showMessageDialog(null, "No se ha seleccionado el horario",
								"Error", JOptionPane.DEFAULT_OPTION, icon);
					} else {
						sch.operations(_ctrl, 3, schedule.getSelectedRow());
					}
				}		
			});
			
			modifyButton.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					modifyButton.setIcon(new ImageIcon("resources/edit_click.png"));
				}
				public void mouseExited(MouseEvent e) {
					modifyButton.setIcon(new ImageIcon("resources/edit.png"));
				}
			});	
			toolBar.add(modifyButton);
		}
		
		schedule.setBackground(Color.WHITE);
		JPanel p2 = createViewPanel(schedule, "Horarios");
		p2.setBackground(Color.WHITE);
		centerPanel.add(p2);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel(new BorderLayout());
		c.setBackground(Color.WHITE);
		p.setBorder(BorderFactory.createTitledBorder(_defaultBorder, title, TitledBorder.LEFT,TitledBorder.TOP));
		p.add(new JScrollPane(c));
		return p;
	}
}
