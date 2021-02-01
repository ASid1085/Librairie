package daoLibrairie;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import entitesLibrairie.Client;
import interfaceDaoLibrairie.iDaoClient;

public class daoClient implements iDaoClient{

	@Override
	public void ajouterClient(Client clt) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void modifierClient(Client clt) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public Vector<Client> vectorListClient() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public DefaultTableModel listeClient() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public Client findClientByLogin(String clientLogin) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public Client findClientByNom(String clientNom) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

}
