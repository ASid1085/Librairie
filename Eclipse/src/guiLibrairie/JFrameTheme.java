package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daoLibrairie.ThemeDAO;
import entitiesLibrairie.Theme;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JDesktopPane;

public class JFrameTheme extends JFrame {

	private JPanel contentPane;
	private JTextField txtSaisieNom;
	private JTable table;
	private Theme theme = new Theme();
	private ThemeDAO themeDAO = new ThemeDAO();
	private Vector<Vector> vectorTheme = new Vector();
	private Vector nomColonne = new Vector();

	
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
					JFrameTheme frame = new JFrameTheme();
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
	public JFrameTheme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 850);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
//JTABLE//////////////////////////////////////////////////////////////////////
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 160, 266, 531);
		contentPane.add(scrollPane);
		
		nomColonne.add("THEME");
		try {
			vectorTheme = themeDAO.afficherTheme();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultTableModel model = new DefaultTableModel(vectorTheme, nomColonne);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		
		
//LABEL//////////////////////////////////////////////////////////////////////
		JLabel lblTitre = new JLabel("Thèmes");
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
		lblTitre.setBounds(141, 39, 1143, 32);
		contentPane.add(lblTitre);
		
		
		
//JTEXTFIELD//////////////////////////////////////////////////////////////////////
		txtSaisieNom = new JTextField();
		txtSaisieNom.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		txtSaisieNom.setBounds(629, 160, 325, 26);
		contentPane.add(txtSaisieNom);
		txtSaisieNom.setColumns(10);
		
		
		
//BOUTON RECHERCHER//////////////////////////////////////////////////////////////////////
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = txtSaisieNom.getText();
				try {
					vectorTheme = themeDAO.rechercherTheme(nom);
					DefaultTableModel nvmodel = new DefaultTableModel(vectorTheme, nomColonne);
					table.setModel(nvmodel);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRechercher.setBounds(981, 159, 117, 29);
		contentPane.add(btnRechercher);
		
		
		JInternalFrameTheme jifTheme = new JInternalFrameTheme();
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.DARK_GRAY);
		desktopPane.setBounds(636, 321, 462, 370);
		contentPane.add(desktopPane);
	
		
		
//BOUTON AJOUTER//////////////////////////////////////////////////////////////////////
		JButton btnAjouter = new JButton("Nouveau thème");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrameTheme jifTheme = new JInternalFrameTheme();
				jifTheme.setVisible(true);
				desktopPane.add(jifTheme);
				jifTheme.toFront();
				
			}
		});
		btnAjouter.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnAjouter.setBounds(629, 289, 481, 29);
		contentPane.add(btnAjouter);
		
		
		
		
		
//BOUTON MODIFIER//////////////////////////////////////////////////////////////////////
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Vector s = vectorTheme.elementAt(row);
				String str = s.toString();
				Theme theme = new Theme(str);
				String nom = (String) table.getValueAt(row, 0);

				try {
					themeDAO.modifierTheme(theme, nom);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnModifier.setBounds(304, 703, 283, 29);
		contentPane.add(btnModifier);
		
		
		
//BOUTON SUPPRIMER//////////////////////////////////////////////////////////////////////
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String nom = (String) table.getValueAt(row, 0);
				try {
					themeDAO.supprimerTheme(nom);
					DefaultTableModel nouveaumodel = new DefaultTableModel(vectorTheme, nomColonne);
					table.setModel(nouveaumodel);
					JOptionPane.showMessageDialog(null, "Theme supprimé avec succès", "Message", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnSupprimer.setBounds(304, 744, 283, 29);
		contentPane.add(btnSupprimer);
		
		
		
//BOUTON QUITTER//////////////////////////////////////////////////////////////////////
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		btnQuitter.setBounds(1167, 721, 117, 29);
		contentPane.add(btnQuitter);
		
		
	}

}
