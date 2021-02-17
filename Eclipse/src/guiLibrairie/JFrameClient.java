package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoLibrairie.daoClient;
import entitiesLibrairie.Client;
import entitiesLibrairie.Genre;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JFrameClient extends JFrame {

	private daoClient daoClt = new daoClient();
	private JComboBox cmbBoxStatut;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtMdp;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtTelephone;
	private JTextField txtMail;
	private String [] statClt = { "--", "Compte actif", "Compte supprimé", "Compte suspendu", "Compte désactivé", "NPAI"};
	private DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<>( statClt);
	private static JDialogCommentaireClient jdcc;
		
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameClient frame = new JFrameClient( "", null, "");
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
	public JFrameClient(String ClientLogin, JFrameLigneCommande frameLigCde, String etat) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if (!etat.equals( "Ajouter")) {
					try {
						Client clt = daoClt.findClientByLogin( ClientLogin);

						txtLogin.setText( clt.getClientLogin());
						txtMdp.setText( clt.getClientMdp());
						txtNom.setText( clt.getClientNom());
						txtPrenom.setText( clt.getClientPrenom());
						cmbBoxStatut.setSelectedItem( clt.getClientStatuts());;
						txtTelephone.setText( clt.getClientTel());
						txtMail.setText( clt.getClientEmail());
					} catch (SQLException ex) {
						ex.printStackTrace();
					}	
				}
			}
		});
		setTitle("Client");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 618, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login :");
		lblLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLogin.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLogin.setBounds(44, 55, 61, 16);
		contentPane.add(lblLogin);
		
		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtLogin.setBounds(117, 50, 130, 26);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblMdp = new JLabel("Mot de passe :");
		lblMdp.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblMdp.setBounds(292, 55, 86, 16);
		contentPane.add(lblMdp);
		
		txtMdp = new JTextField();
		txtMdp.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtMdp.setColumns(10);
		txtMdp.setBounds(390, 50, 130, 26);
		contentPane.add(txtMdp);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNom.setBounds(44, 143, 61, 16);
		contentPane.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNom.setColumns(10);
		txtNom.setBounds(117, 138, 130, 26);
		contentPane.add(txtNom);
		
		JLabel lblPrenom = new JLabel("Prénom :");
		lblPrenom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblPrenom.setBounds(317, 143, 61, 16);
		contentPane.add(lblPrenom);
		
		txtPrenom = new JTextField();
		txtPrenom.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(390, 138, 130, 26);
		contentPane.add(txtPrenom);
		
		JLabel lblStatut = new JLabel("Statut :");
		lblStatut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatut.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblStatut.setBounds(164, 218, 61, 16);
		contentPane.add(lblStatut);
		
		cmbBoxStatut = new JComboBox<>( dcbm);
		cmbBoxStatut.setBounds(237, 214, 174, 27);
		contentPane.add(cmbBoxStatut);
		
		JLabel lblTelephone = new JLabel("Téléphone :");
		lblTelephone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelephone.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblTelephone.setBounds(17, 303, 70, 16);
		contentPane.add(lblTelephone);
		
		txtTelephone = new JTextField();
		txtTelephone.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(95, 298, 130, 26);
		contentPane.add(txtTelephone);
		
		JLabel lblMail = new JLabel("Mail :");
		lblMail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMail.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblMail.setBounds(229, 303, 61, 16);
		contentPane.add(lblMail);
		
		txtMail = new JTextField();
		txtMail.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtMail.setColumns(10);
		txtMail.setBounds(292, 298, 299, 26);
		contentPane.add(txtMail);
		
		JButton btnCommentaireClient = new JButton("");
		btnCommentaireClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdcc = new JDialogCommentaireClient( ClientLogin);
				jdcc.setLocationRelativeTo( jdcc.getParent());
				jdcc.setVisible( true);
			}
		});
		btnCommentaireClient.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/btnPost.png"));
		btnCommentaireClient.setBounds(511, 416, 101, 76);
		contentPane.add(btnCommentaireClient);
		
		JButton btnValiderLesModifications = new JButton("Valider les modifications");
		btnValiderLesModifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client clt = new Client( txtLogin.getText(), txtNom.getText(), txtPrenom.getText(), txtMdp.getText(), txtMail.getText(), txtTelephone.getText(), (String) cmbBoxStatut.getSelectedItem());
				
				try {
					daoClt.modifierClient( clt);
					dispose();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnValiderLesModifications.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/double-checked.png"));
		btnValiderLesModifications.setBounds(290, 382, 190, 50);
		contentPane.add(btnValiderLesModifications);
		
		JButton btnAjouterUnClient = new JButton("    Ajouter un client");
		btnAjouterUnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client clt = new Client( txtLogin.getText(), txtNom.getText(), txtPrenom.getText(), txtMdp.getText(), txtMail.getText(), txtTelephone.getText(), (String) cmbBoxStatut.getSelectedItem());
				try {
					daoClt.ajouterClient( clt);
					String clientSel = txtLogin.getText();
					frameLigCde.refreshCltLogin( clientSel);
					frameLigCde.repaint();
					frameLigCde.setVisible( true);
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAjouterUnClient.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/plusContour.png"));
		btnAjouterUnClient.setBounds(71, 382, 190, 50);
		contentPane.add(btnAjouterUnClient);
		
		if ( etat.equals( "Consulter")) {
			txtLogin.setEnabled( false);
			txtMdp.setEnabled( false);
			txtNom.setEnabled( false);
			txtPrenom.setEnabled( false);
			txtTelephone.setEnabled( false);
			txtMail.setEnabled( false);
			cmbBoxStatut.setEnabled( false);
			btnValiderLesModifications.setEnabled( false);
			btnAjouterUnClient.setEnabled( false);
		}
		
		if ( etat.equals( "Modifier")) {
			txtLogin.setEnabled( false);
			txtMdp.setEnabled( false);
			btnAjouterUnClient.setEnabled( false);
		}
		
	}
}
