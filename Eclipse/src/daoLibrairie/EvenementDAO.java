package daoLibrairie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Evenement;
import interfaceDaoLibrairie.IEvenementDAO;

public class EvenementDAO implements IEvenementDAO {

	private Connection myConnection;
	private Statement stmt;
	private PreparedStatement ptsmt;
	
	public EvenementDAO () {
		myConnection = Connexion.getInstance();
	}

	@Override
	public void ajouterEvenement(String nom, Date debut, Date fin, Float pourcentage, String codePromo, String image, String comment) throws SQLException {
		// TODO Auto-generated method stub
		String idNouveau = "";
		String id="";
		stmt = myConnection.createStatement();
		String queryy = "SELECT EVENEMENTID FROM EVENEMENT ORDER BY EVENEMENTID;";
		ResultSet result = stmt.executeQuery(queryy);
		while (result.next()) {
			id = result.getString("EVENEMENTID");
		}
		id = id.substring(0, 5);
		int change= Integer.parseInt(id);
		change = change + 1;
		idNouveau = "0000" + change + "EVE";
		
		String query = "INSERT INTO EVENEMENT "
				+ "(EVENEMENTID, EVENEMENTNOM, EVENEMENTDATEDEBUT, EVENEMENTDATEFIN, "
				+ "EVENEMENTPOURCENTAGE, EVENMENTCODEPROMO, EVENEMENTIMAGE, EVENEMENTCOMMENT) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.setString(1, idNouveau);
		ptsmt.setString(2, nom);
		ptsmt.setDate(3, (java.sql.Date) debut);
		ptsmt.setDate(4, (java.sql.Date) fin);
		ptsmt.setFloat(5, pourcentage);
		ptsmt.setString(6, codePromo);
		ptsmt.setString(7, image);
		ptsmt.setString(8, comment);
		ptsmt.executeUpdate();
	}

	@Override
	public Vector<Vector> afficherEvenements() throws SQLException {
		// TODO Auto-generated method stub
		Vector<Vector> vecteur = new  Vector();
		stmt = myConnection.createStatement();
		String query = "SELECT EVENEMENTNOM, EVENEMENTDATEDEBUT, EVENEMENTDATEFIN, "
				+ "EVENEMENTPOURCENTAGE, EVENMENTCODEPROMO, EVENEMENTIMAGE, EVENEMENTCOMMENT "
				+ "FROM EVENEMENT;";
		ResultSet res = stmt.executeQuery(query);
		while(res.next()) {
			Vector donnees = new Vector();
			donnees.add(res.getString("EVENEMENTNOM"));
			donnees.add(res.getDate("EVENEMENTDATEDEBUT"));
			donnees.add(res.getDate("EVENEMENTDATEFIN"));
			donnees.add(res.getFloat("EVENEMENTPOURCENTAGE"));
			donnees.add(res.getString("EVENMENTCODEPROMO"));
			donnees.add(res.getString("EVENEMENTIMAGE"));
			donnees.add(res.getString("EVENEMENTCOMMENT"));
			vecteur.add(donnees);
		}
		
		return vecteur;
	}
	
	@Override
	public Vector<Vector> rechercherEvenementparDate()  throws SQLException{
		// TODO Auto-generated method stub
		
		Vector<Vector> vecteur = new Vector();
		stmt = myConnection.createStatement();
		String query = "SELECT E.EVENEMENTNOM, EVENEMENTDATEDEBUT, EVENEMENTDATEFIN, "
				+ "EVENEMENTPOURCENTAGE, EVENMENTCODEPROMO, EVENEMENTIMAGE, EVENEMENTCOMMENT "
				+ "FROM EVENEMENT E "
				+ "INNER JOIN VIEW_INTERVALLE V "
				+ "ON E.EVENEMENTNOM = V.EVENEMENTNOM "
				+ "WHERE TODAY > 0 AND TODAY < INTERVALLE;";
		ResultSet res = stmt.executeQuery(query);
		while(res.next()) {
			Vector donnees = new Vector();
			donnees.add(res.getString("EVENEMENTNOM"));
			donnees.add(res.getDate("EVENEMENTDATEDEBUT"));
			donnees.add(res.getDate("EVENEMENTDATEFIN"));
			donnees.add(res.getFloat("EVENEMENTPOURCENTAGE"));
			donnees.add(res.getString("EVENMENTCODEPROMO"));
			donnees.add(res.getString("EVENEMENTIMAGE"));
			donnees.add(res.getString("EVENEMENTCOMMENT"));
			vecteur.add(donnees);
		}
		
		return vecteur;
	}

