
package ebjs;

import entities.StatutCommentaire;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbStatutCommentaire implements ejbStatutCommentaireLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public StatutCommentaire addStatutCommentaire( StatutCommentaire statutCmt) {
        em.persist( statutCmt);
        return statutCmt;
    }
    
    @Override
    public StatutCommentaire updateStatutCommentaire( StatutCommentaire statutCmt) {
        em.merge( statutCmt);
        return statutCmt;
    }
    
    @Override
    public StatutCommentaire findStatutCommentaire( String code) {
        StatutCommentaire statutCmt = em.find(StatutCommentaire.class, code);
        return statutCmt;
    }
    
    @Override
    public Collection<StatutCommentaire> listStatutsCommentaire() {
        Query query = em.createQuery( "SELECT sc FROM StatutCommande sc");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonStatutCommentaire( String code) {
        StatutCommentaire statutCmt = em.find(StatutCommentaire.class, code);
        if( statutCmt == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public String createCodeStatutCommentaire(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(s) FROM StatutCommentaire s");
        long count = (long) query.getSingleResult();
        if (count > 0 && count < 10){
            str = "0000"+count+"STCOM";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"STCOM";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"STCOM";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"STCOM";
        }
        return str;
    }
}
