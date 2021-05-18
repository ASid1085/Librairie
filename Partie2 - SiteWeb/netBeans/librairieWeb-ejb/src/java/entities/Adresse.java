package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Adresse implements Serializable {

    /*
    ** Variable de l'entity
    */
    @Column(nullable = false)
    @Id
    private String code;
    private String libelle;
    @Column(length = 75, nullable = false)
    private String nom;
    @Column(length = 75, nullable = false)
    private String prenom;
    @Column(length = 75)
    private String nomEntreprise;
    @Column(length = 10, nullable = false)
    private String noRue;
    @Column(length = 80, nullable = false)
    private String rue;
    private String complement;
    @Column(length = 20, nullable = false)
    private String cp;
    @Column(length = 75, nullable = false)
    private String ville;
    @Column(length = 75, nullable = false)
    private String pays;
    private String tel;
    @Column(length = 15, nullable = false)
    private String civilite;
    @ManyToOne
    @JoinTable( name= "ADRESSES_DU_CLIENT",
                joinColumns = @JoinColumn(name = "idAdresse_fk"),
                inverseJoinColumns = @JoinColumn( name = "login_fk"))
    private Client client;
    
    /*
    ** Getter et Setter de l'entity
    */
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getNomEntreprise() {
        return this.nomEntreprise;
    }

    public void setNoRue(String noRue) {
        this.noRue = noRue;
    }

    public String getNoRue() {
        return this.noRue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getRue() {
        return this.rue;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getComplement() {
        return this.complement;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCp() {
        return this.cp;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getVille() {
        return this.ville;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPays() {
        return this.pays;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return this.tel;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getCivilite() {
        return this.civilite;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
}
