package interfaceDaoLibrairie;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import entitesLibrairie.Genre;


public interface iDaoGenre {

	public void ajouterGenre(Genre genre) throws SQLException;

	public void modifierGenre(String genreNom) throws SQLException;

	public Vector<String> vectorListGenre() throws SQLException;

	public DefaultTableModel listeGenre() throws SQLException;

	public Genre findGenreByNom(String genreNom) throws SQLException;
	
	public void supprimerGenre() throws SQLException;

}
