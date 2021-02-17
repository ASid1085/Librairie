package interfaceDaoLibrairie;

import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import entitiesLibrairie.LigneCommande;

public interface iDaoLigneCommande {

	public void addLigneCommande( LigneCommande lCde) throws SQLException;

	public Vector<LigneCommande> vectorListLigneCde() throws SQLException;

	public DefaultTableModel listeLigneCde() throws SQLException;

}
