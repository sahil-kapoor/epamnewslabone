
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
		return true;
	}
	return false;
}

function validate(){
	var title = document.getElementById('title-text');
	var brief = document.getElementById('brief-text');
	var content = document.getElementById('content-text');
	var date = document.getElementById('date-text');
	var message = checkTitle(title);
	message += checkBrief(brief);
	message += checkContent(content);
	message += checkDate(date);
	if (message.length > 0){
		alert(message)	
		return false;
	} else {
		quickDate(date)
		return true;
	}
}

function checkContent(content) {
	var text = content.value;
	if (text == ""){
		return "\n" + invalidContent;
	} else {
		if(text.length > 2000){
			return "\n" + invalidContent;
		}
		return "";
	}
}

function checkBrief(brief) {
	var text = brief.value;
	if (text == ""){
		return "\n" + invalidBrief;
	} else {
		if(text.length > 500){
			return "\n" + invalidBrief;
		}
		return "";
	}
}

function checkTitle(title) {
	var text = title.value;
	if (text == ""){
		return "\n" + invalidTitle;
	} else {
		if(text.length > 100){
			return "\n" + invalidTitle;
		}
		return "";
	}
}

function checkDate(date) {
	var text = date.value;
	var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec(text);
    if (matches == null){
	    return "\n" + invalidDate;
    }  
    var d, m, y;
    if( lang == "ru"){
    	 d = matches[1];
    	 m = matches[2] - 1;
    	 y = matches[3];
    }
    if( lang == "en"){
    	d = matches[2];
        m = matches[1] - 1;
        y = matches[3];
    }
    var composedDate = new Date(y, m, d);
    if (composedDate.getDate() == d &&
	        composedDate.getMonth() == m &&
	        composedDate.getFullYear() == y){
    	return "";
    } else {
    	
    	return "\n" + imposibleDate
	}
}

function quickDate(date){
	var text = date.value;
	var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec(text);
    if (matches == null){
	    return invalidDate;
    }  
    var d, m, y;
    if( lang == "ru"){
    	 d = matches[1];
    	 m = matches[2] - 1;
    	 y = matches[3];
    }
    if( lang == "en"){
    	d = matches[2];
        m = matches[1] - 1;
        y = matches[3];
    }
	var date2 = document.getElementById('date-text');
	var hidden = document.getElementById('date-text-hidden');
	hidden.value = m+1+"/"+d+"/"+y;
	date2.removeAttribute("name");
}

