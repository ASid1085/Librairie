package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Auteur implements Serializable {

    /*
    ** Variable de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String code;
    @Column(length = 75, nullable = false)
    private String nom;
    @Column(length = 75, nullable = false)
    private String prenom;
    private String pseudo;
    @ManyToMany(mappedBy = "ListAuteur", cascade = {CascadeType.ALL})
    private Collection<Livre> ListLivre_Auteur;
    
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
        return this.nom.toUpperCase();
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setListLivre_Auteur(Collection<Livre> ListLivre_Auteur) {
        this.ListLivre_Auteur = ListLivre_Auteur;
    }

    public Collection<Livre> getListLivre_Auteur() {
        return this.ListLivre_Auteur;
    }
}
