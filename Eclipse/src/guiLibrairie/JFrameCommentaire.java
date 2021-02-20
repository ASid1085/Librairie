package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daoLibrairie.CommentaireDAO;
import entitiesLibrairie.Commentaire;
import entitiesLibrairie.Evenement;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class JFrameCommentaire extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtSaisieLivre;
	private JTextField txtSaisieClient;
	private JTextField txtSaisieStatut;
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnQuitter;
	private CommentaireDAO commentaireDAO = new CommentaireDAO();
	private Commentaire commentaire = new Commentaire();
	private Vector<Vector> vectorCommentaire = new Vector();
	private Vector<Vector> vectorCommentaires = new Vector();
	private Vector nomColonnes = new Vector();
	private JLabel lblNewLabel;
	private JLabel lblRechercheParClient;
	private JLabel lblRechercheParStatut;
	private JDialogCommentaireAffichage jdAffichage;
	private JButton btnRafraichir;
	private JButton btnRafraichir_1;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameCommentaire frame = new JFrameCommentaire();
					//makeFrameFullSize(frame);
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
	public JFrameCommentaire() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		
		
//JTABLE ///////////////////////////////////////////////////////////////////////////////////
		
		
		nomColonnes.add("ID");
		nomColonnes.add("Client");
		nomColonnes.add("Livre");
		nomColonnes.add("Texte");
		nomColonnes.add("Note");
		nomColonnes.add("Statut");
		nomColonnes.add("Publication");
		nomColonnes.add("Date modération");
		
		
		try {
		vectorCommentaire = commentaireDAO.afficherCommentaire();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		DefaultTableModel model = new DefaultTableModel( vectorCommentaire, nomColonnes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 160, 832, 330);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
//JLABEL ///////////////////////////////////////////////////////////////////////////////////		
		JLabel lblTitre = new JLabel("COMMENTAIRES");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setForeground(new Color(255, 215, 0));
		lblTitre.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		lblTitre.setBounds(82, 19, 832, 36);
		contentPane.add(lblTitre);
		
		
//TEXT FIELDS ///////////////////////////////////////////////////////////////////////////////////
		txtSaisieLivre = new JTextField();
		txtSaisieLivre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieLivre.setBounds(82, 120, 172, 26);
		contentPane.add(txtSaisieLivre);
		txtSaisieLivre.setColumns(10);
		
		txtSaisieClient = new JTextField();
		txtSaisieClient.setForeground(new Color(128, 0, 0));
		txtSaisieClient.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieClient.setColumns(10);
		txtSaisieClient.setBounds(374, 120, 172, 26);
		contentPane.add(txtSaisieClient);
		
		txtSaisieStatut = new JTextField();
		txtSaisieStatut.setForeground(new Color(128, 0, 0));
		txtSaisieStatut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieStatut.setColumns(10);
		txtSaisieStatut.setBounds(665, 120, 181, 26);
		contentPane.add(txtSaisieStatut);
		
		
		
		
//BOUTON RECHERCHER PAT LIVRE ///////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercheLivre = new JButton("");
		btnRechercheLivre.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		//btnRechercheLivre.setForeground(new Color(128, 0, 0));
		//btnRechercheLivre.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		//btnRechercheLivre.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnRechercheLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titreLivre = txtSaisieLivre.getText();
				try {
					vectorCommentaires = commentaireDAO.rechercherCommentaireparLivre(titreLivre);
					txtSaisieLivre.setText("");
					DefaultTableModel newModel = new DefaultTableModel(vectorCommentaires, nomColonnes);
					table.setModel(newModel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de commentaire correspondant à " +titreLivre, "Oops !", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRechercheLivre.setBounds(266, 103, 56, 45);
		contentPane.add(btnRechercheLivre);
		
		
		
//BOUTON RECHERCHER PAR CLIENT ///////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercheClient = new JButton("");
		btnRechercheClient.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		//btnRechercheClient.setForeground(new Color(128, 0, 0));
		//btnRechercheClient.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		//btnRechercheClient.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnRechercheClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clientNom = txtSaisieClient.getText();
				try {
					vectorCommentaires = commentaireDAO.rechercherCommentaireparClient(clientNom);
					txtSaisieClient.setText("");
					DefaultTableModel newModel = new DefaultTableModel(vectorCommentaires, nomColonnes);
					table.setModel(newModel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de commentaire correspondant au client " + clientNom, "Oops !", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRechercheClient.setBounds(558, 103, 56, 45);
		contentPane.add(btnRechercheClient);
		
		
//BOUTON RECHERCHER PAR STATUT ///////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercherStatut = new JButton("");
		btnRechercherStatut.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		//btnRechercherStatut.setForeground(new Color(128, 0, 0));
		//btnRechercherStatut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		//btnRechercherStatut.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnRechercherStatut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String statut = txtSaisieStatut.getText();
				System.out.println(statut);
				
				try {
					vectorCommentaires = commentaireDAO.rechercherCommentaireparStatut(statut);
					txtSaisieStatut.setText("");
					DefaultTableModel newModel = new DefaultTableModel(vectorCommentaires, nomColonnes);
					table.setModel(newModel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de commentaire avec le statut " + statut, "Oops !", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnRechercherStatut.setBounds(858, 103, 56, 45);
		contentPane.add(btnRechercherStatut);
		
		
		
		
//BOUTON SUPPRIMER ///////////////////////////////////////////////////////////////////////////////////
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(new Color(128, 0, 0));
		btnSupprimer.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimer.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = (String) table.getValueAt(row, 0);
				try {
					commentaireDAO.supprimerCommentaire(id);
					JOptionPane.showMessageDialog(null, "Commentaire supprimé avec succès", "Suppression de commentaire", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnSupprimer.setBounds(218, 517, 117, 29);
		contentPane.add(btnSupprimer);
		
		
		
//BOUTON MODIFIER ///////////////////////////////////////////////////////////////////////////////////
		btnModifier = new JButton("Consulter");
		btnModifier.setForeground(new Color(128, 0, 0));
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				String titre = (String) table.getValueAt(row, 2); 
				try {
					commentaire = commentaireDAO.afficherUnCommentaire(commentaire, titre);
					jdAffichage = new JDialogCommentaireAffichage(commentaire);
					jdAffichage.setVisible(true);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
					
			
			}
		});
		btnModifier.setBounds(82, 517, 117, 29);
		contentPane.add(btnModifier);
		
		
		
//BOUTON QUITTER ///////////////////////////////////////////////////////////////////////////////////
		btnQuitter = new JButton("Quitter");
		btnQuitter.setForeground(new Color(128, 0, 0));
		btnQuitter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnQuitter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnQuitter.setBounds(797, 517, 117, 29);
		contentPane.add(btnQuitter);
		
		lblNewLabel = new JLabel("Recherche par livre :");
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setBounds(82, 92, 367, 16);
		contentPane.add(lblNewLabel);
		
		lblRechercheParClient = new JLabel("Recherche par client :");
		lblRechercheParClient.setForeground(new Color(128, 0, 0));
		lblRechercheParClient.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercheParClient.setBounds(378, 92, 367, 16);
		contentPane.add(lblRechercheParClient);
		
		lblRechercheParStatut = new JLabel("Recherche par statut :");
		lblRechercheParStatut.setForeground(new Color(128, 0, 0));
		lblRechercheParStatut.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercheParStatut.setBounds(672, 92, 219, 16);
		contentPane.add(lblRechercheParStatut);
		
		
		
		
//BOUTON RETOUR///////////////////////////////////////////////////////////////////////////////////
		btnRafraichir = new JButton("Rafraichir");
		btnRafraichir.setForeground(new Color(128, 0, 0));
		btnRafraichir.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnRafraichir.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnRafraichir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					try {
						vectorCommentaire = commentaireDAO.afficherCommentaire();
						DefaultTableModel modell = new DefaultTableModel(vectorCommentaire, nomColonnes);
						table = new JTable(modell);
						scrollPane.setViewportView(table);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}



			}
		});
		btnRafraichir.setBounds(508, 518, 117, 29);
		contentPane.add(btnRafraichir);
		

		

	}
}
