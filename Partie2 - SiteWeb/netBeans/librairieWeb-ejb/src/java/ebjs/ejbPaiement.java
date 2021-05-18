
package ebjs;

import entities.*;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ejbPaiement implements ejbPaiementLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public Paiement addPaiement( Paiement paiement) {
        em.persist( paiement);
        return paiement;
    }
    
    @Override
    public Paiement updatePaiement( Paiement paiement) {
        em.merge( paiement);
        return paiement;
    }
    
    @Override
    public Paiement findPaiement( String code) {
        Paiement paiement = em.find(Paiement.class, code);
        return paiement;
    }
    
    @Override
    public Collection<Paiement> listPaiements() {
        Query query = em.createQuery( "SELECT p FROM Paiement p");
        return query.getResultList();
    }
            
    @Override
    public boolean checkDoublonPaiement( String code) {
        Paiement paiement = em.find(Paiement.class, code);
        if( paiement == null) {
            return false;
        } else {
            return true;
        } 
    }

}
