<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container">
    <!-- Div du bandeau en haut de page -->
    <div class="row mb-5">
        <div id="bandeau" class='col-6 m-auto'>
            <ul id='bandeauEtapes' class="list-group list-group-horizontal">
                <li id='etapeMesInfos' class="text-customEtape text-center list-group-item col-4">
                    <a href="Controller?page=monCompte&sousPage=mesInfos" class="text-decoration-none text-white">Mes infos</a>
                </li>
                <li id='etapeMesCommandes' class="text-custom text-center list-group-item col-4">
                    <a href="Controller?page=monCompte&sousPage=mesCommandes" class="text-custom">Mes commandes</a>
                </li>
                <li  id='etapeMesCommentaires' class="text-custom text-center list-group-item col-4">
                    <a href="Controller?page=monCompte&sousPage=mesCommentaires" class="text-custom">Mes commentaires</a>
                </li>
            </ul>
        </div>
    </div>

    <!-- Div du contenu de la page -->
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <div class="row my-4">
                <div class="col-2"></div>
                <label class="col-2 text-start">Civilité</label>
                <input class="col-6" type="text" name='civilite' value='${upCivilite}'/>
            </div>
            <div class="row mt-4">
                <div class="col-2"></div>
                <label class="col-2 text-start">Nom</label>
                <input class="col-6" type="text" name='nom' value='${upNom}'/>
            </div>
            <div class="row mt-4">
                <div class="col-2"></div>
                <label class="col-2 text-start">Prénom</label>
                <input class="col-6" type="text" name='prenom' value='${upPrenom}'/>
            </div>
            <div class="row mt-4">
                <div class="col-2"></div>
                <label class="col-2 text-start">Email</label>
                <input class="col-6" type="text" name='email' value='${upEmail}'/>
            </div>
            <div class="row mt-4">
                <div class="col-2"></div>
                <label class="col-2 text-start">Téléphone</label>
                <input class="col-6" type="text" name='tel' value='${upTel}'/>
            </div>
            <div class="row mt-4 mb-5">
                <div class="col-2"></div>
                <label class="col-2 text-start">Login</label>
                <input class="col-6" type="text" name='login' value='${upLog}' disabled="disabled"/>
            </div>
        </div>
    </div>
    <div class="row">
        <c:if test="${!oldMdp}">
            <h2 class="text-center text-danger">${oldMdpMesError}</h2>
        </c:if>

        <c:if test="${!newMdp}">
            <h2 class="text-center text-danger">${newMdpMesError}</h2>
        </c:if>

        <c:if test="${newMdp}">
            <h2 class="text-center text-success">${newMdpMesSucces}</h2>
        </c:if>
    </div>
    <!-- Div du bas de la page avec les buttons -->

    <div class="row p-0">
        <div class="col-2 p-0"></div>
        <div class="col-8 mb-5 m-auto p-0">
            <!-- Button trigger modal -->
            <button class="btn btn-custom m-auto col-3 w-25" type="button" data-toggle="modal" data-target="#changePassword">
                <a class="text-decoration-none text-white">Changer le mot de passe</a>
            </button>
            <!-- Modal changement mot de passe -->
            <div class="modal" id="changePassword" >
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content modal-custom">
                        <div class="modal-body">
                            <div class="form-title text-center">
                                <h4>Modifiez votre mot de passe</h4>
                            </div>
                            <div class="d-flex flex-column text-center">
                                <form class="m-0" method="get" action='Controller'>
                                    <input type="hidden" name="page" value="monCompte" />
                                    <input type="hidden" name="sousPage" value="mesInfos" />
                                    <input type='password' class="form-control my-2" name='oldMdp' value="" placeholder='Votre ancien mot de passe ...' />
                                    <input type='password' class="form-control my-2" name='newMdp1' placeholder='Votre nouveau mot de passe ...' />
                                    <input type='password' class="form-control my-2" name='newMdp2' placeholder='Confirmation du nouveau mot de passe ...' />
                                    <div class="row">
                                        <input type='submit' class="btn btn-light text-custom m-auto col-3 w-25 my-3" name='doIt' value='Enregistrer' />
                                        <button class="btn btn-light m-auto col-3 w-25" type="button">
                                            <a class="text-decoration-none text-custom" href="Controller?page=monCompte&sousPage=mesInfos">Annuler</a>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                
                 <button class="btn btn-custom m-auto mx-2 col-3 w-25" type="button">
                    <a class="text-decoration-none text-white" href="Controller?sousPage=mesAdresses">Mes adresses</a>
                </button>
                
            <button class="btn btn-custom m-auto col-3 w-25" type="button">
                <a class="text-decoration-none text-white" href="Controller">Retour à l'accueil</a>
            </button>
        </div>
    </div>
    <div class="row text-center">
        <a class="small text-secondary text-decoration-none" href="Controller?action=deconnexion">Déconnexion</a>
    </div>
</div>
