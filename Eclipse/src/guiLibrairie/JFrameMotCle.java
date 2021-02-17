package guiLibrairie;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import daoLibrairie.daoGenre;
import daoLibrairie.daoMotCle;
import entitiesLibrairie.Genre;
import entitiesLibrairie.MotCle;

public class JFrameMotCle extends JFrame {

	private daoMotCle daoMc = new daoMotCle();
	private JPanel contentPane;
	private JTextField txtMotCleId;
	private JTextField txtMotCleLib;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMotCle frame = new JFrameMotCle( "");
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
	public JFrameMotCle( String motCleLib) {
		
		if (!motCleLib.equals( "")) { 
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					try {
						
						MotCle modifMotCle = daoMc.findMotCleByLibelle( motCleLib);
						txtMotCleId.setText( modifMotCle.getMotCleId());
						txtMotCleLib.setText( modifMotCle.getMotCleLibelle());
					} catch (SQLException ex) {
						ex.printStackTrace();
					}			
				}
			});
		}
		setTitle("Mot-clé");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 439, 369);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMotCleId = new JLabel("Mot-clé Id : ");
		lblMotCleId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotCleId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblMotCleId.setBounds(33, 60, 83, 16);
		contentPane.add(lblMotCleId);
		
		txtMotCleId = new JTextField();
		txtMotCleId.setText( daoMc.ajoutIdMotCle());
		txtMotCleId.setBounds(150, 55, 189, 26);
		txtMotCleId.setEnabled( false);
		txtMotCleId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		contentPane.add(txtMotCleId);
		txtMotCleId.setColumns(10);
		
		JLabel lblMotCleLib = new JLabel("Mot-clé Libellé :");
		lblMotCleLib.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotCleLib.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblMotCleLib.setBounds(19, 170, 97, 16);
		contentPane.add(lblMotCleLib);
		
		txtMotCleLib = new JTextField();
		txtMotCleLib.setBounds(150, 165, 189, 26);
		txtMotCleLib.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		contentPane.add(txtMotCleLib);
		txtMotCleLib.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MotCle mc = new MotCle( txtMotCleId.getText(), txtMotCleLib.getText());
				if ( motCleLib.equals( "")) {
					try {
						daoMc.ajouterMotCle( mc);
						setVisible( false);
						dispose();
					} catch (SQLException e1) {
						System.err.println( "Oops : erreur avec la validation d'un nouveau genre - Voir JFrameGenre & daoGenre");
						e1.printStackTrace();
					}
				} 
				try {
					daoMc.modifierMotCle( mc, motCleLib);
					setVisible( false);
					dispose();
					JFrameListeCategorie lc = new JFrameListeCategorie();
					lc.setLocationRelativeTo( lc.getParent());
					lc.setVisible( true);

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
				txtMotCleId.setText( "");
				txtMotCleLib.setText( "");
			}
		});
		btnAnnuler.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAnnuler.setBounds(69, 256, 97, 41);
		btnAnnuler.setBounds(258, 257, 97, 41);
		contentPane.add(btnAnnuler);
	}
}
