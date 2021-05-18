/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebjs;

import entities.Livre;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface ejbLivreLocal {

    public Livre addLivre(Livre livre);

    public Livre updateLivre(Livre livre);

    public boolean checkDoublonLivre(String isbn);
    
    public Livre findLivre(String isbn);
    
    public Livre findLivreByIsbn( String isbn) ;

    public Collection<Livre> findLivreByEditeur(String editeur);

    public Collection<Livre> findLivreByAuteur(String auteur);

    public Collection<Livre> findLivreByTheme(String theme);

    public Collection<Livre> findLivreByMotCle(String MotCle);

    public Collection<Livre> findLivreByGenre(String genre);

    public Collection<Livre> findLivreByTitle(String titre);

    public Collection<Livre> findLivreByPriceTTC(float prix);

    public Livre updateSrcImage(Livre livre, String image);
    
    public Collection<Livre>list9Livres();
    
    public Collection<Livre> listLivres();

    public Collection<Livre> findLivreByIsbnSearch(String isbn);

    public boolean checkStockLivre(String isbn);
    
}
