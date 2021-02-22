package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daoLibrairie.ThemeDAO;
import daoLibrairie.daoAuteur;
import daoLibrairie.daoEditeur;
import daoLibrairie.daoGenre;
import daoLibrairie.daoMotCle;
import entitiesLibrairie.Genre;
import entitiesLibrairie.Theme;

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
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JFrameListeCategorie extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomGenre;
	private daoGenre daoG = new daoGenre();
	private daoAuteur daoAu = new daoAuteur();
	private daoMotCle daoMot = new daoMotCle();
	private daoEditeur daoEd = new daoEditeur();
	private ThemeDAO themeDAO = new ThemeDAO();
	private JTable tableGenre;
	private static JFrameGenre JFgen;
	private static JFrameAuteur JFaut;
	private static JFrameMotCle JFmc;
	private static JFrameEditeur JFed;
	private static JFrameNvTheme JFth;
	private static JFrameModifTheme JFMth;
	private static JDialogLivreAjout jdLAstatic;
	private static String status;
	private JTextField txtNomAuteur;
	private JTable tableAuteur;
	private JTextField txtLibMotCle;
	private JTable tableMotCle;
	private JTextField txtNomEditeur;
	private JTable tableEditeur;
	private JTextField txtLibTheme;
	private JTable tableTheme;
	private Vector <Vector> vectorTheme;
	private DefaultListModel vecteurMotCle = new DefaultListModel();
	private DefaultListModel vecteurGenre = new DefaultListModel();
	//private DefaultListModel vecteurAuteur = new DefaultListModel();
	private DefaultTableModel modelAuteur = new DefaultTableModel();
	private String vecteurEditeur;
	private DefaultListModel vecteurTheme = new DefaultListModel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameListeCategorie frame = new JFrameListeCategorie(jdLAstatic, "");
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
	public JFrameListeCategorie(JDialogLivreAjout jdLA, String statut) {
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
		
		
		
		
		
//PANE AUTEUR//////////////////////////////////////////////////////////////////////////////////////////		
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
		//tableAuteur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//tableAuteur.setCellSelectionEnabled(true);
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
					txtNomAuteur.setText( "");
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
				JFaut.setLocationRelativeTo( null);
				JFaut.setVisible( true);
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
					JFaut.setLocationRelativeTo( null);
					JFaut.setVisible( true);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un auteur à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour modification de l'auteur - Voir JFrameListeCategorie & daoGenre");
					e1.printStackTrace();
				}
			}
		});
		btnModifierAut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifierAut.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		//btnModifierAut.setBounds(292, 399, 129, 54);
		btnModifierAut.setBounds(215, 399, 129, 54);
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
		
		JButton btnRefreshAuteur = new JButton("");
		btnRefreshAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableAuteur.setModel( daoAu.listeAuteur());
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec l'affichage de la liste Auteur - Voir JFrameListeCategorie & daoAuteur");
					e1.printStackTrace();
				}
			}
		});
		btnRefreshAuteur.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/refresh24px.png"));
		btnRefreshAuteur.setBounds(537, 31, 55, 55);
		paneAuteur.add(btnRefreshAuteur);

		tabbedPane.setBackgroundAt(0, Color.ORANGE);
		tabbedPane.setForegroundAt(0, Color.DARK_GRAY);
		if(statut.equals("AJOUT LIVRE")) {	
			JButton btnSelectionnerAuteur = new JButton("Sélectionner");
			btnSelectionnerAuteur.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row = tableAuteur.getSelectedRow();
					Vector <Vector> auteurs = new Vector();
					Vector <String> nomCol = new Vector();
					nomCol.add("nom");
					nomCol.add("prenom");
					nomCol.add("pseudo");
					String auteurNom="";
					String auteurPrenom="";
					String auteurPseudo ="";
					
					for (int i = 0; i <= tableAuteur.getRowCount(); i++) {
						if (tableAuteur.isRowSelected(i)) {
							
							Vector auteur = new Vector();
							auteurNom = (String) tableAuteur.getValueAt(i, 1);
							auteurPrenom = (String) tableAuteur.getValueAt(i, 2);
							auteurPseudo = (String) tableAuteur.getValueAt(i, 3);
							auteur.add(auteurNom);
							auteur.add(auteurPrenom);
							auteur.add(auteurPseudo);
							auteurs.addElement(auteur);
						}
					}
					modelAuteur = new DefaultTableModel(auteurs, nomCol);
					jdLA.refreshAuteur(modelAuteur);
					jdLA.repaint();
					jdLA.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
			btnSelectionnerAuteur.setForeground(new Color(128, 0, 0));
			btnSelectionnerAuteur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			btnSelectionnerAuteur.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			btnSelectionnerAuteur.setBounds(369, 399, 129, 54);
			paneAuteur.add(btnSelectionnerAuteur);
		}
