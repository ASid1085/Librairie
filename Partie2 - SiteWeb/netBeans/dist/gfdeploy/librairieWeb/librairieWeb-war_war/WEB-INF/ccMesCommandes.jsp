<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<div class="container">
    <div class="row mb-3">
        <div id="bandeau" class='col-6 m-auto'>
            <ul id='bandeauEtapes' class="list-group list-group-horizontal">
                <li id='etapeMesInfos' class="text-custom text-center list-group-item col-4">
                    <a href="Controller?page=monCompte&sousPage=mesInfos" class="text-custom">Mes infos</a>
                </li>
                <li id='etapeMesCommandes' class="text-customEtape text-center list-group-item col-4">
                    <a href="Controller?page=monCompte&sousPage=mesCommandes&numCde=" class="text-decoration-none text-white">Mes commandes</a>
                </li>
                <li  id='etapeMesCommentaires' class="text-custom text-center list-group-item col-4">
                    <a href="Controller?page=monCompte&sousPage=mesCommentaires" class="text-custom">Mes commentaires</a>
                </li>
            </ul>
        </div>
    </div>
    <c:if test="${mesCdes}">
        <c:if test="${monDetailCde}">
            <div class="row bg-light py-3 my-4">
                <h5 class="text-custom text-center">Détail de  votre commande n° ${numeroCde}</h5>
                <div class="row">
                    <!-- debut card -->
                    <c:forEach var="lc" items="${listDetailCde}">
                        <div class="col-12 col-lg-11 p-0 mx-auto">
                            <div class="card border-custom shadow my-3">
                                <div class ='row m-3'>
                                    <div class="col-1 p-0">
                                        <img class="taillePanier p-1" src="${lc.livreCommande.image}" alt="${lc.livreCommande.image}_couverture"/>
                                    </div >
                                    <div class="col-5 my-auto">
                                        <h5 class='card-title text-dark px-2'>${lc.livreCommande.titre}</h5>
                                        <h6 class='text-secondary px-2'>${lc.livreCommande.sousTitre}</h6>
                                    </div>
                                    <!-- Colonne contenant le prix unitaire de la ligne -->
                                    <div class="col-2 my-auto">
                                        <h6 class='card-title text-dark text-center'>Prix Unitaire</h6>
                                        <h6 class=' mt-1 card-text text-custom text-center'>${lc.livreCommande.prixTtcAffichage} € TTC</h6>
                                        <p class="card-text small text-secondary text-center">(${lc.livreCommande.prixHtAffichage} € HT)</p>
                                    </div>
                                    <!-- Colonne contenant quantité de la ligne -->
                                    <div class="col-2 p-0 my-auto">
                                        <h6 class='card-title text-dark text-center'>Quantité</h6>
                                        <h6 class='card-title text-custom text-center'>${lc.quantite}</h6>
                                    </div>
                                    <!-- Colonne contenant le prix total de la ligne -->
                                    <div class="col-2 my-auto">
                                        <h6 class='card-title text-dark text-center'>Prix total</h6>
                                        <h6 class=' mt-1 card-text text-custom text-center'>${lc.mntTotalTtcAff} € TTC</h6>
                                        <p class="card-text small text-secondary text-center">(${lc.mntTotalHt} € HT)</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!-- fin card -->
                    <div class="row mx-auto">
                        <div class="col-6 m-0 p-0">
                            <p class="text-dark w-auto text-start px-5">Vous avez bénéficié du code promo <span class="text-custom" style="font-size: 1.2em">${eventCurrentName} </span>!</p>                                                
                            <p class="text-dark w-auto text-start px-5 small">Vous avez réglé cette commande par ${cdePaiement}.</p>
                        </div>
                        <div class="col-6 mx-auto text-center p-0">
                            <c:if test="${testEventCurrent}">
                                <div class="mb-4 w-50 mx-auto  border-bottom">
                                    <p class="splitLine m-0 w-75 text-start">Remise </p>
                                    <p class="splitLine m-0 w-auto text-end">  ${eventCurrent} %</p>
                                </div>
                            </c:if>
                            <div class="mx-auto mb-2 w-50">
                                <p class="splitLine m-0 w-75 text-start">Total HT</p>
                                <p class="splitLine m-0 w-auto text-end">  ${cdeTotalHt} €</p>
                            </div>
                            <div class="mb-2 w-50 mx-auto">
                                <p class="splitLine m-0 w-75 text-start">Total TVA</p>
                                <p class="splitLine m-0 w-auto text-end">  ${cdeTotalTva} €</p>
                            </div>
                            <div class="mb-4 w-50 mx-auto border-bottom">
                                <p class="splitLine m-0 w-75 text-start">Total TTC</p>
                                <p class="splitLine m-0 w-auto text-end">  ${cdeTotalTtc} €</p>
                            </div>
                            <div class="mb-4 border-bottom w-50 mx-auto">
                                <p class="splitLine m-0 w-75 text-start">Frais de port</p>
                                <p class="splitLine m-0 w-auto text-end">  ${cdeFraisLiv} €</p>
                            </div>
                            <div class="mb-0 border-bottom w-50 mx-auto">
                                <p class="splitLine m-0 w-75 text-start">Total payé</p>
                                <p class="splitLine m-0 w-auto text-end">  ${cdeTotalPaye} €</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="text-center mt-3">
                    <button class="btn btn-custom w-25 mx-2" type="button" >
                        <a class='text-white text-decoration-none' href="Controller?page=monCompte&sousPage=mesCommandes">
                            Revenir à la liste de mes commandes
                        </a>
                    </button>
                            <button class="btn btn-custom w-25 mx-2" type="button">
                                <a class="text-white text-decoration-none" href="Controller?page=monCompte&sousPage=mesCommandes&action=renouvellement&numCde=${numeroCde}">
                                   Renouveller la commande
                                </a>
                            </button>
                       
                    
                </div>
            </div>
        </c:if>
        <div class="row">
            <h4 class='font-weight-light text-custom mt-0'>Récapitulatif de vos commandes</h4>
        </div>
        <hr>
        <c:forEach var="cmd" items="${listCdesUser}">
            <div class="col-12 col-lg-8 p-0 mx-auto">
                <div class="card border-custom shadow my-4">
                    <div class ='row m-3'>
                        <div class="col-2 my-auto p-0 mx-1">
                            <h6 class='card-text text-secondary text-start'>n° de commande</h6>
                            <h6 class='card-text text-custom text-start'>${cmd.numCommande}</h6>
                        </div>
                        <div class="col-2 my-auto p-0 mx-1">
                            <h6 class='card-text text-secondary text-start'>Date de commande</h6>
                            <h6 class='card-text text-custom text-start'>${cmd.dateCde}</h6>
                        </div>
                        <div class="col-1 my-auto p-0 mx-1">
                            <h6 class='card-text text-secondary text-start'>Total payé</h6>
                            <h6 class='card-text text-custom text-start'>${cmd.mntTotalCdePayeAff} €</h6>
                        </div>
                        <div class="col-4 my-auto p-0 mx-2">
                            <h6 class='card-text text-secondary text-start'>Statut</h6>
                            <h6 class='card-text text-custom text-start'>${statutCde}</h6>
                        </div>
                        <div class="col-1 my-auto p-0 mx-0">
                            <button class="btn mx-2" type="button" >
                                <a href="Controller?page=monCompte&sousPage=mesCommandes&action=detailCde&numCde=${cmd.numCommande}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#C9A0E1" class="bi bi-eyeglasses" viewBox="0 0 16 16">
                                    <path d="M4 6a2 2 0 1 1 0 4 2 2 0 0 1 0-4zm2.625.547a3 3 0 0 0-5.584.953H.5a.5.5 0 0 0 0 1h.541A3 3 0 0 0 7 8a1 1 0 0 1 2 0 3 3 0 0 0 5.959.5h.541a.5.5 0 0 0 0-1h-.541a3 3 0 0 0-5.584-.953A1.993 1.993 0 0 0 8 6c-.532 0-1.016.208-1.375.547zM14 8a2 2 0 1 1-4 0 2 2 0 0 1 4 0z"/>
                                    </svg>
                                </a>
                            </button>
                        </div>
                        <div class="col-1 my-auto p-0 mx-0">
                            <button class="btn mx-2" type="button">
                                <a href="Controller?page=monCompte&sousPage=mesCommandes&action=renouvellement&numCde=${cmd.numCommande}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#C9A0E1" class="bi bi-arrow-counterclockwise" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M8 3a5 5 0 1 1-4.546 2.914.5.5 0 0 0-.908-.417A6 6 0 1 0 8 2v1z"/>
                                    <path d="M8 4.466V.534a.25.25 0 0 0-.41-.192L5.23 2.308a.25.25 0 0 0 0 .384l2.36 1.966A.25.25 0 0 0 8 4.466z"/>
                                    </svg>
                                </a>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>