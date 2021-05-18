
package servlets.backOffice;

import ebjs.ejbTvaLocal;
import entities.Tva;
import java.io.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "gestionTva", urlPatterns = {"/gestionTva"})
public class gestionTva extends HttpServlet {

    @EJB
    private ejbTvaLocal ejbTva;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionTva</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionTva at " + request.getContextPath() + "</h1>");
            
            out.print("<form name='gestionTva'>");
            out.print("Code : <input type='text' name='tvaCode' placeholder='Saisir le code' />");
            out.print("<br>");
            out.print("<br>");
            out.print("Taux : <input type='text' name='tvaTaux' placeholder='Saisir le taux' />");
            out.print("<br>");
            out.print("<br>");
            out.print( "<input type='submit' name='addTva' value='Ajouter' />");
            out.print("<br>");
            out.print("</form>");
            if ("Ajouter".equals(request.getParameter("addTva"))) {
                String code = request.getParameter("tvaCode");
                float taux = Float.parseFloat(request.getParameter("tvaTaux").replace(',', '.'));
                Tva tva = new Tva();
                tva.setCode( code);
                tva.setTaux( taux);
                if ( !ejbTva.checkDoublonTva(code)) {
                    System.out.println("recup code TVA : " + code);
                    System.out.println("recup taux TVA : " + taux);
                    ejbTva.addTva( tva);
                    out.print("<p>Le code saisie a bien été ajouté !</p>");
                } else {
                    out.print("<p>Le code saisie existe déjà ! Merci de corriger.</p>");
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
