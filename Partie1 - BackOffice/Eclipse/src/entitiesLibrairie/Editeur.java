package entitiesLibrairie;

public class Editeur {
	
	private String editeurId;
	private String editeurNom;
	private String editeurAdresse;
	private String editeurTel;
	private String editeurMail;
	private String editeurContact;
	private String editeurComment;
	
	public Editeur() {
		super();
	}

	public Editeur(String editeurId, String editeurNom, String editeurAdresse, String editeurTel, String editeurMail,
			String editeurContact, String editeurComment) {
		super();
		this.editeurId = editeurId;
		this.editeurNom = editeurNom;
		this.editeurAdresse = editeurAdresse;
		this.editeurTel = editeurTel;
		this.editeurMail = editeurMail;
		this.editeurContact = editeurContact;
		this.editeurComment = editeurComment;
	}

	public Editeur(String editeurId, String editeurNom, String editeurAdresse, String editeurTel, String editeurMail,
			String editeurContact) {
		super();
		this.editeurId = editeurId;
		this.editeurNom = editeurNom;
		this.editeurAdresse = editeurAdresse;
		this.editeurTel = editeurTel;
		this.editeurMail = editeurMail;
		this.editeurContact = editeurContact;
	}

	public Editeur(String editeurId, String editeurNom) {
		super();
		this.editeurId = editeurId;
		this.editeurNom = editeurNom;
	}

	public String getEditeurId() {
		return editeurId;
	}

	public void setEditeurId(String editeurId) {
		this.editeurId = editeurId;
	}

	public String getEditeurNom() {
		return editeurNom;
	}

	public void setEditeurNom(String editeurNom) {
		this.editeurNom = editeurNom;
	}

	public String getEditeurAdresse() {
		return editeurAdresse;
	}

	public void setEditeurAdresse(String editeurAdresse) {
		this.editeurAdresse = editeurAdresse;
	}

	public String getEditeurTel() {
		return editeurTel;
	}

	public void setEditeurTel(String editeurTel) {
		this.editeurTel = editeurTel;
	}

	public String getEditeurMail() {
		return editeurMail;
	}

	public void setEditeurMail(String editeurMail) {
		this.editeurMail = editeurMail;
	}

	public String getEditeurContact() {
		return editeurContact;
	}

	public void setEditeurContact(String editeurContact) {
		this.editeurContact = editeurContact;
	}

	public String getEditeurComment() {
		return editeurComment;
	}

	public void setEditeurComment(String editeurComment) {
		this.editeurComment = editeurComment;
	}

}
