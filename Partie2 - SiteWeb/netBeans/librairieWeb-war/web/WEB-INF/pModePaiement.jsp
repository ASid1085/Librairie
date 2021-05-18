<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<aside class="col-sm-6 col-8 mx-auto ">
                <article class="card border-custom shadow">
                    <div class="card-body pt-5 pb-0 px-5 mb-3">
                        <ul class="nav bg-white nav-pills rounded nav-fill" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active mb-3" data-toggle="pill" href="#nav-tab-card">
                                    <i class="fa fa-credit-card"></i> Carte bancaire</a></li>
                            <li class="nav-item">
                                <a class="nav-link mb-3" data-toggle="pill" href="#nav-tab-paypal">
                                    <i class="fa fa-paypal"></i> Paypal</a></li>
                            <li class="nav-item">
                                <a class="nav-link mb-3" data-toggle="pill" href="#nav-tab-bank">
                                    <i class="fa fa-university"></i> Virement SEPA</a></li>
                        </ul>
                        
                        <div class="tab-content">
                            <!-- Partie paiement en carte bancaire -->
                            <div class="tab-pane fade show active" id="nav-tab-card">
                                <form role="form">
                                    <div class="form-group my-3">
                                        <label for="nameCard" class="text-custom"><strong>Nom complet</strong> (sur la carte)</label>
                                        <input type="text" class="form-control" name="nameCard" placeholder="Jonh Do" required>
                                    </div> <!-- form-group.// -->
                                    <div class="form-group my-3">
                                        <label for="cardNumber" class="text-custom"><strong>n° de la carte</strong></label>
                                        <div class="input-group">
                                            <input type="text" class="form-control" name="cardNumber" placeholder="xxxx xxxx xxxx 5678" required>
                                            <div class="input-group-append">
                                                <span class="input-group-text text-muted">
                                                    <i class="fa fa-cc-visa"></i>   <i class="fa fa-cc-amex"></i>   
                                                    <i class="fa fa-cc-mastercard"></i> 
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-8">
                                            <div class="form-group my-3">
                                                <label><span class="hidden-xs text-custom"><strong>Expiration</strong></span></label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control " placeholder="MM" required/>
                                                    <input type="text" class="form-control" placeholder="YY" required/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-group my-3">
                                                <label class="text-custom" data-toggle="tooltip"  title="" data-original-title="Cryptogramme au dos de la carte"><strong>CVV</strong> <i class="fa fa-question-circle"></i></label>
                                                <input type="text" class="form-control" placeholder="123" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-center mt-3">
                                        <button type="button" class="btn btn-light text-custom mx-auto my-2"><i class="fa  fa-credit-card"></i>
                                            <a class="text-decoration-none text-custom" href="Controller?page=paiement&action=validPaiement&adrFactCde=${adrFactCde}&adrLivCde=${adrLivCde}&typePaiement=CB&mntPaiement=${mntAPayerUrl}&fdp=${fraisLivCde}">
                                                Payer ${mntAPayer}
                                            </a>
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <!-- Partie paiement via paypal -->
                            <div class="tab-pane fade" id="nav-tab-paypal">
                                <p class="text-center my-4">Paypal est un moyen <strong>simple et sécurisé</strong> de payer en ligne<br>sans utiliser votre carte bancaire</p>
                               
                                <h5 class="text-custom text-center mb-0">Montant à régler</h5>
                                <p class="text-custom text-center"><strong>${mntAPayer}</strong></p>
                        
                                <p  class="text-center small">Cliquez sur le bouton ci-dessous pour effectuer votre paiement<br>avec votre compte Paypal</p>
                                <p class="text-center">
                                    <button type="button" class="btn btn-light text-custom"> <i class="fa fa-paypal"></i>
                                        <a class="text-custom text-decoration-none" href="Controller?page=paiement&action=validPaiement&adrFactCde=${adrFactCde}&adrLivCde=${adrLivCde}&typePaiement=Paypal&mntPaiement=${mntAPayerUrl}&fdp=${fraisLivCde}">
                                            Connectez-vous
                                        </a>
                                    </button>
                                </p>

                            </div>
                            <!-- Partie paiement en virement bancaire -->
                            <div class="tab-pane fade" id="nav-tab-bank">
                                <p class="text-center my-4">Vous pouvez régler votre commande par virement bancaire</p>

                                <p class="m-0 text-secondary small">Détail de nos coordonnées bancaires</p>
                                <dl >
                                    <dt class="text-custom">Banque : </dt>
                                    <dd> THE WORLD BANK</dd>
                                </dl>
                                <dl>
                                    <dt class="text-custom">Iban : </dt>
                                    <dd> FR76 1234 5678 9123 4567 8912 345</dd>
                                </dl>
                                <dl>
                                    <dt class="text-custom">Bic : </dt>
                                    <dd> WOBKFRPPXXX </dd>
                                </dl>
                                <dl>
                                    <dt class="text-custom">Montant à payer : </dt>
                                    <dd> ${mntAPayer} </dd>
                                </dl>
                                <dl>
                                    <dt class="text-custom">Réf virement : </dt>
                                    <dd> Indiquez votre nom d'utilisateur <span class="text-secondary small">(${userLogin})</span> et la date de votre commande <span class="text-secondary small">(${userDate})</span>.<br><span class="text-secondary small">Cela permettra l'envoi de votre commande plus rapidement.</span></dd>
                                </dl>
                                <p class="text-center">
                                    <button type="button" class="btn btn-light text-custom"> <i class="fa fa-university"></i>
                                        <a class="text-custom text-decoration-none" href="Controller?page=paiement&action=validPaiement&adrFactCde=${adrFactCde}&adrLivCde=${adrLivCde}&typePaiement=VIR&mntPaiement=${mntAPayerUrl}&fdp=${fraisLivCde}">
                                            Payer par virement
                                        </a>
                                    </button>
                                </p>
                                <p class="alert alert-warning small"><strong>Note:</strong> Votre commande sera envoyée une fois que la réception de votre virement sera confirmée. Cela peut allonger le délai de livraison de 2 à 3 jours ouvrés.</p>
                            </div>
                        </div>
                    </div>
                </article>
            </aside> 
        