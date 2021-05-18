package entities;

import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

@Entity
public class StatutCommentaire implements java.io.Serializable {
    
    /*
    ** Variable de l'entity
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    private Date dateStatut;
    private String blabla;
    @OneToOne
    @JoinColumn(name = "statut_fk", nullable = false)
    private Statuts statut;
     @OneToOne
    @JoinColumn(name = "commentaire_fk", nullable = false)
    private Commentaire commentaire;
    
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

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public Commentaire getCommentaire() {
        return this.commentaire;
    }

}
