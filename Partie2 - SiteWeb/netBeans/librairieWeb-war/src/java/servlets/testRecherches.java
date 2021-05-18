
package servlets;

import ebjs.ejbEvenementLocal;
import ebjs.ejbLivreLocal;
import entities.Evenement;
import entities.Livre;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "testRecherches", urlPatterns = {"/testRecherches"})
public class testRecherches extends HttpServlet {

    @EJB
    private ejbEvenementLocal ejbEvenement;

    @EJB
    private ejbLivreLocal ejbLivre;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testRecherches</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet testRecherches at " + request.getContextPath() + "</h1>");
            
            out.println("<h2>Recherche de livres</h2>");
            
            out.print("<form name='rechercheEditeur'>");
            out.println("Titre du livre : <input type='text' name='titre' placeholder='Saisir un titre' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom de l'éditeur : <input type='text' name='editeur' placeholder='Saisir un nom' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom de l'auteur : <input type='text' name='auteur' placeholder='Saisir un nom' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Thème : <input type='text' name='theme' placeholder='Saisir un thème' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Mot-clé : <input type='text' name='motCle' placeholder='Saisir un mot-clé' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Genre : <input type='text' name='genre' placeholder='Saisir un genre' />");
            out.println("<br>");
            out.println("<br>");
            out.println( "<input type='submit' name='lookupBook' value='Rechercher'/> <input type='submit' name='lookupEvent' value='Chercher event'/>");
            out.println("<br>");
            out.println("<br>");
            out.print("</form>");
            
            if ( "Rechercher".equals(request.getParameter("lookupBook"))) {
                if ( !request.getParameter("titre").isEmpty()) {
                    String titre = request.getParameter( "titre");
                    out.println("=====> récupération du champ de saisie : " + titre + "<br><br>");
                    Collection<Livre> listBooks = ejbLivre.findLivreByTitle( titre);
                    for (Livre l : listBooks) {
                        out.println("Titre : " + l.getTitre() + " -- ");
                        out.println("Prix HT : " + l.getPrixHT() + " -- ");
                        out.println("Prix TTC : " + l.getCalculTTC() + "<br>");
                    }
                }
                if ( !request.getParameter("editeur").isEmpty()) {
                    String editeur = request.getParameter( "editeur");
                    out.println("=====> récupération du champ de saisie : " + editeur + "<br><br>");
                    Collection<Livre> listBooks = ejbLivre.findLivreByEditeur(editeur);
                    for (Livre l : listBooks) {
                        out.println("Titre : " + l.getTitre() + " -- ");
                        out.println("Prix HT : " + l.getPrixHT() + " -- ");
                        out.println("Prix TTC : " + l.getCalculTTC()+ "<br>");
                    }
                }
                if ( !request.getParameter( "auteur").isEmpty()) {
                    String auteur = request.getParameter( "auteur");
                    out.println("=====> récupération du champ de saisie : " + auteur + "<br><br>");
                    Collection<Livre> listBooks = ejbLivre.findLivreByAuteur( auteur);
                    for (Livre l : listBooks) {
                        out.println("Titre : " + l.getTitre() + " -- ");
                        out.println("Prix HT : " + l.getPrixHT() + " -- ");
                        out.println("Prix TTC : " + l.getCalculTTC()+ "<br>");
                    }
                }
                if ( !request.getParameter( "theme").isEmpty()) {
                    String theme = request.getParameter( "theme");
                    out.println("=====> récupération du champ de saisie : " + theme + "<br><br>");
                    Collection<Livre> listBooks = ejbLivre.findLivreByTheme( theme);
                    for (Livre l : listBooks) {
                        out.println("Titre : " + l.getTitre() + " -- ");
                        out.println("Prix HT : " + l.getPrixHT() + " -- ");
                        out.println("Prix TTC : " + l.getCalculTTC()+ "<br>");
                    }
                }
                if ( !request.getParameter( "motCle").isEmpty()) {
                    String motCle = request.getParameter( "motCle");
                    out.println("=====> récupération du champ de saisie : " + motCle + "<br><br>");
                    Collection<Livre> listBooks = ejbLivre.findLivreByMotCle( motCle);
                    for (Livre l : listBooks) {
                        out.println("Titre : " + l.getTitre() + " -- ");
                        out.println("Prix HT : " + l.getPrixHT() + " -- ");
                        out.println("Prix TTC : " + l.getCalculTTC()+ "<br>");
                    }
                }
                
                if ( !request.getParameter( "genre").isEmpty()) {
                    String genre = request.getParameter( "genre");
                    out.println("=====> récupération du champ de saisie : " + genre + "<br><br>");
                    Collection<Livre> listBooks = ejbLivre.findLivreByGenre( genre);
                    for (Livre l : listBooks) {
                        out.println("Titre : " + l.getTitre() + " -- ");
                        out.println("Prix HT : " + l.getPrixHT() + " -- ");
                        out.println("Prix TTC : " + l.getCalculTTC()+ "<br>");
                    }
                }
           
            }
            
            if ("Chercher event".equals(request.getParameter("lookupEvent"))) {
                Collection<Evenement> listEvents = ejbEvenement.listEvents();
                for (Evenement eve : listEvents) {
                    if (eve.courentEvenement()) {
                        out.println("L'evenement " + eve.getCode() + " est en cours : " + eve.getNom() + "<br>");
                    } else {
                        out.println("L'evenement " + eve.getCode() + " N'est PAS en cours : " + eve.getNom() + "<br>");
                    }
                }
            }
            
            out.println("</body>");
            out.println("</html>");
        }
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
