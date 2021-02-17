package guiLibrairie;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import daoLibrairie.daoAdresse;
import entitiesLibrairie.Adresse;

public class JFrameListeAdresse extends JFrame {

	private JPanel contentPane;
	private JTable tableAdr = new JTable();
	private daoAdresse daoAdr = new daoAdresse();
	private Container parent = this;
	private JFrameListeAdresse thisJF = (JFrameListeAdresse) parent;
	private JFrameAdresse JFad;
	private DefaultTableModel dtm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameListeAdresse frame = new JFrameListeAdresse( "", null, "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refreshAdresse( Adresse adr) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableAdr.setModel( dtm);
				tableAdr.repaint();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameListeAdresse( String clientLogin, JFrameLigneCommande frameLigCde, String etat) {
		
		setTitle("Liste des adresses");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 712, 386);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		panel.setBackground(new Color(255, 248, 220));
		panel.setBounds(18, 6, 680, 267);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 248, 220));
		scrollPane.setBounds(6, 6, 668, 255);
		panel.add(scrollPane);
		
		tableAdr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAdr.setBackground(new Color(255, 248, 220));
		tableAdr.setFillsViewportHeight(true);
		scrollPane.setViewportView( tableAdr);
		tableAdr.setShowGrid( true);
		tableAdr.setShowHorizontalLines( true);
		tableAdr.setShowVerticalLines( true);
		tableAdr.getTableHeader().setBounds(6, 6, 725, 299);
		tableAdr.getTableHeader().setVisible( true);
		try {
			if ( etat.equals( "Livraison")) {
				dtm = daoAdr.listeAdresseLivByLogin( clientLogin);
			}
			if ( etat.equals( "Facturation")) {
				dtm = daoAdr.listeAdresseFacByLogin( clientLogin);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		tableAdr.setModel( dtm);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String adrSel = "";
				int rowSel = tableAdr.getSelectedRow();
				try {
					if ( etat.equals( "Livraison")) {
						adrSel = (String) dtm.getValueAt( rowSel, 0);
					}
					if ( etat.equals( "Facturation")) {
						adrSel = (String) dtm.getValueAt( rowSel, 0);
					}
					JFad = new JFrameAdresse( clientLogin, thisJF, adrSel, etat);
					JFad.setLocationRelativeTo( null);
					JFad.setVisible( true);
					setVisible( false);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner une adresse à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		btnModifier.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnModifier.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnModifier.setBounds(211, 285, 118, 41);
		contentPane.add(btnModifier);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JFad = new JFrameAdresse( clientLogin, thisJF, "", etat);
					JFad.setLocationRelativeTo( null);
					JFad.setVisible( true);
					setVisible( false);
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner un client à consulter !", "Erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAjouter.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnAjouter.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnAjouter.setBounds(41, 285, 118, 41);
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
		btnAnnuler.setBounds(561, 285, 118, 41);
		contentPane.add(btnAnnuler);

		JButton btnSelectionner = new JButton("Selectionner");
		btnSelectionner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rowSel = 0;
				String adrSelect = "";
				Adresse adr = null;
				try {
					if ( etat.equals( "Livraison")) {
						rowSel = tableAdr.getSelectedRow();
						adrSelect = (String) dtm.getValueAt( rowSel, 0);
						adr = daoAdr.findAdresseLivById( adrSelect);
						frameLigCde.refreshAdresseLiv( adr);
					}
					if ( etat.equals( "Facturation")) {
						rowSel = tableAdr.getSelectedRow();
						adrSelect = (String) dtm.getValueAt( rowSel, 0);
						adr = daoAdr.findAdresseFacById( adrSelect);
						frameLigCde.refreshAdresseFact( adr);
					}
					frameLigCde.repaint();
					frameLigCde.setVisible( true);
					if ( tableAdr.getRowCount() > 0) {
						dispose();
					}
				} catch (ArrayIndexOutOfBoundsException aioobe) {
					JOptionPane.showMessageDialog(null, "Merci de sélectionner une adresse à modifier !", "Erreur", JOptionPane.WARNING_MESSAGE);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSelectionner.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		btnSelectionner.setBorder(BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		btnSelectionner.setBounds(382, 285, 118, 41);
		contentPane.add(btnSelectionner);
	}

}
