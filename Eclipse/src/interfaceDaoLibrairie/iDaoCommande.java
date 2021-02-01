package interfaceDaoLibrairie;

import java.sql.*;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import entitesLibrairie.Commande;

public interface iDaoCommande {

	public void ajouterCommande(Commande cde, String ClientLog) throws SQLException;

	public void modifierCommande(Commande cde) throws SQLException;

	public Vector<Commande> vectorListCommande() throws SQLException;

	public DefaultTableModel listeCommande() throws SQLException;

	public Commande findCommandeByLogin(String clientLogin) throws SQLException;

	public Commande findCommandeByCdeNum(String CdeNum) throws SQLException;
	
	public Commande findCommandeByLivreTitre(String LivreTitre) throws SQLException;
	
	public Commande findCommandeByStatutId(String StatutId) throws SQLException;
	
	public Commande findCommandeByDateCde(Date cdeDate) throws SQLException;

}