//PANE EDITEUR//////////////////////////////////////////////////////////////////////////////////////////		
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
					txtNomEditeur.setText( "");
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
				JFed.setLocationRelativeTo( null);
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
		//btnModifierEditeur.setBounds(292, 399, 129, 54);
		btnModifierEditeur.setBounds(215, 399, 129, 54);
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
		
		JButton btnRefreshEditeUr = new JButton("");
		btnRefreshEditeUr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableEditeur.setModel( daoEd.listeEditeur());
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec l'affichage de la liste Editeur - Voir JFrameListeCategorie & daoEditeur");
					e1.printStackTrace();
				}
			}
		});
		btnRefreshEditeUr.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/refresh24px.png"));
		btnRefreshEditeUr.setBounds(537, 31, 55, 55);
		paneEditeur.add(btnRefreshEditeUr);
		tabbedPane.setForegroundAt(1, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(1, Color.ORANGE);
		
		
		if(statut.equals("AJOUT LIVRE")) {	
			JButton btnSelectionnerEditeur = new JButton("Sélectionner");
			btnSelectionnerEditeur.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row = tableEditeur.getSelectedRow();
					
					
					for (int i = 0; i <= tableEditeur.getRowCount(); i++) {
						if (tableEditeur.isCellSelected(i, 1)) {
							String editeurSelected = (String) tableEditeur.getValueAt(i, 1);
							vecteurEditeur = editeurSelected;
						}
					}
					jdLA.refreshEditeur(vecteurEditeur);
					jdLA.repaint();
					jdLA.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
			btnSelectionnerEditeur.setForeground(new Color(128, 0, 0));
			btnSelectionnerEditeur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			btnSelectionnerEditeur.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			btnSelectionnerEditeur.setBounds(369, 399, 129, 54);
			paneEditeur.add(btnSelectionnerEditeur);
		}

//PANE GENRE//////////////////////////////////////////////////////////////////////////////////////////			
		
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
		
/*
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
					System.out.println( motCleSelect);
					tableMotCle.setModel( daoMot.listeMotCleByLib( motCleSelect));
					txtLibMotCle.setText( "");
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
		
		JButton btnRefreshMotCle = new JButton("");
		btnRefreshMotCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableMotCle.setModel( daoMot.listeMotCle());
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec l'affichage de la liste Mot-clé - Voir JFrameListeCategorie & daoMotCle");
					e1.printStackTrace();
				}
			}
		});
		btnRefreshMotCle.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/refresh24px.png"));
		btnRefreshMotCle.setBounds(530, 31, 55, 55);
		paneMotCle.add(btnRefreshMotCle);
		tabbedPane.setForegroundAt(3, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(3, Color.ORANGE);
		
		JLayeredPane layPaneTheme = new JLayeredPane();
		tabbedPane.addTab("Thème", null, layPaneTheme, null);
		tabbedPane.setForegroundAt(4, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(4, Color.ORANGE);
*/
    
		JButton btnLoupeGenre = new JButton("");
		btnLoupeGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genreSelect = "";
				try {
					genreSelect = txtNomGenre.getText();
					tableGenre.setModel( daoG.listeGenreByName( genreSelect));
					txtNomGenre.setText( "");
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
				//setVisible( false);
				//dispose();
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
					//setVisible( false);
					//dispose();
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
		//btnModifierGenre.setBounds(292, 399, 129, 54);
		btnModifierGenre.setBounds(215, 399, 129, 54);
		paneGenre.add(btnModifierGenre);
		
		JButton btnSupprimerGenre = new JButton("Supprimer");
		btnSupprimerGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genreNomSelect = "";
				String genreIdSelect = "";
				try {
					genreNomSelect = (String) daoG.listeGenre().getValueAt(tableGenre.getSelectedRow(), 1);
					genreIdSelect = (String) daoG.listeGenre().getValueAt( tableGenre.getSelectedRow(), 0);
					daoG.delierGenreTheme( genreIdSelect);
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
		
		JButton btnRefreshGenre = new JButton("");
		btnRefreshGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableGenre.setModel( daoG.listeGenre());
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec l'affichage de la liste Genre - Voir JFrameListeCategorie & daoGenre");
					e1.printStackTrace();
				}
			}
		});
		btnRefreshGenre.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/refresh24px.png"));
		btnRefreshGenre.setBounds(525, 31, 55, 55);
		paneGenre.add(btnRefreshGenre);
    
		if(statut.equals("AJOUT LIVRE")) {	
			JButton btnSelectionnerGenre = new JButton("Sélectionner");
			btnSelectionnerGenre.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row = tableGenre.getSelectedRow();
					
					
					for (int i = 0; i <= tableGenre.getRowCount(); i++) {
						if (tableGenre.isCellSelected(i, 1)) {
							String genreSelected = (String) tableGenre.getValueAt(i, 1);
							vecteurGenre.addElement(genreSelected);;
						}
					}
					jdLA.refreshGenre(vecteurGenre);
					jdLA.repaint();
					jdLA.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
			btnSelectionnerGenre.setForeground(new Color(128, 0, 0));
			btnSelectionnerGenre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			btnSelectionnerGenre.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			btnSelectionnerGenre.setBounds(369, 399, 129, 54);
			paneGenre.add(btnSelectionnerGenre);
		}
		

