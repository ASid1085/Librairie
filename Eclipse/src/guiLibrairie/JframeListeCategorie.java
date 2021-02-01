package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class JframeListeCategorie extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JframeListeCategorie frame = new JframeListeCategorie();
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
	public JframeListeCategorie() {
		setTitle("Liste des catégories");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		tabbedPane.addTab("Genre", null, layPaneGenre, null);
		tabbedPane.setForegroundAt(2, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(2, Color.ORANGE);
		
		JLayeredPane layPaneMotCle = new JLayeredPane();
		tabbedPane.addTab("Mot-clé", null, layPaneMotCle, null);
		tabbedPane.setForegroundAt(3, Color.DARK_GRAY);
		tabbedPane.setBackgroundAt(3, Color.ORANGE);
		
		JLayeredPane layPaneTheme = new JLayeredPane();
		layPaneTheme.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, Color.ORANGE));
		layPaneTheme.setForeground(Color.WHITE);
		tabbedPane.addTab("Thème", null, layPaneTheme, null);
		
		JPanel paneGenre = new JPanel();
		paneGenre.setBackground(new Color(255, 248, 220));
		paneGenre.setBounds(6, 6, 709, 471);
		layPaneTheme.add(paneGenre);
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
		
		JButton btnLoupe = new JButton("");
		btnLoupe.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupe.setBounds(459, 31, 55, 55);
		paneGenre.add(btnLoupe);
		
		JList list = new JList();
		list.setVisibleRowCount(100);
		list.setBounds(22, 98, 666, 285);
		paneGenre.add(list);
		
		JButton btnCréer = new JButton("Créer");
		btnCréer.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnCréer.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCréer.setBounds(66, 399, 129, 54);
		paneGenre.add(btnCréer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.setBounds(292, 399, 129, 54);
		paneGenre.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSupprimer.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSupprimer.setBounds(510, 399, 129, 54);
		paneGenre.add(btnSupprimer);
		tabbedPane.setBackgroundAt(4, Color.ORANGE);
		tabbedPane.setForegroundAt(4, Color.DARK_GRAY);
	}
}
