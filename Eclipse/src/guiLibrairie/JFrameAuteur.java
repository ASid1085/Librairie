package guiLibrairie;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import daoLibrairie.*;
import entitiesLibrairie.*;

public class JFrameAuteur extends JFrame {

	private daoAuteur daoAu = new daoAuteur();
	private JPanel contentPane;
	private JTextField txtAuteurId;
	private JTextField txtAuteurNom;
	private JTextField txtAuteurPrenom;
	private JTextField txtAuteurPseudo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameAuteur frame = new JFrameAuteur( "");
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
	public JFrameAuteur( String auteurNom) {
		
		if (!auteurNom.equals( "")) { 
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					try {
						Auteur modifAuteur = daoAu.findAuteurByNom( auteurNom);
						txtAuteurId.setText( modifAuteur.getAuteurId());
						txtAuteurNom.setText( modifAuteur.getAuteurNom());
						txtAuteurPrenom.setText( modifAuteur.getAuteurPrenom());
						txtAuteurPseudo.setText( modifAuteur.getAuteurPseudo());
					} catch (SQLException ex) {
						ex.printStackTrace();
					}			
				}
			});
		}
		setTitle("Auteur");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 439, 369);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAuteurId = new JLabel("Auteur Id : ");
		lblAuteurId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuteurId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAuteurId.setBounds(33, 30, 83, 16);
		contentPane.add(lblAuteurId);
		
		txtAuteurId = new JTextField();
		txtAuteurId.setText( daoAu.ajoutIdAuteur());
		txtAuteurId.setBounds(137, 25, 189, 26);
		txtAuteurId.setEnabled( false);
		txtAuteurId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		contentPane.add(txtAuteurId);
		txtAuteurId.setColumns(10);
		
		JLabel lblAuteurNom = new JLabel("Auteur nom :");
		lblAuteurNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuteurNom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAuteurNom.setBounds(19, 83, 97, 16);
		contentPane.add(lblAuteurNom);
		
		txtAuteurNom = new JTextField();
		txtAuteurNom.setBounds(137, 78, 189, 26);
		txtAuteurNom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		contentPane.add(txtAuteurNom);
		txtAuteurNom.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Auteur au = new Auteur( txtAuteurId.getText(), txtAuteurNom.getText(), txtAuteurPrenom.getText(), txtAuteurPseudo.getText());
				if ( auteurNom.equals( "")) {
					try {
						daoAu.ajouterAuteur( au);
						dispose();
					} catch (SQLException e1) {
						System.err.println( "Oops : erreur avec la validation d'un nouvel auteur - Voir JFrameAuteur & daoAuteur");
						e1.printStackTrace();
					}
				} 
				if ( !auteurNom.equals( "")) {
					try {
						daoAu.modifierAuteur( au, auteurNom);
						dispose();
					} catch (SQLException e1) {
						System.err.println( "Oops : erreur avec la modification d'un nouveau genre - Voir JFrameGenre & daoGenre");
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnValider.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnValider.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnValider.setBounds(69, 256, 97, 41);
		contentPane.add(btnValider);
		
		/*btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//txtAuteurId.setText( "");
				txtAuteurNom.setText( "");
				txtAuteurPrenom.setText( "");
				txtAuteurPseudo.setText( "");
			}
		});
		btnAnnuler.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAnnuler.setBounds(69, 256, 97, 41);
		btnAnnuler.setBounds(258, 257, 97, 41);
		contentPane.add(btnAnnuler);*/
		
		JLabel lblAuteurPrenom = new JLabel("Auteur prénom :");
		lblAuteurPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuteurPrenom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAuteurPrenom.setBounds(6, 143, 110, 16);
		contentPane.add(lblAuteurPrenom);
		
		txtAuteurPrenom = new JTextField();
		txtAuteurPrenom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtAuteurPrenom.setColumns(10);
		txtAuteurPrenom.setBounds(137, 137, 189, 26);
		contentPane.add(txtAuteurPrenom);
		
		JLabel lblAuteurPseudo = new JLabel("Auteur pseudo :");
		lblAuteurPseudo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuteurPseudo.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAuteurPseudo.setBounds(19, 196, 97, 16);
		contentPane.add(lblAuteurPseudo);
		
		txtAuteurPseudo = new JTextField();
		txtAuteurPseudo.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtAuteurPseudo.setColumns(10);
		txtAuteurPseudo.setBounds(137, 190, 189, 26);
		contentPane.add(txtAuteurPseudo);
	}
}
