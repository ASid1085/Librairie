package daoLibrairie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Auteur;
import entitiesLibrairie.Genre;
import interfaceDaoLibrairie.iDaoAuteur;

public class daoAuteur implements iDaoAuteur{
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;
	
	public String ajoutIdAuteur() {
		String id = null;

		myConnexion = Connexion.getInstance();

		String query = "select count(*) from AUTEUR;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				int numAuteur = rs.getInt( 1) +1 ;
				if (numAuteur < 10) {
					id = "0000" + numAuteur + "AUT";
				} else if (numAuteur < 100) {
					id = "000" + numAuteur + "AUT";
				} else if (numAuteur < 1000) {
					id = "00" + numAuteur + "AUT";
				} else if (numAuteur < 10000) {
					id = "0" + numAuteur + "AUT";
				} if (numAuteur > 99999) {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez plus ajouter de nouvel auteur !", "Message d'erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void ajouterAuteur(Auteur auteur) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "insert into AUTEUR values ('" + auteur.getAuteurId() + "', '" + auteur.getAuteurNom()
													  + auteur.getAuteurPrenom() + auteur.getAuteurPseudo() + "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();

		pstmt.close();
		JOptionPane.showMessageDialog(null, "Le nouvel auteur a été ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

	}

	@Override
	public void modifierAuteur(Auteur auteur, String auteurId) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "update AUTEUR set AUTEURID = '" + auteur.getAuteurId() + "', AUTEURNOM = '" + auteur.getAuteurNom()
							+ "', AUTEURPRENOM = '" + auteur.getAuteurPrenom() + "', AUTEURPSEUDO = '" + auteur.getAuteurPseudo()
							+"' where AUTEURID = '" + auteur.getAuteurId() +"';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "La modification de l'auteur a bien été effectuée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public Vector<Auteur> vectorListAuteur() throws SQLException {
		Vector vAuteur = new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select * from AUTEUR order by AUTEURNOM;";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "AUTEURID"));
				colonne.add( rs.getString( "AUTEURNOM"));
				colonne.add( rs.getString( "AUTEURPRENOM"));
				colonne.add( rs.getString( "AUTEURPSEUDO"));

				vAuteur.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vAuteur;
	}

	@Override
	public DefaultTableModel listeAuteur() throws SQLException {
		
		Vector vAuteur = vectorListAuteur();
		Vector nomColonne = new Vector<>();
		nomColonne.add( "ID de l'auteur");
		nomColonne.add( "Nom");
		nomColonne.add( "Prénom");
		nomColonne.add( "Pseudo");
		
		return new DefaultTableModel( vAuteur, nomColonne);
	}
	
	public Vector<Auteur> vectorListAuteurBtName( String auteurNom) throws SQLException {
		Vector vAuteur = new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select * from AUTEUR where AUTEURNOM = '" + auteurNom + "';";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "AUTEURID"));
				colonne.add( rs.getString( "AUTEURNOM"));
				colonne.add( rs.getString( "AUTEURPRENOM"));
				colonne.add( rs.getString( "AUTEURPSEUDO"));

				vAuteur.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vAuteur;
	}

	public DefaultTableModel listeAuteurByName( String auteurNom) throws SQLException {
		
		Vector vAuteur = vectorListAuteurBtName( auteurNom);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "ID de l'auteur");
		nomColonne.add( "Nom");
		nomColonne.add( "Prénom");
		nomColonne.add( "Pseudo");
		
		return new DefaultTableModel( vAuteur, nomColonne);
	}

	@Override
	public Auteur findAuteurByNom(String auteurNom) throws SQLException {
		myConnexion = Connexion.getInstance();

		Auteur au = new Auteur();
		String query = "select * from AUTEUR where AUTEURNOM ='"+ auteurNom +"';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				au = new Auteur( rs.getString( "AUTEURID"),
								 rs.getString( "AUTEURNOM"),
								 rs.getString( "AUTEURPRENOM"),
								 rs.getString( "AUTEURPSEUDO"));

			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return au;
	}

	@Override
	public void supprimerAuteur(String auteurNom) throws SQLException {
		
		myConnexion = Connexion.getInstance();

		String query = "delete from AUTEUR where AUTEURNOM = '"+ auteurNom +"';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "L'auteur a été supprimé !", "Confirmation", JOptionPane.WARNING_MESSAGE);
	}

}
