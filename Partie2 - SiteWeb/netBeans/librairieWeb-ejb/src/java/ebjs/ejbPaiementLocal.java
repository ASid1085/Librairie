
package ebjs;

import entities.Paiement;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface ejbPaiementLocal {

    public Paiement addPaiement( Paiement paiement);

    public Paiement findPaiement(String code);

    public boolean checkDoublonPaiement(String code);

    public Paiement updatePaiement(Paiement paiement);

    public Collection<Paiement> listPaiements();
    
}
