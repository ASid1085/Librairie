
package ebjs;

import entities.Adresse;
import entities.Client;
import entities.Commentaire;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ejbClient implements ejbClientLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public boolean addUser( Client user) {
        if( user.getLogin() == null) return false;
        if( user.getCivilite() == null) return false;
        if( user.getNom() == null) return false;
        if( user.getPrenom() == null) return false;
        if( user.getMdp() == null) return false;
        if( user.getEmail() == null) return false;
        if( user.getLogin().trim().isEmpty()) return false;
        if( user.getCivilite().trim().isEmpty()) return false;
        if( user.getNom().trim().isEmpty()) return false;
        if( user.getPrenom().trim().isEmpty()) return false;
        if( user.getMdp().trim().isEmpty()) return false;
        if( user.getEmail().trim().isEmpty()) return false;
        em.persist( user);
        return true;
    }
    
    @Override
    public Client updateUser( Client user) {
        em.merge( user);
        return user;
    }
    
    @Override
    public Client findUser( String login) {
        Client user = em.find( Client.class, login);
        return user;
    }
    
    @Override
    public Collection<Client> listUsers() {
        Query query = em.createQuery( "SELECT u FROM Client u");
        return query.getResultList();
    }
    
    @Override
    public Collection<Client> findUserByName( String nom) {
        Query query = em.createQuery( "SELECT u FROM Client c WHERE c.nom = \""+nom+"\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Adresse> findAdresseByUser ( String login) {
        Query query = em.createQuery( "SELECT a FROM Adresse a JOIN a.client u WHERE u.login = \""+login+"\"");
        return query.getResultList();
    }
    
    @Override
     public Collection<Commentaire> findCommentByUser ( String login) {
        Query query = em.createQuery( "SELECT c FROM Commentaire c JOIN c.laisserCommentaire lc WHERE lc.commande.client.login = \""+login+"\"");
        return query.getResultList();
    }
     
    @Override
     public boolean checkDoublonLibelle( String login, String libelle) {
         Collection<Adresse> listAdrsUser = findAdresseByUser(login);
         for (Adresse a : listAdrsUser) {
            String lib = a.getLibelle().trim();
             if(libelle.trim().equals(lib.trim())) {
                 return true;
             } 
         }
         return false;
     }
    
    @Override
    public boolean checkConnexionUser(String login, String mdp) {
        if( login == null) return false;
        if( mdp == null) return false;
        if( login.trim().isEmpty()) return false;
        if( mdp.trim().isEmpty()) return false;
        
        Client user = em.find( Client.class, login);
        if (user == null) return false;
        
        return user.getMdp().equals( mdp);
    }
    
    @Override
    public boolean userExist(String login) {
        Client user = em.find(Client.class, login);
        if( user == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public boolean emailExist(String email) {
        Query query = em.createQuery( "SELECT u FROM Client u WHERE u.email = \""+email+"\"");
        Collection<Client> listClts = query.getResultList();
        if(listClts.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
