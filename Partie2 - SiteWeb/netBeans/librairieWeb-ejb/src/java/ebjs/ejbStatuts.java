
package ebjs;

import entities.*;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ejbStatuts implements ejbStatutsLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public Statuts addStatut( Statuts statut) {
        em.persist( statut);
        return statut;
    }
    
    @Override
    public Statuts updateStatut( Statuts statut) {
        em.merge( statut);
        return statut;
    }
    
    @Override
    public Statuts findStatuts(String code) {
        Statuts statut = em.find(Statuts.class, code);
        return statut;
    }
    
    @Override
    public Collection<Statuts> listStatuts() {
        Query query = em.createQuery( "SELECT s FROM Statuts s");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonStatuts(String code) {
        Statuts statut = em.find(Statuts.class, code);
        if( statut == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public String createCodeStatuts(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(s) FROM Statuts s");
        long count = (long) query.getSingleResult();
        if (count > 0 && count < 10){
            str = "0000"+count+"STT";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"STT";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"STT";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"STT";
        }
        return str;
    }
}
