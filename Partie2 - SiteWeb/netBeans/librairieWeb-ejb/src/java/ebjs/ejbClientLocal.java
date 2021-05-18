/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebjs;

import entities.Adresse;
import entities.Client;
import entities.Commentaire;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author a.sid
 */
@Local
public interface ejbClientLocal {

    public boolean addUser(Client user);

    public Client updateUser(Client user);

    public Client findUser(String login);

    public Collection<Client> listUsers();

    public Collection<Client> findUserByName(String nom);

    public Collection<Adresse> findAdresseByUser(String login);

    public boolean userExist(String login);

    public boolean checkConnexionUser(String login, String mdp);

    public boolean emailExist(String email);

    public Collection<Commentaire> findCommentByUser(String login);

    public boolean checkDoublonLibelle(String login, String libelle);
    
}
