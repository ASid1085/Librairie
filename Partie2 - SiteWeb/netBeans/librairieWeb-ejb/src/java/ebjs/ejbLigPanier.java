
package ebjs;

import entities.LignePanier;
import entities.Panier;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbLigPanier implements ejbLigPanierLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public LignePanier addLigPanier ( LignePanier ligPanier) {
        em.merge( ligPanier);
        return ligPanier;
    }
    
//    @Override
//    public LignePanier updateLigPanier ( LignePanier ligPanier) {
//        em.merge( ligPanier);
//        return ligPanier;
//    }
    
    @Override
    public void deleteLigPanier( LignePanier ligPanier, String codePanier) {
        Collection<LignePanier> listLPs = listLPs( codePanier);
        for (LignePanier lp : listLPs) {
            if (ligPanier.getCode().equals(lp.getCode())) {
                em.remove( lp);
            }   
        }
    }
    
    @Override
    public Collection<LignePanier> listLPs(String codePanier) {
        Query query = em.createQuery( "SELECT lp FROM LignePanier lp JOIN lp.panier p WHERE p.code =\""+codePanier+"\"");
        return query.getResultList();
    }
    
    @Override
    public int nbItemLigPanier(String codePanier) {
        Query query = em.createQuery( "SELECT lp FROM LignePanier lp JOIN lp.panier p WHERE p.code =\""+codePanier+"\"");
        return query.getMaxResults();
    }
    
    @Override
    public void deleteAllLigPanier( String codePanier) {
        Collection<LignePanier> listLPs = listLPs( codePanier);
        for (LignePanier lp : listLPs) {
            em.remove( lp);
        }
    }
    
    @Override
    public LignePanier findLigPanier ( String code) {
        LignePanier lp = em.find(LignePanier.class, code);
        return lp;
    }
    
    @Override
    public LignePanier findLigPanierByIsbn( String isbn) {
        Query query = em.createQuery( "SELECT lp FROM LignePanier lp JOIN lp.livreAjout l WHERE l.isbn = \""+isbn+"\"");
        return (LignePanier) query.getSingleResult();
    }
    
    @Override
    public LignePanier findLigPanierByIsbnInPanier( String isbn, Panier panier) {
        Query query = em.createQuery( "SELECT lp FROM LignePanier lp JOIN lp.livreAjout l JOIN lp.panier p WHERE l.isbn = \""+ isbn +"\" AND p.code = \""+ panier.getCode() +"\"");
        System.out.println(" EJB 74 : ligne de panier n° :" + query.getSingleResult());
        return (LignePanier) query.getSingleResult();
    }
        
    @Override
    public boolean checkDoublonLigPanier(String code) {
        LignePanier ligPanier = em.find(LignePanier.class, code);
        if( ligPanier == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public boolean checkDoublonArticle(String isbn) {
        Query query = em.createQuery( "SELECT lp FROM LignePanier lp JOIN lp.livreAjout l WHERE l.isbn = \""+isbn+"\"");
        LignePanier lp = (LignePanier) query.getSingleResult();
        if ( lp == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public Collection<LignePanier> listLigPanierByPanier ( String codePanier) {
        Query query = em.createQuery( "SELECT lp FROM LignePanier lp JOIN lp.panier p WHERE p.code = \""+codePanier+"\"");
        return query.getResultList();
    }
    
    @Override
    public String createCodeLignePanier(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(l) FROM LignePanier l");
        long count = (long) query.getSingleResult()+1;
        if (count > 0 && count < 10){
            str = "0000"+count+"LIGP";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"LIGP";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"LIGP";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"LIGP";
        }
        return str;
    }
}
