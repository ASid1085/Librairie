package entitiesLibrairie;

public class MotCle {
	
	private String motCleId;
	private String motCleLibelle;
	
	public MotCle() {
		super();
	}

	public MotCle(String motCleId, String motCleLibelle) {
		super();
		this.motCleId = motCleId;
		this.motCleLibelle = motCleLibelle;
	}

	public String getMotCleId() {
		return motCleId;
	}

	public void setMotCleId(String motCleId) {
		this.motCleId = motCleId;
	}

	public String getMotCleLibelle() {
		return motCleLibelle;
	}

	public void setMotCleLibelle(String motCleLibelle) {
		this.motCleLibelle = motCleLibelle;
	}
	
}
