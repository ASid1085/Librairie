
package servlets.backOffice;

import ebjs.ejbAdresseLocal;
import ebjs.ejbAuteur;
import ebjs.ejbEditeurLocal;
import entities.Adresse;
import entities.Editeur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "gestionEditeur", urlPatterns = {"/gestionEditeur"})
public class gestionEditeur extends HttpServlet {

    @EJB
    private ejbAdresseLocal ejbAdresse;

    @EJB
    private ejbEditeurLocal ejbEditeur;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionEditeur</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionEditeur at " + request.getContextPath() + "</h1>");
            
            out.println("<h2> Ajout d'un éditeur </h2>");
            
            out.print("<form name='gestionEditeur'>");
            out.println("Code * : <input type='text' name='code' placeholder='Saisir le code' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom * : <input type='text' name='nom' placeholder='Saisir le nom' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Mail * : <input type='text' name='mail' placeholder='Saisir le mail' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Tel : <input type='text' name='tel'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Adresse : <input type='text' name='adresse'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Blabla : <input type='text' name='blabla'/>");
            out.println("<br>");
            out.println("<br>");
            out.println( "<input type='submit' name='addEditeur' value='Ajouter' />");
            out.print("</form>");
            
            if ("Ajouter".equals(request.getParameter("addEditeur"))) {
                Editeur editeur = new Editeur();
                editeur.setCode( request.getParameter( "code"));
                editeur.setNom( request.getParameter( "nom"));
                Adresse adresse = ejbAdresse.findAdresse( request.getParameter( "adresse"));
                editeur.setAdresseEditeur( adresse);
                editeur.setMail( request.getParameter( "mail"));
                editeur.setTel( request.getParameter( "tel"));
                editeur.setBlabla( request.getParameter( "blabla"));
                
                if ( !ejbEditeur.checkDoublonEditeur( request.getParameter( "code"))) {
                    ejbEditeur.addEditeur( editeur);
                    out.print("<p>L'éditeur saisi a bien été ajouté !</p>");
                } else {
                    out.print("<p>L'éditeur existe déjà !</p>");
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
