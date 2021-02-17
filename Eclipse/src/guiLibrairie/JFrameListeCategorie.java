package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import daoLibrairie.daoAuteur;
import daoLibrairie.daoEditeur;
import daoLibrairie.daoGenre;
import daoLibrairie.daoMotCle;
import entitiesLibrairie.Genre;

import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JFrameListeCategorie extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomGenre;
	private daoGenre daoG = new daoGenre();
	private daoAuteur daoAu = new daoAuteur();
	private daoMotCle daoMot = new daoMotCle();
	private daoEditeur daoEd = new daoEditeur();
	private JTable tableGenre;
	private static JFrameGenre JFgen;
	private static JFrameAuteur JFaut;
	private static JFrameMotCle JFmc;
	private static JFrameEditeur JFed;
	private JTextField txtNomAuteur;
	private JTable tableAuteur;
	private JTextField txtLibMotCle;
	private JTable tableMotCle;
	private JTextField txtNomEditeur;
	private JTable tableEditeur;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameListeCategorie frame = new JFrameListeCategorie();
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
	public JFrameListeCategorie() {
		setTitle("Liste des catégories");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 544);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(6, 6, 767, 504);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		contentPane.add(tabbedPane);
		
		JLayeredPane layPaneAuteur = new JLayeredPane();
		tabbedPane.addTab("Auteur", null, layPaneAuteur, null);
		layPaneAuteur.setLayout(null);
		
		JPanel paneAuteur = new JPanel();
		paneAuteur.setBounds(0, 0, 721, 483);
		layPaneAuteur.add(paneAuteur);
		paneAuteur.setLayout(null);
		paneAuteur.setBackground(new Color(255, 248, 220));
		
		JLabel lblRechercheAut = new JLabel("Rechercher par");
		lblRechercheAut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercheAut.setBounds(22, 16, 142, 33);
		paneAuteur.add(lblRechercheAut);
		
		JLabel lblNomAuteur = new JLabel("Nom Auteur :");
		lblNomAuteur.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomAuteur.setBounds(182, 49, 80, 18);
		paneAuteur.add(lblNomAuteur);
		
		txtNomAuteur = new JTextField();
		txtNomAuteur.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNomAuteur.setColumns(10);
		txtNomAuteur.setBounds(274, 45, 162, 26);
		paneAuteur.add(txtNomAuteur);
		
		JPanel panelInterneAut = new JPanel();
		panelInterneAut.setLayout(null);
		panelInterneAut.setBackground(new Color(255, 248, 220));
		panelInterneAut.setBounds(6, 94, 709, 293);
		paneAuteur.add(panelInterneAut);
		
		JScrollPane scrollPaneAut = new JScrollPane();
		scrollPaneAut.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		scrollPaneAut.setBackground(new Color(255, 248, 220));
		scrollPaneAut.setBounds(6, 6, 697, 281);
		panelInterneAut.add(scrollPaneAut);
		
		tableAuteur = new JTable();
		scrollPaneAut.setViewportView(tableAuteur);
		tableAuteur.setBackground(new Color(255, 248, 220));
		tableAuteur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAuteur.setFillsViewportHeight(true);
		tableAuteur.setShowGrid( true);
		tableAuteur.setShowHorizontalLines( true);
		tableAuteur.setShowVerticalLines( true);
		tableAuteur.getTableHeader().setBounds( 6, 6, 685, 281);
		tableAuteur.getTableHeader().setVisible( true);
		try {
			tableAuteur.setModel( daoAu.listeAuteur());
		} catch (SQLException e1) {
			System.err.println( "Oops : erreur avec l'affichage de la liste Auteur - Voir JFrameListeCategorie & daoAuteur");
			e1.printStackTrace();
		}
		
		JButton btnLoupeAut = new JButton("");
		btnLoupeAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String auteurSelect = "";
				try {
					auteurSelect = txtNomAuteur.getText();
					tableAuteur.setModel( daoAu.listeAuteurByName( auteurSelect));
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la recherche d'auteur par nom - Voir JFrameListeCategorie & daoAuteur");
					e1.printStackTrace();
				}
			}
		});
		btnLoupeAut.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupeAut.setBounds(459, 31, 55, 55);
		paneAuteur.add(btnLoupeAut);
		
		JButton btnCreerAut = new JButton("Créer");
		btnCreerAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFaut = new JFrameAuteur( "");
				JFaut.setLocationRelativeTo( JFaut.getParent());
				JFaut.setVisible( true);
				setVisible( false);
				dispose();
			}
		});
		btnCreerAut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnCreerAut.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCreerAut.setBounds(66, 399, 129, 54);
		paneAuteur.add(btnCreerAut);
		
		JButton btnModifierAut = new JButton("Modifier");
		btnModifierAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String auteurNomSelect = "";
				try {
					auteurNomSelect = (String) daoAu.listeAuteur().getValueAt( tableAuteur.getSelectedRow(), 1);
					JFaut = new JFrameAuteur( auteurNomSelect);
					JFaut.setLocationRelativeTo( JFaut.getParent());
					JFaut.setVisible( true);
					setVisible( false);
					dispose();
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un auteur à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour modification d'un auteur - Voir JFrameListeCategorie & daoAuteur");
					e1.printStackTrace();
				}
			}
		});
		btnModifierAut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifierAut.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifierAut.setBounds(292, 399, 129, 54);
		paneAuteur.add(btnModifierAut);
		
		JButton btnSupprimerAut = new JButton("Supprimer");
		btnSupprimerAut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String auteurNomSelect = "";
				try {
					auteurNomSelect = (String) daoAu.listeAuteur().getValueAt( tableAuteur.getSelectedRow(), 1);
					daoAu.supprimerAuteur( auteurNomSelect);
					tableAuteur.repaint();
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog( getParent(), "Merci de sélectionner un auteur à supprimer !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour suppression d'un auteur - Voir JFrameListeCategorie & daoAuteur");
					e1.printStackTrace();
				}
			}
		});
		btnSupprimerAut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimerAut.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimerAut.setBounds(510, 399, 129, 54);
		paneAuteur.add(btnSupprimerAut);
		tabbedPane.setBackgroundAt(0, Color.ORANGE);
		tabbedPane.setForegroundAt(0, Color.DARK_GRAY);
		
		JLayeredPane layPaneEditeur = new JLayeredPane();
		layPaneEditeur.setForeground(Color.WHITE);
		layPaneEditeur.setBackground(Color.WHITE);
		tabbedPane.addTab("Éditeur", null, layPaneEditeur, null);
		
		JPanel paneEditeur = new JPanel();
		paneEditeur.setLayout(null);
		paneEditeur.setBackground(new Color(255, 248, 220));
		paneEditeur.setBounds(0, 0, 721, 483);
		layPaneEditeur.add(paneEditeur);
		
		JLabel lblNomEditeur = new JLabel("Nom éditeur :");
		lblNomEditeur.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomEditeur.setBounds(170, 49, 92, 18);
		paneEditeur.add(lblNomEditeur);
		
		JLabel lblRechercherByEd = new JLabel("Rechercher par");
		lblRechercherByEd.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercherByEd.setBounds(22, 16, 142, 33);
		paneEditeur.add(lblRechercherByEd);
		
		txtNomEditeur = new JTextField();
		txtNomEditeur.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNomEditeur.setColumns(10);
		txtNomEditeur.setBounds(274, 45, 162, 26);
		paneEditeur.add(txtNomEditeur);
		
		JPanel panelEditeur = new JPanel();
		panelEditeur.setLayout(null);
		panelEditeur.setBackground(new Color(255, 248, 220));
		panelEditeur.setBounds(6, 94, 709, 293);
		paneEditeur.add(panelEditeur);
		
		JScrollPane scrollPaneEditeur = new JScrollPane();
		scrollPaneEditeur.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		scrollPaneEditeur.setBackground(new Color(255, 248, 220));
		scrollPaneEditeur.setBounds(6, 6, 697, 281);
		panelEditeur.add(scrollPaneEditeur);
		
		tableEditeur = new JTable();
		scrollPaneEditeur.setViewportView(tableEditeur);
		tableEditeur.setBackground(new Color(255, 248, 220));
		tableEditeur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableEditeur.setFillsViewportHeight(true);
		tableEditeur.setShowGrid( true);
		tableEditeur.setShowHorizontalLines( true);
		tableEditeur.setShowVerticalLines( true);
		tableEditeur.getTableHeader().setBounds( 6, 6, 685, 281);
		tableEditeur.getTableHeader().setVisible( true);
		try {
			tableEditeur.setModel( daoEd.listeEditeur());
		} catch (SQLException e1) {
			System.err.println( "Oops : erreur avec l'affichage de la liste Editeur - Voir JFrameListeCategorie & daoEditeur");
			e1.printStackTrace();
		}
		
		JButton btnLoupeEditeur = new JButton("");
		btnLoupeEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String editeurSelect = "";
				try {
					editeurSelect = txtNomEditeur.getText();
					tableEditeur.setModel( daoEd.listeEditeurByNom( editeurSelect));
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la recherche d'auteur par nom - Voir JFrameListeCategorie & daoAuteur");
					e1.printStackTrace();
				}
				
			}
		});
		btnLoupeEditeur.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupeEditeur.setBounds(459, 31, 55, 55);
		paneEditeur.add(btnLoupeEditeur);
		
		JButton btnCreerEditeur = new JButton("Créer");
		btnCreerEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFed = new JFrameEditeur( "");
				JFed.setLocationRelativeTo( JFed.getParent());
				JFed.setVisible( true);
				setVisible( false);
				dispose();
			}
		});
		btnCreerEditeur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnCreerEditeur.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCreerEditeur.setBounds(66, 399, 129, 54);
		paneEditeur.add(btnCreerEditeur);
		
		JButton btnModifierEditeur = new JButton("Modifier");
		btnModifierEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String editeurNomSelect = "";
				try {
					editeurNomSelect = (String) daoEd.listeEditeur().getValueAt( tableEditeur.getSelectedRow(), 1);
					JFed = new JFrameEditeur( editeurNomSelect);
					JFed.setLocationRelativeTo( JFed.getParent());
					JFed.setVisible( true);
					setVisible( false);
					dispose();
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un éditeur à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour modification d'un éditeur - Voir JFrameListeCategorie & daoEditeur");
					e1.printStackTrace();
				}
			}
		});
		btnModifierEditeur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifierEditeur.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifierEditeur.setBounds(292, 399, 129, 54);
		paneEditeur.add(btnModifierEditeur);
		
		JButton btnSupprimerEditeur = new JButton("Supprimer");
		btnSupprimerEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String editeurNomSelect = "";
				try {
					editeurNomSelect = (String) daoEd.listeEditeur().getValueAt( tableEditeur.getSelectedRow(), 1);
					daoEd.supprimerEditeur( editeurNomSelect);
					tableEditeur.repaint();
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un éditeur à supprimer !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour suppression d'un éditeur - Voir JFrameListeCategorie & daoEditeur");
					e1.printStackTrace();
				}
			}
		});
		btnSupprimerEditeur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimerEditeur.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimerEditeur.setBounds(510, 399, 129, 54);
		paneEditeur.add(btnSupprimerEditeur);
		tabbedPane.setForegroundAt(1, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(1, Color.ORANGE);
		
		JLayeredPane layPaneGenre = new JLayeredPane();
		layPaneGenre.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		layPaneGenre.setForeground(Color.WHITE);
		tabbedPane.addTab("Genre", null, layPaneGenre, null);
		tabbedPane.setBackgroundAt(2, Color.ORANGE);
		tabbedPane.setForegroundAt(2, Color.DARK_GRAY);
		
		JPanel paneGenre = new JPanel();
		paneGenre.setBackground(new Color(255, 248, 220));
		paneGenre.setBounds(6, 6, 709, 471);
		layPaneGenre.add(paneGenre);
		paneGenre.setLayout(null);
		
		JLabel lblNomGenre = new JLabel("Nom genre :");
		lblNomGenre.setBounds(182, 49, 80, 18);
		lblNomGenre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		paneGenre.add(lblNomGenre);
		
		JLabel lblRechercherPar = new JLabel("Rechercher par");
		lblRechercherPar.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercherPar.setBounds(22, 16, 142, 33);
		paneGenre.add(lblRechercherPar);
		
		txtNomGenre = new JTextField();
		txtNomGenre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNomGenre.setColumns(10);
		txtNomGenre.setBounds(274, 45, 162, 26);
		paneGenre.add(txtNomGenre);
		
		JPanel panelGenre = new JPanel();
		panelGenre.setBackground(new Color(255, 248, 220));
		panelGenre.setBounds(6, 94, 697, 293);
		paneGenre.add(panelGenre);
		panelGenre.setLayout(null);
		
		JScrollPane scrollPaneGenre = new JScrollPane();
		scrollPaneGenre.setBackground( new Color(255, 248, 220));
		scrollPaneGenre.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		scrollPaneGenre.setBounds(6, 6, 685, 281);
		panelGenre.add(scrollPaneGenre);
		
		tableGenre = new JTable();
		tableGenre.setBackground(new Color(255, 248, 220));
		tableGenre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableGenre.setFillsViewportHeight(true);
		scrollPaneGenre.setViewportView( tableGenre);
		tableGenre.setShowGrid( true);
		tableGenre.setShowHorizontalLines( true);
		tableGenre.setShowVerticalLines( true);
		tableGenre.getTableHeader().setBounds( 6, 6, 685, 281);
		tableGenre.getTableHeader().setVisible( true);
		try {
			tableGenre.setModel( daoG.listeGenre());
		} catch (SQLException e1) {
			System.err.println( "Oops : erreur avec l'affichage de la liste Genre - Voir JFrameListeCategorie & daoGenre");
			e1.printStackTrace();
		}
		tableGenre.getColumnModel().getColumn( 1).setPreferredWidth(20);
		
		JLayeredPane layPaneMotCle = new JLayeredPane();
		layPaneGenre.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		layPaneGenre.setForeground(Color.WHITE);
		tabbedPane.addTab("Mot-clé", null, layPaneMotCle, null);
		
		JPanel paneMotCle = new JPanel();
		paneMotCle.setLayout(null);
		paneMotCle.setBackground(new Color(255, 248, 220));
		paneMotCle.setBounds(0, 0, 721, 483);
		layPaneMotCle.add(paneMotCle);
		
		JLabel lblLibMotCle = new JLabel("Libellé mot-clé :");
		lblLibMotCle.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLibMotCle.setBounds(170, 49, 92, 18);
		paneMotCle.add(lblLibMotCle);
		
		JLabel lblRechercherByMC = new JLabel("Rechercher par");
		lblRechercherByMC.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercherByMC.setBounds(22, 16, 142, 33);
		paneMotCle.add(lblRechercherByMC);
		
		txtLibMotCle = new JTextField();
		txtLibMotCle.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtLibMotCle.setColumns(10);
		txtLibMotCle.setBounds(274, 45, 162, 26);
		paneMotCle.add(txtLibMotCle);
		
		JPanel panelMotCle = new JPanel();
		panelMotCle.setLayout(null);
		panelMotCle.setBackground(new Color(255, 248, 220));
		panelMotCle.setBounds(6, 94, 709, 293);
		paneMotCle.add(panelMotCle);
		
		JScrollPane scrollPaneMotCle = new JScrollPane();
		scrollPaneMotCle.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		scrollPaneMotCle.setBackground(new Color(255, 248, 220));
		scrollPaneMotCle.setBounds(6, 6, 697, 281);
		panelMotCle.add(scrollPaneMotCle);
		
		tableMotCle = new JTable();
		tableMotCle.setBackground(new Color(255, 248, 220));
		tableMotCle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMotCle.setFillsViewportHeight(true);
		scrollPaneMotCle.setViewportView(tableMotCle);
		tableMotCle.setShowGrid( true);
		tableMotCle.setShowHorizontalLines( true);
		tableMotCle.setShowVerticalLines( true);
		tableMotCle.getTableHeader().setBounds( 6, 6, 685, 281);
		tableMotCle.getTableHeader().setVisible( true);
		try {
			tableMotCle.setModel( daoMot.listeMotCle());
		} catch (SQLException e1) {
			System.err.println( "Oops : erreur avec l'affichage de la liste Mot-clé - Voir JFrameListeCategorie & daoMotCle");
			e1.printStackTrace();
		}
		
		
		JButton btnLoupeMotCle = new JButton("");
		btnLoupeMotCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String motCleSelect = "";
				try {
					motCleSelect = txtLibMotCle.getText();
					tableMotCle.setModel( daoMot.listeMotCleByLib( motCleSelect));
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la recherche de mot-clé par libellé - Voir JFrameListeCategorie & daoMotCle");
					e1.printStackTrace();
				}
			}
		});
		btnLoupeMotCle.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupeMotCle.setBounds(459, 31, 55, 55);
		paneMotCle.add(btnLoupeMotCle);
		
		JButton btnCreerMotCle = new JButton("Créer");
		btnCreerMotCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFmc = new JFrameMotCle( "");
				JFmc.setLocationRelativeTo( JFmc.getParent());
				JFmc.setVisible( true);
				setVisible( false);
				dispose();
			}
		});
		btnCreerMotCle.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnCreerMotCle.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCreerMotCle.setBounds(66, 399, 129, 54);
		paneMotCle.add(btnCreerMotCle);
		
		JButton btnModifierMotCle = new JButton("Modifier");
		btnModifierMotCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String motCleSelect = "";
				try {
					motCleSelect = (String) daoMot.listeMotCle().getValueAt( tableMotCle.getSelectedRow(), 1);
					JFmc = new JFrameMotCle( motCleSelect);
					JFmc.setLocationRelativeTo( JFmc.getParent());
					JFmc.setVisible( true);
					setVisible( false);
					dispose();
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un mot-clé à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour modification d'un mot-clé - Voir JFrameListeCategorie & daoMotCle");
					e1.printStackTrace();
				}
			}
		});
		btnModifierMotCle.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifierMotCle.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifierMotCle.setBounds(292, 399, 129, 54);
		paneMotCle.add(btnModifierMotCle);
		
		JButton btnSupprimerMotCle = new JButton("Supprimer");
		btnSupprimerMotCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String motCleSelect = "";
				try {
					motCleSelect = (String) daoMot.listeMotCle().getValueAt( tableMotCle.getSelectedRow(), 1);
					daoMot.supprimerMotCle( motCleSelect);
					tableMotCle.repaint();
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un mot-clé à supprimer !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour suppression d'un mot-clé - Voir JFrameListeCategorie & daoMotClé");
					e1.printStackTrace();
				}
			}
		});
		btnSupprimerMotCle.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimerMotCle.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimerMotCle.setBounds(510, 399, 129, 54);
		paneMotCle.add(btnSupprimerMotCle);
		tabbedPane.setForegroundAt(3, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(3, Color.ORANGE);
		
		JLayeredPane layPaneTheme = new JLayeredPane();
		tabbedPane.addTab("Thème", null, layPaneTheme, null);
		tabbedPane.setForegroundAt(4, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(4, Color.ORANGE);
		
		JButton btnLoupeGenre = new JButton("");
		btnLoupeGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genreSelect = "";
				try {
					genreSelect = txtNomGenre.getText();
					tableGenre.setModel( daoG.listeGenreByName( genreSelect));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLoupeGenre.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupeGenre.setBounds(459, 31, 55, 55);
		paneGenre.add(btnLoupeGenre);
		
		JButton btnCreerGenre = new JButton("Créer");
		btnCreerGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFgen = new JFrameGenre( "");
				JFgen.setLocationRelativeTo( JFgen.getParent());
				JFgen.setVisible( true);
				setVisible( false);
				dispose();
			}
		});
		btnCreerGenre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnCreerGenre.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCreerGenre.setBounds(66, 399, 129, 54);
		paneGenre.add(btnCreerGenre);
		
		JButton btnModifierGenre = new JButton("Modifier");
		btnModifierGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genreNomSelect = "";
				try {
					genreNomSelect = (String) daoG.listeGenre().getValueAt(tableGenre.getSelectedRow(), 1);
					JFgen = new JFrameGenre( genreNomSelect);
					JFgen.setLocationRelativeTo( JFgen.getParent());
					JFgen.setVisible( true);
					setVisible( false);
					dispose();
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un genre à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour modification d'un genre - Voir JFrameListeCategorie & daoGenre");
					e1.printStackTrace();
				}
			}
		});
		btnModifierGenre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifierGenre.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifierGenre.setBounds(292, 399, 129, 54);
		paneGenre.add(btnModifierGenre);
		
		JButton btnSupprimerGenre = new JButton("Supprimer");
		btnSupprimerGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genreNomSelect = "";
				try {
					genreNomSelect = (String) daoG.listeGenre().getValueAt(tableGenre.getSelectedRow(), 1);
					daoG.supprimerGenre( genreNomSelect);
					tableGenre.repaint();
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un genre à supprimer !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour suppression d'un genre - Voir JFrameListeCategorie & daoGenre");
					e1.printStackTrace();
				}
			}
		});
		btnSupprimerGenre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimerGenre.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimerGenre.setBounds(510, 399, 129, 54);
		paneGenre.add(btnSupprimerGenre);
	}
}
