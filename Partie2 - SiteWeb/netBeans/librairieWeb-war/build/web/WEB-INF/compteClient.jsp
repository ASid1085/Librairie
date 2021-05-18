<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8"/>
        <title>Marque Page - Mon compte</title>
        <link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css" />
        <link rel="stylesheet" href="Ressources/CSS/style.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container mt-4">
            <!-- Si un user est connecté -->
            <c:if test="${connecter}">
                <c:if test="${mesInfos}">
                    <jsp:include page="ccMesInfos.jsp"/>
                </c:if>

                <c:if test="${mesCdes}">
                    <jsp:include page="ccMesCommandes.jsp"/>
                </c:if>

                <c:if test="${mesComments}">
                    <jsp:include page="ccMesCommantaires.jsp"/>
                </c:if>
            </c:if>
            <!-- Si aucun user n'est connecté -->
            <c:if test="${!connecter}">
                <c:if test="${loginError}">
                    <h4 class="text-center text-secondary mb-4">${messageSucces}</h4>
                    <br>
                    <div class="text-center">
                        <button class="btn btn-custom mx-auto" type="button">
                            <a class="text-decoration-none plusPanier" href="Controller?page=monCompte&sousPage=mesInfos">Accéder à votre compte</a>
                        </button>
                    </div>
                </c:if>
                <c:if test="${!loginError}">
                    <div class="row  text-center">
                        <h3 class="text-custom mx-auto">Vous n'êtes pas connecté ...</h3>
                    </div>
                    <div class="row col-2 mx-auto">
                        <!-- Button trigger modal -->
                        <button class="btn btn-light mb-5 mx-1" type="button" data-toggle="modal" data-target="#connexionCompte">
                            <a class="text-decoration-none text-custom">Se connecter</a>
                        </button>
                        <!-- Modal -->
                        <div class="modal" id="connexionCompte" >
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content modal-custom">
                                    <div class="modal-body">
                                        <div class="form-title text-center">
                                            <h4>Connectez -vous !</h4>
                                        </div>
                                        <div class="d-flex flex-column text-center">
                                            <form class="m-0" method="get" action='Controller'>
                                                <input type="hidden" name="action" value="login"/>
                                                <input type="hidden" name="page" value="monCompte" />
                                                <input type="hidden" name="sousPage" value="mesInfos" />
                                                <input type='text' class="form-control my-2" name='userLogin' value='${userLogin}' id='userLogin' placeholder='Votre login' />
                                                <input type='password' class="form-control my-2" name='mdp' id='mdp' placeholder='Votre mot de passe ...' />
                                                <input type='submit' class="btn btn-light  text-secondary my-3" name='doIt' value='Se connecter'  />                           
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row text-center">
                        <h5 class="text-dark">Si vous souhaitez  créer votre compte rien de plus simple ...
                            <br>
                            Passez votre première commande en consultant notre <a class="text-custom text-custom" href="Controller?page=catalogue">catalogue</a> !
                        </h5>
                    </div>
                    <div class="row col-2 mx-auto">
                        <button class="btn btn-light mb-5 mx-auto" type="button">
                            <a class="text-decoration-none text-custom" href="Controller">Retour à l'accueil</a>
                        </button>
                        <h2 class="text-center text-danger">${messageError}</h2>
                    </c:if>
                </c:if>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
         <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
         <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="Ressources/JS/script.js"></script>
    </body>
</html>
