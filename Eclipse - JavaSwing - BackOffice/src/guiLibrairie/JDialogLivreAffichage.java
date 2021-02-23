package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daoLibrairie.LivreDAO;
import entitiesLibrairie.Livre;

public class JDialogLivreAffichage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtISBN;
	private JTextField txtTitre;
	private JTextField txtSousTitre;
	private JTextField txtSimage;
	private JComboBox cmbBxAcces;
	private LivreDAO livreDAO = new LivreDAO();
	private Livre livre = new Livre();
	private JTextArea txtResume;
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
	private String stockk;
	private String comment; 
	private String statut;
	private JLabel lblEditeurRecup;
	private String statutFrame = "AJOUT LIVRE";
	//private DefaultListModel modelThemmeeee;
	private DefaultListModel modelTheme2 ;
	private JTable tableAuteur;
	private static Livre livret;
	private Livre livreTransporte;
	private JTextField textFieldDate;
	private DefaultTableModel modelAuteur;
	private JTable tableMotCle;
	private JTable tableTheme;
	private DefaultTableModel modelThemes; 
	private DefaultTableModel modelMotCle;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogLivreAffichage dialog = new JDialogLivreAffichage(livret);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogLivreAffichage(Livre livre) {
		this.livreTransporte = livre;
		String isbnTransporte = livreTransporte.getLivreISBN();
		
		setBounds(100, 100, 850, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 248, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	
		
//JLABEL //////////////////////////////////////////////////////////////////////////////////////////////		
		
		JLabel lblTitre = new JLabel("LIVRE");
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
		try {
			String editeur = livreDAO.recupererEditeur(isbnTransporte);
			lblEditeurRecup.setText(editeur);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		contentPanel.add(lblEditeurRecup);
		
		
//JTEXTFIELDS //////////////////////////////////////////////////////////////////////////////////////////////		
		
		txtISBN = new JTextField();
		txtISBN.setForeground(new Color(128, 0, 0));
		txtISBN.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtISBN.setBounds(116, 115, 256, 26);
		contentPanel.add(txtISBN);
		txtISBN.setColumns(10);
		txtISBN.setText(livreTransporte.getLivreISBN());
		txtISBN.setEnabled(false);
		
		txtTitre = new JTextField();
		txtTitre.setForeground(new Color(128, 0, 0));
		txtTitre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtTitre.setColumns(10);
		txtTitre.setBounds(116, 175, 256, 26);
		txtTitre.setText(livreTransporte.getLivreTitre());
		txtTitre.setEnabled(false);
		contentPanel.add(txtTitre);
		
		txtSousTitre = new JTextField();
		txtSousTitre.setForeground(new Color(128, 0, 0));
		txtSousTitre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSousTitre.setColumns(10);
		txtSousTitre.setBounds(158, 230, 214, 26);
		txtSousTitre.setText(livreTransporte.getSousTitre());
		txtSousTitre.setEnabled(false);
		contentPanel.add(txtSousTitre);
		
		txtSimage = new JTextField();
		txtSimage.setForeground(new Color(128, 0, 0));
		txtSimage.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSimage.setColumns(10);
		txtSimage.setBounds(518, 115, 287, 26);
		txtSimage.setText(livreTransporte.getLivreImage());
		//txtSaisieMDP.setEnabled(false);
		contentPanel.add(txtSimage);
		
		txtResume = new JTextArea();
		txtResume.setForeground(new Color(128, 0, 0));
		txtResume.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtResume.setColumns(10);
		txtResume.setBounds(528, 160, 275, 58);
		txtResume.setText(livreTransporte.getLivreResume());
		contentPanel.add(txtResume);
		
		txtStatut = new JTextField();
		txtStatut.setForeground(new Color(128, 0, 0));
		txtStatut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtStatut.setColumns(10);
		txtStatut.setBounds(528, 395, 277, 26);
		txtStatut.setText(livreTransporte.getLivreStatut());
		contentPanel.add(txtStatut);
		
		txtComment = new JTextField();
		txtComment.setForeground(new Color(128, 0, 0));
		txtComment.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtComment.setColumns(10);
		txtComment.setBounds(571, 324, 234, 59);
		txtComment.setText(livreTransporte.getLivreComment());
		contentPanel.add(txtComment);
		
		textFieldDate = new JTextField();
		textFieldDate.setText((String) null);
		textFieldDate.setForeground(new Color(128, 0, 0));
		textFieldDate.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		textFieldDate.setEnabled(false);
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(187, 395, 185, 26);
		textFieldDate.setText(livreTransporte.getLivreDateEdition());
		textFieldDate.setEnabled(false);
		contentPanel.add(textFieldDate);
		

		
		
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
		cmbBxTva.setSelectedItem(livreTransporte.getTvaId());
		//cmbBxTva.setEnabled(false);
		contentPanel.add(cmbBxTva);
		

		

		
		
		
//JSPINNER //////////////////////////////////////////////////////////////////////////////////////////////	
		SpinnerNumberModel modelSpinnerPages = new SpinnerNumberModel(1, 1, 2500, 1);
		
		JSpinner spinnerPages = new JSpinner(modelSpinnerPages);
		spinnerPages.setForeground(new Color(128, 0, 0));
		spinnerPages.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		spinnerPages.setBounds(604, 230, 201, 35);
		spinnerPages.setEnabled(false);
		spinnerPages.setValue(livreTransporte.getLivreNbrePage());
		contentPanel.add(spinnerPages);
		
		
		SpinnerNumberModel modelSpinnerStock = new SpinnerNumberModel(100, 1, 300, 1);
		JSpinner spinnerStock = new JSpinner(modelSpinnerStock);
		spinnerStock.setForeground(new Color(128, 0, 0));
		spinnerStock.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		spinnerStock.setBounds(571, 277, 234, 35);
		float stock = Float.parseFloat(livreTransporte.getLivreStock());
		spinnerStock.setValue(stock);
		contentPanel.add(spinnerStock);
		
		SpinnerNumberModel modelSpinnerPrix = new SpinnerNumberModel(0.00, 0.00, 2500.00, 0.1);
		JSpinner spinnerPrix = new JSpinner(modelSpinnerPrix);
		spinnerPrix.setForeground(new Color(128, 0, 0));
		spinnerPrix.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		spinnerPrix.setBounds(138, 281, 234, 35);
		spinnerPrix.setValue(livreTransporte.getLivrePrixHT());
		contentPanel.add(spinnerPrix);
		
//JTABLE//////////////////////////////////////////////////////////////////////////////////////////////	
		//JScrollPane scrollPaneTheme = new JScrollPane();
		//scrollPaneTheme.setBounds(644, 511, 161, 36);
		//contentPanel.add(scrollPaneTheme);
		try {
			Vector < Vector> themes = livreDAO.recupererTheme(isbnTransporte);
			Vector nomCol = new Vector();
			nomCol.add("");
			modelThemes = new DefaultTableModel(themes, nomCol);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		tableTheme = new JTable();
		tableTheme.setModel(modelThemes);
		tableTheme.setBounds(644, 511, 161, 36);
		tableTheme.setBackground(new Color(255, 248, 220));
		tableTheme.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		contentPanel.add(tableTheme);
		//scrollPaneTheme.setViewportView(tableTheme);
		
		
		//JScrollPane scrollPaneMotCle = new JScrollPane();
		//scrollPaneMotCle.setBounds(644, 459, 161, 37);
		//contentPanel.add(scrollPaneMotCle);
		try {
			Vector <Vector> motcle = livreDAO.recupererMotCle(isbnTransporte);
			Vector nomCol = new Vector();
			nomCol.add("");
			modelMotCle = new DefaultTableModel(motcle, nomCol);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		tableMotCle = new JTable();
		tableMotCle.setModel(modelMotCle);
		tableMotCle.setBounds(644, 459, 161, 37);
		tableMotCle.setBackground(new Color(255, 248, 220));
		tableMotCle.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		contentPanel.add(tableMotCle);
		//scrollPaneMotCle.setViewportView(tableMotCle);
		
				
		//JScrollPane scrollPaneAuteur = new JScrollPane();
		//scrollPaneAuteur.setBounds(256, 459, 171, 37);
		//contentPanel.add(scrollPaneAuteur);
		
		try {
			Vector <Vector> auteurs = livreDAO.recupererAuteur(isbnTransporte);
			Vector nomCol = new Vector();
			nomCol.add("nom");
			nomCol.add("prenom");
			nomCol.add("pseudo");
			modelAuteur = new DefaultTableModel(auteurs, nomCol);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tableAuteur = new JTable();
		tableAuteur.setModel(modelAuteur);
		tableAuteur.setBounds(256, 459, 171, 37);
		tableAuteur.setBackground(new Color(255, 248, 220));
		tableAuteur.setFont(new Font("Avenir Next", Font.PLAIN, 12));
		contentPanel.add(tableAuteur);
		//scrollPaneAuteur.setViewportView(tableAuteur);
		
//BOUTON MODIFIER //////////////////////////////////////////////////////////////////////////////////////////////		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				isbn = txtISBN.getText();
				titre = txtTitre.getText();
				sousTitre = txtSousTitre.getText();
				String prixprix = spinnerPrix.getValue().toString();
				prix = Float.parseFloat(prixprix);
				tva = cmbBxTva.getSelectedItem().toString();
				dateEdition = textFieldDate.getText();
				image = txtSimage.getText();
				resume = txtResume.getText();
				String pages = spinnerPages.getValue().toString();
				nbrPages = Float.parseFloat(pages);
				String stockk = spinnerStock.getValue().toString();
				comment = txtComment.getText();
				statut = txtStatut.getText();
				Livre livreModif = new Livre (isbn, titre, sousTitre, prix, tva, dateEdition, image, resume, nbrPages, stockk, comment, statut);
				
				try {
					livreDAO.modifierLivre(livreModif, tva);
					JOptionPane.showMessageDialog(contentPanel, "Livre modifié avec succès", "Modification réussie", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnModifier.setForeground(new Color(128, 0, 0));
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.setActionCommand("OK");
		btnModifier.setBounds(627, 44, 178, 37);
		contentPanel.add(btnModifier);
		
		
		

		
		
//BOUTON AUTEUR //////////////////////////////////////////////////////////////////////////////////////////////			
		JButton btnAuteur = new JButton("Auteur");
		btnAuteur.setForeground(new Color(128, 0, 0));
		btnAuteur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAuteur.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAuteur.setActionCommand("OK");
		btnAuteur.setBounds(70, 459, 178, 37);
		contentPanel.add(btnAuteur);
		
		

		
//BOUTON EDITEUR //////////////////////////////////////////////////////////////////////////////////////////////	
		JButton btnEditeur = new JButton("Editeur");
		btnEditeur.setForeground(new Color(128, 0, 0));
		btnEditeur.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnEditeur.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnEditeur.setActionCommand("OK");
		btnEditeur.setBounds(70, 511, 178, 37);
		contentPanel.add(btnEditeur);

		

//BOUTON MOT CLE //////////////////////////////////////////////////////////////////////////////////////////////	
		
		JButton btnMotcle = new JButton("Mot-clé");
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
