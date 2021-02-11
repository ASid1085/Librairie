package daoLibrairie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import connexionLibrairie.Connexion;
import entitesLibrairie.Client;
import entitesLibrairie.Commande;
import interfaceDaoLibrairie.iDaoCommande;

public class daoCommande implements iDaoCommande {
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;

	public Integer ajoutIdLigneCommande() {
		int id = 0;

		myConnexion = Connexion.getInstance();

		String query = "select count(*) from COMMANDE;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				int numCmd = rs.getInt( 1) +1 ;
			}
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void ajouterCommande(Commande cde, String ClientLog) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void modifierCommande(Commande cde) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public Vector<Commande> vectorListCommande() throws SQLException {
		Vector vCde = new Vector();

		myConnexion = Connexion.getInstance();
		
		String query =	"select cde.COMMANDENUM, clt.CLIENTLOGIN, cde.COMMANDEDATE, sta.STATUTLIBELLE"
							+ " from COMMANDE as cde"
								+ " INNER JOIN CLIENT as clt ON cde.CLIENTLOGIN = clt.CLIENTLOGIN"
								+ " INNER JOIN STATUT as sta ON cde.STATUTID = sta.STATUTID;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "COMMANDENUM"));
				colonne.add( rs.getString( "CLIENTLOGIN"));
				colonne.add( rs.getString( "COMMANDEDATE"));
				colonne.add( rs.getString( "STATUTLIBELLE"));

				vCde.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vCde;
	}

	@Override
	public DefaultTableModel listeCommande() throws SQLException {
		Vector vCde = vectorListCommande();
		
		Vector nomColonne = new Vector<>();
		nomColonne.add( "N° de commande");
		nomColonne.add( "Login client");
		nomColonne.add( "Date de commande");
		nomColonne.add( "Statut de la commande");

		return new DefaultTableModel( vCde, nomColonne);
	}

	public Vector<Commande> vectorCBStatutCde() throws SQLException {
		Vector vStatut =  new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select STATUTLIBELLE from STATUT order by STATUTLIBELLE;";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				vStatut.add( rs.getString( "STATUTLIBELLE"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vStatut;
	}
	
	public DefaultComboBoxModel statutCommande() throws SQLException {
		return new DefaultComboBoxModel<>( vectorCBStatutCde());
	}
	
	@Override
	public Vector<Commande> findCommandeByLogin(String clientLogin) throws SQLException {
		Vector vCde  = new Vector();

		myConnexion = Connexion.getInstance();

		String query = "select cde.COMMANDENUM, clt.CLIENTLOGIN, cde.COMMANDEDATE, sta.STATUTLIBELLE"
							+ " from COMMANDE as cde"
							+ " INNER JOIN CLIENT as clt ON cde.CLIENTLOGIN = clt.CLIENTLOGIN"
							+ " INNER JOIN STATUT as sta ON cde.STATUTID = sta.STATUTID"
							+ " where clt.CLIENTLOGIN ='"+ clientLogin +"';"; 

		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "COMMANDENUM"));
				colonne.add( rs.getString( "CLIENTLOGIN"));
				colonne.add( rs.getDate( "COMMANDEDATE"));
				colonne.add( rs.getString( "STATUTLIBELLE"));
				
				vCde.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return vCde;
	}
	
	@Override
	public DefaultTableModel listeCommandeByLogin(String clientLogin) throws SQLException {
		Vector vCde = findCommandeByLogin( clientLogin);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "N° de commande");
		nomColonne.add( "Login client");
		nomColonne.add( "Date de commande");
		nomColonne.add( "Statut de la commande");

	return new DefaultTableModel( vCde, nomColonne);
	}

	@Override
	public Vector<Commande> findCommandeByCdeNum(String CdeNum) throws SQLException {
		Vector vCde  = new Vector();

		myConnexion = Connexion.getInstance();

		String query = "select cde.COMMANDENUM, clt.CLIENTLOGIN, cde.COMMANDEDATE, sta.STATUTLIBELLE"
							+ " from COMMANDE as cde"
							+ " INNER JOIN CLIENT as clt ON cde.CLIENTLOGIN = clt.CLIENTLOGIN"
							+ " INNER JOIN STATUT as sta ON cde.STATUTID = sta.STATUTID"
							+ " where cde.COMMANDENUM ='"+ CdeNum +"';"; 

		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "COMMANDENUM"));
				colonne.add( rs.getString( "CLIENTLOGIN"));
				colonne.add( rs.getDate( "COMMANDEDATE"));
				colonne.add( rs.getString( "STATUTLIBELLE"));
				
				vCde.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return vCde;
	}

	@Override
	public DefaultTableModel listeCommandeByCdeNum( String CdeNum) throws SQLException {
		Vector vCde = findCommandeByCdeNum( CdeNum);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "N° de commande");
		nomColonne.add( "Login client");
		nomColonne.add( "Date de commande");
		nomColonne.add( "Statut de la commande");

		return new DefaultTableModel( vCde, nomColonne);
	}
	
	@Override
	public Vector<Commande> findCommandeByStatut(String StatutLibelle) throws SQLException {
		Vector vCde  = new Vector();

		myConnexion = Connexion.getInstance();

		String query = "select cde.COMMANDENUM, clt.CLIENTLOGIN, cde.COMMANDEDATE, sta.STATUTLIBELLE"
				+ " from COMMANDE as cde"
				+ " INNER JOIN CLIENT as clt ON cde.CLIENTLOGIN = clt.CLIENTLOGIN"
				+ " INNER JOIN STATUT as sta ON cde.STATUTID = sta.STATUTID"
				+ " where sta.STATUTLIBELLE ='"+ StatutLibelle +"';"; 

		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "COMMANDENUM"));
				colonne.add( rs.getString( "CLIENTLOGIN"));
				colonne.add( rs.getDate( "COMMANDEDATE"));
				colonne.add( rs.getString( "STATUTLIBELLE"));
				
				vCde.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return vCde;
	}
	
	@Override
	public DefaultTableModel listeCommandeByStatut( String StatutLibelle) throws SQLException {
		Vector vCde = findCommandeByStatut( StatutLibelle);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "N° de commande");
		nomColonne.add( "Login client");
		nomColonne.add( "Date de commande");
		nomColonne.add( "Statut de la commande");

		return new DefaultTableModel( vCde, nomColonne);
	}

	@Override
	public Vector<Commande> findCommandeByDateCde(Date cdeDate) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public DefaultTableModel listeCommandeByDateCde(Date cdeDate) throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

}
