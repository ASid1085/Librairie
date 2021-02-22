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

public class JDialogWelcome extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private EmployeDAO employeDAO = new EmployeDAO();
	private String login = "";
	private String mdp = "";
	private Employe employe;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogWelcome dialog = new JDialogWelcome ( null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogWelcome( Employe employe) {
		setTitle("Welcome");
		setBounds(400, 200, 357, 363);
		getContentPane().setLayout(new BorderLayout());

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(255, 248, 220));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);


		JButton cancelButton = new JButton("");
		cancelButton.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/cancel32px.png"));
		cancelButton.setBounds(153, 246, 58, 58);
		contentPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/welcome128px.png"));
		lblNewLabel.setBounds(113, 22, 128, 135);
		contentPanel.add(lblNewLabel);
		
		JLabel lblEmploye = new JLabel("");
		lblEmploye.setText( employe.getEmployeNom().toUpperCase() + " " + employe.getEmployePrenom());
		lblEmploye.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmploye.setForeground(new Color(128, 0, 0));
		lblEmploye.setFont(new Font("Avenir Next", Font.PLAIN, 30));
		lblEmploye.setBounds(6, 179, 341, 28);
		contentPanel.add(lblEmploye);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 248, 220));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

	}
}
