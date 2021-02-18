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

public class JFrameLivree extends JFrame {

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
					JFrameLivree frame = new JFrameLivree();
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
	public JFrameLivree() {
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
		
		/*TableColumnModel columnModel = jTableLivre.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(100);
		columnModel.getColumn(2).setPreferredWidth(100);
		columnModel.getColumn(3).setPreferredWidth(40);
		columnModel.getColumn(4).setPreferredWidth(40);
		columnModel.getColumn(5).setPreferredWidth(70);
		columnModel.getColumn(6).setPreferredWidth(30);
		columnModel.getColumn(7).setPreferredWidth(150);
		columnModel.getColumn(8).setPreferredWidth(80);
		columnModel.getColumn(9).setPreferredWidth(80);
		columnModel.getColumn(10).setPreferredWidth(80);
		columnModel.getColumn(11).setPreferredWidth(80);*/
		
		jTableLivre = new JTable(model);
        jTableLivre.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jTableLivreMouseClicked(evt);
            }
        });
		jScrollPane1.setViewportView(jTableLivre);
		
		
		
		tableAuteur = new JTable();
		tableAuteur.setBounds(97, 596, 268, 59);
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
		jComboBoxLivre.setBounds(39, 90, 129, 46);
		jComboBoxLivre.setModel(new DefaultComboBoxModel(new String[] { "TITRE", "ISBN", "AUTEUR", "EDITEUR", "THEME", "GENRE", "MOT CLE", "STOCK", "PRIX HT", "PRIX TTC" }));
		contentPane.add(jComboBoxLivre);
		
		
		
//JTEXTFIELD//////////////////////////////////////////////////////////////////////////////////
		jTextRecherche = new JTextField();
		jTextRecherche.setBounds(180, 89, 646, 46);
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
		jTextResume.setForeground(new Color(128, 0, 0));
		jTextResume.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		jTextResume.setBackground(new Color(255, 248, 220));
		jTextResume.setColumns(20);
		scrollPane.setViewportView(jTextResume);
		jTextResume.setColumns(10);
		
		
		
//BOUTON OK//////////////////////////////////////////////////////////////////////////////////
		JButton jButtonOK = new JButton("OK");
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
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "ISBN" :
						String isbn = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparISBN(isbn);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
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
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case  "STOCK" :
						String stock = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparStock(stock);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case  "PRIX HT" :
						String prixHT = jTextRecherche.getText();
						try {
							vectorLivre = livreDAO.rechercheLivreparPrixHT(prixHT);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case "PRIX TTC" :
						float prixTTC = Float.parseFloat(jTextRecherche.getText());
						try {
							vectorLivre = livreDAO.rechercheLivreparPrixTTC(prixTTC);
							DefaultTableModel modeltable = new DefaultTableModel(vectorLivre, nomColonnes);
							jTableLivre.setModel(modeltable);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
				}
			    
			}
		});
		jButtonOK.setForeground(new Color(128, 0, 0));
		jButtonOK.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jButtonOK.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		jButtonOK.setBounds(843, 98, 117, 29);
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
		jButtonModifierLivre.setForeground(new Color(128, 0, 0));
		jButtonModifierLivre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		jButtonModifierLivre.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		jButtonModifierLivre.setBounds(463, 680, 117, 29);
		contentPane.add(jButtonModifierLivre);
		
		
		
		
		
		

	}
	
	private void jTableLivreMouseClicked(MouseEvent evt) {//GEN-FIRST:event_jTableLivreMouseClicked
	    
		int row = jTableLivre.getSelectedRow();
		jTextResume.setText((String) jTableLivre.getValueAt(row, 7));
        float prixHT = (Float) jTableLivre.getValueAt(row, 3);
        String tauxTVA= (String) jTableLivre.getValueAt(row, 4);
        float prixhorsTaxe =prixHT;
        float txTva = Float.parseFloat(tauxTVA);
        float prixTTC= prixhorsTaxe + prixhorsTaxe*(txTva/100);
        String prix = prixTTC + " ";
        jTextPrixTTC.setText(prix);
        
        
        
        
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
		JFrameAjouter ajouter= new  JFrameAjouter();  
		ajouter.setVisible(true);
		dispose();
    }//GEN-LAST:event_jButtonAjouterLivreActionPerformed
}
