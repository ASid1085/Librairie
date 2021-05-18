package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Tva implements Serializable {
    
    /*
    ** Variable de l'entity
    */
    @Column(length = 10, nullable = false)
    @Id
    private String code;
    @Column(nullable = false, precision = 2)
    private Float taux;
    
    /*
    ** Getter et Setter de l'entity
    */
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setTaux(Float taux) {
        this.taux = taux;
    }

    public Float getTaux() {
        return this.taux;
    }
}
