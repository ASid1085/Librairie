package interfaceDaoLibrairie;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

import entitesLibrairie.Genre;


public interface iDaoGenre {

	public void ajouterGenre( Genre genre) throws SQLException;

	public void modifierGenre( Genre genre, String genreId) throws SQLException;

	public Vector<Genre> vectorListGenre() throws SQLException;

	public DefaultTableModel listeGenre() throws SQLException;

	public Genre findGenreByNom( String genreNom) throws SQLException;
	
	public void supprimerGenre( String genreNom) throws SQLException;

}
