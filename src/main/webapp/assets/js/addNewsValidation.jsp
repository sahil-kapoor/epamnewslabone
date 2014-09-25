<%@page contentType="text/JavaScript" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

window.onload = function() {
	resizeTd();
	init();
	setTodayDate();
};

window.onresize = function() {
	resizeTd();
}


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
	cancel.setAttribute('onclick', "return confirm('<bean:message key="js.news.cancel" /> ');");
}


function validate(){
	var title = document.getElementById('title-text');
	var brief = document.getElementById('brief-text');
	var content = document.getElementById('content-text');
	var date = document.getElementById('date-text');
	return checkTitle(title)&&checkDate(date)&&checkBrief(brief)&&checkContent(content);
}


function resizeTd(){
	var page = document.getElementById('news-view-body');	
	var div = document.getElementsByClassName("td-div-body");	
	for (var i = 0; i < div.length; ++i) {
	   div[i].style.width = 0.65*page.offsetWidth;
	}
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

function setTodayDate(){
	//var date = document.getElementById('date-text');
	document.getElementById("date-text").valueAsDate = new Date();
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
		alert("date must have value");
		return false;
	} else {
		//var patt = /^(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)\d\d+$/;
		//alert(patt.test(text))
		return true;;
	}
}