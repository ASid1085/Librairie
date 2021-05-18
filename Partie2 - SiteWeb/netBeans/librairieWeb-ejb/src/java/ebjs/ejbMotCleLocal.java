
package ebjs;

import entities.MotCle;
import java.util.Collection;
import javax.ejb.Local;


@Local
public interface ejbMotCleLocal {

    public MotCle addMotCle(MotCle motCle);

    public MotCle findMotCle(String code);

    public boolean checkDoublonMotCle(String code);

    public MotCle updateMotCle(MotCle motCle);

    public Collection<MotCle> listMotCles();

    public String createCodeMotCle();
    
}
