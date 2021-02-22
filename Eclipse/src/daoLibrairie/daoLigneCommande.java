package daoLibrairie;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import connexionLibrairie.Connexion;
import entitiesLibrairie.Adresse;
import entitiesLibrairie.LigneCommande;
import interfaceDaoLibrairie.iDaoLigneCommande;

public class daoLigneCommande implements iDaoLigneCommande{
	
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	static private Connection myConnexion;
	private DecimalFormat dfPrix = new DecimalFormat("##.00");
	private DecimalFormat dfQte = new DecimalFormat("##");
	private DecimalFormat dfRemise = new DecimalFormat("## %");
	
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

		String query = "insert into LIGNE_COMMANDE ( LIGNECOMMANDEID, COMMANDENUM, LIVREISBN, LIGNECOMMANDEQUANTITE, LIGNECOMMANDEPRIXHT, LIGNECOMMANDETVAAPPLIQUEE, LIGNECOMMANDEREMISE, CLIENTLOGIN)"
							+ "values ( '" + lCde.getLigneCdeId() + "', '" + lCde.getCdeNum()
							+ "', '" + lCde.getLivreIsbn() + "', '" + lCde.getLigneCdeQte()
							+ "', '" + lCde.getLigneCdePrixHt() + "', '" + lCde.getLigneCdeTvaAppliquee()
							+ "', '" + lCde.getLigneCdeRemise() + "', '" + lCde.getClientLogin()
							+ "');";

		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();
	}

	@Override
	public Vector<LigneCommande> vectorListLigneCde( String numCde) throws SQLException {
		Vector vLigCde = new Vector();

		myConnexion = Connexion.getInstance();
		
		String query =	"select liv.LIVRETITRE, lig.LIGNECOMMANDEPRIXHT, tva.TVATAUX, lig.LIGNECOMMANDEQUANTITE, lig.LIGNECOMMANDEREMISE"
							+ " from LIGNE_COMMANDE as lig inner join LIVRE as liv on liv.LIVREISBN = lig.LIVREISBN"
													   + " inner join COMMANDE as cde on lig.COMMANDENUM = cde.COMMANDENUM"
													   + " inner join TVA as tva on cde.TVAID = tva.TVAID"
							+ " where lig.COMMANDENUM = '" + numCde + "';";
		
		try {
			stmt = myConnexion.createStatement();
			rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
				colonne.add( rs.getString( "LIVRETITRE"));
				colonne.add( dfPrix.format( rs.getFloat( "LIGNECOMMANDEPRIXHT")));
				colonne.add( dfPrix.format((rs.getFloat( "LIGNECOMMANDEPRIXHT") * (1 + rs.getFloat( "TVATAUX")/100))));
				colonne.add( dfQte.format( rs.getFloat( "LIGNECOMMANDEQUANTITE")));
				colonne.add( dfRemise.format( rs.getFloat( "LIGNECOMMANDEREMISE")/100));
				colonne.add( dfPrix.format( rs.getFloat( "LIGNECOMMANDEQUANTITE") * (rs.getFloat( "LIGNECOMMANDEPRIXHT") * (1 - rs.getFloat( "LIGNECOMMANDEREMISE")/100))));
				colonne.add( dfPrix.format( rs.getFloat( "LIGNECOMMANDEQUANTITE") * (rs.getFloat( "LIGNECOMMANDEPRIXHT") * (1 - rs.getFloat( "LIGNECOMMANDEREMISE")/100) * (1 + rs.getFloat( "TVATAUX")/100))));

				vLigCde.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vLigCde;
	}

	@Override
	public DefaultTableModel listeLigneCde( String numCde) throws SQLException {
		Vector vCdeLig = vectorListLigneCde( numCde);

		Vector nomColonne = new Vector<>();
		nomColonne.add( "Titre du livre");
		nomColonne.add( "Prix unitaire HT");
		nomColonne.add( "Prix unitaire TTC");
		nomColonne.add( "Quantité");
		nomColonne.add( "Remise");
		nomColonne.add( "Prix total HT");
		nomColonne.add( "Prix total TTC");

		return new DefaultTableModel( vCdeLig, nomColonne);
	}

}
