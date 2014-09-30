
window.onload = function() {
	init();
};



function init(){
	setButtonListeners();
	var brief = document.getElementById('brief-text');
	var content = document.getElementById('content-text');	
	brief.setAttribute('maxlength', 500);
	content.setAttribute('maxlength', 2000);
}

function setButtonListeners(){
	var submit = document.getElementById('submit-save-button');
	var cancel = document.getElementById('cancel-save-button');
	submit.setAttribute('onclick', "return validate();");
	cancel.setAttribute('onclick', "return cancelAdd();");
}

function cancelAdd(){
	if(confirm(cancel)){
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
		alert(invalidContent);
		return false;
	} else {
		if(text.length > 2000){
			return false;
		}
		return true;
	}
}

function checkBrief(brief) {
	var text = brief.value;
	if (text == ""){
		alert(invalidBrief);
		return false;
	} else {
		if(text.length > 500){
			return false;
		}
		return true;
	}
}

function checkTitle(title) {
	var text = title.value;
	if (text == ""){
		alert(invalidTitle);
		return false;
	} else {
		if(text.length > 100){
			return false;
		}
		return true;
	}
}

function checkDate(date) {
	var text = date.value;
	if (text == ""){
		alert(invalidDate);
		return false;
	} else {
			var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec(text);
		    if (matches == null){
		     alert(invalidDate);
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
		        	alert(imposibleDate);
		        	return false;
		        }		 
	}
}