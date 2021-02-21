package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connexionLibrairie.Connexion;
import daoLibrairie.EmployeDAO;
import entitiesLibrairie.Employe;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class JDialogLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSaisieLogin;
	private JPasswordField pswdSaisieMDP;
	private EmployeDAO employeDAO = new EmployeDAO();
	private String login = "";
	private String mdp = "";
	private Employe employe;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogLogin dialog = new JDialogLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogLogin() {
		setTitle("Identification");
		setBounds(400, 200, 561, 360);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(255, 248, 220));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtSaisieLogin = new JTextField();
		txtSaisieLogin.setBounds(50, 93, 470, 34);
		contentPanel.add(txtSaisieLogin);
		txtSaisieLogin.setColumns(10);

		pswdSaisieMDP = new JPasswordField();
		pswdSaisieMDP.setBounds(50, 173, 470, 34);
		contentPanel.add(pswdSaisieMDP);

		JLabel lblSaisieLogin = new JLabel("Identifiant");
		lblSaisieLogin.setForeground(new Color(128, 0, 0));
		lblSaisieLogin.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSaisieLogin.setBounds(50, 77, 454, 16);
		contentPanel.add(lblSaisieLogin);

		JLabel lblSaisieMDP = new JLabel("Mot de passe");
		lblSaisieMDP.setForeground(new Color(128, 0, 0));
		lblSaisieMDP.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSaisieMDP.setBounds(50, 156, 454, 16);
		contentPanel.add(lblSaisieMDP);

		JLabel lblTitre = new JLabel("CONNEXION");
		lblTitre.setForeground(new Color(255, 215, 0));
		lblTitre.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setBounds(50, 20, 470, 28);
		contentPanel.add(lblTitre);
		
		
		JButton okButton = new JButton("");
		okButton.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/double-checked32px.png"));
		okButton.setBounds(152, 240, 58, 58);
		contentPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean i = true;
				login = txtSaisieLogin.getText().toString();
				mdp = pswdSaisieMDP.getText().toString();
				try {
					employe = employeDAO.authentification(login, mdp);


					if ( !employe.getEmployeLog().equals(login) || !employe.getEmployeMdp().equals(mdp)) {
						System.out.println("hel1");
						i = false;
						System.out.println( i);
					}  
					/*
					else if (!employe.getEmployeLog().equals(login) && !employe.getEmployeMdp().equals(mdp)) {
						System.out.println("hel2");
						i=1;
					}  else if (!employe.getEmployeLog().equals(login) && employe.getEmployeMdp().equals(mdp)) {
						System.out.println("hel3");
						i=1;
					}*/

					if ( i) {
						
						JDialogWelcome jdWelcome = new JDialogWelcome( employe);
						dispose();
						jdWelcome.setModal( true);
						jdWelcome.setVisible( true);
						JFrameAccueil jfAccueil = new JFrameAccueil( employe);
						jfAccueil.setVisible(true);
						
					} 

				} catch (NullPointerException npe){
					JOptionPane.showMessageDialog(null, "Ooops - le mot de passe et/ou l'identifiant est incorrect", "Erreur saisie", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);


		JButton cancelButton = new JButton("");
		cancelButton.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/cancel32px.png"));
		cancelButton.setBounds(333, 240, 58, 58);
		contentPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connexion.closeInstance();
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 248, 220));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

	}
}
