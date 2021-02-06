package interfaceDaoLibrairie;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import entitesLibrairie.Client;

public interface iDaoClient {
	
	public void ajouterClient(Client clt) throws SQLException;

	public void modifierClient(Client clt) throws SQLException;
	
	public Vector<Client> vectorListClient() throws SQLException;
	
	public DefaultTableModel listeClient() throws SQLException;
		
	public Client findClientByLogin(String clientLogin) throws SQLException;
	
	public Client findClientByNom(String clientLogin) throws SQLException;
}