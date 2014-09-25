<%@page contentType="text/JavaScript" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

window.onload = function() {
	var cancel = document.getElementById('delete-all-button');
	cancel.setAttribute('onclick', "return checkCheckBox();");
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

