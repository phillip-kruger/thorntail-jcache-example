/* 
 * Semantic-UI
 * Javascript file for index.html
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
var contextRoot = getContextRoot();
var messages = document.getElementById("quote");
var cachedTime = document.getElementById("cachedTime");

$('document').ready(function () {
    var url = contextRoot + "/api";
    var quoteJson = httpGet(url);

    console.log(quoteJson);

    var quote = JSON.parse(quoteJson);
    writeResponse(quote.text,quote.author,quote.time);
});

function writeResponse(text,author,time){
    
    
    messages.innerHTML += "<div class='ui statistic'>"
        + "<div class='value'>"
        + text
        + "</div>"
        + "<div class='label'>"
        + author
        + "</div>"
        + "</div>";
 
    cachedTime.innerHTML += "<div class='ui label'>"
        + "<i class='stopwatch icon'></i>"
        + time;
        + "</div>";

}

function httpGet(theUrl) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, false); // false for synchronous request
    xmlHttp.send(null);
    return xmlHttp.responseText;
}


function getContextRoot() {
    var base = document.getElementsByTagName('base')[0];
    if (base && base.href && (base.href.length > 0)) {
        base = base.href;
    } else {
        base = document.URL;
    }

    var u = base.substr(0, base.indexOf("/", base.indexOf("/", base.indexOf("//") + 2) + 1));
    var u = u.substr(u.indexOf("//") + 2);
    var contextRoot = u.substr(u.indexOf("/"));
    return contextRoot;
}