package app.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.model.Station;

public class SearchPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public SearchPanel() {
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel srcLabel = new JLabel("Origen");
		JComboBox<Station> srcCombo = new JComboBox<>();
		this.add(srcLabel);
		this.add(srcCombo);
		
		JLabel destLabel = new JLabel("Destino");
		JComboBox<Station> destCombo = new JComboBox<>();
		this.add(destLabel);
		this.add(destCombo);
		
		JButton searchButton = new JButton("Buscar");
		this.add(searchButton);
	}

}
