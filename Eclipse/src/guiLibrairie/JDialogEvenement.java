package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoLibrairie.EvenementDAO;
import entitiesLibrairie.Evenement;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class JDialogEvenement extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSaisieNom;
	private JTextField txtSaisieDateFin;
	private JTextField txtSaisiePourcentage;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogEvenement dialog = new JDialogEvenement();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogEvenement() {
		setBounds(100, 100, 850, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtSaisieNom = new JTextField();
			txtSaisieNom.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			txtSaisieNom.setBounds(308, 157, 421, 26);
			contentPanel.add(txtSaisieNom);
			txtSaisieNom.setColumns(10);
		}
		
		txtSaisieDateFin = new JTextField();
		txtSaisieDateFin.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		txtSaisieDateFin.setColumns(10);
		txtSaisieDateFin.setBounds(569, 211, 160, 26);
		contentPanel.add(txtSaisieDateFin);
		
		txtSaisiePourcentage = new JTextField();
		txtSaisiePourcentage.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		txtSaisiePourcentage.setColumns(10);
		txtSaisiePourcentage.setBounds(308, 269, 421, 26);
		contentPanel.add(txtSaisiePourcentage);
		
		txtSaisieCodePromo = new JTextField();
		txtSaisieCodePromo.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		txtSaisieCodePromo.setColumns(10);
		txtSaisieCodePromo.setBounds(308, 330, 421, 26);
		contentPanel.add(txtSaisieCodePromo);
		
		txtSaisieImage = new JTextField();
		txtSaisieImage.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		txtSaisieImage.setColumns(10);
		txtSaisieImage.setBounds(308, 388, 421, 26);
		contentPanel.add(txtSaisieImage);
		
		txtSaisieDateDebut = new JTextField();
		txtSaisieDateDebut.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		txtSaisieDateDebut.setColumns(10);
		txtSaisieDateDebut.setBounds(308, 211, 160, 26);
		contentPanel.add(txtSaisieDateDebut);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(308, 446, 421, 62);
		contentPanel.add(scrollPane);
		
		txtSaisieCommentaire = new JTextField();
		scrollPane.setViewportView(txtSaisieCommentaire);
		txtSaisieCommentaire.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		txtSaisieCommentaire.setColumns(10);
		
		JLabel lblNomEvent = new JLabel("Nom de l'événement");
		lblNomEvent.setForeground(Color.WHITE);
		lblNomEvent.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblNomEvent.setBounds(116, 162, 180, 16);
		contentPanel.add(lblNomEvent);
		
		lblDateDebut = new JLabel("Date de début");
		lblDateDebut.setForeground(Color.WHITE);
		lblDateDebut.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblDateDebut.setBounds(116, 217, 180, 16);
		contentPanel.add(lblDateDebut);
		
		lblDateFin = new JLabel("Date de fin");
		lblDateFin.setForeground(Color.WHITE);
		lblDateFin.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblDateFin.setBounds(491, 216, 180, 16);
		contentPanel.add(lblDateFin);
		
		lblPourcentage = new JLabel("Pourcentage appliquable");
		lblPourcentage.setForeground(Color.WHITE);
		lblPourcentage.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblPourcentage.setBounds(116, 275, 180, 16);
		contentPanel.add(lblPourcentage);
		
		lblCodePromo = new JLabel("Code promo affilié");
		lblCodePromo.setForeground(Color.WHITE);
		lblCodePromo.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblCodePromo.setBounds(116, 336, 180, 16);
		contentPanel.add(lblCodePromo);
		
		lblImage = new JLabel("Chemin d'accès de l'image");
		lblImage.setForeground(Color.WHITE);
		lblImage.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblImage.setBounds(116, 388, 211, 26);
		contentPanel.add(lblImage);
		
		lblCommentaire = new JLabel("Commentaire");
		lblCommentaire.setForeground(Color.WHITE);
		lblCommentaire.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblCommentaire.setBounds(116, 468, 180, 16);
		contentPanel.add(lblCommentaire);
		
		lblTitre = new JLabel("AJOUT NOUVEL EVENEMENT");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
		lblTitre.setBounds(116, 48, 613, 35);
		contentPanel.add(lblTitre);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String nom = txtSaisieNom.getText();
						Date debut = Date.valueOf(txtSaisieDateDebut.getText());
						System.out.println(debut);
						Date fin = Date.valueOf(txtSaisieDateFin.getText());
						Float pourcentage = Float.parseFloat(txtSaisiePourcentage.getText());
						String codePromo = txtSaisieCodePromo.getText();
						String image = txtSaisieImage.getText();
						String comment = txtSaisieCommentaire.getText();
						try {
							evenementDAO.ajouterEvenement(nom, debut, fin, pourcentage, codePromo, image, comment);
							JOptionPane.showMessageDialog(null, "Nouvel événement ajouté avec succès", "Bravo !", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				okButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
