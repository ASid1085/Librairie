package entitiesLibrairie;

import java.util.Vector;

public class LivreLilia {

	private String livreISBN;
	private String livreTitre;
	private String livreSousTitre;
	private float livrePrixHT;
	private float livreTVA;
	private String livreDateEdition;
	private String livreImage;
	private String livreResume;
	private int livreNbrePage;
	private int livreStock;
	private String livreCommentaire;
	private String livreStatut;
	private Auteur auteur; // la classe auteur est un objet que l'on peut utiliser comme propriete  de la classe livre. principe d'encapsulation
	private Editeur editeur;

	public LivreLilia() {
	}

	public Vector<LivreLilia> getLigne(){
		Vector lignes = new Vector<>();
		lignes.add( livreISBN);
		lignes.add( livreTitre);
		lignes.add( livreSousTitre);
		lignes.add( livrePrixHT);
		lignes.add( livreTVA);
		lignes.add( livreDateEdition);
		lignes.add( livreImage);
		lignes.add( livreNbrePage);
		lignes.add( livreStock);
		lignes.add( livreCommentaire);
		lignes.add( livreStatut);
		lignes.add( livreResume);
		lignes.add( auteur);
		lignes.add(editeur);
		return lignes;
	}

	public String getLivreISBN() {
		return livreISBN;
	}

	public void setLivreISBN(String livreISBN) {
		this.livreISBN = livreISBN;
	}

	public String getLivreTitre() {
		return livreTitre;
	}

	public void setLivreTitre(String livreTitre) {
		this.livreTitre = livreTitre;
	}

	public String getLivreSousTitre() {
		return livreSousTitre;
	}

	public void setLivreSousTitre(String livreSousTitre) {
		this.livreSousTitre = livreSousTitre;
	}

	public float getLivrePrixHT() {
		return livrePrixHT;
	}

	public void setLivrePrixHT(float livrePrixHT) {
		this.livrePrixHT = livrePrixHT;
	}

	public float getLivreTVA() {
		return livreTVA;
	}

	public void setLivreTVA(float livreTVA) {
		this.livreTVA = livreTVA;
	}

	public String getLivreDateEdition() {
		return livreDateEdition;
	}

	public void setLivreDateEdition(String livreDateEdition) {
		this.livreDateEdition = livreDateEdition;
	}

	public String getLivreImage() {
		return livreImage;
	}

	public void setLivreImage(String livreImage) {
		this.livreImage = livreImage;
	}

	public String getLivreResume() {
		return livreResume;
	}

	public void setLivreResume(String livreResume) {
		this.livreResume = livreResume;
	}

	public int getLivreNbrePage() {
		return livreNbrePage;
	}

	public void setLivreNbrePage(int livreNbrePage) {
		this.livreNbrePage = livreNbrePage;
	}

	public int getLivreStock() {
		return livreStock;
	}

	public void setLivreStock(int livreStock) {
		this.livreStock = livreStock;
	}

	public String getLivreCommentaire() {
		return livreCommentaire;
	}

	public void setLivreCommentaire(String livreCommentaire) {
		this.livreCommentaire = livreCommentaire;
	}

	public String getLivreStatut() {
		return livreStatut;
	}

	public void setLivreStatut(String livreStatut) {
		this.livreStatut = livreStatut;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}

}
