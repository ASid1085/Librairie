package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class MotCle implements Serializable {

    /*
    ** Variable de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String code;
    @Column(nullable = false)
    private String nom;
    @ManyToMany(mappedBy = "ListMotCle")
    private Collection<Livre> ListLivre_MC;

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

    public void setListLivre_MC(Collection<Livre> ListLivre_MC) {
        this.ListLivre_MC = ListLivre_MC;
    }

    public Collection<Livre> getListLivre_MC() {
        return this.ListLivre_MC;
    }
    
}
