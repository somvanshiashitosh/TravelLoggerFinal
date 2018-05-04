flag = {};
flag["form:location-head-cc-msg"] == true;
flag["form:project-head-cc-msg"] == true;
flag["form:ps-head-email-cc-msg"] == true;


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

validateAlphanumeric = function(){
      if(validate(arguments[0], arguments[1], arguments[2])){
           if(!(/([a-zA-Z0-9-]+)$/.test(document.getElementById(arguments[0]).value))){
                 showError("form:"+arguments[1], "Only Alphanumeric Are Allowed");
                 flag["form:"+arguments[1]] = false;
           }else{
                 removeError("form:"+arguments[1]);
                 flag["form:"+arguments[1]] = true;
           }
      }
}

validateNumber = function(){
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

validateString = function(){
      if(validate(arguments[0], arguments[1], arguments[2])){
           if(!( /^[A-Za-z ]+$/.test(document.getElementById(arguments[0]).value))){
                 flag["form:"+arguments[1]] = false;
                 showError("form:"+arguments[1], "Only Letters Are allowed");
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

validateCC = function(){
      if(document.getElementById(arguments[0]).value==""){
           flag["form:"+arguments[1]] = true;
      }else{
           let Email = document.getElementById(arguments[0]).value.replace(/ /g,'').split(",");
           for(let i=0; i<Email.length; i++){
                 if(Email[i].match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/) == null){
                      showError("form:"+arguments[1], arguments[2]);
                      flag["form:"+arguments[1]] = false;
                 }else{
                       removeError("form:"+arguments[1]);
                      flag["form:"+arguments[1]] = true;
                 }
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

function getFlag(){
      console.log(flag);
      let y = 0;
      for(let x in flag){
           if(flag[x] == true)
                 y++;
      }
      if(y == 18)
           return true;
      else
           return false;
}
