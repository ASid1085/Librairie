package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;

public class JFrameAccueil {
	
	private JFrame frame;
	private JPanel contentPane;
	private static JFrameListeCommande lcde;
	private static JFrameListeClient lclt;
	private static JFrameListeCategorie lcat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// test window = new test();
					//window.frame.setVisible(true);
					
					JFrameAccueil frame = new JFrameAccueil();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public JFrameAccueil() {
		initialize();
	}
	/**
	 * Create the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Accueil");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 663, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGestionCategorie = new JButton( "<html><center>Gestion<br>catégorie</center></html>");
		btnGestionCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lcat = new JFrameListeCategorie();
				lcat.setLocationRelativeTo( lcat.getParent());
				lcat.setVisible( true);
			}
		});
		btnGestionCategorie.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnGestionCategorie.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionCategorie.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnGestCategorie.png"));
		btnGestionCategorie.setBounds(57, 43, 119, 95);
		contentPane.add(btnGestionCategorie);
		
		JButton btnGestionEvenement = new JButton( "<html><center>Gestion<br>évènement</center></html>");
		btnGestionEvenement.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnGestEvenement.png"));
		btnGestionEvenement.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnGestionEvenement.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionEvenement.setBounds(274, 43, 119, 95);
		contentPane.add(btnGestionEvenement);
		
		JButton btnGestionClient = new JButton("<html><center>Gestion<br>client</center></html>");
		btnGestionClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lclt = new JFrameListeClient();
				lclt.setLocationRelativeTo( lclt.getParent());
				lclt.setVisible( true);
			}
		});
		btnGestionClient.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnGestClient.png"));
		btnGestionClient.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnGestionClient.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionClient.setBounds(486, 43, 119, 95);
		contentPane.add(btnGestionClient);
		
		JButton btnGestionOuvrage = new JButton("<html><center>Gestion<br>ouvrage</center></html>");
		btnGestionOuvrage.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnGestOuvrage.png"));
		btnGestionOuvrage.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnGestionOuvrage.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionOuvrage.setBounds(57, 191, 119, 95);
		contentPane.add(btnGestionOuvrage);
		
		JButton btnGestionEmployé = new JButton("<html><center>Gestion<br>employé</center></html>");
		btnGestionEmployé.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnGestEmploye.png"));
		btnGestionEmployé.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnGestionEmployé.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionEmployé.setBounds(274, 191, 119, 95);
		contentPane.add(btnGestionEmployé);
		
		JButton btnGestionCommande = new JButton("<html><center>Gestion<br>commande</center></html>");
		btnGestionCommande.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnGestCommande.png"));
		btnGestionCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lcde = new JFrameListeCommande();
				lcde.setLocationRelativeTo( lcde.getParent());
				lcde.setVisible( true);
			}
		});
		btnGestionCommande.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnGestionCommande.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnGestionCommande.setBounds(486, 191, 119, 95);
		contentPane.add(btnGestionCommande);
		
		JButton btnCatalogue = new JButton("Catalogue");
		btnCatalogue.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnCatalogue.png"));
		btnCatalogue.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnCatalogue.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCatalogue.setBounds(57, 335, 548, 75);
		contentPane.add(btnCatalogue);
		
		Component glue = Box.createGlue();
		glue.setBounds(6, 6, 651, 427);
		contentPane.add(glue);
	}
}
