<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8"/>
        <title>Marque Page - Mes adresses</title>
        <link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css" />
        <link rel="stylesheet" href="Ressources/CSS/style.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <jsp:include page="header.jsp"/><br><br>
        <div class="container">
            <div class="row mb-5">
                <div id="bandeau" class='col-6 m-auto'>
                    <ul id='bandeauEtapes' class="list-group list-group-horizontal">
                        <li id='etapeMonPanier' class="text-custom text-center list-group-item col-4"><a href="Controller?page=panier" class="text-custom">Mon panier</a></li>
                        <li id='etapeAdresse' class="text-customEtape text-center list-group-item col-4">Adresses</li>
                        <li  id='etapePaiement' class="text-custom text-center list-group-item col-4">Paiement</li>
                    </ul>
                </div>
            </div>

            <div class="row col-10 mx-auto mb-5">
                <!-- Adresse de livraison -->
                <div class="col-5 mx-auto ">
                    <div class="row">
                        <p class="text-secondary my-0">Livraison à votre adresse ${recupAdrLivLibelle}</p>
                    </div>
                    <div class="row borderAdr text-start px-5 py-3">
                        <c:if test="${adrLivSelect}">
                            <p class="border-bottom"> ${recupAdrLivPrenom} ${recupAdrLivNom}</p>
                            <br>
                            <c:if test="${!adrLivEntrepriseNull}">
                                <p class="border-bottom"> ${recupAdrLivEntreprise}</p>
                                <br>
                            </c:if>
                            <c:if test="${adrLivEntrepriseNull}">
                                <p class="border-bottom text-secondary"> Aucune entreprise renseignée</p>
                                <br>
                            </c:if>
                            <p class="border-bottom"> ${recupAdrLivNum}, ${recupAdrLivRue}</p>
                            <br>
                            <c:if test="${!adrLivComplementNull}">
                                <p class="border-bottom"> ${recupAdrLivComplement}</p>
                                <br>
                            </c:if>
                            <c:if test="${adrLivComplementNull}">
                                <p class="border-bottom text-secondary">Aucun complément d'adresse</p>
                                <br>
                            </c:if>
                            <p class="border-bottom"> ${recupAdrLivCp} - ${recupAdrLivVille}</p>
                            <br>
                            <p class="border-bottom"> ${recupAdrLivPays}</p>
                            <br>
                            <label class="small text-secondary" for='telAdrLiv'>Téléphone contact livraison</label>
                            <input class="borderTel border-bottom mb-2" type="text" name="telAdrLiv" value="${recupAdrLivTel}" placeholder="Précisez le n° en cas de besoin ...">
                        </c:if>
                        <c:if test="${!adrLivSelect}">
                            <p class="border-bottom small text-secondary"> Jonh DO</p>
                            <br>
                            <p class="border-bottom small text-secondary"> C.I.A.</p>
                            <br>
                            <p class="border-bottom small text-secondary"> 1, rue de Paris</p>
                            <br>
                            <p class="border-bottom small text-secondary"> sous-sol 51</p>
                            <br>
                            <p class="border-bottom small text-secondary"> 75000 - Honk-Kong</p>
                            <br>
                            <p class="border-bottom small text-secondary">Japon</p>
                            <br>
                            <p class="border-bottom small text-secondary">Téléphone contact livraison<br>01.02.03.04.05</p>
                            </c:if>
                        <div class="text-center">
                            <!-- Button trigger modal -->
                            <button type="button" class="align-content-end btn btn-custom" data-toggle="modal" data-target="#monCarnetAdrLiv">
                                Mon carnet d'adresse
                            </button>
                            <!-- Modal -->
                            <div class="modal" id="monCarnetAdrLiv" >
                                <div class="modal-dialog modal-lg" >
                                    <form action="Controller">
                                        <input type='hidden' name='page' value='adresses'/>
                                        <input type='hidden' name='user' value='${user}'/>
                                        <input type="hidden" name="adrSelectFact" value="${recupFacturation}"/>
                                        <div class="modal-content modal-custom">                             
                                            <div class="modal-header">
                                                <h4 class="modal-title text-dark mx-auto">Votre liste d'adresse !</h4>
                                            </div>
                                            <div class="modal-body">                                 
                                                <div class="card border-0 cardModal">
                                                    <div class="card-body">
                                                        <div class="row mx-auto p-0">
                                                            <c:forEach var="adr" items="${listAdrsUser}">
                                                                <div  class="form-check text-start my-2 mx-auto" >
                                                                    <input class="form-check-input" type="checkbox" value="${adr.code}" name="adrSelectLiv"/>
                                                                    <label class="form-check-label text-white" for="adrSelectLiv" >${adr.libelle} <br><span class="small text-dark">${adr.prenom} ${adr.nom} (${adr.cp})</span></label>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-light text-decoration-none text-custom mx-auto w-25">Sélectionner</button>
                                                <button type="button" class="btn btn-light mx-auto w-25"><a href="Controller?sousPage=mesAdresses" class="text-decoration-none text-custom">Ajouter</a></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Adresse de facturation -->
                <div class="col-5 mx-auto ">
                    <div class="row">
                        <p class="text-secondary my-0">Facturation à votre adresse ${recupAdrFactLibelle}</p>
                    </div>
                    <div class="row borderAdr text-start px-5 py-3">
                        <c:if test="${adrFactSelect}">
                            
                            <p class="border-bottom"> ${recupAdrFactPrenom} ${recupAdrFactNom}</p>
                            <br>
                            <c:if test="${!adrFactEntrepriseNull}">
                                <p class="border-bottom"> ${recupAdrFactEntreprise}</p>
                                <br>
                            </c:if>
                            <c:if test="${adrFactEntrepriseNull}">
                                <p class="border-bottom text-secondary"> Aucune entreprise renseignée</p>
                                <br>
                            </c:if>
                            <p class="border-bottom"> ${recupAdrFactNum}, ${recupAdrFactRue}</p>
                            <br>
                            <c:if test="${!adrFactComplNull}">
                                <p class="border-bottom">${recupAdrFactComplNull}</p>
                                <br>
                            </c:if>
                            <c:if test="${adrFactComplNull}">
                                <p class="border-bottom text-secondary">Aucun complément d'adresse</p>
                                <br>
                            </c:if>
                            <p class="border-bottom"> ${recupAdrFactCp} - ${recupAdrFactVille}</p>
                            <br>
                            <p class="border-bottom"> ${recupAdrFactPays}</p>
                            <br>
                            <label class="small text-secondary" for='telAdrFact'>Téléphone contact facturation</label>
                            <input class="borderTel border-bottom mb-2" type="text" name="telAdrFact" value="${recupAdrFactTel}" placeholder="Précisez le n° en cas de besoin ...">
                            
                        </c:if>
                        <c:if test="${!adrFactSelect}">
                            <p class="border-bottom small text-secondary"> Jonh DO</p>
                            <br>
                            <p class="border-bottom small text-secondary"> C.I.A.</p>
                            <br>
                            <p class="border-bottom small text-secondary"> 1, rue de Paris</p>
                            <br>
                            <p class="border-bottom small text-secondary"> sous-sol 51</p>
                            <br>
                            <p class="border-bottom small text-secondary"> 75000 - Honk-Kong</p>
                            <br>
                            <p class="border-bottom small text-secondary">Japon</p>
                            <br>
                            <p class="border-bottom small text-secondary">Téléphone contact livraison<br>01.02.03.04.05</p>
                            </c:if>
                        <div class="text-center">
                            <!-- Button trigger modal -->
                            <button type="button" class="align-content-end btn btn-custom" data-toggle="modal" data-target="#monCarnetAdrFact">
                                Mon carnet d'adresse
                            </button>
                            <!-- Modal -->
                            <div class="modal" id="monCarnetAdrFact" >
                                <div class="modal-dialog modal-lg" >
                                    <form action="Controller">
                                        <input type='hidden' name='page' value='adresses'/>
                                        <input type='hidden' name='user' value='${user}'/>
                                        <input type="hidden" name="adrSelectLiv" value="${recupLivraison}"/>
                                        <div class="modal-content modal-custom">                             
                                            <div class="modal-header">
                                                <h4 class="modal-title text-dark mx-auto">Votre liste d'adresse !</h4>
                                            </div>
                                            <div class="modal-body">                                 
                                                <div class="card border-0 cardModal">
                                                    <div class="card-body">
                                                        <div class="row w-75 mx-auto p-0">
                                                            <c:forEach var="adr" items="${listAdrsUser}">
                                                                <div  class="form-check text-start my-2 mx-auto" >

                                                                    <input class="form-check-input" type="checkbox" value="${adr.code}" name="adrSelectFact"/>
                                                                    <label class="form-check-label text-white w-75" for="adrSelectFact" >${adr.libelle} <br><span class="small text-dark">${adr.prenom} ${adr.nom} (${adr.cp})</span></label>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-light text-decoration-none text-custom mx-auto w-25">Sélectionner</button>
                                                <button type="button" class="btn btn-light mx-auto w-25"><a href="Controller?sousPage=mesAdresses" class="text-decoration-none text-custom">Ajouter</a></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row col-10 mx-auto ">
                <!-- Récap de la commande du client -->
                <div class="col-5 mx-auto ">
                    <div class="row">
                        <p class="text-secondary my-0">Récapitulatif de votre commande</p>
                    </div>
                    <div class="row borderAdr text-start px-5 py-3">
                        <c:forEach var="lp" items="${ligPanierUser}" >
                            <div class="row my-2 border-bottom p-0">
                                <div class="col-1 p-0">
                                    <img class="tailleCde p-2" src="${lp.livreAjout.image}" alt="${lp.livreAjout.image}_couverture"/>
                                </div >
                                <div class="col-6 my-auto">

                                    <h6 class='text-dark'>${lp.livreAjout.titre}</h6>
                                    <p class=' small text-secondary m-0'>${lp.livreAjout.sousTitre}</p>
                                </div>
                                <div class="col-2 p-0 my-auto">
                                    <h6 class='text-dark text-center'>${lp.quantite}</h6>
                                </div>
                                <div class="col-3 p-0 my-auto">
                                    <h6 class='text-dark text-end'>${lp.mntTotalTtc} € TTC</h6>
                                    <p class="small text-secondary text-end m-0">(${lp.mntTotalHt} € HT)</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- Récap de la valeur de la commande -->
                <div class="col-5 mx-auto text-center px-5">
                    <c:if test="${testEventCurrent}">
                        <div class="mb-4 text-start border-bottom w-75 mx-auto">
                            <p class="m-0 small text-secondary w-auto">Vous bénéficiez du code promo <span class="text-custom" style="font-size: 1.4em">${eventCurrentName} !</span></p>
                            <p class="splitLine m-0 w-75">Remise </p>
                            <p class="splitLine m-0 w-auto">  ${eventCurrent} %</p>
                        </div>
                    </c:if>
                    <div class="mx-auto mb-2 text-start w-75">
                        <p class="splitLine m-0 w-75">Total HT</p>
                        <p class="splitLine m-0 w-auto">  ${mntTotalHtRemiseUser} €</p>
                    </div>
                    <div class="mb-2 text-start w-75 mx-auto">
                        <p class="splitLine m-0 w-75">Total TVA</p>
                        <p class="splitLine m-0 w-auto">  ${mntTotalTvaRemiseUser} €</p>
                    </div>
                    <div class="mb-4 text-start  w-75 mx-auto border-bottom">
                        <p class="splitLine m-0 w-75">Total TTC</p>
                        <p class="splitLine m-0 w-auto">  ${mntTotalTtcRemiseUser} €</p>
                    </div>

                    <div class="mb-4 text-start border-bottom w-75 mx-auto">
                        <p class="splitLine m-0 w-75">Frais de port</p>
                        <p class="splitLine m-0 w-auto">  ${fraisPort} €</p>
                    </div>

                    <div class="mb-2 text-start w-75 mx-auto">
                        <p class="splitLine m-0 w-75">Total à payer</p>
                        <p class="splitLine m-0 w-auto">  ${totalAPayerAffichage} €</p>
                    </div>
                    <div class="text-center my-3">
                        <button type="button" class="align-content-end btn btn-custom">
                            <c:if test="${!okPaiementLiv || !okPaiementFact}">
                                Manque une adresse
                            </c:if>
                            <c:if test="${okPaiementLiv && okPaiementFact}">
                                <a class="text-decoration-none text-white" href="Controller?page=paiement&mntPaiement=${totalAPayerValue}&adrSelectFact=${recupFacturation}&adrSelectLiv=${recupLivraison}&fdp=${fraisPortUrl}">Effectuer le paiement</a>
                            </c:if>
                        </button>
                    </div>
                </div>
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



