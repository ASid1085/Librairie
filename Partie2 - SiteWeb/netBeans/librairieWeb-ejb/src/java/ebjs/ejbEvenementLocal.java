
package ebjs;

import entities.Evenement;
import java.util.Collection;
import javax.ejb.Local;


@Local
public interface ejbEvenementLocal {

    public Evenement findEvent(String code);

    public Collection<Evenement> listEvents();

    public Evenement addEvent(Evenement event);

    public Evenement updateEvent(Evenement event);

    public Collection<Evenement> findEventByName(String nom);

    public String createCodeEvenement();
    
    public boolean testCurrentEvenement();
    
    public Evenement currentEvenement();
    
}
