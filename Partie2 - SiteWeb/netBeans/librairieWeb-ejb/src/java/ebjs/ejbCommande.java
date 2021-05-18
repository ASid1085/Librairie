
package ebjs;

import entities.Commande;
import entities.LigneCommande;
import entities.Livre;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbCommande implements ejbCommandeLocal {

    @EJB
    private ejbLigCommandeLocal ejbLigCommandeLocal;

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;
    
    @Override
    public Commande addCommande( Commande commande) {
        em.persist( commande);
        return commande;
    }
    
    @Override
    public Commande updateCommande ( Commande commande) {
        em.merge( commande);
        return commande;
    }
    
    @Override
    public Commande findCommande ( String numCde) {
        Commande cde = em.find( Commande.class, numCde);
        return cde;
    }
    
    @Override
    public boolean checkDoublonCommande(String numCde) {
        Commande cde = em.find( Commande.class, numCde);
        if( cde == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public Collection<Commande> listCommandes() {
        Query query = em.createQuery( "SELECT c FROM Commande c");
        return query.getResultList();
    }
    
    @Override
    public Collection<Commande> findCommandeByUser( String login) {
        Query query = em.createQuery( "SELECT c FROM Commande c JOIN c.client cl WHERE cl.login = \""+login+"\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<LigneCommande> listLigCommandeByNumCde ( String numCde) {
        Query query = em.createQuery( "SELECT lc FROM LigneCommande lc JOIN lc.commande c WHERE c.numCommande = \""+numCde+"\"");
        return query.getResultList();
    } 
    
    @Override
    public Collection<LigneCommande> listLigCommandeByUser( String login) {
        Query query = em.createQuery( "SELECT lc FROM LigneCommande lc JOIN lc.commande c WHERE c.client.login = \""+login+"\"");
        return query.getResultList();
    } 

    @Override
    public Collection<Commande> findCommandeByStatut( String statut) {
        Query query = em.createQuery( "SELECT c FROM Commande c JOIN c.listStatutCommande lsc WHERE lsc.statut = \""+statut+"\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Commande> findCommandeByDate ( String date) {
        Query query = em.createQuery( "SELECT c FROM Commande c WHERE c.dateCommande = = \""+date+"\"");
        return query.getResultList();
    }
    
    
    @Override
    public long createNum() {
        Query query = em.createQuery("SELECT COUNT(c) FROM Commande c");
        return (long) query.getSingleResult();
    }
    
    @Override
    public String createNumCommande(){
        String numCde = "";
        long count = createNum()+1;
        if (count > 0 && count < 10){
            numCde = "0000"+count+"CMD";
        } else if (count >= 10 && count < 100){
            numCde = "000"+count+"CMD";
        } else if (count >= 100 && count < 1000){
            numCde = "00"+count+"CMD";
        } else if (count>=1000 && count <10000){
            numCde = "0"+count+"CMD";
        }
        return numCde;
    }
    
    @Override
    public String createNumFacture(){
        String numFact = "";
        long count = createNum() +1;
        if (count > 0 && count < 10){
            numFact = "0000"+count+"FAC";
        } else if (count >= 10 && count < 100){
            numFact = "000"+count+"FAC";
        } else if (count >= 100 && count < 1000){
            numFact = "00"+count+"FAC";
        } else if (count>=1000 && count <10000){
            numFact = "0"+count+"FAC";
        }
        return numFact;
    }
}
