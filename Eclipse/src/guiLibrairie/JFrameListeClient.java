package guiLibrairie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daoLibrairie.daoClient;
import entitiesLibrairie.Client;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;

public class JFrameListeClient extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomClient;
	private daoClient daoClt = new daoClient();
	//private DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel<>();
	private Vector<Client> vLog = new Vector<>();
	private JTable table;
	private DefaultTableModel dtm;
	private static JFrameClient JFcl;
	private static JFrameListeCommande JFlcde;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameListeClient frame = new JFrameListeClient( null);
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
	public JFrameListeClient( JFrameLigneCommande frameLigCde) {
		setTitle("Liste des clients");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 779, 544);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRechercherPar = new JLabel("Rechercher par");
		lblRechercherPar.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblRechercherPar.setBounds(18, 18, 142, 33);
		contentPane.add(lblRechercherPar);
		
		JLabel lblLogin = new JLabel("Login client :");
		lblLogin.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblLogin.setBounds(47, 61, 82, 16);
		contentPane.add(lblLogin);
		
		
		try {
			vLog = daoClt.vectorCBLoginClient(); 
			//dcbm = daoClt.ClientLogin();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JComboBox cmbBxLoginClient = new JComboBox( vLog);
		cmbBxLoginClient.setSelectedIndex(-1);
		cmbBxLoginClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		cmbBxLoginClient.setBounds(127, 57, 180, 27);
		contentPane.add(cmbBxLoginClient);
		
		JLabel lblNomClient = new JLabel("Nom client :");
		lblNomClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblNomClient.setBounds(354, 61, 82, 16);
		contentPane.add(lblNomClient);
		
		txtNomClient = new JTextField();
		txtNomClient.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		txtNomClient.setBounds(430, 56, 162, 26);
		contentPane.add(txtNomClient);
		txtNomClient.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(18, 118, 737, 311);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 248, 220));
		scrollPane.setBounds(6, 6, 725, 299);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(new Color(255, 248, 220));
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView( table);
		table.setShowGrid( true);
		table.setShowHorizontalLines( true);
		table.setShowVerticalLines( true);
		table.getTableHeader().setBounds(6, 6, 725, 299);
		table.getTableHeader().setVisible( true);
		try {
			dtm = daoClt.listeClient();
			table.setModel( dtm);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		JButton btnLoupe = new JButton("");
		btnLoupe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String clientLogSelect = "";
				try {
					if ( txtNomClient.getText().equals( "") ) {	
						clientLogSelect = (String) cmbBxLoginClient.getSelectedItem();
						table.setModel( daoClt.listeClientByLogin( clientLogSelect));
						table.repaint();
					} else {
						clientLogSelect = txtNomClient.getText();
						table.setModel( daoClt.listeClientByNom( clientLogSelect));
						table.repaint();
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnLoupe.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/BtnLoupe.png"));
		btnLoupe.setBounds(615, 40, 55, 55);
		contentPane.add(btnLoupe);
		
		JButton btnCdeLiee = new JButton("Commande liée");
		btnCdeLiee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = "";
				String nom = txtNomClient.getText();
				String clientLogSelect = "";
				try {
					if ( nom.equals( "") && login.equals( "")) {
						clientLogSelect = (String) daoClt.listeClient().getValueAt( table.getSelectedRow(), 0);
System.out.println( "récupération du login via la selection de ligne : " + clientLogSelect);
						JFlcde = new JFrameListeCommande( clientLogSelect);
	 				}
					if( !nom.equals( "")) {
						clientLogSelect = (String) daoClt.listeClientByNom( nom).getValueAt( table.getSelectedRow(), 0);
System.out.println( "récupération du login via la recherche par NOM : " + clientLogSelect);
						JFlcde = new JFrameListeCommande( clientLogSelect);
					}
					if ( clientLogSelect.equals( login)) {
						login = (String) cmbBxLoginClient.getSelectedItem();
						clientLogSelect = (String) daoClt.listeClientByLogin( login).getValueAt( table.getSelectedRow(), 0);
System.out.println( "récupération du login via la recherche par LOGIN : " + clientLogSelect);
						JFlcde = new JFrameListeCommande( clientLogSelect);
					} 
					JFlcde.setLocationRelativeTo( null);
					JFlcde.setVisible( true);
					cmbBxLoginClient.setSelectedIndex( -1);
					txtNomClient.setText( "");
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un client !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnCdeLiee.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnCdeLiee.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnCdeLiee.setBounds(234, 451, 124, 41);
		contentPane.add(btnCdeLiee);
		
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String login = "";
				String nom = txtNomClient.getText();
				String clientLogSelect = "";
				try {
					if ( nom.equals( "") && login.equals( "")) {
						clientLogSelect = (String) daoClt.listeClient().getValueAt( table.getSelectedRow(), 0);
						System.out.println( "récupération du login via la selection de ligne : " + clientLogSelect);
						JFcl = new JFrameClient( clientLogSelect, null, "Modifier");
					}
					if( !nom.equals( "")) {
						clientLogSelect = (String) daoClt.listeClientByNom( nom).getValueAt( table.getSelectedRow(), 0);
						System.out.println( "récupération du login via la recherche par NOM : " + clientLogSelect);
						JFcl = new JFrameClient( clientLogSelect, null, "Modifier");
					}
					if ( clientLogSelect.equals( login)) {
						login = (String) cmbBxLoginClient.getSelectedItem();
						clientLogSelect = (String) daoClt.listeClientByLogin( login).getValueAt( table.getSelectedRow(), 0);
						System.out.println( "récupération du login via la recherche par LOGIN : " + clientLogSelect);
						JFcl = new JFrameClient( clientLogSelect, null, "Modifier");
					}
					JFcl.setLocationRelativeTo( null);
					JFcl.setVisible( true);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un client à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.setBounds(591, 452, 124, 41);
		contentPane.add(btnModifier);

		JButton btnConsulter = new JButton("Consulter");
		btnConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String login = "";
				String nom = txtNomClient.getText();
				String clientLogSelect = "";
				try {
					if ( nom.equals( "") && login.equals( "")) {
						clientLogSelect = (String) daoClt.listeClient().getValueAt( table.getSelectedRow(), 0);
						System.out.println( "récupération du login via la selection de ligne : " + clientLogSelect);
						JFcl = new JFrameClient( clientLogSelect, null, "Consulter");
					}
					if( !nom.equals( "")) {
						clientLogSelect = (String) daoClt.listeClientByNom( nom).getValueAt( table.getSelectedRow(), 0);
						System.out.println( "récupération du login via la recherche par NOM : " + clientLogSelect);
						JFcl = new JFrameClient( clientLogSelect, null, "Consulter");
					}
					if ( clientLogSelect.equals( login)) {
						login = (String) cmbBxLoginClient.getSelectedItem();
						clientLogSelect = (String) daoClt.listeClientByLogin( login).getValueAt( table.getSelectedRow(), 0);
						System.out.println( "récupération du login via la recherche par LOGIN : " + clientLogSelect);
						JFcl = new JFrameClient( clientLogSelect, null, "Consulter");
					}
					JFcl.setLocationRelativeTo( null);
					JFcl.setVisible( true);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un client à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnConsulter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnConsulter.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnConsulter.setBounds(412, 452, 124, 41);
		contentPane.add(btnConsulter);
		
		JButton btnSelectionner = new JButton("Sélectionner");
		btnSelectionner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowSel = table.getSelectedRow();
				String clientSel = (String) dtm.getValueAt( rowSel, 0);
				frameLigCde.refreshCltLogin( clientSel);
				frameLigCde.repaint();
				frameLigCde.setVisible( true);
			}
		});
		btnSelectionner.setVisible( false);
		btnSelectionner.setEnabled( false);
		btnSelectionner.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSelectionner.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSelectionner.setBounds(50, 451, 124, 41);
		contentPane.add(btnSelectionner);
		
		JButton btnRefreshAuteur = new JButton("");
		btnRefreshAuteur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dtm = daoClt.listeClient();
					table.setModel( dtm);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				cmbBxLoginClient.setSelectedIndex( -1);
				txtNomClient.setText( "");
			}
		});
		btnRefreshAuteur.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/refresh24px.png"));
		btnRefreshAuteur.setBounds(687, 40, 55, 55);
		contentPane.add(btnRefreshAuteur);
	}
}
