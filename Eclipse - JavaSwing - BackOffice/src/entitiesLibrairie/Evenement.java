package entitiesLibrairie;

import java.util.Date;

public class Evenement {

	private String evenementId;
	private String evenementNom;
	private Date evenementDateDebut;
	private Date evenementDateFin;
	private float evenementPourcentage;
	private String evenementCodePromo;
	private String evenementImage;
	private String evenementComment;

	public Evenement() {
		
	}
	
	public Evenement(String evenementNom, Date evenementDateDebut, Date evenementDateFin,
			float evenementPourcentage, String evenementCodePromo, String evenementImage, String evenementComment) {
		super();
		this.evenementNom = evenementNom;
		this.evenementDateDebut = evenementDateDebut;
		this.evenementDateFin = evenementDateFin;
		this.evenementPourcentage = evenementPourcentage;
		this.evenementCodePromo = evenementCodePromo;
		this.evenementImage = evenementImage;
		this.evenementComment = evenementComment;
	}
	
	public Evenement(String evenementId, String evenementNom, Date evenementDateDebut, Date evenementDateFin,
			float evenementPourcentage, String evenementCodePromo, String evenementImage, String evenementComment) {
		super();
		this.evenementId = evenementId;
		this.evenementNom = evenementNom;
		this.evenementDateDebut = evenementDateDebut;
		this.evenementDateFin = evenementDateFin;
		this.evenementPourcentage = evenementPourcentage;
		this.evenementCodePromo = evenementCodePromo;
		this.evenementImage = evenementImage;
		this.evenementComment = evenementComment;
	}


	public String getEvenementId() {
		return evenementId;
	}


	public void setEvenementId(String evenementId) {
		this.evenementId = evenementId;
	}


	public String getEvenementNom() {
		return evenementNom;
	}


	public void setEvenementNom(String evenementNom) {
		this.evenementNom = evenementNom;
	}


	public Date getEvenementDateDebut() {
		return evenementDateDebut;
	}


	public void setEvenementDateDebut(Date evenementDateDebut) {
		this.evenementDateDebut = evenementDateDebut;
	}


	public Date getEvenementDateFin() {
		return evenementDateFin;
	}


	public void setEvenementDateFin(Date evenementDateFin) {
		this.evenementDateFin = evenementDateFin;
	}


	public float getEvenementPourcentage() {
		return evenementPourcentage;
	}


	public void setEvenementPourcentage(float evenementPourcentage) {
		this.evenementPourcentage = evenementPourcentage;
	}


	public String getEvenementCodePromo() {
		return evenementCodePromo;
	}


	public void setEvenementCodePromo(String evenementCodePromo) {
		this.evenementCodePromo = evenementCodePromo;
	}


	public String getEvenementImage() {
		return evenementImage;
	}


	public void setEvenementImage(String evenementImage) {
		this.evenementImage = evenementImage;
	}


	public String getEvenementComment() {
		return evenementComment;
	}


	public void setEvenementComment(String evenementComment) {
		this.evenementComment = evenementComment;
	}

	@Override
	public String toString() {
		return "Evenement " + evenementNom + ", débutant le "
				+ evenementDateDebut + ", se cloturant le " + evenementDateFin + ".\nPourcentage appliqué :"
				+ evenementPourcentage + ", avec le code promo : " + evenementCodePromo ;
	}
	
	
	
	
	
	
}
