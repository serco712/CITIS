package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
import app.model.business.station.ASStation;
import app.model.business.trip.ASTrip;
import app.model.business.trip.DTOTrip;

public class AddExistingScheduleDialog extends JDialog {
private static final long serialVersionUID = 1L;
	
	private Controller _ctrl;
	
	private JComboBox<ASTrip> trip_id;
	private DefaultComboBoxModel<ASTrip> trip_id_model;
	private JComboBox<ASStation> stop_id;
	private DefaultComboBoxModel<ASStation> stop_id_model;
	private JSpinner hours;
	private JSpinner minutes;
	private JSpinner seconds;
	private JTextField st_notes;
	private JButton aniadir;
	
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);
	
	public AddExistingScheduleDialog(Controller ctrl) {
		super(new JFrame(), "Anadir Horario Existente", true);
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
		loadTrips();
		idPanel.add(trip_id);
		
		idPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		idPanel.add(new JLabel("Parada: "));
		loadStops();
		idPanel.add(stop_id);
		secPanel.add(idPanel);
		
		JPanel hourPanel = new JPanel();
		hourPanel.setBackground(Color.WHITE);
		hourPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		hourPanel.add(new JLabel("Hora de parada: "));
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
		
		sequ_notesPanel.add(new JLabel("Notas parada: "));
		st_notes = new JTextField();
		st_notes.setPreferredSize(new Dimension(100, 20));
		st_notes.setBorder(_defaultBorder);
		sequ_notesPanel.add(st_notes);
		secPanel.add(sequ_notesPanel);
		
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
	
	private void createExistingSchedule() {
		if(((ASTrip) trip_id.getSelectedItem()).getId().subSequence(0, 1).equals(((ASStation) stop_id.getSelectedItem()).getId().subSequence(0, 1))) {
			TimeADT t = new TimeADT((Integer) hours.getValue(), (Integer) minutes.getValue(), 
					(Integer) seconds.getValue());
			
			DTOTrip dto = new DTOTrip();
			dto.set_id(((ASTrip) trip_id.getSelectedItem()).getId());
			dto.set_stop_id(((ASStation) stop_id.getSelectedItem()).getId());
			dto.set_departureTime(t.toString());
			dto.set_stop_notes(st_notes.getText());
			// ADD TO MARIA DB
			//_ctrl.addData(5, dto);
			dispose();
		}
		else {
			ImageIcon icon = new ImageIcon("resources/error.png");
			JOptionPane.showMessageDialog(null, "La parada y el viaje seleccionado no son del mismo tipo", 
	        		"Anadir Horario Existente", JOptionPane.DEFAULT_OPTION, icon);
		}
	}
	
	private void loadTrips() {
		trip_id_model = new DefaultComboBoxModel<>();
		trip_id_model.removeAllElements();
		// Take data from MariaDB
		trip_id = new JComboBox<>(trip_id_model);
		trip_id.setPreferredSize(new Dimension(150, 20));
		trip_id.setBorder(_defaultBorder);
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
				AddExistingScheduleDialog.this.createExistingSchedule();
					
			}
			
		});
	}
}
