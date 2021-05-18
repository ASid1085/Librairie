package interfaceDaoLibrairie;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Theme;

public interface IThemeDAO {

	
	public void ajouterTheme(String id, String nom) throws SQLException;
	
	public Vector<Vector> afficherTheme() throws SQLException;
	
	public Vector<Vector> rechercherTheme(String string) throws SQLException;
	
	public Theme rechercherUnTheme(String id) throws SQLException;
	
	public void modifierTheme(String id, String nom) throws SQLException;
	
	public void supprimerTheme(String id) throws SQLException;
	
	public String ajoutIdTheme() throws SQLException;
	



	
	
}
