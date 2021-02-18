package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import daoLibrairie.daoGenre;
import entitiesLibrairie.Genre;

import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JFrameListeCategorie extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private daoGenre daoG = new daoGenre();
	private JTable table;
	private static JFrameGenre JFgen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameListeCategorie frame = new JFrameListeCategorie();
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
	public JFrameListeCategorie() {
		setTitle("Liste des catégories");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 544);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(6, 6, 767, 504);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		contentPane.add(tabbedPane);
		
		JLayeredPane layPaneAuteur = new JLayeredPane();
		tabbedPane.addTab("Auteur", null, layPaneAuteur, null);
		tabbedPane.setBackgroundAt(0, Color.ORANGE);
		tabbedPane.setForegroundAt(0, Color.DARK_GRAY);
		
		JLayeredPane layPaneEditeur = new JLayeredPane();
		layPaneEditeur.setForeground(Color.WHITE);
		layPaneEditeur.setBackground(Color.WHITE);
		tabbedPane.addTab("Éditeur", null, layPaneEditeur, null);
		tabbedPane.setForegroundAt(1, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(1, Color.ORANGE);
		
		JLayeredPane layPaneGenre = new JLayeredPane();
		layPaneGenre.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		layPaneGenre.setForeground(Color.WHITE);
		tabbedPane.addTab("Genre", null, layPaneGenre, null);
		tabbedPane.setBackgroundAt(2, Color.ORANGE);
		tabbedPane.setForegroundAt(2, Color.DARK_GRAY);
		
		JPanel paneGenre = new JPanel();
		paneGenre.setBackground(new Color(255, 248, 220));
		paneGenre.setBounds(6, 6, 709, 471);
		layPaneGenre.add(paneGenre);
		paneGenre.setLayout(null);
		
		JLabel lblNomGenre = new JLabel("Nom genre :");
		lblNomGenre.setBounds(182, 49, 80, 18);
		lblNomGenre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		paneGenre.add(lblNomGenre);
		
		JLabel lblRechercherPar = new JLabel("Rechercher par");
		lblRechercherPar.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercherPar.setBounds(22, 16, 142, 33);
		paneGenre.add(lblRechercherPar);
		
		textField = new JTextField();
		textField.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(274, 45, 162, 26);
		paneGenre.add(textField);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(6, 94, 697, 293);
		paneGenre.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground( new Color(255, 248, 220));
		scrollPane.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		scrollPane.setBounds(6, 6, 685, 281);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 248, 220));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView( table);
		table.setShowGrid( true);
		table.setShowHorizontalLines( true);
		table.setShowVerticalLines( true);
		table.getTableHeader().setBounds( 6, 6, 685, 281);
		table.getTableHeader().setVisible( true);
		try {
			table.setModel( daoG.listeGenre());
		} catch (SQLException e1) {
			System.err.println( "Oops : erreur avec l'affichage de la liste Genre - Voir JFrameListeCategorie & daoGenre");
			e1.printStackTrace();
		}
		table.getColumnModel().getColumn( 1).setPreferredWidth(20);
		
		JLayeredPane layPaneMotCle = new JLayeredPane();
		layPaneGenre.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		layPaneGenre.setForeground(Color.WHITE);
		tabbedPane.addTab("Mot-clé", null, layPaneMotCle, null);
		tabbedPane.setForegroundAt(3, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(3, Color.ORANGE);
		
		JLayeredPane layPaneTheme = new JLayeredPane();
		tabbedPane.addTab("Thème", null, layPaneTheme, null);
		tabbedPane.setForegroundAt(4, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(4, Color.ORANGE);
		
		JButton btnLoupe = new JButton("");
		btnLoupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				daoGenre DaoG = new daoGenre();
			}
		});
		btnLoupe.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupe.setBounds(459, 31, 55, 55);
		paneGenre.add(btnLoupe);
		
		JButton btnCreer = new JButton("Créer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFgen = new JFrameGenre( "");
				JFgen.setLocationRelativeTo( JFgen.getParent());
				JFgen.setVisible( true);
				setVisible( false);
				dispose();
			}
		});
		btnCreer.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnCreer.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCreer.setBounds(66, 399, 129, 54);
		paneGenre.add(btnCreer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genreNomSelect = "";
				try {
					genreNomSelect = (String) daoG.listeGenre().getValueAt(table.getSelectedRow(), 1);
					JFgen = new JFrameGenre( genreNomSelect);
					JFgen.setLocationRelativeTo( JFgen.getParent());
					JFgen.setVisible( true);
					setVisible( false);
					dispose();
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un genre à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour modification d'un genre - Voir JFrameListeCategorie & daoGenre");
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.setBounds(292, 399, 129, 54);
		paneGenre.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String genreNomSelect = "";
				try {
					genreNomSelect = (String) daoG.listeGenre().getValueAt(table.getSelectedRow(), 1);
					daoG.supprimerGenre( genreNomSelect);
					table.setModel( daoG.listeGenre());
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un genre à supprimer !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la récupération du nom pour suppression d'un genre - Voir JFrameListeCategorie & daoGenre");
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimer.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimer.setBounds(510, 399, 129, 54);
		paneGenre.add(btnSupprimer);
	}
}
