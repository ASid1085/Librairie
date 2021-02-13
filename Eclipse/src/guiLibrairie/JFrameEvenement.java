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
					JFrameEvenement frame = new JFrameEvenement();
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
	public JFrameEvenement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 850);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		

//JLABEL//////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblNewLabel = new JLabel("EVENEMENTS");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(124, 46, 1142, 34);
		contentPane.add(lblNewLabel);
		
		
		
		
		formater = new SimpleDateFormat("yyyy-MM-dd");
		currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		JLabel lblDate = new JLabel("Date du jour : " + formater.format(currentDate));
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		lblDate.setBounds(745, 122, 173, 16);
		contentPane.add(lblDate);

		
//JTABLE//////////////////////////////////////////////////////////////////////////////////

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(124, 171, 1142, 472);
		contentPane.add(scrollPane);
		
		nomColonnes.add("NOM DE L'EVENEMENT");
		nomColonnes.add("DATE DE DEBUT");
		nomColonnes.add("DATE DE FIN");
		nomColonnes.add("POURCENTAGE APPLIQUABLE");
		nomColonnes.add("CODE PROMO");
		nomColonnes.add("IMAGE");
		nomColonnes.add("COMMENTAIRE");
		
		
		try {
			vectorEvenement = evenementDAO.afficherEvenement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DefaultTableModel model = new DefaultTableModel(vectorEvenement, nomColonnes);
		
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		

//BOUTON QUITTER//////////////////////////////////////////////////////////////////////////////////
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnQuitter.setBounds(1142, 666, 117, 29);
		contentPane.add(btnQuitter);
		

		
		
//BOUTON RECHERCHER//////////////////////////////////////////////////////////////////////////////////
		JButton btnRechercher = new JButton("Rechercher événement en cours");
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
		btnRechercher.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnRechercher.setBounds(930, 117, 336, 29);
		contentPane.add(btnRechercher);
		
		
		
		
		
//BOUTON AJOUTER//////////////////////////////////////////////////////////////////////////////////
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialogEvenement jdialog = new JDialogEvenement();
				jdialog.setVisible(true);
			}
		});
		btnAjouter.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnAjouter.setBounds(124, 666, 117, 29);
		contentPane.add(btnAjouter);
		
		
		
		
//BOUTON MODIFIER//////////////////////////////////////////////////////////////////////////////////
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String nom = (String) table.getValueAt(row, 0);
				Date debut = (Date) table.getValueAt(row, 1);
				Date fin = (Date) table.getValueAt(row, 2);
				Float pourcentage = (Float) table.getValueAt(row, 3);
				String codePromo = (String) table.getValueAt(row, 4);
				String image = (String) table.getValueAt(row, 5);
				String comment = (String) table.getValueAt(row, 6);
				
				evenement = new Evenement(nom, debut, fin, pourcentage, codePromo, image, comment);
				try {
					evenementDAO.modifierEvenement(evenement, nom);
					JOptionPane.showMessageDialog(null, evenement.toString(), "Evénement modifié : ", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				table.setValueAt(nom, row, 0);
				table.setValueAt(debut, row, 1);
				table.setValueAt(fin, row, 2);
				table.setValueAt(pourcentage, row, 3);
				table.setValueAt(codePromo, row, 4);
				table.setValueAt(image, row, 5);
				table.setValueAt(comment, row, 6);
				
			
			}
		});
		btnModifier.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnModifier.setBounds(280, 666, 117, 29);
		contentPane.add(btnModifier);
		
		
		
		
//BOUTON SUPPRIMER//////////////////////////////////////////////////////////////////////////////////
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String nom = (String) table.getValueAt(row, 0);
				try {
					evenementDAO.supprimerEvenement(nom);
					JOptionPane.showMessageDialog(null, evenement.toString(), "Evénement supprimé avec succès ", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		btnSupprimer.setBounds(455, 666, 117, 29);
		contentPane.add(btnSupprimer);
		
		JButton btnRetour = new JButton("retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					vectorEvenement = evenementDAO.afficherEvenement();
					DefaultTableModel model = new DefaultTableModel(vectorEvenement, nomColonnes);
					
					
					table = new JTable(model);
					scrollPane.setViewportView(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRetour.setBounds(1190, 146, 70, 22);
		contentPane.add(btnRetour);
		
		
		

	}
}
