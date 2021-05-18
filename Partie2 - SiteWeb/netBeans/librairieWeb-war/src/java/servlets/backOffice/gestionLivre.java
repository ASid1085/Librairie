package servlets.backOffice;

import ebjs.ejbAuteurLocal;
import ebjs.ejbEditeurLocal;
import ebjs.ejbLivreLocal;
import ebjs.ejbMotCleLocal;
import ebjs.ejbThemeLocal;
import ebjs.ejbTvaLocal;
import entities.Auteur;
import entities.Editeur;
import entities.Livre;
import entities.MotCle;
import entities.Theme;
import entities.Tva;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "gestionLivre", urlPatterns = {"/gestionLivre"})
public class gestionLivre extends HttpServlet {

    @EJB
    private ejbMotCleLocal ejbMotCle;

    @EJB
    private ejbThemeLocal ejbTheme;

    @EJB
    private ejbAuteurLocal ejbAuteur;

    @EJB
    private ejbEditeurLocal ejbEditeur;

    @EJB
    private ejbTvaLocal ejbTva;

    @EJB
    private ejbLivreLocal ejbLivre;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gestionLivre</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gestionLivre at " + request.getContextPath() + "</h1>");

            out.println("<h2> Ajout d'un livre </h2>");

            out.print("<form name='gestionLivre'>");
            out.println("n° ISBN * : <input type='text' name='isbn' placeholder='Saisir le n°' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Titre * : <input type='text' name='titre' placeholder='Saisir le titre' />");
            out.println("<br>");
            out.println("<br>");
            out.println("Sous-titre : <input type='text' name='soustitre'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Prix HT * : <input type='text' name='prixHt' placeholder='Saisir le prix'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Taux TVA * : <input type='text' name='tva' placeholder='Saisir le code'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Date d'édition * : <input type='text' name='dateEdition' placeholder='Saisir la date'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Image : <input type='text' name='image'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Résumé * : <input type='text' name='resume' placeholder='Saisir le résumé'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Nb pages * : <input type='text' name='nbpages' placeholder='Saisir un nombre'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Stock * : <input type='text' name='stock' placeholder='Saisir une qté'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Editeur * : <input type='text' name='editeur' placeholder='Saisir code éditeur'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Auteur 1 * : <input type='text' name='auteur1' placeholder='Saisir code auteur'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Auteur 2 : <input type='text' name='auteur2'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Theme 1 : <input type='text' name='theme1'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Theme 2 : <input type='text' name='theme2'/>");
            out.println("<br>");
            out.println("<br>");
            out.println(" Mot-clé 1 : <input type='text' name='motcle1'/>");
            out.println("<br>");
            out.println("<br>");
            out.println(" Mot-clé 2 : <input type='text' name='motcle2'/>");
            out.println("<br>");
            out.println("<br>");
            out.println(" Mot-clé 3 : <input type='text' name='motcle3'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Commentaire : <input type='text' name='blabla'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("Statut : <input type='text' name='statut'/>");
            out.println("<br>");
            out.println("<br>");
            out.println("<input type='submit' name='addLivre' value='Ajouter' /><span> ------- </span><input type='submit' name='updateLivre' value='Modifier' />");
            out.print("</form>");
            

            if ("Ajouter".equals(request.getParameter("addLivre"))) {
                Livre livre = new Livre();
                livre.setIsbn(request.getParameter("isbn"));
                livre.setTitre(request.getParameter("titre"));
                livre.setSousTitre(request.getParameter("soustitre"));
                livre.setPrixHT(Float.parseFloat(request.getParameter("prixHt").replace(',', '.')));
                livre.setDateEdition(request.getParameter("dateEdition"));
                livre.setImage(request.getParameter("image"));
                livre.setResume(request.getParameter("resume"));
                livre.setNbrePage(Integer.parseInt(request.getParameter("nbpages")));
                livre.setStock(Integer.parseInt(request.getParameter("stock")));
                livre.setBlabla(request.getParameter("blabla"));
                livre.setStatut(request.getParameter("statut"));

                Tva tva = ejbTva.findTva(request.getParameter("tva"));
                livre.setTvaLivre(tva);

                Collection<Auteur> ListAuteur = new ArrayList<Auteur>();
                Auteur auteur1 = ejbAuteur.findAuteur(request.getParameter("auteur1"));
                ListAuteur.add(auteur1);
                if ( !request.getParameter("auteur2").isEmpty()) {
                    Auteur auteur2 = ejbAuteur.findAuteur(request.getParameter("auteur2"));
                    ListAuteur.add(auteur2);
                }
                livre.setListAuteur(ListAuteur);

                Collection<Theme> ListTheme = new ArrayList<Theme>();
                if ( !request.getParameter("theme1").isEmpty()) {
                    Theme theme1 = ejbTheme.findTheme(request.getParameter("theme1"));
                    ListTheme.add(theme1);
                }
                if ( !request.getParameter("theme2").isEmpty()) {
                    Theme theme2 = ejbTheme.findTheme(request.getParameter("theme2"));
                    ListTheme.add(theme2);
                }
                livre.setListTheme(ListTheme);
                
                Editeur editeur = ejbEditeur.findEditeur(request.getParameter("editeur"));
                livre.setEditeur(editeur);

                Collection<MotCle> ListMotCle = new ArrayList<MotCle>();
                if ( !request.getParameter("motcle1").isEmpty()) {
                    MotCle motCle1 = ejbMotCle.findMotCle(request.getParameter("motcle1"));
                    ListMotCle.add(motCle1);
                }
                if ( !request.getParameter("motcle2").isEmpty()) {
                    MotCle motcle2 = ejbMotCle.findMotCle(request.getParameter("motcle2"));
                    ListMotCle.add(motcle2);
                }
                if ( !request.getParameter("motcle3").isEmpty()) {
                    MotCle motcle3 = ejbMotCle.findMotCle(request.getParameter("motcle3"));
                    ListMotCle.add(motcle3);
                }
                livre.setListMotCle(ListMotCle);

                if (!ejbLivre.checkDoublonLivre(request.getParameter("isbn"))) {
                    ejbLivre.addLivre(livre);
                    out.print("<p>Le livre saisi a bien été ajouté !</p>");
                } else {
                    out.print("<p>Le n° ISBN existe déjà !</p>");
                }
            }
            
            if ("Modifier".equals(request.getParameter("updateLivre"))) {
                Livre livre = new Livre();
                livre.setIsbn(request.getParameter("isbn"));
                livre.setTitre(request.getParameter("titre"));
                livre.setSousTitre(request.getParameter("soustitre"));
//                livre.setPrixHT(Float.parseFloat(request.getParameter("prixHt").replace(',', '.')));
                livre.setDateEdition(request.getParameter("dateEdition"));
                livre.setImage(request.getParameter("image"));
                livre.setResume(request.getParameter("resume"));
//                livre.setNbrePage(Integer.parseInt(request.getParameter("nbpages")));
//                livre.setStock(Integer.parseInt(request.getParameter("stock")));
                livre.setBlabla(request.getParameter("blabla"));
                livre.setStatut(request.getParameter("statut"));

                Tva tva = ejbTva.findTva(request.getParameter("tva"));
                livre.setTvaLivre(tva);

                Collection<Auteur> ListAuteur = new ArrayList<Auteur>();
                Auteur auteur1 = ejbAuteur.findAuteur(request.getParameter("auteur1"));
                ListAuteur.add(auteur1);
                if ( !request.getParameter("auteur2").isEmpty()) {
                    Auteur auteur2 = ejbAuteur.findAuteur(request.getParameter("auteur2"));
                    ListAuteur.add(auteur2);
                }
                livre.setListAuteur(ListAuteur);

                Collection<Theme> ListTheme = new ArrayList<Theme>();
                if ( !request.getParameter("theme1").isEmpty()) {
                    Theme theme1 = ejbTheme.findTheme(request.getParameter("theme1"));
                    ListTheme.add(theme1);
                }
                if ( !request.getParameter("theme2").isEmpty()) {
                    Theme theme2 = ejbTheme.findTheme(request.getParameter("theme2"));
                    ListTheme.add(theme2);
                }
                livre.setListTheme(ListTheme);
                
                Editeur editeur = ejbEditeur.findEditeur(request.getParameter("editeur"));
                livre.setEditeur(editeur);

                Collection<MotCle> ListMotCle = new ArrayList<MotCle>();
                if ( !request.getParameter("motcle1").isEmpty()) {
                    MotCle motCle1 = ejbMotCle.findMotCle(request.getParameter("motcle1"));
                    ListMotCle.add(motCle1);
                }
                if ( !request.getParameter("motcle2").isEmpty()) {
                    MotCle motcle2 = ejbMotCle.findMotCle(request.getParameter("motcle2"));
                    ListMotCle.add(motcle2);
                }
                if ( !request.getParameter("motcle3").isEmpty()) {
                    MotCle motcle3 = ejbMotCle.findMotCle(request.getParameter("motcle3"));
                    ListMotCle.add(motcle3);
                }
                livre.setListMotCle(ListMotCle);

                if (ejbLivre.checkDoublonLivre(request.getParameter("isbn"))) {
                   // ejbLivre.updateSrcImage(livre, request.getParameter("image"));
                   ejbLivre.updateLivre( livre);
                    out.print("<p>Le livre saisi a bien été modifié !</p>");
                } else {
                    out.print("<p>Le n° ISBN n'existe pas !</p>");
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
