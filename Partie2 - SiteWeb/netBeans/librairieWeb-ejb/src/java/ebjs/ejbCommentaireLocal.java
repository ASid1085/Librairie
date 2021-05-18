/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebjs;

import entities.Commentaire;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author a.sid
 */
@Local
public interface ejbCommentaireLocal {

    public Commentaire addComment(Commentaire comment);

    public Commentaire updateComment(Commentaire comment);

    public Commentaire findComment(String code);

    public Collection<Commentaire> listComments();

    public boolean checkDoublonComment(String code);

    public Collection<Commentaire> findCommentByBook(String titre);

    public Collection<Commentaire> findCommentByStatut(String statut);

    public String createCodeCommentaire();

    public Collection<Commentaire> findCommentByUser(String login);
    
}
