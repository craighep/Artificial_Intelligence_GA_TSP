var aktivni=new Array();
var pasivni=new Array();
function initObr(jmeno,pasiv,aktiv,sirka,vyska)
{
  aktivni[jmeno]=new Image(sirka,vyska);
  aktivni[jmeno].src="images/"+aktiv;
  pasivni[jmeno]=new Image(sirka,vyska);pasivni[jmeno].src="images/"+pasiv;
}
function aktivuj(koho)
{
  document.images[koho].src=aktivni[koho].src;
}
function pasivuj(koho)
{
  document.images[koho].src = pasivni[koho].src;
}
function initOnLoad()
{
  if(document.images)
  {
    initObr("home","home.gif","shome.gif",129,38);
    initObr("next","next.gif","snext.gif",129,38);
    initObr("prev","prev.gif","sprev.gif",129,38);
    initObr("back","back.gif","sback.gif",129,38);
  }
}
function PopMeUp(theURL,winName,features)
{
  window.open(theURL,winName,features);
  return false;
}
