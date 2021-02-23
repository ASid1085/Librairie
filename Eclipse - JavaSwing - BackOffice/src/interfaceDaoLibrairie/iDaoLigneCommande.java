package interfaceDaoLibrairie;

import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import entitiesLibrairie.LigneCommande;

public interface iDaoLigneCommande {

	public void addLigneCommande( LigneCommande lCde) throws SQLException;

	public Vector<LigneCommande> vectorListLigneCde( String numCde) throws SQLException;

	public DefaultTableModel listeLigneCde( String numCde) throws SQLException;

}
