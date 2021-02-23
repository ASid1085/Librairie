package entitiesLibrairie;

public class Theme {

	private String themeId;
	private String themeNom;
	
	public Theme() {
		
	};
	
	public Theme(String themeId, String themeNom) {
		super();
		this.themeId = themeId;
		this.themeNom = themeNom;
	}

	public Theme(String themeId) {
		super();
		this.themeId = themeId;
	}

	public String getThemeId() {
		return themeId;
	}

	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}

	public String getThemeNom() {
		return themeNom;
	}

	public void setThemeNom(String themeNom) {
		this.themeNom = themeNom;
	}


	
	
	
}
