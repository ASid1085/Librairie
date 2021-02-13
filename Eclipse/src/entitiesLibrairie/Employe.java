package entitiesLibrairie;

public class Employe {

	private String employeId;
	private String droitDaccesId;
	private String employeNom;
	private String employePrenom;
	private String employePoste;
	private String employeLog;
	private String employeMdp;
	private String droitsAcces;
	
	
	public Employe() {
	};
	
	

	public Employe(String employeLog, String employeMdp, String droitsAcces) {
		super();
		this.employeLog = employeLog;
		this.employeMdp = employeMdp;
		this.droitsAcces = droitsAcces;
	}


	


	public Employe(String droitsAcces, String employeNom, String employePrenom, String employePoste, String employeLog,
			String employeMdp) {
		super();
		this.droitsAcces = droitsAcces;
		this.employeNom = employeNom;
		this.employePrenom = employePrenom;
		this.employePoste = employePoste;
		this.employeLog = employeLog;
		this.employeMdp = employeMdp;
	}



	public Employe(String employeId, String droitDaccesId, String employeNom, String employePrenom, String employePoste,
			String employeLog, String employeMdp) {
		super();
		this.employeId = employeId;
		this.droitDaccesId = droitDaccesId;
		this.employeNom = employeNom;
		this.employePrenom = employePrenom;
		this.employePoste = employePoste;
		this.employeLog = employeLog;
		this.employeMdp = employeMdp;
	}


	public String getEmployeId() {
		return employeId;
	}


	public void setEmployeId(String employeId) {
		this.employeId = employeId;
	}


	public String getDroitDaccesId() {
		return droitDaccesId;
	}


	public void setDroitDaccesId(String droitDaccesId) {
		this.droitDaccesId = droitDaccesId;
	}


	public String getEmployeNom() {
		return employeNom;
	}


	public void setEmployeNom(String employeNom) {
		this.employeNom = employeNom;
	}


	public String getEmployePrenom() {
		return employePrenom;
	}


	public void setEmployePrenom(String employePrenom) {
		this.employePrenom = employePrenom;
	}


	public String getEmployePoste() {
		return employePoste;
	}


	public void setEmployePoste(String employePoste) {
		this.employePoste = employePoste;
	}


	public String getEmployeLog() {
		return employeLog;
	}


	public void setEmployeLog(String employeLog) {
		this.employeLog = employeLog;
	}


	public String getEmployeMdp() {
		return employeMdp;
	}


	public void setEmployeMdp(String employeMdp) {
		this.employeMdp = employeMdp;
	}




	public String getDroitsAcces() {
		return droitsAcces;
	}




	public void setDroitsAcces(String droitsAcces) {
		this.droitsAcces = droitsAcces;
	}



	@Override
	public String toString() {
		return "Employe : id = " + employeId + ", Nom = " + employeNom + ", Prenom = " + employePrenom
				+ ", Poste = " + employePoste + ", Log = " + employeLog + ", MDP = " + employeMdp
				+ ", droits d'acces = " + droitsAcces + "]";
	}
	
	
	
	
	
}
