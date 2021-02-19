package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoLibrairie.daoClient;
import daoLibrairie.daoEditeur;
import entitiesLibrairie.Client;
import entitiesLibrairie.Editeur;
import entitiesLibrairie.Genre;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;

public class JFrameEditeur extends JFrame {

	private daoEditeur daoEd = new daoEditeur();
	private JPanel contentPane;
	private JTextField txtEditeurNom;
	private JTextField txtEditeurAdr;
	private JTextField txtTelephone;
	private JTextField txtMail;
	private static JDialogCommentaireEditeur JDed;
	private JTextField txtPersContact;
	private JTextField txtEditeurId;
		
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEditeur frame = new JFrameEditeur( "");
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
	public JFrameEditeur(String editeurNom) {
		
		if (!editeurNom.equals( "")) { 
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					try {
						Editeur modifEditeur = daoEd.findEditeurByNom( editeurNom);
						txtEditeurId.setText( modifEditeur.getEditeurId());
						txtEditeurNom.setText( modifEditeur.getEditeurNom());
						txtEditeurAdr.setText( modifEditeur.getEditeurAdresse());
						txtTelephone.setText( modifEditeur.getEditeurTel());
						txtMail.setText( modifEditeur.getEditeurMail());
						txtPersContact.setText( modifEditeur.getEditeurContact());
					} catch (SQLException ex) {
						ex.printStackTrace();
					}			
				}
			});
		}
		setTitle("Éditeur");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 494, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditeurId = new JLabel("Éditeur id :");
		lblEditeurId.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditeurId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblEditeurId.setBounds(17, 22, 88, 16);
		contentPane.add(lblEditeurId);
		
		JLabel lblEditeurNom = new JLabel("Nom :");
		lblEditeurNom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblEditeurNom.setBounds(17, 50, 86, 16);
		contentPane.add(lblEditeurNom);
		
		txtEditeurNom = new JTextField();
		txtEditeurNom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtEditeurNom.setColumns(10);
		txtEditeurNom.setBounds(17, 67, 465, 26);
		contentPane.add(txtEditeurNom);
		
		JLabel lblEditeurAdr = new JLabel("Adresse :");
		lblEditeurAdr.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditeurAdr.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblEditeurAdr.setBounds(17, 120, 101, 16);
		contentPane.add(lblEditeurAdr);
		
		txtEditeurAdr = new JTextField();
		txtEditeurAdr.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtEditeurAdr.setColumns(10);
		txtEditeurAdr.setBounds(17, 138, 465, 26);
		contentPane.add(txtEditeurAdr);
		
		JLabel lblStatut = new JLabel("Personne à contacter :");
		lblStatut.setHorizontalAlignment(SwingConstants.LEFT);
		lblStatut.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblStatut.setBounds(17, 197, 139, 16);
		contentPane.add(lblStatut);
		
		JLabel lblTelephone = new JLabel("Téléphone :");
		lblTelephone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelephone.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelephone.setBounds(17, 281, 70, 16);
		contentPane.add(lblTelephone);
		
		txtTelephone = new JTextField();
		txtTelephone.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(17, 298, 130, 26);
		contentPane.add(txtTelephone);
		
		JLabel lblEditeurMail = new JLabel("Mail :");
		lblEditeurMail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditeurMail.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblEditeurMail.setBounds(170, 281, 61, 16);
		contentPane.add(lblEditeurMail);
		
		txtMail = new JTextField();
		txtMail.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtMail.setColumns(10);
		txtMail.setBounds(166, 298, 316, 26);
		contentPane.add(txtMail);
		
		JButton btnCommentaireEditeur = new JButton("");
		btnCommentaireEditeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDed = new JDialogCommentaireEditeur( editeurNom);
				JDed.setLocationRelativeTo( JDed.getParent());
				JDed.setVisible( true);
			}
		});
		btnCommentaireEditeur.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnPost.png"));
		btnCommentaireEditeur.setBounds(381, 344, 101, 76);
		contentPane.add(btnCommentaireEditeur);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Editeur ed = new Editeur( txtEditeurId.getText(), txtEditeurNom.getText(), txtEditeurAdr.getText(), txtTelephone.getText(), txtMail.getText(), txtPersContact.getText());
				if (editeurNom.equals( "")) {
					try {
						daoEd.ajouterEditeur( ed);
						setVisible( false);
						dispose();
					} catch (SQLException e1) {
						System.err.println( "Oops : erreur avec la validation d'un nouvel éditeur - Voir JFrameEditeur & daoEditeur");
						e1.printStackTrace();
					}
				} 
				try {
					daoEd.modifierEditeur( ed, editeurNom);
					setVisible( false);
					dispose();
					//JFrameListeCategorie lc = new JFrameListeCategorie();
					//lc.setLocationRelativeTo( null);
					//lc.setVisible( true);

				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la modification d'un nouvel éditeur - Voir JFrameEditeur & daoEditeur");
					e1.printStackTrace();
				}
			}
		});
		btnValider.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnValider.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnValider.setBounds(59, 358, 97, 41);
		contentPane.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEditeurNom.setText( "");
				txtEditeurAdr.setText( "");
				txtTelephone.setText( "");
				txtMail.setText( "");
				txtPersContact.setText( "");
			}
		});
		btnAnnuler.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAnnuler.setBounds(226, 358, 97, 41);
		contentPane.add(btnAnnuler);
		
		txtPersContact = new JTextField();
		txtPersContact.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtPersContact.setColumns(10);
		txtPersContact.setBounds(17, 215, 465, 26);
		contentPane.add(txtPersContact);
		
		txtEditeurId = new JTextField();
		txtEditeurId.setText( daoEd.ajoutIdEditeur());
		txtEditeurId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtEditeurId.setEnabled(false);
		txtEditeurId.setColumns(10);
		txtEditeurId.setBounds(85, 17, 139, 26);
		contentPane.add(txtEditeurId);
		
	}
}
