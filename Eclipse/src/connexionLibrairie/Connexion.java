package connexionLibrairie;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Driver;


public class Connexion {

	
	static Connection conn = null;
	private final String protocole =  "jdbc:mysql:" ;
     // Adresse IP de l’hôte de la base et port
    private final String ip =  "localhost" ;  // dépend du contexte
    private final String port =  "3306" ;  // port MySQL par défaut
     // Nom de la base ;
    private final String nomBase =  "LibrairieTEST" ;  // dépend du contexte
     // Chaîne de connexion
    private final String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
     // Identifiants de connexion et mot de passe
    private final String nomConnexion =  "root" ;  // dépend du contexte
    private final String motDePasse =  "2021PasswordMySQL" ;  // dépend du contexte
    
    private Connexion() {
		
		
		try {
			//1. Get a connection to database
			// chargement de la classe par son nom
	        Class c = Class.forName("com.mysql.jdbc.Driver") ;
	        Driver pilote = (Driver)c.newInstance() ;
	         // enregistrement du pilote auprès du DriverManager
	        DriverManager.registerDriver(pilote);
	         // Protocole de connexion

	         // Connexion
	        conn = DriverManager.getConnection(conString, nomConnexion, motDePasse) ;
			
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
}

public static Connection getInstance() {
	if (conn == null) {
		new Connexion();
	}
	return conn;
}

}