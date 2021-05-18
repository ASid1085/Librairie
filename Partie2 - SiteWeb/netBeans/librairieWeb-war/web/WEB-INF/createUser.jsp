<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8"/>
        <title>Marque Page - Creation compte</title>
        <link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css" />
        <link rel="stylesheet" href="Ressources/CSS/style.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <jsp:include page="header.jsp"/><br><br>
        <div class="container">
            <c:if test="${!checkCreateCompte}">
                <h2 class="text-center text-danger">${errorCreateCompte}</h2>

                <!-- Div contenant la partie haute avec le titre -->
                <div class="row text-center">
                    <h4 class="text-custom">Création de votre compte</h4>
                    <h6 class="text-secondary">Il ne vous reste plus qu'à enregistrer vos informations</h6>
                </div>

                <!-- Div contenant la partie principale avec le formulaire -->
                <div class="row text-center">
                    <fieldset class="col-6 fieldsetCreate mx-auto my-4 p-3">
                        <form action="Controller" class='m-0'>
                            <input type="hidden" name="page" value="createUser"/>
                            <div class="form-check form-check-inline text-start">
                                <input class="form-check-input" type="radio" name="civilite" id="inlineRadio1" value="madame">
                                <label class="form-check-label" for="inlineRadio1">Madame</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="civilite" id="inlineRadio2" value="monsieur">
                                <label class="form-check-label" for="inlineRadio2">Monsieur</label>
                            </div>
                            <br>
                            <input type="text" class="inputCreate my-3 w-75" name="nom" id="nom" value="${recupNom}" placeholder="* Nom" required />
                            <br>
                            <input type="text" class="inputCreate mb-3 w-75" name="prenom" id="prenom" value="${recupPrenom}" placeholder="* Prénom" required />
                            <br>
                            <input type="text" class="inputCreate mb-3 w-75" name="email" id="email" value="${recupEmail}" placeholder="* Email" required />
                            <br>
                            <input type="text" class="inputCreate mb-3 w-75" name="telephone" id="tel" value="${recupTel}" placeholder="  n° de téléphone"/>
                            <br>
                            <input type="text" class="inputCreate mb-3 w-75" name="logConnexion" id="log" value="${recupLog}" placeholder="* Login de connexion" required />
                            <br>
                            <input type="password" class="inputCreate mb-3 w-75" name="mdp" id="mdp1" placeholder="* Mot de passe" required />
                            <br>
                            <input type="password" class="inputCreate mb-3 w-75" name="checkMdp" id="mdp2" placeholder="* Confirmation mot de passe" required />
                            <br>
                            <button class="btn btn-custom mt-3" type="submit" name="doCreate" value="Ok">
                                Créer mon compte
                            </button>
                        </form>
            </fieldset>
                </div>  
            <!-- Div contenant la partie basse avec les boutons de retour -->
            <div class="row-cols-6 text-center mb-5">
                <button class="btn btn-light m-auto col-5 w-25 mx-1" type="button">
                    <a class="text-decoration-none text-custom" href="Controller">Retour à l'accueil</a>
                </button>
                <button class="btn btn-light m-auto col-5 w-25 mx-1" type="button">
                    <a class="text-decoration-none text-custom" href="Controller?page=panier">Retour au panier</a>
                </button>
            </div>
            </c:if>
            <c:if test="${checkCreateCompte}">
                <fieldset class="col-6 fieldsetCreate mx-auto mt-0 mb-4 p-3 text-center">
                    <h2 class="text-center text-success">Votre compte a été créé avec succès !</h2>
                    <br>
                    <p class="text-dark text-center">Bienvenue !</p>
                    <h6 class="text-dark text-center">Nous sommes heureux de vous compter parmis nous.</h6>
                    <button class="btn btn-custom m-auto col-4 mt-3 w-25" type="button">
                        <a class="text-decoration-none text-white" href="Controller?page=monCompte&sousPage=mesInfos">Retour</a>
                    </button>
                </fieldset>
            </c:if>
        </div>
    <jsp:include page="footer.jsp"/>
    <!-- Optional JavaScript -->
     <!-- jQuery first, then Popper.js, then Bootstrap JS -->
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>
