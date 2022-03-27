package app.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SearchControlPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public SearchControlPanel() {
		initGUI();
	}
	
	private void initGUI() {
		//JToolBar jtb = new JToolBar();
		this.setLayout(new GridLayout(1, 2));
		//this.add(jtb, BorderLayout.EAST);
		
		JPanel menu_panel = new JPanel();
		menu_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//Go back
		JButton goBackButton = new JButton();
		goBackButton.setIcon(new ImageIcon("resources/logOut.png"));
		button_panel.add(goBackButton);
		
		//Languages
		JButton languageButton = new JButton();
		languageButton.setIcon(new ImageIcon("resources/idioma.png"));
		button_panel.add(languageButton);
		
		//Profile
		JButton profileButton = new JButton();
		profileButton.setIcon(new ImageIcon("resources/miPerfil.jpg"));
		button_panel.add(profileButton);
		
		this.add(button_panel);
	}
}
