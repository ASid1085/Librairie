package ebjs;

import entities.StatutCommande;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ejbStatutCommande implements ejbStatutCommandeLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public StatutCommande addStatutCommande( StatutCommande statutCde) {
        em.persist( statutCde);
        return statutCde;
    }
    
    @Override
    public StatutCommande updateStatutCommande( StatutCommande statutCde) {
        em.merge( statutCde);
        return statutCde;
    }
    
    @Override
    public StatutCommande findStatutCommande( String code) {
        StatutCommande statutCde = em.find(StatutCommande.class, code);
        return statutCde;
    }
    
    @Override
    public Collection<StatutCommande> listStatutsCommande() {
        Query query = em.createQuery( "SELECT sc FROM StatutCommande sc");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonStatutCommande( String code) {
        StatutCommande statutCde = em.find(StatutCommande.class, code);
        if( statutCde == null) {
            return false;
        } else {
            return true;
        }
    }
}

