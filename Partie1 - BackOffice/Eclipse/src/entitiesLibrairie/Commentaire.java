package entitiesLibrairie;

import java.sql.Date;
//import java.sql.Date;

public class Commentaire {

	private String commentaireId;
	private String clientLogin;
	private String livreISBN;
	private String commentaireTexte;
	private String commentaireNote;
	private String commentaireIp;
	private String commentaireStatut;
	private Date commentaireDate;
	private String commandeNum;
	private String ligneCommandeId;
	private String employeId;
	private Date dateModeration;
	private String livreTitre;
	
	
	public Commentaire() {
		
	}
	
	
	
	
	
	
	
	/*public Commentaire(String commentaireId, String clientLogin, String livreISBN, String commentaireTexte,
			String commentaireNote, String commentaireIp, String commentaireStatut, Date commentaireDate,
			String commandeNum, String ligneCommandeId, String employeId, Date dateModeration, String livreTitre) {
		super();
		this.commentaireId = commentaireId;
		this.clientLogin = clientLogin;
		this.livreISBN = livreISBN;
		this.commentaireTexte = commentaireTexte;
		this.commentaireNote = commentaireNote;
		this.commentaireIp = commentaireIp;
		this.commentaireStatut = commentaireStatut;
		this.commentaireDate = commentaireDate;
		this.commandeNum = commandeNum;
		this.ligneCommandeId = ligneCommandeId;
		this.employeId = employeId;
		this.dateModeration = dateModeration;
		this.livreTitre = livreTitre;
	}*/







	/*public Commentaire(String commentaireId, String clientLogin, String livreTitre, String commentaireTexte,
			String commentaireNote, String commentaireIp, String commentaireStatut, Date commentaireDate,
			String commandeNum, String ligneCommandeId, String employeId, Date dateModeration) {
		super();
		this.commentaireId = commentaireId;
		this.clientLogin = clientLogin;
		this.livreTitre = livreTitre;
		this.commentaireTexte = commentaireTexte;
		this.commentaireNote = commentaireNote;
		this.commentaireIp = commentaireIp;
		this.commentaireStatut = commentaireStatut;
		this.commentaireDate = commentaireDate;
		this.commandeNum = commandeNum;
		this.ligneCommandeId = ligneCommandeId;
		this.employeId = employeId;
		this.dateModeration = dateModeration;
	}*/







	/*public Commentaire(String commentaireId,  String clientLogin, String livreTitre, String commentaireTexte, String commentaireNote,
			String commentaireStatut, Date commentaireDate, String commandeNum, String employeId, Date dateModeration) {
		super();
		this.commentaireId = commentaireId;
		this.clientLogin = clientLogin;
		this.livreTitre = livreTitre;
		this.commentaireTexte = commentaireTexte;
		this.commentaireNote = commentaireNote;
		this.commentaireStatut = commentaireStatut;
		this.commentaireDate = commentaireDate;
		this.commandeNum = commandeNum;
		this.employeId = employeId;
		this.dateModeration = dateModeration;

	}*/







	public Commentaire(String commentaireId,String clientLogin,String livreTitre,  String commentaireTexte,
			String commentaireNote, String commentaireStatut, Date commentaireDate, Date dateModeration) {
		super();
		this.commentaireId = commentaireId;
		this.clientLogin = clientLogin;
		this.livreTitre = livreTitre;
		this.commentaireTexte = commentaireTexte;
		this.commentaireNote = commentaireNote;
		this.commentaireStatut = commentaireStatut;
		this.commentaireDate = commentaireDate;
		this.dateModeration = dateModeration;
	}







	/*public Commentaire(String commentaireId, String clientLogin, String commentaireTexte, String commentaireNote,
			String commentaireStatut, Date commentaireDate, Date dateModeration) {
		super();
		this.commentaireId = commentaireId;
		this.clientLogin = clientLogin;
		this.commentaireTexte = commentaireTexte;
		this.commentaireNote = commentaireNote;
		this.commentaireStatut = commentaireStatut;
		this.commentaireDate = commentaireDate;
		this.dateModeration = dateModeration;
	}*/
	
	
	



	/*public Commentaire(String commentaireId, String clientLogin, String commentaireTexte, String commentaireNote,
			String commentaireStatut, Date dateModeration, String livreTitre) {
		super();
		this.commentaireId = commentaireId;
		this.clientLogin = clientLogin;
		this.commentaireTexte = commentaireTexte;
		this.commentaireNote = commentaireNote;
		this.commentaireStatut = commentaireStatut;
		this.dateModeration = dateModeration;
		this.livreTitre = livreTitre;
	}*/







	/*public Commentaire(String commentaireId, String commentaireTexte, String commentaireNote, String commentaireStatut,
			Date commentaireDate, Date dateModeration) {
		super();
		this.commentaireId = commentaireId;
		this.commentaireTexte = commentaireTexte;
		this.commentaireNote = commentaireNote;
		this.commentaireStatut = commentaireStatut;
		this.commentaireDate = commentaireDate;
		this.dateModeration = dateModeration;
	}*/


	public String getCommentaireId() {
		return commentaireId;
	}


	public void setCommentaireId(String commentaireId) {
		this.commentaireId = commentaireId;
	}


	public String getClientLogin() {
		return clientLogin;
	}


	public void setClientLogin(String clientLogin) {
		this.clientLogin = clientLogin;
	}


	public String getCommentaireTexte() {
		return commentaireTexte;
	}


	public void setCommentaireTexte(String commentaireTexte) {
		this.commentaireTexte = commentaireTexte;
	}


	public String getCommentaireNote() {
		return commentaireNote;
	}


	public void setCommentaireNote(String commentaireNote) {
		this.commentaireNote = commentaireNote;
	}
	
	
	public String getCommentaireIp() {
		return commentaireIp;
	}


	public void setCommentaireIp(String commentaireIp) {
		this.commentaireIp = commentaireIp;
	}


	public void setLivreISBN(String livreISBN) {
		this.livreISBN = livreISBN;
	}


	public String getCommentaireStatut() {
		return commentaireStatut;
	}


	public void setCommentaireStatut(String commentaireStatut) {
		this.commentaireStatut = commentaireStatut;
	}


	public Date getCommentaireDate() {
		return commentaireDate;
	}


	public void setCommentaireDate(Date commentaireDate) {
		this.commentaireDate = commentaireDate;
	}


	public String getCommandeNum() {
		return commandeNum;
	}


	public void setCommandeNum(String commandeNum) {
		this.commandeNum = commandeNum;
	}


	public String getLigneCommandeId() {
		return ligneCommandeId;
	}


	public void setLigneCommandeId(String ligneCommandeId) {
		this.ligneCommandeId = ligneCommandeId;
	}


	public String getEmployeId() {
		return employeId;
	}


	public void setEmployeId(String employeId) {
		this.employeId = employeId;
	}


	public Date getDateModeration() {
		return dateModeration;
	}


	public void setDateModeration(Date dateModeration) {
		this.dateModeration = dateModeration;
	}


	public String getLivreISBN() {
		return livreISBN;
	}


	public String getLivreTitre() {
		return livreTitre;
	}

	public void setLivreTitre(String livreTitre) {
		this.livreTitre = livreTitre;
	}







	@Override
	public String toString() {
		return "Commentaire numéro : " + commentaireId + ", \"" + commentaireTexte
				+ "\", avec une note de :" + commentaireNote + "/5.\nCe commentaire "
				+ "est traité comme : " + commentaireStatut + ".\nIl a été validé pour le livre : "
				+ livreTitre + ".\n"
				+ "Il a été posté par :" +clientLogin;
	}


	
	

}