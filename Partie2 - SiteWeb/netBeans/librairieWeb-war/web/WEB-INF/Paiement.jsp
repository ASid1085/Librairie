<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8"/>
        <title>Marque Page - Paiement</title>
        <link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css" />
        <link rel="stylesheet" href="Ressources/CSS/style.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <div>
            <jsp:include page="header.jsp"/>
        </div>
        <div class="container ">
             <c:if test="${userConnecter}">
                <p class="text-center alert alert-success">${messageSucces}</p>
            </c:if>
            <div class="row my-5">
                <div id="bandeau" class='col-6 m-auto'>
                    <ul id='bandeauEtapes' class="list-group list-group-horizontal">
                        <li id='etapeMonPanier' class="text-custom text-center list-group-item col-4"><a href="Controller?page=panier" class="text-custom">Mon panier</a></li>
                        <li id='etapeAdresse' class="text-custom text-center list-group-item col-4"><a href="Controller?page=adresses&user=${userConnect}" class="text-custom">Adresses</a></li>
                        <li  id='etapePaiement' class="text-customEtape text-center list-group-item col-4">Paiement</li>
                    </ul>
                </div>
            </div>
                <c:if test="${!paiementValid}">
                    <div >
                        <jsp:include page="pModePaiement.jsp"/>
                    </div>
                </c:if>
                <c:if test="${paiementValid}">
                    <div >
                        <jsp:include page="pValidPaiement.jsp"/>
                    </div>
                </c:if>
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
