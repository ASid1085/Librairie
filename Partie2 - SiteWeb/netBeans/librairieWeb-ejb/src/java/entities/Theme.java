package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Theme implements Serializable {
    
    /*
    ** Variable de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String code;
    @Column(nullable = false)
    private String nom;
    @ManyToMany( mappedBy = "ListTheme")
    private Collection<Livre> ListLivre_Theme;
    @ManyToOne
    @JoinTable( name="DECOULE",
                joinColumns = @JoinColumn(name = "genreDuTheme_fk"),
                inverseJoinColumns = @JoinColumn( name = "listTheme_fk"))
    private Genre genreduTheme;
    
    /*
    ** Getter et Setter de l'entity
    */
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setListLivre_Theme(Collection<Livre> ListLivre_Theme) {
        this.ListLivre_Theme = ListLivre_Theme;
    }

    public Collection<Livre> getListLivre_Theme() {
        return this.ListLivre_Theme;
    }

    public void setGenreduTheme(Genre genreduTheme) {
        this.genreduTheme = genreduTheme;
    }

    public Genre getGenreduTheme() {
        return this.genreduTheme;
    }

}
