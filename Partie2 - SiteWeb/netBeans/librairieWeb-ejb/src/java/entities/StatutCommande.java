package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class StatutCommande implements Serializable {

    /*
    ** Variable de l'entity
     */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false, unique = false)
    private Date dateStatut;
    private String blabla;
    @OneToOne
    @JoinColumn(name = "statutCommande_fk", nullable = false)
    private Statuts statut;
    @OneToOne
    @JoinColumn(name = "commande_fk", nullable = false)
    private Commande Commande;

    /*
    ** Getter et Setter de l'entity
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setDateStatut(Date dateStatut) {
        this.dateStatut = dateStatut;
    }

    public Date getDateStatut() {
        return this.dateStatut;
    }

    public void setBlabla(String blabla) {
        this.blabla = blabla;
    }

    public String getBlabla() {
        return this.blabla;
    }

    public void setStatut(Statuts statut) {
        this.statut = statut;
    }

    public Statuts getStatut() {
        return this.statut;
    }

    public void setCommande(Commande Commande) {
        this.Commande = Commande;
    }

    public Commande getCommande() {
        return this.Commande;
    }

}
