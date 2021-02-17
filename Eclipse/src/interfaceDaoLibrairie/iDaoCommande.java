package interfaceDaoLibrairie;

import java.sql.*;
import java.util.Date;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import entitiesLibrairie.Commande;

public interface iDaoCommande {

	public void ajouterCommande( Commande cde) throws SQLException;

	public void modifierCommande( Commande cde) throws SQLException;

	public Vector<Commande> vectorListCommande() throws SQLException;

	public DefaultTableModel listeCommande() throws SQLException;

	public Vector<Commande> findCommandeByLogin(String clientLogin) throws SQLException;
	
	public DefaultTableModel listeCommandeByLogin(String clientLogin) throws SQLException;

	public Vector<Commande> findCommandeByCdeNum(String CdeNum) throws SQLException;
	
	public DefaultTableModel listeCommandeByCdeNum(String CdeNum) throws SQLException;
	
	public Vector<Commande> findCommandeByStatut(String StatutLibelle) throws SQLException;
	
	public DefaultTableModel listeCommandeByStatut(String StatutLibelle) throws SQLException;
	
	public Vector<Commande> findCommandeByDateCde(Date cdeDate) throws SQLException;
	
	public DefaultTableModel listeCommandeByDateCde(Date cdeDate) throws SQLException;

}
