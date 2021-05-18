package servlets.backOffice;

import ebjs.ejbAdresseLocal;
import entities.Adresse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "gestionAdresse", urlPatterns = {"/gestionAdresse"})
public class gestionAdresse extends HttpServlet {

    @EJB
    private ejbAdresseLocal ejbAdresse;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionAdresse</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionAdresse at " + request.getContextPath() + "</h1>");
            
            out.println("<h2> Ajout d'une adresse </h2>");
            
            out.print("<form name='gestionAdresse'>");
            out.println("n° code * : <input type='text' name='code' placeholder='Saisir le n°' />");
            out.println("<br>");
            out.println("<br>");
            out.println("libelle : <input type='text' name='libelle'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Civilité * : <input type='text' name='civilite' placeholder='M., Mme, Melle'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom * : <input type='text' name='nom' placeholder='Saisir le nom'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Prénom * : <input type='text' name='prenom' placeholder='Saisir le prénom'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("nomEntreprise : <input type='text' name='nomEntreprise'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("noRue * : <input type='text' name='noRue' placeholder='Saisir le n° de rue'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("rue * : <input type='text' name='rue' placeholder='Saisir le nom de rue'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Complément : <input type='text' name='complement'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("CP * : <input type='text' name='cp' placeholder='Saisie le CP'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Ville * : <input type='text' name='ville' placeholder='Saisir la ville'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Pays * : <input type='text' name='pays' placeholder='Saisir le pays'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("tel : <input type='text' name='tel'/>");
            out.println("<br>");
            out.println("<br>");
            out.println( "<input type='submit' name='addAdresse' value='Ajouter' />");
            out.print("</form>");
            
            if ("Ajouter".equals(request.getParameter("addAdresse"))) {
                Adresse adresse = new Adresse();
                
                adresse.setCode( request.getParameter( "code"));
                adresse.setLibelle( request.getParameter( "libelle"));
                adresse.setCivilite( request.getParameter( "civilite"));
                adresse.setNom( request.getParameter( "nom"));
                adresse.setPrenom( request.getParameter( "prenom"));
                adresse.setNomEntreprise( request.getParameter( "nomEntreprise"));
                adresse.setNoRue( request.getParameter( "noRue"));
                adresse.setRue( request.getParameter( "rue"));
                adresse.setComplement( request.getParameter( "complement"));
                adresse.setCp( request.getParameter( "cp"));
                adresse.setVille( request.getParameter( "ville"));
                adresse.setPays( request.getParameter( "pays"));
                adresse.setTel( request.getParameter( "tel"));
                
                if ( !ejbAdresse.checkDoublonAdresse( request.getParameter( "code"))) {
                    if ( ejbAdresse.addAdresse( adresse)) {
                        out.print("<p>L'adresse saisie a bien été ajoutée !</p>");
                    } else {
                        out.print("<p>Un champs obligatoire n'a pas été rempli</p>");
                    }
                } else {
                    out.print("<p>L'adresse existe déjà !</p>");
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
