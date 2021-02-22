package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoLibrairie.CommentaireDAO;
import entitiesLibrairie.Commentaire;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class JDialogCommentaireAffichage extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Commentaire comm;
	private Commentaire commentaire;
	private CommentaireDAO commentaireDAO = new CommentaireDAO();
	private String id;
	private String titre ;
	private String login ;
	private String texte ;
	private String note ;
	private String statut ;
	private Date dateCom ;
	private Date dateMode ;
	private String commentaireTexte =""; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogCommentaireAffichage dialog = new JDialogCommentaireAffichage( null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogCommentaireAffichage(Commentaire commentaireAtraiter) {
		
		//this.commentaire = commentaireAtraiter ;

		id = commentaireAtraiter.getCommentaireId();
		titre = commentaireAtraiter.getLivreTitre();
		login = commentaireAtraiter.getClientLogin();
		texte = commentaireAtraiter.getCommentaireTexte();
		note = commentaireAtraiter.getCommentaireNote();
		statut = commentaireAtraiter.getCommentaireStatut();
		dateCom = commentaireAtraiter.getCommentaireDate();
		dateMode = commentaireAtraiter.getDateModeration();
		
		
		
		setBounds(100, 100, 850, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 248, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(128, 0, 0));
		textArea.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		textArea.setBounds(92, 152, 319, 329);
		//contentPanel.add(textArea);
		textArea.setText(commentaireAtraiter.getCommentaireTexte());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 152, 319, 329);
		contentPanel.add(scrollPane);
		scrollPane.setViewportView(textArea);
		


		JLabel lblTitreCom = new JLabel("");
		lblTitreCom.setForeground(new Color(128, 0, 0));
		lblTitreCom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblTitreCom.setBounds(92, 110, 658, 16);
		lblTitreCom.setText("Commentaire numéro : " +commentaireAtraiter.getCommentaireId() + " pour le livre " +commentaireAtraiter.getLivreTitre());
		contentPanel.add(lblTitreCom);
	
		JLabel lblNewLabel = new JLabel("COMMENTAIRE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		lblNewLabel.setBounds(92, 42, 658, 35);
		contentPanel.add(lblNewLabel);
	
		JLabel lblDatePoste = new JLabel("New label");
		lblDatePoste.setForeground(new Color(128, 0, 0));
		lblDatePoste.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblDatePoste.setBounds(451, 228, 307, 16);
		contentPanel.add(lblDatePoste);
		lblDatePoste.setText("Commentaire posté le : "+commentaireAtraiter.getCommentaireDate().toString());
	
		JLabel lblStatut = new JLabel("New label");
		lblStatut.setForeground(new Color(128, 0, 0));
		lblStatut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblStatut.setBounds(451, 288, 307, 16);
		contentPanel.add(lblStatut);
		lblStatut.setText("Statut du commentaire : "+commentaireAtraiter.getCommentaireStatut());

		JLabel lblClientLogin = new JLabel("New label");
		lblClientLogin.setForeground(new Color(128, 0, 0));
		lblClientLogin.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblClientLogin.setBounds(451, 349, 307, 16);
		contentPanel.add(lblClientLogin);
		lblClientLogin.setText("Commentaire posté par " +commentaireAtraiter.getClientLogin());
	
		JLabel lblDateModération = new JLabel("New label");
		lblDateModération.setForeground(new Color(128, 0, 0));
		lblDateModération.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblDateModération.setBounds(451, 409, 307, 16);
		contentPanel.add(lblDateModération);
		lblDateModération.setText("Date de modération : " +commentaireAtraiter.getDateModeration());
	
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setForeground(new Color(128, 0, 0));
		okButton.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		okButton.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		okButton.setBounds(687, 498, 75, 29);
		contentPanel.add(okButton);
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
		
		
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setForeground(new Color(128, 0, 0));
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commentaireTexte = textArea.getText(); 
				try {
					commentaire = commentaireDAO.afficherUnCommentaire(commentaireAtraiter, titre);
					commentaireDAO.modifierCommentaire(commentaire, id, commentaireTexte);
					JOptionPane.showMessageDialog(contentPanel, "Commentaire modéré avec succès", "Modération", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setBounds(438, 152, 324, 29);
		contentPanel.add(btnModifier);
		
	}
}
