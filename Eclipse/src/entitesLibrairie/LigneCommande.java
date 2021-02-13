package entitesLibrairie;

public class LigneCommande {
	
	private String ligneCdeId;
	private String cdeNum;
	private String livreIsbn;
	private float ligneCdeQte;
	private float ligneCdePrixHt;
	private float ligneCdeTvaAppliquee;
	private float ligneCdeRemise;
	private String clientLogin;
	private String commentaireId;
	private String evenementId;
	
	public LigneCommande(String ligneCdeId, float ligneCdeQte, float ligneCdePrixHt, float ligneCdeTvaAppliquee) {
		super();
		this.ligneCdeId = ligneCdeId;
		this.ligneCdeQte = ligneCdeQte;
		this.ligneCdePrixHt = ligneCdePrixHt;
		this.ligneCdeTvaAppliquee = ligneCdeTvaAppliquee;
	}

	public LigneCommande() {
		super();
	}

	public String getLigneCdeId() {
		return ligneCdeId;
	}

	public void setLigneCdeId(String ligneCdeId) {
		this.ligneCdeId = ligneCdeId;
	}

	public String getCdeNum() {
		return cdeNum;
	}

	public void setCdeNum(String cdeNum) {
		this.cdeNum = cdeNum;
	}

	public String getLivreIsbn() {
		return livreIsbn;
	}

	public void setLivreIsbn(String livreIsbn) {
		this.livreIsbn = livreIsbn;
	}

	public float getLigneCdeQte() {
		return ligneCdeQte;
	}

	public void setLigneCdeQte(float ligneCdeQte) {
		this.ligneCdeQte = ligneCdeQte;
	}

	public float getLigneCdePrixHt() {
		return ligneCdePrixHt;
	}

	public void setLigneCdePrixHt(float ligneCdePrixHt) {
		this.ligneCdePrixHt = ligneCdePrixHt;
	}

	public float getLigneCdeTvaAppliquee() {
		return ligneCdeTvaAppliquee;
	}

	public void setLigneCdeTvaAppliquee(float ligneCdeTvaAppliquee) {
		this.ligneCdeTvaAppliquee = ligneCdeTvaAppliquee;
	}

	public float getLigneCdeRemise() {
		return ligneCdeRemise;
	}

	public void setLigneCdeRemise(float ligneCdeRemise) {
		this.ligneCdeRemise = ligneCdeRemise;
	}

	public String getClientLogin() {
		return clientLogin;
	}

	public void setClientLogin(String clientLogin) {
		this.clientLogin = clientLogin;
	}

	public String getCommentaireId() {
		return commentaireId;
	}

	public void setCommentaireId(String commentaireId) {
		this.commentaireId = commentaireId;
	}

	public String getEvenementId() {
		return evenementId;
	}

	public void setEvenementId(String evenementId) {
		this.evenementId = evenementId;
	}
	
}
