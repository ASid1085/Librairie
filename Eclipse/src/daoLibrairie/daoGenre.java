package daoLibrairie;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import entitesLibrairie.Genre;
import interfaceDaoLibrairie.iDaoGenre;

public class daoGenre implements iDaoGenre {

	@Override
	public void ajouterGenre(Genre genre) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void modifierGenre(String genreNom) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public Vector<Genre> vectorListGenre() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public DefaultTableModel listeGenre() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public Genre findGenreByNom(String genreNom) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public void supprimerGenre() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

}
