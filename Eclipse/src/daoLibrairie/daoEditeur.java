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
import entitiesLibrairie.Editeur;
import entitiesLibrairie.Genre;
import interfaceDaoLibrairie.iDaoEditeur;

public class daoEditeur implements iDaoEditeur {
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;
	
	public String ajoutIdEditeur() {
		String id = null;

		myConnexion = Connexion.getInstance();

		String query = "select count(*) from EDITEUR;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				int numEditeur = rs.getInt( 1) +1 ;
				if (numEditeur < 10) {
					id = "0000" + numEditeur + "EDI";
				} else if (numEditeur < 100) {
					id = "000" + numEditeur + "EDI";
				} else if (numEditeur < 1000) {
					id = "00" + numEditeur + "EDI";
				} else if (numEditeur < 10000) {
					id = "0" + numEditeur + "EDI";
				} if (numEditeur > 99999) {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez plus ajouter de nouvel éditeur !", "Message d'erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String recupererNoteEditeur(String editeurNom) throws SQLException {
		String commentaire = "";
		
		myConnexion = Connexion.getInstance();
	
		String query = "select EDITEURCOMMENT from EDITEUR where EDITEURNOM = '"+ editeurNom +"';";
	
		stmt = myConnexion.createStatement();
    	ResultSet rs = stmt.executeQuery( query);
    	while ( rs.next()) {
    		commentaire = rs.getString( "CLIENTCOMMENT");
    	}
    	rs.close();
    	stmt.close();
		
		return commentaire;
	}
	
	public void modifierNoteEditeur(String editeurNom, String commentaire) throws SQLException {
		
		myConnexion = Connexion.getInstance();
	
		String query = "update EDITEUR set EDITEURCOMMENT='"+ commentaire.replace("'", "''") +"' where EDITEURNOM = '"+ editeurNom +"';";
	
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
	
		pstmt.close();
		System.out.println( "La note au sujet de l'éditeur a bien été ajoutée !");;
	}

	@Override
	public void ajouterEditeur(Editeur editeur) throws SQLException {
		
		myConnexion = Connexion.getInstance();

		String query = "insert into EDITEUR values ('" + editeur.getEditeurId() + "', '" + editeur.getEditeurNom() 
													   + "', '" + editeur.getEditeurAdresse() + "', '" + editeur.getEditeurTel()
													   + "', '" + editeur.getEditeurMail() + "', '" + editeur.getEditeurContact()
													   + "', '" + editeur.getEditeurComment() + "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();

		pstmt.close();
		JOptionPane.showMessageDialog(null, "Le nouvel éditeur a été ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void modifierEditeur(Editeur editeur, String editeurId) throws SQLException {
		
		myConnexion = Connexion.getInstance();

		String query = "update EDITEUR set EDITEURID = '" + editeur.getEditeurId() + "', EDITEURNOM = '" + editeur.getEditeurNom()
							+ "', EDITEURADRESSE = '" + editeur.getEditeurAdresse() + "', EDITEURTEL = '" + editeur.getEditeurTel()
							+ "', EDITEURMAIL = '" + editeur.getEditeurMail() + "', EDITEURCONTACT = '" + editeur.getEditeurContact()
							+ "', EDITEURCOMMENT = '" + editeur.getEditeurComment()
							+ "' where GENREID = '" + editeur.getEditeurId() +"';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "La modification de l'éditeur a bien été effectuée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public Vector<Editeur> vectorListEditeur() throws SQLException {
		Vector vEditeur = new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select * from EDITEUR order by EDITEURNOM;";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "EDITEURID"));
				colonne.add( rs.getString( "EDITEURNOM"));
				colonne.add( rs.getString( "EDITEURADRESSE"));
				colonne.add( rs.getString( "EDITEURCONTACT"));
				colonne.add( rs.getString( "EDITEURMAIL"));
				colonne.add( rs.getString( "EDITEURTEL"));

				vEditeur.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vEditeur;
	}

	@Override
	public DefaultTableModel listeEditeur() throws SQLException {
		Vector vEditeur = vectorListEditeur();
		Vector nomColonne = new Vector<>();
		nomColonne.add( "Id de l'éditeur");
		nomColonne.add( "Nom");
		nomColonne.add( "Adresse");
		nomColonne.add( "Contact");
		nomColonne.add( "Mail");
		nomColonne.add( "Téléphone");
		
		return new DefaultTableModel( vEditeur, nomColonne);
	}
	
	public Vector<Editeur> vectorListEditeurByNom( String editeurNom) throws SQLException {
		Vector vEditeur = new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select * from EDITEUR where EDITEURNOM = '" + editeurNom + "';";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "EDITEURID"));
				colonne.add( rs.getString( "EDITEURNOM"));
				colonne.add( rs.getString( "EDITEURADRESSE"));
				colonne.add( rs.getString( "EDITEURCONTACT"));
				colonne.add( rs.getString( "EDITEURMAIL"));
				colonne.add( rs.getString( "EDITEURTEL"));

				vEditeur.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vEditeur;
	}

	public DefaultTableModel listeEditeurByNom( String editeurNom) throws SQLException {
		Vector vEditeur = vectorListEditeurByNom( editeurNom);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "Id de l'éditeur");
		nomColonne.add( "Nom");
		nomColonne.add( "Adresse");
		nomColonne.add( "Contact");
		nomColonne.add( "Mail");
		nomColonne.add( "Téléphone");
		
		return new DefaultTableModel( vEditeur, nomColonne);
	}

	@Override
	public Editeur findEditeurByNom(String editeurNom) throws SQLException {
		
		myConnexion = Connexion.getInstance();

		Editeur ed = new Editeur();
		String query = "select * from EDITEUR where EDITEURNOM ='"+ editeurNom +"';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				ed = new Editeur( rs.getString( "EDITEURID"),
								  rs.getString( "EDITEURNOM"),
								  rs.getString( "EDITEURADRESSE"),
								  rs.getString( "EDITEURTEL"),
								  rs.getString( "EDITEURMAIL"),
								  rs.getString( "EDITEURCONTACT"),
								  rs.getString( "EDITEURCOMMENT"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return ed;
	}

	@Override
	public void supprimerEditeur(String editeurNom) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "delete from EDITEUR where EDITEURNOM = '"+ editeurNom +"';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "L'éditeur a été supprimé !", "Confirmation", JOptionPane.WARNING_MESSAGE);
	}

}
