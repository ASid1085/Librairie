
package servlets.backOffice;

import ebjs.ejbStatutsLocal;
import entities.Statuts;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "gestionStatut", urlPatterns = {"/gestionStatut"})
public class gestionStatut extends HttpServlet {

    @EJB
    private ejbStatutsLocal ejbStatuts;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionStatut</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionStatut at " + request.getContextPath() + "</h1>");
            
            out.print("<form name='gestionStatut'>");
            out.print("Code : <input type='text' name='statutCode' placeholder='Saisir le code' />");
            out.print("<br>");
            out.print("<br>");
            out.print("Catégorie : <input type='text' name='statutCategerie' placeholder='Saisir la catégorie' />");
            out.print("<br>");
            out.print("<br>");
            out.print("Libellé : <input type='text' name='statutLibelle' placeholder='Saisir le libelle' />");
            out.print("<br>");
            out.print("<br>");
            out.print( "<input type='submit' name='addStatut' value='Ajouter' />");
            out.print("<br>");
            out.print("</form>");
            if ("Ajouter".equals(request.getParameter("addStatut"))) {
                String code = request.getParameter("statutCode");
                String categorie = request.getParameter("statutCategerie");
                String libelle = request.getParameter("statutLibelle");
                Statuts statut = new Statuts();
                statut.setCode( code);
                statut.setCategorie( categorie);
                statut.setLibelle( libelle);
                
                if ( !ejbStatuts.checkDoublonStatuts(code)) {
                    System.out.println("recup code statut : " + code);
                    System.out.println("recup categorie statut : " + categorie);
                    System.out.println("recup libelle statut : " + libelle);
                    ejbStatuts.addStatut( statut);
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
