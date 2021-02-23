package interfaceDaoLibrairie;

import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import entitiesLibrairie.Editeur;

public interface iDaoEditeur {
	
	public void ajouterEditeur( Editeur editeur) throws SQLException;

	public void modifierEditeur( Editeur editeur, String editeurId) throws SQLException;

	public Vector<Editeur> vectorListEditeur() throws SQLException;

	public DefaultTableModel listeEditeur() throws SQLException;

	public Editeur findEditeurByNom( String editeurNom) throws SQLException;
	
	public void supprimerEditeur( String editeurNom) throws SQLException;

}
