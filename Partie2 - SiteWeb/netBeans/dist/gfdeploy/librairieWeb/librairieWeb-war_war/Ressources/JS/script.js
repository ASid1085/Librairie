//Calcul du prix total dans la fenêtre modal d'ajout du livre dans le panier
function priceT() {
    let priceU = document.getElementById("priceU").value;
    let qteModal = document.getElementById("qteModal").value;
//    alert(priceU + " " + qteModal);
    let priceF = parseFloat(priceU.replace(",", ".")) * qteModal;
//    alert (priceF.toFixed(2).replace( ".", "," ));
    let recup = document.getElementById("PrixTotalModal");
    recup.innerHTML = "Prix total<br>" + priceF.toFixed(2).replace(".", ",") + " €";
}

//Refrech de la page des recherches
function clearSearch() {
    window.location.reload();
}

// AJAX
function getxmlhttp() {
    let xmlhttp = null;
    if (window.XMLHttpRequest) { // Mozilla
        xmlhttp = new XMLHttpRequest();
    } else {
        if (window.ActiveXObject) { // IE
            try {
                xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        } else {
            alert("Votre navigateur ne supporte pas XMLHttpRequest");
        }
    }
    return xmlhttp;
}

//affichage du résultat de la recherche dans le catalogue via la barre de recherche avec l'élément HTML button
document.querySelector("#loope").addEventListener("click", function (e) {
//     alert("je suis dans la fonction loope");
    let select = document.getElementById("selectResearch");
    let categorie = select.options[select.selectedIndex].value;
//    alert(categorie);
    let saisie = document.getElementById("Saisie").value;
//    alert(saisie);
    url = "Controller?page=resultSearch&search=" + categorie + "&saisie=" + saisie;
//    alert(url);
    xmlhttp = getxmlhttp();
    xmlhttp.onreadystatechange = xmlhttpChange;
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null); // Valeur null car en GET. Si methode POST porte les paramètres de l'URL
});

//affichage du résultat de la recherche dans le catalogue via le range des prix
function searchPrice() {    
    url = "Controller?&page=resultSearch&search=price&saisie=" +parseInt(d.value);
//    alert(url);
    xmlhttp = getxmlhttp();
    xmlhttp.onreadystatechange = xmlhttpChange;
    xmlhttp.open("GET", url, true);
    xmlhttp.send(null); // Valeur null car en GET. Si methode POST porte les paramètres de l'URL
}


//affichage du résultat de la recherche dans le catalogue via la checkbox des thèmes
function searchTheme() {
    for (var i = 1; i <= 16; i++) {
        let theme = document.getElementById(i).checked;
        if (theme === true) {
            let val = document.getElementById(i).value;
//            alert(val);
            url = "Controller?&page=resultSearch&search=theme&saisie=" + val;
//            alert(url);
            xmlhttp = getxmlhttp();
            xmlhttp.onreadystatechange = xmlhttpChange;
            xmlhttp.open("GET", url, true);
            xmlhttp.send(null); // Valeur null car en GET. Si methode POST porte les paramètres de l'URL
        }
    }
}

function xmlhttpChange() {
//    alert("function xmlhttpChange()");
    if (xmlhttp.readyState === 4) { // if xmlhttp shows "loaded"
        if (xmlhttp.status === 200) { // if "OK"
            s = xmlhttp.responseText;
//            alert("(" + s + ")");

            let titre = document.getElementById("titreSearch");
            titre.innerHTML = "Résultats de la recherche";

            let btn = document.getElementById("cleanSearch");
            btn.innerHTML = "<input class='col btn  btn-custom2 mt-5 px-5 text-custom' type='button' value='Revenir au catalogue' onclick='clearSearch()' />";

            let bodySearch = document.getElementById("bodySearch");
            bodySearch.innerHTML = s;
 
        } else {
            let stat = xmlhttp.status;
            alert("Problem retrieving XML data : erreur " + stat);
        }
    }
}

//affichage de l'adresse selectionnée dans la liste des adresses du user
function searchAdresse() {
    for (var i = 101; i < 200; i++) {
        let adr = document.getElementById(i).checked;
        if (adr === true) {
            let val = document.getElementById(i).value;
//            alert(val);
            url = "Controller?sousPage=mesAdresses&search=monAdresse&adrSelect=" + val;
//            alert(url);
            xmlhttp = getxmlhttp();
            xmlhttp.onreadystatechange = xmlhttpChange2;
            xmlhttp.open("GET", url, true);
            xmlhttp.send(null); // Valeur null car en GET. Si methode POST porte les paramètres de l'URL
        }
    }
}

function xmlhttpChange2() {
//        alert("function xmlhttpChange2()");
    if (xmlhttp.readyState === 4) { // if xmlhttp shows "loaded"
        if (xmlhttp.status === 200) { // if "OK"
            s = xmlhttp.responseText;
//            alert("(" + s + ")");
            
            let bodyAdr = document.getElementById("bodyAdr");
            bodyAdr.innerHTML = s;
 
        } else {
            let stat = xmlhttp.status;
            alert("Problem retrieving XML data : erreur " + stat);
        }
    }
}

var star1 = document.getElementById('star1');
if (star1) {
    star1.addEventListener("click", function (event) {
        var note = document.getElementById("star1").value;
//        alert(note);
        document.getElementById("star1").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("recupNote").innerHTML = "<input type='hidden' name='note' value='"+note+"'/>";
        event.preventDefault();
    }, false);
}

var star2 = document.getElementById('star2');
if (star2) {
    star2.addEventListener("click", function (event) {
        var note = document.getElementById("star2").value;
//        alert(note);
        document.getElementById("star1").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star2").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("recupNote").innerHTML = "<input type='hidden' name='note' value='"+note+"'/>";
        event.preventDefault();
    }, false);
}

var star3 = document.getElementById('star3');
if (star3) {
    star3.addEventListener("click", function (event) {
        var note = document.getElementById("star3").value;
//        alert(note);
        document.getElementById("star1").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star2").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star3").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("recupNote").innerHTML = "<input type='hidden' name='note' value='"+note+"'/>";
        event.preventDefault();
    }, false);
}

var star4 = document.getElementById('star4');
if (star4) {
    star4.addEventListener("click", function (event) {
        var note = document.getElementById("star4").value;
//        alert(note);
        document.getElementById("star1").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star2").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star3").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star4").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("recupNote").innerHTML = "<input type='hidden' name='note' value='"+note+"'/>";
        event.preventDefault();
    }, false);
}

var star5 = document.getElementById('star5');
if (star5) {
    star5.addEventListener("click", function (event) {
        var note = document.getElementById("star5").value;
//        alert(note);
        document.getElementById("star1").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star2").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star3").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star4").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("star5").style.backgroundImage = "url('Ressources/Bouton/starFill.svg')";
        document.getElementById("recupNote").innerHTML = "<input type='hidden' name='note' value='"+note+"'/>";
        event.preventDefault();
    }, false);
}

//$(function () {
//    $("#star1").hover(function () {
//        $("#star1").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//    }, function() {
//        $("#star1").css("backgroundImage", "url('Ressources/Bouton/starEmpty.svg')");
//    });
//    $("#star2").focus(function () {
//        $("#star1").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star2").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//    });
//    $("#star3").focus(function () {
//        $("#star1").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star2").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star3").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//    });
//    $("#star4").focus(function () {
//        $("#star1").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star2").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star3").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star4").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//    });
//    $("#star5").focus(function () {
//        $("#star1").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star2").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star3").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star4").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//        $("#star5").css("backgroundImage", "url('Ressources/Bouton/starFill.svg')");
//    });
//});