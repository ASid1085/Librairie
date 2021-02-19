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
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private static JDialogLivreAjout jdLAstatic;
	private static String status;
	private DefaultListModel vecteurTheme = new DefaultListModel();

	
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
					JFrameTheme frame = new JFrameTheme(jdLAstatic, status);
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
	public JFrameTheme(JDialogLivreAjout jdLA, String statut) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
//JTABLE//////////////////////////////////////////////////////////////////////
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 148, 226, 263);
		contentPane.add(scrollPane);
		nomColonne.add("ID");
		nomColonne.add("THEME");
		try {
			vectorTheme = themeDAO.afficherTheme();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultTableModel model = new DefaultTableModel(vectorTheme, nomColonne);
		
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		
		
		
//LABEL//////////////////////////////////////////////////////////////////////
		JLabel lblTitre = new JLabel("THEMES");
		lblTitre.setForeground(new Color(255, 215, 0));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		lblTitre.setBounds(106, 39, 769, 32);
		contentPane.add(lblTitre);
		
		
		
//JTEXTFIELD//////////////////////////////////////////////////////////////////////
		txtSaisieNom = new JTextField();
		txtSaisieNom.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
		txtSaisieNom.setBounds(396, 148, 325, 26);
		contentPane.add(txtSaisieNom);
		txtSaisieNom.setColumns(10);
		
		
		
//BOUTON RECHERCHER//////////////////////////////////////////////////////////////////////
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setForeground(new Color(128, 0, 0));
		btnRechercher.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnRechercher.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
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
		btnRechercher.setBounds(758, 146, 117, 29);
		contentPane.add(btnRechercher);
		
		
		JInternalFrameTheme jifTheme = new JInternalFrameTheme();
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255,248,220));
		desktopPane.setBounds(400, 256, 475, 251);
		contentPane.add(desktopPane);
	
		
		
//BOUTON AJOUTER//////////////////////////////////////////////////////////////////////
		JButton btnAjouter = new JButton("Nouveau thème");
		btnAjouter.setForeground(new Color(128, 0, 0));
		btnAjouter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAjouter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrameTheme jifTheme = new JInternalFrameTheme();
				jifTheme.setVisible(true);
				desktopPane.add(jifTheme);
				jifTheme.toFront();
				
			}
		});
		btnAjouter.setBounds(400, 215, 475, 29);
		contentPane.add(btnAjouter);
		
		
		
		
		
//BOUTON MODIFIER//////////////////////////////////////////////////////////////////////
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setForeground(new Color(128, 0, 0));
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				//Vector s = vectorTheme.elementAt(row);
				//String str = s.toString();
				//Theme theme = new Theme(str);
				String nom = (String) table.getValueAt(row, 1);
				String id = (String) table.getValueAt(row, 0);

				try {
					themeDAO.modifierTheme(id, nom);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setBounds(106, 478, 226, 29);
		contentPane.add(btnModifier);
		
		
		
//BOUTON SUPPRIMER//////////////////////////////////////////////////////////////////////
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setForeground(new Color(128, 0, 0));
		btnSupprimer.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimer.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String id = (String) table.getValueAt(row, 0);
				try {
					themeDAO.supprimerTheme(id);
					DefaultTableModel nouveaumodel = new DefaultTableModel(vectorTheme, nomColonne);
					table.setModel(nouveaumodel);
					JOptionPane.showMessageDialog(null, "Theme supprimé avec succès", "Message", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setBounds(106, 525, 226, 29);
		contentPane.add(btnSupprimer);
		
		
		
//BOUTON QUITTER//////////////////////////////////////////////////////////////////////
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setForeground(new Color(128, 0, 0));
		btnQuitter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnQuitter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(758, 525, 117, 29);
		contentPane.add(btnQuitter);
		
		
		
//BOUTON RAFRAICHIR///////////////////////////////////////////////////////////////////
		JButton btnRafraichir = new JButton("Rafraichir");
		btnRafraichir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					vectorTheme = themeDAO.afficherTheme();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultTableModel model = new DefaultTableModel(vectorTheme, nomColonne);
				table = new JTable(model);
				scrollPane.setViewportView(table);
			}
		});
		btnRafraichir.setForeground(new Color(128, 0, 0));
		btnRafraichir.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnRafraichir.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnRafraichir.setBounds(396, 527, 117, 29);
		contentPane.add(btnRafraichir);
		
		
		
		if(statut.equals("AJOUT LIVRE")) {
		
//BOUTON SELECTIONNER///////////////////////////////////////////////////////////////////		
		JButton btnSelectionner = new JButton("Sélectionner");
		btnSelectionner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				
				
				for (int i = 0; i <= table.getRowCount(); i++) {
					if (table.isCellSelected(i, 1)) {
						String themeSelected = (String) table.getValueAt(i, 1);
						vecteurTheme.addElement(themeSelected);
					}
				}
				//Theme theme2 = new Theme();
				//theme2.setThemeNom(theme);
				jdLA.refreshTheme(vecteurTheme);
				jdLA.repaint();
				jdLA.setVisible(true);
				dispose();
			}
		});
		btnSelectionner.setForeground(new Color(128, 0, 0));
		btnSelectionner.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSelectionner.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSelectionner.setBounds(106, 433, 226, 29);
		contentPane.add(btnSelectionner);
		
		}
	}
	
	
}
