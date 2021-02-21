package guiLibrairie;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Employe;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JLabel;

public class JFrameAccueil extends JFrame {
	
	private JFrame frame;
	private JPanel contentPane;
	private SimpleDateFormat formater = null;
	private Date currentDate ;
	private static JFrameListeCommande lcde;
	private static JFrameListeClient lclt;
	private static JFrameListeCategorie lcat;
	private static JFrameEvenement levt;
	private static JFrameEmploye lemp;
	private static JFrameLivre lliv;
	private static Employe empl = new Employe();
	

	
	/*private static void makeFrameFullSize(JFrame aFrame) {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    aFrame.setSize(screenSize.width, screenSize.height);
	}*/
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameAccueil frame = new JFrameAccueil( empl);
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 *
	public JFrameAccueil(Employe employeATraiter) {
		frame = new JFrame();
		frame.
		frame.
		frame.
		frame.
		//initialize(employeATraiter);
	}*/
	
	
	/**
	 * Create the frame.
	 */
	public JFrameAccueil(Employe employeATraiter) {
		
		setTitle("Accueil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 850);
		setVisible(true);
		
		//employe = employeATraiter;
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
//JLABELS//////////////////////////////////////////////////////////////////////
		JLabel lblAuthentification = new JLabel("Vous êtes connecté en tant que " +employeATraiter.getEmployePrenom() + " " + employeATraiter.getEmployeNom());
		lblAuthentification.setForeground(new Color(255, 215, 0));
		lblAuthentification.setFont(new Font("Avenir Next", Font.PLAIN, 20));
		lblAuthentification.setHorizontalAlignment(SwingConstants.LEFT);
		lblAuthentification.setBounds(57, 54, 1079, 47);
		contentPane.add(lblAuthentification);
		
		
		formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		JLabel lblDate = new JLabel(formater.format(currentDate));
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setForeground(new Color(105, 105, 105));
		lblDate.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblDate.setBounds(57, 6, 1278, 47);
		contentPane.add(lblDate);
		
		
		
		
		
		
		
//BOUTON CATEGORIES//////////////////////////////////////////////////////////////////////		
		JButton btnGestionCategorie = new JButton( "<html><center>Gestion<br>catégorie</center></html>");
		btnGestionCategorie.setForeground(new Color(128, 0, 0));
		btnGestionCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String statut = "AFFICHAGE";
				lcat = new JFrameListeCategorie(null, statut);
				lcat.setLocationRelativeTo( lcat.getParent());
				lcat.setVisible( true);
			}
		});
		btnGestionCategorie.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		btnGestionCategorie.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionCategorie.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/categories64px.png"));
		btnGestionCategorie.setBounds(57, 164, 310, 133);
		contentPane.add(btnGestionCategorie);
		
		
		
//BOUTON EVENEMENTS//////////////////////////////////////////////////////////////////////		
		JButton btnGestionEvenement = new JButton( "<html><center>Gestion<br>évènement</center></html>");
		btnGestionEvenement.setForeground(new Color(128, 0, 0));
		btnGestionEvenement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levt = new JFrameEvenement();
				levt.setVisible(true);
			}
		});
		btnGestionEvenement.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/firework64px.png"));
		btnGestionEvenement.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		btnGestionEvenement.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionEvenement.setBounds(557, 164, 310, 133);
		contentPane.add(btnGestionEvenement);
		
		
		
//BOUTON CLIENTS//////////////////////////////////////////////////////////////////////		
		JButton btnGestionClient = new JButton("<html><center>Gestion<br>client</center></html>");
		btnGestionClient.setForeground(new Color(128, 0, 0));
		btnGestionClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lclt = new JFrameListeClient( null, "");
				lclt.setLocationRelativeTo( lclt.getParent());
				lclt.setVisible( true);
			}
		});
		btnGestionClient.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/rating64px.png"));
		btnGestionClient.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		btnGestionClient.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionClient.setBounds(1025, 164, 310, 133);
		contentPane.add(btnGestionClient);
		
		
		
