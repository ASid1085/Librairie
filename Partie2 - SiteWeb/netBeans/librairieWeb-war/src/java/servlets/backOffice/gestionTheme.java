
package servlets.backOffice;

import ebjs.ejbGenreLocal;
import ebjs.ejbThemeLocal;
import entities.Genre;
import entities.Theme;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "gestionTheme", urlPatterns = {"/gestionTheme"})
public class gestionTheme extends HttpServlet {

    @EJB
    private ejbGenreLocal ejbGenre;

    @EJB
    private ejbThemeLocal ejbTheme;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionTheme</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionTheme at " + request.getContextPath() + "</h1>");
            
            out.println("<h2> Ajout d'un thème </h2>");
            
            out.print("<form name='gestionTheme'>");
            out.println("Code * : <input type='text' name='code' placeholder='Saisir le code' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom * : <input type='text' name='nom' placeholder='Saisir le nom' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Genre correspondant * : <input type='text' name='genre' placeholder='Saisir le genre' />");
            out.println("<br>");
            out.println("<br>");
            out.println( "<input type='submit' name='addTheme' value='Ajouter' />");
            out.print("</form>");
            
            if ("Ajouter".equals(request.getParameter("addTheme"))) {
                // Gestion de la création du thème
                Theme theme = new Theme();
                theme.setCode( request.getParameter( "code"));
                theme.setNom( request.getParameter( "nom"));
                Genre genre = ejbGenre.findGenre( request.getParameter( "genre"));
                theme.setGenreduTheme(genre);
                
                // Gestion des ajouts
                if ( !ejbTheme.checkDoublonTheme(request.getParameter( "code"))) {
                    // Gestion de l'ajout du thème
                    ejbTheme.addTheme( theme);
                    out.print("<p>Le thème saisi a bien été ajouté !</p>");
                } else {
                    out.print("<p>Le thème existe déjà !</p>");
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
