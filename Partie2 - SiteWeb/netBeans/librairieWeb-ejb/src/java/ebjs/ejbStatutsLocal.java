
package ebjs;

import entities.Statuts;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface ejbStatutsLocal {

    public Statuts addStatut(Statuts statut);

    public Statuts findStatuts(String code);

    public boolean checkDoublonStatuts(String code);

    public Statuts updateStatut(Statuts statut);

    public Collection<Statuts> listStatuts();

    public String createCodeStatuts();
    
}
