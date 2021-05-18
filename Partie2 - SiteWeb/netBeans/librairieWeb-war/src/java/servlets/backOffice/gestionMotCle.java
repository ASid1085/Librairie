
package servlets.backOffice;

import ebjs.ejbMotCleLocal;
import entities.MotCle;
import java.io.*;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "gestionMotCle", urlPatterns = {"/gestionMotCle"})
public class gestionMotCle extends HttpServlet {

    @EJB
    private ejbMotCleLocal ejbMotCle;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionMotCle</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionMotCle at " + request.getContextPath() + "</h1>");
            
            out.println("<h2> Ajout d'un mot clé </h2>");
            
            out.print("<form name='gestionMotCle'>");
            out.println("Code * : <input type='text' name='code' placeholder='Saisir le code' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom * : <input type='text' name='nom' placeholder='Saisir le nom' />");
            out.println("<br>");
            out.println("<br>");
            out.println( "<input type='submit' name='addMotCle' value='Ajouter' />");
            out.print("</form>");
            
            if ("Ajouter".equals(request.getParameter("addMotCle"))) {
                MotCle motCle = new MotCle();
                motCle.setCode( request.getParameter( "code"));
                motCle.setNom( request.getParameter( "nom"));
                
                if ( !ejbMotCle.checkDoublonMotCle(request.getParameter( "code"))) {
                    ejbMotCle.addMotCle( motCle);
                    out.print("<p>Le mot-clé saisi a bien été ajouté !</p>");
                } else {
                    out.print("<p>Le mot-clé existe déjà !</p>");
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
