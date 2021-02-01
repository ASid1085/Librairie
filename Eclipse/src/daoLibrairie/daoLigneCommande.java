package daoLibrairie;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import entitesLibrairie.LigneCommande;
import interfaceDaoLibrairie.iDaoLigneCommande;

public class daoLigneCommande implements iDaoLigneCommande{

	@Override
	public void addLigneCommande(LigneCommande ligneCde, String cdeNum, String livreIsbn, String ClientLogin)
			throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public Vector<LigneCommande> vectorListLigneCde() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public DefaultTableModel listeLigneCde() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

}
