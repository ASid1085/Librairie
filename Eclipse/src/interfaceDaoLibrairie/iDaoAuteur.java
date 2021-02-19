package interfaceDaoLibrairie;

import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import entitiesLibrairie.Auteur;

public interface iDaoAuteur {
	
	public void ajouterAuteur( Auteur auteur) throws SQLException;

	public void modifierAuteur( Auteur auteur, String auteurId) throws SQLException;

	public Vector<Auteur> vectorListAuteur() throws SQLException;

	public DefaultTableModel listeAuteur() throws SQLException;

	public Auteur findAuteurByNom( String auteurNom) throws SQLException;
	
	public void supprimerAuteur( String auteurNom) throws SQLException;

}
