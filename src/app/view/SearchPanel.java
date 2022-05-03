package app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import app.control.Controller;
import app.control.ControllerChoices;
import app.misc.Pair;
import app.misc.TimeADT;
import app.model.business.TransportType;
import app.model.business.station.ASStation;
import app.model.layers.integration.station.StationDatabaseDAO;

public class SearchPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);
	private JComboBox<TransportType> tbox;
	private DefaultComboBoxModel<TransportType> tboxm;
	private JComboBox<ASStation> asbox;
	private DefaultComboBoxModel<ASStation> asboxm;
	private Controller _ctrl;
	
	public SearchPanel(Controller ctrl) {
		_ctrl = ctrl;
		tboxm = new DefaultComboBoxModel<>();
		tbox = new JComboBox<>(tboxm);
		asboxm = new DefaultComboBoxModel<>();
		asbox = new JComboBox<>(asboxm);
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.WHITE);
		
		JLabel srcLabel = new JLabel("Tipo de Parada");
		srcLabel.setAlignmentX(CENTER_ALIGNMENT);
		loadTypes();
		tbox.setBackground(Color.WHITE);
		tbox.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		tbox.setPreferredSize(new Dimension(170, 20));
		tbox.setSize(new Dimension(170, 20));
		tbox.setMaximumSize(new Dimension(170, 20));
		tbox.setMinimumSize(new Dimension(170, 20));
		tbox.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(srcLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(tbox);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JLabel destLabel = new JLabel("Parada a buscar");
		destLabel.setAlignmentX(CENTER_ALIGNMENT);
		asbox.setBackground(Color.WHITE);
		asbox.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		asbox.setPreferredSize(new Dimension(275, 20));
		asbox.setSize(new Dimension(275, 20));
		asbox.setMaximumSize(new Dimension(275, 20));
		asbox.setMinimumSize(new Dimension(275, 20));
		asbox.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(destLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(asbox);
		
		this.add(Box.createRigidArea(new Dimension(0, 25)));
		
		JButton searchButton = new JButton("Buscar proximos transportes");
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				searchSchedule();
			}			
		});
		formatButton(searchButton);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonsPanel.setBackground(Color.WHITE);
		buttonsPanel.add(searchButton);
		
		this.add(buttonsPanel);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
	}
	
	private void loadTypes() {
		tboxm.removeAllElements();
		for(TransportType ts : TransportType.values())
			tboxm.addElement(ts);
		tbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadStation();
			}
		});
	}
	
	private void loadStation() {
		asboxm.removeAllElements();
		if((TransportType) tbox.getSelectedItem() != null) {
			for(ASStation as : _ctrl.listStations())
				if(as.getTransport().equals((TransportType) tbox.getSelectedItem()))
					asboxm.addElement(as);
		}
	}
	private void formatButton(JButton b) {
		b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		b.setBackground(Color.WHITE);
		b.setPreferredSize(new Dimension(180, 25));		
		
		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.WHITE));
			}
			public void mouseExited(MouseEvent e) {
				b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
			}
		});	
	}	
	
	@SuppressWarnings("unchecked")
	private void searchSchedule() {
		ASStation ass = (ASStation) asbox.getSelectedItem();
		List<Pair<TimeADT, String>> times = (List<Pair<TimeADT, String>>) _ctrl.findData(ControllerChoices.Find_Next_Time, ass.getId()); 
		
		new TableWindow(new TimeTable(times), "Horarios de " + ass.getName());
	}
}
