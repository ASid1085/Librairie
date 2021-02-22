package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import daoLibrairie.EmployeDAO;
import entitiesLibrairie.Employe;

public class JDialogEmployeAffichage extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JTextField txtSaisieNom;
	private JTextField txtSaisiePrenom;
	private JTextField txtSaisiePoste;
	private JTextField txtSaisieLogin;
	private JTextField txtSaisieMDP;
	private JComboBox cmbBxAcces;
	private EmployeDAO employeDao = new EmployeDAO();
	private Employe employe = new Employe();
	private static Employe empl;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogEmployeAffichage dialog = new JDialogEmployeAffichage(empl);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogEmployeAffichage(Employe employeATraiter) {
		
		this.employe = employeATraiter;
		
		setBounds(100, 100, 850, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 248, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	
		
//JLABEL //////////////////////////////////////////////////////////////////////////////////////////////		
		
		JLabel lblTitre = new JLabel("AFFICHAGE EMPLOYÉ.E");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
		lblTitre.setForeground(new Color(255, 215, 0));
		lblTitre.setBounds(70, 44, 711, 37);
		contentPanel.add(lblTitre);
		
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNom.setForeground(new Color(128, 0, 0));
		lblNom.setBounds(158, 125, 160, 16);
		contentPanel.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblPrenom.setForeground(new Color(128, 0, 0));
		lblPrenom.setBounds(158, 180, 178, 16);
		contentPanel.add(lblPrenom);
		
		JLabel lblPoste = new JLabel("Poste occupé");
		lblPoste.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblPoste.setForeground(new Color(128, 0, 0));
		lblPoste.setBounds(158, 235, 178, 16);
		contentPanel.add(lblPoste);
		
		JLabel lblDroit = new JLabel("Droit d'accès");
		lblDroit.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblDroit.setForeground(new Color(128, 0, 0));
		lblDroit.setBounds(158, 290, 225, 16);
		contentPanel.add(lblDroit);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblLogin.setForeground(new Color(128, 0, 0));
		lblLogin.setBounds(158, 345, 178, 16);
		contentPanel.add(lblLogin);
		
		JLabel lblMDP = new JLabel("Mot de passe");
		lblMDP.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblMDP.setForeground(new Color(128, 0, 0));
		lblMDP.setBounds(158, 400, 178, 16);
		contentPanel.add(lblMDP);
		
		
//JTEXTFIELDS //////////////////////////////////////////////////////////////////////////////////////////////		
		
		txtSaisieNom = new JTextField();
		txtSaisieNom.setForeground(new Color(128, 0, 0));
		txtSaisieNom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieNom.setBounds(395, 120, 300, 26);
		contentPanel.add(txtSaisieNom);
		txtSaisieNom.setColumns(10);
		txtSaisieNom.setText(employe.getEmployeNom());
		txtSaisieNom.setEnabled(false);
		
		txtSaisiePrenom = new JTextField();
		txtSaisiePrenom.setForeground(new Color(128, 0, 0));
		txtSaisiePrenom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisiePrenom.setColumns(10);
		txtSaisiePrenom.setBounds(395, 175, 300, 26);
		txtSaisiePrenom.setText(employe.getEmployePrenom());
		txtSaisiePrenom.setEnabled(false);
		contentPanel.add(txtSaisiePrenom);
		
		txtSaisiePoste = new JTextField();
		txtSaisiePoste.setForeground(new Color(128, 0, 0));
		txtSaisiePoste.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisiePoste.setColumns(10);
		txtSaisiePoste.setBounds(395, 230, 300, 26);
		txtSaisiePoste.setText(employe.getEmployePoste());
		txtSaisiePoste.setEnabled(false);
		contentPanel.add(txtSaisiePoste);
		
		txtSaisieLogin = new JTextField();
		txtSaisieLogin.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieLogin.setForeground(new Color(128, 0, 0));
		txtSaisieLogin.setColumns(10);
		txtSaisieLogin.setBounds(395, 340, 300, 26);
		txtSaisieLogin.setText(employe.getEmployeLog());
		txtSaisieLogin.setEnabled(false);
		contentPanel.add(txtSaisieLogin);
		
		txtSaisieMDP = new JPasswordField();
		txtSaisieMDP.setForeground(new Color(128, 0, 0));
		txtSaisieMDP.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieMDP.setColumns(10);
		txtSaisieMDP.setBounds(395, 395, 300, 26);
		txtSaisieMDP.setText(employe.getEmployeMdp());
		txtSaisieMDP.setEnabled(false);
		contentPanel.add(txtSaisieMDP);
		

		
		
//JCOMBOBOX //////////////////////////////////////////////////////////////////////////////////////////////				

		
		Vector<String> cmbBxAccesModel = new Vector();
		try {
			cmbBxAccesModel= employeDao.recupererDroitsAcces();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JComboBox cmbBxAcces = new JComboBox(cmbBxAccesModel);
		cmbBxAcces.setSelectedIndex(-1);
		cmbBxAcces.setForeground(new Color(128, 0, 0));
		cmbBxAcces.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		cmbBxAcces.setBounds(395, 285, 300, 27);
		if (employe.getDroitsAcces().equals("Administrateur")) {
			cmbBxAcces.setSelectedIndex(0);
		} else if (employe.getDroitsAcces().equals("Responsable")) {
			cmbBxAcces.setSelectedIndex(1);
		} else if (employe.getDroitsAcces().equals("Employé")) {
			cmbBxAcces.setSelectedIndex(2);
		} else if (employe.getDroitsAcces().equals("Stagiaire")) {
			cmbBxAcces.setSelectedIndex(3);
		}
		cmbBxAcces.setEnabled(false);
		
		
		contentPanel.add(cmbBxAcces);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(683, 487, 71, 37);
			contentPanel.add(okButton);
			okButton.setForeground(new Color(128, 0, 0));
			okButton.setFont(new Font("Avenir Next", Font.PLAIN, 15));
			okButton.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			okButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom = txtSaisieNom.getText();
				String prenom = txtSaisiePrenom.getText();
				String poste = txtSaisiePoste.getText();
				String acces = cmbBxAcces.getSelectedItem().toString();
				String login = txtSaisieLogin.getText();
				String mdp = txtSaisieMDP.getText();
				
				employe = new Employe(acces, nom, prenom, poste, login, mdp);
				
				try {
					employeDao.modifierEmployer(employe, login);
					JOptionPane.showMessageDialog(null, employe.toString(), "Vous avez modifié le profil de : ", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnValider.setForeground(new Color(128, 0, 0));
		btnValider.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnValider.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnValider.setActionCommand("OK");
		btnValider.setBounds(247, 487, 71, 37);
		btnValider.setEnabled(false);
		contentPanel.add(btnValider);
		
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtSaisieNom.setEnabled(true);
				txtSaisiePrenom.setEnabled(true);
				txtSaisiePoste.setEnabled(true);
				cmbBxAcces.setEnabled(true);
				btnValider.setEnabled(true);
				
			}
		});
		btnModifier.setForeground(new Color(128, 0, 0));
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.setActionCommand("OK");
		btnModifier.setBounds(158, 488, 71, 37);
		contentPanel.add(btnModifier);
		
		
	}

}
