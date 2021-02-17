package daoLibrairie;

import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Genre;
import interfaceDaoLibrairie.iDaoGenre;

public class daoGenre implements iDaoGenre {
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;

	public String ajoutIdGenre() {
		String id = null;

		myConnexion = Connexion.getInstance();

		String query = "select count(*) from GENRE;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				int numGenre = rs.getInt( 1) +1 ;
				if (numGenre < 10) {
					id = "0000" + numGenre + "GEN";
				} else if (numGenre < 100) {
					id = "000" + numGenre + "GEN";
				} else if (numGenre < 1000) {
					id = "00" + numGenre + "GEN";
				} else if (numGenre < 10000) {
					id = "0" + numGenre + "GEN";
				} if (numGenre > 99999) {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez plus ajouter de nouveau genre !", "Message d'erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return id;
	}


	@Override
	public void ajouterGenre( Genre genre) throws SQLException {
		
		myConnexion = Connexion.getInstance();
		
		String query = "insert into GENRE values ('" + genre.getGenreId() + "', '" + genre.getGenreNom() + "');";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "Le nouveau genre a été ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void modifierGenre( Genre genre, String genreId) throws SQLException {
		
		myConnexion = Connexion.getInstance();

		String query = "update GENRE set GENREID = '" + genre.getGenreId() +"', GENRENOM = '" + genre.getGenreNom() 
							+"' where GENREID = '" + genre.getGenreId() +"';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "La modification du genre a bien été effectuée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public Vector<Genre> vectorListGenre() throws SQLException {
		Vector vGenre = new Vector<>();
		
		myConnexion = Connexion.getInstance();

		String query = "select * from GENRE order by GENREID;";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "GENREID"));
				colonne.add( rs.getString( "GENRENOM"));

				vGenre.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vGenre;
	}

	@Override
	public DefaultTableModel listeGenre() throws SQLException {
		
		Vector Vgenre = vectorListGenre();
		Vector nomColonne = new Vector<>();
		nomColonne.add( "ID du genre");
		nomColonne.add( "Nom du genre");
		
		return new DefaultTableModel( Vgenre, nomColonne);
	}
	
	public Vector<Genre> vectorListGenreByName( String genreNom) throws SQLException {
		Vector vGenre = new Vector<>();
		
		myConnexion = Connexion.getInstance();

		String query = "select * from GENRE where GENRENOM = '" + genreNom + "';";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "GENREID"));
				colonne.add( rs.getString( "GENRENOM"));

				vGenre.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vGenre;
	}

	public DefaultTableModel listeGenreByName( String genreNom) throws SQLException {
		
		Vector Vgenre = vectorListGenreByName( genreNom);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "ID du genre");
		nomColonne.add( "Nom du genre");
		
		return new DefaultTableModel( Vgenre, nomColonne);
	}

	@Override
	public Genre findGenreByNom(String genreNom) throws SQLException {
		
		myConnexion = Connexion.getInstance();
		
		Genre g = new Genre();
		String query = "select * from GENRE where GENRENOM ='"+ genreNom +"';";
        try {
        	stmt = myConnexion.createStatement();
        	ResultSet rs = stmt.executeQuery( query);
        	while ( rs.next()) {
        		g = new Genre( 	rs.getString( "GENREID"),
        						rs.getString( "GENRENOM"));
        	}
        	rs.close();
        	stmt.close();
    } catch (SQLException ex) {
        System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
    }
		return g;
	}

	@Override
	public void supprimerGenre( String genreNom) throws SQLException {

		myConnexion = Connexion.getInstance();

		String query = "delete from GENRE where GENRENOM = '"+ genreNom +"';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "Le genre a été supprimé !", "Confirmation", JOptionPane.WARNING_MESSAGE);
		
		
	}

}
