package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daoLibrairie.EmployeDAO;
import daoLibrairie.LivreDAO;
import entitiesLibrairie.Employe;
import entitiesLibrairie.Livre;
import entitiesLibrairie.Theme;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JDialogLivreAjout extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtISBN;
	private JTextField txtTitre;
	private JTextField txtSousTitre;
	private JTextField txtSimage;
	private JComboBox cmbBxAcces;
	private LivreDAO livreDAO = new LivreDAO();
	private Livre livre = new Livre();
	private JTextField txtResume;
	private JTextField txtStatut;
	private JTextField txtComment;
	private String isbn;
	private String titre; 
	private String sousTitre; 
	private float prix; 
	private String tva; 
	private String dateEdition;
	private String image;
	private String resume;
	private float nbrPages;
	private String stock;
	private String comment; 
	private String statut;
	private JLabel lblEditeurRecup;
	private JList listTheme;
	private JList listAuteur;
	private JList listMotCle;
	private JList listGenre;
	private String statutFrame = "AJOUT LIVRE";
	//private DefaultListModel modelThemmeeee;
	private DefaultListModel modelTheme2 ;
	private DefaultTableModel modelAuteur2 = new DefaultTableModel() ;
	private String modelEditeur2 ;
	private DefaultListModel modelMotCle2 ;
	private DefaultListModel modelGenre2 ;
	private JTable tableAuteur;
	private SimpleDateFormat formater = null;
	private Date currentDate ;

	
	
	public DefaultTableModel refreshAuteur (DefaultTableModel modelAuteur) {
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				//lblThèmeRecup.setText(theme.getThemeNom());
				tableAuteur.setModel(modelAuteur);
			}
		});
		this.modelAuteur2 = modelAuteur;
		return modelAuteur2;
	}
	
	
	public String refreshEditeur (String modelEditeur) {
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				//lblThèmeRecup.setText(theme.getThemeNom());
				lblEditeurRecup.setText(modelEditeur);
				
}
		});
		this.modelEditeur2 = modelEditeur;
		return modelEditeur2;
	}
	
	public DefaultListModel refreshGenre (DefaultListModel modelGenre) {
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				//lblThèmeRecup.setText(theme.getThemeNom());
				listGenre.setModel(modelGenre);
				System.out.println(modelGenre);

			}
		});
		this.modelGenre2 = modelGenre;
		return modelGenre2;
	}
	
	public DefaultListModel refreshMotCle (DefaultListModel modelMotCle) {
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				//lblThèmeRecup.setText(theme.getThemeNom());
				listMotCle.setModel(modelMotCle);
				

			}
		});
		this.modelMotCle2 = modelMotCle;
		return modelMotCle2;
	}
	
	public DefaultListModel refreshTheme (DefaultListModel modelTheme) {
		addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				//lblThèmeRecup.setText(theme.getThemeNom());
				listTheme.setModel(modelTheme);
				

			}
		});
		this.modelTheme2 = modelTheme;
		return modelTheme2;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogLivreAjout dialog = new JDialogLivreAjout();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogLivreAjout() {

		
		setBounds(100, 100, 850, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 248, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	
		
//JLABEL //////////////////////////////////////////////////////////////////////////////////////////////		
		
		JLabel lblTitre = new JLabel("AJOUT NOUVEAU LIVRE");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
		lblTitre.setForeground(new Color(255, 215, 0));
		lblTitre.setBounds(70, 44, 711, 37);
		contentPanel.add(lblTitre);
		
		
		JLabel lblISBN = new JLabel("ISBN :");
		lblISBN.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblISBN.setForeground(new Color(128, 0, 0));
		lblISBN.setBounds(70, 120, 160, 16);
		contentPanel.add(lblISBN);
		
		JLabel lblTitreLivre = new JLabel("Titre :");
		lblTitreLivre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblTitreLivre.setForeground(new Color(128, 0, 0));
		lblTitreLivre.setBounds(70, 180, 178, 16);
		contentPanel.add(lblTitreLivre);
		
		JLabel lblSousTitre = new JLabel("Sous-titre :");
		lblSousTitre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSousTitre.setForeground(new Color(128, 0, 0));
		lblSousTitre.setBounds(70, 235, 178, 16);
		contentPanel.add(lblSousTitre);
		
		JLabel lblTva = new JLabel("TVA :");
		lblTva.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblTva.setForeground(new Color(128, 0, 0));
		lblTva.setBounds(70, 350, 225, 16);
		contentPanel.add(lblTva);
		
		JLabel lblPrix = new JLabel("Prix HT :");
		lblPrix.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblPrix.setForeground(new Color(128, 0, 0));
		lblPrix.setBounds(70, 296, 178, 16);
		contentPanel.add(lblPrix);
		
		JLabel lblDateEdition = new JLabel("Date d'édition : ");
		lblDateEdition.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblDateEdition.setForeground(new Color(128, 0, 0));
		lblDateEdition.setBounds(70, 400, 178, 16);
		contentPanel.add(lblDateEdition);
		
		JLabel lblImage = new JLabel("Image :");
		lblImage.setForeground(new Color(128, 0, 0));
		lblImage.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblImage.setBounds(458, 120, 160, 16);
		contentPanel.add(lblImage);
		
		JLabel lblResume = new JLabel("Résumé :");
		lblResume.setForeground(new Color(128, 0, 0));
		lblResume.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblResume.setBounds(458, 180, 160, 16);
		contentPanel.add(lblResume);
		
		
		JLabel lblStatut = new JLabel("Statut :");
		lblStatut.setForeground(new Color(128, 0, 0));
		lblStatut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblStatut.setBounds(458, 400, 160, 16);
		contentPanel.add(lblStatut);
		
		JLabel lblCommentaire = new JLabel("Commentaire :");
		lblCommentaire.setForeground(new Color(128, 0, 0));
		lblCommentaire.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblCommentaire.setBounds(458, 346, 160, 16);
		contentPanel.add(lblCommentaire);
		
		
		JLabel lblStock = new JLabel("Stock :");
		lblStock.setForeground(new Color(128, 0, 0));
		lblStock.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblStock.setBounds(458, 296, 160, 16);
		contentPanel.add(lblStock);
		
		JLabel lblPages = new JLabel("Nombre de pages :");
		lblPages.setForeground(new Color(128, 0, 0));
		lblPages.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblPages.setBounds(458, 240, 160, 16);
		contentPanel.add(lblPages);
		
		lblEditeurRecup = new JLabel("");
		lblEditeurRecup.setForeground(new Color(128, 0, 0));
		lblEditeurRecup.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		lblEditeurRecup.setBounds(252, 521, 163, 16);
		contentPanel.add(lblEditeurRecup);
		
		
//JTEXTFIELDS //////////////////////////////////////////////////////////////////////////////////////////////		
		
		txtISBN = new JTextField();
		txtISBN.setForeground(new Color(128, 0, 0));
		txtISBN.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtISBN.setBounds(116, 115, 256, 26);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);
		//txtISBN.setEnabled(false);
		
		txtTitre = new JTextField();
		txtTitre.setForeground(new Color(128, 0, 0));
		txtTitre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtTitre.setColumns(10);
		txtTitre.setBounds(116, 175, 256, 26);
		//txtTitre.setEnabled(false);
		contentPanel.add(txtTitre);
		
		txtSousTitre = new JTextField();
		txtSousTitre.setForeground(new Color(128, 0, 0));
		txtSousTitre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSousTitre.setColumns(10);
		txtSousTitre.setBounds(158, 230, 214, 26);
		//txtSousTitre.setEnabled(false);
		contentPanel.add(txtSousTitre);
		
		txtSimage = new JPasswordField();
		txtSimage.setForeground(new Color(128, 0, 0));
		txtSimage.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSimage.setColumns(10);
		txtSimage.setBounds(518, 115, 287, 26);
		//txtSaisieMDP.setEnabled(false);
		contentPanel.add(txtSimage);
		
		txtResume = new JTextField();
		txtResume.setForeground(new Color(128, 0, 0));
		txtResume.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtResume.setColumns(10);
		txtResume.setBounds(528, 160, 275, 58);
		contentPanel.add(txtResume);
		
		txtStatut = new JTextField();
		txtStatut.setForeground(new Color(128, 0, 0));
		txtStatut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtStatut.setColumns(10);
		txtStatut.setBounds(528, 395, 277, 26);
		contentPanel.add(txtStatut);
		
		txtComment = new JTextField();
		txtComment.setForeground(new Color(128, 0, 0));
		txtComment.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtComment.setColumns(10);
		txtComment.setBounds(571, 324, 234, 59);
		contentPanel.add(txtComment);
		
		
//JCOMBOBOX //////////////////////////////////////////////////////////////////////////////////////////////				

		
		Vector<String> cmbBxAccesModel = new Vector();
		try {
			cmbBxAccesModel=livreDAO.recupererTVA();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JComboBox cmbBxTva = new JComboBox(cmbBxAccesModel);
		cmbBxTva.setSelectedIndex(-1);
		cmbBxTva.setForeground(new Color(128, 0, 0));
		cmbBxTva.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		cmbBxTva.setBounds(116, 346, 256, 27);
		//cmbBxTva.setEnabled(false);
		contentPanel.add(cmbBxTva);
		
		formater = new SimpleDateFormat("yyyy");
		currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		String anneeCourante = formater.format(currentDate);
		int anneeEnCours = Integer.parseInt(anneeCourante);
		
		Vector annee = new Vector();
		for(int i=anneeEnCours; i>1850; i--) {
			annee.add(i);
		}
		
		JComboBox cmbBxAnneEdition = new JComboBox(annee);
		cmbBxAnneEdition.setSelectedIndex(-1);
		cmbBxAnneEdition.setForeground(new Color(128, 0, 0));
		cmbBxAnneEdition.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		//cmbBxAnneEdition.setEnabled(false);
		cmbBxAnneEdition.setBounds(301, 396, 71, 27);
		contentPanel.add(cmbBxAnneEdition);
		
		Vector<String> mois = new Vector();
		mois.add("Janvier");
		mois.add("Février");
		mois.add("Mars");
		mois.add("Avril");
		mois.add("Mai");
		mois.add("Juin");
		mois.add("Juillet");
		mois.add("Aout");
		mois.add("Septembre");
		mois.add("Octobre");
		mois.add("Novembre");
		mois.add("Décembre");
		
		JComboBox cmbBxMoisEdition = new JComboBox(mois);
		cmbBxMoisEdition.setSelectedIndex(-1);
		cmbBxMoisEdition.setForeground(new Color(128, 0, 0));
		cmbBxMoisEdition.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		//cmbBxMoisEdition.setEnabled(false);
		cmbBxMoisEdition.setBounds(189, 396, 100, 27);
		contentPanel.add(cmbBxMoisEdition);
		
		
		
//JSPINNER //////////////////////////////////////////////////////////////////////////////////////////////	
		SpinnerNumberModel modelSpinnerPages = new SpinnerNumberModel(1, 1, 2500, 1);
		
		JSpinner spinnerPages = new JSpinner(modelSpinnerPages);
		spinnerPages.setForeground(new Color(128, 0, 0));
		spinnerPages.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		spinnerPages.setBounds(604, 230, 201, 35);
		contentPanel.add(spinnerPages);
		
		
		SpinnerNumberModel modelSpinnerStock = new SpinnerNumberModel(100, 1, 300, 1);
		JSpinner spinnerStock = new JSpinner(modelSpinnerStock);
		spinnerStock.setForeground(new Color(128, 0, 0));
		spinnerStock.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		spinnerStock.setBounds(571, 277, 234, 35);
		contentPanel.add(spinnerStock);
		
		SpinnerNumberModel modelSpinnerPrix = new SpinnerNumberModel(0.00, 0.00, 2500.00, 0.1);
		JSpinner spinnerPrix = new JSpinner(modelSpinnerPrix);
		spinnerPrix.setForeground(new Color(128, 0, 0));
		spinnerPrix.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		spinnerPrix.setBounds(138, 281, 234, 35);
		contentPanel.add(spinnerPrix);
		
//JLIST//////////////////////////////////////////////////////////////////////////////////////////////	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(644, 511, 137, 36);
		contentPanel.add(scrollPane);
		
		listTheme = new JList();
		listTheme.setBackground(new Color(255, 248, 220));
		listTheme.setForeground(new Color(128, 0, 0));
		listTheme.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		scrollPane.setViewportView(listTheme);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(644, 459, 137, 37);
		contentPanel.add(scrollPane_1);
		
		listMotCle = new JList();
		listMotCle.setForeground(new Color(128, 0, 0));
		listMotCle.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		listMotCle.setBackground(new Color(255, 248, 220));
		scrollPane_1.setViewportView(listMotCle);
		
		
//JTABLE //////////////////////////////////////////////////////////////////////////////////////////////				
		JScrollPane scrollPaneAuteur = new JScrollPane();
		scrollPaneAuteur.setBounds(256, 445, 171, 51);
		contentPanel.add(scrollPaneAuteur);
		
		tableAuteur = new JTable();
		tableAuteur.setModel(modelAuteur2);
		tableAuteur.setBackground(new Color(255, 248, 220));
		tableAuteur.setForeground(new Color(128, 0, 0));
		tableAuteur.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		tableAuteur.getTableHeader().setBounds(0, 0, 171, 61);
		tableAuteur.getTableHeader().setVisible(false);
		scrollPaneAuteur.setViewportView(tableAuteur);
		
//BOUTON MODIFIER //////////////////////////////////////////////////////////////////////////////////////////////		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				isbn = txtISBN.getText();
				titre = txtTitre.getText();
				sousTitre = txtSousTitre.getText();
				String prixprix = spinnerPrix.getValue().toString();
				prix = Float.parseFloat(prixprix);
				tva = cmbBxTva.getSelectedItem().toString();
				String mois = cmbBxMoisEdition.getSelectedItem().toString();
				String annee = cmbBxAnneEdition.getSelectedItem().toString();
				dateEdition = mois + " " + annee;
				image = txtSimage.getText();
				resume = txtResume.getText();
				String pages = spinnerPages.getValue().toString();
				nbrPages = Float.parseFloat(pages);
				stock = spinnerStock.getValue().toString();
				comment = txtComment.getText();
				statut = txtStatut.getText();
				
				
				try {
					
					livre = livreDAO.ajouterLivre(isbn, titre, sousTitre, prix, tva, dateEdition, image, resume, nbrPages, stock, comment, statut);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				tableAuteur.setModel(modelAuteur2);
				for (int index = 0; index < tableAuteur.getRowCount() ; index ++) {
					String auteur = (String) tableAuteur.getValueAt(index,0);
					try {
						livreDAO.lierLivreAuteur(auteur, isbn);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
				listMotCle.setModel(modelMotCle2);
				for (int index = 0; index < modelMotCle2.size() ; index++) {
					try {
							livreDAO.lierLivreMotCle(modelMotCle2.getElementAt(index).toString(), isbn);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
				lblEditeurRecup.setText(modelEditeur2);
					try {
							livreDAO.lierLivreEditeur(modelEditeur2, isbn);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
				
				
				listTheme.setModel(modelTheme2);
				for (int index = 0; index < modelTheme2.size() ; index++) {
					try {
							livreDAO.lierLivreTheme(modelTheme2.getElementAt(index).toString(), isbn);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(contentPanel, "Nouveau livre ajouté avec succès", "Ajout de livre", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAjouter.setForeground(new Color(128, 0, 0));
		btnAjouter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAjouter.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAjouter.setActionCommand("OK");
		btnAjouter.setBounds(627, 44, 178, 37);
		contentPanel.add(btnAjouter);
		
		
		
		Container parent = this;
		JDialogLivreAjout jdLA = (JDialogLivreAjout) parent;
		
		
//BOUTON AUTEUR //////////////////////////////////////////////////////////////////////////////////////////////			
		JButton btnAuteur = new JButton("Auteur");
		btnAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrameListeCategorie jfCategories = new JFrameListeCategorie (jdLA, statutFrame);
				jfCategories.setVisible(true);
			}
		});
		btnAuteur.setForeground(new Color(128, 0, 0));
		btnAuteur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAuteur.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAuteur.setActionCommand("OK");
		btnAuteur.setBounds(70, 459, 178, 37);
		contentPanel.add(btnAuteur);
		
		

		
//BOUTON EDITEUR //////////////////////////////////////////////////////////////////////////////////////////////	
		JButton btnEditeur = new JButton("Editeur");
		btnEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrameListeCategorie jfCategories = new JFrameListeCategorie (jdLA, statutFrame);
				jfCategories.setVisible(true);
			}
		});
		btnEditeur.setForeground(new Color(128, 0, 0));
		btnEditeur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnEditeur.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnEditeur.setActionCommand("OK");
		btnEditeur.setBounds(70, 511, 178, 37);
		contentPanel.add(btnEditeur);

		

//BOUTON MOT CLE //////////////////////////////////////////////////////////////////////////////////////////////	
		
		JButton btnMotcle = new JButton("Mot-clé");
		btnMotcle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrameListeCategorie jfCategories = new JFrameListeCategorie (jdLA, statutFrame);
				jfCategories.setVisible(true);
			}
		});
		btnMotcle.setForeground(new Color(128, 0, 0));
		btnMotcle.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnMotcle.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnMotcle.setActionCommand("OK");
		btnMotcle.setBounds(458, 459, 178, 37);
		contentPanel.add(btnMotcle);
		
		
		
//BOUTON THEME //////////////////////////////////////////////////////////////////////////////////////////////	
		JButton btnTheme = new JButton("Thème");
		btnTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		
					//livre = livreDAO.ajouterLivre(isbn, titre, sousTitre, prix, tva, dateEdition, image, resume, nbrPages, stock, comment, statut);
					JFrameListeCategorie jfCategories = new JFrameListeCategorie (jdLA, statutFrame);
					jfCategories.setVisible(true);



			}
		});
		btnTheme.setForeground(new Color(128, 0, 0));
		btnTheme.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnTheme.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnTheme.setActionCommand("OK");
		btnTheme.setBounds(458, 511, 178, 37);
		contentPanel.add(btnTheme);
		

		

		

		

		

		



		
		
	}
}
