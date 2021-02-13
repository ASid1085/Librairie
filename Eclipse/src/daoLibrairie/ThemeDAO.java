package daoLibrairie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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
	public void ajouterTheme(String nom) throws SQLException {
		// TODO Auto-generated method stub
		String idNouveau = "";
		String id="";
		Statement stmtt = myConnection.createStatement();
		String queryy = "SELECT THEMEID FROM THEME ORDER BY THEMEID;";
		ResultSet resultt = stmtt.executeQuery(queryy);
		while(resultt.next()) {
			id = resultt.getString("THEMEID");
		}
		
		id = id.substring(0, 5);
		
		int change= Integer.parseInt(id);
		change = change + 1;
		idNouveau = "000" + change + "THE";
		
		String query = "INSERT INTO THEME (THEMEID, THEMENOM) VALUES ( ?, ?);";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.setString(1, idNouveau);
		ptsmt.setString(2, nom);
		int result = ptsmt.executeUpdate();

	}

	@Override
	public Vector<Vector> rechercherTheme(String string) throws SQLException {
		Vector <Vector> vecteur = new Vector <Vector> ();
		stmt = myConnection.createStatement();
		String query = "SELECT THEMENOM FROM THEME WHERE THEMENOM = '" + string + "';";
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			Vector <String> vectorString = new Vector();
			vectorString.add(res.getString("THEMENOM")) ;
			vecteur.add(vectorString);
		}
		return vecteur;
	}
	
	@Override
	public Vector<Vector> afficherTheme() throws SQLException {
		Vector <Vector> vecteur = new Vector <Vector> ();
		stmt = myConnection.createStatement();
		String query = "SELECT THEMENOM FROM THEME;";
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			Vector <String> vectorString = new Vector();
			vectorString.add(res.getString("THEMENOM")) ;
			vecteur.add(vectorString);
		}
		return vecteur;
	};

	@Override
	public void modifierTheme(Theme theme, String nom)  throws SQLException{
		// TODO Auto-generated method stub
		
		String themeID = "";
		stmt = myConnection.createStatement();
		String queryId = "SELECT THEMEID FROM THEME WHERE THEMENOM = '" +theme.getThemeId()+"';";
		ResultSet result = stmt.executeQuery(queryId);
		while(result.next()) {
			themeID = result.getString("THEMEID");
		}
		String query = "UPDATE THEME SET THEMENOM = '" +nom+"' WHERE THEMEID = '"+themeID+"';";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.executeUpdate();
		System.out.println(query);
	}

	@Override
	public void supprimerTheme(String nom) throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM THEME WHERE THEMENOM = '" +nom+ "';";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.executeUpdate();
	}

}
