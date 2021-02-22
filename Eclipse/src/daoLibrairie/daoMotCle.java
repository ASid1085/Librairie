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
import entitiesLibrairie.Genre;
import entitiesLibrairie.MotCle;
import interfaceDaoLibrairie.iDaoMotCle;

public class daoMotCle implements iDaoMotCle {
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;

	public String ajoutIdMotCle() {
		String id = null;

		myConnexion = Connexion.getInstance();

		String query = "select count(*) from MOT_CLE;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				int numMotCle = rs.getInt( 1)+1 ;
				if (numMotCle < 10) {
					id = "0000" + numMotCle + "MOT";
				} else if (numMotCle < 100) {
					id = "000" + numMotCle + "MOT";
				} else if (numMotCle < 1000) {
					id = "00" + numMotCle + "MOT";
				} else if (numMotCle < 10000) {
					id = "0" + numMotCle + "MOT";
				} if (numMotCle > 99999) {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez plus ajouter de nouveau mot-clé !", "Message d'erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public void ajouterMotCle(MotCle motCle) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "insert into MOT_CLE values ('" + motCle.getMotCleId() + "', '" + motCle.getMotCleLibelle() + "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();

		pstmt.close();
		JOptionPane.showMessageDialog(null, "Le nouveau mot-clé a été ajouté !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void modifierMotCle(MotCle motCle, String motCleId) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "update MOT_CLE set MOTCLEID = '" + motCle.getMotCleId() +"', MOTCLELIBELLE = '" + motCle.getMotCleLibelle() 
							+"' where MOTCLEID = '" + motCleId + "';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "La modification du mot-clé a bien été effectuée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public Vector<MotCle> vectorListMotCle() throws SQLException {
		Vector vMotCle = new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select * from MOT_CLE order by MOTCLEID;";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "MOTCLEID"));
				colonne.add( rs.getString( "MOTCLELIBELLE"));

				vMotCle.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vMotCle;
	}

	@Override
	public DefaultTableModel listeMotCle() throws SQLException {
		Vector vMotCle = vectorListMotCle();
		Vector nomColonne = new Vector<>();
		nomColonne.add( "ID du mot-clé");
		nomColonne.add( "Libellé du mot-clé");
		
		return new DefaultTableModel( vMotCle, nomColonne);
	}

	public Vector<MotCle> vectorListMotCleByLib( String motCleLib) throws SQLException {
		Vector vMotCle = new Vector<>();

		myConnexion = Connexion.getInstance();

		String query = "select * from MOT_CLE where MOTCLELIBELLE like '%"+ motCleLib +"%';";
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "MOTCLEID"));
				colonne.add( rs.getString( "MOTCLELIBELLE"));

				vMotCle.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vMotCle;
	}

	public DefaultTableModel listeMotCleByLib( String motCleLib) throws SQLException {
		Vector vMotCle = vectorListMotCleByLib( motCleLib);
		Vector nomColonne = new Vector<>();
		nomColonne.add( "ID du mot-clé");
		nomColonne.add( "Libellé du mot-clé");
		
		return new DefaultTableModel( vMotCle, nomColonne);
	}

	
	@Override
	public MotCle findMotCleByLibelle(String motCleLibelle) throws SQLException {
		myConnexion = Connexion.getInstance();

		MotCle mc = new MotCle();
		String query = "select * from MOT_CLE where MOTCLELIBELLE like '%"+ motCleLibelle +"%';";
		System.out.println( query);
		try {
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				mc = new MotCle( rs.getString( "MOTCLEID"), rs.getString( "MOTCLELIBELLE"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
		}
		return mc;
	}

	@Override
	public void supprimerMotCle(String motCleLibelle) throws SQLException {
		myConnexion = Connexion.getInstance();

		String query = "delete from MOT_CLE where MOTCLELIBELLE ='" + motCleLibelle + "';";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "Le mot-clé a été supprimé !", "Confirmation", JOptionPane.WARNING_MESSAGE);
		

	}

}
