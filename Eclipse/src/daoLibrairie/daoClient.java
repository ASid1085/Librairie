package daoLibrairie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import entitesLibrairie.Client;
import entitesLibrairie.Genre;
import interfaceDaoLibrairie.iDaoClient;

public class daoClient implements iDaoClient{
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;

	@Override
	public void ajouterClient(Client clt) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "insert into CLIENT values ( '"+ clt.getClientLogin() + "', '" + clt.getClientNom() 
							+ "', '" + clt.getClientPrenom() + "', '" + clt.getClientMdp() 
							+ "', '" + clt.getClientEmail() + "', '" + clt.getClientTel() 
							+ "', '" + clt.getClientStatuts() + "', '" + clt.getClientStatuts()
							+ "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "Le nouveau client a été ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void modifierClient(Client clt) throws SQLException {
		
		myConnexion = Connexion.getInstance();

		String query = "update CLIENT set CLIENTNOM = '" + clt.getClientNom() +"', CLIENTPRENOM = '" + clt.getClientPrenom() 
							+"', CLIENTTEL = '"+ clt.getClientTel() +"', CLIENTEMAIL = '"+ clt.getClientEmail() +"', CLIENTTEL = '" + clt.getClientTel()
							+"', CLIENTSTATUT = '" + clt.getClientStatuts()
							+"' where CLIENTLOGIN = '" + clt.getClientLogin() +"';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "La modification du client a bien été effectuée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	public void modifierNoteClient(String clientLogin, String commentaire) throws SQLException {
	
		myConnexion = Connexion.getInstance();
	
		String query = "update CLIENT set CLIENTCOMMENT='"+ commentaire +"' where CLIENTLOGIN = '"+ clientLogin +"';";
	
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
	
		pstmt.close();
		System.out.println( "La note au sujet du client a bien été ajoutée !");;
	}
	
	public String recupererNoteClient(String clientLogin) throws SQLException {
		String commentaire = "";
		
		myConnexion = Connexion.getInstance();
	
		String query = "select CLIENTCOMMENT from CLIENT where CLIENTLOGIN = '"+ clientLogin +"';";
	
		stmt = myConnexion.createStatement();
    	ResultSet rs = stmt.executeQuery( query);
    	while ( rs.next()) {
    		commentaire = rs.getString( "CLIENTCOMMENT");
    	}
    	rs.close();
    	stmt.close();
		
		return commentaire;
	}

	@Override
	public Vector<Client> vectorListClient() throws SQLException {
		Vector vClient = new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select CLIENTLOGIN, CLIENTNOM, CLIENTPRENOM, CLIENTEMAIL, CLIENTSTATUT from CLIENT;";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "CLIENTLOGIN"));
				colonne.add( rs.getString( "CLIENTNOM"));
				colonne.add( rs.getString( "CLIENTPRENOM"));
				colonne.add( rs.getString( "CLIENTEMAIL"));
				colonne.add( rs.getString( "CLIENTSTATUT"));

				vClient.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vClient;
	}
	
	@Override
	public DefaultTableModel listeClient() throws SQLException {
		Vector vClient = vectorListClient();
		Vector nomColonne = new Vector<>();
		nomColonne.add( "Login");
		nomColonne.add( "Nom");
		nomColonne.add( "Prénom");
		nomColonne.add( "Mail");
		nomColonne.add( "Statut");
		
		return new DefaultTableModel( vClient, nomColonne);
	}
	
	public Vector<Client> vectorListClientByLogin( String clientLogin) throws SQLException {
		Vector vClient = new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select * from CLIENT where CLIENTLOGIN ='"+ clientLogin +"';";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "CLIENTLOGIN"));
				colonne.add( rs.getString( "CLIENTNOM"));
				colonne.add( rs.getString( "CLIENTPRENOM"));
				colonne.add( rs.getString( "CLIENTEMAIL"));
				colonne.add( rs.getString( "CLIENTSTATUT"));

				vClient.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vClient;
	}
	
	public DefaultTableModel listeClientByLogin( String clientLogin) throws SQLException {
		Vector vClient = vectorListClientByLogin( clientLogin);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "Login");
		nomColonne.add( "Nom");
		nomColonne.add( "Prénom");
		nomColonne.add( "Mail");
		nomColonne.add( "Statut");
		
		return new DefaultTableModel( vClient, nomColonne);
	}
	
	public Vector<Client> vectorListClientByNom( String clientNom) throws SQLException {
		Vector vClient = new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select * from CLIENT where CLIENTLOGIN ='"+ clientNom +"';";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "CLIENTLOGIN"));
				colonne.add( rs.getString( "CLIENTNOM"));
				colonne.add( rs.getString( "CLIENTPRENOM"));
				colonne.add( rs.getString( "CLIENTEMAIL"));
				colonne.add( rs.getString( "CLIENTSTATUT"));

				vClient.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vClient;
	}
	
	public DefaultTableModel listeClientByNom( String clientNom) throws SQLException {
		Vector vClient = vectorListClientByNom( clientNom);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "Login");
		nomColonne.add( "Nom");
		nomColonne.add( "Prénom");
		nomColonne.add( "Mail");
		nomColonne.add( "Statut");
		
		return new DefaultTableModel( vClient, nomColonne);
	}
	
	public Vector<Client> vectorCBLoginClient() throws SQLException {
		Vector vLogin =  new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select CLIENTLOGIN from CLIENT order by CLIENTLOGIN;";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				vLogin.add( rs.getString( "CLIENTLOGIN"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vLogin;
	}
	
	public DefaultComboBoxModel ClientLogin() throws SQLException {
		return new DefaultComboBoxModel<>( vectorCBLoginClient());
	}

	@Override
	public Client findClientByLogin(String clientLogin) throws SQLException {
		Client clt = null;
		
		myConnexion = Connexion.getInstance();
		
		
		String query = "select * from CLIENT where CLIENTLOGIN ='"+ clientLogin +"';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				clt = new Client( rs.getString( "CLIENTLOGIN"),
						rs.getString( "CLIENTNOM"),
						rs.getString( "CLIENTPRENOM"),
						rs.getString( "CLIENTMDP"),
						rs.getString( "CLIENTEMAIL"),
						rs.getString( "CLIENTTEL"),
						rs.getString( "CLIENTSTATUT"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return clt;
	}

	@Override
	public Client findClientByNom(String clientNom) throws SQLException {
		Client clt = null;

		myConnexion = Connexion.getInstance();


		String query = "select * from CLIENT where CLIENTLOGIN ='"+ clientNom +"';";
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				clt = new Client( rs.getString( "CLIENTLOGIN"),
						rs.getString( "CLIENTNOM"),
						rs.getString( "CLIENTPRENOM"),
						rs.getString( "CLIENTMDP"),
						rs.getString( "CLIENTEMAIL"),
						rs.getString( "CLIENTSTATUT"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return clt;
	}

}
