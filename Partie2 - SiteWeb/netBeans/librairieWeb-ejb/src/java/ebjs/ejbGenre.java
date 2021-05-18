
package ebjs;

import entities.Genre;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbGenre implements ejbGenreLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;
    
    @Override
    public Genre addGenre( Genre genre) {
        em.persist( genre);
        return genre;
    }
    
    @Override
    public Genre updateGenre( Genre genre) {
        em.merge( genre);
        return genre;
    }
    
    @Override
    public Genre findGenre( String code) {
        Genre genre = em.find(Genre.class, code);
        return genre;
    }
    
    @Override
    public Collection<Genre> listGenres() {
        Query query = em.createQuery( "SELECT g FROM Genre g");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonGenre(String code) {
        Genre genre = em.find(Genre.class, code);
        if( genre == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public String createCodeGenre(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(g) FROM Genre g");
        long count = (long) query.getSingleResult();
        if (count > 0 && count < 10){
            str = "0000"+count+"GEN";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"GEN";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"GEN";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"GEN";
        }
        return str;
    }
    
    
}
