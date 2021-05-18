/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebjs;

import entities.Commande;
import entities.LigneCommande;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author a.sid
 */
@Local
public interface ejbCommandeLocal {

    public Commande addCommande(Commande commande);

    public Commande updateCommande(Commande commande);

    public Commande findCommande(String numCde);

    public boolean checkDoublonCommande(String numCde);

    public Collection<Commande> listCommandes();

    public Collection<Commande> findCommandeByUser(String login);

    public Collection<Commande> findCommandeByStatut(String statut);

    public Collection<Commande> findCommandeByDate(String date);

    public long createNum();

    public String createNumCommande();

    public String createNumFacture();

    public Collection<LigneCommande> listLigCommandeByNumCde(String numCde);

    public Collection<LigneCommande> listLigCommandeByUser(String login);

}
