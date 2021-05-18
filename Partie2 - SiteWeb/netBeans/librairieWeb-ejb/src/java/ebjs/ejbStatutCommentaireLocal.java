/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebjs;

import entities.StatutCommentaire;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author a.sid
 */
@Local
public interface ejbStatutCommentaireLocal {

    public StatutCommentaire addStatutCommentaire(StatutCommentaire statutCmt);

    public StatutCommentaire updateStatutCommentaire(StatutCommentaire statutCmt);

    public StatutCommentaire findStatutCommentaire(String code);

    public Collection<StatutCommentaire> listStatutsCommentaire();

    public boolean checkDoublonStatutCommentaire(String code);

    public String createCodeStatutCommentaire();
    
}
