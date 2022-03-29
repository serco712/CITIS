package app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.model.TransportType;

public class AddStationDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JComboBox<TransportType> transportCombo;
	private JTextField stationName;
	private JTextField xCoor;
	private JTextField yCoor;
	
	public AddStationDialog(Frame parent) {
		super(parent, true);
		initGUI();
	}
	
	public void initGUI() {
		this.setMinimumSize(new Dimension(300,400));
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);
		
		JButton goBackButton = new JButton("Go back");
		mainPanel.add(goBackButton, BorderLayout.PAGE_START);
		JPanel secPanel = new JPanel();
		secPanel.setLayout(new BoxLayout(secPanel, BoxLayout.Y_AXIS));
		mainPanel.add(secPanel, BorderLayout.CENTER);
		
		//Up panel
		JPanel upPanel = new JPanel(new GridLayout(1, 2));
		secPanel.add(upPanel);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		upPanel.add(dataPanel);
		upPanel.add(new JLabel(""));
		
		dataPanel.add(new JLabel("Tipo de parada:"));
		transportCombo = new JComboBox<>();
		dataPanel.add(transportCombo);
		dataPanel.add(new JLabel("Nombre de la parada:"));
		stationName = new JTextField();
		dataPanel.add(stationName);
		
		//Down panel
		JPanel downPanel = new JPanel();
		downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.Y_AXIS));
		secPanel.add(downPanel);
		
		downPanel.add(new JLabel("Coordenadas"));
		
		JPanel coorPanel = new JPanel();
		coorPanel.setLayout(new BoxLayout(coorPanel, BoxLayout.X_AXIS));
		downPanel.add(coorPanel);
		
		coorPanel.add(new JLabel("X "));
		xCoor = new JTextField();
		coorPanel.add(xCoor);
		coorPanel.add(new JLabel("  Y "));
		yCoor = new JTextField();
		coorPanel.add(yCoor);
		
		JButton addButton = new JButton("Anadir");
		downPanel.add(addButton);
		
		
		this.setVisible(true);
	}

}
