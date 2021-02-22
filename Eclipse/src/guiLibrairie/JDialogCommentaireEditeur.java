package guiLibrairie;

import java.awt.*;
import javax.swing.border.EmptyBorder;

import daoLibrairie.daoClient;
import daoLibrairie.daoEditeur;
import entitiesLibrairie.Genre;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JDialogCommentaireEditeur extends JDialog {

	private JTextArea textAreaPostIt;
	private final JPanel contentPanel = new JPanel();
	private daoEditeur daoEd = new daoEditeur();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogCommentaireEditeur dialog = new JDialogCommentaireEditeur( "");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogCommentaireEditeur(String editeurNom) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String recup = "";
				try {
					recup = daoEd.recupererNoteEditeur( editeurNom);
					textAreaPostIt.setText( recup);
				} catch (SQLException ex) {
					System.err.println( "Oops : Erreur avec la récupération du post'it éditeur");
					ex.printStackTrace();
				}			
			}
		});

		setTitle("Notes sur le client");
		setBounds(100, 100, 400, 322);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 248, 220));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder( BorderFactory.createMatteBorder(3, 0, 3, 0, Color.ORANGE));
		scrollPane.setBackground(new Color(255, 248, 220));
		scrollPane.setBounds(16, 17, 367, 206);
		contentPanel.add(scrollPane);
		
		textAreaPostIt = new JTextArea();
		scrollPane.setViewportView(textAreaPostIt);
		JButton btnValider = new JButton("");
		btnValider.setBounds(275, 235, 48, 50);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String note = "";
				try {
					note = textAreaPostIt.getText();
					daoEd.modifierNoteEditeur( editeurNom, note);
					JOptionPane.showMessageDialog( contentPanel, "Le commentaire concernant le client \na bien été enregistré !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog( contentPanel, "Ne pas mettre d'apostrophe dans votre commantaire ! ", "Erreur", JOptionPane.WARNING_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		contentPanel.setLayout(null);
		btnValider.setIcon(new ImageIcon(JDialogCommentaireEditeur.class.getResource("/icon/double-checked.png")));
		contentPanel.add(btnValider);
		getRootPane().setDefaultButton(btnValider);

		JButton btnSortir = new JButton("");
		btnSortir.setBounds(335, 235, 48, 50);
		btnSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible( false);
				dispose();
			}
		});
		btnSortir.setIcon(new ImageIcon(JDialogCommentaireEditeur.class.getResource("/icon/cancel.png")));
		contentPanel.add(btnSortir);
	}
}
