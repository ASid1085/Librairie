/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ebjs;

import entities.Genre;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author a.sid
 */
@Local
public interface ejbGenreLocal {

    public Genre addGenre(Genre genre);

    public Genre findGenre(String code);

    public boolean checkDoublonGenre(String code);

    public Genre updateGenre(Genre genre);

    public Collection<Genre> listGenres();

    public String createCodeGenre();
    
}
