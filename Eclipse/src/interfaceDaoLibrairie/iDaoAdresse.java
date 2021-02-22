package interfaceDaoLibrairie;

import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import entitiesLibrairie.Adresse;

public interface iDaoAdresse {
	
	public void addAdresse( Adresse adresse) throws SQLException;
	
	public void ModifierAdresse( Adresse adresse) throws SQLException;
	
	public Vector<Adresse> vectorListAdresse() throws SQLException;
	
	public DefaultTableModel listeAdresse() throws SQLException;
	
	public Vector<Adresse> findAdresseLivByLogin(String clientLogin) throws SQLException;
	
	public DefaultTableModel listeAdresseLivByLogin(String clientLogin) throws SQLException;

	public Vector<Adresse> findAdresseFacByLogin(String clientLogin) throws SQLException;

	public DefaultTableModel listeAdresseFacByLogin(String clientLogin) throws SQLException;

}
