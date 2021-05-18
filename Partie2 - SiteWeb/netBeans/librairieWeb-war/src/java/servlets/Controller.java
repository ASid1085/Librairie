package servlets;

import ebjs.*;
import entities.*;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    @EJB
    private ejbStatutCommentaireLocal ejbStatutCommentaireLocal;
    @EJB
    private ejbStatutsLocal ejbStatutsLocal;
    @EJB
    private ejbStatutCommandeLocal ejbStatutCommandeLocal;
    @EJB
    private ejbCommentaireLocal ejbCommentaireLocal;
    @EJB
    private ejbLigCommandeLocal ejbLigCommandeLocal;
    @EJB
    private ejbPaiementLocal ejbPaiementLocal;
    @EJB
    private ejbAdresseLocal ejbAdresseLocal;
    @EJB
    private ejbCommandeLocal ejbCommandeLocal;
    @EJB
    private ejbEvenementLocal ejbEvenementLocal;
    @EJB
    private ejbClientLocal ejbClientLocal;
    @EJB
    private ejbLigPanierLocal ejbLigPanierLocal;
    @EJB
    private ejbPanierLocal ejbPanierLocal;
    @EJB
    private ejbThemeLocal ejbThemeLocal;
    @EJB
    private ejbLivreLocal ejbLivreLocal;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ServletContext application = this.getServletContext();
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        Client userCurrent = null;
        String login = null;
        int qtePanier = 0;
        Cookie userConnect = getCookie(request.getCookies(), "cookieUser");
        if (userConnect != null) {
            request.setAttribute("userConnect", userConnect.getValue());
            login = userConnect.getValue();
            request.setAttribute("userLogin", login);
            SimpleDateFormat dt = new SimpleDateFormat("dd.MM.yy");
            request.setAttribute("userDate", dt.format(new Date()));
            request.setAttribute("connecter", true);
        } else {
            request.setAttribute("connecter", false);
        }
        Panier monPanier = null;
        float remiseEvent = 0;
        if (ejbEvenementLocal.testCurrentEvenement()) {
            remiseEvent = ejbEvenementLocal.currentEvenement().calculPourcentage();
        }

        // on determine ici la page par défaut à l'ouveture
        String url = "WEB-INF/Accueil.jsp";
        request.setAttribute("listSuggestions", ejbLivreLocal.list9Livres()); //on détermine ici ce qui pourra être afficher sur la page d'accueil
        if ("".equals(request.getParameter("page"))) {
            url = "WEB-INF/Accueil.jsp";
        request.setAttribute("listSuggestions", ejbLivreLocal.list9Livres()); //on détermine ici ce qui pourra être afficher sur la page d'accueil
        }
        

        //On dertermine ici la page correspondante au formulaire de contact au moment de cliquer sur le lien du footer
        if ("contact".equals(request.getParameter("page"))) {
            url = "WEB-INF/contactFormulaire.jsp";
        }

        //Gestion de la connection client dans la fenetre modal de la validation de commande
        if ("login".equals(request.getParameter("action"))) {
            String userLogin = request.getParameter("userLogin");
            String mdp = request.getParameter("mdp");
            //test si la saisie permet de se connecter 
            if (ejbClientLocal.checkConnexionUser(userLogin, mdp)) {
                Cookie c = new Cookie("cookieUser", userLogin);
                response.addCookie(c);
                request.setAttribute("userConnecter", true);
            } else {
                request.setAttribute("userLogin", userLogin);
            }
            request.setAttribute("loginError", ejbClientLocal.checkConnexionUser(userLogin, mdp));
            request.setAttribute("messageError", "Erreur : login OU mot de passe erroné");
            request.setAttribute("messageSucces", "Bonjour " + userLogin + " !<br>Nous sommes heureux de vous revoir !");
        }

        //Affichage du catalogue et de la liste des thèmes sur la page catalogue
        if ("catalogue".equals(request.getParameter("page"))) {
            url = "WEB-INF/Catalogue.jsp";
            request.setAttribute("listLivres", ejbLivreLocal.listLivres());
            request.setAttribute("listThemes", ejbThemeLocal.listThemes());

            if ("auteur".equals(request.getParameter("search"))) {
                request.setAttribute("research", true);
                request.setAttribute("titreSection", "Résultats de la recherche");
                request.setAttribute("listLivres", ejbLivreLocal.findLivreByAuteur(request.getParameter("saisie")));
            }

            if ("title".equals(request.getParameter("search"))) {
                request.setAttribute("research", true);
                request.setAttribute("titreSection", "Résultat de la recherche");
                request.setAttribute("listLivres", ejbLivreLocal.findLivreByTitle(request.getParameter("saisie")));
            }

            if ("isbn".equals(request.getParameter("search"))) {
                request.setAttribute("research", true);
                request.setAttribute("titreSection", "Résultat de la recherche");
                request.setAttribute("listLivres", ejbLivreLocal.findLivreByIsbnSearch(request.getParameter("saisie")));
            }

            if ("motCle".equals(request.getParameter("search"))) {
                request.setAttribute("research", true);
                request.setAttribute("titreSection", "Résultat de la recherche");
                request.setAttribute("listLivres", ejbLivreLocal.findLivreByMotCle(request.getParameter("saisie")));
            }
        }

        //Affichage des résultats d'une recherche selon le critère choisi (recherche multi-critère pas possible)   
        if ("resultSearch".equals(request.getParameter("page"))) {
            url = "WEB-INF/resultSearch.jsp";
            application.setAttribute("listLivres", ejbLivreLocal.listLivres());

            if ("theme".equals(request.getParameter("search"))) {
                request.setAttribute("listLivres", ejbLivreLocal.findLivreByTheme(request.getParameter("saisie")));
            }

            if ("price".equals(request.getParameter("search"))) {
                request.setAttribute("listLivres", ejbLivreLocal.findLivreByPriceTTC(parseFloat(request.getParameter("saisie"))));
            }
        }

        //Affichage de détail d'un livre
        if ("detail".equals(request.getParameter("page"))) {
            url = "WEB-INF/detailLivre.jsp";
            request.setAttribute("monLivre", ejbLivreLocal.findLivre(request.getParameter("isbn")));
            
            Collection<Commentaire> listCommentsBook = ejbCommentaireLocal.findCommentByBook(request.getParameter("isbn"));
            if (listCommentsBook.size() > 0) {
                request.setAttribute("commentMonLivre", true);
                request.setAttribute("listCommentsBook", listCommentsBook);
            } else {
                request.setAttribute("commentMonLivre", false);
            }
        }

        //On créé une ligne panier au moment du clique sur un livre dans le catalogue
        if ("addPanier".equals(request.getParameter("action"))) {

            if (login == null) { // Le login est null si la connexion au compte client n'a pas été tentée ou bien si elle a échoué
                /*
                ** Ici la gestion du panier se fait uniquement via la session et mouvement la hashMap en conséquence
                 */
                String idSession = session.getId();

                // Création du panier
                monPanier = (Panier) session.getAttribute("monPanier");
                if (monPanier == null) {
                    monPanier = new Panier();
                    session.setAttribute("monPanier", monPanier);
                }
                monPanier.setCode(idSession);
                monPanier.setdatePanier(new Date());
                monPanier.setDetailPanier(ejbPanierLocal.listContenuPanier());

                // Création ou modification de la ligne de panier du panier en cours
                String isbn = request.getParameter("isbn");
                LignePanier lp = new LignePanier();
                lp.setCode(isbn);
                lp.setLivreAjout(ejbLivreLocal.findLivre(isbn));
                lp.setQuantite(parseInt(request.getParameter("qtee")));
                lp.setPrixHT(lp.getLivreAjout().getPrixHT());
                lp.setTva(lp.getLivreAjout().getTvaLivre().getTaux());

                //Ajout de la ligne de panier à la HashMap pour l'affichage dans Panier.jsp
                ejbPanierLocal.add(lp, parseInt(request.getParameter("qtee")));

                //Sauvegarde du panier en session après modification et durée de vie déterminée
                session.setAttribute("monPanier", monPanier);
                session.setMaxInactiveInterval(86400); // 24 heures
            }

            if (login != null) { // Le login est not null si la connexion au compte a réussi
                /*
                ** Ici la gestion du panier se fait uniquement via la BDD
                 */

                //Si aucun panier rattaché au user est existant ==> création
                if (!ejbPanierLocal.checkDoublonPanier(login)) {
                    monPanier = new Panier();
                    monPanier.setCode(login);
                    monPanier.setdatePanier(new Date());
                    monPanier.setPanierDuClient(userCurrent);
                    //Si un panier en session existe déjà récupération du contenu session mettre en BDD
                    if (session.getAttribute("monPanier") != null) {
                        Collection<LignePanier> contenuSession = ejbPanierLocal.listContenuPanier();
                        for (LignePanier lp : contenuSession) {
                            LignePanier ligPanier = new LignePanier();
                            ligPanier.setCode(ejbLigPanierLocal.createCodeLignePanier());
                            ligPanier.setLivreAjout(lp.getLivreAjout());
                            ligPanier.setQuantite(lp.getQuantite());
                            ligPanier.setPrixHT(lp.getPrixHT());
                            ligPanier.setTva(lp.getTva());
                            ligPanier.setPanier(monPanier);
                            //Persistance de la ligne de panier en BDD
                            ejbLigPanierLocal.addLigPanier(ligPanier);
                            //Persistance de mon panier en BDD
                            ejbPanierLocal.updatePanier(monPanier);
                        }
                        ejbPanierLocal.clear();
                        session.removeAttribute("monPanier");
                    }
                    //J'ajoute le livre selectionné à l'ajout
                    String isbnAdd = request.getParameter("isbn");
                    Collection<LignePanier> panierCourant = ejbLigPanierLocal.listLPs(login);
                    boolean doublon = false;
                    String codeLig = null;
                    for (LignePanier lp : panierCourant) {
                        if (isbnAdd.equals(lp.getLivreAjout().getIsbn())) {
                            doublon = true;
                            codeLig = lp.getCode();
                        }
                    }
                    if (doublon) {
                        LignePanier ligPanier = ejbLigPanierLocal.findLigPanier(codeLig);
                        int oldQte = ligPanier.getQuantite();
                        ligPanier.setQuantite(oldQte + parseInt(request.getParameter("qtee")));
                        //Modification de ma ligne de panier en BDD
                        ejbLigPanierLocal.addLigPanier(ligPanier);
                        //Modification de mon panier en BDD
                        ejbPanierLocal.updatePanier(monPanier);
                    } else {
                        LignePanier ligPanier = new LignePanier();
                        ligPanier.setCode(ejbLigPanierLocal.createCodeLignePanier());
                        Livre l = ejbLivreLocal.findLivreByIsbn(isbnAdd);
                        ligPanier.setLivreAjout(l);
                        ligPanier.setQuantite(parseInt(request.getParameter("qtee")));
                        ligPanier.setPrixHT(l.getPrixHT());
                        ligPanier.setTva(l.getTvaLivre().getTaux());
                        ligPanier.setPanier(monPanier);
                        /*Persistance de mon panier en BDD*/
                        ejbLigPanierLocal.addLigPanier(ligPanier);
                        //Modification de mon panier en BDD
                        ejbPanierLocal.updatePanier(monPanier);
                    }
                    //Si un panier est rattaché au user ==> modification
                } else {
                    //On récupère le panier user de la BDD
                    monPanier = ejbPanierLocal.monPanier(login);
                    monPanier.setdatePanier(new Date());
                    // On test s'il y a une session en cours => Si oui on récupère les éléments à mettre dans le panier user
                    if (session.getAttribute("monPanier") != null) {
                        Collection<LignePanier> contenuSession = ejbPanierLocal.listContenuPanier();
                        Collection<LignePanier> panierCourant = ejbLigPanierLocal.listLPs(login);
                        // On récupère les éléments de la session pour les mettres en BDD
                        for (LignePanier lp : contenuSession) {
                            boolean doublon = false;
                            String codeLig = null;
                            String isbnSession = lp.getLivreAjout().getIsbn();
                            for (LignePanier lpc : panierCourant) {
                                if (isbnSession.equals(lpc.getLivreAjout().getIsbn())) {
                                    doublon = true;
                                    codeLig = lp.getCode();
                                }
                            }
                            if (doublon) {
                                LignePanier ligPanier = ejbLigPanierLocal.findLigPanier(codeLig);
                                int oldQte = ligPanier.getQuantite();
                                ligPanier.setQuantite(oldQte + lp.getQuantite());
                                //Modification de ma ligne de panier en BDD
                                ejbLigPanierLocal.addLigPanier(ligPanier);
                                //Modification de mon panier en BDD
                                ejbPanierLocal.updatePanier(monPanier);
                            } else {
                                LignePanier ligPanier = new LignePanier();
                                ligPanier.setCode(ejbLigPanierLocal.createCodeLignePanier());
                                //  Livre l = ejbLivreLocal.findLivreByIsbn(lp.getLivreAjout().getIsbn());
                                ligPanier.setLivreAjout(lp.getLivreAjout());
                                ligPanier.setQuantite(lp.getQuantite());
                                ligPanier.setPrixHT(lp.getPrixHT());
                                ligPanier.setTva(lp.getTva());
                                ligPanier.setPanier(monPanier);
                                /*Persistance de mon panier en BDD*/
                                ejbLigPanierLocal.addLigPanier(ligPanier);
                                //Modification de mon panier en BDD
                                ejbPanierLocal.updatePanier(monPanier);
                            }
                        }
                        ejbPanierLocal.clear();
                        session.removeAttribute("monPanier");
                    }
                    //J'ajoute le livre selectionné à l'ajout
                    String isbnAdd = request.getParameter("isbn");
                    monPanier = ejbPanierLocal.monPanier(login);
                    Collection<LignePanier> panierCourant = ejbLigPanierLocal.listLPs(login);
                    boolean doublon = false;
                    String codeLig = null;
                    for (LignePanier lp : panierCourant) {
                        if (isbnAdd.equals(lp.getLivreAjout().getIsbn())) {
                            doublon = true;
                            codeLig = lp.getCode();
                        }
                    }
                    if (doublon) {
                        LignePanier ligPanier = ejbLigPanierLocal.findLigPanier(codeLig);
                        int oldQte = ligPanier.getQuantite();
                        ligPanier.setQuantite(oldQte + ligPanier.getQuantite());
                        //Modification de ma ligne de panier en BDD
                        ejbLigPanierLocal.addLigPanier(ligPanier);
                        //Modification de mon panier en BDD
                        ejbPanierLocal.updatePanier(monPanier);
                    } else {
                        LignePanier ligPanier = new LignePanier();
                        ligPanier.setCode(ejbLigPanierLocal.createCodeLignePanier());
                        Livre l = ejbLivreLocal.findLivreByIsbn(isbnAdd);
                        ligPanier.setLivreAjout(l);
                        ligPanier.setQuantite(parseInt(request.getParameter("qtee")));
                        ligPanier.setPrixHT(l.getPrixHT());
                        ligPanier.setTva(l.getTvaLivre().getTaux());
                        ligPanier.setPanier(monPanier);
                        /*Persistance de mon panier en BDD*/
                        ejbLigPanierLocal.addLigPanier(ligPanier);
                        //Modification de mon panier en BDD
                        ejbPanierLocal.updatePanier(monPanier);
                    }
                }
            }
        }

        //Affichage du contenu du panier
        if ("panier".equals(request.getParameter("page"))) {
            url = "WEB-INF/Panier.jsp";

            if (login == null) { // Le login est null si la connexion au compte client n'a pas été tentée ou bien si elle a échoué
                /*
                ** Ici la gestion du panier se fait uniquement via la session et mouvement la hashMap en conséquence
                 */
                if (request.getParameter("add") != null) {
                    LignePanier ligPanier = ejbPanierLocal.findInMap(request.getParameter("add"));
                    //Correction de la quanité dans la HashMap
                    ejbPanierLocal.add(ligPanier);
                }
                if (request.getParameter("dec") != null) {
                    LignePanier ligPanier = ejbPanierLocal.findInMap(request.getParameter("dec"));
                    //Correction de la quantité dans la HashMap
                    ejbPanierLocal.dec(ligPanier);
                }
                if (request.getParameter("del") != null) {
                    LignePanier ligPanier = ejbPanierLocal.findInMap(request.getParameter("del"));
                    //Suppression dans la HashMap
                    ejbPanierLocal.del(ligPanier);
                }
                if (request.getParameter("clear") != null) {
                    //Suppression de toute la HashMap
                    ejbPanierLocal.clear();
                }
                request.setAttribute("panierVideSession", ejbPanierLocal.isEmpty());
                request.setAttribute("ligPanierSession", ejbPanierLocal.listContenuPanier());
            }

            if (login != null) { // Le login est not null si la connexion au compte a réussi
                /*
                ** Ici la gestion du panier se fait uniquement via la BDD
                 */
                if (!ejbPanierLocal.checkDoublonPanier(login)) {
                    monPanier = new Panier();
                    monPanier.setCode(login);
                    monPanier.setdatePanier(new Date());
                    monPanier.setPanierDuClient(userCurrent);
                    ejbPanierLocal.addPanier(monPanier);

                    //Si un panier en session existe déjà récupération du contenu session mettre en BDD
                    if (session.getAttribute("monPanier") != null) {
                        Collection<LignePanier> contenuSession = ejbPanierLocal.listContenuPanier();
                        for (LignePanier lp : contenuSession) {
                            LignePanier ligPanier = new LignePanier();
                            ligPanier.setCode(ejbLigPanierLocal.createCodeLignePanier());
                            ligPanier.setLivreAjout(lp.getLivreAjout());
                            ligPanier.setQuantite(lp.getQuantite());
                            ligPanier.setPrixHT(lp.getPrixHT());
                            ligPanier.setTva(lp.getTva());
                            ligPanier.setPanier(monPanier);
                            //Persistance de la ligne de panier en BDD
                            ejbLigPanierLocal.addLigPanier(ligPanier);
                            //Persistance de mon panier en BDD
                            ejbPanierLocal.updatePanier(monPanier);
                        }
                        ejbPanierLocal.clear();
                        session.removeAttribute("monPanier");
                    }
                } else {
                    //On récupère le panier user de la BDD
                    monPanier = ejbPanierLocal.monPanier(login);
                    monPanier.setdatePanier(new Date());
                    ejbPanierLocal.updatePanier(monPanier);
                    // On test s'il y a une session en cours => Si oui on récupère les éléments à mettre dans le panier user
                    if (session.getAttribute("monPanier") != null) {
                        Collection<LignePanier> contenuSession = ejbPanierLocal.listContenuPanier();
                        Collection<LignePanier> panierCourant = ejbLigPanierLocal.listLPs(login);
                        // On récupère les éléments de la session pour les mettres en BDD
                        for (LignePanier lp : contenuSession) {
                            boolean doublon = false;
                            String codeLig = null;
                            String isbnSession = lp.getLivreAjout().getIsbn();
                            for (LignePanier lpc : panierCourant) {
                                if (isbnSession.equals(lpc.getLivreAjout().getIsbn())) {
                                    doublon = true;
                                    codeLig = lpc.getCode();
                                }
                            }
                            if (doublon) {
                                LignePanier ligPanier = ejbLigPanierLocal.findLigPanier(codeLig);
                                int oldQte = ligPanier.getQuantite();
                                ligPanier.setQuantite(oldQte + lp.getQuantite());
                                //Modification de ma ligne de panier en BDD
                                ejbLigPanierLocal.addLigPanier(ligPanier);
                                //Modification de mon panier en BDD
                                ejbPanierLocal.updatePanier(monPanier);
                            } else {
                                LignePanier ligPanier = new LignePanier();
                                ligPanier.setCode(ejbLigPanierLocal.createCodeLignePanier());
                                //  Livre l = ejbLivreLocal.findLivreByIsbn(lp.getLivreAjout().getIsbn());
                                ligPanier.setLivreAjout(lp.getLivreAjout());
                                ligPanier.setQuantite(lp.getQuantite());
                                ligPanier.setPrixHT(lp.getPrixHT());
                                ligPanier.setTva(lp.getTva());
                                ligPanier.setPanier(monPanier);
                                /*Persistance de mon panier en BDD*/
                                ejbLigPanierLocal.addLigPanier(ligPanier);
                                //Modification de mon panier en BDD
                                ejbPanierLocal.updatePanier(monPanier);
                            }
                        }
                        ejbPanierLocal.clear();
                        session.removeAttribute("monPanier");
                    }
                }
                Collection<LignePanier> contenuBdd = ejbLigPanierLocal.listLPs(login);
                LignePanier ligPanier = new LignePanier();
                if (request.getParameter("add") != null) {
                    for (LignePanier lp : contenuBdd) {
                        if (request.getParameter("add").equals(lp.getCode())) {
                            ligPanier = ejbLigPanierLocal.findLigPanier(lp.getCode());
                            int oldQte = ligPanier.getQuantite();
                            ligPanier.setQuantite(oldQte + 1);
                            ejbLigPanierLocal.addLigPanier(ligPanier);
                        }
                    }
                }

                if (request.getParameter("dec") != null) {
                    for (LignePanier lp : contenuBdd) {
                        if (request.getParameter("dec").equals(lp.getCode())) {
                            ligPanier = ejbLigPanierLocal.findLigPanier(lp.getCode());
                            int oldQte = ligPanier.getQuantite();
                            ligPanier.setQuantite(oldQte - 1);
                            if ((oldQte - 1) < 1) {
                                //Suppression de la ligne panier en BDD
                                ejbLigPanierLocal.deleteLigPanier(ligPanier, login);
                            } else {
                                //Correction de la quantité de la ligne panier en BDD
                                ejbLigPanierLocal.addLigPanier(ligPanier);
                            }
                        }
                    }
                }

                if (request.getParameter("del") != null) {
                    for (LignePanier lp : contenuBdd) {
                        if (request.getParameter("del").equals(lp.getCode())) {
                            ligPanier = ejbLigPanierLocal.findLigPanier(lp.getCode());
                            //Suppression de la ligne de panier en BDD
                            ejbLigPanierLocal.deleteLigPanier(ligPanier, login);
                        }
                    }
                }

                if (request.getParameter("clear") != null) {
                    ejbLigPanierLocal.deleteAllLigPanier(login);
                }
            }
            Collection<LignePanier> contenuBdd = ejbLigPanierLocal.listLPs(login);
            request.setAttribute("panierVideUser", contenuBdd.isEmpty());
            request.setAttribute("ligPanierUser", contenuBdd);

            //Gestion de la remise par les évènements
            request.setAttribute("testEventCurrent", ejbEvenementLocal.testCurrentEvenement());
            request.setAttribute("eventCurrent", ejbEvenementLocal.currentEvenement());
            float totalTtcPanierSession = ejbPanierLocal.totalTtcPanier();
            float totalTtcRemiseSession = totalTtcPanierSession * (1 - remiseEvent);
            String mntTotalTtcRemiseSession = String.format("%.2f", totalTtcRemiseSession);
            request.setAttribute("mntTotalTtcRemiseSession", mntTotalTtcRemiseSession);
            float totalTtcPanierUser = ejbPanierLocal.totalTtcPanierUser(login);
            float totalTtcRemiseUser = totalTtcPanierUser * (1 - remiseEvent);
            String mntTotalTtcRemiseUser = String.format("%.2f", totalTtcRemiseUser);
            request.setAttribute("mntTotalTtcRemiseUser", mntTotalTtcRemiseUser);
        }

        //On affiche la page dédiée à la création d'un nouveau compte utilisateur
        if ("createUser".equals(request.getParameter("page"))) {
            url = "WEB-INF/createUser.jsp";
        }

        //On accède à la page de création d'un nouveau client
        if ("Ok".equals(request.getParameter("doCreate"))) {
            String logConnexion = request.getParameter("logConnexion");
            String email = request.getParameter("email");
            if (!ejbClientLocal.userExist(logConnexion)) {
                if (!ejbClientLocal.emailExist(email)) {
                    String mdp1 = request.getParameter("mdp");
                    String mdp2 = request.getParameter("checkMdp");
                    if (mdp2.equals(mdp1)) {
                        Client user = new Client();
                        user.setCivilite(request.getParameter("civilite"));
                        user.setNom(request.getParameter("nom"));
                        user.setPrenom(request.getParameter("prenom"));
                        user.setEmail(request.getParameter("email"));
                        user.setTel(request.getParameter("telephone"));
                        user.setLogin(request.getParameter("logConnexion"));
                        user.setMdp(mdp2);
                        ejbClientLocal.addUser(user);
                        request.setAttribute("checkCreateCompte", true);
                    } else {
                        request.setAttribute("recupNom", request.getParameter("nom"));
                        request.setAttribute("recupPrenom", request.getParameter("prenom"));
                        request.setAttribute("recupEmail", request.getParameter("email"));
                        request.setAttribute("recupLog", request.getParameter("logConnexion"));
                        request.setAttribute("recupTel", request.getParameter("telephone"));
                        request.setAttribute("checkCreateCompte", false);
                        request.setAttribute("errorCreateCompte", "Les champs 'mot de passe' ne correspondent pas !");
                    }
                } else {
                    request.setAttribute("recupNom", request.getParameter("nom"));
                    request.setAttribute("recupPrenom", request.getParameter("prenom"));
                    request.setAttribute("recupLog", request.getParameter("logConnexion"));
                    request.setAttribute("recupTel", request.getParameter("telephone"));
                    request.setAttribute("checkCreateCompte", false);
                    request.setAttribute("errorCreateCompte", "Cette adresse email est déjà liée à un compte utilisateur !");
                }
            } else {
                request.setAttribute("recupNom", request.getParameter("nom"));
                request.setAttribute("recupPrenom", request.getParameter("prenom"));
                request.setAttribute("recupEmail", request.getParameter("email"));
                request.setAttribute("recupTel", request.getParameter("telephone"));
                request.setAttribute("checkCreateCompte", false);
                request.setAttribute("errorCreateCompte", "Ce login existe déjà !");
            }
        }

        //On affichela page dédiée à la gestion du compte client
        if ("monCompte".equals(request.getParameter("page"))) {
            url = "WEB-INF/compteClient.jsp";

            Client user = null;
            if (userConnect != null) {
                login = userConnect.getValue();
                user = ejbClientLocal.findUser(login);
            } else {
                request.setAttribute("connecter", false);
            }

            if (user == null) {
                request.setAttribute("connecter", false);
            } else {
                request.setAttribute("connecter", true);
                if ("mesInfos".equals(request.getParameter("sousPage"))) {
                    request.setAttribute("mesInfos", true);
                    request.setAttribute("upCivilite", user.getCivilite());
                    request.setAttribute("upNom", user.getNom());
                    request.setAttribute("upPrenom", user.getPrenom());
                    request.setAttribute("upEmail", user.getEmail());
                    request.setAttribute("upLog", user.getLogin());
                    request.setAttribute("upTel", user.getTel());
                    Collection<Adresse> listAdrsUser = ejbClientLocal.findAdresseByUser(user.getLogin());
                    request.setAttribute("listAdrsEmpty", listAdrsUser.isEmpty());
                }
                if ("mesCommandes".equals(request.getParameter("sousPage"))) {
                    request.setAttribute("mesCdes", true);
                    Collection<Commande> listCdesUser = ejbCommandeLocal.findCommandeByUser(login);
                    request.setAttribute("listCdesUser", listCdesUser);
                    request.setAttribute("listCdesEmpty", listCdesUser.isEmpty());
                    Statuts statutCourrent = ejbStatutsLocal.findStatuts("ST01");
                    request.setAttribute("statutCde", statutCourrent.getLibelle());

                    //Gestion de la remise par les évènements
                    request.setAttribute("testEventCurrent", ejbEvenementLocal.testCurrentEvenement());
                    request.setAttribute("eventCurrent", ejbEvenementLocal.currentEvenement().getPourcentageAffichage());
                    request.setAttribute("eventCurrentName", ejbEvenementLocal.currentEvenement().getCodePromo());
                }

                if ("detailCde".equals(request.getParameter("action"))) {
                    request.setAttribute("monDetailCde", true);
                    String numCde = request.getParameter("numCde");
                    Collection<LigneCommande> contenuCde = ejbCommandeLocal.listLigCommandeByNumCde(numCde);
                    request.setAttribute("listDetailCde", contenuCde);
                    request.setAttribute("numeroCde", numCde);
                    Commande cde = ejbCommandeLocal.findCommande(numCde);
                    request.setAttribute("cdePaiement", cde.getPaiement().getMoyenPaiement());
                    request.setAttribute("cdeTotalHt", cde.getMntTotalCdeHtAff());
                    request.setAttribute("cdeTotalTva", cde.getMntTotalCdeTvaAff());
                    request.setAttribute("cdeTotalTtc", cde.getMntTotalCdeTtcAff());
                    request.setAttribute("cdeFraisLiv", cde.getForfaitLivAff());
                    request.setAttribute("cdeTotalPaye", cde.getMntTotalCdePayeAff());
                }

                if ("renouvellement".equals(request.getParameter("action"))) {
                    String numCde = request.getParameter("numCde");
                    Collection<LigneCommande> contenuCde = ejbCommandeLocal.listLigCommandeByNumCde(numCde);
                    for (LigneCommande lc : contenuCde) {

                        //Si aucun panier rattaché au user est existant ==> création
                        if (!ejbPanierLocal.checkDoublonPanier(login)) {
                            monPanier = new Panier();
                            monPanier.setCode(login);
                            monPanier.setdatePanier(new Date());
                            monPanier.setPanierDuClient(userCurrent);

                            //J'ajoute le livre selectionné à l'ajout
                            String isbnAdd = lc.getLivreCommande().getIsbn();
                            LignePanier ligPanier = new LignePanier();
                            ligPanier.setCode(ejbLigPanierLocal.createCodeLignePanier());
                            Livre l = ejbLivreLocal.findLivreByIsbn(isbnAdd);
                            ligPanier.setLivreAjout(l);
                            ligPanier.setQuantite(lc.getQuantite());
                            ligPanier.setPrixHT(l.getPrixHT());
                            ligPanier.setTva(l.getTvaLivre().getTaux());
                            ligPanier.setPanier(monPanier);
                            //Persistance de la ligne panier en BDD
                            ejbLigPanierLocal.addLigPanier(ligPanier);
                            //Modification de mon panier en BDD
                            ejbPanierLocal.updatePanier(monPanier);

                            //Si un panier est rattaché au user ==> modification
                        } else {
                            //On récupère le panier user de la BDD
                            monPanier = ejbPanierLocal.monPanier(login);
                            monPanier.setdatePanier(new Date());
                            //J'ajoute le livre selectionné à l'ajout
                            String isbnAdd = lc.getLivreCommande().getIsbn();
                            monPanier = ejbPanierLocal.monPanier(login);
                            Collection<LignePanier> panierCourant = ejbLigPanierLocal.listLPs(login);
                            boolean doublon = false;
                            String codeLig = null;
                            for (LignePanier lp : panierCourant) {
                                if (isbnAdd.equals(lp.getLivreAjout().getIsbn())) {
                                    doublon = true;
                                    codeLig = lp.getCode();
                                }
                            }
                            if (doublon) {
                                LignePanier ligPanier = ejbLigPanierLocal.findLigPanier(codeLig);
                                int oldQte = ligPanier.getQuantite();
                                ligPanier.setQuantite(oldQte + lc.getQuantite());
                                //Modification de ma ligne de panier en BDD
                                ejbLigPanierLocal.addLigPanier(ligPanier);
                                //Modification de mon panier en BDD
                                ejbPanierLocal.updatePanier(monPanier);
                            } else {
                                LignePanier ligPanier = new LignePanier();
                                ligPanier.setCode(ejbLigPanierLocal.createCodeLignePanier());
                                Livre l = ejbLivreLocal.findLivreByIsbn(isbnAdd);
                                ligPanier.setLivreAjout(l);
                                ligPanier.setQuantite(lc.getQuantite());
                                ligPanier.setPrixHT(l.getPrixHT());
                                ligPanier.setTva(l.getTvaLivre().getTaux());
                                ligPanier.setPanier(monPanier);
                                /*Persistance de mon panier en BDD*/
                                ejbLigPanierLocal.addLigPanier(ligPanier);
                                //Modification de mon panier en BDD
                                ejbPanierLocal.updatePanier(monPanier);
                            }
                        }
                    }
                }

                if ("mesCommentaires".equals(request.getParameter("sousPage"))) {
                    request.setAttribute("mesComments", true);
                    Collection<LigneCommande> AllBooksCommand = ejbCommandeLocal.listLigCommandeByUser(login);
                    Collection<Livre> booksCommand = new ArrayList<>();
                    if (!AllBooksCommand.isEmpty()) {
                        for (LigneCommande lc : AllBooksCommand) {
                            String isbn = lc.getLivreCommande().getIsbn();
                            boolean doublon = false;
                            for (Livre b : booksCommand) {
                                if (isbn.equals(b.getIsbn())) {
                                    doublon = true;
                                }
                            }
                            if (!doublon) {
                                booksCommand.add(lc.getLivreCommande());
                            }
                        }
                    }
                    request.setAttribute("booksCommand", booksCommand);
                    request.setAttribute("booksCommandEmpty", booksCommand.isEmpty());

                    if ("detailComment".equals(request.getParameter("action"))) {
                        request.setAttribute("note0", true);
                        request.setAttribute("detailComment", true);
                        String isbnRec = request.getParameter("isbn");
                        request.setAttribute("isbnRec", isbnRec);

                        if (ejbCommentaireLocal.findCommentByUser(login) != null) {
                            Collection<Commentaire> listCommentsUser = ejbCommentaireLocal.findCommentByUser(login);
                            Commentaire commentBook = null;
                            for (Commentaire c : listCommentsUser) {
                                if (c.getLivre().getIsbn().equals(isbnRec)) {
                                    request.setAttribute("commentFind", true);
                                    commentBook = c;
                                    request.setAttribute("bookComment", commentBook.getTexte());
                                    String bookNote = commentBook.getNote();
                                    if ("1".equals(bookNote)) {
                                        request.setAttribute("note1", true);
                                        request.setAttribute("note0", false);
                                    } else if ("2".equals(bookNote)) {
                                        request.setAttribute("note2", true);
                                        request.setAttribute("note0", false);
                                    } else if ("3".equals(bookNote)) {
                                        request.setAttribute("note3", true);
                                        request.setAttribute("note0", false);
                                    } else if ("4".equals(bookNote)) {
                                        request.setAttribute("note4", true);
                                        request.setAttribute("note0", false);
                                    } else if ("5".equals(bookNote)) {
                                        request.setAttribute("note5", true);
                                        request.setAttribute("note0", false);
                                    }
                                }
                            }
                        }

                        if ("commenter".equals(request.getParameter("doIt"))) {
                            Commentaire comment = new Commentaire();
                            comment.setCode(ejbCommentaireLocal.createCodeCommentaire());
                            comment.setTexte(request.getParameter("comment"));
                            comment.setNote(request.getParameter("note"));
                            comment.setDate(new Date());
                            comment.setIp(comment.recupAdresseIp());
                            comment.setClient(ejbClientLocal.findUser(login));
                            comment.setLivre(ejbLivreLocal.findLivreByIsbn(isbnRec));
                            ejbCommentaireLocal.addComment(comment);

//                            Ajout du statut "non modéré" au commentaire laissé
//                            StatutCommentaire sco = new StatutCommentaire();
//                            sco.setDateStatut(new Date());
//                            sco.setStatut(ejbStatutsLocal.findStatuts("ST50"));
//                            sco.setCommentaire(comment);
//                            Persistance du statut dans la BDD
//                            ejbStatutCommentaireLocal.addStatutCommentaire(sco);
                            request.setAttribute("commentAdd", true);
                        }
                    }
                }
            }
        }

        //On accède à la page de création d'une nouvelle adresse
        if ("Ajouter".equals(request.getParameter("doCreate"))) {
            String logConnexion = request.getParameter("logConnexion");
            String email = request.getParameter("email");
            if (!ejbClientLocal.userExist(logConnexion)) {
                if (!ejbClientLocal.emailExist(email)) {
                    String mdp1 = request.getParameter("mdp");
                    String mdp2 = request.getParameter("checkMdp");
                    if (mdp2.equals(mdp1)) {
                        Client user = new Client();
                        user.setCivilite(request.getParameter("civilite"));
                        user.setNom(request.getParameter("nom"));
                        user.setPrenom(request.getParameter("prenom"));
                        user.setEmail(request.getParameter("email"));
                        user.setTel(request.getParameter("telephone"));
                        user.setLogin(request.getParameter("logConnexion"));
                        user.setMdp(mdp2);
                        ejbClientLocal.addUser(user);
                        request.setAttribute("checkCreateCompte", true);
                    } else {
                        request.setAttribute("recupNom", request.getParameter("nom"));
                        request.setAttribute("recupPrenom", request.getParameter("prenom"));
                        request.setAttribute("recupEmail", request.getParameter("email"));
                        request.setAttribute("recupLog", request.getParameter("logConnexion"));
                        request.setAttribute("recupTel", request.getParameter("telephone"));
                        request.setAttribute("checkCreateCompte", false);
                        request.setAttribute("errorCreateCompte", "Les champs 'mot de passe' ne correspondent pas !");
                    }
                } else {
                    request.setAttribute("recupNom", request.getParameter("nom"));
                    request.setAttribute("recupPrenom", request.getParameter("prenom"));
                    request.setAttribute("recupLog", request.getParameter("logConnexion"));
                    request.setAttribute("recupTel", request.getParameter("telephone"));
                    request.setAttribute("checkCreateCompte", false);
                    request.setAttribute("errorCreateCompte", "Cette adresse email est déjà liée à un compte utilisateur !");
                }
            } else {
                request.setAttribute("recupNom", request.getParameter("nom"));
                request.setAttribute("recupPrenom", request.getParameter("prenom"));
                request.setAttribute("recupEmail", request.getParameter("email"));
                request.setAttribute("recupTel", request.getParameter("telephone"));
                request.setAttribute("checkCreateCompte", false);
                request.setAttribute("errorCreateCompte", "Ce login existe déjà !");
            }
        }

        //On affiche la page dédiée à la gestion des adresses utiles à la commande
        if ("adresses".equals(request.getParameter("page"))) {
            url = "WEB-INF/adresseCommande.jsp";

            Client user = null;
            if (userConnect != null) {
                login = userConnect.getValue();
                request.setAttribute("user", login);
                user = ejbClientLocal.findUser(login);
                Collection<Adresse> listAdrsUser = ejbClientLocal.findAdresseByUser(user.getLogin());
                request.setAttribute("listAdrsUser", listAdrsUser);
                request.setAttribute("listAdrsEmpty", listAdrsUser.isEmpty());
            }

            //Gestion des adresses de livraison et de facturation de la commande
            if (request.getParameter("adrSelectLiv") != null) {
                if (request.getParameter("adrSelectFact") != null) {
                    String adrSelectFact = request.getParameter("adrSelectFact");
                    Adresse adresSelectFact = ejbAdresseLocal.findAdresse(adrSelectFact);
                    if (adresSelectFact != null) {
                        request.setAttribute("adrFactSelectionner", true);
                        request.setAttribute("recupFacturation", adresSelectFact.getCode());
                    }
                }
                String adrSelectLiv = request.getParameter("adrSelectLiv");
                Adresse adresSelectLiv = ejbAdresseLocal.findAdresse(adrSelectLiv);
                if (adresSelectLiv != null) {
                    request.setAttribute("adrLivSelect", true);
                    request.setAttribute("recupAdrLivCode", adrSelectLiv);
                    request.setAttribute("recupAdrLivNom", adresSelectLiv.getNom());
                    request.setAttribute("recupAdrLivPrenom", adresSelectLiv.getPrenom());
                    if (adresSelectLiv.getNomEntreprise() == null || adresSelectLiv.getNomEntreprise().isEmpty()) {
                        request.setAttribute("adrLivEntrepriseNull", true);
                    } else {
                        request.setAttribute("adrLivEntrepriseNull", false);
                        request.setAttribute("recupAdrLivEntreprise", adresSelectLiv.getNomEntreprise());
                    }
                    request.setAttribute("recupAdrLivNum", adresSelectLiv.getNoRue());
                    request.setAttribute("recupAdrLivRue", adresSelectLiv.getRue());
                    if (adresSelectLiv.getComplement() == null || adresSelectLiv.getComplement().isEmpty()) {
                        request.setAttribute("adrLivComplementNull", true);
                    } else {
                        request.setAttribute("adrLivComplementNull", false);
                        request.setAttribute("recupAdrLivComplement", adresSelectLiv.getComplement());
                    }
                    request.setAttribute("recupAdrLivCp", adresSelectLiv.getCp());
                    request.setAttribute("recupAdrLivVille", adresSelectLiv.getVille());
                    request.setAttribute("recupAdrLivPays", adresSelectLiv.getPays());
                    request.setAttribute("recupAdrLivLibelle", adresSelectLiv.getLibelle());
                    request.setAttribute("recupAdrLivTel", adresSelectLiv.getTel());
                    request.setAttribute("okPaiementLiv", true);
                } else {
                    request.setAttribute("okPaiementLiv", false);
                }
            }
            if (request.getParameter("adrSelectFact") != null) {
                if (request.getParameter("adrSelectLiv") != null) {
                    String adrSelectLiv = request.getParameter("adrSelectLiv");
                    Adresse adresSelectLiv = ejbAdresseLocal.findAdresse(adrSelectLiv);
                    if (adresSelectLiv != null) {
                        request.setAttribute("adrLivSelectionner", true);
                        request.setAttribute("recupLivraison", adresSelectLiv.getCode());
                    }
                }
                String adrSelectFact = request.getParameter("adrSelectFact");
                Adresse adresSelectFact = ejbAdresseLocal.findAdresse(adrSelectFact);
                if (adresSelectFact != null) {
                    request.setAttribute("adrFactSelect", true);
                    request.setAttribute("recupAdrFactCode", adrSelectFact);
                    request.setAttribute("recupAdrFactNom", adresSelectFact.getNom());
                    request.setAttribute("recupAdrFactPrenom", adresSelectFact.getPrenom());
                    if (adresSelectFact.getNomEntreprise() == null || adresSelectFact.getNomEntreprise().isEmpty()) {
                        request.setAttribute("adrFactEntrepriseNull", true);
                    } else {
                        request.setAttribute("adrFactEntrepriseNull", false);
                        request.setAttribute("recupAdrFactEntreprise", adresSelectFact.getNomEntreprise());
                    }
                    request.setAttribute("recupAdrFactNum", adresSelectFact.getNoRue());
                    request.setAttribute("recupAdrFactRue", adresSelectFact.getRue());
                    if (adresSelectFact.getComplement() == null || adresSelectFact.getComplement().isEmpty()) {
                        request.setAttribute("adrFactComplNull", true);
                    } else {
                        request.setAttribute("adrFactComplNull", false);
                        request.setAttribute("recupAdrFactComplNull", adresSelectFact.getComplement());
                    }
                    request.setAttribute("recupAdrFactCp", adresSelectFact.getCp());
                    request.setAttribute("recupAdrFactVille", adresSelectFact.getVille());
                    request.setAttribute("recupAdrFactPays", adresSelectFact.getPays());
                    request.setAttribute("recupAdrFactLibelle", adresSelectFact.getLibelle());
                    request.setAttribute("recupAdrFactTel", adresSelectFact.getTel());
                    request.setAttribute("okPaiementFact", true);
                } else {
                    request.setAttribute("okPaiementFact", false);
                }
            }

            //Gestion de l'affichage du récap commande
            Collection<LignePanier> contenuBdd = ejbLigPanierLocal.listLPs(login);
            request.setAttribute("ligPanierUser", contenuBdd);

            //Gestion de la remise par les évènements
            request.setAttribute("testEventCurrent", ejbEvenementLocal.testCurrentEvenement());
            request.setAttribute("eventCurrent", ejbEvenementLocal.currentEvenement().getPourcentageAffichage());
            request.setAttribute("eventCurrentName", ejbEvenementLocal.currentEvenement().getCodePromo());

            // Gestion du montant HT du panier (avec ou sans remise)
            float totatHtPanierUser = ejbPanierLocal.totalHtPanierUser(login);
            float totalHtRemiseUser = totatHtPanierUser * (1 - remiseEvent);
            String mntTotalHtRemiseUser = String.format("%.2f", totalHtRemiseUser);
            request.setAttribute("mntTotalHtRemiseUser", mntTotalHtRemiseUser);

            // Gestion du montant de la TVA du panier (avec ou sans remise)
            float totatTvaPanierUser = ejbPanierLocal.totalTvaPanierUser(login);
            float totalTvaRemiseUser = totatTvaPanierUser * (1 - remiseEvent);
            String mntTotalTvaRemiseUser = String.format("%.2f", totalTvaRemiseUser);
            request.setAttribute("mntTotalTvaRemiseUser", mntTotalTvaRemiseUser);

            // Gestion du montant TTC du panier (avec ou sans remise)
            float totalTtcPanierUser = ejbPanierLocal.totalTtcPanierUser(login);
            float totalTtcRemiseUser = totalTtcPanierUser * (1 - remiseEvent);
            String mntTotalTtcRemiseUser = String.format("%.2f", totalTtcRemiseUser);
            request.setAttribute("mntTotalTtcRemiseUser", mntTotalTtcRemiseUser);

            // Gestion du montant total à payer du panier (avec ou sans frais de port)
            float fraisPort = 10;
            if (totalTtcRemiseUser >= 20) {
                fraisPort = 0;
            }
            request.setAttribute("fraisPortUrl", fraisPort);
            request.setAttribute("fraisPort", String.format("%.2f", fraisPort));
            float totalAPayer = totalTtcRemiseUser + fraisPort;
            request.setAttribute("totalAPayerValue", totalAPayer);
            request.setAttribute("totalAPayerAffichage", String.format("%.2f", totalAPayer));
        }

        //On affiche la page dédiée au carnet d'adresse du user
        if ("mesAdresses".equals(request.getParameter("sousPage"))) {
            url = "WEB-INF/ccMesAdresses.jsp";

            Client user = null;
            if (userConnect != null) {
                login = userConnect.getValue();
                user = ejbClientLocal.findUser(login);
            } else {
                request.setAttribute("connecter", false);
            }

            if (user != null) {
                request.setAttribute("connecter", true);

                //Création ou modification de l'adresse
                if ("enregistrerAdr".equals(request.getParameter("action"))) {
                    Adresse adr = null;
                    if (request.getParameter("adrSelectNew") != null) {
                        adr = ejbAdresseLocal.findAdresse(request.getParameter("adrSelectNew"));
                    }
                    if (adr != null) {
                        adr.setNom(request.getParameter("nom"));
                        adr.setPrenom(request.getParameter("prenom"));
                        adr.setNomEntreprise(request.getParameter("nomEntreprise"));
                        adr.setNoRue(request.getParameter("numRue"));
                        adr.setRue(request.getParameter("rue"));
                        adr.setComplement(request.getParameter("complement"));
                        adr.setCp(request.getParameter("cp"));
                        adr.setVille(request.getParameter("ville"));
                        adr.setPays(request.getParameter("pays"));
                        adr.setTel(request.getParameter("tel"));
                        ejbAdresseLocal.updateAdresse(adr);
                        request.setAttribute("checkUpAdr", true);
                    } else {
                        adr = new Adresse();
                        adr.setCode(login.toUpperCase() + ejbAdresseLocal.createCodeAdresse());
                        adr.setCivilite(request.getParameter("civilite"));
                        adr.setNom(request.getParameter("nom"));
                        adr.setPrenom(request.getParameter("prenom"));
                        adr.setNomEntreprise(request.getParameter("nomEntreprise"));
                        adr.setNoRue(request.getParameter("numRue"));
                        adr.setRue(request.getParameter("rue"));
                        adr.setComplement(request.getParameter("complement"));
                        adr.setCp(request.getParameter("cp"));
                        adr.setVille(request.getParameter("ville"));
                        adr.setPays(request.getParameter("pays"));
                        adr.setLibelle(request.getParameter("libelle"));
                        adr.setTel(request.getParameter("tel"));
                        adr.setClient(user);
                        if (ejbClientLocal.checkDoublonLibelle(login, adr.getLibelle())) {
                            request.setAttribute("checkUpAdr", false);
                            request.setAttribute("errorUpAdr", "Le nom choisi pour nommer cette adresse existe déjà ...");
                            request.setAttribute("recupAdrNom", adr.getNom());
                            request.setAttribute("recupAdrPrenom", adr.getPrenom());
                            request.setAttribute("recupAdrEntreprise", adr.getNomEntreprise());
                            request.setAttribute("recupAdrNum", adr.getNoRue());
                            request.setAttribute("recupAdrRue", adr.getRue());
                            request.setAttribute("recupAdrComplement", adr.getComplement());
                            request.setAttribute("recupAdrCp", adr.getCp());
                            request.setAttribute("recupAdrVille", adr.getVille());
                            request.setAttribute("recupAdrPays", adr.getPays());
                            request.setAttribute("recupAdrTel", adr.getTel());
                        } else {
                            ejbAdresseLocal.addAdresse(adr);
                            request.setAttribute("checkUpAdr", true);
                        }
                    }
                }

                //Suppression de l'adresse
                if ("supprimerAdr".equals(request.getParameter("action"))) {
                    Adresse adr = null;
                    if (request.getParameter("adrSelectNew") != null) {
                        adr = ejbAdresseLocal.findAdresse(request.getParameter("adrSelectNew"));
                        ejbAdresseLocal.deleteAdresse(request.getParameter("adrSelectNew"));
                    }
                }

                //Affichage d'un adresse au moment de la sélection dans la liste (voir aussi JS/Ajax)
                if ("monAdresse".equals(request.getParameter("search"))) {
                    url = "WEB-INF/detailAdresse.jsp";
                    String adrSelect = request.getParameter("adrSelect");
                    Adresse adresSelect = ejbAdresseLocal.findAdresse(adrSelect);
                    request.setAttribute("adrSelect", true);
                    request.setAttribute("recupAdrCode", adresSelect.getCode());
                    request.setAttribute("recupAdrNom", adresSelect.getNom());
                    request.setAttribute("recupAdrPrenom", adresSelect.getPrenom());
                    request.setAttribute("recupAdrEntreprise", adresSelect.getNomEntreprise());
                    request.setAttribute("recupAdrNum", adresSelect.getNoRue());
                    request.setAttribute("recupAdrRue", adresSelect.getRue());
                    request.setAttribute("recupAdrComplement", adresSelect.getComplement());
                    request.setAttribute("recupAdrCp", adresSelect.getCp());
                    request.setAttribute("recupAdrVille", adresSelect.getVille());
                    request.setAttribute("recupAdrPays", adresSelect.getPays());
                    request.setAttribute("recupAdrLibelle", adresSelect.getLibelle());
                    request.setAttribute("recupAdrTel", adresSelect.getTel());
                }
                Collection<Adresse> listAdrsUser = ejbClientLocal.findAdresseByUser(user.getLogin());
                request.setAttribute("listAdrsUser", listAdrsUser);
                request.setAttribute("listAdrsEmpty", listAdrsUser.isEmpty());
            }
        }

        if ("Enregistrer".equals(request.getParameter("doIt"))) {
            Client user = ejbClientLocal.findUser(login);
            String odlMdp = request.getParameter("oldMdp");
            String newMdp1 = request.getParameter("newMdp1");
            String newMdp2 = request.getParameter("newMdp2");
            if (odlMdp.equals(user.getMdp())) {
                if (newMdp1.equals(newMdp2)) {
                    user.setMdp(newMdp2);
                    ejbClientLocal.updateUser(user);
                    request.setAttribute("newMdp", true);
                    request.setAttribute("newMdpMesSucces", "Votre mot de passe a été modifié avec succès !");
                } else {
                    request.setAttribute("newMdp", false);
                    request.setAttribute("newMdpMesError", "La confirmation du mot de passe ne correspond pas au nouveau saisi.");
                }
            } else {
                request.setAttribute("oldMdp", false);
                request.setAttribute("oldMdpMesError", "Votre ancien mot de passe est erroné !");
            }
        }

        //On affiche la page dédiée à la gestion du paiment de la commande
        if ("paiement".equals(request.getParameter("page"))) {
            url = "WEB-INF/Paiement.jsp";
            String mntPaiement = request.getParameter("mntPaiement");
            float monPayement = parseFloat(mntPaiement);
            request.setAttribute("mntAPayerUrl", monPayement);
            request.setAttribute("mntAPayer", String.format("%.2f", monPayement) + " €");
            String adrLiv = request.getParameter("adrSelectLiv");
            request.setAttribute("adrLivCde", adrLiv);
            String adrFact = request.getParameter("adrSelectFact");
            request.setAttribute("adrFactCde", adrFact);
            String fraisLiv = request.getParameter("fdp");
            float fraisLivr = parseFloat(fraisLiv);
            request.setAttribute("fraisLivCde", fraisLivr);
        }

        //On créé une commande et ses lignes de commande à partir du panier et de son contenu
        if ("validPaiement".equals(request.getParameter("action"))) {

            // Création de la commande
            Commande cde = new Commande();
            cde.setNumCommande(ejbCommandeLocal.createNumCommande());
            cde.setNumFacture(ejbCommandeLocal.createNumFacture());
            cde.setDate(new Date());
            cde.setIp(cde.recupAdresseIp());
            float fraisLiv = parseFloat(request.getParameter("fdp"));
            cde.setForfaitLivraison(fraisLiv);
            cde.setTvaLivraison(20.0f);
            Adresse adrCdeFact = ejbAdresseLocal.findAdresse(request.getParameter("adrFactCde"));
            cde.setAdresseFact(adrCdeFact);
            Adresse adrCdeLiv = ejbAdresseLocal.findAdresse(request.getParameter("adrLivCde"));
            cde.setAdresseLiv(adrCdeLiv);
            Paiement payment = ejbPaiementLocal.findPaiement(request.getParameter("typePaiement"));
            cde.setPaiement(payment);
            cde.setClient(ejbClientLocal.findUser(login));
            //Persistance de la commande dans la BDD
            ejbCommandeLocal.addCommande(cde);
            request.setAttribute("numCde", cde.getNumCommande());

            //Récupération des lignes de panier pour faire des lignes de commande
            Collection<LignePanier> contenuPanier = ejbLigPanierLocal.listLigPanierByPanier(login);
            for (LignePanier lp : contenuPanier) {

                //Création de la ligne de commande
                LigneCommande lc = new LigneCommande();
                lc.setCode(ejbLigCommandeLocal.createCodeLigneCommande());
                lc.setQuantite(lp.getQuantite());
                lc.setLivreCommande(lp.getLivreAjout());
                lc.setPrixHT(lp.getLivreAjout().getPrixHT()); // Ici on utilise les éléments de l'objet livre et non pas de la ligne de panier au cas où l'article aurait changé (prix/tva/...)
                lc.setTvaAppliquee(lp.getLivreAjout().getTvaLivre().getTaux());
                lc.setRemise(ejbEvenementLocal.currentEvenement().getPourcentage());
                lc.setEvenement(ejbEvenementLocal.currentEvenement());
                lc.setCommande(cde);

                //Persistance de la ligne de commande dans la BDD
                ejbLigCommandeLocal.addLigCommande(lc);

                //Suppression de la ligne de panier dans la BDD
                ejbLigPanierLocal.deleteLigPanier(lp, login);

                //Correction du stock
                Livre livreMouvStock = lp.getLivreAjout();
                int oldStock = livreMouvStock.getStock();
                int qteMouvStock = lp.getQuantite();
                int newStock = oldStock - qteMouvStock;
                livreMouvStock.setStock(newStock);
                ejbLivreLocal.updateLivre(livreMouvStock);
            }
            //supression du panier dans la BDD
            ejbPanierLocal.deletePanier(login);

            //Ajout du statut "validé" à la commande payée
            StatutCommande sc = new StatutCommande();
            sc.setDateStatut(new Date());
            sc.setStatut(ejbStatutsLocal.findStatuts("ST01"));
            sc.setCommande(cde);
            //Persistance du statut dans la BDD
            ejbStatutCommandeLocal.addStatutCommande(sc);

            request.setAttribute("paiementValid", true);
        }

        if ("deconnexion".equals(request.getParameter("action"))) {
            if (userConnect != null) {
                userConnect.setMaxAge(0);
                response.addCookie(userConnect);
            }
        }

        //Gestion du badge du panier
        if (login == null) {
            Collection<LignePanier> panierSession = ejbPanierLocal.listContenuPanier();
            for (LignePanier lps : panierSession) {
                qtePanier += lps.getQuantite();
            }
        }
        if (login != null) {
            Collection<LignePanier> panierCourant = ejbLigPanierLocal.listLPs(login);
            for (LignePanier lpc : panierCourant) {
                qtePanier += lpc.getQuantite();
            }
        }
        if (qtePanier > 0) {
            request.setAttribute("panierRempli", true);
            request.setAttribute("badgePanier", qtePanier);
        } else {
            request.setAttribute("panierRempli", false);
        }

        request.getRequestDispatcher(url).include(request, response);
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
