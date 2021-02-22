package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
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
import javax.swing.ImageIcon;

public class JDialogEmployeAjout extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtSaisieNom;
	private JTextField txtSaisiePrenom;
	private JTextField txtSaisiePoste;
	private JTextField txtSaisieLogin;
	private JTextField txtSaisieMDP;
	//private JComboBox cmbBxAcces;
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
		contentPanel.setBackground(new Color(255, 248, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	
		
//JLABEL //////////////////////////////////////////////////////////////////////////////////////////////		
		
		JLabel lblTitre = new JLabel("AJOUT NOUVEL.LE EMPLOYÉ.E");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
		lblTitre.setForeground(new Color(255, 215, 0));
		lblTitre.setBounds(70, 44, 711, 37);
		contentPanel.add(lblTitre);
		
		
		JLabel lblSaisieNom = new JLabel("Saisir le nom");
		lblSaisieNom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSaisieNom.setForeground(new Color(128, 0, 0));
		lblSaisieNom.setBounds(158, 125, 160, 16);
		contentPanel.add(lblSaisieNom);
		
		JLabel lblSaisiePrenom = new JLabel("Saisir le prénom");
		lblSaisiePrenom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSaisiePrenom.setForeground(new Color(128, 0, 0));
		lblSaisiePrenom.setBounds(158, 180, 178, 16);
		contentPanel.add(lblSaisiePrenom);
		
		JLabel lblSaisiePoste = new JLabel("Saisir le poste occupé");
		lblSaisiePoste.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSaisiePoste.setForeground(new Color(128, 0, 0));
		lblSaisiePoste.setBounds(158, 235, 178, 16);
		contentPanel.add(lblSaisiePoste);
		
		JLabel lblSaisieDroit = new JLabel("Sélectionner le droit d'accès");
		lblSaisieDroit.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSaisieDroit.setForeground(new Color(128, 0, 0));
		lblSaisieDroit.setBounds(158, 290, 225, 16);
		contentPanel.add(lblSaisieDroit);
		
		JLabel lblSaisieLogin = new JLabel("Saisir un login");
		lblSaisieLogin.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSaisieLogin.setForeground(new Color(128, 0, 0));
		lblSaisieLogin.setBounds(158, 345, 178, 16);
		contentPanel.add(lblSaisieLogin);
		
		JLabel lblSaisieMDP = new JLabel("Saisir un mot de passe");
		lblSaisieMDP.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSaisieMDP.setForeground(new Color(128, 0, 0));
		lblSaisieMDP.setBounds(158, 400, 178, 16);
		contentPanel.add(lblSaisieMDP);
		
		
//JTEXTFIELDS //////////////////////////////////////////////////////////////////////////////////////////////		
		
		txtSaisieNom = new JTextField();
		txtSaisieNom.setForeground(new Color(128, 0, 0));
		txtSaisieNom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieNom.setBounds(395, 120, 300, 26);
		contentPanel.add(txtSaisieNom);
		txtSaisieNom.setColumns(10);
		
		txtSaisiePrenom = new JTextField();
		txtSaisiePrenom.setForeground(new Color(128, 0, 0));
		txtSaisiePrenom.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisiePrenom.setColumns(10);
		txtSaisiePrenom.setBounds(395, 175, 300, 26);
		contentPanel.add(txtSaisiePrenom);
		
		txtSaisiePoste = new JTextField();
		txtSaisiePoste.setForeground(new Color(128, 0, 0));
		txtSaisiePoste.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisiePoste.setColumns(10);
		txtSaisiePoste.setBounds(395, 230, 300, 26);
		contentPanel.add(txtSaisiePoste);
		
		txtSaisieLogin = new JTextField();
		txtSaisieLogin.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		txtSaisieLogin.setForeground(new Color(128, 0, 0));
		txtSaisieLogin.setColumns(10);
		txtSaisieLogin.setBounds(395, 340, 300, 26);
		contentPanel.add(txtSaisieLogin);
		
		txtSaisieMDP = new JTextField();
		txtSaisieMDP.setForeground(new Color(128, 0, 0));
		txtSaisieMDP.setFont(new Font("Avenir Next", Font.PLAIN, 15));
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
		cmbBxAcces.setSelectedIndex(-1);
		cmbBxAcces.setForeground(new Color(128, 0, 0));
		cmbBxAcces.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		cmbBxAcces.setBounds(395, 285, 300, 27);
		contentPanel.add(cmbBxAcces);

		
		//JBOUTON //////////////////////////////////////////////////////////////////////////////////////////////			
		
		JButton okButton = new JButton("");
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
						JOptionPane.showMessageDialog(contentPanel, "Nouvel utilisateur ajouté avec succès", "Bravo !", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					} catch (SQLException e1) {
						//e1.printStackTrace();
						JOptionPane.showMessageDialog(contentPanel, "Oops, une erreur s'est produite", "Veuillez réessayer", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(contentPanel, "Oops, certains champs ne sont pas remplis...", "Saisir tous les champs", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		okButton.setIcon(new ImageIcon(JDialogEmployeAjout.class.getResource("/icon/double-checked32px.png")));
		okButton.setActionCommand("OK");
		okButton.setBounds(248, 479, 58, 58);
		contentPanel.add(okButton);
		
		JButton cancelButton = new JButton("");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setIcon(new ImageIcon(JDialogEmployeAjout.class.getResource("/icon/cancel32px.png")));
		cancelButton.setActionCommand("Cancel");
		cancelButton.setBounds(501, 479, 58, 58);
		contentPanel.add(cancelButton);
	}
}
