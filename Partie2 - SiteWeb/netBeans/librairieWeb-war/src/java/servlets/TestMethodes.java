package servlets;


import ebjs.ejbAuteurLocal;
import ebjs.ejbLivreLocal;
import ebjs.ejbTvaLocal;
import entities.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet(urlPatterns = {"/TestEJBs"})
public class TestMethodes extends HttpServlet {

    @EJB
    private ejbAuteurLocal ejbAuteur;

    @EJB
    private ejbTvaLocal ejbTva;

    @EJB
    private ejbLivreLocal ejbLivre;
    
    InitialContext context;
    DataSource ds;
    Connection connexion;
//  ConnexionDS myConnexion = new ConnexionDS();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        /*
        ** TestMethodes de création d'un livre dans la BDD Test
        */
        
        Livre livre = ejbLivre.findLivre( "0000059001");
        
        Collection<Auteur> listAuteur = new ArrayList<Auteur>();
        
        Auteur auteur1 = ejbAuteur.findAuteur( "00001AUT");
        Auteur auteur2 = ejbAuteur.findAuteur( "00002AUT");
        
        listAuteur.add( auteur1);
        listAuteur.add( auteur2);
        
        livre.setListAuteur(listAuteur);
        
        ejbLivre.updateLivre( livre);
        
        
//        Livre livre = new Livre();
//        Tva tva = ejbTva.findTva( "TVA3");
//        livre.setIsbn( "5601284002");
//        livre.setBlabla( "");
//        livre.setDateEdition( "08/02/2018");
//        livre.setImage( "");
//        livre.setNbrePage( 350);
//        livre.setPrixHT( 14.5f);
//        livre.setResume( "Derrière l'innocente façade de St Mary, le secret du voyage dans le temps a été découvert et reste bien gardé. "+
//                        "Les chercheurs en Histoire ont ainsi une méthode de travail tout à fait particulière : " +
//                        "ils « étudient en temps réel les événements majeurs de l’Histoire ». " +
//                        "En se faisant passer pour d’inoffensifs excentriques, ils tentent de répondre à certaines questions qui n'ont jamais été résolues, " +
//                        "sans jamais toucher au cours de l’Histoire… au risque d’en mourir. Madeleine Maxwell, une jeune et brillante historienne est contactée " +
//                        "par son ancien professeur afin de rejoindre l'équipe de l'Institut St Mary. " +
//                        "Au cours de son étrange entretien d'embauche, Maxwell comprend vite les possibilités qui s’offrent à elle… " +
//                        "De la disparition de Pompéi aux tranchées de la Première Guerre mondiale, du grand incendie de Londres à la destruction de la bibliothèque d'Alexandrie, " +
//                        "la jeune historienne va revivre d'extraordinaires événements. Alors qu’au sein de l institut naissent des enjeux de pouvoir…");
//        livre.setSousTitre( "");
//        livre.setStatut( "");
//        livre.setStock( 30);
//        livre.setTitre( "Un monde apres l'autre");
//        livre.setTvaLivre( tva);
//        ejbLivre.addLivre( livre);
        
        
        /*
        ** TestMethodes de connection à la dataSource - OK
        */
        try {
            context = new InitialContext();
            ds = (DataSource) context.lookup("jdbc/MySQLLibWebTest");
            connexion = ds.getConnection();
            System.out.println("===> Connection LibWebTest réussie !");
        } catch (SQLException ex) {
            System.out.println("Oops: Connection: " + ex.getMessage());
        } catch (NamingException ex) {
            System.out.println("Oops: Naming: " + ex.getMessage());
        }
//        myConnexion.getInstance();
        try {
            connexion.close();
            System.out.println("===> Déconnection LibWebTest réussie !");
        } catch (SQLException ex) {
            System.out.println("Oops: Déconnection: " + ex.getMessage());
        }
//        myConnexion.closeInstance();
        
////////////////////////////////////////////////////////////////////////////////  

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestEJBs</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestEJBs at " + request.getContextPath() + "</h1>");
            out.println("<p>Tests de connexion et déconnexion à la DS réussis</p>");
            //out.println("<p>Le code de tva récupéré est le "+ tva.getCode() +" et le taux est "+ tva.getTaux() +".</p>");
            out.println("<p>L'ajout/modification du livre "+ livre.getIsbn() +" / "+ livre.getTitre() +" a bien été effectué</p>");
            out.println( "la modification avec l'auteur "+ auteur1.getNom().toUpperCase() + ", "+ auteur1.getPrenom() );
            out.println( " et l'auteur "+ auteur2.getNom().toUpperCase() + ", "+ auteur2.getPrenom() +" a bien été faite !");
            out.println("</body>");
            out.println("</html>");
        }
////////////////////////////////////////////////////////////////////////////////   
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
