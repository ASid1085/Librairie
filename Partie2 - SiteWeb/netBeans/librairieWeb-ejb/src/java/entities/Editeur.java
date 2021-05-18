package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Editeur implements Serializable {
    
    /*
    ** Variable de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String code;
    private String nom;
    private String tel;
    private String mail;
    private String blabla;
    @OneToOne
    @JoinColumn(name = "adresse_fk")
    private Adresse AdresseEditeur;
    @OneToMany
    @JoinTable( name= "EDITE_PAR",
                joinColumns = @JoinColumn(name = "idEditeur_fk"),
                inverseJoinColumns = @JoinColumn( name = "isbn_fk"))
    //@JoinColumn(name = "listLivreEdite_fk")
    private Collection<Livre> ListLivre_editeur;
    
    /*
    ** Getter et Setter de l'entity
    */
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return this.tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return this.mail;
    }

    public void setBlabla(String blabla) {
        this.blabla = blabla;
    }

    public String getBlabla() {
        return this.blabla;
    }

    public void setAdresseEditeur(Adresse AdresseEditeur) {
        this.AdresseEditeur = AdresseEditeur;
    }

    public Adresse getAdresseEditeur() {
        return this.AdresseEditeur;
    }

    public void setListLivre_editeur(Collection<Livre> ListLivre_editeur) {
        this.ListLivre_editeur = ListLivre_editeur;
    }

    public Collection<Livre> getListLivre_editeur() {
        return this.ListLivre_editeur;
    }
    
}
