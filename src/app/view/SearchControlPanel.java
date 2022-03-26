package app.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class SearchControlPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public SearchControlPanel() {
		initGUI();
	}
	
	private void initGUI() {
		JToolBar jtb = new JToolBar();
		this.setLayout(new BorderLayout());
		this.add(jtb, BorderLayout.EAST);
		
		//Go back
		JButton goBackButton = new JButton();
		goBackButton.setIcon(new ImageIcon("resources/logOut.png"));
		jtb.add(goBackButton);
		
		//Languages
		JButton languageButton = new JButton();
		languageButton.setIcon(new ImageIcon("resources/idioma.png"));
		jtb.add(languageButton);
		
		//Profile
		JButton profileButton = new JButton();
		profileButton.setIcon(new ImageIcon("resources/miPerfil.jpg"));
		jtb.add(profileButton);
	}

}
