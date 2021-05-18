<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="l" items="${listLivres}" varStatus="index">
    <div class="card border-custom shadow my-3">
        <div class ='row m-3'>
            <div class="col-2 p-0">
                <a  href="Controller?page=detail&isbn=${l.isbn}"><img class="taille" src=${l.image} alt="${l.image}_couverture"/></a>
            </div>
            <div id='parent' class='card-body col-7 my-auto'>
                <h4 class='card-title text-dark'><a class='text-dark text-decoration-none'  href="Controller?page=detail&isbn=${l.isbn}">${l.titre}</a></h4>
                <p class='card-text'>${l.sousTitre}</p>
                <p class='card-text text-secondary'>
                <p>Auteur(s)  :</p>
                <c:forEach var="a" items="${l.listAuteur}">
                    <c:if test="${l.listAuteur.size() > 1}">
                        <span>  ${a.prenom} ${a.nom},  </span>
                    </c:if>
                    <c:if test="${l.listAuteur.size() == 1}">
                        <span> ${a.prenom} ${a.nom} </span>
                    </c:if>
                </c:forEach>
                <p class='card-text text-secondary'>Éditeur : ${l.editeur.nom}</p>
                <p class='card-text text-secondary'>n° ISBN-10 : <span id='spanIsbn'>${l.isbn}</span></p>
                <div id='cacher' >
                    <p class='card-text text-custom'>Résumé :</p>
                <p class='card-text text-custom'>${l.resume}</p>
                </div>
                
            </div> 
            <div class="card-body col-2 m-auto">
                <h6 class='card-text text-dark'> ${l.prixHtAffichage} € HT</h6>
                <h5 class='card-text text-dark'> ${l.prixTtcAffichage} € TTC</h5>
                
                <c:if test="${l.stock != 0}">
                    <!-- Button trigger modal -->
                <button type="button" class="align-content-end btn btn-custom mx-auto" data-toggle="modal" data-target="#ajoutPanier${index.count+1000}">
                    Ajouter au panier
                </button>
                <!-- Modal -->
                <div class="modal" id="ajoutPanier${index.count+1000}" >
                    <div class="modal-dialog modal-dialog-centered modal-lg" >
                        <div class="modal-content modal-custom">                             
                            <div class="modal-header">
                                <h4 class="modal-title text-white mx-auto" id="ajoutPanierTitle">Article ajouté au panier avec succès !</h4>
                            </div>
                            <div class="modal-body">                                 
                                <div class="card border-0 cardModal">
                                    <div class="card-body">
                                        <div class="row">
                                            <p class="col-2">
                                                <img class="tailleModal p-1" src=${l.image} alt="${l.image}_couverture"/>
                                            </p>
                                            <p class='text-white col-4 px-4 my-auto'>${l.titre}<br> ${l.sousTitre}</p>
                                            <p class='text-white col-3 m-auto text-center'>Prix unit.<br>${l.prixTtcAffichage} € TTC<br><span class="small text-secondary">${l.prixHtAffichage} € HT</span></p>
                                            <p class='text-white col-3 m-auto text-center'>Qté<br>1<br><span class="text-custom">.</span></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-light  mx-auto"><a href="Controller?page=catalogue&action=addPanier&isbn=${l.isbn}&qtee=1" class="text-decoration-none text-custom">Continuer mes achats</a></button>
                                <button type="button" class="btn btn-light mx-auto"><a href="Controller?page=panier&action=addPanier&isbn=${l.isbn}&qtee=1" class="text-decoration-none text-custom">Consulter mon panier</a></button>
                            </div>
                        </div>
                    </div>
                </div>
                </c:if>
                <c:if test="${l.stock == 0}">
                    <p class="alert alert-warning small">Livre en rupture de stock</p>
                </c:if>
                
            </div>
        </div>
    </div>
</c:forEach>