//PANE MOTCLE//////////////////////////////////////////////////////////////////////////////////////////			
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
		//tableMotCle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMotCle.setFillsViewportHeight(true);
		scrollPaneMotCle.setViewportView(tableMotCle);
		tableMotCle.setShowGrid( true);
		tableMotCle.setShowHorizontalLines( true);
		tableMotCle.setShowVerticalLines( true);
		tableMotCle.getTableHeader().setBounds( 6, 6, 685, 281);
		tableMotCle.getTableHeader().setVisible( true);
		tableMotCle.setCellSelectionEnabled(true);
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
					txtLibMotCle.setText( "");
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
				//setVisible( false);
				//dispose();
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
					//setVisible( false);
					//dispose();
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
		btnModifierMotCle.setBounds(215, 399, 129, 54);
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
		
		JButton btnRefreshMotCle = new JButton("");
		btnRefreshMotCle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tableMotCle.setModel( daoMot.listeMotCle());
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec l'affichage de la liste Mot-clé - Voir JFrameListeCategorie & daoMotCle");
					e1.printStackTrace();
				}
			}
		});
		btnRefreshMotCle.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/refresh24px.png"));
		btnRefreshMotCle.setBounds(530, 31, 55, 55);
		paneMotCle.add(btnRefreshMotCle);
		tabbedPane.setForegroundAt(3, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(3, Color.ORANGE);
		
		
		if(statut.equals("AJOUT LIVRE")) {	
			JButton btnSelectionnerMC = new JButton("Sélectionner");
			btnSelectionnerMC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row = tableMotCle.getSelectedRow();
					
					
					for (int i = 0; i <= tableMotCle.getRowCount(); i++) {
						if (tableMotCle.isCellSelected(i, 1)) {
							String motcleSelected = (String) tableMotCle.getValueAt(i, 1);
							vecteurMotCle.addElement(motcleSelected);
						}
					}
					jdLA.refreshMotCle(vecteurMotCle);
					jdLA.repaint();
					jdLA.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
			btnSelectionnerMC.setForeground(new Color(128, 0, 0));
			btnSelectionnerMC.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			btnSelectionnerMC.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			btnSelectionnerMC.setBounds(369, 399, 129, 54);
			paneMotCle.add(btnSelectionnerMC);
		}
			
//PANE THEME//////////////////////////////////////////////////////////////////////////////////////////		
		JLayeredPane layPaneTheme = new JLayeredPane();
		tabbedPane.addTab("Thème", null, layPaneTheme, null);
		tabbedPane.setForegroundAt(4, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(4, Color.ORANGE);
		

		JPanel paneTheme = new JPanel();
		paneTheme.setLayout(null);
		paneTheme.setBackground(new Color(255, 248, 220));
		paneTheme.setBounds(0, 0, 721, 483);
		layPaneTheme.add(paneTheme);
		
		JLabel lblLibTheme = new JLabel("Libellé thème :");
		lblLibTheme.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLibTheme.setBounds(170, 49, 92, 18);
		paneTheme.add(lblLibTheme);
		
		JLabel lblRechercherByTheme = new JLabel("Rechercher par");
		lblRechercherByTheme.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercherByTheme.setBounds(22, 16, 142, 33);
		paneTheme.add(lblRechercherByTheme);
		
		txtLibTheme = new JTextField();
		txtLibTheme.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtLibTheme.setColumns(10);
		txtLibTheme.setBounds(274, 45, 162, 26);
		paneTheme.add(txtLibTheme);
		
		JPanel panelTheme = new JPanel();
		panelTheme.setLayout(null);
		panelTheme.setBackground(new Color(255, 248, 220));
		panelTheme.setBounds(6, 94, 709, 293);
		paneTheme.add(panelTheme);
		
		JScrollPane scrollPaneTheme = new JScrollPane();
		scrollPaneTheme.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		scrollPaneTheme.setBackground(new Color(255, 248, 220));
		scrollPaneTheme.setBounds(6, 6, 697, 281);
		panelTheme.add(scrollPaneTheme);
		
		tableTheme = new JTable();
		tableTheme.setBackground(new Color(255, 248, 220));
		//tableTheme.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTheme.setFillsViewportHeight(true);
		scrollPaneTheme.setViewportView(tableTheme);
		tableTheme.setShowGrid( true);
		tableTheme.setShowHorizontalLines( true);
		tableTheme.setShowVerticalLines( true);
		tableTheme.getTableHeader().setBounds( 6, 6, 685, 281);
		tableTheme.getTableHeader().setVisible( true);
		tableTheme.setCellSelectionEnabled(true);
		Vector<String> nomCol = new Vector();
		nomCol.add("ID");
		nomCol.add("Libellé");
		
		try {
			vectorTheme = themeDAO.afficherTheme();
			DefaultTableModel themeModel = new DefaultTableModel(vectorTheme, nomCol);
			tableTheme.setModel(themeModel);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JButton btnLoupeTheme = new JButton("");
		btnLoupeTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String themeSelect = "";
				try {
					themeSelect = txtLibTheme.getText();
					vectorTheme = themeDAO.rechercherTheme(themeSelect);
					DefaultTableModel themeModel = new DefaultTableModel(vectorTheme, nomCol);
					tableTheme.setModel(themeModel);
					txtLibTheme.setText( "");
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la recherche de thème par libellé - Voir JFrameListeCategorie & daoTheme");
					e1.printStackTrace();
				}
			}
		});
		btnLoupeTheme.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupeTheme.setBounds(459, 31, 55, 55);
		paneTheme.add(btnLoupeTheme);
		
		JButton btnCreerTheme = new JButton("Créer");
		btnCreerTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFth = new JFrameNvTheme();
				JFth.setLocationRelativeTo( JFth.getParent());
				JFth.setVisible( true);
			}
		});
		btnCreerTheme.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnCreerTheme.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCreerTheme.setBounds(66, 399, 129, 54);
		paneTheme.add(btnCreerTheme);
		
		JButton btnModifierTheme = new JButton("Modifier");
		btnModifierTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String themeId = "";
				int row = tableTheme.getSelectedRow();
				try {
					themeId = (String) tableTheme.getValueAt(row, 0);
					Theme themeModif = themeDAO.rechercherUnTheme(themeId);
					JFMth = new JFrameModifTheme(themeModif);
					JFMth.setVisible( true);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un thème à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException exc) {
					System.err.println( "Oops : erreur avec la récupération du nom pour modification d'un thème - Voir JFrameListeCategorie & daoThème");
					exc.printStackTrace();
				}
			}
		});
		btnModifierTheme.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifierTheme.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifierTheme.setBounds(215, 399, 129, 54);
		paneTheme.add(btnModifierTheme);
		
		JButton btnSupprimerTheme = new JButton("Supprimer");
		btnSupprimerTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String themeSelect = "";
				int row = tableTheme.getSelectedRow();
				String id = (String) tableTheme.getValueAt(row, 0);
				try {
					themeDAO.supprimerTheme(id);
					DefaultTableModel nouveaumodel = new DefaultTableModel(vectorTheme, nomCol);
					tableTheme.setModel(nouveaumodel);
					JOptionPane.showMessageDialog(null, "Theme supprimé avec succès", "Message", JOptionPane.INFORMATION_MESSAGE);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un thème à supprimer !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour suppression d'un thème - Voir JFrameListeCategorie & daoThème");
					e1.printStackTrace();
				}
			}
		});
		btnSupprimerTheme.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimerTheme.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimerTheme.setBounds(510, 399, 129, 54);
		paneTheme.add(btnSupprimerTheme);
		
		JButton btnRefreshTheme = new JButton("");
		btnRefreshTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector themeVecteur = themeDAO.afficherTheme();
					DefaultTableModel nouveauModel = new DefaultTableModel(themeVecteur, nomCol);
					tableTheme.setModel(nouveauModel);
					scrollPaneTheme.setViewportView(tableTheme);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec l'affichage de la liste thème - Voir JFrameListeCategorie & daoTheme");
					e1.printStackTrace();
				}
			}
		});
		btnRefreshTheme.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/refresh24px.png"));
		btnRefreshTheme.setBounds(530, 31, 55, 55);
		paneTheme.add(btnRefreshTheme);
		tabbedPane.setForegroundAt(3, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(3, Color.ORANGE);
		
		
		if(statut.equals("AJOUT LIVRE")) {	
			JButton btnSelectionnerTheme = new JButton("Sélectionner");
			btnSelectionnerTheme.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row = tableTheme.getSelectedRow();
					
					
					for (int i = 0; i <= tableTheme.getRowCount(); i++) {
						if (tableTheme.isCellSelected(i, 1)) {
							String themeSelected = (String) tableTheme.getValueAt(i, 1);
							vecteurTheme.addElement(themeSelected);
						}
					}
					jdLA.refreshTheme(vecteurTheme);
					jdLA.repaint();
					jdLA.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
			btnSelectionnerTheme.setForeground(new Color(128, 0, 0));
			btnSelectionnerTheme.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			btnSelectionnerTheme.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			btnSelectionnerTheme.setBounds(369, 399, 129, 54);
			paneTheme.add(btnSelectionnerTheme);
		}
	}
}
