
window.onload = function() {
	var deleteButton = document.getElementById('delete-all-button');
	deleteButton.setAttribute('onclick', "return checkCheckBox();");
	deletedeletebutton();
} 

function checkCheckBox(){
	var checks = document.getElementsByClassName("check-box");
	for(var i = 0; i < checks.length; i++){
		if (checks[i].checked){
			return confirm(confirmDelete);
		}
	}
	alert(nothingDelete);
	return false;	
}

function deletedeletebutton(){
 	var checks = document.getElementsByClassName("check-box");
	if(checks.length == 0){
		document.getElementById('delete-all-button').remove();
	}
}


