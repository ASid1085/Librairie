
package entitiesLibrairie;


public class Auteur {
    
     private String id;
     private String nom; 
     private String prenom;
     private String pseudo;

    public Auteur() {
    }
    
    public Auteur(String id, String nom, String prenom,  String pseudo){
        this.id= id;
        this. nom= nom;
        this.prenom= prenom;
        this.pseudo= pseudo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return "Auteur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", pseudo=" + pseudo + '}';
    }
     
     
    
}
