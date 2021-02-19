package guiLibrairie;

import java.awt.*;
import javax.swing.border.EmptyBorder;

import daoLibrairie.daoClient;
import entitiesLibrairie.Genre;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JDialogCommentaireClient extends JDialog {

	private JTextArea textAreaPostIt;
	private final JPanel contentPanel = new JPanel();
	private daoClient daoClt = new daoClient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDialogCommentaireClient dialog = new JDialogCommentaireClient( "");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDialogCommentaireClient(String clientLogin) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String recup = "";
				try {
					recup = daoClt.recupererNoteClient( clientLogin);
					textAreaPostIt.setText( recup);
				} catch (SQLException ex) {
					System.err.println( "Oops : Erreur avec la récupération du post'it client");
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
					note = daoClt.recupererNoteClient( clientLogin);
					daoClt.modifierNoteClient( clientLogin, note);
				} catch (SQLException ex) {
					System.err.println( "Oops : Erreur avec l'ajout/modification du post'it client.");
					ex.printStackTrace();
				}
			}
		});
		contentPanel.setLayout(null);
		btnValider.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/double-checked.png"));
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
		btnSortir.setIcon(new ImageIcon("/Users/a.sid/Documents/gitHub/Librairie/Eclipse/icon/cancel.png"));
		contentPanel.add(btnSortir);
	}
}
