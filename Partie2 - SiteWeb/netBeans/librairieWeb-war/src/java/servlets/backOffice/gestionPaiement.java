package servlets.backOffice;

import ebjs.ejbPaiementLocal;
import entities.Paiement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "gestionPaiement", urlPatterns = {"/gestionPaiement"})
public class gestionPaiement extends HttpServlet {

    @EJB
    private ejbPaiementLocal ejbPaiement;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionPaiement</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionPaiement at " + request.getContextPath() + "</h1>");
            
            out.print("<form name='gestionPaiement'>");
            out.print("Code : <input type='text' name='paiementCode' placeholder='Saisir le code' />");
            out.print("<br>");
            out.print("<br>");
            out.print("Mode de paiement : <input type='text' name='paiementMoyen' placeholder='Saisir le moyen de paiement' />");
            out.print("<br>");
            out.print("<br>");
            out.print( "<input type='submit' name='addPaiement' value='Ajouter' />");
            out.print("<br>");
            out.print("</form>");
            if ("Ajouter".equals(request.getParameter("addPaiement"))) {
                String code = request.getParameter("paiementCode");
                String moyen = request.getParameter("paiementMoyen");
                Paiement paiement = new Paiement();
                paiement.setCode( code);
                paiement.setMoyenPaiement( moyen);
                if ( !ejbPaiement.checkDoublonPaiement(code)) {
                    System.out.println("recup code statut : " + code);
                System.out.println("recup categorie statut : " + moyen);
                    ejbPaiement.addPaiement( paiement);
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
