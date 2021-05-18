/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebjs;

import entities.Auteur;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author a.sid
 */
@Local
public interface ejbAuteurLocal {

    public Auteur addAuteur(Auteur auteur);

    public Auteur findAuteur(String code);

    public boolean checkDoublonAuteur(String code);

    public Auteur updateAuteur(Auteur auteur);

    public Auteur findAuteurByPseudo(String pseudo);

    public Collection<Auteur> findAuteurByName(String nom);

    public Collection<Auteur> listAuteurs();

    public String createCodeAuteur();
    
}
