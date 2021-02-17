package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JDialogLogin extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSaisieLogin;
	private JPasswordField pswdSaisieMDP;
	private EmployeDAO employeDAO = new EmployeDAO();
	private String id;
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
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
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
			lblSaisieLogin.setForeground(Color.WHITE);
			lblSaisieLogin.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			lblSaisieLogin.setBounds(103, 117, 454, 16);
			contentPanel.add(lblSaisieLogin);
		}
		{
			JLabel lblSaisieMDP = new JLabel("Mot de passe");
			lblSaisieMDP.setForeground(Color.WHITE);
			lblSaisieMDP.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			lblSaisieMDP.setBounds(103, 224, 454, 16);
			contentPanel.add(lblSaisieMDP);
		}
		{
			JLabel lblTitre = new JLabel("CONNEXION");
			lblTitre.setForeground(Color.LIGHT_GRAY);
			lblTitre.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
			lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitre.setBounds(95, 20, 470, 28);
			contentPanel.add(lblTitre);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						id = txtSaisieLogin.getText().toString();
						mdp = pswdSaisieMDP.getText().toString();
						String acces = "";
						try {
							employe = employeDAO.authentification(id, mdp);

							if (employe != null) {
								String droits = employe.getDroitsAcces();

								//if (droits.equals("Administrateur")) {
									JFrameEmploye jframeEmploye = new JFrameEmploye(employe);
									System.out.println(employe);
									jframeEmploye.setVisible(true);
								//}
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				okButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
