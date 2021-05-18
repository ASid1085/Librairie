<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!-- Div contenant le formulaire -->
<div class="row">
    <fieldset class="fieldsetCreate p-3">
        <form action="Controller" class='m-0'>
            <input type="hidden" name="sousPage" value="mesAdresses"/>
            <input type="hidden" name="adrSelectNew" value="${recupAdrCode}"/>
            <label class="col-1"></label>
            <div class="form-check form-check-inline text-start">
                <input class="form-check-input" type="radio" name="civilite" id="inlineRadio1" value="madame">
                <label class="form-check-label" for="inlineRadio1">Madame</label>
            </div>
            <div class="form-check form-check-inline mx-5">
                <input class="form-check-input" type="radio" name="civilite" id="inlineRadio2" value="monsieur">
                <label class="form-check-label" for="inlineRadio2">Monsieur</label>
            </div>
            
            <div class="row mt-4">
                <label class="col-1"></label>
                <label class="col-1 text-start">Nom *</label>
                <input type="text" class="inputCreate col-4" name="nom" value="${recupAdrNom}" required />
                <label class="col-1 text-start">Prénom *</label>
                <input type="text" class="inputCreate col-4" name="prenom" value="${recupAdrPrenom}" required />
            </div>
            <br>
           <div class="row mt-4">
                <label class="col-1"></label>
                <label class="col-1 text-start">Entreprise</label>
                <input type="text" class="inputCreate col-4" name="nomEntreprise" value="${recupAdrEntreprise}"/>
            </div>
            <br>
            <div class="row mt-4">
                <label class="col-1"></label>
                <label class="col-1 text-start">n° rue *</label>
                <input type="text" class="inputCreate col-1" name="numRue" value="${recupAdrNum}" required />
                <label class="col-1 text-start">Rue *</label>
                <input type="text" class="inputCreate mx-3 col-5" name="rue" value="${recupAdrRue}" required />
            </div>
            <br>
            <div class="row mt-4">
                <label class="col-1"></label>
                <label class="col-1 text-start">Compl.</label>
                <input type="text" class="inputCreate mb-3 col-7" name="complement" value="${recupAdrComplement}"/>
            </div>
            <br>
            <div class="row mt-4">
                <label class="col-1"></label>
                <label class="col-1 text-start">CP *</label>
                <input type="text" class="inputCreate col-1" name="cp" value="${recupAdrCp}" required />
                <label class="col-1 text-start">Ville *</label>
                <input type="text" class="inputCreate mx-3 col-5" name="ville" value="${recupAdrVille}" required />
            </div>
            <br>
            <div class="row mt-4">
                <label class="col-1"></label>
                <label class="col-1 text-start">Pays *</label>
                <input type="text" class="inputCreate col-4" name="pays" value="${recupAdrPays}" required/>
            </div>
            <br>
            <div class="row mt-4">
                <label class="col-1"></label>
                <label class="col-1 text-start">Téléphone</label>
                <input type="text" class="inputCreate col-4" name="tel" value="${recupAdrTel}"/>
            </div>
            <div class="row">
                <label class="col-2"></label>
            <p class=" col-6 small text-secondary mb-3 text-start">Ce numéro sera utilisé qu'en cas de problème avec votre commande</p>
            </div>
            <br>
            <label class="col-1"></label>
                <label class="col-3 text-start">Nommez cette adresse *</label>
                <br>
                <label class="col-1"></label>
                <input type="text" class="inputCreate col-5" name="libelle" value="${recupAdrLibelle}" required />
           
            <br>
            <div class="row">
                <label class="col-1"></label>
            <p class=" col-6 small text-secondary mb-3 text-start">Donnez un nom à cette adresse pour la retrouver plus facilement.</p>
            </div>
            <br>
            <div class="text-center mx-auto">
                <button class="btn btn-custom w-25 mb-3 mx-1" type="submit" name="action" value="enregistrerAdr">
                Enregistrer
            </button>
                <button class="btn btn-custom w-25 mb-3  mx-1" type="submit" name="action" value="supprimerAdr">
                Supprimer
            </button>
            </div>
            
            
        </form>
        <c:if test="${checkUpAdr}">
            <h6 class=" small text-center text-success">L'adresse a bien été enregistrée</h6>
        </c:if>

        <c:if test="${!checkUpAdr}">
            <h6 class="small text-center text-danger">${errorUpAdr}</h6>
        </c:if>

    </fieldset>
</div>  

  
            
<!-- Div contenant la partie basse avec les boutons de retour -->
<div class="row-cols-6 text-center my-4">
    <button class="btn btn-light m-auto col-5 w-25 mx-1" type="button">
        <a class="text-decoration-none text-custom" href="Controller">Retour à l'accueil</a>
    </button>
    <button class="btn btn-light m-auto col-5 w-25 mx-1" type="button">
        <a class="text-decoration-none text-custom" href="Controller?page=panier">Retour au panier</a>
    </button>
</div>

