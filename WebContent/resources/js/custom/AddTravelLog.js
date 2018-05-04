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
           if(!( /^[A-Za-z0-9() ]+$/.test(document.getElementById(arguments[0]).value))){
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

validateProjectDate = function(){
      if(dummy(arguments[0], arguments[1], arguments[2])){       
           var initiationDate = (document.getElementById("form:initiationDate_input").value);
           if(!validateDate(initiationDate)){
                 flag["form:"+arguments[1]] = false;      
                 showError("form:initiationDate-msg", "Invalid Date");
                 return false;
           }
           initiationDate = initiationDate.split("/").reverse().join("-");    
           
           var confirmationDate = (document.getElementById("form:confirmationDate_input").value);
           if(!validateDate(confirmationDate)){
                 flag["form:"+arguments[1]] = false;      
                 showError("form:confirmationDate-msg", "Invalid Date");
                 return false;
           }
           confirmationDate = confirmationDate.split("/").reverse().join("-");
           
           if(new Date(confirmationDate).getTime() - new Date(initiationDate).getTime() <= 0){
                 flag["form:"+arguments[1]] = false;      
                 showError("form:confirmationDate-msg", "Confirmation Date should be ahead of Initiation Date");
           }else{
                 removeError("form:confirmationDate-msg");
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
