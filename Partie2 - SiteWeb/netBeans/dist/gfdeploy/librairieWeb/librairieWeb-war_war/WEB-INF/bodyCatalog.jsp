<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container">
    <c:if test="${userConnecter}">
        <p class="text-center alert alert-success">${messageSucces}</p>
    </c:if>
    <c:if test="${research}">
        <div class='row'>
            <div class='col-9'>
                <h3 id="titreSearch"  class='font-weight-light text-custom mt-5 px-5'>${titreSection}</h3>
            </div>
            <div class='col-3 text-end'>
                <button class="btn btn-custom2 mt-5 px-5">
                    <a class="text-decoration-none text-custom" href="Controller?page=catalogue">
                        Revenir au catalogue
                    </a>
                </button>
                <!-- <input class='col btn btn-custom2 mt-5 px-5 text-custom' type="button" value='Revenir au catalogue'  onclick="clearSearch()" /> -->
            </div> 
            <hr>
        </div>
    </c:if>

    <c:if test="${!research}">
        <div class='row'>
            <div class='col-10'>
                <h3 id="titreSearch"  class='font-weight-light text-custom mt-5 px-5'>Tous nos livres</h3>
            </div>
            <div id='cleanSearch' class='col-2'>
                <!-- <input class='col btn  btn-custom2 mt-5 px-5 text-custom' type="button" value='Revenir au catalogue'  onclick="clearSearch()" /> -->
            </div> 
            <hr>
        </div>
    </c:if>

    <div class="row">
        <div class="col-2">
            <h6 class="text-custom">Filtrer par prix</h6>
            <hr>
            <form>
                <fieldset >
                    <input type="range" id="d" name="d" min="0" max="80" step="5" value="0" oninput="result4.value=parseInt(d.value)" onchange="searchPrice()"/>
                    <p>
                        Livres inférieurs à <output class="text-custom" name="result4"></output><span class="text-custom">  €</span>
                    </p>
                </fieldset>
            </form>
            <h6 class="text-custom">Filter par Thème</h6>
            <hr>
            <c:forEach var="t" items="${listThemes}" varStatus="status">
                <div  class="form-check" >
                    <input class="form-check-input" type="radio" value="${t.nom}" id="${status.count}" name="check" onchange="searchTheme()">
                    <label class="form-check-label" for="check" id="checkLabel">${t.nom}</label>
                </div>
            </c:forEach>
        </div>
        <div id="bodySearch" class="col-10 col-lg-10">
            <jsp:include page="resultSearch.jsp" /> 
        </div>
    </div>
</div>
