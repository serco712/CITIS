package app.view;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import app.control.Controller;
import app.model.business.user.ASUser;
import app.model.business.user.DTOUser;


public class UserWindow extends JDialog {

	private static final long serialVersionUID = 1L;

	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);
	
	private ImageIcon profilePhoto = ASUser.getInstance().getPhoto();
	
	private JTextField emaT;
	private JTextField apeT;
	private JTextField nomT;
	private JButton changePhoto;
	
	private Controller _ctrl;
	
	private int _statusEditNom = 1;
	private int _statusEditApe = 1;

	public UserWindow(Controller ctrl) {
		super(new JFrame(), "Mi perfil", true);
		_ctrl = ctrl;
		InitGUI();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(470, 260));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void InitGUI() {
		this.setMinimumSize(new Dimension(500, 300));
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10, 5));
		mainPanel.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		mainPanel.setBackground(Color.WHITE);
		
		this.setContentPane(mainPanel);
		
		JPanel panelIzd = new JPanel();
		panelIzd.setBackground(Color.WHITE);
		panelIzd.setLayout(new BoxLayout(panelIzd, BoxLayout.Y_AXIS));
		mainPanel.add(panelIzd, BorderLayout.WEST);

		
		JPanel butPanel = new JPanel();
		butPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		butPanel.setBorder(null);
		butPanel.setBackground(Color.WHITE);
		panelIzd.add(butPanel);

		JButton goBackButton = new JButton();
		goBackButton.setBorder(null);
		goBackButton.setToolTipText("Retroceder");
		goBackButton.setIcon(new ImageIcon("resources/back.png"));
		goBackButton.setBackground(Color.WHITE);

		goBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

		butPanel.add(goBackButton);
		
		JPanel panelImg = new JPanel();
		panelImg.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelImg.setBackground(Color.white);
		panelImg.add(userPhoto());
		panelIzd.add(panelImg);
		
		JPanel panelDrch = new JPanel(new GridLayout(6, 1));
		panelDrch.setBackground(Color.WHITE);
		mainPanel.add(panelDrch, BorderLayout.EAST);

		JPanel nom_panel = new JPanel();
		nom_panel.setBackground(Color.WHITE);
		nom_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel nom = new JLabel("Nombre: ", JLabel.TRAILING);
		nom_panel.add(nom);
		panelDrch.add(nom_panel);
		
		JPanel nomT_panel = new JPanel();
		nomT_panel.setBackground(Color.WHITE);
		nomT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		nomT = new JTextField(25);
		nomT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		nomT.setEnabled(false);
		nomT.setText(ASUser.getInstance().getName());
		nom.setLabelFor(nomT);
		nomT_panel.add(nomT);
		
		JButton editNom = new JButton();
		editNom.setBorder(null);
		editNom.setToolTipText("Editar nombre");
		editNom.setIcon(new ImageIcon("resources/edit.png"));
		editNom.setBackground(Color.WHITE);

		editNom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (_statusEditNom == 1) {
					nomT.setEnabled(true);
					_statusEditNom = 0;
				}
				else {
					ASUser.getInstance().setName(nomT.getText());
					updateData();
					nomT.setEnabled(false);
					_statusEditNom = 1;
				}
			}
		});

		editNom.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				editNom.setIcon(new ImageIcon("resources/edit_click.png"));
			}

			public void mouseExited(MouseEvent e) {
				editNom.setIcon(new ImageIcon("resources/edit.png"));
			}
		});
		
		nomT_panel.add(editNom);
		panelDrch.add(nomT_panel);

		JPanel ape_panel = new JPanel();
		ape_panel.setBackground(Color.WHITE);
		ape_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ape = new JLabel("Apellidos: ", JLabel.TRAILING);
		ape_panel.add(ape);
		panelDrch.add(ape_panel);

		JPanel apeT_panel = new JPanel();
		apeT_panel.setBackground(Color.WHITE);
		apeT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		apeT = new JTextField(25);
		apeT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		apeT.setEnabled(false);
		apeT.setText(ASUser.getInstance().getSurname());
		ape.setLabelFor(apeT);
		apeT_panel.add(apeT);
		
		JButton editApe = new JButton();
		editApe.setBorder(null);
		editApe.setToolTipText("Editar apellidos");
		editApe.setIcon(new ImageIcon("resources/edit.png"));
		editApe.setBackground(Color.WHITE);

		editApe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (_statusEditApe == 1) {
					apeT.setEnabled(true);
					_statusEditApe = 0;
				}
				else {
					ASUser.getInstance().setSurname(apeT.getText());
					updateData();
					apeT.setEnabled(false);
					_statusEditApe = 1;
				}
			}
		});

		editApe.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				editApe.setIcon(new ImageIcon("resources/edit_click.png"));
			}

			public void mouseExited(MouseEvent e) {
				editApe.setIcon(new ImageIcon("resources/edit.png"));
			}
		});
		
		apeT_panel.add(editApe);
		
		panelDrch.add(apeT_panel);

		JPanel ema_panel = new JPanel();
		ema_panel.setBackground(Color.WHITE);
		ema_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel ema = new JLabel("E-mail: ", JLabel.TRAILING);
		ema_panel.add(ema);
		panelDrch.add(ema_panel);

		JPanel emaT_panel = new JPanel();
		emaT_panel.setBackground(Color.WHITE);
		emaT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		emaT = new JTextField(25);
		emaT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
		emaT.setEnabled(false);
		emaT.setText(ASUser.getInstance().getEmail());
		ema.setLabelFor(emaT);
		emaT_panel.add(emaT);
		panelDrch.add(emaT_panel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		JButton mostrarcontra = new JButton("Cambiar contrasena");
		formatButton(mostrarcontra);
		mostrarcontra.setPreferredSize(new Dimension(130, 25));
		mostrarcontra.setAlignmentX(CENTER_ALIGNMENT);
		buttonPanel.add(mostrarcontra);
		mostrarcontra.add(Box.createRigidArea(new Dimension(0, 30)));

		mostrarcontra.addActionListener(new mostrarListener());
		
		this.pack();
		this.setResizable(false);
	}

	private void formatButton(JButton b) {
		b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		b.setBackground(Color.WHITE);

		b.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.WHITE));
			}

			public void mouseExited(MouseEvent e) {
				b.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
			}
		});
	}

	private class mostrarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog diag = new JDialog(new JFrame(), "Cambiar contrasena", true);
			diag.setLayout(new GridLayout(6,1));
			JPanel contra_panel = new JPanel();
			contra_panel.setBackground(Color.WHITE);
			contra_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel contra = new JLabel("Contrasena: ", JLabel.TRAILING);
			contra_panel.add(contra);
			diag.add(contra_panel);

			JPanel contraT_panel = new JPanel();
			contraT_panel.setBackground(Color.WHITE);
			contraT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			JPasswordField contraT = new JPasswordField(15);
			contraT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
			contra.setLabelFor(contraT);
			contraT_panel.add(contraT);
			diag.add(contraT_panel);

			JPanel rcontra_panel = new JPanel();
			rcontra_panel.setBackground(Color.WHITE);
			rcontra_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel rcontra = new JLabel("Repetir contrasena: ", JLabel.TRAILING);
			rcontra_panel.add(rcontra);
			diag.add(rcontra_panel);

			JPanel rcontraT_panel = new JPanel();
			rcontraT_panel.setBackground(Color.WHITE);
			rcontraT_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
			JPasswordField rcontraT = new JPasswordField(15);
			rcontraT.setBorder(BorderFactory.createTitledBorder(_defaultBorder));
			rcontra.setLabelFor(rcontraT);
			rcontraT_panel.add(rcontraT);
			diag.add(rcontraT_panel);

			JPanel showContra = new JPanel();
			showContra.setBackground(Color.WHITE);
			showContra.setLayout(new FlowLayout(FlowLayout.LEFT));
			JCheckBox jc = new JCheckBox();
			jc.setBackground(Color.WHITE);
			jc.addActionListener(new ActionListener() {

				private boolean ctrl = true;

				private char def = contraT.getEchoChar();

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(ctrl) {
						contraT.setEchoChar((char) 0);
						rcontraT.setEchoChar((char) 0);
						ctrl = false;
					}
					else {
						contraT.setEchoChar(def);
						rcontraT.setEchoChar(def);
						ctrl = true;
					}
				}
			});

			showContra.add(jc);
			showContra.add(Box.createRigidArea(new Dimension(5, 0)));
			showContra.add(new JLabel("Mostrar Contrasena"));
			diag.add(showContra);

			JButton acept = new JButton("Aceptar");
			formatButton(acept);
			acept.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String str1 = "", str2 = "";
					for (char c : contraT.getPassword())
						str1 += c;
					for (char c : rcontraT.getPassword())
						str2 += c;
					if (str1.contentEquals("") || str2.contentEquals("")) {
						ImageIcon icon = new ImageIcon("resources/error.png");
						JOptionPane.showMessageDialog(null, "Faltan algunos datos requeridos", "Cambiar contrasena",
								JOptionPane.DEFAULT_OPTION, icon);
					} else if (str1.contentEquals(str2)) {
						ASUser.getInstance().setPassword(str1);
						updateData();
						ImageIcon icon = new ImageIcon("resources/check.jpg");
						JOptionPane.showMessageDialog(null, "Los datos introducidos son correctos",
								"Cambiar contrasena", JOptionPane.DEFAULT_OPTION, icon);
						diag.dispose();
					} else {
						ImageIcon icon = new ImageIcon("resources/error.png");
						JOptionPane.showMessageDialog(null, "Las contrasenas no coinciden", "Cambiar contrasena",
								JOptionPane.DEFAULT_OPTION, icon);
					}
				}
			});

			JButton cancel = new JButton("Cancelar");
			formatButton(cancel);
			cancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					diag.dispose();
				}
			});

			JPanel buttonsPanel = new JPanel();
			buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
			buttonsPanel.setBackground(Color.WHITE);
			buttonsPanel.add(acept);
			buttonsPanel.add(Box.createRigidArea(new Dimension(7, 0)));
			buttonsPanel.add(cancel);
			
			diag.add(buttonsPanel);
			diag.pack();
			diag.setResizable(false);
			diag.setLocationRelativeTo(null);
			diag.setVisible(true);			
		}
	}
	
	private JComponent userPhoto() {
		JPanel photoPanel = new JPanel();
		photoPanel.setLayout(new BoxLayout(photoPanel, BoxLayout.Y_AXIS));
		photoPanel.setBackground(Color.white);
		photoPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		changePhoto = new JButton("Cambiar foto");
		formatButton(changePhoto);
		changePhoto.setPreferredSize(new Dimension(40, 25));
		changePhoto.setAlignmentX(CENTER_ALIGNMENT);
		
		changePhoto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadFile();
			}
			
		});
		
		JLabel photo = new JLabel();
		photo.setIcon(profilePhoto);
		ImageIcon imgIcon = profilePhoto;
        Image imgEscalada = imgIcon.getImage().getScaledInstance(100,
                100, Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        photo.setIcon(iconoEscalado);
		photo.setAlignmentX(CENTER_ALIGNMENT);
		photoPanel.add(photo);
		
		photoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		photoPanel.add(changePhoto);
		return photoPanel;
	}
	
	private void loadFile() {
		JFileChooser fc = new JFileChooser();
		int value = fc.showOpenDialog(this);
		
		if (value == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			
			try {
				profilePhoto = new ImageIcon(file.getAbsolutePath());
				ASUser.getInstance().setPhoto(profilePhoto);
				updateData();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "An error occurred.");
			}
		}
	}
	
	private void updateData() {
		ASUser.getInstance().setSurname(apeT.getText());
		DTOUser ds = new DTOUser();
		ds.setId(ASUser.getInstance().getId());
		ds.setSurname(ASUser.getInstance().getSurname());
		ds.setName(ASUser.getInstance().getName());
		ds.setEmail(ASUser.getInstance().getEmail());
		ds.setPassword(ASUser.getInstance().getPassword());
		ds.setRole(ASUser.getInstance().getRol());
		SerialBlob imagenBlob = null;
        BufferedImage bi = new BufferedImage (ASUser.getInstance().getPhoto().getIconWidth(), ASUser.getInstance().getPhoto().getIconHeight(), BufferedImage.TYPE_INT_ARGB );
        Graphics bg = bi.getGraphics ();
	    bg.drawImage (ASUser.getInstance().getPhoto().getImage(), 0, 0, null );
	    bg.dispose();
	    ByteArrayOutputStream baos = new ByteArrayOutputStream ();
	      try {
	         ImageIO.write (bi, "png", baos);
	         baos.flush ();
	         baos.close ();
	      } catch ( IOException e ) {
	         e.printStackTrace ();
	      }

	      byte [] imagenByte = baos.toByteArray ();

	      try {
	         imagenBlob = new SerialBlob ( imagenByte );
	      } catch ( SerialException se ) {
	         se.printStackTrace ();
	      } catch ( SQLException sqle ) {
	         sqle.printStackTrace ();
	      }
	      
	      ds.setBlob(imagenBlob);
	    UserWindow.this._ctrl.updateData(1, ds);
	}
}

