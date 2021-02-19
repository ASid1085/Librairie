package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoLibrairie.EvenementDAO;
import entitiesLibrairie.Evenement;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class JDialogEvenementAjout extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSaisieNom;
	private JTextField txtSaisieDateFin;
	private JTextField txtSaisieCodePromo;
	private JTextField txtSaisieImage;
	private JTextField txtSaisieCommentaire;
	private JTextField txtSaisieDateDebut;
	private JLabel lblDateDebut;
	private JLabel lblDateFin;
	private JLabel lblPourcentage;
	private JLabel lblCodePromo;
	private JLabel lblImage;
	private JLabel lblCommentaire;
	private JLabel lblTitre;
	private SimpleDateFormat formater = null;
	private EvenementDAO evenementDAO = new EvenementDAO();
	private Evenement evenement = new Evenement();
	private Date date ;
	private JLabel lblNewLabel;
	private JLabel lblMois;
	private JLabel lblJour;
	private JComboBox comboBoxAnnee;
	private JComboBox comboBoxMois;
	private JComboBox comboBoxJour;
	private JComboBox comboBoxAnnee_1;
	private JComboBox comboBoxMois_1;
	private JComboBox comboBoxJour_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogEvenementAjout dialog = new JDialogEvenementAjout();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogEvenementAjout() {
		setBounds(100, 100, 850, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 248, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtSaisieNom = new JTextField();
			txtSaisieNom.setForeground(new Color(128, 0, 0));
			txtSaisieNom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			txtSaisieNom.setBounds(308, 97, 421, 26);
			contentPanel.add(txtSaisieNom);
			txtSaisieNom.setColumns(10);
		}
		
		SpinnerNumberModel modelSpinner = new SpinnerNumberModel(0.0, 0.0, 5.0, 0.1);
		
		JSpinner spinner = new JSpinner(modelSpinner);
		spinner.setForeground(new Color(128, 0, 0));
		spinner.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		spinner.setBounds(308, 255, 421, 26);
		contentPanel.add(spinner);
		
		txtSaisieCodePromo = new JTextField();
		txtSaisieCodePromo.setForeground(new Color(128, 0, 0));
		txtSaisieCodePromo.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieCodePromo.setColumns(10);
		txtSaisieCodePromo.setBounds(308, 312, 421, 26);
		contentPanel.add(txtSaisieCodePromo);
		
		txtSaisieImage = new JTextField();
		txtSaisieImage.setForeground(new Color(128, 0, 0));
		txtSaisieImage.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieImage.setColumns(10);
		txtSaisieImage.setBounds(308, 374, 421, 26);
		contentPanel.add(txtSaisieImage);
		
		/*txtSaisieDateDebut = new JTextField();
		txtSaisieDateDebut.setForeground(new Color(128, 0, 0));
		txtSaisieDateDebut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieDateDebut.setColumns(10);
		txtSaisieDateDebut.setBounds(309, 222, 160, 26);
		contentPanel.add(txtSaisieDateDebut);*/
		
		

		//JComboBox comboBoxAnnee = new JComboBox();
		Vector <Integer> annee = new Vector();
		for (int i = 2020; i<=2030; i++) {
			annee.add(i);
			comboBoxAnnee = new JComboBox(annee);
		}
		comboBoxAnnee.setForeground(new Color(128, 0, 0));
		comboBoxAnnee.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		comboBoxAnnee.setBounds(308, 152, 136, 27);
		contentPanel.add(comboBoxAnnee);
		
		//JComboBox comboBoxMois = new JComboBox();
		Vector <Integer> mois = new Vector();
		for (int i = 1; i<=12; i++) {
			mois.add(i);
			comboBoxMois = new JComboBox(mois);
		}
		comboBoxMois.setForeground(new Color(128, 0, 0));
		comboBoxMois.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		comboBoxMois.setBounds(445, 152, 136, 27);
		contentPanel.add(comboBoxMois);
		
		//JComboBox comboBoxJour = new JComboBox();
		Vector <Integer> jour = new Vector();
		for (int i = 1; i<=31; i++) {
			jour.add(i);
			comboBoxJour = new JComboBox(jour);
		}
		comboBoxJour.setForeground(new Color(128, 0, 0));
		comboBoxJour.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		comboBoxJour.setBounds(593, 152, 136, 27);
		contentPanel.add(comboBoxJour);
		
		//JComboBox comboBoxAnnee_1 = new JComboBox();
		Vector <Integer> annee1 = new Vector();
		for (int i = 2020; i<=2030; i++) {
			annee1.add(i);
			comboBoxAnnee_1 = new JComboBox(annee1);
		}
		comboBoxAnnee_1.setForeground(new Color(128, 0, 0));
		comboBoxAnnee_1.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		comboBoxAnnee_1.setBounds(308, 202, 136, 27);
		contentPanel.add(comboBoxAnnee_1);
		
		//JComboBox comboBoxMois_1 = new JComboBox();
		Vector <Integer> mois1 = new Vector();
		for (int i = 1; i<=12; i++) {
			mois1.add(i);
			comboBoxMois_1 = new JComboBox(mois1);
		}
		comboBoxMois_1.setForeground(new Color(128, 0, 0));
		comboBoxMois_1.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		comboBoxMois_1.setBounds(445, 202, 136, 27);
		contentPanel.add(comboBoxMois_1);
		
		//JComboBox comboBoxJour_1 = new JComboBox();
		Vector <Integer> jour1 = new Vector();
		for (int i = 1; i<=31; i++) {
			jour1.add(i);
			comboBoxJour_1 = new JComboBox(jour1);
		}
		comboBoxJour_1.setForeground(new Color(128, 0, 0));
		comboBoxJour_1.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		comboBoxJour_1.setBounds(593, 202, 136, 27);
		contentPanel.add(comboBoxJour_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 248, 220));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(308, 430, 421, 62);
		contentPanel.add(scrollPane);
		
		txtSaisieCommentaire = new JTextField();
		txtSaisieCommentaire.setForeground(new Color(128, 0, 0));
		scrollPane.setViewportView(txtSaisieCommentaire);
		txtSaisieCommentaire.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieCommentaire.setColumns(10);
		
		JLabel lblNomEvent = new JLabel("Nom de l'événement");
		lblNomEvent.setForeground(new Color(128, 0, 0));
		lblNomEvent.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNomEvent.setBounds(114, 102, 180, 16);
		contentPanel.add(lblNomEvent);
		
		lblDateDebut = new JLabel("Date de début");
		lblDateDebut.setForeground(new Color(128, 0, 0));
		lblDateDebut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblDateDebut.setBounds(114, 156, 180, 16);
		contentPanel.add(lblDateDebut);
		
		lblDateFin = new JLabel("Date de fin");
		lblDateFin.setForeground(new Color(128, 0, 0));
		lblDateFin.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblDateFin.setBounds(114, 206, 89, 16);
		contentPanel.add(lblDateFin);
		
		lblPourcentage = new JLabel("Pourcentage appliquable");
		lblPourcentage.setForeground(new Color(128, 0, 0));
		lblPourcentage.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblPourcentage.setBounds(116, 260, 180, 16);
		contentPanel.add(lblPourcentage);
		
		lblCodePromo = new JLabel("Code promo affilié");
		lblCodePromo.setForeground(new Color(128, 0, 0));
		lblCodePromo.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblCodePromo.setBounds(116, 317, 180, 16);
		contentPanel.add(lblCodePromo);
		
		lblImage = new JLabel("Chemin d'accès de l'image");
		lblImage.setForeground(new Color(128, 0, 0));
		lblImage.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblImage.setBounds(116, 374, 211, 26);
		contentPanel.add(lblImage);
		
		lblCommentaire = new JLabel("Commentaire");
		lblCommentaire.setForeground(new Color(128, 0, 0));
		lblCommentaire.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblCommentaire.setBounds(116, 458, 180, 16);
		contentPanel.add(lblCommentaire);
		
		lblTitre = new JLabel("AJOUT NOUVEL EVENEMENT");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setForeground(new Color(255, 215, 0));
		lblTitre.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		lblTitre.setBounds(116, 26, 613, 35);
		contentPanel.add(lblTitre);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(660, 521, 69, 35);
			contentPanel.add(okButton);
			okButton.setForeground(new Color(128, 0, 0));
			okButton.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			okButton.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String nom = txtSaisieNom.getText();
					String debutEvent = comboBoxAnnee.getSelectedItem()+ "-"+ comboBoxMois.getSelectedItem() + "-" +comboBoxJour.getSelectedItem().toString();
					Date debut = Date.valueOf(debutEvent);
					int anneeDebut = (int) comboBoxAnnee.getSelectedItem();
					int moisDebut = (int) comboBoxMois.getSelectedItem();
					int jourDebut = (int) comboBoxJour.getSelectedItem();
					int anneeFin = (int) comboBoxAnnee_1.getSelectedItem();
					int moisFin = (int) comboBoxMois_1.getSelectedItem();
					int jourFin = (int) comboBoxJour_1.getSelectedItem();
					String finEvent = comboBoxAnnee_1.getSelectedItem()+ "-"+ comboBoxMois_1.getSelectedItem() + "-" +comboBoxJour_1.getSelectedItem().toString();
					Date fin = Date.valueOf(finEvent);
					String value = spinner.getValue().toString();
					
					Float pourcentage = Float.parseFloat(value);
					
					String codePromo = txtSaisieCodePromo.getText();
					String image = txtSaisieImage.getText();
					String comment = txtSaisieCommentaire.getText();
					try {
						if (!nom.isEmpty() && debut!=null && fin!=null && pourcentage!=null && !codePromo.isEmpty()) {
							if ((anneeFin>anneeDebut)) {
								evenementDAO.ajouterEvenement(nom, debut, fin, pourcentage, codePromo, image, comment);
								JOptionPane.showMessageDialog(null, "Nouvel événement ajouté avec succès", "Bravo !", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								
							}else if((anneeFin==anneeDebut)) {
								if ((moisFin>moisDebut)) {
									evenementDAO.ajouterEvenement(nom, debut, fin, pourcentage, codePromo, image, comment);
									JOptionPane.showMessageDialog(null, "Nouvel événement ajouté avec succès", "Bravo !", JOptionPane.INFORMATION_MESSAGE);
									dispose();
								} else if (moisFin==moisDebut) {
									if (jourFin>jourDebut) {
										evenementDAO.ajouterEvenement(nom, debut, fin, pourcentage, codePromo, image, comment);
										JOptionPane.showMessageDialog(null, "Nouvel événement ajouté avec succès", "Bravo !", JOptionPane.INFORMATION_MESSAGE);
										dispose();
									} else if (jourFin==jourDebut) {
										evenementDAO.ajouterEvenement(nom, debut, fin, pourcentage, codePromo, image, comment);
										JOptionPane.showMessageDialog(null, "Nouvel événement ajouté avec succès", "Bravo !", JOptionPane.INFORMATION_MESSAGE);
										dispose();
									} else {
										JOptionPane.showMessageDialog(null, "Veuillez saisir une date de fin ultérieure à la date de début");
									}
								} else {
									JOptionPane.showMessageDialog(null, "Veuillez saisir une date de fin ultérieure à la date de début");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Veuillez saisir une date de fin ultérieure à la date de début");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Veuillez saisir les champs obligatoires");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			okButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(761, 521, 69, 35);
			contentPanel.add(cancelButton);
			cancelButton.setForeground(new Color(128, 0, 0));
			cancelButton.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			cancelButton.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			cancelButton.setActionCommand("Cancel");
		}
		
		lblNewLabel = new JLabel("Année");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setBounds(308, 180, 136, 16);
		contentPanel.add(lblNewLabel);
		
		lblMois = new JLabel("Mois");
		lblMois.setHorizontalAlignment(SwingConstants.CENTER);
		lblMois.setForeground(new Color(255, 215, 0));
		lblMois.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblMois.setBounds(445, 179, 136, 16);
		contentPanel.add(lblMois);
		
		lblJour = new JLabel("Jour");
		lblJour.setHorizontalAlignment(SwingConstants.CENTER);
		lblJour.setForeground(new Color(255, 215, 0));
		lblJour.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblJour.setBounds(593, 179, 136, 16);
		contentPanel.add(lblJour);
		

		
		
	}
}
