
package ebjs;

import entities.LigneCommande;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ejbLigCommande implements ejbLigCommandeLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public LigneCommande addLigCommande ( LigneCommande ligCde) {
        em.persist( ligCde);
        return ligCde;
    }
    
    @Override
    public LigneCommande updateLigCommande ( LigneCommande ligCde) {
        em.merge( ligCde);
        return ligCde;
    }
    
    @Override
    public boolean checkDoublonLigCde(String code) {
        LigneCommande ligCde = em.find(LigneCommande.class, code);
        if( ligCde == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public Collection<LigneCommande> listLigCommandeByNumCde ( String numCde) {
        Query query = em.createQuery( "SELECT lc FROM LigneCommande lc JOIN lc.commande c WHERE c.numCommande = \""+numCde+"\"");
        return query.getResultList();
    }
    
    @Override
    public String createCodeLigneCommande(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(l) FROM LigneCommande l");
        long count = (long) query.getSingleResult()+1;
        if (count > 0 && count < 10){
            str = "0000"+count+"LIGC";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"LIGC";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"LIGC";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"LIGC";
        }
        return str;
    }
}
