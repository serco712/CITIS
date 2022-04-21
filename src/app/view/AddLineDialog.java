package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;

import app.control.Controller;
import app.model.business.TransportType;
import app.model.business.line.DTOLine;

public class AddLineDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JComboBox<TransportType> transportCombo;
	private DefaultComboBoxModel<TransportType> transportCombom;
	private JSpinner rText;
	private JSpinner gText;
	private JSpinner bText;
	private JSpinner rLine;
	private JSpinner gLine;
	private JSpinner bLine;
	private JTextField lineId;
	private JTextField lineSName;
	private JTextField lineLName;
	private Controller ctrl;
	
	public AddLineDialog(Controller ctrl) {
		super(new JFrame(), "Anadir Linea", true);
		this.ctrl = ctrl;
		initGUI();
	}
	
	public void initGUI() {
		this.setMinimumSize(new Dimension(500, 300));
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);
		
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
		
		JPanel secPanel = new JPanel();
		secPanel.setBackground(Color.WHITE);
		secPanel.setLayout(new BoxLayout(secPanel, BoxLayout.Y_AXIS));
		mainPanel.add(secPanel);
		
		//Up panel
		JPanel upPanel = new JPanel();
		upPanel.setBackground(Color.WHITE);
		JLabel txt = new JLabel("Introduzca los datos de la nueva linea:");
		txt.setAlignmentX(CENTER_ALIGNMENT);
		upPanel.add(txt);
		secPanel.add(upPanel);
		JPanel typeidPanel = new JPanel();
		typeidPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		typeidPanel.setBackground(Color.WHITE);
		typeidPanel.add(new JLabel("Tipo de parada: "));
		transportCombom = new DefaultComboBoxModel<>();
		transportCombo = new JComboBox<>(transportCombom);
		transportCombom.removeAllElements();
		for (TransportType t : TransportType.values())
			transportCombom.addElement(t);
		transportCombo.setPreferredSize(new Dimension(80, 20));
		transportCombo.setBackground(Color.WHITE);
		typeidPanel.add(transportCombo);
		typeidPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		typeidPanel.add(new JLabel("Id parada: "));
		lineId = new JTextField();
		lineId.setPreferredSize(new Dimension(100, 20));
		typeidPanel.add(lineId);
		secPanel.add(typeidPanel);
		
		
		JPanel dataPanel = new JPanel();
		dataPanel.setBackground(Color.WHITE);
		dataPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		dataPanel.add(new JLabel("Nombre abrev.: "));
		lineSName = new JTextField();
		lineSName.setPreferredSize(new Dimension(100, 20));
		dataPanel.add(lineSName);
		dataPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		dataPanel.add(new JLabel("Nombre comp.: "));
		lineLName = new JTextField();
		lineLName.setPreferredSize(new Dimension(100, 20));
		dataPanel.add(lineLName);
		secPanel.add(dataPanel);
		
		//Down panel
		JPanel colorTextPanel = new JPanel();
		colorTextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		colorTextPanel.setBackground(Color.WHITE);
		colorTextPanel.add(new JLabel("Color texto: "));
		colorTextPanel.add(new JLabel("R: "));
		rText = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		rText.setMaximumSize(new Dimension(70, 20));
		rText.setMinimumSize(new Dimension(70, 20));
		rText.setPreferredSize(new Dimension(70, 20));
		colorTextPanel.add(rText);
		colorTextPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		colorTextPanel.add(new JLabel("G: "));
		gText = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		gText.setMaximumSize(new Dimension(70, 20));
		gText.setMinimumSize(new Dimension(70, 20));
		gText.setPreferredSize(new Dimension(70, 20));
		colorTextPanel.add(gText);
		colorTextPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		colorTextPanel.add(new JLabel("B: "));
		bText = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		bText.setMaximumSize(new Dimension(70, 20));
		bText.setMinimumSize(new Dimension(70, 20));
		bText.setPreferredSize(new Dimension(70, 20));
		colorTextPanel.add(bText);
		secPanel.add(colorTextPanel);
		
		JPanel colorLinePanel = new JPanel();
		colorLinePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		colorLinePanel.setBackground(Color.WHITE);
		colorLinePanel.add(new JLabel("Color linea: "));
		colorLinePanel.add(new JLabel("R: "));
		rLine = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		rLine.setMaximumSize(new Dimension(70, 20));
		rLine.setMinimumSize(new Dimension(70, 20));
		rLine.setPreferredSize(new Dimension(70, 20));
		colorLinePanel.add(rLine);
		colorLinePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		colorLinePanel.add(new JLabel("G: "));
		gLine = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		gLine.setMaximumSize(new Dimension(70, 20));
		gLine.setMinimumSize(new Dimension(70, 20));
		gLine.setPreferredSize(new Dimension(70, 20));
		colorLinePanel.add(gLine);
		colorLinePanel.add(Box.createRigidArea(new Dimension(0, 10)));
		colorLinePanel.add(new JLabel("B: "));
		bLine = new JSpinner(new SpinnerNumberModel(0, 0, 255, 1));
		bLine.setMaximumSize(new Dimension(70, 20));
		bLine.setMinimumSize(new Dimension(70, 20));
		bLine.setPreferredSize(new Dimension(70, 20));
		colorLinePanel.add(bLine);
		secPanel.add(colorLinePanel);

		JButton addButton = new JButton("Anadir");
		formatButton(addButton);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		buttonsPanel.add(addButton);
		buttonsPanel.setBackground(Color.WHITE);
		
		secPanel.add(buttonsPanel);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void createLine() {
		if(transportCombom.getSelectedItem() != null && !lineId.getText().equals("") 
				&& !lineSName.getText().equals("") && !lineLName.getText().equals("")) {
			int ttype;
			if(transportCombom.getSelectedItem().equals(TransportType.SUBWAY))
				ttype = 4;
			else if(transportCombom.getSelectedItem().equals(TransportType.TRAIN))
				ttype = 5;
			else
				ttype = 8;
			StringBuilder st = new StringBuilder();
			st.append(ttype);
			st.append('_');
			st.append(lineId.getText());
			String[] dat = {st.toString()};
			if(!ctrl.checkData(3, dat)) {
				DTOLine dtl = new DTOLine();
				dtl.setId(st.toString());
				dtl.setTransportType((TransportType) transportCombom.getSelectedItem());
				dtl.setLongName(lineLName.getText());
				dtl.setShortName(lineSName.getText());
				dtl.setColorLine(new Color((Integer)rLine.getValue(), (Integer)gLine.getValue(), (Integer)bLine.getValue()));
				dtl.setColorText(new Color((Integer)rText.getValue(), (Integer)gLine.getValue(), (Integer)bText.getValue()));
				dtl.setAgency(" ");
				ctrl.addData(3, dtl);
				dispose();
			}
			else {
				ImageIcon icon = new ImageIcon("resources/error.png");
				JOptionPane.showMessageDialog(null, "El id ya existe", 
		        		"Anadir Linea", JOptionPane.DEFAULT_OPTION, icon);
			}
		}
		else {
			ImageIcon icon = new ImageIcon("resources/error.png");
			JOptionPane.showMessageDialog(null, "Faltan algunos datos requeridos", 
	        		"Anadir Linea", JOptionPane.DEFAULT_OPTION, icon);
		}
	}
	
	private void formatButton(JButton b) {
		b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		b.setPreferredSize(new Dimension(50, 25));
		b.setBackground(Color.WHITE);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddLineDialog.this.createLine();
			}
			
		});	
		
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
