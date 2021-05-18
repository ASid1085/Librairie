
package ebjs;

import entities.Adresse;
import java.util.Collection;
import javax.ejb.Local;


@Local
public interface ejbAdresseLocal {

    public boolean addAdresse(Adresse adresse);

    public Adresse findAdresse(String code);

    public boolean checkDoublonAdresse(String code);

    public Collection<Adresse> listAdresses();

    public Adresse updateAdresse(Adresse adresse);

    public String createCodeAdresse();

    public void deleteAdresse(String code);
    
}
