package entitesLibrairie;

import java.util.Date;

public class Commande {

	private String cdeNum;
	private String clientLogin;
	private String cdePaiement;
	private String cdeForfaitLiv;
	private Date cdeDate;
	private String cdeNumFacture;
	private String tvaID;
	private String statutId;
	private String adresseIdF;
	private String adresseIdL;
	private String cdeIp;
	private String cdeNotaBene;
	private Date dateStatut;
	
	public Commande(String cdeNum, String clientLogin, Date cdeDate, String tvaID, String statutId, String adresseIdF,
			String adresseIdL, String cdeIp, Date dateStatut) {
		super();
		this.cdeNum = cdeNum;
		this.clientLogin = clientLogin;
		this.cdeDate = cdeDate;
		this.tvaID = tvaID;
		this.statutId = statutId;
		this.adresseIdF = adresseIdF;
		this.adresseIdL = adresseIdL;
		this.cdeIp = cdeIp;
		this.dateStatut = dateStatut;
	}

	public Commande() {
		super();
	}

	public String getCdeNum() {
		return cdeNum;
	}

	public void setCdeNum(String cdeNum) {
		this.cdeNum = cdeNum;
	}

	public String getClientLogin() {
		return clientLogin;
	}

	public void setClientLogin(String clientLogin) {
		this.clientLogin = clientLogin;
	}

	public String getCdePaiement() {
		return cdePaiement;
	}

	public void setCdePaiement(String cdePaiement) {
		this.cdePaiement = cdePaiement;
	}

	public String getCdeForfaitLiv() {
		return cdeForfaitLiv;
	}

	public void setCdeForfaitLiv(String cdeForfaitLiv) {
		this.cdeForfaitLiv = cdeForfaitLiv;
	}

	public Date getCdeDate() {
		return cdeDate;
	}

	public void setCdeDate(Date cdeDate) {
		this.cdeDate = cdeDate;
	}

	public String getCdeNumFacture() {
		return cdeNumFacture;
	}

	public void setCdeNumFacture(String cdeNumFacture) {
		this.cdeNumFacture = cdeNumFacture;
	}

	public String getTvaID() {
		return tvaID;
	}

	public void setTvaID(String tvaID) {
		this.tvaID = tvaID;
	}

	public String getStatutId() {
		return statutId;
	}

	public void setStatutId(String statutId) {
		this.statutId = statutId;
	}

	public String getAdresseIdF() {
		return adresseIdF;
	}

	public void setAdresseIdF(String adresseIdF) {
		this.adresseIdF = adresseIdF;
	}

	public String getAdresseIdL() {
		return adresseIdL;
	}

	public void setAdresseIdL(String adresseIdL) {
		this.adresseIdL = adresseIdL;
	}

	public String getCdeIp() {
		return cdeIp;
	}

	public void setCdeIp(String cdeIp) {
		this.cdeIp = cdeIp;
	}

	public String getCdeNotaBene() {
		return cdeNotaBene;
	}

	public void setCdeNotaBene(String cdeNotaBene) {
		this.cdeNotaBene = cdeNotaBene;
	}

	public Date getDateStatut() {
		return dateStatut;
	}

	public void setDateStatut(Date dateStatut) {
		this.dateStatut = dateStatut;
	}

	@Override
	public String toString() {
		return "Commande n° :" + cdeNum + " pour le client " + clientLogin;
	}
	
	
	
	
	
}
