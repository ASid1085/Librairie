package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JFrameListeClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomClient;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameListeClient frame = new JFrameListeClient();
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
	public JFrameListeClient() {
		setTitle("Liste des clients");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		lblLogin.setBounds(47, 61, 82, 16);
		contentPane.add(lblLogin);
		
		JComboBox cmbBxLoginClient = new JComboBox();
		cmbBxLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBxLoginClient.setBounds(127, 57, 180, 27);
		contentPane.add(cmbBxLoginClient);
		
		JLabel lblNomClient = new JLabel("Nom client :");
		lblNomClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomClient.setBounds(398, 61, 82, 16);
		contentPane.add(lblNomClient);
		
		JPanel panel = new JPanel();
		panel.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(18, 118, 737, 311);
		contentPane.add(panel);
		
		table = new JTable();
		table.setFont(new Font("Cochin", Font.PLAIN, 15));
		table.setShowGrid( true);
		table.setShowHorizontalLines( true);
		table.setShowVerticalLines( true);
		table.getTableHeader().setBounds( 6, 6, 731, 305);
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
		
		txtNomClient = new JTextField();
		txtNomClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNomClient.setBounds(478, 55, 162, 26);
		contentPane.add(txtNomClient);
		txtNomClient.setColumns(10);
	}
}
