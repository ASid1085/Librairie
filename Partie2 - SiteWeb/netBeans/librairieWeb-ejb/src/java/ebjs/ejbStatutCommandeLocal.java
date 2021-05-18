/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebjs;

import entities.StatutCommande;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author a.sid
 */
@Local
public interface ejbStatutCommandeLocal {

    public StatutCommande addStatutCommande(StatutCommande statutCde);

    public StatutCommande updateStatutCommande(StatutCommande statutCde);

    public StatutCommande findStatutCommande(String code);

    public Collection<StatutCommande> listStatutsCommande();

    public boolean checkDoublonStatutCommande(String code);
    
}
