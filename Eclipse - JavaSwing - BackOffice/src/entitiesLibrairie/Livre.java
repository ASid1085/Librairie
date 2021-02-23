package entitiesLibrairie;

import java.util.Vector;

public class Livre {

	
	private String livreISBN;
	private String livreTitre;
	private String sousTitre;
	private float livrePrixHT;
	private String tvaId;
	private String livreDateEdition;
	private String livreImage;
	private String livreResume;
	private float livreNbrePage;
	private String livreStock;
	private String livreComment;
	private String livreStatut;

	
	
	
	public Livre() {
		
	}
	
	
	public Livre(String livreISBN, String livreTitre, String sousTitre, float livrePrixHT, String tvaId,
			String livreDateEdition, String livreImage, String livreResume, float livreNbrePage, String livreStock,
			String livreComment, String livreStatut) {
		super();
		this.livreISBN = livreISBN;
		this.livreTitre = livreTitre;
		this.sousTitre = sousTitre;
		this.livrePrixHT = livrePrixHT;
		this.tvaId = tvaId;
		this.livreDateEdition = livreDateEdition;
		this.livreImage = livreImage;
		this.livreResume = livreResume;
		this.livreNbrePage = livreNbrePage;
		this.livreStock = livreStock;
		this.livreComment = livreComment;
		this.livreStatut = livreStatut;
	}
	
	
	 public Vector <Livre> getLigne(){
	        Vector lignes = new Vector();
	        lignes.add(this.livreISBN);
	        lignes.add(this.livreTitre);
	        lignes.add(this.sousTitre);
	        lignes.add(this.livrePrixHT);
	        lignes.add(this.tvaId);
	        lignes.add(this.livreDateEdition);
	        lignes.add(this.livreImage);
	        lignes.add(this.livreNbrePage);
	        lignes.add(this.livreStock);
	        lignes.add(this.livreComment);
	        lignes.add(this.livreStatut);
	        lignes.add(this.livreResume);
	        //lignes.add(this.auteur);
	        //lignes.add(this.editeur);
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
	public String getSousTitre() {
		return sousTitre;
	}
	public void setSousTitre(String sousTitre) {
		this.sousTitre = sousTitre;
	}
	public float getLivrePrixHT() {
		return livrePrixHT;
	}
	public void setLivrePrixHT(float livrePrixHT) {
		this.livrePrixHT = livrePrixHT;
	}
	public String getTvaId() {
		return tvaId;
	}
	public void setTvaId(String tvaId) {
		this.tvaId = tvaId;
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
	public float getLivreNbrePage() {
		return livreNbrePage;
	}
	public void setLivreNbrePage(float livreNbrePage) {
		this.livreNbrePage = livreNbrePage;
	}
	public String getLivreStock() {
		return livreStock;
	}
	public void setLivreStock(String livreStock) {
		this.livreStock = livreStock;
	}
	public String getLivreComment() {
		return livreComment;
	}
	public void setLivreComment(String livreComment) {
		this.livreComment = livreComment;
	}
	public String getLivreStatut() {
		return livreStatut;
	}
	public void setLivreStatut(String livreStatut) {
		this.livreStatut = livreStatut;
	}
	
	
	
}
