<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="mb-5 text-center">
    <aside class="col-sm-6 col-8 m-auto">
        <article class="card border-custom shadow">
            <div class="card-body pt-5 pb-0 px-5 mb-3">
                <h3 class="text-custom text-center">Paiement réalisé avec succès !</h3>
                <br>
                <p class="text-center">Nous vous remercions pour votre achat et nous occupons de sa livraison dans les plus brefs délais.</p>
                <br>
                <p class="text-center text-custom">Voici votre numéro de commande : <strong class="text-dark">${numCde}</strong></p>
                <br>
                <p class="text-center m-0">Retrouver le détail de votre commande <strong><a class="text-custom text-decoration-none" href="Controller?page=monCompte&sousPage=mesCommandes">ici</a></strong>.</p>
                <p class="text-center">Pour nous poser une question c'est par <strong><a class="text-custom text-decoration-none" href="Controller?page=contact">ici</a></strong>.</p>
                <br>
                <p class="text-center small text-secondary m-0">N'oubliez pas de laissez un commentaire après votre lecture :)</p>
                <p class="text-center text-custom"><strong>Bonne lecture et à bientôt !</strong></p>
            </div>
        </article>
    </aside> 
                
                <button class="btn btn-light mt-5">
                    <a class="text-custom text-decoration-none" href="Controller">Retour à l'accueil</a>
                </button>
</div>

