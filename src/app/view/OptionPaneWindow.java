package app.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import app.control.Controller;

public class OptionPaneWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String INFO_TEXT = "Estas seguro que quieres cerrar la aplicacion?";

	private static final String COMMAND_NO = "No";
	private static final String COMMAND_YES = "Yes";
	
	private Controller _ctrl;
	
	public OptionPaneWindow(Controller ctrl) {
		super(new JFrame(), "Confirmacion de cierre", true);
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBackground(Color.WHITE);

		JButton btnNo = new JButton(COMMAND_NO);
		formatButton(btnNo);
		btnNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
			
		JButton btnYes = new JButton(COMMAND_YES);
		formatButton(btnYes);
		btnYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					_ctrl.saveData();
				}
				catch(Exception a) {
					System.out.println(a.getMessage());
				}
				
				System.exit(0);
			}
		});
			
		JPanel pnl = new JPanel();
		pnl.setBackground(Color.WHITE);
		this.getContentPane().add(pnl);
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		this.setContentPane(pnl);
		
		JPanel pnlMsg = new JPanel();
		pnlMsg.setBackground(Color.WHITE);
		pnlMsg.setAlignmentX(CENTER_ALIGNMENT);
		pnl.add(pnlMsg);
				
		JLabel icon = new JLabel(new ImageIcon("resources/exit.png"));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		pnlMsg.add(icon);
		
		JLabel lbText = new JLabel(INFO_TEXT);
		lbText.setAlignmentX(CENTER_ALIGNMENT);
		pnlMsg.add(lbText);
		
		JPanel pnlButtons = new JPanel();
		pnlButtons.setBackground(Color.WHITE);
		pnlButtons.setAlignmentX(CENTER_ALIGNMENT);
		pnl.add(pnlButtons);
		
		pnlButtons.add(btnNo);
		pnlButtons.add(btnYes);
		
		this.add(Box.createRigidArea(new Dimension(0, 10)));

		this.setSize(500,200);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(true);
	}
	
	private void formatButton(JButton b) {
		b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		b.setBackground(Color.WHITE);
		b.setPreferredSize(new Dimension(35, 25));
		
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
