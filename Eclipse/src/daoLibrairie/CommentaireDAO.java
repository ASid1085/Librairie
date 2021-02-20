package daoLibrairie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Commentaire;
import interfaceDaoLibrairie.ICommentaireDAO;

public class CommentaireDAO implements ICommentaireDAO{

	private Connection myConnection;
	private Statement stmt;
	private PreparedStatement ptsmt;
	private Commentaire commentaire ;
	private Vector <Vector> vecteur = new Vector <Vector> ();
	private String id ="";
	private String titre ="";
	private String clientt = "";
	private String texte =""  ;
	private String note ="" ;
	private String statutt="" ;
	private Date date = null ;
	private String commande = "";
	private String employeId = "";
	private Date moderation =null ;
	
	
	
	
	public CommentaireDAO() {
		myConnection = Connexion.getInstance();
	}

	@Override
	public Vector <Vector> afficherCommentaire() throws SQLException {

		vecteur = new Vector();
		stmt = myConnection.createStatement();
		String query = "SELECT COMMENTAIREID, LIVRETITRE, CLIENTLOGIN, COMMENTAIRETEXTE, "
				+ "COMMENTAIRENOTE, COMMENTAIRESTATUT, COMMENTAIREDATE, "
				+ "DATEMODERATION  "
				+ "FROM COMMENTAIRE C "
				+ "INNER JOIN LIVRE L ON "
				+ "C.LIVREISBN = L.LIVREISBN "
				+ "ORDER BY COMMENTAIREID; ";
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			Vector vectorString = new Vector();
			vectorString.add(res.getString("COMMENTAIREID")) ;
			vectorString.add(res.getString("CLIENTLOGIN")) ;
			vectorString.add(res.getString("LIVRETITRE"));
			vectorString.add(res.getString("COMMENTAIRETEXTE"));
			vectorString.add(res.getString("COMMENTAIRENOTE"));
			vectorString.add(res.getString("COMMENTAIRESTATUT"));
			vectorString.add(res.getDate("COMMENTAIREDATE"));
			vectorString.add(res.getDate("DATEMODERATION"));
			vecteur.add(vectorString);
		}
		return vecteur;
	}
	
	@Override
	public Vector<Vector> rechercherCommentaireparLivre(String livre) throws SQLException {
		// TODO Auto-generated method stub
		
		Vector <Vector> vecteur = new Vector <Vector>();
		
		stmt = myConnection.createStatement();
		String query = "SELECT COMMENTAIREID, LIVRETITRE, CLIENTLOGIN, COMMENTAIRETEXTE, "
				+ "	COMMENTAIRENOTE, COMMENTAIRESTATUT, COMMENTAIREDATE, "
				+ " DATEMODERATION  "
				+ "	FROM COMMENTAIRE C "
				+ "	INNER JOIN LIVRE L ON "
				+ "	C.LIVREISBN = L.LIVREISBN "
				+ "	WHERE LIVRETITRE LIKE '%" +livre+ "%'"
				+ "	ORDER BY COMMENTAIREID;";
		ResultSet res = stmt.executeQuery(query);

		while (res.next()) {
			Vector vectorString = new Vector();
			vectorString.add(res.getString("COMMENTAIREID")) ;
			vectorString.add(res.getString("CLIENTLOGIN")) ;
			vectorString.add(res.getString("LIVRETITRE"));
			vectorString.add(res.getString("COMMENTAIRETEXTE"));
			vectorString.add(res.getString("COMMENTAIRENOTE"));
			vectorString.add(res.getString("COMMENTAIRESTATUT"));
			vectorString.add(res.getDate("COMMENTAIREDATE"));
			vectorString.add(res.getDate("DATEMODERATION"));
			vecteur.add(vectorString);

		}
		return vecteur;
	}

	@Override
	public Vector<Vector> rechercherCommentaireparClient(String client) throws SQLException {
		// TODO Auto-generated method stub
		Vector <Vector> vecteur = new Vector <Vector>();
		
		stmt = myConnection.createStatement();
		String query = "SELECT COMMENTAIREID, LIVRETITRE, CLIENTLOGIN, COMMENTAIRETEXTE, "
				+ "	COMMENTAIRENOTE, COMMENTAIRESTATUT, COMMENTAIREDATE, "
				+ "	DATEMODERATION  "
				+ "	FROM COMMENTAIRE C "
				+ "	INNER JOIN LIVRE L ON "
				+ "	C.LIVREISBN = L.LIVREISBN "
				+ "	WHERE CLIENTLOGIN LIKE '%" +client+ "%'"
				+ "	ORDER BY COMMENTAIREID;";

		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			Vector vectorString = new Vector();
			vectorString.add(res.getString("COMMENTAIREID")) ;
			vectorString.add(res.getString("CLIENTLOGIN")) ;
			vectorString.add(res.getString("LIVRETITRE"));
			vectorString.add(res.getString("COMMENTAIRETEXTE"));
			vectorString.add(res.getString("COMMENTAIRENOTE"));
			vectorString.add(res.getString("COMMENTAIRESTATUT"));
			vectorString.add(res.getDate("COMMENTAIREDATE"));
			vectorString.add(res.getDate("DATEMODERATION"));
			vecteur.add(vectorString);
		}
		return vecteur;
	}

	@Override
	public Vector<Vector> rechercherCommentaireparStatut (String statut)throws SQLException {
		// TODO Auto-generated method stub
		
		Vector <Vector> vecteur = new Vector <Vector>();
		
		stmt = myConnection.createStatement();
		String query = "SELECT COMMENTAIREID, LIVRETITRE, CLIENTLOGIN, COMMENTAIRETEXTE, "
				+ "COMMENTAIRENOTE, COMMENTAIRESTATUT, COMMENTAIREDATE, "
				+ "DATEMODERATION  "
				+ "FROM COMMENTAIRE C "
				+ "INNER JOIN LIVRE L ON "
				+ "C.LIVREISBN = L.LIVREISBN "
				+ "WHERE COMMENTAIRESTATUT LIKE '%" +statut+ "%' "
				+ "ORDER BY COMMENTAIREID;";
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			Vector vectorString = new Vector();
			vectorString.add(res.getString("COMMENTAIREID")) ;
			vectorString.add(res.getString("CLIENTLOGIN")) ;
			vectorString.add(res.getString("LIVRETITRE"));
			vectorString.add(res.getString("COMMENTAIRETEXTE"));
			vectorString.add(res.getString("COMMENTAIRENOTE"));
			vectorString.add(res.getString("COMMENTAIRESTATUT"));
			vectorString.add(res.getDate("COMMENTAIREDATE"));
			vectorString.add(res.getDate("DATEMODERATION"));
			vecteur.add(vectorString);
		}
		return vecteur;
	}

	@Override
	public void modifierCommentaire(Commentaire commentaire, String id, String texte) throws SQLException {
		// TODO Auto-generated method stub
		String isbn = "";
		stmt = myConnection.createStatement();
		String queryIsbn = "SELECT LIVREISBN FROM COMMENTAIRE WHERE COMMENTAIREID = '" +commentaire.getCommentaireId()+"';";
		ResultSet result = stmt.executeQuery(queryIsbn);
		while(result.next()) {
			isbn = result.getString("LIVREISBN");
		}


		
		String query = "UPDATE COMMENTAIRE C INNER JOIN LIVRE L "
						+ "ON C.LIVREISBN = L.LIVREISBN "
						+ "SET COMMENTAIREID ='" +commentaire.getCommentaireId()+ "', "
						+ "CLIENTLOGIN = '" +commentaire.getClientLogin()+ "', "
						+ "LIVRETITRE = '" +commentaire.getLivreTitre()+ "', "
						+ "COMMENTAIRETEXTE = '" +texte.replace("'", "''")+"' , "
						+ "COMMENTAIRENOTE = '" +commentaire.getCommentaireNote()+ "', "
						+ "COMMENTAIRESTATUT = '" +commentaire.getCommentaireStatut()+ "', "
						+ "COMMENTAIREDATE = '" + commentaire.getCommentaireDate()+ "', "
						+ "DATEMODERATION = '" +commentaire.getDateModeration()+"' "
						+ "WHERE COMMENTAIREID = '" + id+"' "
						+ "AND C.LIVREISBN = \""+isbn+"\";";
		ptsmt = myConnection.prepareStatement(query);

		ptsmt.executeUpdate();

	}

	@Override
	public void supprimerCommentaire(String id) throws SQLException {
		// TODO Auto-generated method stub
		String query = "DELETE FROM COMMENTAIRE WHERE COMMENTAIREID = '" + id + "';";
		ptsmt = myConnection.prepareStatement(query);
		ptsmt.executeUpdate();
	}

	@Override
	public Commentaire afficherUnCommentaire(Commentaire commentaire, String titre) throws SQLException {
		stmt = myConnection.createStatement();
		String query = "SELECT COMMENTAIREID, LIVRETITRE, CLIENTLOGIN, COMMENTAIRETEXTE, "
				+ "COMMENTAIRENOTE, COMMENTAIRESTATUT, COMMENTAIREDATE, "
				+ "DATEMODERATION  "
				+ "FROM COMMENTAIRE C "
				+ "INNER JOIN LIVRE L ON "
				+ "C.LIVREISBN = L.LIVREISBN "
				+ "WHERE LIVRETITRE = '"+titre+"'; ";
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			
			commentaire.setCommentaireId(res.getString("COMMENTAIREID")) ;
			commentaire.setLivreTitre(res.getString("LIVRETITRE")) ;
			commentaire.setClientLogin(res.getString("CLIENTLOGIN"));
			commentaire.setCommentaireTexte(res.getString("COMMENTAIRETEXTE")) ;
			commentaire.setCommentaireNote(res.getString("COMMENTAIRENOTE")) ;
			commentaire.setCommentaireStatut(res.getString("COMMENTAIRESTATUT")) ;
			commentaire.setCommentaireDate(res.getDate("COMMENTAIREDATE")) ;
			commentaire.setDateModeration(res.getDate("DATEMODERATION")) ;
		}
		return commentaire;
	}






	
}
