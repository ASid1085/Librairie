package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoLibrairie.daoAdresse;
import entitiesLibrairie.Adresse;
import entitiesLibrairie.Client;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JFrameAdresse extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtNumRue;
	private JTextField txtRue;
	private JTextField txtComplement;
	private JTextField txtCP;
	private JTextField txtVille;
	private JTextField txtPays;
	private JTextField txtTelephone;
	private JTextField txtCode;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JRadioButton rdbtnLivraison;
	private JRadioButton rdbtnFacturation;
	private daoAdresse daoAdr = new daoAdresse();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameAdresse frame = new JFrameAdresse( "", null, "", "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameAdresse( String clientLogin, JFrameListeAdresse frameListeAd, String id, String etat) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Adresse adr = new Adresse();
	
				if ( !id.equals( "")) {
					try {
						if ( etat.equals( "Livraison")) {
							adr = daoAdr.findAdresseLivById( id);
						}
						if ( etat.equals( "Facturation")) {
							adr = daoAdr.findAdresseFacById( id);
						}
						txtNom.setText( adr.getAdresseNom());
						txtPrenom.setText( adr.getAdressePrenom());
						txtNumRue.setText( adr.getAdresseNoRue());
						txtRue.setText( adr.getAdresseRue());
						txtComplement.setText( adr.getAdresseCompl());
						txtCP.setText( adr.getAdresseCp());
						txtVille.setText( adr.getAdresseVille());
						txtPays.setText( adr.getAdressePays());
						txtTelephone.setText( adr.getAdresseTel());
						txtCode.setText( adr.getAdresseCode());
					} catch (SQLException ex) {
						ex.printStackTrace();
					}	
				}
				if ( !id.equals( "")) {
					btnAjouter.setEnabled( false);
					rdbtnFacturation.setVisible( false);
					rdbtnLivraison.setVisible( false);
				} else {
					btnModifier.setEnabled( false);
				}
			}
		});
		
		setTitle("Adresse");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 493, 547);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnLivraison = new JRadioButton("Livraison");
		rdbtnLivraison.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		rdbtnLivraison.setBounds(18, 26, 104, 23);
		contentPane.add(rdbtnLivraison);
		
		rdbtnFacturation = new JRadioButton("Facturation");
		rdbtnFacturation.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		rdbtnFacturation.setBounds(134, 25, 104, 23);
		contentPane.add(rdbtnFacturation);
		
		ButtonGroup btnGroup =	new ButtonGroup();
		btnGroup.add( rdbtnLivraison);
		btnGroup.add( rdbtnFacturation);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setEnabled(false);
		lblNom.setForeground(Color.BLACK);
		lblNom.setFont(lblNom.getFont().deriveFont(11f));
		lblNom.setBounds(18, 68, 61, 16);
		contentPane.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setBackground(new Color(255, 248, 220));
		txtNom.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtNom.setBounds(18, 84, 220, 30);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Prénom :");
		lblPrenom.setEnabled(false);
		lblPrenom.setFont(new Font("Avenir Next", Font.PLAIN, 11));
		lblPrenom.setForeground(Color.BLACK);
		lblPrenom.setBounds(250, 68, 61, 16);
		contentPane.add(lblPrenom);
		
		JLabel lblNumRue = new JLabel("N° rue :");
		lblNumRue.setEnabled(false);
		lblNumRue.setForeground(Color.BLACK);
		lblNumRue.setFont(lblNumRue.getFont().deriveFont(11f));
		lblNumRue.setBounds(18, 136, 61, 16);
		contentPane.add(lblNumRue);
		
		JLabel lblRue = new JLabel("Nom de rue :");
		lblRue.setEnabled(false);
		lblRue.setForeground(Color.BLACK);
		lblRue.setFont(lblRue.getFont().deriveFont(11f));
		lblRue.setBounds(78, 136, 104, 16);
		contentPane.add(lblRue);
		
		JLabel lblComplement = new JLabel("Complement :");
		lblComplement.setEnabled(false);
		lblComplement.setForeground(Color.BLACK);
		lblComplement.setFont(lblComplement.getFont().deriveFont(11f));
		lblComplement.setBounds(41, 191, 111, 16);
		contentPane.add(lblComplement);
		
		JLabel lblCP = new JLabel("Code postal :");
		lblCP.setEnabled(false);
		lblCP.setForeground(Color.BLACK);
		lblCP.setFont(lblCP.getFont().deriveFont(11f));
		lblCP.setBounds(18, 249, 88, 16);
		contentPane.add(lblCP);
		
		JLabel lblVille = new JLabel("Ville :");
		lblVille.setEnabled(false);
		lblVille.setForeground(Color.BLACK);
		lblVille.setFont(lblVille.getFont().deriveFont(11f));
		lblVille.setBounds(134, 249, 88, 16);
		contentPane.add(lblVille);
		
		JLabel lblPays = new JLabel("Pays :");
		lblPays.setEnabled(false);
		lblPays.setForeground(Color.BLACK);
		lblPays.setFont(lblPays.getFont().deriveFont(11f));
		lblPays.setBounds(18, 308, 88, 16);
		contentPane.add(lblPays);
		
		JLabel lblTelephone = new JLabel("N° de téléphone :");
		lblTelephone.setEnabled(false);
		lblTelephone.setForeground(Color.BLACK);
		lblTelephone.setFont(lblTelephone.getFont().deriveFont(11f));
		lblTelephone.setBounds(18, 381, 111, 16);
		contentPane.add(lblTelephone);
		
		JLabel lblCode = new JLabel("Nommer cette adresse :");
		lblCode.setEnabled(false);
		lblCode.setForeground(Color.BLACK);
		lblCode.setFont(new Font("Avenir Next", Font.PLAIN, 11));
		lblCode.setBounds(250, 381, 174, 16);
		contentPane.add(lblCode);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtPrenom.setBackground(new Color(255, 248, 220));
		txtPrenom.setBounds(250, 84, 225, 30);
		contentPane.add(txtPrenom);
		
		txtNumRue = new JTextField();
		txtNumRue.setColumns(10);
		txtNumRue.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtNumRue.setBackground(new Color(255, 248, 220));
		txtNumRue.setBounds(18, 155, 48, 30);
		contentPane.add(txtNumRue);
		
		txtRue = new JTextField();
		txtRue.setColumns(10);
		txtRue.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtRue.setBackground(new Color(255, 248, 220));
		txtRue.setBounds(78, 156, 395, 30);
		contentPane.add(txtRue);
		
		txtComplement = new JTextField();
		txtComplement.setColumns(10);
		txtComplement.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtComplement.setBackground(new Color(255, 248, 220));
		txtComplement.setBounds(41, 207, 432, 30);
		contentPane.add(txtComplement);
		
		txtCP = new JTextField();
		txtCP.setColumns(10);
		txtCP.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtCP.setBackground(new Color(255, 248, 220));
		txtCP.setBounds(18, 265, 104, 30);
		contentPane.add(txtCP);
		
		txtVille = new JTextField();
		txtVille.setColumns(10);
		txtVille.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtVille.setBackground(new Color(255, 248, 220));
		txtVille.setBounds(134, 266, 339, 30);
		contentPane.add(txtVille);
		
		txtPays = new JTextField();
		txtPays.setColumns(10);
		txtPays.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtPays.setBackground(new Color(255, 248, 220));
		txtPays.setBounds(18, 324, 455, 30);
		contentPane.add(txtPays);
		
		txtTelephone = new JTextField();
		txtTelephone.setColumns(10);
		txtTelephone.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtTelephone.setBackground(new Color(255, 248, 220));
		txtTelephone.setBounds(18, 398, 220, 30);
		contentPane.add(txtTelephone);
		
		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		txtCode.setBackground(new Color(255, 248, 220));
		txtCode.setBounds(249, 399, 225, 30);
		contentPane.add(txtCode);
		
		JLabel lblAdresseId = new JLabel("");
		if ( !id.equals( "")) {
			lblAdresseId.setText( id);
		} else {
			lblAdresseId.setText( daoAdr.ajoutIdAdresse());
		}
		lblAdresseId.setEnabled(false);
		lblAdresseId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdresseId.setBounds(392, 6, 83, 30);
		contentPane.add(lblAdresseId);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adresse adr = new Adresse( lblAdresseId.getText(), txtCode.getText().replace("'", "''"), txtNom.getText().replace("'", "''"),
										   txtPrenom.getText().replace("'", "''"), txtNumRue.getText().replace("'", "''"), txtRue.getText().replace("'", "''"),
										   txtComplement.getText().replace("'", "''"), txtCP.getText(), txtVille.getText().replace("'", "''"),
										   txtPays.getText().replace("'", "''"), txtTelephone.getText().replace("'", "''"), "");
				try {
					daoAdr.addAdresse( adr);
					if ( rdbtnLivraison.isSelected()) {
						daoAdr.attribAdresseLivraison( clientLogin, adr);
					} else if ( rdbtnFacturation.isSelected()) {
						daoAdr.attribAdresseFacturation( clientLogin, adr);
					} else {
						JOptionPane.showMessageDialog( contentPane, "Merci de sélectionner un type d'adresse !", "Champs non sélectionné", JOptionPane.WARNING_MESSAGE);
					}
					frameListeAd.refreshAdresse( adr);
					frameListeAd.repaint();
					frameListeAd.setVisible( true);
					
					if ( etat.equals( "Livraison")) {
						daoAdr.lierAdLivClt( adr, clientLogin, etat);
					}
					if ( etat.equals( "Facturation")) {
						daoAdr.lierAdFacClt( adr, clientLogin, etat);
					}
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAjouter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAjouter.setIcon(new ImageIcon(JFrameAdresse.class.getResource("/icon/double-checked32px.png")));
		btnAjouter.setBounds(78, 455, 123, 49);
		contentPane.add(btnAjouter);
		
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Adresse adr = new Adresse( lblAdresseId.getText(), txtCode.getText().replace("'", "''"), txtNom.getText().replace("'", "''"),
						txtPrenom.getText().replace("'", "''"), txtNumRue.getText().replace("'", "''"), txtRue.getText().replace("'", "''"),
						txtComplement.getText().replace("'", "''"), txtCP.getText().replace("'", "''"), txtVille.getText().replace("'", "''"),
						txtPays.getText().replace("'", "''"), txtTelephone.getText().replace("'", "''"), "");
				try {
					daoAdr.ModifierAdresse( adr);
					frameListeAd.refreshAdresse( adr);
					frameListeAd.repaint();
					frameListeAd.setVisible( true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setIcon(new ImageIcon(JFrameAdresse.class.getResource("/icon/double-checked32px.png")));
		btnModifier.setBounds(263, 455, 123, 49);
		contentPane.add(btnModifier);
		
	}
}
