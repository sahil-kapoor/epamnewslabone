<%@page contentType="text/JavaScript" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

window.onload = function() {
	var deleteButton = document.getElementById('delete-all-button');
	deleteButton.setAttribute('onclick', "return checkCheckBox();");
	deletedeletebutton();
} 

function checkCheckBox(){
	var checks = document.getElementsByClassName("check-box");
	for(var i = 0; i < checks.length; i++){
		if (checks[i].checked){
			return confirm('<bean:message key="js.news.delete.all" />');
		}
	}
	alert(' <bean:message key="js.news.delete.nothing" />');
	return false;	
}

function deletedeletebutton(){
 	var checks = document.getElementsByClassName("check-box");
	if(checks.length == 0){
		document.getElementById('delete-all-button').remove();
	}
}


