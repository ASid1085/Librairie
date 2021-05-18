
package servlets.backOffice;

import ebjs.ejbAuteurLocal;
import entities.Auteur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "gestionAuteur", urlPatterns = {"/gestionAuteur"})
public class gestionAuteur extends HttpServlet {

    @EJB
    private ejbAuteurLocal ejbAuteur;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionAuteur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionAuteur at " + request.getContextPath() + "</h1>");
            
            out.println("<h2> Ajout d'un auteur </h2>");
            
            out.print("<form name='gestionAuteur'>");
            out.println("Code * : <input type='text' name='code' placeholder='Saisir le code' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom * : <input type='text' name='nom' placeholder='Saisir le nom' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Prénom * : <input type='text' name='prenom' placeholder='Saisir le prenom'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Pseudo : <input type='text' name='pseudo'/>");
            out.println("<br>");
            out.println("<br>");
            out.println( "<input type='submit' name='addAuteur' value='Ajouter' />");
            out.print("</form>");
            
            if ("Ajouter".equals(request.getParameter("addAuteur"))) {
                Auteur auteur = new Auteur();
                auteur.setCode( request.getParameter( "code"));
                auteur.setNom( request.getParameter( "nom"));
                auteur.setPrenom( request.getParameter( "prenom"));
                auteur.setPseudo( request.getParameter( "pseudo"));
                
                if ( !ejbAuteur.checkDoublonAuteur( request.getParameter( "code"))) {
                    ejbAuteur.addAuteur( auteur);
                    out.print("<p>L'auteur saisi a bien été ajouté !</p>");
                } else {
                    out.print("<p>L'auteur existe déjà !</p>");
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
