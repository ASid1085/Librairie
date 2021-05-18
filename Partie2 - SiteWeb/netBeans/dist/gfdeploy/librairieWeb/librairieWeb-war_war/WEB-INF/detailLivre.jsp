<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8">
        <title>Marque Page - Détail de ${monLivre.titre}</title>
        <link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css">
        <link rel="stylesheet" href="Ressources/CSS/style.css">
        <link rel="stylesheet" href="Ressources/CSS/styleReview.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <jsp:include page="header.jsp"/>
    </head>
    <body>
        <div class="container">
            <c:if test="${userConnecter}">
                <p class="text-center alert alert-success">${messageSucces}</p>
            </c:if>
            <!-- Détail du livre séléctionné -->
            <div class="row" id='livreDetail'>
                <div class="col-12 col-lg-12">
                    <div class="card border-custom shadow my-3">
                        <div class ='row m-3'>
                            <div class="col-3 p-0">
                                <img class="tailleDetail" src=${monLivre.image} alt="${monLivre.image}_couverture"/>
                                <p class="card-text text-secondary my-1 px-3">Date de parution : ${monLivre.dateEdition}</p>
                                <p class="card-text text-secondary my-1 px-3">Éditeur : ${monLivre.editeur.nom}</p>
                                <p class="card-text text-secondary my-1 px-3">Nombres de page : ${monLivre.nbrePage}</p>
                                <p class='card-text text-secondary my-1 px-3'>n° ISBN-10 : ${monLivre.isbn}</p>
                            </div >
                            <div class="card-body col-5 p-3">
                                <h3 class='card-title text-dark'>${monLivre.titre} ${monLivre.sousTitre}</h3>
                                <hr>
                                <h4 class='card-text text-custom'>Auteur(s)</h4><br>
                                <c:forEach var="a" items="${monLivre.listAuteur}">
                                    <span> ${a.prenom} ${a.nom} </span> <br>
                                </c:forEach>
                                <hr>        
                                <h4 class='card-text text-custom'>Résumé</h4><br>
                                <p>${monLivre.resume}</p>
                            </div>
                            <div class="card-body col-1 p-0"> </div>
                            <div class="card-body col-3 p-0 ">
                                <h6 class=' mt-4 text-center card-text text-dark'> Prix HT : ${monLivre.prixHtAffichage} €</h6>
                                <h3 class=' mb-5 mt-1 text-center card-text text-dark'> Prix TTC : ${monLivre.prixTtcAffichage} € </h3>
                                <input id="priceU" type="hidden" value="${monLivre.prixTtcAffichage}"/>
                                <hr>
                                <h4 class='card-text text-custom m-0'>Disponibilité</h4><br>

                                <c:if test="${monLivre.stock == 0}">
                                    <p class="alert alert-warning small">Livre en rupture de stock</p>
                                </c:if>
                                <c:if test="${monLivre.stock != 0}">

                                    <c:if test="${monLivre.stock > 9}">
                                        <h5 class="card-text text-success my-1 px-3">En stock</h5>
                                    </c:if>
                                    <c:if test="${monLivre.stock < 10}">
                                        <h5 class="card-text text-warning my-1 px-3">Bientôt indisponible</h5>
                                    </c:if>
                                    <ul>
                                        <li>Livraison offret à partir de 30 € d'achat.</li>
                                        <li>Retrait en magasin possible.</li>
                                    </ul>
                                    <hr>
                                    <form >
                                        <input type="hidden" name="page" value="panier" />
                                        <input type="hidden" name="action" value="addPanier" />
                                        <input type="hidden" name="isbn" value="${monLivre.isbn}" />
                                        <div>
                                            <h4 id='quantite' class='text-custom mx-2'>Quantité</h4>
                                            <input type="number" id="qtee" name="qtee"  min="1" max="50" value="1"  class="selectCustom2"  oninput="qteModal.value=parseInt(qtee.value)" />
                                        </div> 
                                        <br>
                                        <br>
                                        <!-- Button trigger modal -->
                                        <button id='addPanier' type="button" class="align-content-end btn btn-custom btn-lg mx-auto" data-toggle="modal" data-target="#ajoutPanier" onclick="priceT()">
                                            Ajouter au panier
                                        </button>

                                        <!-- Modal -->
                                        <div id="ajoutPanier" class="ajoutPanier modal">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content modal-custom">
                                                    <div class="modal-header ">
                                                        <h4 class="modal-title text-white mx-auto" id="exampleModalLongTitle">Article ajouté au panier avec succès !</h4>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="card border-0 cardModal">
                                                            <div class="card-body">
                                                                <div class="row">
                                                                    <p class="col-2">
                                                                        <img class="tailleModal p-1" src=${monLivre.image} alt="${monLivre.image}_couverture"/>
                                                                    </p>
                                                                    <p class='text-white col-4 px-4 my-auto'>${monLivre.titre}<br> ${monLivre.sousTitre}</p>
                                                                    <p class='text-white col-2 m-auto text-center'>Prix unit.<br>${monLivre.prixTtcAffichage} €</p>
                                                                    <p class='text-white col-2 m-auto text-center'>Qté<br>
                                                                        <output id="qteModal" name="qteModal">1</output>
                                                                    </p>
                                                                    <p id='PrixTotalModal' class='text-white col-2 m-auto text-center'></p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="submit" class="btn btn-light mx-auto" >Valider l'ajout au panier</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" id='titreCommentaire'>
                <div class="col">
                    <h2 class='font-weight-light text-custom mt-3'>Ils nous donnent leur avis !</h2>
                    <span id='titrecom' class="text-dark text-decoration-none">${monLivre.titre}</span>
                </div>
            </div>
            <hr class='my-1'>
            <c:if test="${!commentMonLivre}">
                <h4 class='font-weight-light text-dark mt-3'>Soyez le premier à nous donner votre avis sur ce livre.</h4>
            </c:if>
            <c:if test="${commentMonLivre}">
                <div class="row border-success" id='commentaire'>
                    <c:forEach var="comment" items="${listCommentsBook}">

                        <div class="col-12 col-lg-4 py-4 m-0">
                            <div class="card border-dark shadow">
                                <div class="card-img-top">
                                    <div class="row m-auto">
                                        <img src="Ressources/Avatar/avatar${comment.ramdomAvatarComment}.png" class="wpx-100 img-round m-3 col-2" alt="avatar${comment.ramdomAvatarComment}">
                                        <p class='col-3 m-auto'></p>
                                        <c:if test="${comment.note == 0}">
                                            <p class='col-4 m-auto'>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" />
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${comment.note == 1}">
                                            <p class='col-4 m-auto'>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" />
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${comment.note == 2}">
                                            <p class='col-4 m-auto'>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${comment.note == 3}">
                                            <p class='col-4 m-auto'>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${comment.note == 4}">
                                            <p class='col-4 m-auto'>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" />
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${comment.note == 5}">
                                            <p class='col-4 m-auto'>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars"/>
                                            </p>
                                        </c:if>
                                    </div>

                                </div>
                                <div class="card-body">
                                    <div class="card-text">
                                        <h5 class="text-custom font-cond mgb-5 fs-130 px-4 m-0" contenteditable="false">${comment.client.login}</h5>
                                        <span class="px-4 ">
                                            <small class=" text-secondary font-cond case-u lts-sm fs-80 fg-text-l" contenteditable="false">Posté le ${comment.dateAff}</small>
                                        </span>
                                        <div class="text-center">

                                            <textarea class="border-light mx-auto mt-3" rows="5" cols="50" readonly>${comment.texte}</textarea>
                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                </div>
            </c:if>
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
