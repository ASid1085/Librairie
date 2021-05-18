/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebjs;

import entities.LigneCommande;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author a.sid
 */
@Local
public interface ejbLigCommandeLocal {

    public LigneCommande addLigCommande(LigneCommande ligCde);

    public LigneCommande updateLigCommande(LigneCommande ligCde);

    public Collection<LigneCommande> listLigCommandeByNumCde(String numCde);

    public boolean checkDoublonLigCde(String code);

    public String createCodeLigneCommande();
    
}
