package guiLibrairie;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import daoLibrairie.*;
import entitiesLibrairie.*;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.text.*;
import java.util.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class JFrameCommande extends JFrame {
	
	private JPanel contentPane;
	private JTable table;
	private JTextField txtPortHt = new JTextField( "");
	private JTextField txtHT = new JTextField( "");
	private JTextField txtTTC = new JTextField( "");
	private JTextField txtTVA = new JTextField( "");
	private JTextField txtLoginClient = new JTextField( "");
	private JTextField txtNumCde = new JTextField( "");
	private JTextField txtCdeDate = new JTextField( "");
	private JTextField txtDateStatut = new JTextField( "");
	private JLabel lblIdentiteClt;
	private JTextField txtIdentiteClt = new JTextField( "");
	private JTextField txtPaiementClt = new JTextField( "");
	private DefaultTableModel dmtCde;
	private JLabel lblNomAdrFact = new JLabel( "");
	private JLabel lblRueAdrFact = new JLabel( "");
	private JLabel lblComplAdrFact = new JLabel( "");
	private JLabel lblCpVilleAdrFact = new JLabel( "");
	private JLabel lblTelAdrFact = new JLabel( "");
	private JLabel lblNomAdrLiv = new JLabel( "");
	private JLabel lblRueAdrLiv = new JLabel( "");
	private JLabel lblComplAdrLiv = new JLabel( "");
	private JLabel lblCpVilleAdrLiv = new JLabel( "");
	private JLabel lblTelAdrLiv = new JLabel( "");
	private JLabel lblAdrFacId = new JLabel( "Test");
	private JLabel lblAdrLivId = new JLabel( "Test");
	private Vector vStatut = new Vector<>();
	private JComboBox cmbBxStatutLivre = new JComboBox();
	private static int nbRow = 0;
	private static JFrameListeAdresse JFAdr;
	private static JDialogCommentaireCommande JDcde;
	private DecimalFormat df = new DecimalFormat("##.00");
	private DateFormat datef = new SimpleDateFormat( "yyyy.MM.dd");
	private DefaultTableModel dtmCde = new DefaultTableModel( dtm(), 1);
	private DefaultTableModel dtmLigCde = new DefaultTableModel();
	private daoLigneCommande daoLigCde = new daoLigneCommande();
	private daoCommande daoCde = new daoCommande();
	private daoAdresse daoAdr = new daoAdresse();
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
					JFrameCommande frame = new JFrameCommande( "", "");
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
	public JFrameCommande( String numCde, String etat) {
		
System.out.println( "récupération du n° de commande : " + numCde);
System.out.println( "récupération de l'état : " + etat);
		
		setTitle("Commande");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 698);
		
		try {
			vStatut = daoCde.vectorCBStatutCde();
			//cmbBxStatutLivre.setModel( daoCde.statutCommande());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cmbBxStatutLivre = new JComboBox( vStatut);
		cmbBxStatutLivre.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date d = new Date( Calendar.getInstance().getTime().getTime());
				txtDateStatut.setText( datef.format( d));
			}
		});
		cmbBxStatutLivre.setEditable(true);
		cmbBxStatutLivre.setEnabled(false);
		cmbBxStatutLivre.setMaximumRowCount(100);
		cmbBxStatutLivre.setSelectedIndex(-1);
		if( !numCde.equals( "")) {
			try {
				dtmCde = daoCde.accesConsultationCde( numCde);
				String prenom = (String) dtmCde.getValueAt(0, 8);
				String nom = (String) dtmCde.getValueAt(0, 9);
				txtIdentiteClt.setText( prenom.toUpperCase() + ", " + nom);
				txtLoginClient.setText( (String) dtmCde.getValueAt(0, 1));
				txtPaiementClt.setText( (String) dtmCde.getValueAt(0, 2));
				txtNumCde.setText( (String) dtmCde.getValueAt(0, 0));
				Date dateCde = (Date) dtmCde.getValueAt(0, 4);
				txtCdeDate.setText( datef.format( dateCde));
				String cmbItem = (String) dtmCde.getValueAt(0, 11);
				cmbBxStatutLivre.setSelectedItem( cmbItem);;
				Date dateStatut = (Date) dtmCde.getValueAt(0, 7);
				txtDateStatut.setText( datef.format( dateStatut));
				txtPortHt.setText( (String) dtmCde.getValueAt(0, 3));
				float tva = (float) dtmCde.getValueAt(0, 10);
				txtTVA.setText( df.format( tva) + " %");
				lblAdrFacId.setText( (String) dtmCde.getValueAt(0, 5));
				lblAdrLivId.setText( (String) dtmCde.getValueAt(0, 6));
				
				Adresse adrLiv = new Adresse();
				adrLiv = daoAdr.findAdresseById( lblAdrLivId.getText());				
				lblNomAdrLiv.setText( adrLiv.getAdresseNom() + " " + adrLiv.getAdressePrenom());
				lblRueAdrLiv.setText( adrLiv.getAdresseNoRue() + ", " + adrLiv.getAdresseRue());
				lblComplAdrLiv.setText( adrLiv.getAdresseCompl());
				lblCpVilleAdrLiv.setText( adrLiv.getAdresseCp() + " - " + adrLiv.getAdresseVille());
				lblTelAdrLiv.setText( adrLiv.getAdresseTel());
				
				Adresse adrFac = new Adresse();
				adrFac = daoAdr.findAdresseById( lblAdrFacId.getText());			
				lblNomAdrFact.setText( adrFac.getAdresseNom() + " " + adrFac.getAdressePrenom());
				lblRueAdrFact.setText( adrFac.getAdresseNoRue() + ", " + adrFac.getAdresseRue());
				lblComplAdrFact.setText( adrFac.getAdresseCompl());
				lblCpVilleAdrFact.setText( adrFac.getAdresseCp() + " - " + adrFac.getAdresseVille());
				lblTelAdrFact.setText( adrFac.getAdresseTel());
				
				dtmLigCde = daoLigCde.listeLigneCde( numCde);
				String fp = (String) dtmCde.getValueAt(0, 3);
				float HT = Float.parseFloat( fp.replace(",", "."));
				for (int i = 0; i < dtmLigCde.getRowCount(); i++) {
					String ajout = (String) dtmLigCde.getValueAt(i, 5);
					HT = HT + Float.parseFloat( ajout.replace(",", "."));
				}
				txtHT.setText( df.format( HT));
				
				float TTC = (float) (Float.parseFloat( fp.replace(",", ".")) * 1.2);
				for (int i = 0; i < dtmLigCde.getRowCount(); i++) {
					String ajout = (String) dtmLigCde.getValueAt(i, 6);
					TTC = TTC + Float.parseFloat( ajout.replace(",", "."));
				}
				txtTTC.setText( df.format( TTC));
				
			} catch (SQLException e1) {
				//System.out.println( "Erreur JFrameCommande avec l'acces aux données de la commande - Ligne 212");
				e1.printStackTrace();
			}
			
		}
		
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
		
		try {
			dtmLigCde = daoLigCde.listeLigneCde( numCde);
//System.out.println( dtmLigCde);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		table = new JTable( dtmLigCde);
		table.setRowSelectionAllowed(false);
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
		txtLoginClient.setEditable(false);
	
		txtLoginClient.setBounds(111, 51, 189, 26);
		panelHaut.add(txtLoginClient);
		txtLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtLoginClient.setColumns(10);
		
		JLabel lblNumCde = new JLabel("N° de commande :");
		lblNumCde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumCde.setBounds(336, 18, 122, 16);
		panelHaut.add(lblNumCde);
		lblNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		txtNumCde.setBounds(464, 13, 103, 26);
		panelHaut.add(txtNumCde);
		txtNumCde.setEditable(false);
		txtNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNumCde.setColumns(10);
		
		txtCdeDate.setBounds(464, 41, 103, 26);
		panelHaut.add(txtCdeDate);
		txtCdeDate.setEditable(false);
		txtCdeDate.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtCdeDate.setColumns(10);
		
		JLabel lblDateDeCde = new JLabel("Date de commande :");
		lblDateDeCde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateDeCde.setBounds(325, 46, 133, 16);
		panelHaut.add(lblDateDeCde);
		lblDateDeCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		JLabel lblStatutCde = new JLabel("Statut commande :");
		lblStatutCde.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatutCde.setBounds(328, 74, 130, 16);
		panelHaut.add(lblStatutCde);
		lblStatutCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		txtDateStatut.setBounds(464, 97, 103, 26);
		panelHaut.add(txtDateStatut);
		txtDateStatut.setEditable(false);
		txtDateStatut.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtDateStatut.setColumns(10);
		
		JLabel lblDateStatut_1 = new JLabel("Date statut :");
		lblDateStatut_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateStatut_1.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblDateStatut_1.setBounds(380, 102, 78, 16);
		panelHaut.add(lblDateStatut_1);
		
		JLabel lblPaiement = new JLabel("Paiement :");
		lblPaiement.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPaiement.setBounds(16, 89, 91, 16);
		panelHaut.add(lblPaiement);
		lblPaiement.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		
		lblIdentiteClt = new JLabel("Nom & prénom :");
		lblIdentiteClt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentiteClt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblIdentiteClt.setBounds(6, 18, 101, 16);
		panelHaut.add(lblIdentiteClt);
		txtIdentiteClt.setEditable(false);
		
		txtIdentiteClt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtIdentiteClt.setColumns(10);
		txtIdentiteClt.setBounds(111, 13, 189, 26);
		panelHaut.add(txtIdentiteClt);
		txtPaiementClt.setEditable(false);
		
		txtPaiementClt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtPaiementClt.setColumns(10);
		txtPaiementClt.setBounds(111, 84, 189, 26);
		panelHaut.add(txtPaiementClt);
		cmbBxStatutLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBxStatutLivre.setBounds(458, 71, 326, 27);
		panelHaut.add(cmbBxStatutLivre);
		
		JPanel panelBas = new JPanel();
		panelBas.setBackground(new Color(255, 248, 220));
		panelBas.setBounds(17, 415, 790, 240);
		contentPane.add(panelBas);
		panelBas.setLayout(null);
		
		JLabel lblAdresseFact = new JLabel("Adresse de facturation");
		lblAdresseFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdresseFact.setBounds(29, 11, 145, 26);
		panelBas.add(lblAdresseFact);
		
		lblNomAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomAdrFact.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.ORANGE));
		lblNomAdrFact.setBounds(22, 49, 202, 26);
		panelBas.add(lblNomAdrFact);
		
		lblRueAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRueAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblRueAdrFact.setBounds(22, 87, 202, 26);
		panelBas.add(lblRueAdrFact);
		
		lblComplAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblComplAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblComplAdrFact.setBounds(22, 123, 202, 26);
		panelBas.add(lblComplAdrFact);
		
		lblCpVilleAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblCpVilleAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblCpVilleAdrFact.setBounds(22, 161, 202, 26);
		panelBas.add(lblCpVilleAdrFact);
		
		lblTelAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.ORANGE));
		lblTelAdrFact.setBounds(22, 199, 202, 26);
		panelBas.add(lblTelAdrFact);
		
		JLabel lblAdresseLiv = new JLabel("Adresse de livraison");
		lblAdresseLiv.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdresseLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdresseLiv.setBounds(259, 16, 126, 16);
		panelBas.add(lblAdresseLiv);
		
		lblNomAdrLiv.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.ORANGE));
		lblNomAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomAdrLiv.setBounds(249, 49, 202, 26);
		panelBas.add(lblNomAdrLiv);
		
		lblRueAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblRueAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRueAdrLiv.setBounds(249, 87, 202, 26);
		panelBas.add(lblRueAdrLiv);
		
		lblComplAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblComplAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblComplAdrLiv.setBounds(249, 123, 202, 26);
		panelBas.add(lblComplAdrLiv);
		
		lblCpVilleAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblCpVilleAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblCpVilleAdrLiv.setBounds(249, 161, 202, 26);
		panelBas.add(lblCpVilleAdrLiv);
		
		lblTelAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.ORANGE));
		lblTelAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelAdrLiv.setBounds(249, 199, 202, 26);
		panelBas.add(lblTelAdrLiv);
		
		JLabel lblPortHt = new JLabel("Frais port HT :");
		lblPortHt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPortHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPortHt.setBounds(603, 11, 103, 16);
		panelBas.add(lblPortHt);
		txtPortHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtPortHt.setBounds(707, 6, 77, 26);
		panelBas.add(txtPortHt);
		txtPortHt.setColumns(10);
		
		JLabel lblTotalHT = new JLabel("Total HT :");
		lblTotalHT.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalHT.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTotalHT.setBounds(561, 39, 145, 26);
		panelBas.add(lblTotalHT);
		
		txtHT.setEditable(false);
		txtHT.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtHT.setBounds(707, 39, 77, 26);
		panelBas.add(txtHT);
		txtHT.setColumns(10);
		
		JLabel lblTxTva = new JLabel("Taux TVA :");
		lblTxTva.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTxTva.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTxTva.setBounds(603, 70, 103, 26);
		panelBas.add(lblTxTva);
		
		txtTTC.setEditable(false);
		txtTTC.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTTC.setBounds(707, 102, 77, 26);
		panelBas.add(txtTTC);
		txtTTC.setColumns(10);
		
		JLabel lblTotalTtc = new JLabel("Total TTC :");
		lblTotalTtc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalTtc.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTotalTtc.setBounds(603, 107, 103, 16);
		panelBas.add(lblTotalTtc);
		
		txtTVA.setEditable(false);
		txtTVA.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTVA.setBounds(707, 70, 77, 26);
		panelBas.add(txtTVA);
		txtTVA.setColumns(10);
		
		JButton btnFindAdreLiv = new JButton("");
		btnFindAdreLiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String login =  txtLoginClient.getText();
				JFAdr = new JFrameListeAdresse( login, null, thisJF, "Livraison");
				JFAdr.setLocationRelativeTo( null);
				JFAdr.setVisible( true);
				setVisible( false);
			}
		});
		btnFindAdreLiv.setToolTipText("Carnet d'adresse");
		btnFindAdreLiv.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/map-book.png"));
		btnFindAdreLiv.setBounds(409, 6, 40, 36);
		panelBas.add(btnFindAdreLiv);
		
		JButton btnFindAdreFact = new JButton("");
		btnFindAdreFact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String login =  txtLoginClient.getText();
				JFAdr = new JFrameListeAdresse( login, null, thisJF, "Facturation");
				JFAdr.setLocationRelativeTo( null);
				JFAdr.setVisible( true);
				setVisible( false);

			}
		});
		btnFindAdreFact.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/map-book.png"));
		btnFindAdreFact.setToolTipText("Carnet d'adresse");
		btnFindAdreFact.setBounds(186, 6, 40, 36);
		panelBas.add(btnFindAdreFact);
		
		lblAdrFacId.setForeground( new Color(255, 255, 224));
		lblAdrFacId.setBackground( new Color(255, 255, 224));
		lblAdrFacId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdrFacId.setBounds(464, 49, 152, 20);
		panelBas.add(lblAdrFacId);
		
		lblAdrLivId.setForeground( new Color(255, 255, 224));
		lblAdrLivId.setBackground( new Color(255, 255, 224));
		lblAdrLivId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdrLivId.setBounds(477, 108, 152, 20);
		panelBas.add(lblAdrLivId);
		
		
		JButton btnCommentaireClient = new JButton("");
		btnCommentaireClient.setBounds(528, 149, 101, 76);
		panelBas.add(btnCommentaireClient);
		btnCommentaireClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDcde = new JDialogCommentaireCommande( txtNumCde.getText());
				JDcde.setLocationRelativeTo( null);
				JDcde.setVisible( true);
			}
		});
		btnCommentaireClient.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnPost.png"));
		
		JButton btnValider = new JButton("");
		btnValider.setVisible( false);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Commande cde = new Commande();
				Date dateCourante = new Date( Calendar.getInstance().getTime().getTime());
				String numCdeCourent = txtNumCde.getText(); 
				try {
					cde = daoCde.commandeByCdeNum( numCdeCourent);				
					cde.setAdresseIdF( lblAdrFacId.getText());
					cde.setAdresseIdL( lblAdrLivId.getText());
					cde.setDateStatut( dateCourante);
					cde.setStatutId( "ST2");
					daoCde.modifierCommande( cde, numCdeCourent);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
	});
		btnValider.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/double-checked32px.png"));
		btnValider.setBounds(736, 184, 48, 50);
		panelBas.add(btnValider);
		
		if ( etat.equals( "Consulter")) {
			btnCommentaireClient.setVisible( false);
			btnFindAdreFact.setVisible( false);
			btnFindAdreLiv.setVisible( false);
		}
		if ( etat.equals( "Modifier")) {
			cmbBxStatutLivre.setEnabled( true);
			btnValider.setVisible( true);
		}
		
		
	}
}