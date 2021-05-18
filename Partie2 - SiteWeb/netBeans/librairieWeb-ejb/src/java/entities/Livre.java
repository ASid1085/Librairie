package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

@Entity
public class Livre implements Serializable {
    
    /*
    ** Variable de l'entity
    */
    @Id
    @Column(name = "ISBN", length = 14, nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String titre;
    private String sousTitre;
    @Column(nullable = false)
    private Float prixHT;
    private String image;
    @Column(length = 10000, nullable = false)
    private String resume;
    @Column(length = 8, nullable = false)
    private Integer nbrePage;
    @Column(nullable = false)
    private Integer stock;
    private String dateEdition;
    private String blabla;
    private String statut;
    @OneToOne
    @JoinColumn(name = "tvaLivre_fk", nullable = false)
    private Tva tvaLivre;
    @ManyToMany
    @JoinTable(name = "ATTRIBUER_MC", 
            joinColumns = {@JoinColumn(name = "isbn_fk", nullable = false, 
                        foreignKey = @ForeignKey(name = "livre_mc_fk", value = ConstraintMode.CONSTRAINT))},
            inverseJoinColumns = {@JoinColumn(name = "IdMotCle_fk", nullable = false)})
    private Collection<MotCle> ListMotCle;
    @ManyToMany
    @JoinTable(name = "AVOIR_THEME",
            joinColumns = {@JoinColumn(name = "isbn_fk", nullable = false, 
                        foreignKey = @ForeignKey(name = "livre_theme_fk", value = ConstraintMode.CONSTRAINT))},
            inverseJoinColumns = {@JoinColumn(name = "idTheme_fk", nullable = false)})
    private Collection<Theme> ListTheme;
    @OneToMany
    @JoinTable( name= "LIVRE_COMMENTAIRE",
                joinColumns = @JoinColumn(name = "isbn_fk"),
                inverseJoinColumns = @JoinColumn( name = "idCommentaire_fk"))
    private Collection<Commentaire> commentairesDuLivre;
    @ManyToMany
    @JoinTable(name = "ECRIT_PAR",
            joinColumns = { @JoinColumn(name = "isbn_fk", unique = false, nullable = false,
                        insertable = true, updatable = true,
                        foreignKey = @ForeignKey(name = "livre_auteur_fk", value = ConstraintMode.CONSTRAINT))},
            inverseJoinColumns = {@JoinColumn(name = "idAuteur_fk",
                        unique = false, nullable = false, insertable = true, updatable = true)})
    private Collection<Auteur> ListAuteur;
    @ManyToOne
    @JoinTable( name= "EDITE_PAR",
                joinColumns = @JoinColumn(name = "isbn_fk"),
                inverseJoinColumns = @JoinColumn( name = "idEditeur_fk"))
    private Editeur editeur;
    
    /*
    ** Getter et Setter de l'entity
    */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getSousTitre() {
        return this.sousTitre;
    }

    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    public Float getPrixHT() {
        return  this.prixHT;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getResume() {
        return this.resume;
    }

    public void setNbrePage(Integer nbrePage) {
        this.nbrePage = nbrePage;
    }

    public Integer getNbrePage() {
        return this.nbrePage;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setDateEdition(String dateEdition) {
        this.dateEdition = dateEdition;
    }

    public String getDateEdition() {
        return this.dateEdition;
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

    public void setListMotCle(Collection<MotCle> ListMotCle) {
        this.ListMotCle = ListMotCle;
    }

    public Collection<MotCle> getListMotCle() {
        return this.ListMotCle;
    }

    public void setListTheme(Collection<Theme> ListTheme) {
        this.ListTheme = ListTheme;
    }

    public Collection<Theme> getListTheme() {
        return this.ListTheme;
    }

    public void setTvaLivre(Tva tvaLivre) {
        this.tvaLivre = tvaLivre;
    }

    public Tva getTvaLivre() {
        return this.tvaLivre;
    }

    public void setCommentairesDuLivre(Collection<Commentaire> commentairesDuLivre) {
        this.commentairesDuLivre = commentairesDuLivre;
    }

    public Collection<Commentaire> getCommentairesDuLivre() {
        return this.commentairesDuLivre;
    }

    public void setListAuteur(Collection<Auteur> ListAuteur) {
        this.ListAuteur = ListAuteur;
    }

    public Collection<Auteur> getListAuteur() {
        return this.ListAuteur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public Editeur getEditeur() {
        return this.editeur;
    }
    
    /*
    ** Méthodes non natives de l'entity
    */
    public Float getCalculTTC() {
        return getPrixHT() * (1 + (getTvaLivre().getTaux()/100));
    }
    
    public String getPrixHtAffichage() {
        return String.format("%.2f", getPrixHT());
    }
    
     public String getPrixTtcAffichage() {
         return String.format("%.2f", getCalculTTC());
    }

}