//BOUTON OUVRAGES//////////////////////////////////////////////////////////////////////
		JButton btnGestionOuvrage = new JButton("<html><center>Gestion<br>ouvrages</center></html>");
		btnGestionOuvrage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lliv = new JFrameLivre();
				lliv.setVisible(true);
			}
		});
		btnGestionOuvrage.setForeground(new Color(128, 0, 0));
		btnGestionOuvrage.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/books64px.png"));
		btnGestionOuvrage.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		btnGestionOuvrage.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionOuvrage.setBounds(57, 414, 310, 133);
		contentPane.add(btnGestionOuvrage);
		
		
		
//BOUTON EMPLOYES//////////////////////////////////////////////////////////////////////		
		JButton btnGestionEmployé = new JButton("<html><center>Gestion<br>employés</center></html>");
		btnGestionEmployé.setForeground(new Color(128, 0, 0));
		btnGestionEmployé.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employe employe = new Employe();
				lemp = new JFrameEmploye();
				lemp.setVisible(true);
			}
		});
		btnGestionEmployé.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/work64px.png"));
		btnGestionEmployé.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		btnGestionEmployé.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionEmployé.setBounds(557, 414, 310, 133);
		contentPane.add(btnGestionEmployé);
		
		
		
//BOUTON COMMANDES//////////////////////////////////////////////////////////////////////
		JButton btnGestionCommande = new JButton("<html><center>Gestion<br>commandes</center></html>");
		btnGestionCommande.setForeground(new Color(128, 0, 0));
		btnGestionCommande.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/shopping-cart64px.png"));
		btnGestionCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lcde = new JFrameListeCommande( "");
				lcde.setLocationRelativeTo( null);
				lcde.setVisible( true);
			}
		});
		btnGestionCommande.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		btnGestionCommande.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionCommande.setBounds(1025, 414, 310, 133);
		contentPane.add(btnGestionCommande);
		
		
		
		
//BOUTON CATALOGUE//////////////////////////////////////////////////////////////////////
		JButton btnCatalogue = new JButton("Catalogue");
		btnCatalogue.setForeground(new Color(128, 0, 0));
		btnCatalogue.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/catalogue64px.png"));
		btnCatalogue.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		btnCatalogue.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCatalogue.setBounds(57, 659, 810, 133);
		contentPane.add(btnCatalogue);
		
		
		
		
		
		
//BOUTON COMMENTAIRES//////////////////////////////////////////////////////////////////////
		JButton btnGestionCommentaires = new JButton("<html><center>Gestion<br>commentaires</center></html>");
		btnGestionCommentaires.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/chat64px.png"));
		btnGestionCommentaires.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrameCommentaire jfCommentaire = new JFrameCommentaire();
				jfCommentaire.setVisible(true);
			}
		});
		btnGestionCommentaires.setForeground(new Color(128, 0, 0));
		btnGestionCommentaires.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		btnGestionCommentaires.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionCommentaires.setBounds(1025, 659, 310, 133);
		contentPane.add(btnGestionCommentaires);
		
		
		
//ASSIGNATION DES DROITS////////////////////////////////////////////////////////////////////////
		String droits = employeATraiter.getDroitsAcces();
		switch (droits) {
		case "Administrateur" :
			break;
		case "Responsable":
			btnGestionEmployé.setEnabled(false);;
			break;
		case "Employé" :
			btnGestionEmployé.setEnabled(false);
			btnGestionEvenement.setEnabled(false);
			break;
		case "Stagiaire":
			btnGestionEmployé.setEnabled(false);
			btnGestionEvenement.setEnabled(false);
			btnGestionClient.setEnabled(false);
			btnGestionCategorie.setEnabled(false);
			btnGestionCommentaires.setEnabled(false);
			btnGestionOuvrage.setEnabled(false);
			break;
		}
	
		
		
		
//BOUTON DECONNEXION//////////////////////////////////////////////////////////////////////
		JButton btnDeconnexion = new JButton("");
		btnDeconnexion.setBackground(new Color(255, 250, 250));
		btnDeconnexion.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/logout64px.png"));
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connexion.closeInstance();
				System.exit(0);
			}
		});
		//btnDeconnexion.setForeground(new Color(128, 0, 0));
		//btnDeconnexion.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		//btnDeconnexion.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnDeconnexion.setBounds(1247, 33, 88, 88);
		contentPane.add(btnDeconnexion);
		

	}
}

