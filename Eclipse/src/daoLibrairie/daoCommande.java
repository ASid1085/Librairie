package daoLibrairie;

import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import entitesLibrairie.Commande;
import interfaceDaoLibrairie.iDaoCommande;

public class daoCommande implements iDaoCommande {

	@Override
	public void ajouterCommande(Commande cde, String ClientLog) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void modifierCommande(Commande cde) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public Vector<Commande> vectorListCommande() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public DefaultTableModel listeCommande() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public Commande findCommandeByLogin(String clientLogin) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public Commande findCommandeByCdeNum(String CdeNum) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public Commande findCommandeByLivreTitre(String LivreTitre) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public Commande findCommandeByStatutId(String StatutId) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public Commande findCommandeByDateCde(Date cdeDate) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

}
