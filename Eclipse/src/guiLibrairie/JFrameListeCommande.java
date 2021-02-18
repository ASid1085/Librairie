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
	private JComboBox cmbBxStatutLivre;;
	private daoCommande daoCde = new daoCommande();
	private JTable table;
	private JTextField txtLoginClient;
	private JFrameLigneCommande JFlc;

	
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
		lblLogin.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLogin.setBounds(28, 54, 82, 16);
		contentPane.add(lblLogin);
		
		txtLoginClient = new JTextField();
		txtLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtLoginClient.setColumns(10);
		txtLoginClient.setBounds(107, 52, 122, 20);
		contentPane.add(txtLoginClient);
		
		JLabel lblNumCde = new JLabel("n° de commande :");
		lblNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNumCde.setBounds(38, 82, 115, 16);
		contentPane.add(lblNumCde);
		
		txtNumCde = new JTextField();
		txtNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNumCde.setColumns(10);
		txtNumCde.setBounds(149, 80, 100, 20);
		contentPane.add(txtNumCde);
		
		JLabel lblDateCde = new JLabel("Date commande :");
		lblDateCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblDateCde.setBounds(261, 82, 115, 16);
		contentPane.add(lblDateCde);
		
		txtDateCde = new JTextField();
		txtDateCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtDateCde.setColumns(10);
		txtDateCde.setBounds(370, 80, 100, 20);
		contentPane.add(txtDateCde);
		
		JLabel lblStatutCommande = new JLabel("Statut commande :");
		lblStatutCommande.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblStatutCommande.setBounds(80, 110, 122, 16);
		contentPane.add(lblStatutCommande);
		
		cmbBxStatutLivre = new JComboBox();
		try {
			cmbBxStatutLivre.setModel( daoCde.statutCommande());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cmbBxStatutLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBxStatutLivre.setBounds(195, 106, 326, 27);
		contentPane.add(cmbBxStatutLivre);
		
		JLabel lblTitreLivre = new JLabel("Titre livre :");
		lblTitreLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTitreLivre.setBounds(241, 54, 70, 16);
		contentPane.add(lblTitreLivre);
		
		JComboBox cmbBxTitreLivre = new JComboBox();
		try {
			cmbBxTitreLivre.setModel( listeLivre());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		cmbBxTitreLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBxTitreLivre.setBounds(315, 50, 362, 27);
		contentPane.add(cmbBxTitreLivre);
		
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
						
					} else if ( txtLoginClient.getText().equals( "") && txtNumCde.getText().equals( "") && txtDateCde.getText().equals( "")) {
						String statut = (String) cmbBxStatutLivre.getSelectedItem();
						daoCde.findCommandeByStatut( statut);
						table.setModel( daoCde.listeCommandeByStatut( statut));
					} else {
						table.setModel( daoCde.listeCommande());
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnLoupe.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupe.setBounds(700, 58, 55, 55);
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
		btnConsulter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnConsulter.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnConsulter.setBounds(290, 484, 173, 41);
		contentPane.add(btnConsulter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.setBounds(564, 484, 173, 41);
		contentPane.add(btnModifier);
	}
}
