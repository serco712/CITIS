package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import app.control.Controller;
import app.misc.TimeADT;
import app.model.business.TransportType;
import app.model.business.line.ASLine;
import app.model.business.line.DTOLine;
import app.model.business.station.ASStation;

public class AddScheduleDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private Controller _ctrl;
	private JTextField trip_id;
	private JComboBox<ASStation> stop_id;
	private DefaultComboBoxModel<ASStation> stop_id_model;
	private JSpinner hours;
	private JSpinner minutes;
	private JSpinner seconds;
	private JSpinner stop_sequence;
	private JTextField st_notes;
	private JComboBox<ASLine> route_id;
	private DefaultComboBoxModel<ASLine> route_id_model;
	private JTextField trip_long_name;
	private JTextField trip_notes;
	private JButton aniadir;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);
	
	public AddScheduleDialog(Controller ctrl) {
		super(new JFrame(), "Anadir Horario", true);
		_ctrl = ctrl;
		InitGUI();
	}

	private void InitGUI() {
		this.setMinimumSize(new Dimension(500, 300));
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBorder(null);
		toolBar.setBackground(Color.WHITE);
		this.add(toolBar, BorderLayout.PAGE_START);
		
		JButton goBackButton = new JButton();
		goBackButton.setBorder(null);
		goBackButton.setToolTipText("Retroceder");
		goBackButton.setIcon(new ImageIcon("resources/back.png")); 
		goBackButton.setBackground(Color.WHITE);
	
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
		
		JPanel secPanel = new JPanel();
		secPanel.setBackground(Color.WHITE);
		secPanel.setLayout(new BoxLayout(secPanel, BoxLayout.Y_AXIS));
		secPanel.setBorder(_defaultBorder);
		mainPanel.add(secPanel);
		
		JPanel textPanel = new JPanel();
		textPanel.setBackground(Color.WHITE);
		textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textPanel.add(new JLabel("Introduzca los datos del nuevo horario:"));
		secPanel.add(textPanel);
		
		JPanel idPanel = new JPanel();
		idPanel.setBackground(Color.WHITE);
		idPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		idPanel.add(new JLabel("Id viaje: "));
		trip_id = new JTextField();
		trip_id.setPreferredSize(new Dimension(100, 20));
		trip_id.setBorder(_defaultBorder);
		idPanel.add(trip_id);
		
		idPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		idPanel.add(new JLabel("Parada: "));
		loadStops();
		idPanel.add(stop_id);
		secPanel.add(idPanel);
		
		JPanel hourPanel = new JPanel();
		hourPanel.setBackground(Color.WHITE);
		hourPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		hourPanel.add(new JLabel("Hora de salida: "));
		hourPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		hourPanel.add(new JLabel("HH: "));
		hours = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
		hours.setMaximumSize(new Dimension(70, 20));
		hours.setMinimumSize(new Dimension(70, 20));
		hours.setPreferredSize(new Dimension(70, 20));
		hours.setBorder(_defaultBorder);
		hourPanel.add(hours);
		
		hourPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		hourPanel.add(new JLabel("MM: "));
		minutes = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		minutes.setMaximumSize(new Dimension(70, 20));
		minutes.setMinimumSize(new Dimension(70, 20));
		minutes.setPreferredSize(new Dimension(70, 20));
		minutes.setBorder(_defaultBorder);
		hourPanel.add(minutes);
		
		hourPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		hourPanel.add(new JLabel("SS: "));
		seconds = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		seconds.setMaximumSize(new Dimension(70, 20));
		seconds.setMinimumSize(new Dimension(70, 20));
		seconds.setPreferredSize(new Dimension(70, 20));
		seconds.setBorder(_defaultBorder);
		hourPanel.add(seconds);
		secPanel.add(hourPanel);
		
		JPanel sequ_notesPanel = new JPanel();
		sequ_notesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		sequ_notesPanel.setBackground(Color.WHITE);
		
		sequ_notesPanel.add(new JLabel("Secuencia Parada: "));
		stop_sequence = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
		stop_sequence.setMaximumSize(new Dimension(70, 20));
		stop_sequence.setMinimumSize(new Dimension(70, 20));
		stop_sequence.setPreferredSize(new Dimension(70, 20));
		stop_sequence.setBorder(_defaultBorder);
		sequ_notesPanel.add(stop_sequence);
		
		sequ_notesPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		sequ_notesPanel.add(new JLabel("Notas parada: "));
		st_notes = new JTextField();
		st_notes.setPreferredSize(new Dimension(100, 20));
		st_notes.setBorder(_defaultBorder);
		sequ_notesPanel.add(st_notes);
		secPanel.add(sequ_notesPanel);
		
		JPanel idNamePanel = new JPanel();
		idNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		idNamePanel.setBackground(Color.WHITE);
		
		idNamePanel.add(new JLabel("Linea: "));
		loadRoutes();
		idNamePanel.add(route_id);
		
		idNamePanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		idNamePanel.add(new JLabel("Nombre viaje: "));
		trip_long_name = new JTextField();
		trip_long_name.setPreferredSize(new Dimension(100, 20));
		trip_long_name.setBorder(_defaultBorder);
		idNamePanel.add(trip_long_name);
		secPanel.add(idNamePanel);
		
		JPanel notesPanel = new JPanel();
		notesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		notesPanel.setBackground(Color.WHITE);
		
		notesPanel.add(new JLabel("Notas viaje: "));
		trip_notes = new JTextField();
		trip_notes.setPreferredSize(new Dimension(100, 20));
		trip_notes.setBorder(_defaultBorder);
		notesPanel.add(trip_notes);
		secPanel.add(notesPanel);
		
		aniadir = new JButton("Anadir");
		formatButton(aniadir);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonsPanel.add(aniadir);
		buttonsPanel.setBackground(Color.WHITE);
		secPanel.add(buttonsPanel);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void createSchedule() {
		if(!trip_id.getText().equals("") && !trip_long_name.getText().equals("")) {
			TimeADT t = new TimeADT((Integer) hours.getValue(), (Integer) minutes.getValue(), 
					(Integer) seconds.getValue());
			String[] dat1 = {trip_id.getText()};
			String[] dat2 = {((ASStation) stop_id.getSelectedItem()).getId(), 
					((ASLine) route_id.getSelectedItem()).getId(), t.toString()};
			if(((ASStation) stop_id.getSelectedItem()).getTransport() != 
					((ASLine) route_id.getSelectedItem()).getTransport()) {
				ImageIcon icon = new ImageIcon("resources/error.png");
				JOptionPane.showMessageDialog(null, "La estacion y la linea no son del mismo tipo", 
		        		"Anadir Horario", JOptionPane.DEFAULT_OPTION, icon);
			}
			else if (_ctrl.checkData(3, dat1)){
				ImageIcon icon = new ImageIcon("resources/error.png");
				JOptionPane.showMessageDialog(null, "El id introducido ya existe", 
		        		"Anadir Linea", JOptionPane.DEFAULT_OPTION, icon);
			}
			else if(!_ctrl.checkData(4, dat2)) {
				ImageIcon icon = new ImageIcon("resources/error.png");
				JOptionPane.showMessageDialog(null, "La agencia introducida no existe", 
		        		"Anadir Linea", JOptionPane.DEFAULT_OPTION, icon);
			}
		}
		else {
			ImageIcon icon = new ImageIcon("resources/error.png");
			JOptionPane.showMessageDialog(null, "Faltan algunos datos requeridos", 
	        		"Anadir Horario", JOptionPane.DEFAULT_OPTION, icon);
		}
	}
	
	private void loadRoutes() {
		route_id_model = new DefaultComboBoxModel<>();
		route_id_model.removeAllElements();
		for(ASLine as : _ctrl.listLines())
			route_id_model.addElement(as);
		route_id = new JComboBox<>(route_id_model);
		route_id.setPreferredSize(new Dimension(120, 20));
		route_id.setBorder(_defaultBorder);
	}

	private void loadStops() {
		stop_id_model = new DefaultComboBoxModel<>();
		stop_id_model.removeAllElements();
		for(ASStation as : _ctrl.listStations())
			stop_id_model.addElement(as);
		stop_id = new JComboBox<>(stop_id_model);
		stop_id.setPreferredSize(new Dimension(150, 20));
		stop_id.setBorder(_defaultBorder);
	}
	
	private void formatButton(JButton b) {
		b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		b.setPreferredSize(new Dimension(50, 25));
		b.setBackground(Color.WHITE);
				
		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.WHITE));
			}
			public void mouseExited(MouseEvent e) {
				b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
			}
		});	
		
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddScheduleDialog.this.createSchedule();
			}
			
		});
	}
}
