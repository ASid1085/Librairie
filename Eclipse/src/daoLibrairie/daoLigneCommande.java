package daoLibrairie;

import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import entitiesLibrairie.LigneCommande;
import interfaceDaoLibrairie.iDaoLigneCommande;

public class daoLigneCommande implements iDaoLigneCommande{
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;
	
	public String ajoutIdLigneCommande() {
		String id = null;

		myConnexion = Connexion.getInstance();

		String query = "select count(*) from LIGNE_COMMANDE;";

		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				int numLig = rs.getInt( 1) ;
				if (numLig < 10) {
					id = "0000" + numLig + "LIG";
				} else if (numLig < 100) {
					id = "000" + numLig + "LIG";
				} else if (numLig < 1000) {
					id = "00" + numLig + "LIG";
				} else if (numLig < 10000) {
					id = "0" + numLig + "LIG";
				} if (numLig > 99999) {
					JOptionPane.showMessageDialog( null, "Le nombre maximum de ligne de commande est atteint !\nVeuillez contacter l'administrateur de BDD !", "Message d'erreur", JOptionPane.WARNING_MESSAGE);
				}
			}
		} catch (SQLException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void addLigneCommande( LigneCommande lCde) throws SQLException {
		
		myConnexion = Connexion.getInstance();

		String query = "insert into LIGNE_COMMANDE ( LIGNECOMMANDEID, COMMANDENUM, LIVREISBN, LIGNECOMMANDEQUANTITE, LIGNECOMMANDEPRIXHT, LIGNECOMMANDETVAAPPLIQUEE, LIGNECOMMANDEREMISE, CLIENTLOGIN) "
							+ "values ( '" + lCde.getLigneCdeId() + "', '" + lCde.getCdeNum()
							+ "', '" + lCde.getLivreIsbn() + "', '" + lCde.getLigneCdeQte()
							+ "', '" + lCde.getLigneCdePrixHt() + "', '" + lCde.getLigneCdeTvaAppliquee()
							+ "', '" + lCde.getLigneCdeRemise() + "', '" + lCde.getClientLogin()
							+ "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
		JOptionPane.showMessageDialog(null, "La commande à bien été Ajoutée !", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public Vector<LigneCommande> vectorListLigneCde() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public DefaultTableModel listeLigneCde() throws SQLException {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

}
