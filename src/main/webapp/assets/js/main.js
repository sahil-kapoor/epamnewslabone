
window.addEventListener('load', setNavLink, false);


function setNavLink() {	
	var pageName = document.getElementById("current-page-hidden").value;
	document.getElementById("page-name").innerHTML = ">>" + pageName;
}

function changeLanguage(href) {
	var req = getXmlHttp()
	req.onreadystatechange = function() {
		if (req.readyState == 4 && req.status == 200) {											
			location.reload();
		}
	}
	req.open('GET', href, true);
	req.send(null); 
}


function getXmlHttp() {
	var xmlhttp;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
		}
	}
	if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}