package app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import app.model.business.station.ASStation;

public class SearchPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);

	public SearchPanel() {
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.WHITE);
		
		JLabel srcLabel = new JLabel("Origen");
		srcLabel.setAlignmentX(CENTER_ALIGNMENT);
		JComboBox<ASStation> srcCombo = new JComboBox<>();
		srcCombo.setBackground(Color.WHITE);
		srcCombo.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
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
		destCombo.setBackground(Color.WHITE);
		destCombo.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
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
		formatButton(searchButton);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonsPanel.setBackground(Color.WHITE);
		buttonsPanel.add(searchButton);
		
		this.add(buttonsPanel);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	private void formatButton(JButton b) {
		b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		b.setBackground(Color.WHITE);
		b.setPreferredSize(new Dimension(65, 25));		
		
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
