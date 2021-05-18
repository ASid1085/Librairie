package ebjs;

import entities.LignePanier;
import entities.Panier;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface ejbLigPanierLocal {
    
    public Collection<LignePanier> listLigPanierByPanier(String codePanier);

    public boolean checkDoublonLigPanier(String code);

//    public LignePanier updateLigPanier(LignePanier ligPanier);
    
    public void deleteLigPanier( LignePanier ligPanier, String codePanier);

    public LignePanier addLigPanier(LignePanier ligPanier);

    public String createCodeLignePanier();
    
    public LignePanier findLigPanier ( String code);
    
    public LignePanier findLigPanierByIsbn( String isbn);
    
    public LignePanier findLigPanierByIsbnInPanier( String isbn, Panier panier);
    
    public boolean checkDoublonArticle(String isbn);
    
    public void deleteAllLigPanier( String codePanier);
    
    public Collection<LignePanier> listLPs(String codePanier);

    public int nbItemLigPanier(String codePanier);
    
}
