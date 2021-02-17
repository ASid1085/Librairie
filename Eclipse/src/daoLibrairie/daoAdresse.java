package daoLibrairie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Adresse;
import entitiesLibrairie.Client;
import entitiesLibrairie.Commande;
import entitiesLibrairie.Genre;
import interfaceDaoLibrairie.iDaoAdresse;

public class daoAdresse implements iDaoAdresse {
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;

	public String ajoutIdAdresse() {
		String id = null;

		myConnexion = Connexion.getInstance();

		String query = "select count(*) from ADRESSE;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				int numAdr = rs.getInt( 1) ;
				if (numAdr < 10) {
					id = "0000" + numAdr + "ADR";
				} else if (numAdr < 100) {
					id = "000" + numAdr + "ADR";
				} else if (numAdr < 1000) {
					id = "00" + numAdr + "ADR";
				} else if (numAdr < 10000) {
					id = "0" + numAdr + "ADR";
				} if (numAdr > 99999) {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez plus ajouter de nouvelle adresse !", "Message d'erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return id;
	}
	
	public void lierAdLivClt(Adresse adr, String clt, String etat) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "insert into FAIRE_LIVRER values ( '" + clt + "', '" + adr.getAdresseId() + "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();

		pstmt.close();
		JOptionPane.showMessageDialog(null, "La nouvelle adresse livraison a été rattachée au client !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void lierAdFacClt(Adresse adr, String clt, String etat) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "insert into FACTURER values ( '" + clt + "', '" + adr.getAdresseId() + "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();

		pstmt.close();
		JOptionPane.showMessageDialog(null, "La nouvelle adresse facturation a été rattachée au client !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void addAdresse(Adresse adresse) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "insert into ADRESSE values ('" + adresse.getAdresseId() + "', '" + adresse.getAdresseCode() 
								+ "', '" + adresse.getAdresseNom() + "', '" + adresse.getAdressePrenom()
								+ "', '" + adresse.getAdresseNoRue() + "', '" + adresse.getAdresseRue()
								+ "', '" + adresse.getAdresseCompl() + "', '" + adresse.getAdresseCp() 
								+ "', '" + adresse.getAdresseVille() + "', '" + adresse.getAdressePays()
								+ "', '" + adresse.getAdresseTel() + "', '" + adresse.getAdresseSociete() + "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();

		pstmt.close();
		JOptionPane.showMessageDialog(null, "La nouvelle adresse a été ajoutée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void attribAdresseLivraison( String clientLogin, Adresse adresse) throws SQLException {
		myConnexion = Connexion.getInstance();
		
		String query = "insert into FAIRE_LIVRER values ( '" + clientLogin + "', '" + adresse.getAdresseId() + "');";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();

		pstmt.close();
		JOptionPane.showMessageDialog(null, "L'adresse a été liée au client avec succès !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void attribAdresseFacturation( String clientLogin, Adresse adresse) throws SQLException {
		myConnexion = Connexion.getInstance();
		
		String query = "insert into FACTURER values ( '" + clientLogin + "', '" + adresse.getAdresseId() + "');";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();

		pstmt.close();
		JOptionPane.showMessageDialog(null, "L'adresse a été liée au client avec succès !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	@Override
	public void ModifierAdresse(Adresse adresse) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "update ADRESSE set ADRESSECODE = '" + adresse.getAdresseCode() +"', ADRESSENOM = '" + adresse.getAdresseNom() 
							+"', ADRESSEPRENOM = '" + adresse.getAdressePrenom() + "', ADRESSENORUE = '"+ adresse.getAdresseNoRue() + "', ADRESSERUE = '" + adresse.getAdresseRue()
							+"', ADRESSECOMPL = '" + adresse.getAdresseCompl() + "', ADRESSECP = '"+ adresse.getAdresseCp() + "', ADRESSEVILLE = '" + adresse.getAdresseVille()
							+"', ADRESSEPAYS = '" + adresse.getAdressePays() + "', ADRESSETEL = '"+ adresse.getAdresseTel()
							+"' where ADRESSEID = '" + adresse.getAdresseId() +"';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "La modification de l'adresse a bien été effectuée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public Vector<Adresse> vectorListAdresse() throws SQLException {
		Vector vAdr = new Vector();

		myConnexion = Connexion.getInstance();
		
		String query =	"select * from ADRESSE order by ADRESSEID;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "ADRESSENOM") + " " + rs.getString( "ADRESSEPRENOM"));
				colonne.add( rs.getString( "ADRESSENORUE") + ", " + rs.getString( "ADRESSERUE"));
				colonne.add( rs.getString( "ADRESSECOMPL"));
				colonne.add( rs.getString( "ADRESSECP") + " - " + rs.getString( "ADRESSEVILLE"));
				colonne.add( rs.getString( "ADRESSETEL"));

				vAdr.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vAdr;
	}

	@Override
	public DefaultTableModel listeAdresse() throws SQLException {
		Vector vAdr = vectorListAdresse();

		Vector nomColonne = new Vector<>();
		nomColonne.add( "Nom, Prénom");
		nomColonne.add( "Rue");
		nomColonne.add( "Complément");
		nomColonne.add( "CP - Ville");
		nomColonne.add( "Téléphone");

		return new DefaultTableModel( vAdr, nomColonne);
	}
	
	public Vector<Adresse> findAdresseLivByLogin( String clientLogin) throws SQLException {
		Vector vAdr  = new Vector();

		myConnexion = Connexion.getInstance();

		String query = "select distinct * from ADRESSE as adr inner join FAIRE_LIVRER as liv on adr.ADRESSEID = liv.ADRESSEID"
							+ " inner join CLIENT as clt on liv.CLIENTLOGIN = clt.CLIENTLOGIN"
							+ " where clt.CLIENTLOGIN = '" + clientLogin + "';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "ADRESSEID"));
				colonne.add( rs.getString( "ADRESSENOM") + " " + rs.getString( "ADRESSEPRENOM"));
				colonne.add( rs.getString( "ADRESSENORUE") + ", " + rs.getString( "ADRESSERUE"));
				colonne.add( rs.getString( "ADRESSECOMPL"));
				colonne.add( rs.getString( "ADRESSECP") + " - " + rs.getString( "ADRESSEVILLE"));
				colonne.add( rs.getString( "ADRESSETEL"));

				vAdr.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return vAdr;
	}
	
	public DefaultTableModel listeAdresseLivByLogin( String clientLogin) throws SQLException {
		Vector vAdrLiv = findAdresseLivByLogin( clientLogin);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "Id adresse");
		nomColonne.add( "Nom, Prénom");
		nomColonne.add( "Rue");
		nomColonne.add( "Complément");
		nomColonne.add( "CP - Ville");
		nomColonne.add( "Téléphone");

		return new DefaultTableModel( vAdrLiv, nomColonne);
	}
	
	public Vector<Adresse> findAdresseFacByLogin( String clientLogin) throws SQLException {
		Vector vAdrFac  = new Vector();

		myConnexion = Connexion.getInstance();

		String query = "select distinct * from ADRESSE as adr inner join FACTURER as fac on adr.ADRESSEID = fac.ADRESSEID"
							+ " inner join CLIENT as clt on fac.CLIENTLOGIN = clt.CLIENTLOGIN"
							+ " where clt.CLIENTLOGIN = '" + clientLogin + "';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "ADRESSEID"));
				colonne.add( rs.getString( "ADRESSENOM") + " " + rs.getString( "ADRESSEPRENOM"));
				colonne.add( rs.getString( "ADRESSENORUE") + ", " + rs.getString( "ADRESSERUE"));
				colonne.add( rs.getString( "ADRESSECOMPL"));
				colonne.add( rs.getString( "ADRESSECP") + " - " + rs.getString( "ADRESSEVILLE"));
				colonne.add( rs.getString( "ADRESSETEL"));

				vAdrFac.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return vAdrFac;
	}
	
	public DefaultTableModel listeAdresseFacByLogin( String clientLogin) throws SQLException {
		Vector vAdrFac = findAdresseFacByLogin( clientLogin);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "Id adresse");
		nomColonne.add( "Nom, Prénom");
		nomColonne.add( "Rue");
		nomColonne.add( "Complément");
		nomColonne.add( "CP - Ville");
		nomColonne.add( "Téléphone");

		return new DefaultTableModel( vAdrFac, nomColonne);
	}
	
	public Adresse findAdresseLivById( String id) throws SQLException {
		Adresse adrLiv = null;
		
		myConnexion = Connexion.getInstance();

		String query = "select * from ADRESSE as adr inner join FAIRE_LIVRER as liv on adr.ADRESSEID = liv.ADRESSEID"
							+ " inner join CLIENT as clt on liv.CLIENTLOGIN = clt.CLIENTLOGIN"
							+ " where adr.ADRESSEID = '" + id + "';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				adrLiv = new Adresse( rs.getString( "ADRESSEID"), 
								   rs.getString( "ADRESSENOM").toUpperCase(),
								   rs.getString( "ADRESSEPRENOM"),
								   rs.getString( "ADRESSENORUE"),
								   rs.getString( "ADRESSERUE"),
								   rs.getString( "ADRESSECOMPL"),
								   rs.getString( "ADRESSECP"),
								   rs.getString( "ADRESSEVILLE"),
								   rs.getString( "ADRESSETEL"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return adrLiv;
	}
	
	public Adresse findAdresseFacById( String id) throws SQLException {
		Adresse adrFac = null;
		
		myConnexion = Connexion.getInstance();

		String query = "select * from ADRESSE as adr inner join FACTURER as fac on adr.ADRESSEID = fac.ADRESSEID"
							+ " inner join CLIENT as clt on fac.CLIENTLOGIN = clt.CLIENTLOGIN"
							+ " where adr.ADRESSEID = '" + id + "';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				adrFac = new Adresse( rs.getString( "ADRESSEID"), 
								   rs.getString( "ADRESSENOM").toUpperCase(),
								   rs.getString( "ADRESSEPRENOM"),
								   rs.getString( "ADRESSENORUE"),
								   rs.getString( "ADRESSERUE"),
								   rs.getString( "ADRESSECOMPL"),
								   rs.getString( "ADRESSECP"),
								   rs.getString( "ADRESSEVILLE"),
								   rs.getString( "ADRESSETEL"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return adrFac;
	}
	
	public Adresse findAdresseById( String id) throws SQLException {
		Adresse adr = null;
		
		myConnexion = Connexion.getInstance();

		String query = "select * from ADRESSE as adr inner join FACTURER as fac on adr.ADRESSEID = fac.ADRESSEID"
							+ "inner join FAIRE_LIVRER as liv on adr.ADRESSEID = liv.ADRESSEID"
							+ " inner join CLIENT as clt on fac.CLIENTLOGIN = clt.CLIENTLOGIN"
							+ " inner join FACTURER as fact on fact.CLIENTLOGIN = clt.CLIENTLOGIN"
							+ " where adr.ADRESSEID = '" + id + "';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				adr = new Adresse( rs.getString( "ADRESSEID"), 
								   rs.getString( "ADRESSENOM").toUpperCase(),
								   rs.getString( "ADRESSEPRENOM"),
								   rs.getString( "ADRESSENORUE"),
								   rs.getString( "ADRESSERUE"),
								   rs.getString( "ADRESSECOMPL"),
								   rs.getString( "ADRESSECP"),
								   rs.getString( "ADRESSEVILLE"),
								   rs.getString( "ADRESSETEL"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return adr;
	}
}
