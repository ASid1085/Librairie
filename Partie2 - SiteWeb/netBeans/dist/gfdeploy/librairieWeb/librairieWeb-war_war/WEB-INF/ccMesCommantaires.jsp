<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
    <div class="row mb-5">
        <div id="bandeau" class='col-6 m-auto'>
            <ul id='bandeauEtapes' class="list-group list-group-horizontal">
                <li id='etapeMesInfos' class="text-custom text-center list-group-item col-4">
                    <a href="Controller?page=monCompte&sousPage=mesInfos" class="text-custom">Mes infos</a>
                </li>
                <li id='etapeMesCommandes' class="text-custom text-center list-group-item col-4">
                    <a href="Controller?page=monCompte&sousPage=mesCommandes" class=" text-custom">Mes commandes</a>
                </li>
                <li  id='etapeMesCommentaires' class="text-customEtape text-center list-group-item col-4">
                    <a href="Controller?page=monCompte&sousPage=mesCommentaires" class="text-decoration-none text-white">Mes commentaires</a>
                </li>
            </ul>
        </div>
    </div>
    <c:if test="${!booksCommandEmpty}">
        <c:forEach var='b' items="${booksCommand}" varStatus="livreLu">
            <div class="col-12 col-lg-7 p-0 mx-auto">
                <div class="card border-custom shadow my-3">
                    <div class ='row m-3'>
                        <div class="col-1 p-0 m-auto">
                            <img class="tailleCde p-1" src="${b.image}" alt="${b.image}_couverture"/>
                        </div >
                        <div class="col-6 my-auto">
                            <h5 class='card-title text-dark px-2'>${b.titre}</h5>
                            <h6 class='text-secondary px-2'>${b.sousTitre}</h6>
                        </div>
                        <!-- Colonne contenant le prix unitaire de la ligne -->
                        <div class="col-3 my-auto">
                            <h6 class='card-title text-dark text-center'>Prix Unitaire</h6>
                            <h6 class=' mt-1 card-text text-custom text-center'>${b.prixTtcAffichage} € TTC</h6>
                            <p class="card-text small text-secondary text-center">(${b.prixHtAffichage} € HT)</p>
                        </div>
                        <div class="col-2 m-auto p-0">
                            <button class="btn mx-2" type="button">
                                <a href="Controller?page=monCompte&sousPage=mesCommentaires&action=detailComment&isbn=${b.isbn}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#C9A0E1" class="bi bi-chat-left-dots" viewBox="0 0 16 16">
                                        <path d="M14 1a1 1 0 0 1 1 1v8a1 1 0 0 1-1 1H4.414A2 2 0 0 0 3 11.586l-2 2V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12.793a.5.5 0 0 0 .854.353l2.853-2.853A1 1 0 0 1 4.414 12H14a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
                                        <path d="M5 6a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
                                    </svg>
                                </a>
                            </button>
                        </div>
                    </div>                 
                    <c:if test="${detailComment}">
                        <c:if test="${b.isbn == isbnRec}">
                            <div class="bg-light m-3 p-3">
                                <div class="row text-center" >
                                    <h4 class="modal-title text-dark mx-auto">Votre avis sur <span class="text-custom">${b.titre}</span></h4>
                                </div>
                                <c:if test="${commentAdd}">
                                    <p class="row alert alert-success m-auto p-2">Votre commentaire a bien été ajouté !</p>
                                </c:if>
                                <div class="row m-0">
                                    <form action="Controller">
                                        <input type="hidden" name="page" value="monCompte"/>
                                        <input type="hidden" name="sousPage" value="mesCommentaires"/>
                                        <input type="hidden" name="action" value="detailComment"/>
                                        <input type="hidden" name="isbn" value="${b.isbn}"/>
                                        <div id="recupNote"></div>
                                        <c:if test="${note0}">
                                            <p class='p-4 m-0'>
                                                Votre note  
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star1" name="star" value="1"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star2" name="star" value="2"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star3" name="star" value="3"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star4" name="star" value="4"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star5" name="star" value="5"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${note1}">
                                            <p class='p-4 m-0'>
                                                Votre note  
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" name="star" value="1"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star2" name="star" value="2"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star3" name="star" value="3"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star4" name="star" value="4"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star5" name="star" value="5"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${note2}">
                                            <p class='p-4 m-0'>
                                                Votre note  
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" name="star" value="1"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star2" name="star" value="2"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star3" name="star" value="3"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star4" name="star" value="4"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star5" name="star" value="5"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${note3}">
                                            <p class='p-4 m-0'>
                                                Votre note  
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" name="star" value="1"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star2" name="star" value="2"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star3" name="star" value="3"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star4" name="star" value="4"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star5" name="star" value="5"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${note4}">
                                            <p class='p-4 m-0'>
                                                Votre note  
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" name="star" value="1"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star2" name="star" value="2"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star3" name="star" value="3"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star4" name="star" value="4"/>
                                                <input type='image' src='Ressources/Bouton/starEmpty.svg' class="stars" id="star5" name="star" value="5"/>
                                            </p>
                                        </c:if>
                                        <c:if test="${note5}">
                                            <p class='p-4 m-0'>
                                                Votre note  
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" name="star" value="1"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star2" name="star" value="2"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star3" name="star" value="3"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star4" name="star" value="4"/>
                                                <input type='image' src='Ressources/Bouton/starFill.svg' class="stars" id="star5" name="star" value="5"/>
                                            </p>
                                        </c:if>
                                </div>
                                <c:if test="${!commentFind}">
                                    <div class="row px-4 mb-3">
                                        <textarea id='comment' name="comment" rows="5" cols="20" placeholder="Laissez votre commantaire ici ..." autocomplete='true' spellcheck='true' wrap='soft'></textarea>
                                    </div>
                                    <div class="row">
                                        <button type="submit" name="doIt" value="commenter" class="btn btn-custom text-decoration-none text-light mx-auto w-25">Commenter</button>
                                        <button type="button" class="btn btn-custom mx-auto w-25"><a href="Controller?page=monCompte&sousPage=mesCommentaires" class="text-decoration-none text-light">Annuler</a></button>
                                    </div>
                                </c:if>
                                <c:if test="${commentFind}">
                                    <div class="row px-4 mb-3">
                                        <textarea id='comment' name="comment" rows="5" cols="20"  readonly>${bookComment}</textarea>
                                    </div>
                                    <div class="row">
                                        <button type="button" class="btn btn-custom mx-auto w-25"><a href="Controller?page=monCompte&sousPage=mesCommentaires" class="text-decoration-none text-light">Fermer</a></button>
                                    </div>
                                </c:if>
                                </form>
                            </div>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>