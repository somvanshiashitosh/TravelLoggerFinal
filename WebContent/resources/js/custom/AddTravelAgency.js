flag = {};



validateEmail = function(){
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

getFlag = function(){
	let y = 0;
    for(let x in flag){
         if(flag[x] == true)
               y++;
    }
    if(Object.keys(flag).length == y)
         return true;
    else
         return false;
}
