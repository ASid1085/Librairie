package interfaceDaoLibrairie;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Vector;

import entitiesLibrairie.Evenement;

public interface IEvenementDAO {


	
	public void ajouterEvenement(String nom, Date debut, Date fin, Float pourcentage, String codePromo, String image, String comment) throws SQLException;

	public Vector<Vector> afficherEvenement() throws SQLException;
	
	public Vector<Vector> rechercherEvenementparDate() throws SQLException;
	
	public void modifierEvenement(Evenement evenement, String nom) throws SQLException;
	
	public void supprimerEvenement(String nom) throws SQLException;
	
}
