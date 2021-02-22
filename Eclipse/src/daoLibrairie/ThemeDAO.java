package daoLibrairie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Theme;
import interfaceDaoLibrairie.IThemeDAO;

public class ThemeDAO implements IThemeDAO {

	private Connection myConnection ;
	private Statement stmt;
	private PreparedStatement ptsmt;
	
	
	
	public ThemeDAO() {
		myConnection = Connexion.getInstance();
	}

	@Override
	public void ajouterTheme(String id, String nom) throws SQLException {
		// TODO Auto-generated method stub
		
		String query = "INSERT INTO THEME (THEMEID, THEMENOM) VALUES ( ?, ?);";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.setString(1, id);
		ptsmt.setString(2, nom);
		int result = ptsmt.executeUpdate();

	}

	@Override
	public Vector<Vector> rechercherTheme(String string) throws SQLException {
		Vector <Vector> vecteur = new Vector <Vector> ();
		stmt = myConnection.createStatement();
		String query = "SELECT THEMEID, THEMENOM FROM THEME WHERE THEMENOM LIKE '%" + string + "%';";
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			Vector <String> vectorString = new Vector();
			vectorString.add(res.getString("THEMEID"));
			vectorString.add(res.getString("THEMENOM")) ;
			vecteur.add(vectorString);
		}
		return vecteur;
	}
	
	@Override
	public Vector<Vector> afficherTheme() throws SQLException {
		Vector <Vector> vecteur = new Vector <Vector> ();
		stmt = myConnection.createStatement();
		String query = "SELECT THEMEID, THEMENOM FROM THEME;";
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			Vector <String> vectorString = new Vector();
			vectorString.add(res.getString("THEMEID"));
			vectorString.add(res.getString("THEMENOM")) ;
			vecteur.add(vectorString);
		}
		return vecteur;
	};

	@Override
	public void modifierTheme(String id, String nom)  throws SQLException{
		// TODO Auto-generated method stub
		
		String query = "UPDATE THEME SET THEMENOM = '" +nom+"' WHERE THEMEID = '"+id+"';";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.executeUpdate();

	}

	@Override
	public void supprimerTheme(String id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM THEME WHERE THEMEID = '" +id+ "';";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.executeUpdate();
	}
	
	@Override
	public String ajoutIdTheme() throws SQLException {
		String id = null;
		stmt = myConnection.createStatement();
		String query = "SELECT COUNT(*) FROM THEME;";
		ResultSet rs = stmt.executeQuery( query);
		while ( rs.next()) {
			int numTheme = rs.getInt( 1) +1 ;
			if (numTheme < 10) {
				id = "0000" + numTheme + "THE";
			} else if (numTheme < 100) {
				id = "000" + numTheme + "THE";
			} else if (numTheme < 1000) {
				id = "00" + numTheme + "THE";
			} else if (numTheme < 10000) {
				id = "0" + numTheme + "THE";
			} if (numTheme > 99999) {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez plus ajouter de nouveau Theme !", "Message d'erreur", JOptionPane.WARNING_MESSAGE);
			}
		}

		return id;
	}

	@Override
	public Theme rechercherUnTheme(String id) throws SQLException {
		// TODO Auto-generated method stub
		Theme theme = new Theme();
		stmt = myConnection.createStatement();
		String query = "SELECT THEMEID, THEMENOM FROM THEME WHERE THEMEID = '" + id + "';";
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			theme.setThemeId(res.getString("THEMEID"));
			theme.setThemeNom(res.getString("THEMENOM")) ;
		}
	
		return theme;
	}
	
	public Vector<String> vectorListTheme() throws SQLException {
		Vector<String> vTheme = new Vector<>();

		
		String query =	"select * from THEME order by THEMENOM;";

		try {
			stmt = myConnection.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				vTheme.add( rs.getString( "THEMENOM"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vTheme;
	}
	
	public String recupIdTheme( String nomTheme) throws SQLException {
		String id = "";
		
		stmt = myConnection.createStatement();
		String query = "select THEMEID from THEME where THEMENOM = '" + nomTheme + "';";
		ResultSet rs = stmt.executeQuery( query);
		
		while ( rs.next()) {
			id = rs.getString( "THEMEID");
		}

		return id;
	}

}
