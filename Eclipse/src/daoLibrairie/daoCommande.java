package daoLibrairie;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
import entitiesLibrairie.Client;
import entitiesLibrairie.Commande;
import interfaceDaoLibrairie.iDaoCommande;

public class daoCommande implements iDaoCommande {
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;

	public String ajoutIdCommande() {
		String id = "";

		myConnexion = Connexion.getInstance();

		String query = "select count(*) from COMMANDE;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				int numCde = rs.getInt( 1)+1 ;
				if (numCde < 10) {
					id = "0000" + numCde + "CMD";
				} else if (numCde < 100) {
					id = "000" + numCde + "CMD";
				} else if (numCde < 1000) {
					id = "00" + numCde + "CMD";
				} else if (numCde < 10000) {
					id = "0" + numCde + "CMD";
				} if (numCde > 99999) {
					JOptionPane.showMessageDialog(null, "Le nombre maximum de commande est atteint !\nVeuillez contacter l'administrateur de BDD !", "Message d'erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String recupAdresseIp() {
		String adrIp = "";
		
		InetAddress ip = null;
		try {
			ip = InetAddress.getLocalHost();
			adrIp = ip.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return adrIp;
	}
		
	public String recupererNoteCommande(String numCde) throws SQLException {
		String commentaire = "";
		
		myConnexion = Connexion.getInstance();
	
		String query = "select COMMANDECOMMENT from COMMANDE where COMMANDENUM = '"+ numCde +"';";
	
		stmt = myConnexion.createStatement();
    	ResultSet rs = stmt.executeQuery( query);
    	while ( rs.next()) {
    		commentaire = rs.getString( "COMMANDECOMMENT");
    	}
    	rs.close();
    	stmt.close();
		
		return commentaire;
	}
	
	public void modifierNoteCommande(String numCde, String commentaire) throws SQLException {
		
		myConnexion = Connexion.getInstance();
	
		String query = "update COMMANDE set COMMANDECOMMENT='"+ commentaire.replace("'", "''") +"' where COMMANDENUM = '"+ numCde +"';";
	
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
	
		pstmt.close();
		System.out.println( "La note au sujet de l'éditeur a bien été ajoutée !");;
	}

	@Override
	public void ajouterCommande( Commande cde) throws SQLException {
		
		myConnexion = Connexion.getInstance();

		String query = "insert into COMMANDE ( COMMANDENUM, CLIENTLOGIN, COMMANDEPAIEMENT, COMMANDEFORFAITLIVRAISON, "
							+ "COMMANDEDATE, TVAID, STATUTID, ADRESSEIDF, ADRESSEIDL, COMMANDEIP, DATESTATUT) "
							+ "values ( '"+ cde.getCdeNum() + "', '" + cde.getClientLogin()
							+ "', '" + cde.getCdePaiement() + "', '" + cde.getCdeForfaitLiv() 
							+ "', '" + cde.getCdeDate() + "', '" + cde.getTvaID() 
							+ "', '" + cde.getStatutId() + "', '" + cde.getAdresseIdF()
							+ "', '" + cde.getAdresseIdL() + "', '" + cde.getCdeIp() + "', '" + cde.getDateStatut()
							+ "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "Votre commande a été envoyée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		
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
							+ " where clt.CLIENTLOGIN like '%"+ clientLogin +"%';"; 

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
							+ " where cde.COMMANDENUM like '%"+ CdeNum +"%';"; 

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
				+ " where sta.STATUTLIBELLE like '%"+ StatutLibelle +"%';"; 

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
