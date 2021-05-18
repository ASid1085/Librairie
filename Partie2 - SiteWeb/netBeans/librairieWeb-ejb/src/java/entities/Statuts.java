package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Statuts implements Serializable {
    
    /*
    ** Variable de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String code;
    @Column(nullable = false)
    private String categorie;
    @Column(nullable = false)
    private String libelle;
    
    /*
    ** Getter et Setter de l'entity
    */
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return this.libelle;
    }
    
    /*
    ** Méthodes non natives de l'entity
    */
    public String addCodeStatuts() {
        //TODO
        return "";
    }

}
