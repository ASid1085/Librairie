
package ebjs;

import entities.Editeur;
import entities.Livre;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbEditeur implements ejbEditeurLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public Editeur addEditeur( Editeur editeur) {
        em.persist( editeur);
        return editeur;
    }
    
    @Override
    public Editeur updateEditeur( Editeur editeur) {
        em.merge( editeur);
        return editeur;
    }
    
    @Override
    public Editeur findEditeur( String code) {
        Editeur editeur = em.find(Editeur.class, code);
        return editeur;
    }
    
    @Override
    public Collection<Editeur> listEditeurs() {
        Query query = em.createQuery( "SELECT e FROM Editeur e");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonEditeur(String code) {
        Editeur editeur = em.find(Editeur.class, code);
        if( editeur == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public String createCodeEditeur(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(e) FROM Editeur e");
        long count = (long) query.getSingleResult();
        if (count > 0 && count < 10){
            str = "0000"+count+"EDI";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"EDI";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"EDI";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"EDI";
        }
        return str;
    }

}
