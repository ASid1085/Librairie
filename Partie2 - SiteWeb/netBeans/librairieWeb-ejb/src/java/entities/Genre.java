package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Genre implements Serializable {

    /*
    ** Variable de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String code;
    @Column(nullable = false)
    private String nom;
    @OneToMany
    @JoinTable( name= "DECOULE",
                joinColumns = @JoinColumn(name = "listTheme_fk"),
                inverseJoinColumns = @JoinColumn( name = "genreDuTheme_fk"))
    private Collection<Theme> ListTheme;

    
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

    public void setListTheme(Collection<Theme> ListTheme) {
        this.ListTheme = ListTheme;
    }

    public Collection<Theme> getListTheme() {
        return this.ListTheme;
    }
    
}
