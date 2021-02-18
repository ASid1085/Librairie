package guiLibrairie;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Adresse;
import entitiesLibrairie.Client;

import java.awt.event.*;
import java.sql.*;
import java.sql.Statement;
import java.util.Vector;
import java.beans.*;

public class JFrameLigneCommande extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtPortHt;
	private JTextField txtRemiseHt;
	private JTextField txtTotalHt;
	private JTextField txtTxTva;
	private JTextField txtTotalTtc;
	private JTextField txtLoginClient;
	private JLabel lblNomAdrFact;
	private JLabel lblRueAdrFact;
	private JLabel lblComplAdrFact;
	private JLabel lblCpVilleAdrFact;
	private JLabel lblTelAdrFact;
	private JLabel lblNomAdrLiv;
	private JLabel lblRueAdrLiv;
	private JLabel lblComplAdrLiv;
	private JLabel lblCpVilleAdrLiv;
	private JLabel lblTelAdrLiv;
	private JFrameListeClient JFListClt;
	private static JFrameListeAdresse JFAdr;
	private JFrameClient JFclt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLigneCommande frame = new JFrameLigneCommande();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refreshAdresseFact( Adresse adrF) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {		
				lblNomAdrFact.setText( adrF.getAdresseNom() + " " + adrF.getAdressePrenom());
				lblRueAdrFact.setText( adrF.getAdresseNoRue() + ", " + adrF.getAdresseRue());
				lblComplAdrFact.setText( adrF.getAdresseCompl());
				lblCpVilleAdrFact.setText( adrF.getAdresseCp() + " - " + adrF.getAdresseVille());
				lblTelAdrFact.setText( adrF.getAdresseTel());
			}
		});
	}
	
	public void refreshAdresseLiv( Adresse adrL) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {		
				lblNomAdrLiv.setText( adrL.getAdresseNom() + " " + adrL.getAdressePrenom());
				lblRueAdrLiv.setText( adrL.getAdresseNoRue() + ", " + adrL.getAdresseRue());
				lblComplAdrLiv.setText( adrL.getAdresseCompl());
				lblCpVilleAdrLiv.setText( adrL.getAdresseCp() + " - " + adrL.getAdresseVille());
				lblTelAdrLiv.setText( adrL.getAdresseTel());
			}
		});
	}
	
	/**
	 * Méthode à supprimer une fois la mise en commun effectué
	 */
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;
	private JTextField textField;
	
	public Vector<String> vectorListLivre() throws SQLException {
		Vector vLiv = new Vector();

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

	public DefaultComboBoxModel listeLivre() throws SQLException {

		return new DefaultComboBoxModel( vectorListLivre());
	}

	public Float recupPrixHt(String titre) throws SQLException {
		Float tarif = null;

		myConnexion = Connexion.getInstance();

		String query = "select LIVREPRIXHT from LIVRE where LIVRETITRE = '" + titre + "';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				tarif = rs.getFloat( "LIVREPRIXHT");
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return tarif;
	}
	
	public DefaultTableModel dtm() {
		Vector nomColonne = new Vector<>();
		nomColonne.add( "Livre");
		nomColonne.add( "Qté");
		nomColonne.add( "Tarif unitaire HT");
		nomColonne.add( "Total HT");
		return new DefaultTableModel( nomColonne, 100);
	}
	
	/*********************************************************************/
	
	
	/**
	 * Create the frame.
	 */
	public JFrameLigneCommande() {
		
		setTitle("Détail de commande");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 828, 744);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelHaut = new JPanel();
		panelHaut.setBackground(new Color(255, 248, 220));
		//panelHaut.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		panelHaut.setBounds(17, 6, 789, 57);
		contentPane.add(panelHaut);
		panelHaut.setLayout(null);
		
		JLabel lblLivre = new JLabel("Livre :");
		lblLivre.setBounds(6, 22, 46, 16);
		panelHaut.add(lblLivre);
		
		JLabel lblPrixHt = new JLabel("");
		lblPrixHt.setBounds(471, 18, 77, 20);
		panelHaut.add(lblPrixHt);
		
		JComboBox cmbBoxLivre = new JComboBox();
		try {
			cmbBoxLivre.setModel( listeLivre());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		cmbBoxLivre.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String liv = (String) cmbBoxLivre.getSelectedItem();
				try {
					lblPrixHt.setText( String.valueOf( recupPrixHt( liv)));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		cmbBoxLivre.setBounds(51, 18, 345, 27);
		panelHaut.add(cmbBoxLivre);
		
		JLabel lblPrixUnitHt = new JLabel("Tarif HT :");
		
		lblPrixUnitHt.setBounds(408, 18, 67, 20);
		panelHaut.add(lblPrixUnitHt);
		
		JLabel lblQte = new JLabel("Quantité :");
		lblQte.setBounds(560, 22, 67, 16);
		panelHaut.add(lblQte);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(639, 17, 77, 26);
		panelHaut.add(spinner);
		
		JScrollPane scrollPaneDroite = new JScrollPane();
		scrollPaneDroite.setBackground(new Color(255, 248, 220));
		scrollPaneDroite.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.ORANGE));
		scrollPaneDroite.setBounds(53, 62, 715, 312);
		contentPane.add(scrollPaneDroite);
		
		table = new JTable( dtm());
		table.setEnabled(false);
		table.setBackground(new Color(255, 248, 220));
		table.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.ORANGE));
		scrollPaneDroite.setViewportView(table);
		
		JPanel panelDroite = new JPanel();
		panelDroite.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.ORANGE));
		panelDroite.setBackground(new Color(255, 248, 220));
		panelDroite.setBounds(53, 375, 715, 43);
		contentPane.add(panelDroite);
		panelDroite.setLayout(null);
		
		JButton btnRemoveLivre = new JButton("");
		btnRemoveLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveLivre.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/moins12px.png"));
		btnRemoveLivre.setToolTipText("Supprimer un livre de la commande");
		btnRemoveLivre.setBounds(618, 6, 40, 32);
		panelDroite.add(btnRemoveLivre);
		
		JButton btnRemoveAll = new JButton("");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveAll.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/delete.png"));
		btnRemoveAll.setToolTipText("Supprimer tous les livres de la commande");
		btnRemoveAll.setBounds(669, 6, 40, 32);
		panelDroite.add(btnRemoveAll);
		
		JLabel lblPaiement = new JLabel("Paiement :");
		lblPaiement.setBounds(6, 21, 67, 16);
		panelDroite.add(lblPaiement);
		lblPaiement.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		JPanel panelBas = new JPanel();
		panelBas.setBackground(new Color(255, 248, 220));
		//panelBas.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		panelBas.setBounds(17, 415, 790, 287);
		contentPane.add(panelBas);
		panelBas.setLayout(null);
		
		JLabel lblLoginClient = new JLabel("Login client :");
		lblLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLoginClient.setBounds(16, 17, 82, 16);
		panelBas.add(lblLoginClient);
		
		txtLoginClient = new JTextField();
		txtLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtLoginClient.setBounds(101, 12, 189, 26);
		panelBas.add(txtLoginClient);
		txtLoginClient.setColumns(10);
		
		JLabel lblAdresseFact = new JLabel("Adresse de facturation");
		lblAdresseFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdresseFact.setBounds(16, 70, 152, 16);
		panelBas.add(lblAdresseFact);
		
		lblNomAdrFact = new JLabel("");
		lblNomAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomAdrFact.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.ORANGE));
		lblNomAdrFact.setBounds(16, 89, 202, 26);
		panelBas.add(lblNomAdrFact);
		
		lblRueAdrFact = new JLabel("");
		lblRueAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRueAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblRueAdrFact.setBounds(16, 127, 202, 26);
		panelBas.add(lblRueAdrFact);
		
		lblComplAdrFact = new JLabel("");
		lblComplAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblComplAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblComplAdrFact.setBounds(17, 168, 202, 26);
		panelBas.add(lblComplAdrFact);
		
		lblCpVilleAdrFact = new JLabel("");
		lblCpVilleAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblCpVilleAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblCpVilleAdrFact.setBounds(16, 213, 202, 26);
		panelBas.add(lblCpVilleAdrFact);
		
		lblTelAdrFact = new JLabel("");
		lblTelAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.ORANGE));
		lblTelAdrFact.setBounds(16, 251, 202, 26);
		panelBas.add(lblTelAdrFact);
		
		JLabel lblAdresseLiv = new JLabel("Adresse de livraison");
		lblAdresseLiv.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresseLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdresseLiv.setBounds(299, 70, 152, 16);
		panelBas.add(lblAdresseLiv);
		
		lblNomAdrLiv = new JLabel("");
		lblNomAdrLiv.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.ORANGE));
		lblNomAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomAdrLiv.setBounds(249, 89, 202, 26);
		panelBas.add(lblNomAdrLiv);
		
		lblRueAdrLiv = new JLabel("");
		lblRueAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblRueAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRueAdrLiv.setBounds(249, 127, 202, 26);
		panelBas.add(lblRueAdrLiv);
		
		lblComplAdrLiv = new JLabel("");
		lblComplAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblComplAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblComplAdrLiv.setBounds(248, 168, 202, 26);
		panelBas.add(lblComplAdrLiv);
		
		lblCpVilleAdrLiv = new JLabel("");
		lblCpVilleAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblCpVilleAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblCpVilleAdrLiv.setBounds(249, 213, 202, 26);
		panelBas.add(lblCpVilleAdrLiv);
		
		lblTelAdrLiv = new JLabel("");
		lblTelAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.ORANGE));
		lblTelAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelAdrLiv.setBounds(249, 251, 202, 26);
		panelBas.add(lblTelAdrLiv);
		
		JButton btnValiderLigCde = new JButton("");
		btnValiderLigCde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnValiderLigCde.setToolTipText("Valider la commande");
		btnValiderLigCde.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnGestCommande.png"));
		btnValiderLigCde.setBounds(717, 223, 67, 54);
		panelBas.add(btnValiderLigCde);
		
		JLabel lblPortHt = new JLabel("Frais port HT :");
		lblPortHt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPortHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPortHt.setBounds(557, 17, 103, 16);
		panelBas.add(lblPortHt);
		
		txtPortHt = new JTextField();
		txtPortHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtPortHt.setBounds(675, 12, 77, 26);
		panelBas.add(txtPortHt);
		txtPortHt.setColumns(10);
		
		JLabel lblRemise = new JLabel("Remise % :");
		lblRemise.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRemise.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRemise.setBounds(564, 84, 96, 16);
		panelBas.add(lblRemise);
		
		txtRemiseHt = new JTextField();
		txtRemiseHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtRemiseHt.setBounds(675, 79, 77, 26);
		panelBas.add(txtRemiseHt);
		txtRemiseHt.setColumns(10);
		
		JLabel lblTotalHT = new JLabel("Total HT après remise :");
		lblTotalHT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalHT.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTotalHT.setBounds(515, 116, 145, 26);
		panelBas.add(lblTotalHT);
		
		txtTotalHt = new JTextField();
		txtTotalHt.setEnabled(false);
		txtTotalHt.setEditable(false);
		txtTotalHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTotalHt.setBounds(675, 116, 77, 26);
		panelBas.add(txtTotalHt);
		txtTotalHt.setColumns(10);
		
		JLabel lblTxTva = new JLabel("Taux TVA :");
		lblTxTva.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxTva.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTxTva.setBounds(557, 149, 103, 26);
		panelBas.add(lblTxTva);
		
		txtTxTva = new JTextField();
		txtTxTva.setEnabled(false);
		txtTxTva.setEditable(false);
		txtTxTva.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTxTva.setBounds(675, 149, 77, 26);
		panelBas.add(txtTxTva);
		txtTxTva.setColumns(10);
		
		JLabel lblTotalTtc = new JLabel("Total TTC :");
		lblTotalTtc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalTtc.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTotalTtc.setBounds(557, 190, 103, 16);
		panelBas.add(lblTotalTtc);
		
		txtTotalTtc = new JTextField();
		txtTotalTtc.setEnabled(false);
		txtTotalTtc.setEditable(false);
		txtTotalTtc.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTotalTtc.setBounds(675, 185, 77, 26);
		panelBas.add(txtTotalTtc);
		txtTotalTtc.setColumns(10);
		
		JButton btnAddLivre = new JButton("");
		btnAddLivre.setToolTipText("Ajouter un livre à la commande");
		btnAddLivre.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/plus32px.png"));
		btnAddLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sLivre = (String) cmbBoxLivre.getSelectedItem();
				int iQte = (int) spinner.getValue();
				try {
					float prixHt = recupPrixHt( sLivre);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddLivre.setBounds(728, 6, 54, 44);
		panelHaut.add(btnAddLivre);
		
		JButton btnFindAdreLiv = new JButton("");
		btnFindAdreLiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( !txtLoginClient.getText().equals( "")) {
					JFAdr = new JFrameListeAdresse( txtLoginClient.getText());
					JFAdr.setLocationRelativeTo( JFAdr.getParent());
					JFAdr.setVisible( true);
					//setVisible( false);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Merci de renseigner un login client !", "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnFindAdreLiv.setToolTipText("Carnet d'adresse");
		btnFindAdreLiv.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/map-book.png"));
		btnFindAdreLiv.setBounds(214, 50, 40, 36);
		panelBas.add(btnFindAdreLiv);
		
		JButton btnFindClt = new JButton("");
		btnFindClt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFListClt = new JFrameListeClient();
				JFListClt.setLocationRelativeTo( JFListClt.getParent());
				JFListClt.setVisible( true);
			}
		});
		btnFindClt.setToolTipText("Rechercher un client");
		btnFindClt.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/verify16px.png"));
		btnFindClt.setBounds(312, 12, 40, 32);
		panelBas.add(btnFindClt);
		
		JButton btnAddClt = new JButton("");
		btnAddClt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFclt = new JFrameClient("", "Ajouter");
				JFclt.setLocationRelativeTo( JFclt.getParent());
				JFclt.setVisible( true);
			}
		});
		btnAddClt.setToolTipText("Ajouter un nouveau client");
		btnAddClt.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/plus12px.png"));
		btnAddClt.setBounds(361, 11, 40, 32);
		panelBas.add(btnAddClt);
		
		JLabel lblPaiementType = new JLabel("");
		lblPaiementType.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPaiementType.setBounds(528, 261, 145, 16);
		panelBas.add(lblPaiementType);
		
		JLabel lblTotalHtAvant = new JLabel("Total HT avant remise :");
		lblTotalHtAvant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalHtAvant.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTotalHtAvant.setBounds(515, 45, 145, 26);
		panelBas.add(lblTotalHtAvant);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(675, 45, 77, 26);
		panelBas.add(textField);
	}
}
