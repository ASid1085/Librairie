package interfaceDaoLibrairie;

import java.sql.SQLException;
import java.util.Vector;

import entitiesLibrairie.Commentaire;

public interface ICommentaireDAO {

	
	public Vector<Vector> afficherCommentaire() throws SQLException;
	
	public Commentaire afficherUnCommentaire(Commentaire commentaire) throws SQLException;
	
	public Commentaire rechercherCommentaireparLivre(String livre) throws SQLException;
	
	public Commentaire rechercherCommentaireparClient(String client) throws SQLException;
	
	public Commentaire rechercherCommentaireparStatut(String statut) throws SQLException;
	
	public void modifierCommentaire(Commentaire commentaire) throws SQLException;
	
	public void supprimerCommentaire(String id) throws SQLException;
}
