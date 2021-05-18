
package ebjs;

import entities.MotCle;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbMotCle implements ejbMotCleLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public MotCle addMotCle( MotCle motCle) {
        em.persist( motCle);
        return motCle;
    }
    
    @Override
    public MotCle updateMotCle ( MotCle motCle) {
        em.merge( motCle);
        return motCle;
    }
    
    @Override
    public MotCle findMotCle( String code) {
        MotCle motCle = em.find(MotCle.class, code);
        return motCle;
    }
    
    @Override
    public Collection<MotCle> listMotCles() {
        Query query = em.createQuery( "SELECT mc FROM MotCle mc");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonMotCle(String code) {
        MotCle motCle = em.find(MotCle.class, code);
        if( motCle == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public String createCodeMotCle(){
        String str = "";
        Query query = em.createQuery("SELECT COUNT(m) FROM MotCle m");
        long count = (long) query.getSingleResult();
        if (count > 0 && count < 10){
            str = "0000"+count+"MOT";
        } else if (count >= 10 && count < 100){
            str = "000"+count+"MOT";
        } else if (count >= 100 && count < 1000){
            str = "00"+count+"MOT";
        } else if (count>=1000 && count <10000){
            str = "0"+count+"MOT";
        }
        return str;
    }

}
