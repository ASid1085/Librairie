
package ebjs;

import entities.Evenement;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbEvenement implements ejbEvenementLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public Evenement addEvent(Evenement event) {
        em.persist( event);   
        return event;
    }
    
    @Override
     public Evenement updateEvent(Evenement event) {
        em.merge(event);
        return event;
    }
    
    @Override
    public Evenement findEvent(String code) {
        Evenement event = em.find(Evenement.class, code);
        return event;
    }
    
    @Override
    public Collection<Evenement> listEvents() {
        Query query = em.createQuery("SELECT e FROM Evenement e");
        return query.getResultList();
    }
    
    @Override
    public Collection<Evenement> findEventByName( String nom) {
        Query query = em.createQuery( "SELECT e FROM Evenement e WHERE e.nom = \""+nom+"\"");
        return query.getResultList();
    }
    
    @Override
    public String createCodeEvenement(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(e) FROM Evenement e");
        long count = (long) query.getSingleResult();
        if (count > 0 && count < 10){
            str = "0000"+count+"EVE";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"EVE";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"EVE";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"EVE";
        }
        return str;
    }
    
    @Override
    public boolean testCurrentEvenement() {
        Query query = em.createQuery( "SELECT e FROM Evenement e WHERE CURRENT_DATE > e.dateDebut AND CURRENT_DATE < e.dateFin");
        if( query.getResultList().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public Evenement currentEvenement() {
        Query query = em.createQuery( "SELECT e FROM Evenement e WHERE CURRENT_DATE > e.dateDebut AND CURRENT_DATE < e.dateFin");
        if( query.getResultList().isEmpty()) {
            return new Evenement();
        } else {
            return (Evenement) query.getSingleResult();
        }
    }
      

}
