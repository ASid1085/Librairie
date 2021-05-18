<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">
<link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css">
-->

<div class="bg-light">
    <div class="container">
        <div class = "row">
            <nav class="navbar navbar-expand-lg navbar-light col">
                <a class="navbar-brand" href="Controller">
                    <img src="Ressources/Images/LogoMarquePageNoir.png" width="140" alt="MarquePage logo">
                </a>
                <form action="Controller" class="col-lg-9 col-md-6 col-6" name="mySearch">    
                    <input type="hidden" name="page" value="catalogue"/>
                    <div class="input-group research">
                        <select id='selectResearch' class="selectCustom mx-2" name="search">
                            <option selected value="title">Titre</option>
                            <option value="auteur">Auteur</option>
                            <option value="isbn">ISBN</option>
                            <option value="motCle">Mot-clé</option>
                        </select>
                        <input id="Saisie" name="saisie" type="text" class="form-control" placeholder="Search ..." required>
                                <button class="btn btn-custom mx-2" type="submit" id="loope">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class=" text-white bi bi-search" viewBox="0 0 16 16" alt='loupe'>
                                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                                    </svg>
                            </button>
                         

                            
                    </div>
                </form>
                <div id="navContent" class="col-lg-3 collapse navbar-collapse">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="Controller">
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#585858" class="bi bi-house-door" viewBox="0 0 16 16" alt='accueil'>
                                    <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"/>
                                </svg>
                            </a>
                        </li>
                        <li class="nav-item">
                            <button class="nav-link border-0 dropdown-toggle" data-toggle="dropdown">
                                <a href="Controller?page=monCompte&sousPage=mesInfos">
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#585858" class="bi bi-person" viewBox="0 0 16 16" alt='user'>
                                    <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
                                </svg></a>
                            </button>
                            <c:if test="${connecter}">
                                <div class="dropdown-menu">
                                <a class="dropdown-item text-custom" href="Controller?page=monCompte&sousPage=mesInfos">Mon compte</a>
                                <a class="dropdown-item text-custom" href="Controller?page=monCompte&sousPage=mesCommandes">Mes commandes</a>
                                <a class="dropdown-item text-custom" href="Controller?page=monCompte&sousPage=mesCommentaires">Mes commentaires</a>
                                <hr>
                                    <a class="dropdown-item text-custom" href="Controller?page=accueil&action=deconnexion">Déconnexion</a>
                            </div>
                            </c:if>
                            <c:if test="${!connecter}">
                                <div class="dropdown-menu">
                                    <p class="dropdown-item m-0 text-custom" data-toggle="modal" data-target="#connCompte">Se connecter</p>
                                </div>
                                
                            <!-- Modal -->
                            <div class="modal" id="connCompte">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content modal-custom">
                                        <div class="modal-body">
                                            <div class="form-title text-center">
                                                <h4>Connectez -vous !</h4>
                                            </div>
                                            <div class="d-flex flex-column text-center">
                                                <form class="m-0" method="post" action='Controller'>
                                                    <input type="hidden" name="page" value="accueil"/>
                                                    <input type="hidden" name="action" value="login"/>
                                                    <input type='text' class="form-control my-2" name='userLogin' value='${userLogin}' id='userLogin' placeholder='Votre login' />
                                                    <input type='password' class="form-control my-2" name='mdp' id='mdp' placeholder='Votre mot de passe ...' />
                                                    <input type='submit' class="btn btn-light  text-secondary my-3" name='doIt' value='Se connecter'  id="refresh" />                           
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </c:if>
                        </li>
                        <li class="nav-item">
                            <button class="iconPanier p-0 mb-3">
                                <a class="nav-link" href="Controller?page=panier">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#585858" class="bi bi-cart3" viewBox="0 0 16 16" alt='panier'>
                                        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .49.598l-1 5a.5.5 0 0 1-.465.401l-9.397.472L4.415 11H13a.5.5 0 0 1 0 1H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l.84 4.479 9.144-.459L13.89 4H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                    </svg><span class="badge rounded-pill badge-custom text-white">${badgePanier}</span>
                                </a>
                            </button>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
</div>