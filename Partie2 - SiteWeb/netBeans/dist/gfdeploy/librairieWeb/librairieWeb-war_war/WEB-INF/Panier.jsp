<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8"/>
        <title>Marque Page - Mon panier</title>
        <link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css" />
        <link rel="stylesheet" href="Ressources/CSS/style.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <div>
            <jsp:include page="header.jsp"/>
        </div>
        <c:if test="${!connecter}">
            <c:if test="${panierVideSession}">
                <div class="container mt-4">
                    <h4 class="text-custom">Votre panier est vide ...<br>Consultez notre <a class="text-custom text-dark" href="Controller?page=catalogue">catalogue</a> pour le remplir !</h4>
                </div>
            </c:if>

            <c:if test="${!panierVideSession}">
                <div class="container mt-4">
                    <c:if test="${!loginError}">
                        <h2 class="text-center text-danger">${messageError}</h2>
                        <div>
                            <jsp:include page="detailPanier.jsp"/>
                        </div>
                        <div class="row mt-5 mb-2">
                            <button class="btn btn-custom m-auto col-4 w-25" type="button">
                                <a class="text-decoration-none plusPanier" href="Controller?page=panier&clear">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="text-white bi bi-trash" viewBox="0 0 16 16">
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                    </svg>
                                    Vider le panier</a>
                            </button>
                            <button class="btn btn-custom m-auto col-4 w-25" type="button">
                                <a class="text-decoration-none plusPanier" href="Controller?page=catalogue">Retour au catalogue</a>
                            </button>

                            <!-- Button trigger modal -->
                            <button class="btn btn-custom m-auto col-4 w-25" type="button" data-toggle="modal" data-target="#connexionCompte">
                                <a class="text-decoration-none text-white">Finaliser la commande</a>
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
                                                    <input type="hidden" name="page" value="panier" />
                                                    <input type='text' class="form-control my-2" name='userLogin' value='${userLogin}' id='userLogin' placeholder='Votre login' />
                                                    <input type='password' class="form-control my-2" name='mdp' id='mdp' placeholder='Votre mot de passe ...' />
                                                    <input type='submit' class="btn btn-light  text-secondary my-3" name='doIt' value='Se connecter'   />                           
                                                </form>
                                            </div>
                                        </div>
                                        <div class="modal-footer d-flex justify-content-center">
                                            <div class="signup-section">Pas encore membre ? Créez votre compte <a class="text-white text-decoration-none" href="Controller?page=createUser"> ici </a>!</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </c:if>

            <c:if test="${loginError}">
                <div class="container mt-4 mx-auto">
                    <h2 class="text-center text-success">${messageSucces}</h2>
                    <br>
                    <div class="text-center">
                        <button class="btn btn-custom mx-auto" type="button">
                            <a class="text-decoration-none plusPanier" href="Controller?page=panier">Retour au panier</a>
                        </button>
                    </div>

                </div>
            </c:if>
        </c:if>

        <c:if test="${connecter}">
            <c:if test="${panierVideUser}">
                <div class="container mt-4">
                    <h4 class="text-custom">Votre panier est vide ...<br>Consultez notre <a class="text-custom text-dark" href="Controller?page=catalogue">catalogue</a> pour le remplir !</h4>
                </div>
            </c:if>

            <c:if test="${!panierVideUser}">
                <div class="container mt-4">
                    <div>
                        <jsp:include page="detailPanierUser.jsp"/>
                    </div>
                    <div class="row mt-5 mb-2">
                        <button class="btn btn-custom m-auto col-4 w-25" type="button">
                            <a class="text-decoration-none plusPanier" href="Controller?page=panier&clear">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="text-white bi bi-trash" viewBox="0 0 16 16">
                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                </svg>
                                Vider le panier</a>
                        </button>
                        <button class="btn btn-custom m-auto col-4 w-25" type="button">
                            <a class="text-decoration-none plusPanier" href="Controller?page=catalogue">Retour au catalogue</a>
                        </button>

                        <button class="btn btn-custom m-auto col-4 w-25" type="button">
                            <a class="text-decoration-none text-white" href="Controller?page=adresses&user=${userConnect}">Finaliser la commande</a>
                        </button>
                    </div>
                </div>
            </c:if>
        </c:if>
        
        <jsp:include page="footer.jsp"/>
        <!-- Optional JavaScript -->
         <!-- jQuery first, then Popper.js, then Bootstrap JS -->
           <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="Ressources/JS/script.js"></script>
    </body>
</html>