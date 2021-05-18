<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="UTF-8"/>
        <title>Marque Page - Ajout adresse</title>
        <link rel="stylesheet" href="Ressources/CSS/bootstrap.min.css" />
        <link rel="stylesheet" href="Ressources/CSS/style.css" />
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <jsp:include page="header.jsp"/><br><br>
        <div class="container">

            <div class='row'>
                <div class='text-center'>
                    <h1 class='text-custom mb-4 mx-auto'>Votre carnet d'adresse</h5>
                </div>
                <hr>
            </div>
            
            <div class="row">
                
                <div class="col-2 mt-3">
                    <h6 class="text-custom">Liste d'adresses connues</h6>
                    <hr>
                    <c:if test="${!listAdrsEmpty}">
                        <c:forEach var="adr" items="${listAdrsUser}" varStatus="adrUser">
                        <div  class="form-check my-2" >
                            <input type='hidden' />
                            <input class="form-check-input" type="radio" value="${adr.code}" id="${adrUser.count+100}" name="checkAdr" onchange="searchAdresse()"/>
                            <label class="form-check-label" for="checkAdr" id="checkAdrLabel">${adr.libelle}</label>
                        </div>
                    </c:forEach>
                        
                    </c:if>
                    <c:if test="${listAdrsEmpty}">
                        <p class="text-secondary">Vous n'avez aucune adresse connue</p>
                    </c:if>
                        
                        <button class='btn btn-custom'>
                            <a class='text-decoration-none text-white' href="Controller?&sousPage=mesAdresses&action=afficherAdr&adrSelect=">Nouvelle adresse</a>
                        </button>
                        
                </div>
                
                <div id="bodyAdr" class="col-9 col-lg-9 mt-3">
                    <jsp:include page="detailAdresse.jsp" /> 
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
