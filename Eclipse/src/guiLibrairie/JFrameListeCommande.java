package guiLibrairie;

import javax.swing.*;
import javax.swing.border.*;

import daoLibrairie.*;
import entitiesLibrairie.Client;
import entitiesLibrairie.Commande;

import javax.swing.table.*;

import connexionLibrairie.Connexion;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class JFrameListeCommande extends JFrame {

	private static JFrameListeCommande frame;
	private JPanel contentPane;
	private JTextField txtNumCde;
	private JTextField txtDateCde;
	private JComboBox cmbBxStatutLivre;
	private Vector<Commande> vStatut = new Vector();
	private Vector<String> vLivre = new Vector<>();
	private daoCommande daoCde = new daoCommande();
	private JTable table;
	private JTextField txtLoginClient;
	private JFrameLigneCommande JFlc;
	private JFrameCommande JFcde;

	
	/**
	 * Méthode à supprimer une fois la mise en commun effectué
	 */
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;

	
	public Vector<String> vectorListLivre() throws SQLException {
		Vector<String> vLiv = new Vector<>();

		myConnexion = Connexion.getInstance();
		
		String query =	"select * from LIVRE order by LIVRETITRE;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				vLiv.add( rs.getString( "LIVRETITRE"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vLiv;
	}

	public DefaultComboBoxModel<String> listeLivre() throws SQLException {

		return new DefaultComboBoxModel<>( vectorListLivre());
	}
	/*
	 * 
	 */
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new JFrameListeCommande( "");
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
	public JFrameListeCommande( String clientLogin) {
		
		setTitle("Liste des commandes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 575);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRechercherPar = new JLabel("Rechercher par");
		lblRechercherPar.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercherPar.setBounds(18, 18, 142, 33);
		contentPane.add(lblRechercherPar);
		
		JLabel lblLogin = new JLabel("Login client :");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLogin.setBounds(54, 54, 82, 16);
		contentPane.add(lblLogin);
		
		txtLoginClient = new JTextField();
		txtLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtLoginClient.setColumns(10);
		txtLoginClient.setBounds(137, 52, 122, 20);
		contentPane.add(txtLoginClient);
		
		JLabel lblNumCde = new JLabel("n° de commande :");
		lblNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNumCde.setBounds(302, 54, 115, 16);
		contentPane.add(lblNumCde);
		
		txtNumCde = new JTextField();
		txtNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNumCde.setColumns(10);
		txtNumCde.setBounds(411, 52, 122, 20);
		contentPane.add(txtNumCde);
		
		JLabel lblDateCde = new JLabel("Date commande :");
		lblDateCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblDateCde.setBounds(28, 94, 115, 16);
		contentPane.add(lblDateCde);
		
		txtDateCde = new JTextField();
		txtDateCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtDateCde.setColumns(10);
		txtDateCde.setBounds(137, 92, 122, 20);
		contentPane.add(txtDateCde);
		
		JLabel lblStatutCommande = new JLabel("Statut commande :");
		lblStatutCommande.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblStatutCommande.setBounds(295, 94, 122, 16);
		contentPane.add(lblStatutCommande);
		
		try {
			vStatut = daoCde.vectorCBStatutCde();
			//cmbBxStatutLivre.setModel( daoCde.statutCommande());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cmbBxStatutLivre = new JComboBox( vStatut);
		cmbBxStatutLivre.setSelectedIndex(-1);
		cmbBxStatutLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBxStatutLivre.setBounds(411, 90, 326, 27);
		contentPane.add(cmbBxStatutLivre);
		
		try {
			vLivre = vectorListLivre();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		panel.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(18, 146, 737, 311);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 248, 220));
		scrollPane.setBounds(6, 6, 725, 299);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(new Color(255, 248, 220));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView( table);
		table.setShowGrid( true);
		table.setShowHorizontalLines( true);
		table.setShowVerticalLines( true);
		table.getTableHeader().setBounds(6, 6, 725, 299);
		table.getTableHeader().setVisible( true);
		try {
			if ( !clientLogin.equals( "")) {
				table.setModel( daoCde.listeCommandeByLogin( clientLogin));
			} else {
				table.setModel( daoCde.listeCommande());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		table.getColumnModel().getColumn( 3).setPreferredWidth( 250);
		
		JButton btnLoupe = new JButton("");
		btnLoupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					if ( !txtLoginClient.getText().equals( "")) {
						String log = txtLoginClient.getText();
						daoCde.findCommandeByLogin( log);
						table.setModel( daoCde.listeCommandeByLogin( log));
						
					} else if ( !txtNumCde.getText().equals( "")) {
						String nCde = txtNumCde.getText();
						daoCde.findCommandeByCdeNum( nCde);
						table.setModel( daoCde.listeCommandeByCdeNum( nCde));
						
					} else if ( !txtDateCde.getText().equals( "")) {
						//String 
						
					} else if ( txtLoginClient.getText().equals( "") && txtNumCde.getText().equals( "") && txtDateCde.getText().equals( "")) {
						String statut = (String) cmbBxStatutLivre.getSelectedItem();
						daoCde.findCommandeByStatut( statut);
						table.setModel( daoCde.listeCommandeByStatut( statut));
						
					} else {
						table.setModel( daoCde.listeCommande());
					}
					txtLoginClient.setText( "");
					txtNumCde.setText( "");
					txtDateCde.setText( "");
					cmbBxStatutLivre.setSelectedIndex( -1);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnLoupe.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupe.setBounds(618, 18, 55, 55);
		contentPane.add(btnLoupe);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFlc = new JFrameLigneCommande( "Ajouter");
				JFlc.setLocationRelativeTo( null);
				JFlc.setVisible( true);
				
			}
		});
		btnAjouter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAjouter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAjouter.setBounds(42, 484, 173, 41);
		contentPane.add(btnAjouter);
		
		JButton btnConsulter = new JButton("Consulter");
		btnConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cdeSelect = (String) table.getValueAt( table.getSelectedRow(), 0);
				JFcde = new JFrameCommande( cdeSelect, "Consulter");
				JFcde.setLocationRelativeTo( null);
				JFcde.setVisible( true);
			}
		});
		btnConsulter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnConsulter.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnConsulter.setBounds(290, 484, 173, 41);
		contentPane.add(btnConsulter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cdeSelect = (String) table.getValueAt( table.getSelectedRow(), 0);
				JFcde = new JFrameCommande( cdeSelect, "Modifier");
				JFcde.setLocationRelativeTo( null);
				JFcde.setVisible( true);
			}
		});
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.setBounds(564, 484, 173, 41);
		contentPane.add(btnModifier);
		
		JButton btnRefreshAuteur = new JButton("");
		btnRefreshAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ( !clientLogin.equals( "")) {
						table.setModel( daoCde.listeCommandeByLogin( clientLogin));
					} else {
						table.setModel( daoCde.listeCommande());
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnRefreshAuteur.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/refresh24px.png"));
		btnRefreshAuteur.setBounds(685, 18, 55, 55);
		contentPane.add(btnRefreshAuteur);
	}
}
