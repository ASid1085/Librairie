package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


import daoLibrairie.EvenementDAO;
import daoLibrairie.LivreDAO;
import entitiesLibrairie.Auteur;
import entitiesLibrairie.Editeur;
import entitiesLibrairie.Evenement;
import entitiesLibrairie.Livre;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class JFrameLivre extends JFrame {

	private JPanel contentPane;
	private JTable jTableLivre;
	private Vector nomColonnes = new Vector();
	private JTextField jTextRecherche;
	private JTextArea jTextResume;
	private LivreDAO livreDAO = new LivreDAO();
	private Livre livre;
	private Vector vectorLivre = new Vector <Vector>();
	private JTextArea jTextPrixTTC;
	private JComboBox jComboBoxLivre;
	private JTextArea jTextEditeur;
	private JTable tableTheme;
	private JTable tableAuteur;
	private JTable tableMotCle;
	private DecimalFormat df = new DecimalFormat("##.##");



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameLivre frame = new JFrameLivre();
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
	public JFrameLivre() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		

//JLABEL//////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblTitre = new JLabel("CATALOGUE D'OUVRAGES");
		lblTitre.setForeground(new Color(255, 215, 0));
		lblTitre.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(39, 20, 921, 34);
		contentPane.add(lblTitre);
		
		JLabel jLabel1 = new JLabel("Auteur :");
		jLabel1.setForeground(new Color(128, 0, 0));
		jLabel1.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jLabel1.setBounds(39, 596, 61, 16);
		contentPane.add(jLabel1);
		
		
		JLabel jLabel2 = new JLabel("Editeur :");
		jLabel2.setForeground(new Color(128, 0, 0));
		jLabel2.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jLabel2.setBounds(377, 596, 61, 16);
		contentPane.add(jLabel2);
		
		JLabel jLabel3 = new JLabel("Prix TTC :");
		jLabel3.setForeground(new Color(128, 0, 0));
		jLabel3.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jLabel3.setBounds(691, 596, 86, 16);
		contentPane.add(jLabel3);
		
		
		JLabel jLabel4 = new JLabel(" Thème(s) :");
		jLabel4.setForeground(new Color(128, 0, 0));
		jLabel4.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jLabel4.setBounds(352, 484, 86, 16);
		contentPane.add(jLabel4);
		
		JLabel jLabel5 = new JLabel(" Mots-clés :");
		jLabel5.setForeground(new Color(128, 0, 0));
		jLabel5.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jLabel5.setBounds(691, 484, 86, 16);
		contentPane.add(jLabel5);

		JLabel lblNotes = new JLabel("");
		lblNotes.setForeground(new Color(255, 0, 0));
		lblNotes.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNotes.setBounds(180, 149, 646, 16);
		contentPane.add(lblNotes);
		
		
//JTABLE//////////////////////////////////////////////////////////////////////////////////

		JScrollPane jScrollPane1 = new JScrollPane(jTableLivre);
		jScrollPane1.setBounds(39, 177, 921, 279);
		jScrollPane1.setPreferredSize(new Dimension(921, 279));
		contentPane.add(jScrollPane1);
		
		nomColonnes.add("ISBN");
		nomColonnes.add("TITRE");
		nomColonnes.add("SOUS-TITRE");
		nomColonnes.add("PRIX HT");
		nomColonnes.add("TVA");
		nomColonnes.add("EDITION");
		nomColonnes.add("IMAGE");
		nomColonnes.add("RESUME");
		nomColonnes.add("PAGES");
		nomColonnes.add("STOCK");
		nomColonnes.add("COMMENTAIRE");
		nomColonnes.add("STATUT");
		
		
		
		try {
			vectorLivre = livreDAO.findAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DefaultTableModel model = new DefaultTableModel(vectorLivre, nomColonnes);
		jTableLivre = new JTable(model);
		jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
		TableColumnModel columnModel = jTableLivre.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(120);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(170);
		columnModel.getColumn(3).setPreferredWidth(40);
		columnModel.getColumn(4).setPreferredWidth(40);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(30);
		columnModel.getColumn(7).setPreferredWidth(30);
		columnModel.getColumn(8).setPreferredWidth(80);
		columnModel.getColumn(9).setPreferredWidth(80);
		columnModel.getColumn(10).setPreferredWidth(80);
		/*columnModel.getColumn(11).setPreferredWidth(80);*/
		

		jTableLivre.setForeground(new Color(128, 0, 0));
		jTableLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
        jTableLivre.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jTableLivreMouseClicked(evt);
            }
        });
		jScrollPane1.setViewportView(jTableLivre);
		
		
		
		tableAuteur = new JTable();
		tableAuteur.setBounds(102, 596, 263, 59);
		tableAuteur.setBackground(new Color(255, 248, 220));
		tableAuteur.setForeground(new Color(128, 0, 0));
		tableAuteur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		contentPane.add(tableAuteur);

		
		tableTheme = new JTable();
		tableTheme.setBounds(435, 484, 174, 34);
		tableTheme.setBackground(new Color(255, 248, 220));
		tableTheme.setForeground(new Color(128, 0, 0));
		tableTheme.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		contentPane.add(tableTheme);

		
		
		tableMotCle = new JTable();
		tableMotCle.setBounds(786, 484, 174, 39);
		tableMotCle.setBackground(new Color(255, 248, 220));
		tableMotCle.setForeground(new Color(128, 0, 0));
		tableMotCle.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		contentPane.add(tableMotCle);

		
