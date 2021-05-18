
package ebjs;

import entities.Tva;
import java.util.Collection;
import javax.ejb.Local;


@Local
public interface ejbTvaLocal {

    public Tva addTva( Tva tva);

    public boolean checkDoublonTva(String code);

    public Tva findTva(String code);

    public Collection<Tva> listTva();

    public Tva upadateTva(Tva tva);
    
}
