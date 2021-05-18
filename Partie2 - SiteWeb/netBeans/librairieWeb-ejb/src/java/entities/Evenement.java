package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Evenement implements Serializable {

    /*
    ** Variable de l'entity
    */
    @Column(nullable = false)
    @Id
    private String code;
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Column(length = 4, precision = 2)
    private Float pourcentage;
    private String codePromo;
    private String image;
    private String blabla;
    
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

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateDebut() {
        return this.dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateFin() {
        return this.dateFin;
    }

    public void setPourcentage(Float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Float getPourcentage() {
        return this.pourcentage;
    }

    public void setCodePromo(String codePromo) {
        this.codePromo = codePromo;
    }

    public String getCodePromo() {
        return this.codePromo;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public void setBlabla(String blabla) {
        this.blabla = blabla;
    }

    public String getBlabla() {
        return this.blabla;
    }
    
    /*
    ** Méthodes non natives de l'entity
    */
    public boolean courentEvenement() {
        Date d = new Date();
        long today = d.getTime() - getDateDebut().getTime();
        int days = (int) ( today / ( 1000*60*60*24));
        
        if ( d.getTime() > getDateFin().getTime()) {
            return false;
        } else if ( days < 0) {
            return false;
        } else {
            return true;
        }
    }
    
     public float calculPourcentage() {
        return getPourcentage()/100;
    }
     
     public String getPourcentageAffichage() {
         return String.format("%.0f", getPourcentage());
    }

}