//JCOMBOBOX//////////////////////////////////////////////////////////////////////////////////		
		
		jComboBoxLivre = new JComboBox();
		jComboBoxLivre.setForeground(new Color(128, 0, 0));
		jComboBoxLivre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jComboBoxLivre.setBounds(39, 66, 129, 46);
		jComboBoxLivre.setModel(new DefaultComboBoxModel(new String[] { "TITRE", "ISBN", "AUTEUR", "EDITEUR", "THEME", "GENRE", "MOT CLE", "STOCK", "PRIX HT", "PRIX TTC" }));
		contentPane.add(jComboBoxLivre);
		
		
		
//JTEXTFIELD//////////////////////////////////////////////////////////////////////////////////
		jTextRecherche = new JTextField();
		jTextRecherche.setForeground(new Color(128, 0, 0));
		jTextRecherche.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jTextRecherche.setBounds(39, 108, 646, 46);
		contentPane.add(jTextRecherche);
		jTextRecherche.setColumns(10);
		

		
		jTextEditeur = new JTextArea();
		jTextEditeur.setBackground(new Color(255, 248, 220));
		jTextEditeur.setForeground(new Color(128, 0, 0));
		jTextEditeur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jTextEditeur.setBounds(440, 594, 174, 34);
		contentPane.add(jTextEditeur);
		

		
		jTextPrixTTC = new JTextArea();
		jTextPrixTTC.setBackground(new Color(255, 248, 220));
		jTextPrixTTC.setForeground(new Color(128, 0, 0));
		jTextPrixTTC.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jTextPrixTTC.setBounds(786, 594, 174, 34);
		contentPane.add(jTextPrixTTC);
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 482, 296, 84);
		contentPane.add(scrollPane);
		
		jTextResume = new JTextArea();
		jTextResume.setWrapStyleWord(true);
		jTextResume.setForeground(new Color(128, 0, 0));
		jTextResume.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		jTextResume.setBackground(new Color(255, 248, 220));
		jTextResume.setColumns(20);
		scrollPane.setViewportView(jTextResume);
		jTextResume.setColumns(10);
		
		
		
