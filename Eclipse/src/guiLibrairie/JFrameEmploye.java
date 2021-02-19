package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daoLibrairie.EmployeDAO;
import entitiesLibrairie.Employe;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameEmploye extends JFrame {

	private JPanel contentPane;
	private JTable tableEmployes;
	private JFrame frame;
	private JTextField txtSaisieNom;
	private Vector <Vector> vectorEmploye = new Vector();
	private EmployeDAO employeDAO = new EmployeDAO();
	private Employe employe = new Employe();
	private static Employe employeDroits ;
	
	/*private static void makeFrameFullSize(JFrame aFrame) {
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    aFrame.setSize(screenSize.width, screenSize.height);
	}*/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEmploye frame = new JFrameEmploye(/*employeDroits*/);
				//	makeFrameFullSize(frame);
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
	public JFrameEmploye(/*Employe employeATraiter*/) {


		//this.employe = employeATraiter ;
		
		

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100,1000, 600);
		setPreferredSize(new Dimension(1000, 600));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		

		
//JTABLE///////////////////////////////////////////////////////////////////////////////////////
		
		Vector <String> nomColonnes = new Vector();
		nomColonnes.add("ACCES");
		nomColonnes.add("NOM");
		nomColonnes.add("PRENOM");
		nomColonnes.add("POSTE");
		//nomColonnes.add("LOGIN");
		//nomColonnes.add("MOT DE PASSE");

		try {
			vectorEmploye = employeDAO.afficherEmployes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, petit problème technique", "Nous sommes désolés", JOptionPane.INFORMATION_MESSAGE);
		}

		
		DefaultTableModel modelEmploye = new DefaultTableModel(vectorEmploye, nomColonnes);
		tableEmployes = new JTable();
		tableEmployes.setModel(modelEmploye);
		tableEmployes.setBounds(81, 97, 1200, 650);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 163, 684, 333);
		scrollPane.setViewportView(tableEmployes);
		contentPane.add(scrollPane);

	
		
//TEXTFIELDS///////////////////////////////////////////////////////////////////////////////////////
		txtSaisieNom = new JTextField();
		txtSaisieNom.setColumns(10);
		txtSaisieNom.setBounds(401, 94, 365, 26);
		contentPane.add(txtSaisieNom);
		

//JLABELS///////////////////////////////////////////////////////////////////////////////////////		
		JLabel lblSaisieNom = new JLabel("Saisir un nom");
		lblSaisieNom.setForeground(new Color(128, 0, 0));
		lblSaisieNom.setFont(new Font("Avenir Next", Font.PLAIN, 16));
		lblSaisieNom.setBounds(286, 99, 218, 16);
		contentPane.add(lblSaisieNom);
		
		JLabel lblSaisirUnDroit = new JLabel("Saisir un droit d'accès");
		lblSaisirUnDroit.setForeground(new Color(128, 0, 0));
		lblSaisirUnDroit.setFont(new Font("Avenir Next", Font.PLAIN, 16));
		lblSaisirUnDroit.setBounds(286, 132, 218, 16);
		contentPane.add(lblSaisirUnDroit);

		
		JLabel lblTitre = new JLabel("GESTION DES EMPLOYES");
		lblTitre.setForeground(new Color(255, 215, 0));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		lblTitre.setBounds(81, 38, 837, 30);
		contentPane.add(lblTitre);
		
//COMBOBOX///////////////////////////////////////////////////////////////////////////////////////
		Vector <String> vecteurCmbBxAcces = new Vector();
		try {
			vecteurCmbBxAcces = employeDAO.recupererDroitsAcces();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JComboBox cmbBxAcces = new JComboBox(vecteurCmbBxAcces);
		cmbBxAcces.setSelectedIndex(-1);
		cmbBxAcces.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		cmbBxAcces.setForeground(new Color(128, 0, 0));
		cmbBxAcces.setBounds(460, 127, 308, 27);
		contentPane.add(cmbBxAcces);
		
		
//BOUTON AJOUTER///////////////////////////////////////////////////////////////////////////////////////		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(new Color(128, 0, 0));
		btnAjouter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAjouter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogEmployeAjout nouvelUtilisateur = new JDialogEmployeAjout();
				nouvelUtilisateur.setVisible(true);
			}
		});
		btnAjouter.setBounds(814, 197, 117, 48);
		contentPane.add(btnAjouter);
		

		
