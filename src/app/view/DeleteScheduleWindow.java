package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import app.control.Controller;
import app.model.business.station.ASStation;

public class DeleteScheduleWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String TEXT_SEARCH = "buscar por \"id\" de la estacion"; 
	
	private Controller _ctrl;
	
	private JTable table;
	private JTable tableFiltred;
		
	private List<ASStation> _list = new ArrayList<ASStation>();
		
	public DeleteScheduleWindow(List<ASStation> list, Controller ctrl) {
		super(new JFrame(), "Eliminar horario", true);
		_ctrl = ctrl;
		_list = list;
		table = new StationTable(list, _ctrl, null, 2);
		initGUI("Eliminar horario");
	}
	
	public void initGUI(String str) {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		
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
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.white);
		toolBar.add(searchPanel);
		JTextField search = new JTextField(TEXT_SEARCH, 15);
		search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				search.setText(null);
			}
		});
		searchPanel.add(search);
		JButton searchButton = new JButton();
		searchButton.setBorder(null);
		searchButton.setToolTipText("Buscar");
		searchButton.setIcon(new ImageIcon("resources/search.png")); 
		searchButton.setBackground(Color.WHITE);
		searchPanel.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String _id = search.getText();
				tableFiltred = new StationTable(_list, _ctrl, _id, 2);
			}		
		});
		
		searchButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				searchButton.setIcon(new ImageIcon("resources/search_click.png"));
			}
			public void mouseExited(MouseEvent e) {
				searchButton.setIcon(new ImageIcon("resources/search.png"));
			}
		});
				
		JPanel viewsPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(viewsPanel, BorderLayout.CENTER);
		
		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		viewsPanel.add(tablesPanel);
		
		if (tableFiltred != null) {
			JPanel eventsView = createViewPanel(tableFiltred, str);
			tablesPanel.add(eventsView);
		}
		else {
			JPanel eventsView = createViewPanel(table, str);
			tablesPanel.add(eventsView);
		}
				
		
		this.pack();
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private JPanel createViewPanel(JComponent c, String title) {
		JPanel p = new JPanel(new BorderLayout());
		p.add(new JScrollPane(c));
		return p;
	}
	
}
