<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row mb-5">
    <div id="bandeau" class='col-6 m-auto'>
        <ul id='bandeauEtapes' class="list-group list-group-horizontal">
            <li id='etapeMonPanier' class="text-customEtape text-center list-group-item col-4">Mon panier</li>
            <li id='etapeAdresse' class="text-custom text-center list-group-item col-4">Adresses</li>
            <li  id='etapePaiement' class="text-custom text-center list-group-item col-4">Paiement</li>
        </ul>
    </div>
</div>
<c:forEach var="lp" items="${ligPanierUser}" >

    <div class="col-12 col-lg-11 p-0 mx-auto">
        <div class="card border-custom shadow my-3">
            <div class ='row m-3'>
               <div class="col-1 p-0">
                    <img class="taillePanier p-1" src="${lp.livreAjout.image}" alt="${lp.livreAjout.image}_couverture"/>
                </div >
                <div class="col-4 my-auto">
                    <h4 class='card-title text-dark'>${lp.livreAjout.titre}</h4>
                    <h5 class='text-secondary'>${lp.livreAjout.sousTitre}</h5>
                </div>
                <!-- Colonne contenant le prix unitaire de la ligne -->
                <div class="col-2 my-auto">
                    <h5 class='card-title text-dark text-center'>Prix Unitaire</h5>
                    <h5 class=' mt-1 card-text text-custom text-center'>${lp.livreAjout.prixTtcAffichage} € TTC</h5>
                    <p class="card-text small text-secondary text-center">(${lp.livreAjout.prixHtAffichage} € HT)</p>
                </div>
                <div class="col-2 p-0 my-auto">
                    <h5 class='card-title text-dark text-center'>Quantité</h5>
                    <div class="text-center">
                        <br>
                        <button class="btn mx-1 p-0" type="button">
                            <a href="Controller?page=panier&dec=${lp.code}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="text-custom bi bi-dash-circle" viewBox="0 0 16 16">
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                    <path d="M4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8z"/>
                                </svg>
                            </a>
                        </button>
                        <!-- Qté saisie -->
                        <input class="text-end champPanier" name="qtePanier" type="text" value="${lp.quantite}" >
                        <!-- Bouton plus -->
                        <button class="btn mx-1 p-0" type="button">
                            <a href="Controller?page=panier&add=${lp.code}">
                                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="text-custom bi bi-plus-circle" viewBox="0 0 16 16" >
                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                                </svg>
                            </a>
                        </button>
                    </div>
                </div>
                <!-- Colonne contenant le prix total de la ligne -->
                <div class="col-2 my-auto">
                    <h5 class='card-title text-dark text-center'>Prix total</h5>
                    <h5 class=' mt-1 card-text text-custom text-center'>${lp.mntTotalTtc} € TTC</h5>
                    <p class="card-text small text-secondary text-center">(${lp.mntTotalHt} € HT)</p>
                </div>
                <!-- Supprimer la ligne panier du panier -->
                <div class="col-1 my-auto text-end">
                    <button class="btn mx-2" type="button">
                        <a href="Controller?page=panier&del=${lp.code}">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="text-custom bi bi-bag-x" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M6.146 8.146a.5.5 0 0 1 .708 0L8 9.293l1.146-1.147a.5.5 0 1 1 .708.708L8.707 10l1.147 1.146a.5.5 0 0 1-.708.708L8 10.707l-1.146 1.147a.5.5 0 0 1-.708-.708L7.293 10 6.146 8.854a.5.5 0 0 1 0-.708z"/>
                                <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"/>
                            </svg>
                        </a>
                    </button>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
<div>
    <c:if test="${testEventCurrent}">
        <p class="text-end mx-5 px-3">Remise ........................................ ${eventCurrent.pourcentageAffichage} %</p>
    </c:if>
    <c:if test="${!testEventCurrent}">
        <p class="text-end mx-5 px-3">Remise ........................................ 0 %</p>
    </c:if>
    <c:if test="${connecter}">
        <p class="text-end mx-5 px-3">Total Panier  ..................................... ${mntTotalTtcRemiseUser} €</p>
    </c:if>
        <c:if test="${!connecter}">
        <p class="text-end mx-5 px-3">Total Panier  ..................................... ${mntTotalTtcRemiseSession} €</p>
    </c:if>
</div>