//BOUTON MODIFIER///////////////////////////////////////////////////////////////////////////////////////	
		JButton btnModifier = new JButton("Consulter");
		btnModifier.setForeground(new Color(128, 0, 0));
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tableEmployes.getSelectedRow();
				String nom = (String) tableEmployes.getValueAt(row, 1);
				String prenom = (String) tableEmployes.getValueAt(row, 2);
				try {
					employe = employeDAO.afficherEmploye(nom, prenom);
					System.out.println(employe);
					JDialogEmployeAffichage jdEmployeAffichage = new JDialogEmployeAffichage(employe);
					jdEmployeAffichage.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*int row = tableEmployes.getSelectedRow();
				String droitacces = (String) tableEmployes.getValueAt(row, 0);
				String nom = (String) tableEmployes.getValueAt(row, 1);
				String prenom = (String) tableEmployes.getValueAt(row, 2);
				String poste = (String) tableEmployes.getValueAt(row, 3);
				String log = (String) tableEmployes.getValueAt(row, 4);
				String mdp = (String) tableEmployes.getValueAt(row, 5);

				tableEmployes.setValueAt(droitacces, row, 0);
				tableEmployes.setValueAt(nom, row, 1);
				tableEmployes.setValueAt(prenom, row, 2);
				tableEmployes.setValueAt(poste, row, 3);
				tableEmployes.setValueAt(log, row, 4);
				tableEmployes.setValueAt(mdp, row, 5);
				employe = new Employe(droitacces, nom, prenom, poste, log, mdp);
				try {
					employeDAO.modifierEmployer(employe, log);
					JOptionPane.showMessageDialog(null, employe.toString(), "Vous avez modifié le profil de : ", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
			}
		});
		btnModifier.setBounds(814, 282, 117, 48);
		contentPane.add(btnModifier);
		
		
		
//BOUTON SUPPRIMER///////////////////////////////////////////////////////////////////////////////////////
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(new Color(128, 0, 0));
		btnSupprimer.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimer.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableEmployes.getSelectedRow();
				String nom = (String) tableEmployes.getValueAt(row, 1);
				String prenom = (String) tableEmployes.getValueAt(row, 2);
				try {
					employeDAO.supprimerEmploye(nom, prenom);
					JOptionPane.showMessageDialog(null, "Utilisateur supprimé avec succès", "SUPPRESSION UTILISATEUR", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setBounds(814, 364, 117, 48);
		contentPane.add(btnSupprimer);
		
		
		

//BOUTON RECHERCHER///////////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setForeground(new Color(128, 0, 0));
		btnRechercher.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnRechercher.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSaisieNom.getText().length() > 0) {
					try {
						vectorEmploye = employeDAO.rechercherEmploye(txtSaisieNom.getText());
						DefaultTableModel autreModelEmploye = new DefaultTableModel(vectorEmploye, nomColonnes);
						tableEmployes.setModel(autreModelEmploye);
						cmbBxAcces.setSelectedIndex(-1);
						txtSaisieNom.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Oops, nous ne trouvons pas ce nom... veuillez réessayer !", "Nous sommes désolés", JOptionPane.INFORMATION_MESSAGE);
					}
				} else if(cmbBxAcces.getSelectedIndex()>=0) {
					try {
						vectorEmploye = employeDAO.rechercherEmployeparDroitAccess(cmbBxAcces.getSelectedItem().toString());
						DefaultTableModel encoreAutreModelEmploye = new DefaultTableModel(vectorEmploye, nomColonnes);
						tableEmployes.setModel(encoreAutreModelEmploye);
						cmbBxAcces.setSelectedIndex(-1);
						txtSaisieNom.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Oops, nous n'avons trouvé personne... veuillez réessayer !", "Nous sommes désolés", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}
		});
		btnRechercher.setBounds(783, 94, 148, 61);
		contentPane.add(btnRechercher);

		
		
//BOUTON QUITTER///////////////////////////////////////////////////////////////////////////////////////
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setForeground(new Color(128, 0, 0));
		btnQuitter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnQuitter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(814, 448, 117, 48);
		contentPane.add(btnQuitter);
		
		
		
		
		
//BOUTON RAFRAICHIR ///////////////////////////////////////////////////////////////////////////////////
		
		JButton btnRafraichir = new JButton("Rafraichir");
		btnRafraichir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				try {
				vectorEmploye = employeDAO.afficherEmployes();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DefaultTableModel model = new DefaultTableModel(vectorEmploye, nomColonnes);
				
				
				tableEmployes = new JTable(model);
				scrollPane.setViewportView(tableEmployes);
			}
		});
		btnRafraichir.setForeground(new Color(128, 0, 0));
		btnRafraichir.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnRafraichir.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnRafraichir.setBounds(81, 103, 117, 48);
		contentPane.add(btnRafraichir);

		
		
		pack();
		
		//if(employe.getDroitsAcces().equals("Stagiaire")) {
		//	btnAjouter.setEnabled(false);
		//}
	}
}


