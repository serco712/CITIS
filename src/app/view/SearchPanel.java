package app.view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.model.Station;

public class SearchPanel extends JPanel {
	
	public SearchPanel() {
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JLabel srcLabel = new JLabel("Origen");
		JComboBox<Station> srcCombo = new JComboBox<>();
		
		JLabel destLabel = new JLabel("Destino");
		JComboBox<Station> destCombo = new JComboBox<>();
		
		JButton searchButton = new JButton("Buscar");
	}

}
