package guiLibrairie;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

import daoLibrairie.daoAdresse;
import entitiesLibrairie.Adresse;

public class JFrameListeAdresse extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private daoAdresse daoAdr = new daoAdresse();
	private static JFrameLigneCommande JFlc = new JFrameLigneCommande();
	private JFrameAdresse JFad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameListeAdresse frame = new JFrameListeAdresse( "");
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
	public JFrameListeAdresse( String clientLogin) {

		setTitle("Liste des adresses");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 741, 386);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(18, 6, 706, 267);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 248, 220));
		scrollPane.setBounds(6, 6, 695, 255);
		panel.add(scrollPane);
		
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
			table.setModel( daoAdr.listeAdresseByLogin( clientLogin));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String adresseSelect = "";
				try {
					adresseSelect = (String) daoAdr.listeAdresseByLogin( clientLogin).getValueAt( table.getSelectedRow(), 0);
					JFad = new JFrameAdresse( adresseSelect);
					JFad.setLocationRelativeTo( JFad.getParent());
					JFad.setVisible( true);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner une adresse à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.setBounds(164, 285, 118, 41);
		contentPane.add(btnModifier);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFad = new JFrameAdresse( "");
					JFad.setLocationRelativeTo( JFad.getParent());
					JFad.setVisible( true);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un client à consulter !", "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAjouter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAjouter.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAjouter.setBounds(18, 285, 118, 41);
		contentPane.add(btnAjouter);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible( false);
				dispose();
			}
		});
		btnAnnuler.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAnnuler.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAnnuler.setBounds(606, 285, 118, 41);
		contentPane.add(btnAnnuler);
		
		JButton btnAddAdFact = new JButton("Adr. Fact");
		btnAddAdFact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String adresseSelect;
				try {
					adresseSelect = (String) daoAdr.listeAdresseByLogin( clientLogin).getValueAt( table.getSelectedRow(), 0);
						Adresse adr = daoAdr.findAdresseById( adresseSelect);
						JFlc.refreshAdresseFact( adr);
						//JFlc.setVisible( true);
						JFlc.repaint();
						JOptionPane.showMessageDialog(null, "Merci de choisir l'adresse de livraison !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner une adresse à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddAdFact.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAddAdFact.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAddAdFact.setBounds(312, 285, 118, 41);
		contentPane.add(btnAddAdFact);
		
		JButton btnAddAdLiv = new JButton("Adr. Liv");
		btnAddAdLiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String adresseSelect;
				try {
					adresseSelect = (String) daoAdr.listeAdresseByLogin( clientLogin).getValueAt( table.getSelectedRow(), 0);
						Adresse adr = daoAdr.findAdresseById( adresseSelect);
						JFlc.refreshAdresseLiv( adr);
						JFlc.setVisible( true);
						JFlc.repaint();
						if ( table.getRowCount() > 0) {
							dispose();
						}
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner une adresse à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAddAdLiv.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAddAdLiv.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAddAdLiv.setBounds(461, 285, 118, 41);
		contentPane.add(btnAddAdLiv);
	}

}
