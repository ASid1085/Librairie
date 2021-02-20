package guiLibrairie;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import daoLibrairie.daoGenre;
import entitiesLibrairie.Genre;

public class JFrameGenre extends JFrame {

	private daoGenre daoG = new daoGenre();
	private JPanel contentPane;
	private JTextField txtGenreId;
	private JTextField txtGenreNom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameGenre frame = new JFrameGenre( "");
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
	public JFrameGenre( String genreNom) {
		
		if (!genreNom.equals( "")) { 
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					try {
						Genre modifGenre = daoG.findGenreByNom( genreNom);
						txtGenreId.setText( modifGenre.getGenreId());
						txtGenreNom.setText( modifGenre.getGenreNom());
					} catch (SQLException ex) {
						ex.printStackTrace();
					}			
				}
			});
		}
		setTitle("Genre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 439, 369);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGenreId = new JLabel("Genre Id : ");
		lblGenreId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenreId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblGenreId.setBounds(33, 60, 83, 16);
		contentPane.add(lblGenreId);
		
		txtGenreId = new JTextField();
		txtGenreId.setText( daoG.ajoutIdGenre());
		txtGenreId.setBounds(150, 55, 189, 26);
		txtGenreId.setEnabled( false);
		txtGenreId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		contentPane.add(txtGenreId);
		txtGenreId.setColumns(10);
		
		JLabel lblGenreNom = new JLabel("Genre nom :");
		lblGenreNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGenreNom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblGenreNom.setBounds(19, 170, 97, 16);
		contentPane.add(lblGenreNom);
		
		txtGenreNom = new JTextField();
		txtGenreNom.setBounds(150, 165, 189, 26);
		txtGenreNom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		contentPane.add(txtGenreNom);
		txtGenreNom.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Genre g = new Genre( txtGenreId.getText(), txtGenreNom.getText());
				if (genreNom.equals( "")) {
					try {
						daoG.ajouterGenre( g);
						setVisible( false);
						dispose();
					} catch (SQLException e1) {
						System.err.println( "Oops : erreur avec la validation d'un nouveau genre - Voir JFrameGenre & daoGenre");
						e1.printStackTrace();
					}
				} 
				try {
					daoG.modifierGenre( g, genreNom);
					setVisible(false);
					dispose();
					//JFrameListeCategorie lc = new JFrameListeCategorie();
					//lc.setLocationRelativeTo( lc.getParent());
					//lc.setVisible( true);

				} catch (SQLException e1) {
					System.err.println( "Oops : erreur avec la modification d'un nouveau genre - Voir JFrameGenre & daoGenre");
					e1.printStackTrace();
				}
			}
		});
		btnValider.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnValider.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnValider.setBounds(69, 256, 97, 41);
		contentPane.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtGenreId.setText( "");
				txtGenreNom.setText( "");
			}
		});
		btnAnnuler.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAnnuler.setBounds(69, 256, 97, 41);
		btnAnnuler.setBounds(258, 257, 97, 41);
		contentPane.add(btnAnnuler);
	}
}
