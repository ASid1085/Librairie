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
	private JDialogAffichageCommentaire jdAffichage;

	
	private static void makeFrameFullSize(JFrame aFrame) {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    aFrame.setSize(screenSize.width, screenSize.height);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameCommentaire frame = new JFrameCommentaire();
					makeFrameFullSize(frame);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 850);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
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
		nomColonnes.add("Commande");
		nomColonnes.add("ID");
		nomColonnes.add("Date modération");
		
		
		Vector <String> nomColonnes2 = new Vector();
		nomColonnes2.add("Commentaire ID");
		nomColonnes2.add("Commentaire Date");
		nomColonnes2.add("Commentaire Note");
		nomColonnes2.add("Commentaire Statut");
		nomColonnes2.add("Commentaire Texte");
		
		try {
			vectorCommentaire = commentaireDAO.afficherCommentaire();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DefaultTableModel model = new DefaultTableModel(vectorCommentaire, nomColonnes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 170, 1162, 527);
		contentPane.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
//JLABEL ///////////////////////////////////////////////////////////////////////////////////		
		JLabel lblTitre = new JLabel("COMMENTAIRES");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
		lblTitre.setBounds(123, 19, 1162, 36);
		contentPane.add(lblTitre);
		
		
//TEXT FIELDS ///////////////////////////////////////////////////////////////////////////////////
		txtSaisieLivre = new JTextField();
		txtSaisieLivre.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		txtSaisieLivre.setBounds(123, 120, 249, 26);
		contentPane.add(txtSaisieLivre);
		txtSaisieLivre.setColumns(10);
		
		txtSaisieClient = new JTextField();
		txtSaisieClient.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		txtSaisieClient.setColumns(10);
		txtSaisieClient.setBounds(551, 120, 249, 26);
		contentPane.add(txtSaisieClient);
		
		txtSaisieStatut = new JTextField();
		txtSaisieStatut.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		txtSaisieStatut.setColumns(10);
		txtSaisieStatut.setBounds(975, 120, 202, 26);
		contentPane.add(txtSaisieStatut);
		
		
		
		
//BOUTON RECHERCHER PAT LIVRE ///////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercheLivre = new JButton("Rechercher");
		btnRechercheLivre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titreLivre = txtSaisieLivre.getText();
				try {
					commentaire = commentaireDAO.rechercherCommentaireparLivre(titreLivre);
					System.out.println(commentaire);
					jdAffichage = new JDialogAffichageCommentaire(commentaire);
					jdAffichage.setVisible(true);
					//DefaultTableModel newModel = new DefaultTableModel(vectorCommentaires, nomColonnes2);
					//table.setModel(newModel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de commentaire correspondant à " +titreLivre, "Oops !", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRechercheLivre.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnRechercheLivre.setBounds(373, 120, 117, 29);
		contentPane.add(btnRechercheLivre);
		
		
		
//BOUTON RECHERCHER PAR CLIENT ///////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercheClient = new JButton("Rechercher");
		btnRechercheClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clientNom = txtSaisieClient.getText();
				try {
					commentaire = commentaireDAO.rechercherCommentaireparClient(clientNom);
					System.out.println(commentaire);
					jdAffichage = new JDialogAffichageCommentaire(commentaire);
					jdAffichage.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de commentaire correspondant au client " + clientNom, "Oops !", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRechercheClient.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnRechercheClient.setBounds(797, 120, 117, 29);
		contentPane.add(btnRechercheClient);
		
		
//BOUTON RECHERCHER PAR STATUT ///////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercherStatut = new JButton("Rechercher");
		btnRechercherStatut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String statut = txtSaisieStatut.getText();
				System.out.println(statut);
				
				try {
					commentaire = commentaireDAO.rechercherCommentaireparStatut(statut);
					System.out.println(commentaire);
					jdAffichage = new JDialogAffichageCommentaire(commentaire);
					jdAffichage.setVisible(true);
					
					//DefaultTableModel newModel = new DefaultTableModel(vectorCommentaires, nomColonnes2);
					//table.setModel(newModel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Nous ne trouvons pas de commentaire avec le statut " + statut, "Oops !", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnRechercherStatut.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnRechercherStatut.setBounds(1178, 120, 117, 29);
		contentPane.add(btnRechercherStatut);
		
		
		
		
//BOUTON SUPPRIMER ///////////////////////////////////////////////////////////////////////////////////
		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = (String) table.getValueAt(row, 0);
				try {
					commentaireDAO.supprimerCommentaire(id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnSupprimer.setBounds(269, 724, 117, 29);
		contentPane.add(btnSupprimer);
		
		
		
//BOUTON MODIFIER ///////////////////////////////////////////////////////////////////////////////////
		btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String commentaireId = (String) table.getValueAt(row, 0); 
				String clientLogin = (String) table.getValueAt(row, 1); 
				String livreTitre = (String) table.getValueAt(row, 2); 
				String commentaireTexte = (String) table.getValueAt(row, 3); 
				String commentaireNote = (String) table.getValueAt(row, 4); 
				String commentaireStatut = (String) table.getValueAt(row, 5); 
				Date commentaireDate = (Date) table.getValueAt(row, 6); 
				String commandeNum = (String) table.getValueAt(row, 7); 
				String employeId = (String) table.getValueAt(row, 8); 
				Date dateModeration = (Date) table.getValueAt(row, 9); 
				table.setValueAt(commentaireId, row, 0);
				table.setValueAt(clientLogin, row, 1);
				table.setValueAt(livreTitre, row, 2);
				table.setValueAt(commentaireTexte, row, 3);
				table.setValueAt(commentaireNote, row, 4);
				table.setValueAt(commentaireStatut, row, 5);
				table.setValueAt(commentaireDate, row, 6);
				table.setValueAt(commandeNum, row, 7);
				table.setValueAt(employeId, row, 8);
				table.setValueAt(dateModeration, row, 9);
				commentaire = new Commentaire(commentaireId, clientLogin, livreTitre, commentaireTexte, commentaireNote, commentaireStatut, commentaireDate, commandeNum, employeId, dateModeration);
				try {
					commentaireDAO.modifierCommentaire(commentaire);
					JOptionPane.showMessageDialog(null, commentaire.toString(), "Commentaire modifié : ", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			
			}
		});
		btnModifier.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnModifier.setBounds(123, 724, 117, 29);
		contentPane.add(btnModifier);
		
		
		
//BOUTON QUITTER ///////////////////////////////////////////////////////////////////////////////////
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnQuitter.setBounds(1168, 725, 117, 29);
		contentPane.add(btnQuitter);
		
		lblNewLabel = new JLabel("Recherche par livre :");
		lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(123, 92, 367, 16);
		contentPane.add(lblNewLabel);
		
		lblRechercheParClient = new JLabel("Recherche par client :");
		lblRechercheParClient.setForeground(Color.WHITE);
		lblRechercheParClient.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblRechercheParClient.setBounds(551, 92, 367, 16);
		contentPane.add(lblRechercheParClient);
		
		lblRechercheParStatut = new JLabel("Recherche par statut :");
		lblRechercheParStatut.setForeground(Color.WHITE);
		lblRechercheParStatut.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblRechercheParStatut.setBounds(975, 93, 367, 16);
		contentPane.add(lblRechercheParStatut);
		

	}
}
