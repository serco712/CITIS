package app.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class SearchControlPanel extends JPanel {
	
	public SearchControlPanel() {
		initGUI();
	}
	
	private void initGUI() {
		JToolBar jtb = new JToolBar();
		this.setLayout(new BorderLayout());
		this.add(jtb, BorderLayout.PAGE_END);
		
		//Go back
		JButton goBackButton = new JButton();
		
		//Languages
		JButton languageButton = new JButton();
		
		//Profile
		JButton profileButton = new JButton();
	}

}