//BOUTON OK//////////////////////////////////////////////////////////////////////////////////
		JButton jButtonOK = new JButton("");
		jButtonOK.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/search.png"));
		jButtonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String selection = jComboBoxLivre.getSelectedItem().toString();
				switch (selection) {
					case "TITRE" :
						String titre = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparTitre(titre);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
							jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
							TableColumnModel columnModel = jTableLivre.getColumnModel();
							columnModel.getColumn(0).setPreferredWidth(120);
							columnModel.getColumn(1).setPreferredWidth(200);
							columnModel.getColumn(2).setPreferredWidth(170);
							columnModel.getColumn(3).setPreferredWidth(40);
							columnModel.getColumn(4).setPreferredWidth(40);
							columnModel.getColumn(5).setPreferredWidth(100);
							columnModel.getColumn(6).setPreferredWidth(30);
							columnModel.getColumn(7).setPreferredWidth(30);
							columnModel.getColumn(8).setPreferredWidth(80);
							columnModel.getColumn(9).setPreferredWidth(80);
							columnModel.getColumn(10).setPreferredWidth(80);
							if (modeltable.getRowCount()==0) {
								JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre avec ce titre.", "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					case "ISBN" :
						String isbn = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparISBN(isbn);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
							jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
							TableColumnModel columnModel = jTableLivre.getColumnModel();
							columnModel.getColumn(0).setPreferredWidth(120);
							columnModel.getColumn(1).setPreferredWidth(200);
							columnModel.getColumn(2).setPreferredWidth(170);
							columnModel.getColumn(3).setPreferredWidth(40);
							columnModel.getColumn(4).setPreferredWidth(40);
							columnModel.getColumn(5).setPreferredWidth(100);
							columnModel.getColumn(6).setPreferredWidth(30);
							columnModel.getColumn(7).setPreferredWidth(30);
							columnModel.getColumn(8).setPreferredWidth(80);
							columnModel.getColumn(9).setPreferredWidth(80);
							columnModel.getColumn(10).setPreferredWidth(80);
							if (modeltable.getRowCount()==0) {
								JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre avec ce numéro d'ISBN.", "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "AUTEUR":
						String auteur = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparAuteur(auteur);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
							jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
							TableColumnModel columnModel = jTableLivre.getColumnModel();
							columnModel.getColumn(0).setPreferredWidth(120);
							columnModel.getColumn(1).setPreferredWidth(200);
							columnModel.getColumn(2).setPreferredWidth(170);
							columnModel.getColumn(3).setPreferredWidth(40);
							columnModel.getColumn(4).setPreferredWidth(40);
							columnModel.getColumn(5).setPreferredWidth(100);
							columnModel.getColumn(6).setPreferredWidth(30);
							columnModel.getColumn(7).setPreferredWidth(30);
							columnModel.getColumn(8).setPreferredWidth(80);
							columnModel.getColumn(9).setPreferredWidth(80);
							columnModel.getColumn(10).setPreferredWidth(80);
							if (modeltable.getRowCount()==0) {
								JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre associé à cet auteur.", "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "EDITEUR":
						String editeur = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparEditeur(editeur);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
							jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
							TableColumnModel columnModel = jTableLivre.getColumnModel();
							columnModel.getColumn(0).setPreferredWidth(120);
							columnModel.getColumn(1).setPreferredWidth(200);
							columnModel.getColumn(2).setPreferredWidth(170);
							columnModel.getColumn(3).setPreferredWidth(40);
							columnModel.getColumn(4).setPreferredWidth(40);
							columnModel.getColumn(5).setPreferredWidth(100);
							columnModel.getColumn(6).setPreferredWidth(30);
							columnModel.getColumn(7).setPreferredWidth(30);
							columnModel.getColumn(8).setPreferredWidth(80);
							columnModel.getColumn(9).setPreferredWidth(80);
							columnModel.getColumn(10).setPreferredWidth(80);
							if (modeltable.getRowCount()==0) {
								JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre associé à cet éditeur.", "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "THEME":
						String theme = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparTheme(theme);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
							jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
							TableColumnModel columnModel = jTableLivre.getColumnModel();
							columnModel.getColumn(0).setPreferredWidth(120);
							columnModel.getColumn(1).setPreferredWidth(200);
							columnModel.getColumn(2).setPreferredWidth(170);
							columnModel.getColumn(3).setPreferredWidth(40);
							columnModel.getColumn(4).setPreferredWidth(40);
							columnModel.getColumn(5).setPreferredWidth(100);
							columnModel.getColumn(6).setPreferredWidth(30);
							columnModel.getColumn(7).setPreferredWidth(30);
							columnModel.getColumn(8).setPreferredWidth(80);
							columnModel.getColumn(9).setPreferredWidth(80);
							columnModel.getColumn(10).setPreferredWidth(80);
							if (modeltable.getRowCount()==0) {
								JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre associé à ce thème.", "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "GENRE":
						String genre = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparGenre(genre);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
							jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
							TableColumnModel columnModel = jTableLivre.getColumnModel();
							columnModel.getColumn(0).setPreferredWidth(120);
							columnModel.getColumn(1).setPreferredWidth(200);
							columnModel.getColumn(2).setPreferredWidth(170);
							columnModel.getColumn(3).setPreferredWidth(40);
							columnModel.getColumn(4).setPreferredWidth(40);
							columnModel.getColumn(5).setPreferredWidth(100);
							columnModel.getColumn(6).setPreferredWidth(30);
							columnModel.getColumn(7).setPreferredWidth(30);
							columnModel.getColumn(8).setPreferredWidth(80);
							columnModel.getColumn(9).setPreferredWidth(80);
							columnModel.getColumn(10).setPreferredWidth(80);
							if (modeltable.getRowCount()==0) {
								JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre associé à ce genre.", "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "MOT CLE" :
						String motcle = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparMotCle(motcle);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
							jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
							TableColumnModel columnModel = jTableLivre.getColumnModel();
							columnModel.getColumn(0).setPreferredWidth(120);
							columnModel.getColumn(1).setPreferredWidth(200);
							columnModel.getColumn(2).setPreferredWidth(170);
							columnModel.getColumn(3).setPreferredWidth(40);
							columnModel.getColumn(4).setPreferredWidth(40);
							columnModel.getColumn(5).setPreferredWidth(100);
							columnModel.getColumn(6).setPreferredWidth(30);
							columnModel.getColumn(7).setPreferredWidth(30);
							columnModel.getColumn(8).setPreferredWidth(80);
							columnModel.getColumn(9).setPreferredWidth(80);
							columnModel.getColumn(10).setPreferredWidth(80);
							if (modeltable.getRowCount()==0) {
								JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre associé à ce mot-clé.", "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case  "STOCK" :
						String stock = jTextRecherche.getText();
						Pattern p = Pattern.compile("[0-9]*");
						Matcher m = p.matcher(stock);
						boolean b = m.matches();
						if (b) {
							try {
								vectorLivre = livreDAO.rechercheLivreparStock(stock);
								DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
								jTableLivre.setModel(modeltable);
								jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
								TableColumnModel columnModel = jTableLivre.getColumnModel();
								columnModel.getColumn(0).setPreferredWidth(120);
								columnModel.getColumn(1).setPreferredWidth(200);
								columnModel.getColumn(2).setPreferredWidth(170);
								columnModel.getColumn(3).setPreferredWidth(40);
								columnModel.getColumn(4).setPreferredWidth(40);
								columnModel.getColumn(5).setPreferredWidth(100);
								columnModel.getColumn(6).setPreferredWidth(30);
								columnModel.getColumn(7).setPreferredWidth(30);
								columnModel.getColumn(8).setPreferredWidth(80);
								columnModel.getColumn(9).setPreferredWidth(80);
								columnModel.getColumn(10).setPreferredWidth(80);
								int alert = Integer.parseInt(stock);
								if (alert<15) {
									lblNotes.setText("Attention seuil d'alerte du niveau de stock dépassé !");
								}
								if (modeltable.getRowCount()==0) {
									JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre dont le stock est inférieur à " + stock, "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres pour la recherche de stiock", "Erreur saisie", JOptionPane.INFORMATION_MESSAGE);
						}
						break;
					case  "PRIX HT" :
						String prixHT = jTextRecherche.getText().replace(",", ".");
						p = Pattern.compile("[0-9]*\\.?[0-9]*|[0-9]*\\,?[0-9]*");
						m = p.matcher(prixHT);
						b = m.matches();
						if (b) {
							try {
								vectorLivre = livreDAO.rechercheLivreparPrixHT(prixHT);
								DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
								jTableLivre.setModel(modeltable);
								jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
								TableColumnModel columnModel = jTableLivre.getColumnModel();
								columnModel.getColumn(0).setPreferredWidth(120);
								columnModel.getColumn(1).setPreferredWidth(200);
								columnModel.getColumn(2).setPreferredWidth(170);
								columnModel.getColumn(3).setPreferredWidth(40);
								columnModel.getColumn(4).setPreferredWidth(40);
								columnModel.getColumn(5).setPreferredWidth(100);
								columnModel.getColumn(6).setPreferredWidth(30);
								columnModel.getColumn(7).setPreferredWidth(30);
								columnModel.getColumn(8).setPreferredWidth(80);
								columnModel.getColumn(9).setPreferredWidth(80);
								columnModel.getColumn(10).setPreferredWidth(80);
								if (modeltable.getRowCount()==0) {
									JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre dont le prix est inférieur à "+prixHT+" € HT.", "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres pour la recherche de prix", "Erreur saisie", JOptionPane.INFORMATION_MESSAGE);
						}
						break;
					case "PRIX TTC" :
						String recupPrix = jTextRecherche.getText().replace(",", ".");
						//float prixTTC = Float.parseFloat(recupPrix);
						p = Pattern.compile("[0-9]*\\.?[0-9]*|[0-9]*\\,?[0-9]*");
						m = p.matcher(recupPrix);
						b = m.matches();
						if (b) {
							try {
								vectorLivre = livreDAO.rechercheLivreparPrixTTC(recupPrix);
								DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
								jTableLivre.setModel(modeltable);
								jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
								TableColumnModel columnModel = jTableLivre.getColumnModel();
								columnModel.getColumn(0).setPreferredWidth(120);
								columnModel.getColumn(1).setPreferredWidth(200);
								columnModel.getColumn(2).setPreferredWidth(170);
								columnModel.getColumn(3).setPreferredWidth(40);
								columnModel.getColumn(4).setPreferredWidth(40);
								columnModel.getColumn(5).setPreferredWidth(100);
								columnModel.getColumn(6).setPreferredWidth(30);
								columnModel.getColumn(7).setPreferredWidth(30);
								columnModel.getColumn(8).setPreferredWidth(80);
								columnModel.getColumn(9).setPreferredWidth(80);
								columnModel.getColumn(10).setPreferredWidth(80);
								if (modeltable.getRowCount()==0) {
									JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de livre dont le prix est inférieur à "+recupPrix+" € TTC.", "Livre introuvable", JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							lblNotes.setText("");
							jTextRecherche.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres pour la recherche de prix", "Erreur saisie", JOptionPane.INFORMATION_MESSAGE);
						}
						break;
				}
			    
			}
		});
		//jButtonOK.setForeground(new Color(128, 0, 0));
		//jButtonOK.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		//jButtonOK.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		jButtonOK.setBounds(723, 95, 80, 57);
		contentPane.add(jButtonOK);
		

//BOUTON QUITTER//////////////////////////////////////////////////////////////////////////////////
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setForeground(new Color(128, 0, 0));
		btnQuitter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnQuitter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(843, 679, 117, 29);
		contentPane.add(btnQuitter);
		
		
		
		
		
//BOUTON AJOUTER//////////////////////////////////////////////////////////////////////////////////
		JButton jButtonAjouterLivre = new JButton("Ajouter");
		jButtonAjouterLivre.setForeground(new Color(128, 0, 0));
		jButtonAjouterLivre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jButtonAjouterLivre.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		jButtonAjouterLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogLivreAjout jdialog = new JDialogLivreAjout();
				jdialog.setVisible(true);
			}
		});
		jButtonAjouterLivre.setBounds(39, 679, 117, 29);
		contentPane.add(jButtonAjouterLivre);
		
	
		
//BOUTON MODIFIER//////////////////////////////////////////////////////////////////////////////////
		JButton jButtonModifierLivre = new JButton("Modifier");
		jButtonModifierLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = jTableLivre.getSelectedRow();
				String isbn = (String) jTableLivre.getValueAt(row, 0);
				String titre = (String) jTableLivre.getValueAt(row, 1);
				String sousTitre = (String) jTableLivre.getValueAt(row, 2);
				Float prixHT = (Float) jTableLivre.getValueAt(row, 3);
				String tvaId = (String) jTableLivre.getValueAt(row, 4);
				String dateEdition = (String) jTableLivre.getValueAt(row, 5);
				String image = (String) jTableLivre.getValueAt(row, 6);
				String resume = (String) jTableLivre.getValueAt(row, 7);
				Float nbrePages = (Float) jTableLivre.getValueAt(row, 8);
				String stock = (String) jTableLivre.getValueAt(row, 9);
				String comment = (String) jTableLivre.getValueAt(row, 10);
				String statut = (String) jTableLivre.getValueAt(row, 11);
						
				Livre livre = new Livre (isbn, titre, sousTitre, prixHT, tvaId, dateEdition, image, resume, nbrePages, stock, comment, statut);
				JDialogLivreAffichage jda = new JDialogLivreAffichage(livre);
				jda.setVisible(true);
				
				
				
			}
		});
		jButtonModifierLivre.setForeground(new Color(128, 0, 0));
		jButtonModifierLivre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jButtonModifierLivre.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		jButtonModifierLivre.setBounds(463, 680, 117, 29);
		contentPane.add(jButtonModifierLivre);
		
		
		
		
//BOUTON RAFRAICHIR//////////////////////////////////////////////////////////////////////////////////		
		JButton jButtonRefresh = new JButton("");
		jButtonRefresh.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/refresh32px.png"));
		jButtonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					vectorLivre = livreDAO.findAll();
					
					DefaultTableModel model = new DefaultTableModel(vectorLivre, nomColonnes);
					jTableLivre = new JTable(model);
					jTableLivre.setAutoResizeMode(jTableLivre.AUTO_RESIZE_OFF);
					TableColumnModel columnModel = jTableLivre.getColumnModel();
					columnModel.getColumn(0).setPreferredWidth(120);
					columnModel.getColumn(1).setPreferredWidth(200);
					columnModel.getColumn(2).setPreferredWidth(170);
					columnModel.getColumn(3).setPreferredWidth(40);
					columnModel.getColumn(4).setPreferredWidth(40);
					columnModel.getColumn(5).setPreferredWidth(100);
					columnModel.getColumn(6).setPreferredWidth(30);
					columnModel.getColumn(7).setPreferredWidth(30);
					columnModel.getColumn(8).setPreferredWidth(80);
					columnModel.getColumn(9).setPreferredWidth(80);
					columnModel.getColumn(10).setPreferredWidth(80);
					jScrollPane1.setViewportView(jTableLivre);
					jTableLivre.setForeground(new Color(128, 0, 0));
					jTableLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblNotes.setText("");
				jTextRecherche.setText("");
			}
		});
		//jButtonRefresh.setForeground(new Color(128, 0, 0));
		//jButtonRefresh.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		//jButtonRefresh.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		jButtonRefresh.setBounds(839, 95, 80, 57);
		contentPane.add(jButtonRefresh);
		

		
		
		
		
		
		

	}
	
	private void jTableLivreMouseClicked(MouseEvent evt) {//GEN-FIRST:event_jTableLivreMouseClicked
	    
		int row = jTableLivre.getSelectedRow();
		jTextResume.setText((String) jTableLivre.getValueAt(row, 7));
        float prixHT = (Float) jTableLivre.getValueAt(row, 3);
        String tauxTVA= (String) jTableLivre.getValueAt(row, 4);
        float prixhorsTaxe =prixHT;
        float txTva = Float.parseFloat(tauxTVA);
        float prixTTC= prixhorsTaxe + prixhorsTaxe*(txTva/100);
        String prix = df.format(prixTTC) + " ";
        jTextPrixTTC.setText(prix + "€");
        
        
        
        
        String isbn = (String) jTableLivre.getValueAt(row, 0);
        try {
			Vector auteurs = livreDAO.recupererAuteur(isbn);
			Vector nomCol = new Vector();
			nomCol.add("");
			nomCol.add("");
			nomCol.add("");
			DefaultTableModel modelAuteur = new DefaultTableModel(auteurs, nomCol);
			tableAuteur.setModel(modelAuteur);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
        
        
        try {
			String editeur = livreDAO.recupererEditeur(isbn);
			jTextEditeur.setText(editeur);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			Vector themes = livreDAO.recupererTheme(isbn);
			Vector nomCol = new Vector();
			nomCol.add("");
			DefaultTableModel modelThemes = new DefaultTableModel(themes, nomCol);
			tableTheme.setModel(modelThemes);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

		try {
	        Vector motcle = livreDAO.recupererMotCle(isbn);
	        Vector nomCol = new Vector();
			nomCol.add("");
			DefaultTableModel modelMotCle = new DefaultTableModel(motcle, nomCol);
			tableMotCle.setModel(modelMotCle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
        
    }//GEN-LAST:event_jTableLivreMouseClicked
	
	
	


    private void jButtonModifierLivreActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonModifierLivreActionPerformed

        for (int ligne =0; ligne< jTableLivre.getRowCount(); ligne++){
	        if (jTableLivre.isRowSelected(ligne)){
	            /*Vector res= livreLignes.get(ligne);
	            JFrameAjouter modif= new JFrameAjouter(res);
	            modif.setVisible(true);*/
	            this.dispose();
	
	        }  
        }//GEN-LAST:event_jButtonModifierLivreActionPerformed
    }
    
    private void jButtonAjouterLivreActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterLivreActionPerformed
		//JFrameAjouter ajouter= new  JFrameAjouter();  
		//ajouter.setVisible(true);
		dispose();
    }//GEN-LAST:event_jButtonAjouterLivreActionPerformed
}
