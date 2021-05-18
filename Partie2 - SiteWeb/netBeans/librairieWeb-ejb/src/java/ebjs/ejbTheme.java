package ebjs;

import entities.Theme;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ejbTheme implements ejbThemeLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public Theme addTheme( Theme theme) {
        em.persist( theme);
        return theme;
    }
    
    @Override
    public Theme updateTheme( Theme theme) {
        em.merge( theme);
        return theme;
    }
    
    @Override
    public Theme findTheme( String code) {
        Theme theme = em.find(Theme.class, code);
        return theme;
    }
    
    @Override
    public Collection<Theme> listThemes() {
        Query query = em.createQuery( "SELECT t FROM Theme t ORDER BY t.nom");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonTheme(String code) {
        Theme theme = em.find(Theme.class, code);
        if( theme == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String createCodeTheme(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(t) FROM Theme t");
        long count = (long) query.getSingleResult();
        if (count > 0 && count < 10){
            str = "0000"+count+"THE";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"THE";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"THE";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"THE";
        }
        return str;
    }
}
