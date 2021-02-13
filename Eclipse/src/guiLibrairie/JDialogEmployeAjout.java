package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daoLibrairie.EmployeDAO;
import entitiesLibrairie.Employe;

import java.awt.Font;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JDialogEmployeAjout extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSaisieNom;
	private JTextField txtSaisiePrenom;
	private JTextField txtSaisiePoste;
	private JTextField txtSaisieLogin;
	private JTextField txtSaisieMDP;
	private JComboBox cmbBxAcces;
	private EmployeDAO employeDao = new EmployeDAO();
	private Employe employe = new Employe();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogEmployeAjout dialog = new JDialogEmployeAjout();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogEmployeAjout() {
		setBounds(100, 100, 850, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	
		
//JLABEL //////////////////////////////////////////////////////////////////////////////////////////////		
		
		JLabel lblTitre = new JLabel("AJOUT NOUVEL UTILISATEUR");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
		lblTitre.setForeground(Color.WHITE);
		lblTitre.setBounds(70, 61, 711, 37);
		contentPanel.add(lblTitre);
		
		
		JLabel lblSaisieNom = new JLabel("Saisir le nom");
		lblSaisieNom.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblSaisieNom.setForeground(Color.WHITE);
		lblSaisieNom.setBounds(158, 125, 160, 16);
		contentPanel.add(lblSaisieNom);
		
		JLabel lblSaisiePrenom = new JLabel("Saisir le prénom");
		lblSaisiePrenom.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblSaisiePrenom.setForeground(Color.WHITE);
		lblSaisiePrenom.setBounds(158, 180, 178, 16);
		contentPanel.add(lblSaisiePrenom);
		
		JLabel lblSaisiePoste = new JLabel("Saisir le poste occupé");
		lblSaisiePoste.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblSaisiePoste.setForeground(Color.WHITE);
		lblSaisiePoste.setBounds(158, 235, 178, 16);
		contentPanel.add(lblSaisiePoste);
		
		JLabel lblSaisieDroit = new JLabel("Sélectionner le droit d'accès");
		lblSaisieDroit.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblSaisieDroit.setForeground(Color.WHITE);
		lblSaisieDroit.setBounds(158, 290, 225, 16);
		contentPanel.add(lblSaisieDroit);
		
		JLabel lblSaisieLogin = new JLabel("Saisir un login");
		lblSaisieLogin.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblSaisieLogin.setForeground(Color.WHITE);
		lblSaisieLogin.setBounds(158, 345, 178, 16);
		contentPanel.add(lblSaisieLogin);
		
		JLabel lblSaisieMDP = new JLabel("Saisir un mot de passe");
		lblSaisieMDP.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		lblSaisieMDP.setForeground(Color.WHITE);
		lblSaisieMDP.setBounds(158, 400, 178, 16);
		contentPanel.add(lblSaisieMDP);
		
		
//JTEXTFIELDS //////////////////////////////////////////////////////////////////////////////////////////////		
		
		txtSaisieNom = new JTextField();
		txtSaisieNom.setBounds(395, 120, 300, 26);
		contentPanel.add(txtSaisieNom);
		txtSaisieNom.setColumns(10);
		
		txtSaisiePrenom = new JTextField();
		txtSaisiePrenom.setColumns(10);
		txtSaisiePrenom.setBounds(395, 175, 300, 26);
		contentPanel.add(txtSaisiePrenom);
		
		txtSaisiePoste = new JTextField();
		txtSaisiePoste.setColumns(10);
		txtSaisiePoste.setBounds(395, 230, 300, 26);
		contentPanel.add(txtSaisiePoste);
		
		txtSaisieLogin = new JTextField();
		txtSaisieLogin.setColumns(10);
		txtSaisieLogin.setBounds(395, 340, 300, 26);
		contentPanel.add(txtSaisieLogin);
		
		txtSaisieMDP = new JTextField();
		txtSaisieMDP.setColumns(10);
		txtSaisieMDP.setBounds(395, 395, 300, 26);
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
		cmbBxAcces.setBounds(395, 285, 300, 27);
		contentPanel.add(cmbBxAcces);

		
//JBUTTON //////////////////////////////////////////////////////////////////////////////////////////////		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 488, 800, 39);
			contentPanel.add(buttonPane);
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String acces = cmbBxAcces.getSelectedItem().toString();
						String nom = txtSaisieNom.getText().toString();
						String prenom = txtSaisiePrenom.getText().toString();
						String poste = txtSaisiePoste.getText().toString();
						String log = txtSaisieLogin.getText().toString();
						String mdp = txtSaisieMDP.getText().toString();
						if(!acces.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !poste.isEmpty() && !log.isEmpty() && !mdp.isEmpty()) {
							try {
									employeDao.ajouterEmploye(acces, nom, prenom, poste, log, mdp);
									JOptionPane.showMessageDialog(null, "Nouvel utilisateur ajouté avec succès", "Bravo !", JOptionPane.INFORMATION_MESSAGE);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								//e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "Oops, une erreur s'est produite", "Veuillez réessayer", JOptionPane.INFORMATION_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(new JDialogEmployeAjout(), "Oops, certains champs ne sont pas remplis...", "Saisir tous les champs", JOptionPane.INFORMATION_MESSAGE);
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
