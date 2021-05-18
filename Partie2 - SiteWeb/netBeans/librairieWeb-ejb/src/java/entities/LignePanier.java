package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class LignePanier implements Serializable {

    /*
    ** Variable de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String code;
    @Column(nullable = false)
    private Integer quantite;
    @Column(nullable = false)
    private Float prixHT;
    @Column(length = 4, nullable = false, precision = 2)
    private Float tva;
    @OneToOne
    @JoinColumn(name = "livreAjout_fk", nullable = false)
    private Livre livreAjout;
    @ManyToOne
    @JoinTable( name= "DETAIL_DU_PANIER",
                joinColumns = @JoinColumn(name = "idLigPanier_fk"),
                inverseJoinColumns = @JoinColumn( name = "idPanier_fk"))
    private Panier panier;
    
    /*
    ** Getter et Setter de l'entity
    */
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Integer getQuantite() {
        return this.quantite;
    }

    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    public Float getPrixHT() {
        return this.prixHT;
    }

    public void setTva(Float tva) {
        this.tva = tva;
    }

    public Float getTva() {
        return this.tva;
    }

    public void setLivreAjout(Livre livreAjout) {
        this.livreAjout = livreAjout;
    }

    public Livre getLivreAjout() {
        return this.livreAjout;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
    
    /*
    ** Méthodes non natives de l'entity
    */
    public float getTvaPourcentage() {
        return getTva()/100;
    }
    
    public float getMntHt() {
        return getPrixHT() * getQuantite();
    }
    
    public String getMntTotalHt() {
        return String.format("%.2f", getPrixHT() * getQuantite());
    }
    
    public float getMntTva() {
        return getPrixHT() * getTvaPourcentage();
    }
    
    public String getMntTotalTva() {
        return String.format("%.2f", getPrixHT() * getQuantite() * getTvaPourcentage());
    }
    
    public float getMntTtc() {
        return getPrixHT() * (1 + getTvaPourcentage());
    }
    
    public String getMntTotalTtc() {
        return String.format("%.2f", getPrixHT() * getQuantite() * (1 + getTvaPourcentage()));
    }
   
}
