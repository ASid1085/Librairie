package entities;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Commentaire implements Serializable {

    /*
    ** Variable de l'entity
    */
    @Id
    @Column(nullable = false)
    private String code;
    @Column(length = 500, nullable = false)
    private String texte;
    @Column(length = 20, nullable = false)
    private String note;
    @Column(nullable = false)
    private String ip;
    private Date date;
    @OneToOne
    @JoinColumn(name = "laisserCommentaire_fk")
    private Client client;
    @OneToMany
    @JoinTable( name= "STATUT_DU_COMMENTAIRE",
                joinColumns = @JoinColumn(name = "listStatutCommentaire_fk"),
                inverseJoinColumns = @JoinColumn( name = "commentaire_fk"))
    private Collection<StatutCommentaire> ListStatutCommentaire;
    @ManyToOne
    @JoinTable( name= "LIVRE_COMMENTAIRE",
                joinColumns = @JoinColumn(name = "idCommentaire_fk"),
                inverseJoinColumns = @JoinColumn( name = "isbn_fk"))
    private Livre livre;

    /*
    ** Getter et Setter de l'entity
    */

    public Commentaire() {
        ListStatutCommentaire = new ArrayList<>();
    }
    
    
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getTexte() {
        return this.texte;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return this.note;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return this.ip;
    }

    public String getDateAff() {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(date);
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public void setListStatutCommentaire(Collection<StatutCommentaire> ListStatutCommentaire) {
        this.ListStatutCommentaire = ListStatutCommentaire;
    }

    public Collection<StatutCommentaire> getListStatutCommentaire() {
        return this.ListStatutCommentaire;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
    
    /*
    ** Méthodes non natives de l'entity
    */
    public String recupAdresseIp() {
        String adrIp = "";
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
            adrIp = ip.getHostAddress();
        } catch (UnknownHostException ex) {
            System.out.println( "oops: UnknownHostException: " + ex.getMessage());
        }
        return adrIp;
    }
    
    public int getRamdomAvatarComment() {
        if ( (int) (Math.random() * 8) < 1) {
            return 1;
        } else {
            return (int) (Math.random() * 8);
        }
    }

}
