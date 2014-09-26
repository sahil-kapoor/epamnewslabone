<%@page contentType="text/JavaScript" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

window.onload = function() {
	init();
};



function init(){
	setButtonListeners();
	var brief = document.getElementById('brief-text');
	var content = document.getElementById('content-text');	
	brief.setAttribute('maxlength', 500);
	content.setAttribute('maxlength', 2048);
}

function setButtonListeners(){
	var submit = document.getElementById('submit-save-button');
	var cancel = document.getElementById('cancel-save-button');
	submit.setAttribute('onclick', "return validate();");
	cancel.setAttribute('onclick', "return cancel();");
}

function cancel(){
	if(confirm('<bean:message key="js.news.cancel" /> ')){
		javascript:history.back();
	}
	return false;
}

function validate(){
	var title = document.getElementById('title-text');
	var brief = document.getElementById('brief-text');
	var content = document.getElementById('content-text');
	var date = document.getElementById('date-text');
	return checkTitle(title)&&checkDate(date)&&checkBrief(brief)&&checkContent(content);
}

function checkContent(content) {
	var text = content.value;
	if (text == ""){
		alert("'<bean:message key="js.news.bad.content" />'");
		return false;
	} else {
		return true;
	}
}

function checkBrief(brief) {
	var text = brief.value;
	if (text == ""){
		alert('<bean:message key="js.news.bad.brief" />');
		return false;
	} else {
		return true;
	}
}

function checkTitle(title) {
	var text = title.value;
	if (text == ""){
		alert('<bean:message key="js.news.bad.title" />');
		return false;
	} else {
		return true;
	}
}

function checkDate(date) {
	var text = date.value;
	if (text == ""){
		alert('<bean:message key="js.news.not.date" />');
		return false;
	} else {
			var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec(text);
		    if (matches == null){
		     alert('<bean:message key="js.news.bad.date" />');
		     return false;
		    }
		    var d = matches[2];
		    var m = matches[1] - 1;
		    var y = matches[3];
		    var composedDate = new Date(y, m, d);
		    if (composedDate.getDate() == d &&
		        composedDate.getMonth() == m &&
		        composedDate.getFullYear() == y){
		       	 	return true;
		        } else {
		        	alert('<bean:message key="js.news.imposible.date" />');
		        	return false;
		        }		 
	}
}