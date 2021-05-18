package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class LigneCommande implements Serializable {

    /*
    ** Variable de l'entity
    */
    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    @Id
    private String code;
    @Column(length = 4, nullable = false)
    private Integer quantite;
    @Column(length = 10, nullable = false, precision = 2)
    private Float prixHT;
    private Float remise;
    @Column(length = 4, nullable = false, precision = 2)
    private Float tvaAppliquee;
    @OneToOne
    @JoinColumn(name = "evenement_fk")
    private Evenement evenement;
    @OneToOne
    @JoinColumn(name = "livreCommande_fk", nullable = false)
    private Livre livreCommande;
    @ManyToOne
    @JoinTable( name= "DETAIL_DE_COMMANDE",
                joinColumns = @JoinColumn(name = "idLigCommande_fk"),
                inverseJoinColumns = @JoinColumn( name = "idCommande_fk"))
    private Commande commande;

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

    public void setRemise(Float remise) {
        this.remise = remise;
    }

    public Float getRemise() {
        return this.remise;
    }

    public void setTvaAppliquee(Float tvaAppliquee) {
        this.tvaAppliquee = tvaAppliquee;
    }

    public Float getTvaAppliquee() {
        return this.tvaAppliquee;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Evenement getEvenement() {
        return this.evenement;
    }

    public void setLivreCommande(Livre livreCommande) {
        this.livreCommande = livreCommande;
    }

    public Livre getLivreCommande() {
        return this.livreCommande;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    
    /*
    ** Méthodes non natives de l'entity
    */
    public float remisePourcentage() {
        return getRemise()/100;
    }
    
    public float getTvaAppliqueePourcentage() {
        return getTvaAppliquee()/100;
    }
    
    public float getMntHt() {
        return getPrixHT() * getQuantite();
    }
    
    public String getMntTotalHt() {
        return String.format("%.2f", getPrixHT() * getQuantite());
    }
    
    public float getMntTva() {
        return getPrixHT() * getTvaAppliqueePourcentage();
    }
    
    public float getMntTotalTva() {
        return getPrixHT() * getQuantite() * getTvaAppliqueePourcentage();
    }
    
    public String getMntTotalTvaAff() {
        return String.format("%.2f", getPrixHT() * getQuantite() * getTvaAppliqueePourcentage());
    }
    
    public float getMntTtc() {
        return getPrixHT() * (1 + getTvaAppliqueePourcentage());
    }
    
    public float getMntTotalTtc() {
        return getPrixHT() * getQuantite() * (1 + getTvaAppliqueePourcentage());
    }
     public String getMntTotalTtcAff() {
        return String.format("%.2f", getPrixHT() * getQuantite() * (1 + getTvaAppliqueePourcentage()));
    }
    
     public float getMntTotalHtR() {
        return  getPrixHT() * (1-remisePourcentage()) * getQuantite();
    }
     
     public float getMntTotalTvaR() {
        return getPrixHT() * (1-remisePourcentage()) * getQuantite() * getTvaAppliqueePourcentage();
    }
     
     public float getMntTotalTtcR() {
        return getMntTotalHtR() + getMntTotalTvaR();
    }
     
}
