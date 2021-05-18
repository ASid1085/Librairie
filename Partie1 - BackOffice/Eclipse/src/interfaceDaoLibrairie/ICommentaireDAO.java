package interfaceDaoLibrairie;

import java.sql.SQLException;
import java.util.Vector;

import entitiesLibrairie.Commentaire;

public interface ICommentaireDAO {

	
	public Vector<Vector> afficherCommentaire() throws SQLException;
	
	public Commentaire afficherUnCommentaire(Commentaire commentaire, String titre) throws SQLException;
	
	public Vector<Vector> rechercherCommentaireparLivre(String livre) throws SQLException;
	
	public Vector<Vector> rechercherCommentaireparClient(String client) throws SQLException;
	
	public Vector<Vector> rechercherCommentaireparStatut(String statut) throws SQLException;
	
	public void modifierCommentaire(Commentaire commentaire, String id, String texte) throws SQLException;
	
	public void supprimerCommentaire(String id) throws SQLException;
	
	
}
