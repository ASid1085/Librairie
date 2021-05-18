package ebjs;

import entities.LignePanier;
import entities.Panier;
import java.util.Collection;
import javax.ejb.Local;

@Local
public interface ejbPanierLocal {
    
    
    public void add( LignePanier ligPanier);
    
     public void add( LignePanier ligPanier, String qte);
     
     public void add( LignePanier ligPanier, int qte);
     
     public void dec( LignePanier ligPanier);
     
     public void del( LignePanier ligPanier);
     
     public void clear();
     
     public boolean isEmpty();
     
     public int nbItem();
     
     public LignePanier findInMap( String isbn);
     
     public Collection<LignePanier> listContenuPanier();

    public Panier addPanier(Panier panier);

    public Panier updatePanier(Panier panier);

    public void deletePanier(String code);

    public boolean checkDoublonPanier(String code);

    public Collection<Panier> listPaniers();

    public Panier findPanierByUser(String login);
    
    public Panier monPanier(String code);

    public String createCodePanier();
    
    public float totalHtPanier();
    
    public float totalTvaPanier();
    
    public float totalTtcPanier();
    
    public String totalHtPanierAffichage();
    
    public String totalTvaPanierAffichage();
    
    public String totalTtcPanierAffichage();

    public float totalHtPanierUser(String login);

    public float totalTvaPanierUser(String login);

    public float totalTtcPanierUser(String login);

    public String totalHtPanierUserAffichage(String login);

    public String totalTvaPanierUserAffichage(String login);

    public String totalTtcPanierUserAffichage(String login);
}
