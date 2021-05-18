package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Client implements Serializable {

    /*
    ** Variable de l'entity
    */
    @Id
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String mdp;
    @Column(length = 15, nullable = false)
    private String civilite;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(length = 75, nullable = false)
    private String nom;
    @Column(length = 75, nullable = false)
    private String prenom;
    @Column(length = 20)
    private String tel;
    @Column(length = 300)
    private String blabla;
    private String statut;
    @OneToMany
    @JoinTable( name= "COMMENTAIRE_DU_CLIENT",
                joinColumns = @JoinColumn(name = "login_fk"),
                inverseJoinColumns = @JoinColumn( name = "laisserCommentaire_fk"))
    private Collection<Commentaire> commentairePoste;
    @OneToMany
    @JoinTable( name= "ADRESSES_DU_CLIENT",
                joinColumns = @JoinColumn(name = "login_fk"),
                inverseJoinColumns = @JoinColumn( name = "idAdresse_fk"))
    private Collection<Adresse> adressesClient;
    @OneToMany
    @JoinTable( name= "CLIENT_COMMANDE",
                joinColumns = @JoinColumn(name = "login_fk"),
                inverseJoinColumns = @JoinColumn( name = "client_fk"))
    private Collection<Commande> ListCommandeClient;

    /*
    ** Getter et Setter de l'entity
    */
    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMdp() {
        return this.mdp;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getCivilite() {
        return this.civilite;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
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

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return this.tel;
    }

    public void setBlabla(String blabla) {
        this.blabla = blabla;
    }

    public String getBlabla() {
        return this.blabla;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setCommentairePoste(Collection<Commentaire> commentairePoste) {
        this.commentairePoste = commentairePoste;
    }

    public Collection<Commentaire> getCommentairePoste() {
        return this.commentairePoste;
    }

    public void setAdressesClient(Collection<Adresse> adressesClient) {
        this.adressesClient = adressesClient;
    }

    public Collection<Adresse> getAdressesClient() {
        return this.adressesClient;
    }

    public void setListCommandeClient(Collection<Commande> ListCommandeClient) {
        this.ListCommandeClient = ListCommandeClient;
    }

    public Collection<Commande> getListCommandeClient() {
        return this.ListCommandeClient;
    }

}
