
package ebjs;

import entities.Commentaire;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ejbCommentaire implements ejbCommentaireLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;

    @Override
    public Commentaire addComment( Commentaire comment) {
        em.persist( comment);
        return comment;
    }

    @Override
    public Commentaire updateComment( Commentaire comment) {
        em.merge( comment);
        return comment;
    }
    
    @Override
    public Commentaire findComment( String code) {
        Commentaire comment = em.find( Commentaire.class, code);
        return comment;
    }
    
    @Override
    public Collection<Commentaire> listComments() {
        Query query = em.createQuery( "SELECT c FROM Commentaire c");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonComment( String code) {
        Commentaire comment = em.find( Commentaire.class, code);
        if( comment == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public Collection<Commentaire> findCommentByBook( String login) {
        Query query = em.createQuery( "SELECT c FROM Commentaire c WHERE c.livre.isbn = \""+login+"\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Commentaire> findCommentByUser( String login) {
        Collection<Commentaire> listCommentFind = null;
        Query query = em.createQuery( "SELECT c FROM Commentaire c WHERE c.client.login = \""+login+"\"");
        if ( query != null) {
            listCommentFind = query.getResultList();
        }
        return listCommentFind;
    }
    
    @Override
    public Collection<Commentaire> findCommentByStatut( String statut) {
        Query query = em.createQuery( "SELECT c FROM Commentaire c JOIN c.ListStatutCommentaire lst WHERE lst.statut = \""+statut+"\"");
        return query.getResultList();
    }
    
    @Override
    public String createCodeCommentaire() {
        String str = "";
        Query query = em.createQuery("SELECT COUNT(c) FROM Commentaire c");
        long count = (long) query.getSingleResult()+1;
        if (count > 0 && count < 10) {
            str = "0000" + count + "COM";
        } else if (count >= 10 && count < 100) {
            str = "000" + count + "COM";
        } else if (count >= 100 && count < 1000) {
            str = "00" + count + "COM";
        } else if (count >= 1000 && count < 10000) {
            str = "0" + count + "COM";
        }
        return str;
    }
}
