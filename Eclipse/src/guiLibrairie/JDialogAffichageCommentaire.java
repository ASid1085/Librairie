package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoLibrairie.CommentaireDAO;
import entitiesLibrairie.Commentaire;

import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class JDialogAffichageCommentaire extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Commentaire comm;
	private Commentaire commentaire;
	private CommentaireDAO commentaireDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogAffichageCommentaire dialog = new JDialogAffichageCommentaire(comm);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogAffichageCommentaire(Commentaire commentaireAtraiter) {
		
		this.commentaire = commentaireAtraiter ;
		System.out.println(commentaire);
		
		/*try {
			commentaire = commentaireDAO.afficherUnCommentaire(commentaire);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		
		setBounds(100, 100, 850, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextArea textArea = new JTextArea();
			textArea.setBounds(92, 152, 319, 329);
			//contentPanel.add(textArea);
			textArea.setText(commentaire.getCommentaireTexte());
	
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(92, 152, 319, 329);
			contentPanel.add(scrollPane);
			scrollPane.setViewportView(textArea);
			
		}
		
		{
			JButton btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						commentaireDAO.modifierCommentaire(commentaire);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnModifier.setBounds(438, 152, 324, 29);
			contentPanel.add(btnModifier);
		}
		{
			JLabel lblTitreCom = new JLabel("");
			lblTitreCom.setForeground(Color.WHITE);
			lblTitreCom.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			lblTitreCom.setBounds(92, 110, 658, 16);
			lblTitreCom.setText("Commentaire numéro : " +commentaire.getCommentaireId() + " pour le livre " + commentaire.getLivreTitre());
			contentPanel.add(lblTitreCom);
		}
		{
			JLabel lblNewLabel = new JLabel("COMMENTAIRE");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
			lblNewLabel.setBounds(92, 42, 658, 35);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblDatePoste = new JLabel("New label");
			lblDatePoste.setForeground(Color.WHITE);
			lblDatePoste.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			lblDatePoste.setBounds(451, 228, 307, 16);
			contentPanel.add(lblDatePoste);
			lblDatePoste.setText("Commentaire posté le : "+commentaire.getCommentaireDate().toString());
		}
		{
			JLabel lblStatut = new JLabel("New label");
			lblStatut.setForeground(Color.WHITE);
			lblStatut.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			lblStatut.setBounds(451, 288, 307, 16);
			contentPanel.add(lblStatut);
			lblStatut.setText("Statut du commentaire : "+commentaire.getCommentaireStatut());
		}
		{
			JLabel lblClientLogin = new JLabel("New label");
			lblClientLogin.setForeground(Color.WHITE);
			lblClientLogin.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			lblClientLogin.setBounds(451, 349, 307, 16);
			contentPanel.add(lblClientLogin);
			lblClientLogin.setText("Commentaire posté par "+commentaire.getClientLogin());
		}
		{
			JLabel lblDateModération = new JLabel("New label");
			lblDateModération.setForeground(Color.WHITE);
			lblDateModération.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			lblDateModération.setBounds(451, 409, 307, 16);
			contentPanel.add(lblDateModération);
			lblDateModération.setText("Date de modération : " +commentaire.getDateModeration());
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