	@Override
	public void modifierEvenement(Evenement evenement, String id)  throws SQLException{
		// TODO Auto-generated method stub

		
		String query = "UPDATE EVENEMENT SET "
				+ "EVENEMENTNOM = '" + evenement.getEvenementNom() + "', "
				+ "EVENEMENTDATEDEBUT ='" + evenement.getEvenementDateDebut()+ "', "
				+ "EVENEMENTDATEFIN = '" + evenement.getEvenementDateFin()+ "', "
				+ "EVENEMENTPOURCENTAGE = " +evenement.getEvenementPourcentage() +", "
				+ "EVENMENTCODEPROMO = '" +evenement.getEvenementCodePromo()+ "', "
				+ "EVENEMENTIMAGE = '" +evenement.getEvenementImage()+"', "
				+ "EVENEMENTCOMMENT ='" +evenement.getEvenementComment()+"' "
				+ "WHERE EVENEMENTID = '" + evenement.getEvenementId()+"';";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.executeUpdate();

	}
	

	@Override
	public void supprimerEvenement(String nom) throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM EVENEMENT WHERE EVENEMENTNOM =\"" + nom + "\";";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.executeUpdate();
	}

	@Override
	public Evenement afficherEvenement(String nom) throws SQLException {
		
		Evenement evenement = new Evenement();
		String id = "";
		Statement stmtt = myConnection.createStatement();
		String queryy = "SELECT EVENEMENTID FROM EVENEMENT E"
				+ " INNER JOIN VIEW_INTERVALLE V"
				+ " ON E.EVENEMENTNOM = V.EVENEMENTNOM"
				+ " WHERE V.EVENEMENTNOM ='" +nom+"';";
		ResultSet resultt = stmtt.executeQuery(queryy);
		while (resultt.next()) {
			id = resultt.getString("EVENEMENTID");
		}
		
		
		Statement stmt = myConnection.createStatement();
		String query = "SELECT EVENEMENTID, EVENEMENTNOM, EVENEMENTDATEDEBUT, EVENEMENTDATEFIN, "
				+ "EVENEMENTPOURCENTAGE, EVENMENTCODEPROMO, EVENEMENTIMAGE, EVENEMENTCOMMENT "
				+ "FROM EVENEMENT WHERE EVENEMENTID = '" +id+ "';";
		ResultSet res = stmt.executeQuery(query);
		while(res.next()) {
			evenement.setEvenementId(id);
			evenement.setEvenementNom(res.getString("EVENEMENTNOM"));
			evenement.setEvenementDateDebut(res.getDate("EVENEMENTDATEDEBUT"));
			evenement.setEvenementDateFin(res.getDate("EVENEMENTDATEFIN"));
			evenement.setEvenementPourcentage(res.getFloat("EVENEMENTPOURCENTAGE"));
			evenement.setEvenementCodePromo(res.getString("EVENMENTCODEPROMO"));
			evenement.setEvenementImage(res.getString("EVENEMENTIMAGE"));
			evenement.setEvenementComment(res.getString("EVENEMENTCOMMENT"));
		}
		return evenement;
	}

	public Float recupRemise() throws SQLException {
		float remise = 0;
		
		String query = "SELECT E.EVENEMENTNOM, EVENEMENTDATEDEBUT, EVENEMENTDATEFIN, "
				+ "EVENEMENTPOURCENTAGE, EVENMENTCODEPROMO, EVENEMENTIMAGE, EVENEMENTCOMMENT "
				+ "FROM EVENEMENT E "
				+ "INNER JOIN VIEW_INTERVALLE V "
				+ "ON E.EVENEMENTNOM = V.EVENEMENTNOM "
				+ "WHERE TODAY > 0 AND TODAY < INTERVALLE;";
		
		stmt = myConnection.createStatement();
		ResultSet rs = stmt.executeQuery( query);
		while(rs.next()) {
			remise = rs.getFloat( "EVENEMENTPOURCENTAGE");
		}
		return remise;
	}
	
	public Evenement rechercherEvenementByDate() throws SQLException{
		Evenement ev = null;
		
		stmt = myConnection.createStatement();
		String query = "SELECT E.EVENEMENTNOM, EVENEMENTDATEDEBUT, EVENEMENTDATEFIN, "
				+ "EVENEMENTPOURCENTAGE, EVENMENTCODEPROMO, EVENEMENTIMAGE, EVENEMENTCOMMENT "
				+ "FROM EVENEMENT E "
				+ "INNER JOIN VIEW_INTERVALLE V "
				+ "ON E.EVENEMENTNOM = V.EVENEMENTNOM "
				+ "WHERE TODAY > 0 AND TODAY < INTERVALLE;";
		
		ResultSet res = stmt.executeQuery( query);
		while(res.next()) {
			ev = new Evenement( res.getString("EVENEMENTNOM"), res.getDate("EVENEMENTDATEDEBUT"), res.getDate("EVENEMENTDATEFIN"),
								res.getFloat("EVENEMENTPOURCENTAGE"), res.getString("EVENMENTCODEPROMO"), res.getString("EVENEMENTIMAGE"), res.getString("EVENEMENTCOMMENT"));
		}
		return ev;
	}
	
}
