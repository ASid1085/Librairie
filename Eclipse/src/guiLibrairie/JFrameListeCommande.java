package guiLibrairie;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class JFrameListeCommande extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumCde;
	private JTextField txtDateCde;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameListeCommande frame = new JFrameListeCommande();
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
	public JFrameListeCommande() {
		setTitle("Liste des commandes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 544);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRechercherPar = new JLabel("Rechercher par");
		lblRechercherPar.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercherPar.setBounds(18, 18, 142, 33);
		contentPane.add(lblRechercherPar);
		
		JLabel lblLogin = new JLabel("Login client :");
		lblLogin.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLogin.setBounds(28, 54, 82, 16);
		contentPane.add(lblLogin);
		
		JComboBox cmbBxLoginClient = new JComboBox();
		cmbBxLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBxLoginClient.setBounds(104, 50, 130, 27);
		contentPane.add(cmbBxLoginClient);
		
		JLabel lblNumCde = new JLabel("n° de commande :");
		lblNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNumCde.setBounds(246, 54, 115, 16);
		contentPane.add(lblNumCde);
		
		txtNumCde = new JTextField();
		txtNumCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNumCde.setColumns(10);
		txtNumCde.setBounds(358, 52, 107, 20);
		contentPane.add(txtNumCde);
		
		JLabel lblDateCde = new JLabel("Date commande :");
		lblDateCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblDateCde.setBounds(477, 54, 115, 16);
		contentPane.add(lblDateCde);
		
		txtDateCde = new JTextField();
		txtDateCde.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtDateCde.setColumns(10);
		txtDateCde.setBounds(591, 52, 100, 20);
		contentPane.add(txtDateCde);
		
		JLabel lblStatutLivre = new JLabel("Statut livre :");
		lblStatutLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblStatutLivre.setBounds(38, 82, 82, 16);
		contentPane.add(lblStatutLivre);
		
		JComboBox cmbBxStatutLivre = new JComboBox();
		cmbBxStatutLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBxStatutLivre.setBounds(104, 78, 130, 27);
		contentPane.add(cmbBxStatutLivre);
		
		JLabel lblTitreLivre = new JLabel("Titre livre :");
		lblTitreLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTitreLivre.setBounds(246, 82, 82, 16);
		contentPane.add(lblTitreLivre);
		
		JComboBox cmbBxTitreLivre = new JComboBox();
		cmbBxTitreLivre.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBxTitreLivre.setBounds(310, 78, 362, 27);
		contentPane.add(cmbBxTitreLivre);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(18, 118, 737, 311);
		contentPane.add(panel);
		
		table = new JTable();
		// table.setModel( A compléter le moment venu);
		table.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		table.setFont(new Font("Cochin", Font.PLAIN, 15));
		table.setShowGrid( true);
		table.setShowHorizontalLines( true);
		table.setShowVerticalLines( true);
		table.getTableHeader().setBounds( 6, 6, 500, 324);
		table.getTableHeader().setVisible( true);
		panel.add(table);
		
		JButton btnLoupe = new JButton("");
		btnLoupe.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupe.setBounds(703, 43, 55, 55);
		contentPane.add(btnLoupe);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAjouter.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAjouter.setBounds(155, 456, 173, 54);
		contentPane.add(btnAjouter);
		
		JButton btnConsulterModifier = new JButton("<html><center>Consulter /<br>modifier</center></html>");
		btnConsulterModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnConsulterModifier.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnConsulterModifier.setBounds(434, 456, 173, 54);
		contentPane.add(btnConsulterModifier);
	}
}
