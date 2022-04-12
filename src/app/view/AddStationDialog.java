package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

import app.model.business.TransportType;

public class AddStationDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JComboBox<TransportType> transportCombo;
	private JTextField stationName;
	private JTextField xCoor;
	private JTextField yCoor;
	
	public AddStationDialog() {
		super(new JFrame(), "Anadir", true);
		initGUI();
	}
	
	public void initGUI() {
		this.setMinimumSize(new Dimension(400,250));
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
		mainPanel.add(secPanel);
		
		//Up panel
		JPanel upPanel = new JPanel();
		upPanel.setBackground(Color.WHITE);
		secPanel.add(upPanel);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setBackground(Color.WHITE);
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		upPanel.add(dataPanel);
		
		dataPanel.add(new JLabel("Tipo de parada:"));
		transportCombo = new JComboBox<>();
		transportCombo.setPreferredSize(new Dimension(100, 20));
		transportCombo.setBackground(Color.white);
		dataPanel.add(transportCombo);
		dataPanel.add(new JLabel("Nombre de la parada:"));
		stationName = new JTextField();
		stationName.setPreferredSize(new Dimension(100, 20));
		dataPanel.add(stationName);
		
		//Down panel
		JPanel downPanel = new JPanel();
		downPanel.setBackground(Color.WHITE);
		downPanel.setLayout(new BoxLayout(downPanel, BoxLayout.Y_AXIS));
		secPanel.add(downPanel);
		
		downPanel.add(new JLabel("Coordenadas"));
		
		JPanel coorPanel = new JPanel();
		coorPanel.setBackground(Color.WHITE);
		downPanel.add(coorPanel, RIGHT_ALIGNMENT);
		
		coorPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		coorPanel.add(new JLabel("X "));
		xCoor = new JTextField();
		xCoor.setPreferredSize(new Dimension(50,20));
		coorPanel.add(xCoor);
		coorPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		coorPanel.add(new JLabel("Y "));
		yCoor = new JTextField();
		yCoor.setPreferredSize(new Dimension(50,20));
		coorPanel.add(yCoor);
		coorPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		JButton addButton = new JButton("Anadir");
		formatButton(addButton);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonsPanel.add(addButton);
		buttonsPanel.setBackground(Color.WHITE);
		
		secPanel.add(buttonsPanel);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
	}
}
