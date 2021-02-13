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
					JFrameEmploye frame = new JFrameEmploye(employeDroits);
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
	public JFrameEmploye(Employe employeATraiter) {


		this.employe = employeATraiter ;
		
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		setPreferredSize(new Dimension(1400, 850));
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		

		
//JTABLE///////////////////////////////////////////////////////////////////////////////////////
		
		Vector <String> nomColonnes = new Vector();
		nomColonnes.add("ACCES");
		nomColonnes.add("NOM");
		nomColonnes.add("PRENOM");
		nomColonnes.add("POSTE");
		nomColonnes.add("LOGIN");
		nomColonnes.add("MOT DE PASSE");

		try {
			vectorEmploye = employeDAO.afficherEmploye();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Oops, petit problème technique", "Nous sommes désolés", JOptionPane.INFORMATION_MESSAGE);
		}

		
		DefaultTableModel modelEmploye = new DefaultTableModel(vectorEmploye, nomColonnes);
		tableEmployes = new JTable();
		tableEmployes.setModel(modelEmploye);
		tableEmployes.setBounds(81, 97, 1200, 650);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 163, 1230, 527);
		scrollPane.setViewportView(tableEmployes);
		contentPane.add(scrollPane);

	
		
//TEXTFIELDS///////////////////////////////////////////////////////////////////////////////////////
		txtSaisieNom = new JTextField();
		txtSaisieNom.setColumns(10);
		txtSaisieNom.setBounds(786, 94, 365, 26);
		contentPane.add(txtSaisieNom);
		

//JLABELS///////////////////////////////////////////////////////////////////////////////////////		
		JLabel lblSaisieNom = new JLabel("Saisir un nom");
		lblSaisieNom.setForeground(Color.WHITE);
		lblSaisieNom.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		lblSaisieNom.setBounds(674, 98, 218, 16);
		contentPane.add(lblSaisieNom);
		
		JLabel lblSaisirUnDroit = new JLabel("Saisir un droit d'accès");
		lblSaisirUnDroit.setForeground(Color.WHITE);
		lblSaisirUnDroit.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		lblSaisirUnDroit.setBounds(674, 127, 218, 16);
		contentPane.add(lblSaisirUnDroit);

		
		JLabel lblTitre = new JLabel("GESTION DES EMPLOYES");
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
		lblTitre.setBounds(81, 38, 1200, 30);
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
		cmbBxAcces.setBounds(846, 124, 308, 27);
		contentPane.add(cmbBxAcces);
		
		
//BOUTON AJOUTER///////////////////////////////////////////////////////////////////////////////////////		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogEmployeAjout nouvelUtilisateur = new JDialogEmployeAjout();
				nouvelUtilisateur.setVisible(true);
			}
		});
		btnAjouter.setForeground(Color.BLACK);
		btnAjouter.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnAjouter.setBounds(81, 705, 117, 48);
		contentPane.add(btnAjouter);
		

		
//BOUTON MODIFIER///////////////////////////////////////////////////////////////////////////////////////	
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableEmployes.getSelectedRow();
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
			}
		});
		btnModifier.setForeground(Color.BLACK);
		btnModifier.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnModifier.setBounds(255, 705, 117, 48);
		contentPane.add(btnModifier);
		
		
		
//BOUTON SUPPRIMER///////////////////////////////////////////////////////////////////////////////////////
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableEmployes.getSelectedRow();
				String log = (String) tableEmployes.getValueAt(row, 4);
				try {
					employeDAO.supprimerEmploye(log);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setForeground(Color.BLACK);
		btnSupprimer.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnSupprimer.setBounds(428, 705, 117, 48);
		contentPane.add(btnSupprimer);
		
		
		

//BOUTON RECHERCHER///////////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSaisieNom.getText().length() > 0) {
					try {
						vectorEmploye = employeDAO.rechercherEmploye(txtSaisieNom.getText());
						DefaultTableModel autreModelEmploye = new DefaultTableModel(vectorEmploye, nomColonnes);
						tableEmployes.setModel(autreModelEmploye);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Oops, nous ne trouvons pas ce nom... veuillez réessayer !", "Nous sommes désolés", JOptionPane.INFORMATION_MESSAGE);
					}
				} else if(cmbBxAcces.getSelectedIndex()>=0) {
					try {
						vectorEmploye = employeDAO.rechercherEmployeparDroitAccess(cmbBxAcces.getSelectedItem().toString());
						DefaultTableModel encoreAutreModelEmploye = new DefaultTableModel(vectorEmploye, nomColonnes);
						tableEmployes.setModel(encoreAutreModelEmploye);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Oops, nous n'avons trouvé personne... veuillez réessayer !", "Nous sommes désolés", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}
		});
		btnRechercher.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnRechercher.setBounds(1163, 90, 148, 61);
		contentPane.add(btnRechercher);

		
		
//BOUTON QUITTER///////////////////////////////////////////////////////////////////////////////////////
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setForeground(Color.BLACK);
		btnQuitter.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnQuitter.setBounds(1195, 705, 117, 48);
		contentPane.add(btnQuitter);

		
		
		pack();
		
		if(employe.getDroitsAcces().equals("Stagiaire")) {
			btnAjouter.setEnabled(false);
		}
	}
}


