package servlets.backOffice;

import ebjs.ejbEvenementLocal;
import entities.Evenement;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "gestionEvenement", urlPatterns = {"/gestionEvenement"})
public class gestionEvenement extends HttpServlet {

    @EJB
    private ejbEvenementLocal ejbEvenement;

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionEvenement</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionEvenement at " + request.getContextPath() + "</h1>");
            
            out.println("<h2> Ajout d'un évènement </h2>");
            
            out.print("<form name='gestionEvenement'>");
            out.println("n° code * : <input type='text' name='code' placeholder='Saisir le n°' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Nom * : <input type='text' name='nom' placeholder='Saisir le nom'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Date de début * : <input type='text' name='dateDebut' placeholder='yyyy-MM-dd'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Date de fin * : <input type='text' name='dateFin' placeholder='yyyy-MM-dd'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Code promo * : <input type='text' name='codepromo' placeholder='Saisir le code promo'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("remise * : <input type='text' name='remise' placeholder='Saisir le pourcentage'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("image : <input type='text' name='image'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Blabla : <input type='text' name='blabla'/>");
            out.println("<br>");
            out.println("<br>");
            out.println( "<input type='submit' name='addEvenement' value='Ajouter' />");
            out.print("</form>");
            
            if ("Ajouter".equals(request.getParameter("addEvenement"))) {
                Evenement event = new Evenement();
                SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
                Date dateDebut = new Date();
                Date dateFin = new Date();
                try {
                    dateDebut = sdf.parse( request.getParameter( "dateDebut"));
                    dateFin = sdf.parse( request.getParameter( "dateFin"));
                } catch (ParseException ex) {
                    System.out.println( "Oops : ParseException : "+ ex.getMessage());
                }
                float remise = Float.parseFloat( request.getParameter( "remise").replace(",", "."));
                
                event.setCode( request.getParameter( "code"));
                event.setNom( request.getParameter( "nom"));
                event.setDateDebut( dateDebut);
                event.setDateFin( dateFin);
                event.setCodePromo( request.getParameter( "codepromo"));
                event.setPourcentage( remise);
                event.setImage( request.getParameter( "image"));
                event.setBlabla( request.getParameter( "blabla"));
                
                ejbEvenement.addEvent( event);
                out.print("<p>L'évènement saisi a bien été ajouté !</p>");
                
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
