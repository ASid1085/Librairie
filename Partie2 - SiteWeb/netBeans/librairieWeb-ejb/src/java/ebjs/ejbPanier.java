
package ebjs;

import entities.LignePanier;
import entities.Panier;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class ejbPanier implements ejbPanierLocal {

    @EJB
    private ejbLigPanierLocal ejbLigPanierLocal;

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;
     private HashMap<String, LignePanier> map = null;

    public ejbPanier() {
        map = new HashMap<>();
    }
     
    @Override
    public void add( LignePanier ligPanier) {
        add( ligPanier, +1);
    }
    
    @Override
    public void add( LignePanier ligPanier, String qte) {
        int i = Integer.valueOf( qte);
        add( ligPanier, i);
    }
    
    @Override
    public void add( LignePanier ligPanier, int qte) {
        if ( map.containsKey( ligPanier.getLivreAjout().getIsbn())) {
            LignePanier ligPan = map.get( ligPanier.getLivreAjout().getIsbn());
           ligPan.setQuantite( ligPan.getQuantite()+qte);
            if ( ligPan.getQuantite()<1) {
                del( ligPan);
            }  
        } else {
           map.put( ligPanier.getLivreAjout().getIsbn(), ligPanier);
        }   
    }
    
    @Override
    public void dec( LignePanier ligPanier) {
        add( ligPanier, -1);
    }
    
    @Override
    public void del( LignePanier ligPanier) {
        map.remove( ligPanier.getLivreAjout().getIsbn());
    }
    
    @Override
    public void clear() {
        map.clear();
    }
    
    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
    
    @Override
    public int nbItem() {
        return map.size();
    }
    
    @Override
    public LignePanier findInMap( String isbn) {
         LignePanier ligPan = map.get( isbn);
         return ligPan;
    }
           
    
    @Override    
    public Collection<LignePanier> listContenuPanier() {
        return map.values();
    }
    
    @Override
    public Panier addPanier( Panier panier) {
        em.persist( panier);
        return panier;
    }
    
    @Override
    public Panier updatePanier( Panier panier) {
        em.merge( panier);
        return panier;
    }
    
    @Override
    public void deletePanier( String code) {
        Panier panier = em.find( Panier.class, code);
        em.remove(panier);
    }
    
    @Override
    public boolean checkDoublonPanier(String code) {
        Panier panier = em.find( Panier.class, code);
        if( panier == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public Collection<Panier> listPaniers() {
        Query query = em.createQuery( "SELECT p FROM Panier p");
        return query.getResultList();
    }
    
    public Collection<LignePanier> listLigPaniersUser(String login) {
        Query query = em.createQuery( "SELECT lp FROM LignePanier lp WHERE lp.panier.code = \"" + login + "\"");
        return query.getResultList();
    }
    
    @Override
    public Panier findPanierByUser( String login) {
        Query query = em.createQuery( "SELECT p FROM Panier p JOIN p.panierDuClient pc WHERE pc.login = \""+login+"\"");
        return (Panier) query.getSingleResult();
    }
    
     @Override
    public Panier monPanier(String code) {
        return em.find( Panier.class, code);
    }
    
    @Override
    public String createCodePanier(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(p) FROM Panier p");
        long count = (long) query.getSingleResult()+1;
        if (count > 0 && count < 10){
            str = "0000"+count+"PAN";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"PAN";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"PAN";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"PAN";
        }
        return str;
    }
    
    @Override
    public float totalHtPanier() {
        Collection<LignePanier> panierCourant = listContenuPanier();
        float totalHt = 0;
        for (LignePanier lp : panierCourant) {
            totalHt += lp.getPrixHT()*lp.getQuantite();
        }
        return totalHt;
    }
    
    @Override
    public float totalHtPanierUser( String login) {
        Collection<LignePanier> panierUser = ejbLigPanierLocal.listLPs(login);
        float totalHt = 0;
        for (LignePanier lp : panierUser) {
            totalHt += lp.getPrixHT()*lp.getQuantite();
        }
        return totalHt;
    }
    
    @Override
    public float totalTvaPanier() {
        Collection<LignePanier> panierCourant = listContenuPanier();
        float totalTva = 0;
        for (LignePanier lp : panierCourant) {
            totalTva += lp.getMntTva()*lp.getQuantite();
        }
        return totalTva;
    }
    
    @Override
    public float totalTvaPanierUser(String login) {
        Collection<LignePanier> panierUser = ejbLigPanierLocal.listLPs(login);
        float totalTva = 0;
        for (LignePanier lp : panierUser) {
            totalTva += lp.getMntTva()*lp.getQuantite();
        }
        return totalTva;
    }
    
    @Override
    public float totalTtcPanier() {
        Collection<LignePanier> panierCourant = listContenuPanier();
        float totalTtc = 0;
        for (LignePanier lp : panierCourant) {
            totalTtc += lp.getMntTtc()*lp.getQuantite();
        }
        return totalTtc;
    }
    
    @Override
    public float totalTtcPanierUser( String login) {
        Collection<LignePanier> panierUser = ejbLigPanierLocal.listLPs(login);
        float totalTtc = 0;
        for (LignePanier lp : panierUser) {
            totalTtc += lp.getMntTtc()*lp.getQuantite();
        }
        return totalTtc;
    }
    
    @Override
    public String totalHtPanierAffichage() {
        return String.format("%.2f", totalHtPanier());
    }
    
    @Override
    public String totalHtPanierUserAffichage(String login) {
        return String.format("%.2f", totalHtPanierUser(login));
    }
    
    @Override
    public String totalTvaPanierAffichage() {
        return String.format("%.2f", totalTvaPanier());
    }
    
    @Override
     public String totalTvaPanierUserAffichage(String login) {
        return String.format("%.2f", totalTvaPanierUser(login));
    }
    
    @Override
    public String totalTtcPanierAffichage() {
        return String.format("%.2f", totalTtcPanier());
    }
    
    @Override
    public String totalTtcPanierUserAffichage(String login) {
        return String.format("%.2f", totalTtcPanierUser(login));
    }
}
