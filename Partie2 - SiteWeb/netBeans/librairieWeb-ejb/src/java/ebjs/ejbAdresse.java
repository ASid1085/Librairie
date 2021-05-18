
package ebjs;

import entities.Adresse;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbAdresse implements ejbAdresseLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public boolean addAdresse( Adresse adresse) {
        if( adresse.getCivilite() == null) return false;
        if( adresse.getCivilite().trim().isEmpty()) return false;
        if( adresse.getNom() == null) return false;
        if( adresse.getNom().trim().isEmpty()) return false;
        if( adresse.getPrenom() == null) return false;
        if( adresse.getPrenom().trim().isEmpty()) return false;
        if( adresse.getNoRue() == null) return false;
        if( adresse.getNoRue().trim().isEmpty()) return false;
        if( adresse.getRue() == null) return false;
        if( adresse.getRue().trim().isEmpty()) return false;
        if( adresse.getCp() == null) return false;
        if( adresse.getCp().trim().isEmpty()) return false;
        if( adresse.getVille() == null) return false;
        if( adresse.getVille().trim().isEmpty()) return false;
        if( adresse.getPays() == null) return false;
        if( adresse.getPays().trim().isEmpty()) return false;
        em.persist( adresse);
        return true;
    }
    
    @Override
    public Adresse updateAdresse( Adresse adresse) {
        em.merge( adresse);
        return adresse;
    }
    
    
    @Override
    public void deleteAdresse( String code) {
       Collection<Adresse> listAdrs = listAdresses();
        for (Adresse a : listAdrs) {
            if (code.equals(a.getCode())) {
                em.remove( a);
            }   
        }
    }
    
    @Override
    public Adresse findAdresse( String code) {
        Adresse adresse = em.find(Adresse.class, code);
        return adresse;
    }
    
    @Override
    public Collection<Adresse> listAdresses() {
        Query query = em.createQuery( "SELECT a FROM Adresse a");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonAdresse(String code) {
        Adresse adresse = em.find(Adresse.class, code);
        if( adresse == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public String createCodeAdresse(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(a) FROM Adresse a");
        long count = (long) query.getSingleResult();
        if (count > 0 && count < 10){
            str = "0000"+count+"ADR";
        } else if (count >= 10 && count < 100) {
            str = "000"+count+"ADR";
        } else if (count >= 100 && count < 1000) {
            str = "00"+count+"ADR";
        } else if (count>=1000 && count < 10000) {
            str = "0"+count+"ADR";
        }
        return str;
    }
}
