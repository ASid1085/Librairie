package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daoLibrairie.EvenementDAO;
import entitiesLibrairie.Evenement;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;

public class JFrameEvenement extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private SimpleDateFormat formater = null;
	private Date currentDate ;
	private Vector<String> nomColonnes = new Vector();
	private Vector<Vector> vectorEvenement = new Vector();
	private EvenementDAO evenementDAO = new EvenementDAO();
	private Evenement evenement = new Evenement();

    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameEvenement frame = new JFrameEvenement();
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
	public JFrameEvenement() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		

//JLABEL//////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblNewLabel = new JLabel("EVENEMENTS");
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(39, 38, 921, 34);
		contentPane.add(lblNewLabel);
		
		
		
		
		formater = new SimpleDateFormat("yyyy-MM-dd");
		currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		JLabel lblDate = new JLabel("Date du jour : " + formater.format(currentDate));
		lblDate.setForeground(new Color(128, 0, 0));
		lblDate.setFont(new Font("Avenir Next", Font.PLAIN, 14));
		lblDate.setBounds(414, 117, 173, 16);
		contentPane.add(lblDate);

		
//JTABLE//////////////////////////////////////////////////////////////////////////////////

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 159, 921, 267);
		contentPane.add(scrollPane);
		
		nomColonnes.add("NOM DE L'EVENEMENT");
		nomColonnes.add("DATE DE DEBUT");
		nomColonnes.add("DATE DE FIN");
		nomColonnes.add("POURCENTAGE APPLIQUABLE");
		nomColonnes.add("CODE PROMO");
		nomColonnes.add("IMAGE");
		nomColonnes.add("COMMENTAIRE");
		
		
		try {
			vectorEvenement = evenementDAO.afficherEvenements();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DefaultTableModel model = new DefaultTableModel(vectorEvenement, nomColonnes);
		
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		

//BOUTON QUITTER//////////////////////////////////////////////////////////////////////////////////
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setForeground(new Color(128, 0, 0));
		btnQuitter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnQuitter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(843, 450, 117, 29);
		contentPane.add(btnQuitter);
		

		
		
//BOUTON RECHERCHER//////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercher = new JButton("Rechercher événement en cours");
		btnRechercher.setForeground(new Color(128, 0, 0));
		btnRechercher.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnRechercher.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					vectorEvenement = evenementDAO.rechercherEvenementparDate();
					DefaultTableModel newModel = new DefaultTableModel(vectorEvenement, nomColonnes);
					table.setModel(newModel);
					if (vectorEvenement.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Aucun événement en cours", "Rééssayer demain !", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnRechercher.setBounds(686, 110, 274, 29);
		contentPane.add(btnRechercher);
		
		
		
		
		
//BOUTON AJOUTER//////////////////////////////////////////////////////////////////////////////////
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setForeground(new Color(128, 0, 0));
		btnAjouter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAjouter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogEvenementAjout jdialog = new JDialogEvenementAjout();
				jdialog.setVisible(true);
			}
		});
		btnAjouter.setBounds(39, 450, 117, 29);
		contentPane.add(btnAjouter);
		
		
		
		
//BOUTON MODIFIER//////////////////////////////////////////////////////////////////////////////////
		JButton btnModifier = new JButton("Consulter");
		btnModifier.setForeground(new Color(128, 0, 0));
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String nom = (String) table.getValueAt(row, 0);
				try {
					evenement = evenementDAO.afficherEvenement(nom);
					JDialogEvenementAffichage jdEvenementAffichage = new JDialogEvenementAffichage(evenement);
					jdEvenementAffichage.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			
			}
		});
		btnModifier.setBounds(194, 450, 117, 29);
		contentPane.add(btnModifier);
		
		
		
		
//BOUTON SUPPRIMER//////////////////////////////////////////////////////////////////////////////////
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(new Color(128, 0, 0));
		btnSupprimer.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimer.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String nom = (String) table.getValueAt(row, 0);
				try {
					evenementDAO.supprimerEvenement(nom);
					JOptionPane.showMessageDialog(null,"Evénement supprimé avec succès" , "Suppression", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setBounds(349, 450, 117, 29);
		contentPane.add(btnSupprimer);
		
		
		
		
//BOUTON RAFRAICHIR//////////////////////////////////////////////////////////////////////////////////
		JButton btnRetour = new JButton("Rafraichir");
		btnRetour.setForeground(new Color(128, 0, 0));
		btnRetour.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnRetour.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					vectorEvenement = evenementDAO.afficherEvenements();
					DefaultTableModel model = new DefaultTableModel(vectorEvenement, nomColonnes);
					table = new JTable(model);
					scrollPane.setViewportView(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRetour.setBounds(39, 110, 117, 32);
		contentPane.add(btnRetour);
		
		
		

	}
}
