package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class JDialogLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSaisieLogin;
	private JPasswordField pswdSaisieMDP;
	private EmployeDAO employeDAO = new EmployeDAO();
	private String login;
	private String mdp;
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
		setBounds(400, 200, 650, 450);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(255, 248, 220));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtSaisieLogin = new JTextField();
			txtSaisieLogin.setBounds(95, 135, 470, 34);
			contentPanel.add(txtSaisieLogin);
			txtSaisieLogin.setColumns(10);
		}
		{
			pswdSaisieMDP = new JPasswordField();
			pswdSaisieMDP.setBounds(95, 242, 470, 34);
			contentPanel.add(pswdSaisieMDP);
		}
		{
			JLabel lblSaisieLogin = new JLabel("Identifiant");
			lblSaisieLogin.setForeground(new Color(128, 0, 0));
			lblSaisieLogin.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			lblSaisieLogin.setBounds(103, 117, 454, 16);
			contentPanel.add(lblSaisieLogin);
		}
		{
			JLabel lblSaisieMDP = new JLabel("Mot de passe");
			lblSaisieMDP.setForeground(new Color(128, 0, 0));
			lblSaisieMDP.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			lblSaisieMDP.setBounds(103, 224, 454, 16);
			contentPanel.add(lblSaisieMDP);
		}
		{
			JLabel lblTitre = new JLabel("CONNEXION");
			lblTitre.setForeground(new Color(255, 215, 0));
			lblTitre.setFont(new Font("Avenir Next", Font.PLAIN, 30));
			lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitre.setBounds(95, 20, 470, 28);
			contentPanel.add(lblTitre);
		}
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(373, 341, 84, 40);
			contentPanel.add(okButton);
			okButton.setForeground(new Color(128, 0, 0));
			okButton.setFont(new Font("Avenir Next", Font.PLAIN, 30));
			okButton.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i = 0;
					login = txtSaisieLogin.getText().toString();
					mdp = pswdSaisieMDP.getText().toString();
					String acces = "";
					try {
						employe = employeDAO.authentification(login, mdp);
							System.out.println(employe);
							if (employe.getEmployeLog().equals(login) && !employe.getEmployeMdp().equals(mdp)) {
								System.out.println("hel");
								i=1;
							}  else if (!employe.getEmployeLog().equals(login) && !employe.getEmployeMdp().equals(mdp)) {
								System.out.println("hel");
								i=1;
							}  else if (!employe.getEmployeLog().equals(login) && employe.getEmployeMdp().equals(mdp)) {
								System.out.println("hel");
								i=1;
							}

						if (i==0) {
							String droits = employe.getDroitsAcces();
							JFrameAccueil jfAccueil = new JFrameAccueil(employe);
							jfAccueil.setVisible(true);
							dispose();
							
						} else {
							JOptionPane.showMessageDialog(null, "OOps - le mot de passe et/ou l'identifiant est incorrect", "Erreur saisie", JOptionPane.INFORMATION_MESSAGE);
						}
						
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "OOps - le mot de passe et/ou l'identifiant est incorrect", "Erreur saisie", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			okButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(481, 341, 84, 40);
			contentPanel.add(cancelButton);
			cancelButton.setForeground(new Color(128, 0, 0));
			cancelButton.setFont(new Font("Avenir Next", Font.PLAIN, 30));
			cancelButton.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			cancelButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			cancelButton.setActionCommand("Cancel");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 248, 220));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
