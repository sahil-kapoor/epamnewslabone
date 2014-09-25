<%@page contentType="text/JavaScript" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

window.onload = function() {
	var cancel = document.getElementById('delete-button');
	cancel.setAttribute('onclick', "return confirm('<bean:message key="js.news.delete.single" /> ');");
};


