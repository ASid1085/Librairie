package servlets.backOffice;

import ebjs.ejbClientLocal;
import entities.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "gestionClient", urlPatterns = {"/gestionClient"})
public class gestionClient extends HttpServlet {

    @EJB
    private ejbClientLocal ejbClient;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionClient</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionClient at " + request.getContextPath() + "</h1>");
            
            out.println("<h2> Ajout d'un client </h2>");
            
            out.print("<form name='gestionClient'>");
            out.println("Login * : <input type='text' name='login' placeholder='Saisir le login' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Civilité * : <input type='text' name='civilite' placeholder='M. Mme Melle'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom * : <input type='text' name='nom' placeholder='Saisir le nom'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Prénom * : <input type='text' name='prenom' placeholder='Saisir le prénom'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Email * : <input type='text' name='mail' placeholder='Saisir e-mail'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Mot de passe * : <input type='password' name='mdp' placeholder='Saisir le mot de passe '/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Telephone : <input type='text' name='tel'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Statut : <input type='text' name='statut'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Blabla : <input type='text' name='blabla'/>");
            out.println("<br>");
            out.println("<br>");
            out.println( "<input type='submit' name='addClient' value='Ajouter' />");
            out.print("</form>");
            
            if ("Ajouter".equals(request.getParameter("addClient"))) {
                Client user = new Client();
                
                user.setLogin( request.getParameter( "login"));
                user.setCivilite( request.getParameter( "civilite"));
                user.setNom( request.getParameter( "nom"));
                user.setPrenom( request.getParameter( "prenom"));
                user.setEmail( request.getParameter( "mail"));
                user.setMdp( request.getParameter( "mdp"));
                user.setTel( request.getParameter( "tel"));
                user.setStatut( request.getParameter( "statut"));
                user.setBlabla( request.getParameter( "blabla"));
                
                if ( !ejbClient.userExist(request.getParameter( "login"))) {
                    if ( ejbClient.addUser( user)) {
                        out.print("<p>Le user saisi a bien été ajouté !</p>");
                    } else {
                        out.print("<p>Un champs obligatoire n'a pas été rempli</p>");
                    }
                } else {
                    out.print("<p>Le user existe déjà !</p>");
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
