
package ebjs;

import entities.Auteur;
import entities.Livre;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbAuteur implements ejbAuteurLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public Auteur addAuteur( Auteur auteur) {
        em.persist( auteur);
        return auteur;
    }
    
    @Override
    public Auteur updateAuteur( Auteur auteur) {
        em.merge( auteur);
        return auteur;
    }
    
    @Override
    public Auteur findAuteur( String code) {
        Auteur auteur = em.find(Auteur.class, code);
        return auteur;
    }
    
    @Override
    public Auteur findAuteurByPseudo( String pseudo) {
        Query query = em.createQuery( "SELECT a FROM Auteur a WHERE PSEUDO = \""+pseudo+"\"");
        return (Auteur) query.getSingleResult();
    }
    
    @Override
    public Collection<Auteur> findAuteurByName( String nom) {
        Query query = em.createQuery( "SELECT a FROM Auteur a WHERE NOM = \""+nom+"\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Auteur> listAuteurs() {
        Query query = em.createQuery( "SELECT a FROM Auteur a");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonAuteur(String code) {
        Auteur auteur = em.find(Auteur.class, code);
        if( auteur == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public String createCodeAuteur(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(a) FROM Auteur a");
        long count = (long) query.getSingleResult();
        if (count > 0 && count < 10){
            str = "0000"+count+"AUT";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"AUT";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"AUT";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"AUT";
        }
        return str;
    }
    
}
