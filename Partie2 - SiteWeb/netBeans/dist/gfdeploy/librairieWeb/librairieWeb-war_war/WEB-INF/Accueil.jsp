<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
        <title>Marque Page - Accueil</title>
        <link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css">
        <link rel="stylesheet" href="Ressources/CSS/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container">
            <c:if test="${userConnecter}">
                <p class="text-center alert alert-success">${messageSucces}</p>
            </c:if>
            <div class="row mt-3 carousel">
                <div class="col">
                    <div id="carouselControls" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item  active">
                                <img src="Ressources/Images/livre_basket.png" class="d-block w-100" alt="livre_basket">
                            </div>
                            <div class="carousel-item">
                                <img src="Ressources/Images/livre_biblio.png" class="d-block w-100" alt="livre_biblio">
                            </div>
                            <div class="carousel-item">
                                <img src="Ressources/Images/livre_bureau.png" class="d-block w-100" alt="livre_bureau">
                            </div>
                            <div class="carousel-item">
                                <img src="Ressources/Images/livre_ext.png" class="d-block w-100" alt="livre_ext">
                            </div>
                            <div class="carousel-item">
                                <img src="Ressources/Images/livre_pages.png" class="d-block w-100" alt="livre_pages">
                            </div>
                            <div class="carousel-item">
                                <img src="Ressources/Images/livre_plage.png" class="d-block w-100" alt="livre_plage">
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselControls" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        </a>
                        <a class="carousel-control-next" href="#carouselControls" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-9">
                     <h4 class='font-weight-light text-custom mt-5' id="teeeest">Nos suggestions du moment</h4>
                </div>
                <div class="col-3">
                    <h4 class="font-weight-light mt-5 text-custom" >Tout notre catalogue <a class="text-dark text-decoration-none" href="Controller?page=catalogue">ici</a></h4>
                </div>
            </div>
            <hr>
            <div class="row">
                <c:forEach var="l" items="${listSuggestions}">
                    <div class="col-12 col-lg-4 py-4 m-0">
                        <div class="card border-custom shadow">
                            <div class ='row'>
                                <div class="col-4">
                                    <img class="taille my-4" src=${l.image} alt="${l.image}_couverture"/>
                                </div>
                                <div class="col-8 ">
                                    <div class='card-body m-3'>
                                        <h4 class='card-title text-dark'><a class='text-dark text-decoration-none'  href="Controller?page=detail&isbn=${l.isbn}">${l.titre}</a></h4>
                                         <p class='card-text'>${l.sousTitre}</p>
                                         <p class='card-text text-secondary'>
                                             <c:forEach var="a" items="${l.listAuteur}">
                                                <c:if test="${l.listAuteur.size() > 1}">
                                                    <span>  ${a.prenom} ${a.nom},  </span>
                                                </c:if>
                                                <c:if test="${l.listAuteur.size() == 1}">
                                                     <span> ${a.prenom} ${a.nom} </span>
                                                </c:if>
                                            </c:forEach>
                                         </p>
                                        <p class='card-text text-secondary'>Éditeur : ${l.editeur.nom}</p>
                                        <p class='card-text text-custom'>Tarif : ${l.prixHtAffichage} € HT - ${l.prixTtcAffichage} € TTC</p>
                                        <p class='card-text text-secondary'>n° ISBN-10 : ${l.isbn}</p>
                                        <a href="Controller?page=detail&isbn=${l.isbn}" class="stretched-link"></a>
                                       <!-- <p><a class="btn btn-custom btn-lg" href="#">Ajouter au panier</a></p>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
        <script src="Ressources/JS/script.js"></script>
        <!-- Optional JavaScript -->
            <!-- jQuery first, then Popper.js, then Bootstrap JS -->
           <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
