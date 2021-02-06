package daoLibrairie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import connexionLibrairie.Connexion;
import entitiesLibrairie.Employe;
import interfaceDaoLibrairie.IEmployeDAO;

public class EmployeDAO implements IEmployeDAO {

	private Connection myConnection;
	private Statement stmt;
	private PreparedStatement ptsmt;
	private Employe employe = new Employe();
	
	public EmployeDAO() {
		myConnection = Connexion.getInstance();
	}

	@Override
	public void ajouterEmploye(String acces, String nom, String prenom, String poste, String log, String mdp) throws SQLException{
		// TODO Auto-generated method stub
		stmt = myConnection.createStatement();
		String query = "SELECT DROITDACCESID FROM DROITS_D_ACCES WHERE DROITDACCESLIBELLE ='" +acces+ "';";
		ResultSet result = stmt.executeQuery(query);
		while(result.next()) {
			acces = result.getString("DROITDACCESID");
		}
		
		String idNouveau = "";
		String id="";
		Statement stmtt = myConnection.createStatement();
		String queryy = "SELECT EMPLOYEID FROM EMPLOYE ORDER BY EMPLOYEID;";
		ResultSet resultt = stmtt.executeQuery(queryy);
		while(resultt.next()) {
			id = resultt.getString("EMPLOYEID");
		}
	
		id = id.substring(0, 5);
	
		int change= Integer.parseInt(id);
		change = change + 1;
		idNouveau = "000" + change + "EMP";
		
		
		String pquery = "INSERT INTO EMPLOYE"
				+ " (EMPLOYEID, DROITDACCESID, EMPLOYENOM, EMPLOYEPRENOM, EMPLOYEPOSTE, EMPLOYELOG, EMPLOYEMDP)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
		ptsmt = myConnection.prepareStatement(pquery);
		ptsmt.setString(1, idNouveau);
		ptsmt.setString(2, acces);
		ptsmt.setString(3, nom);
		ptsmt.setString(4, prenom);
		ptsmt.setString(5, poste);
		ptsmt.setString(6, log);
		ptsmt.setString(7, mdp);
		int resultat = ptsmt.executeUpdate();
	}
	
	
	@Override
	public Vector<Vector> afficherEmploye() throws SQLException {
		// TODO Auto-generated method stub
		Vector <Vector> vecteur = new Vector <Vector> ();
		stmt = myConnection.createStatement();
		String query = "SELECT DROITDACCESLIBELLE, EMPLOYENOM, EMPLOYEPRENOM, EMPLOYEPOSTE, EMPLOYELOG, EMPLOYEMDP"
				+ " FROM EMPLOYE E"
				+ " JOIN DROITS_D_ACCES D"
				+ " ON E.DROITDACCESID = D.DROITDACCESID ;";
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			Vector <String> vectorString = new Vector();
			vectorString.add(res.getString("DROITDACCESLIBELLE")) ;
			vectorString.add(res.getString("EMPLOYENOM")) ;
			vectorString.add(res.getString("EMPLOYEPRENOM"));
			vectorString.add(res.getString("EMPLOYEPOSTE"));
			vectorString.add(res.getString("EMPLOYELOG"));
			vectorString.add(res.getString("EMPLOYEMDP"));
			vecteur.add(vectorString);
		}
		return vecteur;
	}
	

	@Override
	public Vector<Vector> rechercherEmploye(String nom)throws SQLException {
		// TODO Auto-generated method stub
		Vector <Vector> vecteur = new Vector <Vector> ();
		//Employe employe = null;
		stmt = myConnection.createStatement();
		String query = "SELECT DROITDACCESLIBELLE, EMPLOYENOM, EMPLOYEPRENOM, EMPLOYEPOSTE, EMPLOYELOG, EMPLOYEMDP"
				+ "	FROM EMPLOYE E"
				+ "	JOIN DROITS_D_ACCES D"
				+ "	ON E.DROITDACCESID = D.DROITDACCESID"
				+ " WHERE EMPLOYENOM ='" + nom + "';";
		ResultSet res = stmt.executeQuery(query);
		
		while (res.next()) {
			Vector <String> vectorString = new Vector();
			vectorString.add(res.getString("DROITDACCESLIBELLE")) ;
			vectorString.add(res.getString("EMPLOYENOM")) ;
			vectorString.add(res.getString("EMPLOYEPRENOM"));
			vectorString.add(res.getString("EMPLOYEPOSTE"));
			vectorString.add(res.getString("EMPLOYELOG"));
			vectorString.add(res.getString("EMPLOYEMDP"));
			vecteur.add(vectorString);
		}
		/*while (res.next()) {
			employe = new Employe (res.getString("EMPLOYEID"), 
					res.getString("DROITDACCESLIBELLE"), 
					res.getString("EMPLOYENOM"),
					res.getString("EMPLOYEPRENOM"),
					res.getString("EMPLOYEPOSTE"),
					res.getString("EMPLOYELOG"),
					res.getString("EMPLOYEMDP"));
		}*/

		return vecteur;
	}
	
	
	
	
	
	
	public Vector<Vector> rechercherEmployeparDroitAccess(String acces)throws SQLException {
		Employe employe = null;
		Vector <Vector> vecteur = new Vector();
		
		stmt = myConnection.createStatement();
		String query = "SELECT DROITDACCESLIBELLE, EMPLOYENOM, EMPLOYEPRENOM, EMPLOYEPOSTE, EMPLOYELOG, EMPLOYEMDP"
				+ "	FROM EMPLOYE E"
				+ "	JOIN DROITS_D_ACCES D"
				+ "	ON E.DROITDACCESID = D.DROITDACCESID"
				+ " WHERE DROITDACCESLIBELLE ='" + acces + "';";
		ResultSet res = stmt.executeQuery(query);
		
		
		while(res.next()) {
			Vector <String> vectorString = new Vector();
			vectorString.add(res.getString("DROITDACCESLIBELLE")) ;
			vectorString.add(res.getString("EMPLOYENOM")) ;
			vectorString.add(res.getString("EMPLOYEPRENOM"));
			vectorString.add(res.getString("EMPLOYEPOSTE"));
			vectorString.add(res.getString("EMPLOYELOG"));
			vectorString.add(res.getString("EMPLOYEMDP"));
			vecteur.add(vectorString);
		}
		
		/*while (res.next()) {
			employe = new Employe (res.getString("EMPLOYEID"), 
					res.getString("DROITDACCESLIBELLE"), 
					res.getString("EMPLOYENOM"),
					res.getString("EMPLOYEPRENOM"),
					res.getString("EMPLOYEPOSTE"),
					res.getString("EMPLOYELOG"),
					res.getString("EMPLOYEMDP"));
		}*/
		
		return vecteur;
	}

	@Override
	public void modifierEmployer(Employe employe, String log) throws SQLException{
		// TODO Auto-generated method stub
		
		
		stmt = myConnection.createStatement();
		String query = "SELECT DROITDACCESID FROM DROITS_D_ACCES WHERE DROITDACCESLIBELLE ='" + employe.getDroitsAcces()+ "';";
		ResultSet result = stmt.executeQuery(query);
		while(result.next()) {
			employe.setDroitDaccesId(result.getString("DROITDACCESID"));
		}

		Statement stmtt = myConnection.createStatement();
		String queryy = "SELECT EMPLOYEID FROM EMPLOYE WHERE EMPLOYELOG ='" +log+"';";
		ResultSet resultt = stmtt.executeQuery(queryy);
		while (resultt.next()) {
			employe.setEmployeId(resultt.getString("EMPLOYEID"));
		}
	
		String update = "UPDATE EMPLOYE "
				+ "SET DROITDACCESID ='"+ employe.getDroitDaccesId() + "', "
				+ "EMPLOYENOM = '" 		+ employe.getEmployeNom()+ "', "
				+ "EMPLOYEPRENOM = '" 	+ employe.getEmployePrenom() + "', "
				+ "EMPLOYEPOSTE = '" 	+ employe.getEmployePoste() + "', "
				+ "EMPLOYELOG = '" 		+ employe.getEmployeLog() + "', "
				+ "EMPLOYEMDP = '" 		+ employe.getEmployeMdp() + "' "
				+ "WHERE EMPLOYEID = '" + employe.getEmployeId() + "';";
		ptsmt = myConnection.prepareStatement(update);
		ptsmt.executeUpdate();
	}

	@Override
	public void supprimerEmploye(String log) throws SQLException{
		// TODO Auto-generated method stub
		String delete = "DELETE FROM EMPLOYE WHERE EMPLOYELOG = '" + log +"';";
		ptsmt = myConnection.prepareStatement(delete);
		ptsmt.executeUpdate();
	}

	@Override
	public Employe authentification(String id, String mdp) throws SQLException{
		// TODO Auto-generated method stub
		stmt = myConnection.createStatement();
		String query = "SELECT EMPLOYELOG, EMPLOYEMDP, DROITDACCESLIBELLE "
				+ "FROM EMPLOYE E "
				+ "JOIN DROITS_D_ACCES D "
				+ "ON E.DROITDACCESID = D.DROITDACCESID "
				+ "WHERE EMPLOYELOG ='" +id+"' AND EMPLOYEMDP ='" +mdp+ "';";
		ResultSet result = stmt.executeQuery(query);
		while (result.next()) {
			id = result.getString("EMPLOYELOG"); 
			mdp = result.getString("EMPLOYEMDP"); 
			String acces = result.getString("DROITDACCESLIBELLE");
			employe = new Employe (id, mdp, acces);
			
		}
		return employe;

		
	}

	@Override
	public Vector<String> recupererDroitsAcces() throws SQLException {
		// TODO Auto-generated method stub
		Vector<String> vecteur = new Vector();
		stmt = myConnection.createStatement();
		String query = "SELECT DROITDACCESLIBELLE FROM DROITS_D_ACCES;";
		ResultSet result = stmt.executeQuery(query);
		while (result.next()) {
			vecteur.add(result.getString("DROITDACCESLIBELLE"));
		}
		return vecteur;
	}


	

}
