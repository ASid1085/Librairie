package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Paiement implements Serializable {

    /*
    ** Variable de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String code;
    @Column(nullable = false)
    private String moyenPaiement;

    /*
    ** Getter et Setter de l'entity
    */
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setMoyenPaiement(String moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public String getMoyenPaiement() {
        return this.moyenPaiement;
    }

}
