package app.view;

import java.awt.Dimension;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.model.business.station.ASStation;

public class SearchPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public SearchPanel() {
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel srcLabel = new JLabel("Origen");
		srcLabel.setAlignmentX(CENTER_ALIGNMENT);
		JComboBox<ASStation> srcCombo = new JComboBox<>();
		srcCombo.setPreferredSize(new Dimension(170, 20));
		srcCombo.setSize(new Dimension(170, 20));
		srcCombo.setMaximumSize(new Dimension(170, 20));
		srcCombo.setMinimumSize(new Dimension(170, 20));
		srcCombo.setAlignmentX(CENTER_ALIGNMENT);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(srcLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(srcCombo);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		
		JLabel destLabel = new JLabel("Destino");
		destLabel.setAlignmentX(CENTER_ALIGNMENT);
		JComboBox<ASStation> destCombo = new JComboBox<>();
		destCombo.setPreferredSize(new Dimension(170, 20));
		destCombo.setSize(new Dimension(170, 20));
		destCombo.setMaximumSize(new Dimension(170, 20));
		destCombo.setMinimumSize(new Dimension(170, 20));
		destCombo.setAlignmentX(CENTER_ALIGNMENT);
		this.add(destLabel);
		this.add(Box.createRigidArea(new Dimension(0, 5)));
		this.add(destCombo);
		
		this.add(Box.createRigidArea(new Dimension(0, 25)));
		JButton searchButton = new JButton("Buscar");
		searchButton.setAlignmentX(CENTER_ALIGNMENT);
		this.add(searchButton);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
	}

}
