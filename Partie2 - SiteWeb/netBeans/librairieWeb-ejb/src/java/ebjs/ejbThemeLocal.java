
package ebjs;

import entities.Theme;
import java.util.Collection;
import javax.ejb.Local;


@Local
public interface ejbThemeLocal {

    public Theme addTheme(Theme theme);

    public Theme findTheme(String code);

    public boolean checkDoublonTheme(String code);

    public Theme updateTheme(Theme theme);

    public Collection<Theme> listThemes();

    public String createCodeTheme();
    
}
