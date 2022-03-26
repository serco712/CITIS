package app.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TableWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Frame _ancestor;
	
	public TableWindow(AbstractTableModel table, String str, Frame ancestor) {
		super("Listado");
		_ancestor = ancestor;
		initGUI(table, str);
	}
	
	public void initGUI(AbstractTableModel table, String str) {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
		JPanel return_panel = new JPanel();
		return_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JButton goPrevious = new JButton("Retroceder");
		goPrevious.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TableWindow.this.setVisible(false);
				_ancestor.setVisible(true);
			}
			
		});
		return_panel.add(goPrevious);
		mainPanel.add(return_panel, BorderLayout.NORTH);
		
		JPanel viewsPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(viewsPanel, BorderLayout.CENTER);
		
		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(tablesPanel);
		
		JPanel eventsView =
				createViewPanel(new JTable(table), str);
		tablesPanel.add(eventsView);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(c));
		return p;
	}
}
