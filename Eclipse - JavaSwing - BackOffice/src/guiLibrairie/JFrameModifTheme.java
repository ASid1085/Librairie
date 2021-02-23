package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import daoLibrairie.ThemeDAO;
import entitiesLibrairie.Theme;

public class JFrameModifTheme extends JFrame {

	private JTextField textField;
	private JPanel contentPane;
	private Theme theme = new Theme();
	private ThemeDAO themeDAO = new ThemeDAO();
	private static Theme themetheme = new Theme();
	private JTextField txtThemeId;
	private JTextField txtThemeLib;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameModifTheme frame = new JFrameModifTheme(themetheme);
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
	public JFrameModifTheme(Theme themeALier) {
		
		this.theme = themeALier;
		
		
		getContentPane().setBackground(new Color(255, 248, 220));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thème à ajouter :");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-76, 48, 311, 23);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(148, 47, 280, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		setTitle("Thème");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 439, 369);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblThemeId = new JLabel("Thème Id : ");
		lblThemeId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThemeId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblThemeId.setBounds(33, 60, 83, 16);
		contentPane.add(lblThemeId);
		
		
		
		JLabel lblThemeLib = new JLabel("Thème Libellé :");
		lblThemeLib.setHorizontalAlignment(SwingConstants.RIGHT);
		lblThemeLib.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblThemeLib.setBounds(19, 170, 97, 16);
		contentPane.add(lblThemeLib);
		
		txtThemeLib = new JTextField();
		txtThemeLib.setBounds(150, 165, 189, 26);
		txtThemeLib.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		contentPane.add(txtThemeLib);
		txtThemeLib.setColumns(10);
		txtThemeLib.setText(theme.getThemeNom());
		
		
			
		txtThemeId = new JTextField();
		txtThemeId.setBounds(150, 55, 189, 26);
		txtThemeId.setEnabled( false);
		txtThemeId.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtThemeId.setText(theme.getThemeId());
		txtThemeId.setEnabled(false);
		contentPane.add(txtThemeId);
		txtThemeId.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = txtThemeLib.getText();
				String id = txtThemeId.getText();
				try {
					themeDAO.modifierTheme(id, nom);
					JOptionPane.showMessageDialog(contentPane, "Thème modifié avec succès", "Modification thème", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		btnValider.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnValider.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnValider.setBounds(69, 256, 97, 41);
		contentPane.add(btnValider);
		
		/*JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAnnuler.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAnnuler.setBounds(69, 256, 97, 41);
		btnAnnuler.setBounds(258, 257, 97, 41);
		contentPane.add(btnAnnuler);*/
	}

}
