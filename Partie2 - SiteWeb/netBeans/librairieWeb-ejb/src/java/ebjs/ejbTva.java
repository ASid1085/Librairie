
package ebjs;

import entities.*;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbTva implements ejbTvaLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public Tva addTva( Tva tva) {
        em.persist( tva);   
        return tva;
    }
    
    @Override
    public Tva upadateTva( Tva tva) {
        em.merge(tva);
        return tva;
    }
    
    @Override
    public Tva findTva( String code) {
        Tva tva = em.find(Tva.class, code);
        return tva;
    }
    
    @Override
    public Collection<Tva> listTva() {
        Query query = em.createQuery("SELECT t FROM Tva t");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonTva(String code) {
        Tva tva = em.find(Tva.class, code);
        if( tva == null) {
            return false;
        } else {
            return true;
        } 
    }
}
