flag = {};

validate = function(){
	if(document.getElementById(arguments[0]).value == ""){
		showError("form:"+arguments[1], arguments[2]);
		flag["form:"+arguments[1]] = false;
		return false;
	}else{
		removeError("form:"+arguments[1]);
		flag["form:"+arguments[1]] = true;
		return true;
	}
}

validateName = function(){
	if(validate(arguments[0], arguments[1], arguments[2])){
		if(!( /^[A-Za-z0-9 |]+$/.test(document.getElementById(arguments[0]).value))){
			flag["form:"+arguments[1]] = false;
			showError("form:"+arguments[1], "Only Letters Are allowed");
		}else{
			removeError("form:"+arguments[1]);
			flag["form:"+arguments[1]] = true;
		}
	}	
}

validateDropDown = function(){
	if(document.getElementById(arguments[0]).value == ""){
		showError("form:"+arguments[1], arguments[2]);
		flag["form:"+arguments[1]] = false;
		return false;
	}else{
		removeError("form:"+arguments[1]);
		flag["form:"+arguments[1]] = true;
		return true;
	}
}

validateTravelDate = function(){
	if(validate(arguments[0], arguments[1], arguments[2])){		
		var initiationDate = (document.getElementById("form:travelStartDate_input").value);
		initiationDate = initiationDate.split("/").reverse().join("-");	
		var confirmationDate = (document.getElementById("form:travelEndDate_input").value);
		confirmationDate = confirmationDate.split("/").reverse().join("-");
		
		if(new Date(confirmationDate).getTime() - new Date(initiationDate).getTime() <= 0){
			flag["form:"+arguments[1]] = false;	
			showError("form:travelEndDate-msg", "Start date should be ahead of End date");
		}else{
			removeError("form:travelEndDate-msg");
			flag["form:"+arguments[1]] = true;
		}
	}	
}

validateNumber = function(){
	if(validate(arguments[0], arguments[1], arguments[2])){
		if(/\D/.test(document.getElementById(arguments[0]).value)){
			flag["form:"+arguments[1]] = false;
			showError("form:"+arguments[1], "Only Numbers Are allowed");
		}else if(document.getElementById(arguments[0]).value.length != 6){
			flag["form:"+arguments[1]] = false;
			showError("form:"+arguments[1], "Maximum 6 Digit are Allowed");
		}else{
			removeError("form:"+arguments[1]);
			flag["form:"+arguments[1]] = true;
		}
	}
}

validateLocation = function(){
	if(validate(arguments[0], arguments[1], arguments[2])){
		if(document.getElementById(arguments[0]).value.match(/^[a-zA-Z ]+$/) == null){
			showError("form:"+arguments[1], "Only Characters Allowed");
			flag["form:"+arguments[1]] = false;
		}else{
			removeError("form:"+arguments[1]);
			flag["form:"+arguments[1]] = true;
		}
	}
}

validateMobile = function(){
	if(validate(arguments[0], arguments[1], arguments[2])){
		if(/\D/.test(document.getElementById(arguments[0]).value)){
			flag["form:"+arguments[1]] = false;
			showError("form:"+arguments[1], "Only Numbers Are allowed");
		}else if(document.getElementById(arguments[0]).value.length != 10){
			flag["form:"+arguments[1]] = false;
			showError("form:"+arguments[1], "Maximum 10 Digit are Allowed");
		}else if( !(document.getElementById(arguments[0]).value.charAt(0) == "9" || document.getElementById(arguments[0]).value.charAt(0) == "8" || document.getElementById(arguments[0]).value.charAt(0) == "7")){
			flag["form:"+arguments[1]] = false;
			showError("form:"+arguments[1], "Invalid Mobile Number");
		}else{
			removeError("form:"+arguments[1]);
			flag["form:"+arguments[1]] = true;
		}
	}
}

validateEmail = function(){
	if(validate(arguments[0], arguments[1], arguments[2])){
		if(document.getElementById(arguments[0]).value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/) == null){
			showError("form:"+arguments[1], "Invalid Email");
			flag["form:"+arguments[1]] = false;
		}else{
			removeError("form:"+arguments[1]);
			flag["form:"+arguments[1]] = true;
		}
	}	
}

serialNumber = function(){
	if(validate(arguments[0], arguments[1], arguments[2])){
		if(document.getElementById(arguments[0]).value.match(/^[a-zA-Z0-9-@]+$/) == null){
			showError("form:"+arguments[1], "Invalid Serial Number");
			flag["form:"+arguments[1]] = false;
		}else{
			removeError("form:"+arguments[1]);
			flag["form:"+arguments[1]] = true;
		}
	}	
}


function showError(messageId, message){
	document.getElementById(messageId).classList.add("ui-message-error", "ui-widget", "ui-corner-all");
	document.getElementById(messageId).innerHTML = message;
}

function removeError(messageId){
	document.getElementById(messageId).classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
	document.getElementById(messageId).innerHTML = "";
}


getFlag = function(){
	let y = 0;
	for(let x in flag)
		if(flag[x]==true)
			y++;
	
	if(Object.keys(flag).length == y)
		return true;
	else
		return false;
}


let validateMailDailog = true;
emailFieldValidation = function() {
	let toEmail = document.getElementById(arguments[0]).value;

	if (toEmail == "" && arguments[0].includes("to")) {
		document.getElementById(arguments[0]+"-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById(arguments[0]+"-msg").innerHTML = "Please Enter Email Address";
		validateMailDailog = false;
	}else if(toEmail == ""){ 
		document.getElementById(arguments[0]+"-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById(arguments[0]+"-msg").innerHTML = "";
		validateMailDailog = true;
	}else {
		document.getElementById(arguments[0]+"-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById(arguments[0]+"-msg").innerHTML = "";
		
		let toEmailArray = toEmail.split(",");
		let counter = 0;
		
		for(let i=0; i<toEmailArray.length; i++){
			if(emailValidation(toEmailArray[i]))
				counter++;
		}
		
		if(counter == toEmailArray.length){
			document.getElementById(arguments[0]+"-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
			document.getElementById(arguments[0]+"-msg").innerHTML = "";
			validateMailDailog = true;
		}else{
			document.getElementById(arguments[0]+"-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
			document.getElementById(arguments[0]+"-msg").innerHTML = "Please Check Entered Emails";
			validateMailDailog = false;
		}
	}
}

emailValidation = function() {
	if (arguments[0].match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/) == null) {
		return false;
	}
	return true;
}

subjectFieldValidation = function(){
	if (document.getElementById(arguments[0]).value == "") {
		document.getElementById(arguments[0]+"-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById(arguments[0]+"-msg").innerHTML = "Please Enter Subject";
		validateMailDailog = true;
	} else {
		document.getElementById(arguments[0]+"-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById(arguments[0]+"-msg").innerHTML = "";
		validateMailDailog = false;
	}
}

validateOnClick = function(){
	return validateMailDailog;
}