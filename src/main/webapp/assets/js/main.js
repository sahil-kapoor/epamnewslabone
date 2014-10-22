window.addEventListener('load', setNavLink, false);

function setNavLink() {
	var pageName = document.getElementById("current-page-hidden").value;
	document.getElementById("page-name").innerHTML = " >> " + pageName;
}
