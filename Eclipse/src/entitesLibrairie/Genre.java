package entitesLibrairie;

public class Genre {

	private String genreId;
	private String genreNom;
	
	public Genre(String genreId, String genreNom) {
		super();
		this.genreId = genreId;
		this.genreNom = genreNom;
	}

	public Genre() {
		super();
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public String getGenreNom() {
		return genreNom;
	}

	public void setGenreNom(String genreNom) {
		this.genreNom = genreNom;
	}

	@Override
	public String toString() {
		return genreNom;
	}



}
