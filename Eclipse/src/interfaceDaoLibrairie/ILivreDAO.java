package interfaceDaoLibrairie;

import entitiesLibrairie.Employe;
import entitiesLibrairie.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import connexionLibrairie.Connexion;

public interface ILivreDAO {
	

	
	public Livre ajouterLivre(String isbn, String titre, String sousTitre, float prixHT, String tvaID, String dateEdition, String image, String resume, float nbrePages, String stock, String comment, String statut) throws SQLException;
	
	
	public void lierLivreAuteur(String auteur, String isbn) throws SQLException;
	
	
	public void lierLivreEditeur(String editeur, String isbn) throws SQLException;
	
	
	public void lierLivreTheme(String theme, String titre) throws SQLException;
	
	
	public void lierLivreMotCle(String motCle, String isbn) throws SQLException;
	
	
	public Float recupererTVA( String titre) throws SQLException;
	
	
	public String recupererISBN(String titre) throws SQLException;
	
	
	public Vector findAll() throws SQLException;
	
	public String addBook(Livre livre, String a, String b) throws SQLException;
	
	public Vector findByParameter(String champs, String valeurChamps) throws SQLException;
	
	public void modifierLivre(Livre livre, String tva) throws SQLException;
	
	public Vector recupererAuteur(String isbn) throws SQLException;
	
	public String recupererEditeur(String isbn) throws SQLException;
	
	public Vector recupererGenre(String isbn) throws SQLException;
	
	public Vector recupererMotCle(String isbn) throws SQLException;
	
	public Vector recupererTheme(String isbn) throws SQLException;
	
	public Vector rechercheLivreparAuteur(String auteur) throws SQLException;
	
	public Vector rechercheLivreparEditeur(String editeur) throws SQLException;
	
	public Vector rechercheLivreparISBN(String isbn) throws SQLException;
	
	public Vector rechercheLivreparTitre(String titre) throws SQLException;
	
	public Vector rechercheLivreparPrixHT(String prixHT) throws SQLException;
	
	public Vector rechercheLivreparPrixTTC(String prixTTC) throws SQLException;
	
	public Vector rechercheLivreparTheme(String theme) throws SQLException;
	
	public Vector rechercheLivreparGenre(String genre) throws SQLException;
	
	public Vector rechercheLivreparMotCle(String motCle) throws SQLException;
	
	public Vector rechercheLivreparStock(String stock) throws SQLException;
	
	public Livre afficherLivre(String isbn) throws SQLException;

	
	

}
