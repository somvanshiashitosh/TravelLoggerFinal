flag = {};
let sum = {};
let total = 0;

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

validateCurrency = function(){
	if(validate(arguments[0], arguments[1], arguments[2])){
		if(!( /^[A-Za-z]+$/.test(document.getElementById(arguments[0]).value))){
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

dummy = function(){
	if(document.getElementById(arguments[0]).value == ""){
		flag["form:"+arguments[1]] = false;
		return false;
	}else{
		flag["form:"+arguments[1]] = true;
		return true;
	}
}

validateTravelDate = function(){
	if(dummy(arguments[0], arguments[1], arguments[2])){		
		var initiationDate = (document.getElementById("form:travelStartDate_input").value);
		if(!validateDate(initiationDate)){
			flag["form:"+arguments[1]] = false;	
			showError("form:travelStartDate-msg", "Invalid Date");
			return null;
		}
		
		initiationDate = initiationDate.split("/").reverse().join("-");	
		var confirmationDate = (document.getElementById("form:travelEndDate_input").value);
		if(!validateDate(confirmationDate)){
			flag["form:"+arguments[1]] = false;	
			showError("form:travelEndDate-msg", "Invalid Date");
			return null;
		}
		confirmationDate = confirmationDate.split("/").reverse().join("-");
		
		if(new Date(confirmationDate).getTime() - new Date(initiationDate).getTime() <= 0){
			flag["form:"+arguments[1]] = false;	
			showError("form:travelEndDate-msg", "Please Check Travel dates");
		}else{
			removeError("form:travelEndDate-msg");
			flag["form:"+arguments[1]] = true;
			
			let number = (new Date(confirmationDate) - new Date(initiationDate))/(1000*60*60*24);
			
			if(number > 0)
				setNumberOfDays(number);
			else
				setNumberOfDays("");
		}
	}	
}

setNumberOfDays = function(){
	document.getElementById("form:hotelNumberOfDays").value = arguments[0];
	document.getElementById("form:travelNumberOfDay").value = arguments[0];
	document.getElementById("form:perdiemNumberOfDays").value = arguments[0];
}

calculateOnDayChange = function(){
	if(document.getElementById("form:"+arguments[1]).value == ""){
		showError("form:"+arguments[3], arguments[4]);
		flag["form:"+arguments[3]] = false;
	}else{
		removeError("form:"+arguments[3]);
		flag["form:"+arguments[3]] = true;
		//calculateTotal(arguments[1], arguments[0], arguments[2], arguments[3], arguments[4]);
		sum[arguments[0]] = Number(document.getElementById("form:"+arguments[2]).value = document.getElementById("form:"+arguments[1]).value * document.getElementById("form:"+arguments[0]).value);
		setTotal();
	}
}


calculateTotal = function(){	
	if(validate("form:"+arguments[1], arguments[3], arguments[4])){
		sum[arguments[1]] = Number(document.getElementById("form:"+arguments[2]).value = document.getElementById("form:"+arguments[0]).value * document.getElementById("form:"+arguments[1]).value);
		setTotal();
	}	
}

conversionRateCall = function(){
	if(validate(arguments[0],arguments[1],arguments[2])){
		perDiemCalculation();
	}
}

perDiemCalculation = function(){
	applicableCurrency = document.getElementById("form:applicable").value;
	conversionRate = document.getElementById("form:ConversionRate").value;
	removeError("form:applicable-msg");
	removeError("form:ConversionRate-msg");
	
	if(arguments[0]!=undefined){
		if(document.getElementById("form:perdiemNumberOfDays").value == ""){
			showError("form:perdiumTotal-msg", "Please Enter Number Of day");
			flag["form:perdiumTotal-msg"] = false;
		}else if(document.getElementById("form:perdiumPerDay").value == ""){
			showError("form:perdiumTotal-msg", "Please Enter per Day Charges");
			flag["form:perdiumTotal-msg"] = false;
		}
	} 
	if(applicableCurrency == ""){
		showError("form:applicable-msg", "Please Enter Applicable Unit");
		flag["form:perdiumTotal-msg"] = false;
	}else if(conversionRate == ""){
		showError("form:ConversionRate-msg", "Please Enter Conversion Rate");
		flag["form:perdiumTotal-msg"] = false;
	}else{		
		document.getElementById("form:perdiumPerDay").value = Number(applicableCurrency * conversionRate);
		sum["perdiumPerDay"] = Number(document.getElementById("form:perdiumTotal").value = document.getElementById("form:perdiemNumberOfDays").value * document.getElementById("form:perdiumPerDay").value);
		flag["form:perdiumTotal-msg"] = true;
		setTotal();
	}
}

setTotal = function(){
	total = 0;
	for(let i=0; i<Object.keys(sum).length; i++){
		total += Number(Object.values(sum)[i]);
	}	
	document.getElementById("form:total").value = total;
}

miscellaneous = function(){
	total = 0;
	
	if(document.getElementById(arguments[0]).value != ""){
		sum[arguments[0]] = Number(document.getElementById(arguments[0]).value);
	}else{
		sum[arguments[0]] = 0;
	}
	
	for(let i=0; i<Object.keys(sum).length; i++){
		total += Number(Object.values(sum)[i]);
	}	
	document.getElementById("form:total").value = total;
}

validateFormDate = function(){
	if(dummy(arguments[0], arguments[1], arguments[2])){
		if(validateDate(document.getElementById("form:conversionDate_input").value)){
			removeError("form:"+arguments[1]);
			flag["form:"+arguments[1]] = true;
		}else{
			flag["form:"+arguments[1]] = false;
			showError("form:"+arguments[1], "Invalid Date");
		}
	}
}

let prevMessage = "Please Enter Applicable ";
applicableUnit = function(){
		if(arguments[2] != prevMessage){
			validate(arguments[0], arguments[1], arguments[2]);
			prevMessage = arguments[2];
		}else{
			removeError("form:"+arguments[1]);
			flag["form:"+arguments[1]] = true;
		}
}

validateCard = function(){
	if(document.getElementById(arguments[0]).value == "" && arguments[2] == ""){
		removeError("form:"+arguments[1]);
		flag["form:"+arguments[1]] = true;
		return null;
	}
	if(validate(arguments[0], arguments[1], arguments[2])){
		if(/\D/.test(document.getElementById(arguments[0]).value)){
			flag["form:"+arguments[1]] = false;
			showError("form:"+arguments[1], "Only Numbers Are allowed");
		}else if(document.getElementById(arguments[0]).value.length != 16){
			flag["form:"+arguments[1]] = false;
			showError("form:"+arguments[1], "Maximum 16 Digit are Allowed");
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

forexAmount = function(){
	if(document.getElementById("form:cardNumber").value != ""){
		if(document.getElementById("form:cash").value != "" && document.getElementById("form:card").value != "")
		if(Number(document.getElementById("form:total").value) == Number(document.getElementById("form:cash").value) + Number(document.getElementById("form:card").value)){
			removeError("form:card-msg");
			flag["form:card"] = true;
		}else{
			showError("form:card-msg", "Amount does not Add up to Total");
			flag["form:card"] = false;
		}
	}else{
		if(document.getElementById("form:card").value == ""){
			if(Number(document.getElementById("form:total").value) == Number(document.getElementById("form:cash").value)){
				document.getElementById("form:cash-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
				document.getElementById("form:cash-msg").innerHTML = "";
				flag["form:card"] = true;
			}else{
				showError("form:cash-msg", "Since Card is not Available, Cash Amount is not equals Total Amount");
				flag["form:card"] = false;
			}
		}else{
			showError("form:card-msg", "Since Card is not Available, Use Cash");
			flag["form:card"] = false;
		}
	}
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
	console.log(validateMailDailog);
	return validateMailDailog;
}


validateDate = function(date){
    var dateLength = [29,31,28,31,30,31,30,31,31,30,31,30,31];
    
    let splitString = date.split("/");
    let day = Number(splitString[0]);
    let month = Number(splitString[1]);
    let year = Number(splitString[2]);
    let isLeap = isleap(year);
    
    if(isLeap && month == 2){
        if(day > dateLength[0])
            return false;
        return true;
    }
    
    if(month > 12)
        return false;
    
    if(day > dateLength[month])
        return false;
    
    return true;
}

function isleap(){
    var yr=arguments[0];
    if ((parseInt(yr)%4) == 0){
        if (parseInt(yr)%100 == 0){
             if (parseInt(yr)%400 != 0)
                    return false;
            
             if (parseInt(yr)%400 == 0)
                    return true;                
        }
        if (parseInt(yr)%100 != 0)
            return true;
    }
    if ((parseInt(yr)%4) != 0)
        return false;        
}