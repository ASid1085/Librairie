package connexionLibrairie;


import java.sql.*;
import com.mysql.jdbc.Driver;


public class Connexion {

	private String BDD1 = "Librairie";
	private String BDD2 = "LibrairieTEST"; // Nom de la BDD de Marine pour effectuer les tests "LibrairieTEST" Librairie2
	private String url = "jdbc:mysql://localhost:3306/" + BDD2;
	private String user = "root";
	private String mdp = "2021PasswordMySQL"; // Mot de passe du root de Marine "2021PasswordMySQL" Sidonie1
	static private Connection conn;
	
	//Constructeur de ma calsse
	private Connexion() {
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection(url, user, mdp);
		    System.out.println("Connecté");
		} catch (Exception e){
		    e.printStackTrace();
		    System.out.println("Erreur");
		    //System.exit(0);
		}
	}
	
	public static Connection getInstance() {
		if (conn == null) {
			new Connexion();
		}
		return conn;
	}
	
	public static Connection closeInstance() {
		try {
            conn.close();
            System.out.println( "Déconnection de la BDD");
        } catch (SQLException e) {
        	e.printStackTrace();
        	
        }
		return conn;
	}

}