package entitiesLibrairie;

public class Adresse {
	
	private String adresseId;
	private String adresseCode;
	private String adresseNom;
	private String adressePrenom;
	private String adresseNoRue;
	private String adresseRue;
	private String adresseCompl;
	private String adresseCp;
	private String adresseVille;
	private String adressePays;
	private String adresseTel;
	private String adresseSociete;
	
	public Adresse() {
		super();
	}
	
	public Adresse(String adresseId, String adresseCode, String adresseNom, String adressePrenom, String adresseNoRue,
			String adresseRue, String adresseCompl, String adresseCp, String adresseVille, String adressePays,
			String adresseTel, String adresseSociete) {
		super();
		this.adresseId = adresseId;
		this.adresseCode = adresseCode;
		this.adresseNom = adresseNom;
		this.adressePrenom = adressePrenom;
		this.adresseNoRue = adresseNoRue;
		this.adresseRue = adresseRue;
		this.adresseCompl = adresseCompl;
		this.adresseCp = adresseCp;
		this.adresseVille = adresseVille;
		this.adressePays = adressePays;
		this.adresseTel = adresseTel;
		this.adresseSociete = adresseSociete;
	}
	
	public Adresse(String adresseId, String adresseNom, String adressePrenom, String adresseNoRue, String adresseRue,
			String adresseCompl, String adresseCp, String adresseVille, String adresseTel) {
		super();
		this.adresseId = adresseId;
		this.adresseNom = adresseNom;
		this.adressePrenom = adressePrenom;
		this.adresseNoRue = adresseNoRue;
		this.adresseRue = adresseRue;
		this.adresseCompl = adresseCompl;
		this.adresseCp = adresseCp;
		this.adresseVille = adresseVille;
		this.adresseTel = adresseTel;
	}
	
	public Adresse(String adresseNom, String adressePrenom, String adresseNoRue, String adresseRue,
			String adresseCompl, String adresseCp, String adresseVille, String adresseTel) {
		super();
		this.adresseNom = adresseNom;
		this.adressePrenom = adressePrenom;
		this.adresseNoRue = adresseNoRue;
		this.adresseRue = adresseRue;
		this.adresseCompl = adresseCompl;
		this.adresseCp = adresseCp;
		this.adresseVille = adresseVille;
		this.adresseTel = adresseTel;
	}

	public String getAdresseId() {
		return adresseId;
	}

	public void setAdresseId(String adresseId) {
		this.adresseId = adresseId;
	}

	public String getAdresseCode() {
		return adresseCode;
	}

	public void setAdresseCode(String adresseCode) {
		this.adresseCode = adresseCode;
	}

	public String getAdresseNom() {
		return adresseNom;
	}

	public void setAdresseNom(String adresseNom) {
		this.adresseNom = adresseNom;
	}

	public String getAdressePrenom() {
		return adressePrenom;
	}

	public void setAdressePrenom(String adressePrenom) {
		this.adressePrenom = adressePrenom;
	}

	public String getAdresseNoRue() {
		return adresseNoRue;
	}

	public void setAdresseNoRue(String adresseNoRue) {
		this.adresseNoRue = adresseNoRue;
	}

	public String getAdresseRue() {
		return adresseRue;
	}

	public void setAdresseRue(String adresseRue) {
		this.adresseRue = adresseRue;
	}

	public String getAdresseCompl() {
		return adresseCompl;
	}

	public void setAdresseCompl(String adresseCompl) {
		this.adresseCompl = adresseCompl;
	}

	public String getAdresseCp() {
		return adresseCp;
	}

	public void setAdresseCp(String adresseCp) {
		this.adresseCp = adresseCp;
	}

	public String getAdresseVille() {
		return adresseVille;
	}

	public void setAdresseVille(String adresseVille) {
		this.adresseVille = adresseVille;
	}

	public String getAdressePays() {
		return adressePays;
	}

	public void setAdressePays(String adressePays) {
		this.adressePays = adressePays;
	}

	public String getAdresseTel() {
		return adresseTel;
	}

	public void setAdresseTel(String adresseTel) {
		this.adresseTel = adresseTel;
	}

	public String getAdresseSociete() {
		return adresseSociete;
	}

	public void setAdresseSociete(String adresseSociete) {
		this.adresseSociete = adresseSociete;
	}

}
