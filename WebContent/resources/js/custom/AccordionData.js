validateOnSubmit = true;



getIdEaLink = function() {
      var numbers = arguments[0].substring(11, arguments[0].length - 8);
      if (numbers == "")
           numbers = arguments[0].substring(11, arguments[0].length - 8);

      if (document.getElementById("form:panel:" + numbers + ":EA-link").innerHTML == "to be prepared"){
           document.getElementById("form:EA").value = document
                      .getElementById("form:panel:" + numbers + ":id").value;
           validateOnSubmit = true;
      }
      else{
           document.getElementById("form:EA").value = "Invalid";
           validateOnSubmit = false;
      }
}

getIdPlLink = function() {
      var numbers = arguments[0].substring(11, arguments[0].length - 8);
      if (numbers == "")
           numbers = arguments[0].substring(11, arguments[0].length - 8);

      if (document.getElementById("form:panel:" + numbers + ":PL-link").innerHTML == "to be prepared"){
           document.getElementById("form:EA").value = document
                      .getElementById("form:panel:" + numbers + ":id").value;
           validateOnSubmit = true;
      }
      else{
           document.getElementById("form:EA").value = "Invalid";
           validateOnSubmit = false;
      }
}

getIdLaLink = function() {
      var numbers = arguments[0].substring(11, arguments[0].length - 8);
      if (numbers == "")
           numbers = arguments[0].substring(11, arguments[0].length - 8);

      if (document.getElementById("form:panel:" + numbers + ":LA-link").innerHTML == "to be prepared"){
           document.getElementById("form:EA").value = document
                      .getElementById("form:panel:" + numbers + ":id").value;
           validateOnSubmit = true;
      }
      else{
           document.getElementById("form:EA").value = "Invalid";
           validateOnSubmit = false;
      }
}

getIdPsLink = function() {
      var numbers = arguments[0].substring(11, arguments[0].length - 8);
      if (numbers == "")
           numbers = arguments[0].substring(11, arguments[0].length - 8);

      if (document.getElementById("form:panel:" + numbers + ":PS-link").innerHTML == "to be prepared"){
           document.getElementById("form:EA").value = document
                      .getElementById("form:panel:" + numbers + ":id").value;
           validateOnSubmit = true;
      }
      else{
           document.getElementById("form:EA").value = "Invalid";
           validateOnSubmit = false;
      }
}

emailFieldValidation = function() {
      let toEmail = document.getElementById(arguments[0]).value;

      if (toEmail == "" && arguments[0].includes("to")) {
           document.getElementById(arguments[0]+"-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
           document.getElementById(arguments[0]+"-msg").innerHTML = "Please Enter Email Address";
           validateOnSubmit = false;
      }else if(toEmail == ""){ 
           document.getElementById(arguments[0]+"-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
           document.getElementById(arguments[0]+"-msg").innerHTML = "";
           validateOnSubmit = true;
      }else {
           document.getElementById(arguments[0]+"-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
           document.getElementById(arguments[0]+"-msg").innerHTML = "";
           
           let toEmailArray = toEmail.replace(' ', '').split(",");
           let counter = 0;
           
           for(let i=0; i<toEmailArray.length; i++){
                 if(emailValidation(toEmailArray[i]))
                      counter++;
           }
           
           if(counter == toEmailArray.length){
                 document.getElementById(arguments[0]+"-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
                 document.getElementById(arguments[0]+"-msg").innerHTML = "";
                 validateOnSubmit = true;
           }else{
                 document.getElementById(arguments[0]+"-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
                 document.getElementById(arguments[0]+"-msg").innerHTML = "Please Check Entered Emails";
                 validateOnSubmit = false;
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
           validateOnSubmit = true;
      } else {
           document.getElementById(arguments[0]+"-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
           document.getElementById(arguments[0]+"-msg").innerHTML = "";
           validateOnSubmit = false;
      }
}

validateOnClick = function(){
      return validateOnSubmit;
}

getIdForAgengyMail = function(){
      var numbers = arguments[0].substring(11, arguments[0].length - 11);
      document.getElementById("form:EA").value = document.getElementById("form:panel:" + numbers + ":id").value;
}

var sendMailFlag = true;
var flag = false;
validateAgencyEmail = function(){
      if(document.getElementById("form:select-agency").value=="" && flag){
            document.getElementById("form:sendMailMsg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
            document.getElementById("form:sendMailMsg").innerHTML = "Please Select Agency";
           sendMailFlag = false;
      }else{
            document.getElementById("form:sendMailMsg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
            document.getElementById("form:sendMailMsg").innerHTML = "";
           sendMailFlag = true;
      }
      flag = true;
}

getMailFlag = function(){
      return sendMailFlag;
}

