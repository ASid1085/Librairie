package entitiesLibrairie;

public class Auteur {
	
	private String auteurId;
	private String auteurNom;
	private String auteurPrenom;
	private String auteurPseudo;
	
	public Auteur() {
		super();
	}

	public Auteur(String auteurId, String auteurNom, String auteurPrenom, String auteurPseudo) {
		super();
		this.auteurId = auteurId;
		this.auteurNom = auteurNom;
		this.auteurPrenom = auteurPrenom;
		this.auteurPseudo = auteurPseudo;
	}

	public String getAuteurId() {
		return auteurId;
	}

	public void setAuteurId(String auteurId) {
		this.auteurId = auteurId;
	}

	public String getAuteurNom() {
		return auteurNom;
	}

	public void setAuteurNom(String auteurNom) {
		this.auteurNom = auteurNom;
	}

	public String getAuteurPrenom() {
		return auteurPrenom;
	}

	public void setAuteurPrenom(String auteurPrenom) {
		this.auteurPrenom = auteurPrenom;
	}

	public String getAuteurPseudo() {
		return auteurPseudo;
	}

	public void setAuteurPseudo(String auteurPseudo) {
		this.auteurPseudo = auteurPseudo;
	}

}
