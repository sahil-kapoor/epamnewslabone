
window.onload = function() {
	var cancel = document.getElementById('delete-button');
	cancel.setAttribute('onclick', "return confirm(deleteSingle);");
	resizeTd();
};

window.onresize = function() {
	resizeTd();
}



function resizeTd(){
	var page = document.getElementById('news-view-body');	
	var div = document.getElementsByClassName("td-div-body");	
	for (var i = 0; i < div.length; ++i) {
	   div[i].style.width = 0.65*page.offsetWidth;
	}
}

