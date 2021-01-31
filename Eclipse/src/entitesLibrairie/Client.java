package entitesLibrairie;

public class Client {
	
	private String clientLogin;
	private String clientNom;
	private String clientPrenom;
	private String clientMdp;
	private String clientEmail;
	private String clientTel;
	private String clientStatuts;
	private String clientNotaBene;
	
	public Client() {
		super();
	}

	public Client(String clientLogin, String clientNom, String clientPrenom, String clientMdp, String clientEmail) {
		super();
		this.clientLogin = clientLogin;
		this.clientNom = clientNom;
		this.clientPrenom = clientPrenom;
		this.clientMdp = clientMdp;
		this.clientEmail = clientEmail;
	}

	public String getClientLogin() {
		return clientLogin;
	}

	public void setClientLogin(String clientLogin) {
		this.clientLogin = clientLogin;
	}

	public String getClientNom() {
		return clientNom;
	}

	public void setClientNom(String clientNom) {
		this.clientNom = clientNom;
	}

	public String getClientPrenom() {
		return clientPrenom;
	}

	public void setClientPrenom(String clientPrenom) {
		this.clientPrenom = clientPrenom;
	}

	public String getClientMdp() {
		return clientMdp;
	}

	public void setClientMdp(String clientMdp) {
		this.clientMdp = clientMdp;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientTel() {
		return clientTel;
	}

	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}

	public String getClientStatuts() {
		return clientStatuts;
	}

	public void setClientStatuts(String clientStatuts) {
		this.clientStatuts = clientStatuts;
	}

	public String getClientNotaBene() {
		return clientNotaBene;
	}

	public void setClientNotaBene(String clientNotaBene) {
		this.clientNotaBene = clientNotaBene;
	}

	@Override
	public String toString() {
		return clientLogin;
	}
	
	

}
