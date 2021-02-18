package guiLibrairie;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import daoLibrairie.daoCommande;
import daoLibrairie.daoLigneCommande;
import entitiesLibrairie.Adresse;
import entitiesLibrairie.Commande;
import entitiesLibrairie.LigneCommande;
import entitiesLibrairie.LivreLilia;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import java.beans.*;

public class JFrameCommande extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	private JTextField txtPortHt;
	private JTextField txtTotalHtApresRemise;
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
	private JLabel lblAdrFacId;
	private JLabel lblAdrLivId;
	private float totCdeHtAvRem;
	private float totCdeHtApRem;
	private float totCdeTtc;
	private float remise;
	private JFrameListeClient JFListClt;
	private static int nbRow = 0;
	private static JFrameListeAdresse JFAdr;
	private static JFrameClient JFclt;
	private static JDialogCommentaireCommande JDcde;
	private DecimalFormat df = new DecimalFormat("##.00");
	private DateFormat datef = new SimpleDateFormat( "yyyy.MM.dd");
	private DefaultTableModel dtm = new DefaultTableModel( dtm(), 100);
	private daoCommande daoCde = new daoCommande();
	private daoLigneCommande daoLigCde = new daoLigneCommande();
	private String tabPaiement [] = { "--", "en CB", "en magasin"};
	private DefaultComboBoxModel<String> dcmPai = new DefaultComboBoxModel( tabPaiement);
	private Container parent = this;
	private JFrameCommande thisJF = (JFrameCommande) parent;
	
	public Vector dtm() {
		Vector nomColonne = new Vector<>();
		nomColonne.add( "n° ISBN");
		nomColonne.add( "Livre");
		nomColonne.add( "Tarif unitaire HT");
		nomColonne.add( "Qté");
		nomColonne.add( "Total HT");
		return nomColonne;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameCommande frame = new JFrameCommande( "");
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
			public void windowActivated(WindowEvent e) {
				lblAdrFacId.setText( adrF.getAdresseId());
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
			public void windowActivated(WindowEvent e) {
				lblAdrLivId.setText( adrL.getAdresseId());
				lblNomAdrLiv.setText( adrL.getAdresseNom() + " " + adrL.getAdressePrenom());
				lblRueAdrLiv.setText( adrL.getAdresseNoRue() + ", " + adrL.getAdresseRue());
				lblComplAdrLiv.setText( adrL.getAdresseCompl());
				lblCpVilleAdrLiv.setText( adrL.getAdresseCp() + " - " + adrL.getAdresseVille());
				lblTelAdrLiv.setText( adrL.getAdresseTel());
			}
		});
	}
	
	public void refreshCltLogin( String clientLogin) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtLoginClient.setText( clientLogin);
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
	private JTextField txtNumCde;
	private JTextField txtCdeDate;
	private JTextField txtDateStatut;
	private JTextField txtCdeStatut;
	private JLabel lblIdentiteClt;
	private JTextField txtIdentiteClt;
	private JTextField txtPaiementClt;
	
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

	public Float recupPrixHt(String titre) throws SQLException {
		Float tarif = null;

		myConnexion = Connexion.getInstance();

		String query = "select LIVREPRIXHT from LIVRE where LIVRETITRE = '" + titre.replace( "'", "''") + "';";
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
	
	public String recupIsbn(String titre) throws SQLException {
		String isbn = null;

		myConnexion = Connexion.getInstance();

		String query = "select LIVREISBN from LIVRE where LIVRETITRE = '" + titre.replace( "'", "''") + "';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				isbn = rs.getString( "LIVREISBN");
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return isbn;
	}
	
	
	/*********************************************************************/
	
	
	/**
	 * Create the frame.
	 */
	public JFrameCommande( String etat) {
		
		setTitle("Commande");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 698);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneDroite = new JScrollPane();
		scrollPaneDroite.setBackground(new Color(255, 248, 220));
		scrollPaneDroite.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		scrollPaneDroite.setBounds(17, 141, 790, 274);
		contentPane.add(scrollPaneDroite);
		
		table = new JTable( dtm);
		table.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		table.setBackground(new Color(255, 248, 220));
		scrollPaneDroite.setViewportView(table);
		
		JPanel panelHaut = new JPanel();
		//panelHaut.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.ORANGE));
		panelHaut.setBackground(new Color(255, 248, 220));
		panelHaut.setBounds(17, 6, 790, 136);
		contentPane.add(panelHaut);
		panelHaut.setLayout(null);
		
		JLabel lblLoginClient = new JLabel("Login :");
		lblLoginClient.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLoginClient.setBounds(6, 56, 101, 16);
		panelHaut.add(lblLoginClient);
		lblLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		txtLoginClient = new JTextField();
		txtLoginClient.setBounds(111, 51, 189, 26);
		panelHaut.add(txtLoginClient);
		txtLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtLoginClient.setColumns(10);
		
		JLabel lblNumCde = new JLabel("N° de commande :");
		lblNumCde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumCde.setBounds(547, 6, 122, 16);
		panelHaut.add(lblNumCde);
		lblNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		txtNumCde = new JTextField();
		txtNumCde.setBounds(681, 1, 103, 26);
		panelHaut.add(txtNumCde);
		txtNumCde.setEditable(false);
		txtNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNumCde.setColumns(10);
		
		txtCdeDate = new JTextField();
		txtCdeDate.setBounds(681, 33, 103, 26);
		panelHaut.add(txtCdeDate);
		txtCdeDate.setEditable(false);
		txtCdeDate.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtCdeDate.setColumns(10);
		
		JLabel lblDateDeCde = new JLabel("Date de commande :");
		lblDateDeCde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateDeCde.setBounds(536, 38, 133, 16);
		panelHaut.add(lblDateDeCde);
		lblDateDeCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		txtCdeStatut = new JTextField();
		txtCdeStatut.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtCdeStatut.setEditable(false);
		txtCdeStatut.setColumns(10);
		txtCdeStatut.setBounds(644, 65, 140, 26);
		panelHaut.add(txtCdeStatut);
		
		JLabel lblStatutCde = new JLabel("Statut commnade :");
		lblStatutCde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatutCde.setBounds(512, 70, 130, 16);
		panelHaut.add(lblStatutCde);
		lblStatutCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		txtDateStatut = new JTextField();
		txtDateStatut.setBounds(681, 95, 103, 26);
		panelHaut.add(txtDateStatut);
		txtDateStatut.setEditable(false);
		txtDateStatut.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtDateStatut.setColumns(10);
		
		JLabel lblDateStatut_1 = new JLabel("Date statut :");
		lblDateStatut_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateStatut_1.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblDateStatut_1.setBounds(591, 103, 78, 16);
		panelHaut.add(lblDateStatut_1);
		
		JLabel lblPaiement = new JLabel("Paiement :");
		lblPaiement.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaiement.setBounds(16, 89, 91, 16);
		panelHaut.add(lblPaiement);
		lblPaiement.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		
		JButton btnCommentaireClient = new JButton("");
		btnCommentaireClient.setBounds(342, 29, 101, 76);
		panelHaut.add(btnCommentaireClient);
		btnCommentaireClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDcde = new JDialogCommentaireCommande( txtNumCde.getText());
				JDcde.setLocationRelativeTo( null);
				JDcde.setVisible( true);
			}
		});
		btnCommentaireClient.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnPost.png"));
		
		lblIdentiteClt = new JLabel("Nom & prénom :");
		lblIdentiteClt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentiteClt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblIdentiteClt.setBounds(6, 18, 101, 16);
		panelHaut.add(lblIdentiteClt);
		
		txtIdentiteClt = new JTextField();
		txtIdentiteClt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtIdentiteClt.setColumns(10);
		txtIdentiteClt.setBounds(111, 13, 189, 26);
		panelHaut.add(txtIdentiteClt);
		
		txtPaiementClt = new JTextField();
		txtPaiementClt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtPaiementClt.setColumns(10);
		txtPaiementClt.setBounds(111, 84, 189, 26);
		panelHaut.add(txtPaiementClt);
		
		JPanel panelBas = new JPanel();
		panelBas.setBackground(new Color(255, 248, 220));
		panelBas.setBounds(17, 415, 790, 240);
		contentPane.add(panelBas);
		panelBas.setLayout(null);
		
		JLabel lblAdresseFact = new JLabel("Adresse de facturation");
		lblAdresseFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdresseFact.setBounds(29, 11, 145, 26);
		panelBas.add(lblAdresseFact);
		
		lblNomAdrFact = new JLabel("");
		lblNomAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomAdrFact.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.ORANGE));
		lblNomAdrFact.setBounds(22, 49, 202, 26);
		panelBas.add(lblNomAdrFact);
		
		lblRueAdrFact = new JLabel("");
		lblRueAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRueAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblRueAdrFact.setBounds(22, 87, 202, 26);
		panelBas.add(lblRueAdrFact);
		
		lblComplAdrFact = new JLabel("");
		lblComplAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblComplAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblComplAdrFact.setBounds(22, 123, 202, 26);
		panelBas.add(lblComplAdrFact);
		
		lblCpVilleAdrFact = new JLabel("");
		lblCpVilleAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblCpVilleAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblCpVilleAdrFact.setBounds(22, 161, 202, 26);
		panelBas.add(lblCpVilleAdrFact);
		
		lblTelAdrFact = new JLabel("");
		lblTelAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.ORANGE));
		lblTelAdrFact.setBounds(22, 199, 202, 26);
		panelBas.add(lblTelAdrFact);
		
		JLabel lblAdresseLiv = new JLabel("Adresse de livraison");
		lblAdresseLiv.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdresseLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdresseLiv.setBounds(259, 16, 126, 16);
		panelBas.add(lblAdresseLiv);
		
		lblNomAdrLiv = new JLabel("");
		lblNomAdrLiv.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.ORANGE));
		lblNomAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomAdrLiv.setBounds(249, 49, 202, 26);
		panelBas.add(lblNomAdrLiv);
		
		lblRueAdrLiv = new JLabel("");
		lblRueAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblRueAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRueAdrLiv.setBounds(249, 87, 202, 26);
		panelBas.add(lblRueAdrLiv);
		
		lblComplAdrLiv = new JLabel("");
		lblComplAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblComplAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblComplAdrLiv.setBounds(249, 123, 202, 26);
		panelBas.add(lblComplAdrLiv);
		
		lblCpVilleAdrLiv = new JLabel("");
		lblCpVilleAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblCpVilleAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblCpVilleAdrLiv.setBounds(249, 161, 202, 26);
		panelBas.add(lblCpVilleAdrLiv);
		
		lblTelAdrLiv = new JLabel("");
		lblTelAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.ORANGE));
		lblTelAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelAdrLiv.setBounds(249, 199, 202, 26);
		panelBas.add(lblTelAdrLiv);
		
		JLabel lblPortHt = new JLabel("Frais port HT :");
		lblPortHt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPortHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPortHt.setBounds(603, 11, 103, 16);
		panelBas.add(lblPortHt);
		
		txtPortHt = new JTextField();
		txtPortHt.setEditable(false);
		txtPortHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtPortHt.setBounds(707, 6, 77, 26);
		panelBas.add(txtPortHt);
		txtPortHt.setColumns(10);
		
		JLabel lblTotalHT = new JLabel("Total HT :");
		lblTotalHT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalHT.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTotalHT.setBounds(561, 39, 145, 26);
		panelBas.add(lblTotalHT);
		
		txtTotalHtApresRemise = new JTextField();
		txtTotalHtApresRemise.setEditable(false);
		txtTotalHtApresRemise.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTotalHtApresRemise.setBounds(707, 39, 77, 26);
		panelBas.add(txtTotalHtApresRemise);
		txtTotalHtApresRemise.setColumns(10);
		
		JLabel lblTxTva = new JLabel("Taux TVA :");
		lblTxTva.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxTva.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTxTva.setBounds(603, 70, 103, 26);
		panelBas.add(lblTxTva);
		
		txtTxTva = new JTextField();
		txtTxTva.setEditable(false);
		txtTxTva.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTxTva.setBounds(707, 102, 77, 26);
		panelBas.add(txtTxTva);
		txtTxTva.setColumns(10);
		
		JLabel lblTotalTtc = new JLabel("Total TTC :");
		lblTotalTtc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalTtc.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTotalTtc.setBounds(603, 107, 103, 16);
		panelBas.add(lblTotalTtc);
		
		txtTotalTtc = new JTextField();
		txtTotalTtc.setEditable(false);
		txtTotalTtc.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTotalTtc.setBounds(707, 70, 77, 26);
		panelBas.add(txtTotalTtc);
		txtTotalTtc.setColumns(10);
		
		JButton btnFindAdreLiv = new JButton("");
		btnFindAdreLiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( !txtLoginClient.getText().equals( "")) {
					//JFAdr = new JFrameListeAdresse( txtLoginClient.getText(), thisJF, "Livraison");
					JFAdr.setLocationRelativeTo( null);
					JFAdr.setVisible( true);
					setVisible( false);
				} else {
					JOptionPane.showMessageDialog(null, "Merci de renseigner un login client !", "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnFindAdreLiv.setToolTipText("Carnet d'adresse");
		btnFindAdreLiv.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/map-book.png"));
		btnFindAdreLiv.setBounds(409, 6, 40, 36);
		panelBas.add(btnFindAdreLiv);
		
		JButton btnFindAdreFact = new JButton("");
		btnFindAdreFact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( !txtLoginClient.getText().equals( "")) {
					//JFAdr = new JFrameListeAdresse( txtLoginClient.getText(), thisJF, "Facturation");
					JFAdr.setLocationRelativeTo( null);
					JFAdr.setVisible( true);
					setVisible( false);
				} else {
					JOptionPane.showMessageDialog(null, "Merci de renseigner un login client !", "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnFindAdreFact.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/map-book.png"));
		btnFindAdreFact.setToolTipText("Carnet d'adresse");
		btnFindAdreFact.setBounds(186, 6, 40, 36);
		panelBas.add(btnFindAdreFact);
		if ( etat.equals( "Ajouter")) {
			txtNumCde.setText( daoCde.ajoutIdCommande());
		}
		if ( etat.equals( "Ajouter")) {
			Date d = new Date( Calendar.getInstance().getTime().getTime());
			txtCdeDate.setText( datef.format( d));
		}
		
		lblAdrFacId = new JLabel("");
		lblAdrFacId.setForeground(new Color(255, 255, 224));
		lblAdrFacId.setBackground(new Color(255, 255, 224));
		lblAdrFacId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdrFacId.setBounds(39, 112, 152, 20);
		panelBas.add(lblAdrFacId);
		
		lblAdrLivId = new JLabel("");
		lblAdrLivId.setForeground(new Color(255, 255, 224));
		lblAdrLivId.setBackground(new Color(255, 255, 224));
		lblAdrLivId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdrLivId.setBounds(247, 117, 152, 20);
		panelBas.add(lblAdrLivId);
		
	}
}