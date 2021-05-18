
package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnexionDS extends SQLException {
    
    private InitialContext context;
    private DataSource ds;
    private Connection connexion;
    
    // Connection à la base si elle n'existe pas déjà
    public Connection getInstance() {
        try {
            context = new InitialContext();
            ds = (DataSource) context.lookup("jdbc/MySQLLibWebTest");
            connexion = ds.getConnection();
            System.out.println("Connection LibWebTest réussie !");
        } catch (SQLException ex) {
            System.out.println("Oops: Connection: " + ex.getMessage());
        } catch (NamingException ex) {
            System.out.println("Oops: Naming: " + ex.getMessage());
        }
        return connexion;
    }
    
    // Déconnection de la base si elle existe
    public Connection closeInstance() {
        if (connexion != null) {
            try {
                connexion.close();
                System.out.println("Déconnection LibWebTest réussie !");
            } catch (SQLException ex) {
                System.out.println("Oops: Déconnection: " + ex.getMessage());
            }
        }
        return connexion;
    }
}
