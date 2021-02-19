package interfaceDaoLibrairie;

import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import entitiesLibrairie.MotCle;

public interface iDaoMotCle {

	public void ajouterMotCle( MotCle motCle) throws SQLException;

	public void modifierMotCle( MotCle motCle, String motCleId) throws SQLException;

	public Vector<MotCle> vectorListMotCle() throws SQLException;

	public DefaultTableModel listeMotCle() throws SQLException;

	public MotCle findMotCleByLibelle( String motCleLibelle) throws SQLException;
	
	public void supprimerMotCle( String MotClelibelle) throws SQLException;
	
}
