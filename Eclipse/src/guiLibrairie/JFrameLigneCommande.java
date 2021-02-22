package guiLibrairie;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import daoLibrairie.EvenementDAO;
import daoLibrairie.LivreDAO;
import daoLibrairie.daoCommande;
import daoLibrairie.daoLigneCommande;
import entitiesLibrairie.Adresse;
import entitiesLibrairie.Client;
import entitiesLibrairie.Commande;
import entitiesLibrairie.Evenement;
import entitiesLibrairie.LigneCommande;
import entitiesLibrairie.Livre;
import entitiesLibrairie.LivreLilia;
import entitiesLibrairie.Adresse;
import entitiesLibrairie.Client;

import java.awt.event.*;
import java.sql.*;
import java.sql.Date;
import java.sql.Statement;
import java.text.*;
import java.util.*;
import java.beans.*;

public class JFrameLigneCommande extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtPortHt;
	private JTextField txtRemise;
	private JTextField txtTotalTtc;
	private JTextField txtLoginClient;
	private JTextField txtTotalHt;
	private JTextField txtNumCde;
	private JTextField txtCdeDate;
	private JTextField txtDateStatut;
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
	private JLabel lblPrixTtc;
	private JLabel lblAdrFacId;
	private JLabel lblAdrLivId;
	private float totCdeHtAvRem;
	private float totCdeTtc;
	private float remise;
	private float txTva;
	private Vector<Commande> vStatutCde;
	private JFrameListeClient JFListClt;
	private static int nbRow = 0;
	private static JFrameListeAdresse JFAdr;
	private static JFrameClient JFclt;
	private static JDialogCommentaireCommande JDcde;
	private DecimalFormat df = new DecimalFormat("##.00");
	private DecimalFormat dfpour = new DecimalFormat("##.00 %");
	private DateFormat datef = new SimpleDateFormat( "yyyy.MM.dd");
	private Vector<String> vLivre = new Vector<>();
	private DefaultTableModel dtm = new DefaultTableModel( dtm(), 100);
	private daoCommande daoCde = new daoCommande();
	private daoLigneCommande daoLigCde = new daoLigneCommande();
	private LivreDAO daoLivre = new LivreDAO();
	private EvenementDAO daoEve = new EvenementDAO();
	//private String tabPaiement [] = { "--", };
	private Container parent = this;
	private JFrameLigneCommande thisJF = (JFrameLigneCommande) parent;


	public Vector dtm() {
		Vector nomColonne = new Vector<>();
		nomColonne.add( "n° ISBN");
		nomColonne.add( "Livre");
		nomColonne.add( "Tarif unitaire HT");
		nomColonne.add( "Remise");
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
					JFrameLigneCommande frame = new JFrameLigneCommande( "");
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
	 * Create the frame.
	 */
	public JFrameLigneCommande( String etat) {

		setTitle("Détail de commande");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 829, 756);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelHaut = new JPanel();
		panelHaut.setBackground(new Color(255, 248, 220));
		//panelHaut.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		panelHaut.setBounds(18, 6, 789, 57);
		contentPane.add(panelHaut);
		panelHaut.setLayout(null);

		JLabel lblLivre = new JLabel("Livre :");
		lblLivre.setForeground(new Color(128, 0, 0));
		lblLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLivre.setBounds(12, 6, 35, 32);
		panelHaut.add(lblLivre);

		JLabel lblPrixHt = new JLabel("");
		lblPrixHt.setForeground(new Color(128, 0, 0));
		lblPrixHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPrixHt.setBounds(471, 6, 77, 20);
		panelHaut.add(lblPrixHt);

		try {
			vLivre = daoLivre.vectorListLivre();
		} catch (SQLException e3) {
			e3.printStackTrace();
		} 
		JComboBox cmbBoxLivre = new JComboBox( vLivre);
		cmbBoxLivre.setForeground(new Color(128, 0, 0));
		cmbBoxLivre.setSelectedIndex(-1);
		cmbBoxLivre.setMaximumRowCount(1000);
		cmbBoxLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBoxLivre.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String selectLiv = (String) cmbBoxLivre.getSelectedItem();
				try {
					Float prixUnitHt = daoLivre.recupPrixHt( selectLiv);
					Float txTva = daoLivre.recupererTVA( selectLiv);
					Float prixUnitTtc = prixUnitHt * (1 + txTva/100);
					lblPrixHt.setText( df.format( prixUnitHt));
					lblPrixTtc.setText( df.format( prixUnitTtc));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		cmbBoxLivre.setBounds(59, 6, 345, 38);
		panelHaut.add(cmbBoxLivre);

		JLabel lblPrixUnitHt = new JLabel("Tarif HT :");
		lblPrixUnitHt.setForeground(new Color(128, 0, 0));
		lblPrixUnitHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPrixUnitHt.setBounds(416, 6, 59, 20);
		panelHaut.add(lblPrixUnitHt);

		JLabel lblQte = new JLabel("Quantité :");
		lblQte.setForeground(new Color(128, 0, 0));
		lblQte.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblQte.setBounds(560, 12, 67, 32);
		panelHaut.add(lblQte);

		JSpinner spinner = new JSpinner();
		spinner.setForeground(new Color(128, 0, 0));
		spinner.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		spinner.setBounds(639, 17, 77, 27);
		panelHaut.add(spinner);

		JScrollPane scrollPaneDroite = new JScrollPane();
		scrollPaneDroite.setBackground(new Color(255, 248, 220));
		scrollPaneDroite.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 0, Color.ORANGE));
		scrollPaneDroite.setBounds(53, 64, 715, 274);
		contentPane.add(scrollPaneDroite);

		table = new JTable( dtm);
		table.setForeground(new Color(128, 0, 0));
		table.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		table.setBackground(new Color(255, 248, 220));
		scrollPaneDroite.setViewportView(table);

		JPanel panelDroite = new JPanel();
		panelDroite.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.ORANGE));
		panelDroite.setBackground(new Color(255, 248, 220));
		panelDroite.setBounds(53, 337, 715, 43);
		contentPane.add(panelDroite);
		panelDroite.setLayout(null);

		JPanel panelBas = new JPanel();
		panelBas.setBackground(new Color(255, 248, 220));
		panelBas.setBounds(17, 378, 790, 344);
		contentPane.add(panelBas);
		panelBas.setLayout(null);

		JLabel lblLoginClient = new JLabel("Login client :");
		lblLoginClient.setForeground(new Color(128, 0, 0));
		lblLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLoginClient.setBounds(50, 84, 82, 16);
		panelBas.add(lblLoginClient);

		txtLoginClient = new JTextField();
		txtLoginClient.setForeground(new Color(128, 0, 0));
		txtLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtLoginClient.setBounds(134, 79, 189, 26);
		panelBas.add(txtLoginClient);
		txtLoginClient.setColumns(10);

		JLabel lblAdresseFact = new JLabel("Adresse de facturation");
		lblAdresseFact.setForeground(new Color(128, 0, 0));
		lblAdresseFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdresseFact.setBounds(39, 137, 152, 16);
		panelBas.add(lblAdresseFact);

		lblNomAdrFact = new JLabel("");
		lblNomAdrFact.setForeground(new Color(128, 0, 0));
		lblNomAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomAdrFact.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.ORANGE));
		lblNomAdrFact.setBounds(35, 160, 202, 26);
		panelBas.add(lblNomAdrFact);

		lblRueAdrFact = new JLabel("");
		lblRueAdrFact.setForeground(new Color(128, 0, 0));
		lblRueAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRueAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblRueAdrFact.setBounds(35, 198, 202, 26);
		panelBas.add(lblRueAdrFact);

		lblComplAdrFact = new JLabel("");
		lblComplAdrFact.setForeground(new Color(128, 0, 0));
		lblComplAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblComplAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblComplAdrFact.setBounds(35, 236, 202, 26);
		panelBas.add(lblComplAdrFact);

		lblCpVilleAdrFact = new JLabel("");
		lblCpVilleAdrFact.setForeground(new Color(128, 0, 0));
		lblCpVilleAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblCpVilleAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblCpVilleAdrFact.setBounds(35, 274, 202, 26);
		panelBas.add(lblCpVilleAdrFact);

		lblTelAdrFact = new JLabel("");
		lblTelAdrFact.setForeground(new Color(128, 0, 0));
		lblTelAdrFact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelAdrFact.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.ORANGE));
		lblTelAdrFact.setBounds(35, 312, 202, 26);
		panelBas.add(lblTelAdrFact);

		JLabel lblAdresseLiv = new JLabel("Adresse de livraison");
		lblAdresseLiv.setForeground(new Color(128, 0, 0));
		lblAdresseLiv.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdresseLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAdresseLiv.setBounds(249, 137, 126, 16);
		panelBas.add(lblAdresseLiv);

		lblNomAdrLiv = new JLabel("");
		lblNomAdrLiv.setForeground(new Color(128, 0, 0));
		lblNomAdrLiv.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.ORANGE));
		lblNomAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomAdrLiv.setBounds(249, 160, 202, 26);
		panelBas.add(lblNomAdrLiv);

		lblRueAdrLiv = new JLabel("");
		lblRueAdrLiv.setForeground(new Color(128, 0, 0));
		lblRueAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblRueAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRueAdrLiv.setBounds(249, 198, 202, 26);
		panelBas.add(lblRueAdrLiv);

		lblComplAdrLiv = new JLabel("");
		lblComplAdrLiv.setForeground(new Color(128, 0, 0));
		lblComplAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblComplAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblComplAdrLiv.setBounds(249, 236, 202, 26);
		panelBas.add(lblComplAdrLiv);

		lblCpVilleAdrLiv = new JLabel("");
		lblCpVilleAdrLiv.setForeground(new Color(128, 0, 0));
		lblCpVilleAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.ORANGE));
		lblCpVilleAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblCpVilleAdrLiv.setBounds(249, 274, 202, 26);
		panelBas.add(lblCpVilleAdrLiv);

		lblTelAdrLiv = new JLabel("");
		lblTelAdrLiv.setForeground(new Color(128, 0, 0));
		lblTelAdrLiv.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.ORANGE));
		lblTelAdrLiv.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelAdrLiv.setBounds(249, 312, 202, 26);
		panelBas.add(lblTelAdrLiv);

		JLabel lblPortHt = new JLabel("Frais port HT :");
		lblPortHt.setForeground(new Color(128, 0, 0));
		lblPortHt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPortHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPortHt.setBounds(557, 12, 103, 16);
		panelBas.add(lblPortHt);

		txtPortHt = new JTextField();
		txtPortHt.setForeground(new Color(128, 0, 0));
		txtPortHt.setEditable(false);
		txtPortHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtPortHt.setBounds(672, 7, 77, 26);
		panelBas.add(txtPortHt);
		txtPortHt.setColumns(10);

		JLabel lblRemise = new JLabel("Remise % :");
		lblRemise.setForeground(new Color(128, 0, 0));
		lblRemise.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRemise.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblRemise.setBounds(557, 50, 103, 16);
		panelBas.add(lblRemise);

		txtRemise = new JTextField();
		txtRemise.setForeground(new Color(128, 0, 0));
		txtRemise.setEditable(false);
		txtRemise.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtRemise.setBounds(672, 45, 77, 26);
		panelBas.add(txtRemise);
		txtRemise.setColumns(10);

		JLabel lblTotalTtc = new JLabel("Total TTC :");
		lblTotalTtc.setForeground(new Color(128, 0, 0));
		lblTotalTtc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalTtc.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTotalTtc.setBounds(557, 132, 103, 16);
		panelBas.add(lblTotalTtc);

		txtTotalTtc = new JTextField();
		txtTotalTtc.setForeground(new Color(128, 0, 0));
		txtTotalTtc.setEditable(false);
		txtTotalTtc.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTotalTtc.setBounds(672, 127, 77, 26);
		panelBas.add(txtTotalTtc);
		txtTotalTtc.setColumns(10);

		JButton btnAddLivre = new JButton("");
		btnAddLivre.setToolTipText("Ajouter un livre à la commande");
		btnAddLivre.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/plus32px.png"));
		btnAddLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sLivre = (String) cmbBoxLivre.getSelectedItem();
				//System.out.println( "liiiivre" + sLivre);
				String sIsbn = "";
				try {
					sIsbn = daoLivre.recupIsbn( sLivre);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				int iQte = (int) spinner.getValue();
				float prixHt = 0;
				try {
					prixHt = daoLivre.recupPrixHt( sLivre);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				if ( !sLivre.equals( "") && (Integer) spinner.getValue() > 0) {
					boolean doublon = false;
					int indexDoub = 0;
					if ( table.getRowCount() >=0) {
						for (int i = 0; i < table.getRowCount(); i++) {
							String livreParcouru = (String) table.getValueAt(i, 1);
							if ( sLivre.equals( livreParcouru)) {
								doublon = true;
								indexDoub = i;
								System.out.println( "Doublon détectée !");
							}
						}
					}
					totCdeHtAvRem = 0;
					String sRecupLivre = (String) table.getValueAt( indexDoub, 1);
					//System.out.println( "LIVREEEEEE" + sRecupLivre);
					float txTva = 0;
					Evenement ev = null;
					float remiseCourante = 0;
					try {
						txTva = daoLivre.recupererTVA( sLivre);
						ev = daoEve.rechercherEvenementByDate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					try {
						remiseCourante = ev.getEvenementPourcentage();
					} catch ( NullPointerException npe) {
						remiseCourante = 0;
					}
					//System.out.println( "TVAAAAAA" + txTva);
					if ( !doublon) {
						table.setValueAt( sIsbn, nbRow, 0);
						table.setValueAt( sLivre, nbRow, 1);
						table.setValueAt( df.format( prixHt), nbRow, 2);

						if ( remiseCourante == 0) {
							table.setValueAt( "0,00", nbRow, 3);
						} else {
							table.setValueAt( dfpour.format( remiseCourante/100), nbRow, 3);
						}
						table.setValueAt( iQte, nbRow, 4);
						table.setValueAt( df.format( iQte*prixHt*(1-remiseCourante/100)), nbRow, 5);
						for (int i = 0; i <= nbRow; i++) {
							String ajout = (String) table.getValueAt(i, 5);
							totCdeHtAvRem = totCdeHtAvRem + Float.parseFloat( ajout.replace(",", "."));
						}
						txtTotalHt.setText( df.format( totCdeHtAvRem));
						if ( remiseCourante == 0) {
							txtRemise.setText( "0,00");
						} else {
							txtRemise.setText( dfpour.format( remiseCourante/100));
						}
						totCdeTtc = totCdeHtAvRem * ( 1 + txTva/100);
						txtTotalTtc.setText( df.format( totCdeTtc));
						nbRow ++;
					}
					if ( doublon) {
						int recupQte = (int) table.getValueAt( indexDoub, 4);
						int newQte = recupQte + iQte;

						//String recupTotCdeHt = txtTotalHt.getText().replace(",", ".");
						table.setValueAt( newQte, indexDoub, 4);
						table.setValueAt( df.format( newQte*prixHt*(1-remiseCourante/100)), indexDoub, 5);
//System.out.println( "nb ligne = " + nbRow);
						for (int i = 0; i < nbRow; i++) {
//System.out.println( "i = " + i);
							String ajout = (String) table.getValueAt(i, 5);
//System.out.println( "string ajout = " + ajout);
							totCdeHtAvRem = totCdeHtAvRem + Float.parseFloat( ajout.replace(",", "."));
//System.out.println( "float totCdeHtAvRem = " + totCdeHtAvRem);
						}
						//totCdeHtAvRem = Float.parseFloat( recupTotCdeHt) + (iQte * prixHt);
						txtTotalHt.setText( df.format( totCdeHtAvRem));
						totCdeTtc = totCdeHtAvRem * ( 1 + txTva/100);
						txtTotalTtc.setText( df.format( totCdeTtc));
					}

					String recupTotCdeHt = txtTotalHt.getText().replace(",", ".");

					if ( Float.parseFloat( recupTotCdeHt) < 30.0) {
						txtPortHt.setText( "10,00");
						if ( remiseCourante == 0) {
							txtRemise.setText( "0,00");
						} else {
							txtRemise.setText( dfpour.format( remiseCourante/100));
						}
						totCdeHtAvRem = totCdeHtAvRem + 10;
						txtTotalHt.setText( df.format( totCdeHtAvRem));
						totCdeTtc = totCdeHtAvRem * ( 1 + txTva/100);
						txtTotalTtc.setText( df.format( totCdeTtc));
					} else {
						txtPortHt.setText( "0,00");
						if ( remiseCourante == 0) {
							txtRemise.setText( "0,00 %");
						} else {
							txtRemise.setText( dfpour.format( remiseCourante/100));
						}
						totCdeTtc = totCdeHtAvRem * ( 1 + txTva/100);
						txtTotalTtc.setText( df.format( totCdeTtc));
					}
				}
			}
		});
		btnAddLivre.setBounds(728, 6, 48, 47);
		panelHaut.add(btnAddLivre);

		JLabel lblPrixUnitTtc = new JLabel("Tarif TTC :");
		lblPrixUnitTtc.setForeground(new Color(128, 0, 0));
		lblPrixUnitTtc.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPrixUnitTtc.setBounds(408, 27, 67, 26);
		panelHaut.add(lblPrixUnitTtc);

		lblPrixTtc = new JLabel("");
		lblPrixTtc.setForeground(new Color(128, 0, 0));
		lblPrixTtc.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPrixTtc.setBounds(471, 31, 77, 20);
		panelHaut.add(lblPrixTtc);

		JButton btnFindAdreLiv = new JButton("");
		btnFindAdreLiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ( !txtLoginClient.getText().equals( "")) {
					JFAdr = new JFrameListeAdresse( txtLoginClient.getText(), thisJF, null, "Livraison");
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
		btnFindAdreLiv.setBounds(411, 117, 40, 36);
		panelBas.add(btnFindAdreLiv);

		JButton btnFindAdreFact = new JButton("");
		btnFindAdreFact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( !txtLoginClient.getText().equals( "")) {
					JFAdr = new JFrameListeAdresse( txtLoginClient.getText(), thisJF, null, "Facturation");
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
		btnFindAdreFact.setBounds(197, 117, 40, 36);
		panelBas.add(btnFindAdreFact);

		JButton btnFindClt = new JButton("");
		btnFindClt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFListClt = new JFrameListeClient( thisJF, "ChoixLogin", null);
				JFListClt.setLocationRelativeTo( null);
				JFListClt.setVisible( true);
			}
		});
		btnFindClt.setToolTipText("Rechercher un client");
		btnFindClt.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/verify16px.png"));
		btnFindClt.setBounds(335, 79, 40, 32);
		panelBas.add(btnFindClt);

		JButton btnAddClt = new JButton("");
		btnAddClt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFclt = new JFrameClient("", thisJF, "Ajouter");
				JFclt.setLocationRelativeTo( null);
				JFclt.setVisible( true);
			}
		});
		btnAddClt.setToolTipText("Ajouter un nouveau client");
		btnAddClt.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/plus12px.png"));
		btnAddClt.setBounds(374, 79, 40, 32);
		panelBas.add(btnAddClt);

		JLabel lblTotalHtAvant = new JLabel("Total HT :");
		lblTotalHtAvant.setForeground(new Color(128, 0, 0));
		lblTotalHtAvant.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalHtAvant.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTotalHtAvant.setBounds(515, 85, 145, 26);
		panelBas.add(lblTotalHtAvant);

		txtTotalHt = new JTextField();
		txtTotalHt.setForeground(new Color(128, 0, 0));
		txtTotalHt.setEditable(false);
		txtTotalHt.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTotalHt.setColumns(10);
		txtTotalHt.setBounds(672, 85, 77, 26);
		panelBas.add(txtTotalHt);

		JLabel lblPaiement = new JLabel("Paiement :");
		lblPaiement.setForeground(new Color(128, 0, 0));
		lblPaiement.setBounds(583, 208, 67, 16);
		panelBas.add(lblPaiement);
		lblPaiement.setFont(new Font("Avenir Next", Font.PLAIN, 13));

		JComboBox cmbBoxPaiement = new JComboBox( );
		cmbBoxPaiement.setForeground(new Color(128, 0, 0));
		cmbBoxPaiement.setModel(new DefaultComboBoxModel(new String[] {"en CB", "en magasin"}));
		cmbBoxPaiement.setSelectedIndex(-1);
		cmbBoxPaiement.setFont(new Font("Avenir Next", Font.PLAIN, 10));
		cmbBoxPaiement.setBounds(649, 198, 103, 36);
		panelBas.add(cmbBoxPaiement);

		JLabel lblNumCde = new JLabel("N° de cde :");
		lblNumCde.setForeground(new Color(128, 0, 0));
		lblNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNumCde.setBounds(39, 12, 77, 16);
		panelBas.add(lblNumCde);

		txtNumCde = new JTextField();
		txtNumCde.setForeground(new Color(128, 0, 0));
		txtNumCde.setEditable(false);
		if ( etat.equals( "Ajouter")) {
			txtNumCde.setText( daoCde.ajoutIdCommande());
		}
		txtNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNumCde.setColumns(10);
		txtNumCde.setBounds(104, 7, 103, 26);
		panelBas.add(txtNumCde);

		JLabel lblDateDeCde = new JLabel("Date de cde :");
		lblDateDeCde.setForeground(new Color(128, 0, 0));
		lblDateDeCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblDateDeCde.setBounds(281, 12, 82, 16);
		panelBas.add(lblDateDeCde);

		txtCdeDate = new JTextField();
		txtCdeDate.setForeground(new Color(128, 0, 0));
		txtCdeDate.setEditable(false);
		if ( etat.equals( "Ajouter")) {
			Date d = new Date( Calendar.getInstance().getTime().getTime());
			txtCdeDate.setText( datef.format( d));
		}
		txtCdeDate.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtCdeDate.setColumns(10);
		txtCdeDate.setBounds(361, 7, 90, 26);
		panelBas.add(txtCdeDate);

		JLabel lblStatutCde = new JLabel("Statut cde :");
		lblStatutCde.setForeground(new Color(128, 0, 0));
		lblStatutCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblStatutCde.setBounds(39, 50, 77, 16);
		panelBas.add(lblStatutCde);

		JLabel lblDateStatut = new JLabel("Date statut :");
		lblDateStatut.setForeground(new Color(128, 0, 0));
		lblDateStatut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateStatut.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblDateStatut.setBounds(281, 50, 78, 16);
		panelBas.add(lblDateStatut);

		txtDateStatut = new JTextField();
		txtDateStatut.setForeground(new Color(128, 0, 0));
		txtDateStatut.setEditable(false);
		txtDateStatut.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtDateStatut.setColumns(10);
		txtDateStatut.setBounds(361, 44, 90, 26);
		panelBas.add(txtDateStatut);

		try {
			vStatutCde = daoCde.vectorCBStatutCde();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JComboBox cmbBoxStatut = new JComboBox( vStatutCde);
		cmbBoxStatut.setForeground(new Color(128, 0, 0));
		cmbBoxStatut.setSelectedIndex(-1);
		cmbBoxStatut.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date d = new Date( Calendar.getInstance().getTime().getTime());
				txtDateStatut.setText( datef.format( d));
			}
		});
		cmbBoxStatut.setFont(new Font("Avenir Next", Font.PLAIN, 10));
		cmbBoxStatut.setBounds(104, 42, 180, 36);
		panelBas.add(cmbBoxStatut);

		JButton btnCommentaireClient = new JButton("");
		btnCommentaireClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDcde = new JDialogCommentaireCommande( txtNumCde.getText());
				JDcde.setLocationRelativeTo( null);
				JDcde.setVisible( true);
			}
		});
		btnCommentaireClient.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnPost.png"));
		btnCommentaireClient.setBounds(475, 262, 101, 76);
		panelBas.add(btnCommentaireClient);

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

		JButton btnValiderLigCde = new JButton("");
		btnValiderLigCde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LigneCommande ligCde = null;
				String tvaId = "TVA2";
				String statutId = "";
				String numCde = daoCde.ajoutIdCommande();
				String clt = txtLoginClient.getText();
				String paiement = (String) cmbBoxPaiement.getSelectedItem();
				String fraisLiv = txtPortHt.getText();		
				Date dateCourante = new Date( Calendar.getInstance().getTime().getTime());
				String statut = (String) cmbBoxStatut.getSelectedItem();
				String adrLiv = lblAdrLivId.getText();
				String adrFac = lblAdrFacId.getText();
				String ip = daoCde.recupAdresseIp();
				try {
					//tvaId = daoLivre.recupererTvaId( txTva);
//System.out.println( "id tva récupéré : " + tvaId);
					statutId = daoCde.recupererStatutId( statut);
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				try {
					Commande cde = new Commande( numCde, clt, paiement, fraisLiv, dateCourante, tvaId, statutId, adrLiv, adrFac, ip, dateCourante);
					daoCde.ajouterCommande( cde);
					for (int i = 0; i < nbRow ; i++) {
						String isbn = (String) table.getValueAt(i, 0);
						String titre = (String) table.getValueAt(i, 1);
						float txTvaLivre = daoLivre.recupererTVA( titre);
						String sQte = table.getValueAt(i, 4).toString();
						float qte = Float.parseFloat( sQte);
						String sPuHt = table.getValueAt(i, 2).toString().replace(",", ".");
						float puHt = Float.parseFloat( sPuHt);
						String sRemise =  (String) table.getValueAt(i, 3).toString().replace(",", ".");
						remise = Float.parseFloat( sRemise);
						ligCde = new LigneCommande( "0000"+(i+1)+"LIG", numCde, isbn, qte, puHt, txTvaLivre, remise, clt);
						daoLigCde.addLigneCommande( ligCde);
						dispose();
					}
					JOptionPane.showMessageDialog(null, "La commande a bien été ajoutée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnValiderLigCde.setToolTipText("Valider la commande");
		btnValiderLigCde.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnGestCommande.png"));
		btnValiderLigCde.setBounds(685, 284, 67, 54);
		panelBas.add(btnValiderLigCde);

		JButton btnRemoveLivre = new JButton("");
		btnRemoveLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int rowSelect = table.getSelectedRow();
					String sRecupTotCdeHt = txtTotalHt.getText();
					String sRecupTotHtSupp = (String) table.getValueAt( rowSelect, 5);
					float recupTotHtSupp = Float.parseFloat( sRecupTotHtSupp.replace(",", "."));
					float recupTotCdeHt = Float.parseFloat( sRecupTotCdeHt.replace(",", "."));
					String sRecupLivre = (String) table.getValueAt( rowSelect, 1);
					txTva = 0;
					Evenement ev = null;
					try {
						txTva = daoLivre.recupererTVA( sRecupLivre);
						ev = daoEve.rechercherEvenementByDate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					float remiseCourante = 0;
					try {
						remiseCourante = ev.getEvenementPourcentage();
					} catch ( NullPointerException npe) {
						remiseCourante = 0;
					}
					dtm.removeRow( rowSelect);
					totCdeHtAvRem = recupTotCdeHt - recupTotHtSupp;
					txtTotalHt.setText( df.format( totCdeHtAvRem));
					totCdeTtc = totCdeHtAvRem * ( 1 + txTva/100);
					txtTotalTtc.setText( df.format( totCdeTtc));
					if ( totCdeHtAvRem < 30.0) {
						txtPortHt.setText( "10,00");
						totCdeHtAvRem = totCdeHtAvRem + 10;
						txtTotalHt.setText( df.format( totCdeHtAvRem));
						totCdeTtc = totCdeHtAvRem * ( 1 + txTva/100);
						txtTotalTtc.setText( df.format( totCdeTtc));
					} else {
						txtTotalHt.setText( df.format( totCdeHtAvRem));
						txtPortHt.setText( "0,00");
						totCdeTtc = totCdeHtAvRem * ( 1 + txTva/100);
						txtTotalTtc.setText( df.format( totCdeTtc));
					}
					if ( txtTotalHt.getText().equals( ",00")) {
						lblPrixHt.setText( "");
						lblPrixTtc.setText( "");
						spinner.setValue( 0);
						cmbBoxLivre.setSelectedIndex( -1);
						txtPortHt.setText( "");
						txtTotalHt.setText( "");
						txtRemise.setText( "");
						txtTotalTtc.setText( "");
					}
					nbRow --;
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog( contentPane, "Merci de selectionner une ligne de commande à supprimer !", "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnRemoveLivre.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/moins12px.png"));
		btnRemoveLivre.setToolTipText("Supprimer un livre de la commande");
		btnRemoveLivre.setBounds(618, 6, 40, 32);
		panelDroite.add(btnRemoveLivre);

		JButton btnRemoveAll = new JButton("");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < dtm.getRowCount(); i++) {
					dtm.removeRow( 0);
				}
				table.setModel( dtm);
				table.repaint();
				lblPrixHt.setText( "");
				lblPrixTtc.setText( "");
				cmbBoxLivre.setSelectedIndex( -1);
				txtPortHt.setText( "");
				txtTotalHt.setText( "");
				txtRemise.setText( "");
				txtTotalTtc.setText( "");
				nbRow = 0;
			}
		});
		btnRemoveAll.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/delete.png"));
		btnRemoveAll.setToolTipText("Supprimer tous les livres de la commande");
		btnRemoveAll.setBounds(669, 6, 40, 32);
		panelDroite.add(btnRemoveAll);

	}
}