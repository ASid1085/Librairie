
package ebjs;

import entities.Editeur;
import java.util.Collection;
import javax.ejb.Local;


@Local
public interface ejbEditeurLocal {

    public Editeur addEditeur(Editeur editeur);

    public Editeur findEditeur(String code);

    public boolean checkDoublonEditeur(String code);

    public Editeur updateEditeur(Editeur editeur);

    public Collection<Editeur> listEditeurs();

    public String createCodeEditeur();
    
}
