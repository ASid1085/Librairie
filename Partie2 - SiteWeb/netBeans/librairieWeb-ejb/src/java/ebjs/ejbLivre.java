
package ebjs;

import entities.Livre;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ejbLivre implements ejbLivreLocal {

    @PersistenceContext(unitName = "librairieWeb-ejbPU")
    private EntityManager em;
    
    @Override
    public Livre addLivre(Livre livre) {
        em.persist( livre);   
        return livre;
    }
    
    @Override
     public Livre updateSrcImage(Livre livre, String image) {
        livre = em.find(Livre.class, livre.getIsbn());
        livre.setImage(image);
        em.merge(livre);
        return livre;
    }
    
    @Override
    public Livre updateLivre(Livre livre) {
        em.merge(livre);
        return livre;
    }
    
    @Override
    public Livre findLivre(String isbn) {
        Livre livre = em.find(Livre.class, isbn);
        return livre;
    }
    
    @Override
    public Collection<Livre> listLivres() {
        Query query = em.createQuery( "SELECT l FROM Livre l ORDER BY l.titre");
        return query.getResultList();
    }
    
    @Override
    public boolean checkDoublonLivre(String isbn) {
        Livre livre = em.find(Livre.class, isbn);
        if( livre == null) {
            return false;
        } else {
            return true;
        } 
    }
    
    @Override
    public boolean checkStockLivre(String isbn) {
        Livre livre = em.find(Livre.class, isbn);
        int stockLivre = livre.getStock();
        if ( stockLivre == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public Collection<Livre>list9Livres() {
        Query query = em.createQuery( "SELECT l FROM Livre l ORDER BY l.stock DESC");
        return query.setMaxResults(9).getResultList();
    }
    
    @Override
     public Livre findLivreByIsbn( String isbn) {
        Query query = em.createQuery("SELECT l FROM Livre l WHERE l.isbn LIKE \"%"+isbn+"%\"");
        return (Livre) query.getSingleResult();
    }
     
    @Override
     public Collection<Livre> findLivreByIsbnSearch( String isbn) {
        Query query = em.createQuery("SELECT l FROM Livre l WHERE l.isbn LIKE \"%"+isbn+"%\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Livre> findLivreByTitle( String titre) {
        Query query = em.createQuery("SELECT l FROM Livre l WHERE l.titre LIKE \"%"+titre+"%\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Livre> findLivreByEditeur( String editeur) {
        Query query = em.createQuery("SELECT l FROM Livre l WHERE l.editeur.nom LIKE \"%"+editeur+"%\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Livre> findLivreByAuteur (String auteur) {
        Query query = em.createQuery( "SELECT l FROM Livre l JOIN l.ListAuteur a WHERE a.nom LIKE \"%"+auteur+"%\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Livre> findLivreByTheme (String theme) {
        Query query = em.createQuery( "SELECT l FROM Livre l JOIN l.ListTheme t WHERE t.nom LIKE \"%"+theme+"%\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Livre> findLivreByGenre (String genre) {
        Query query = em.createQuery( "SELECT DISTINCT l FROM Livre l JOIN l.ListTheme t JOIN t.genreduTheme g WHERE g.nom LIKE \"%"+genre+"%\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Livre> findLivreByMotCle (String MotCle) {
        Query query = em.createQuery( "SELECT l FROM Livre l JOIN l.ListMotCle mc WHERE mc.nom LIKE \"%"+MotCle+"%\"");
        return query.getResultList();
    }
    
    @Override
    public Collection<Livre> findLivreByPriceTTC (float prixTTC) { 
        Query query = em.createQuery("SELECT l FROM Livre l WHERE (l.prixHT*(1+(l.tvaLivre.taux/100))) <= \"" + prixTTC + "\" ORDER BY l.prixHT DESC");
        return query.getResultList();
    }
}
