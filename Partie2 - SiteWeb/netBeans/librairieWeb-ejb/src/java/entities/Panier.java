package entities;

import ebjs.ejbEvenement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Panier implements Serializable {
    
    /*
    ** Variable de l'entity
    */
    @Column(length = 255, nullable = false)
    @Id
    private String code;
    @Column(nullable = false)
    private Date datePanier;
     @OneToMany
    @JoinTable( name= "DETAIL_DU_PANIER",
                joinColumns = @JoinColumn(name = "idPanier_fk"),
                inverseJoinColumns = @JoinColumn( name = "idLigPanier_fk"))
    private Collection<LignePanier> detailPanier;
    @OneToOne
    @JoinColumn( name = "panierDuClient_fk")
    private Client panierDuClient;

    /*
    ** Getter et Setter de l'entity
    */
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setdatePanier(Date datePanier) {
        this.datePanier = datePanier;
    }

    public Date getdatePanier() {
        return this.datePanier;
    }
    
    public void setDetailPanier(Collection<LignePanier> detailPanier) {
        this.detailPanier = detailPanier;
    }

    public Collection<LignePanier> getDetailPanier() {
        return this.detailPanier;
    }

    public void setPanierDuClient(Client panierDuClient) {
        this.panierDuClient = panierDuClient;
    }

    public Client getPanierDuClient() {
        return this.panierDuClient;
    }
    
}  