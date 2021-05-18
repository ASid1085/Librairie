package servlets.backOffice;

import ebjs.ejbGenreLocal;
import entities.Genre;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "gestionGenre", urlPatterns = {"/gestionGenre"})
public class gestionGenre extends HttpServlet {

    @EJB
    private ejbGenreLocal ejbGenre;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionGenre</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionGenre at " + request.getContextPath() + "</h1>");
            
            out.println("<h2> Ajout d'un genre </h2>");
            
            out.print("<form name='gestionGenre'>");
            out.println("Code * : <input type='text' name='code' placeholder='Saisir le code' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom * : <input type='text' name='nom' placeholder='Saisir le nom' />");
            out.println("<br>");
            out.println("<br>");
            out.println( "<input type='submit' name='addGenre' value='Ajouter' />");
            out.print("</form>");
            
            if ("Ajouter".equals(request.getParameter("addGenre"))) {
                Genre genre = new Genre();
                genre.setCode( request.getParameter( "code"));
                genre.setNom( request.getParameter( "nom"));
                
                if ( !ejbGenre.checkDoublonGenre( request.getParameter( "code"))) {
                    ejbGenre.addGenre( genre);
                    out.print("<p>Le genre saisi a bien été ajouté !</p>");
                } else {
                    out.print("<p>Le genre existe déjà !</p>");
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
