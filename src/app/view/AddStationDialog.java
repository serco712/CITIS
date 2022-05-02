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
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import app.control.Controller;
import app.model.business.TransportType;
import app.model.business.station.DTOStation;

public class AddStationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<TransportType> transportCombo;
	private DefaultComboBoxModel<TransportType> transportType;
	private JTextField stationId;
	private JTextField stationName;
	private JTextField stationCity;
	private JTextField xCoor;
	private JTextField yCoor;
	private JTextField parent_id;
	private Controller _ctrl;
	private JButton aniadir;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);
	
	public AddStationDialog(Controller ctrl) {
		super(new JFrame(), "Anadir estacion", true);
		_ctrl = ctrl;
		initGUI();
	}
	
	public void initGUI() {
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
		textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		textPanel.add(new JLabel("Introduzca los datos de la nueva estacion:"));
		textPanel.setBackground(Color.WHITE);
		secPanel.add(textPanel);
		
		//Up panel
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		upPanel.setBackground(Color.WHITE);
		secPanel.add(upPanel);
		
		upPanel.add(new JLabel("Id parada: "));
		stationId = new JTextField();
		stationId.setPreferredSize(new Dimension(100, 20));
		stationId.setBorder(_defaultBorder);
		upPanel.add(stationId);
		
		upPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		upPanel.add(new JLabel("Tipo de parada: "));
		transportType = new DefaultComboBoxModel<>();
		loadType();
		transportCombo = new JComboBox<>(transportType);
		transportCombo.setPreferredSize(new Dimension(100, 20));
		transportCombo.setBackground(Color.white);
		transportCombo.setBorder(_defaultBorder);
		upPanel.add(transportCombo);
		secPanel.add(upPanel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		centerPanel.add(new JLabel("Nombre estacion: "));
		stationName = new JTextField();
		stationName.setPreferredSize(new Dimension(100, 20));
		stationName.setBorder(_defaultBorder);
		centerPanel.add(stationName);
		
		centerPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		centerPanel.add(new JLabel("Ciudad: "));
		stationCity = new JTextField();
		stationCity.setPreferredSize(new Dimension(100, 20));
		stationCity.setBorder(_defaultBorder);
		centerPanel.add(stationCity);
		secPanel.add(centerPanel);
		
		JPanel centerPanelBis = new JPanel();
		centerPanelBis.setBackground(Color.WHITE);
		centerPanelBis.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		centerPanelBis.add(new JLabel("X: "));
		xCoor = new JTextField();
		xCoor.setPreferredSize(new Dimension(100, 20));
		xCoor.setBorder(_defaultBorder);
		centerPanelBis.add(xCoor);
		
		centerPanelBis.add(Box.createRigidArea(new Dimension(15, 0)));
		
		centerPanelBis.add(new JLabel("Y: "));
		yCoor = new JTextField();
		yCoor.setPreferredSize(new Dimension(100, 20));
		yCoor.setBorder(_defaultBorder);
		centerPanelBis.add(yCoor);
		
		secPanel.add(centerPanelBis);
		
		JPanel downPanel = new JPanel();
		downPanel.setBackground(Color.WHITE);
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		downPanel.add(new JLabel("Id estacion padre: "));
		parent_id = new JTextField();
		parent_id.setPreferredSize(new Dimension(50,20));
		parent_id.setBorder(_defaultBorder);
		downPanel.add(parent_id);
		secPanel.add(downPanel);
		
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

	private void loadType() {
		transportType.removeAllElements();
		for(TransportType ts : TransportType.values())
			transportType.addElement(ts);
	}
	
	private void createStation() {
		if(transportType.getSelectedItem() != null && !stationId.getText().equals("") 
				&& !stationName.getText().equals("") && !stationCity.getText().equals("")
				&& !xCoor.getText().equals("") && !yCoor.getText().equals("")) {
			int ttype;
			if(transportType.getSelectedItem().equals(TransportType.SUBWAY))
				ttype = 4;
			else if(transportType.getSelectedItem().equals(TransportType.TRAIN))
				ttype = 5;
			else
				ttype = 8;
			
			StringBuilder st = new StringBuilder();
			st.append(ttype);
			st.append('_');
			st.append(stationId.getText());
			String[] dat1 = {st.toString()};
			if(!_ctrl.checkData(5, dat1)) {
				DTOStation dtl = new DTOStation();
				dtl.setId(st.toString());
				dtl.setTransportType((TransportType) transportType.getSelectedItem());
				dtl.setName(stationName.getText());
				dtl.setCity(stationCity.getText());
				dtl.setParent(parent_id.getText());
				dtl.setXCoor(Integer.parseInt(xCoor.getText()));
				dtl.setYCoor(Integer.parseInt(yCoor.getText()));
				_ctrl.addData(2, dtl);
				dispose();
			}
			else {
				ImageIcon icon = new ImageIcon("resources/error.png");
				JOptionPane.showMessageDialog(null, "El id introducido ya existe", 
		        		"Anadir Linea", JOptionPane.DEFAULT_OPTION, icon);
			}
		}
		else {
			ImageIcon icon = new ImageIcon("resources/error.png");
			JOptionPane.showMessageDialog(null, "Faltan algunos datos requeridos", 
	        		"Anadir Linea", JOptionPane.DEFAULT_OPTION, icon);
		}
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
				AddStationDialog.this.createStation();
			}
			
		});
	}
}
