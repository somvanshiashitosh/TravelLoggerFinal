flag = {};

customerName = function(){
	if(document.getElementById("form:customerInput").value==""){
		document.getElementById("form:customer-name-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:customer-name-msg").innerHTML = "Please Enter Customer Name";
		flag["form:customerInput"] = false;
	}else if(document.getElementById("form:customerInput").value.match(/^[A-Za-z]+$/)==null){
		document.getElementById("form:customer-name-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:customer-name-msg").innerHTML = "numbers and special character Not allowed";
		flag["form:customerInput"] = false;
	}else{
		document.getElementById("form:customer-name-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:customer-name-msg").innerHTML = "";
		flag["form:customerInput"] = true;
	}
}

projectName = function(){
	if(document.getElementById("form:projectName-input").value==""){
		document.getElementById("form:project-name-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-name-msg").innerHTML = "Select Project Name";
		flag["form:projectName-input"] = false;
	}else{
		document.getElementById("form:project-name-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-name-msg").innerHTML = "";
		flag["form:projectName-input"] = true;
	}
}

TravellerName = function() {
	if(document.getElementById("form:traveller-text-input_input").value==""){
		document.getElementById("form:traveller-name-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:traveller-name-msg").innerHTML = "Select Traveller Name";
		flag["form:traveller-text-input_input"] = false;
	}else{
		document.getElementById("form:traveller-name-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:traveller-name-msg").innerHTML = "";
		flag["form:traveller-text-input_input"] = true;
	}
}

travelDestination = function(){
	if(document.getElementById("form:travel-destination").value==""){
		document.getElementById("form:travel-destination-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:travel-destination-msg").innerHTML = "Select Travel Destination";
		flag["form:travel-destination"] = false;
	}else{
		document.getElementById("form:travel-destination-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:travel-destination-msg").innerHTML = "";
		flag["form:travel-destination"] = true;
	}
}

engagementApproval = function(){
	if(document.getElementById("form:engagement-agreement").value==""){
		document.getElementById("form:engagement-agreement-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:engagement-agreement-msg").innerHTML = "Select Engageent Approval Status";
		flag["form:engagement-agreement"] = false;
	}else{
		document.getElementById("form:engagement-agreement-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:engagement-agreement-msg").innerHTML = "";
		flag["form:engagement-agreement"] = true;
	}
}

projectApproval = function(){
	if(document.getElementById("form:project-level").value==""){
		document.getElementById("form:project-level-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-level-msg").innerHTML = "Select Project Level Approval Status";
		flag["form:project-level"] = false;
	}else{
		document.getElementById("form:project-level-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-level-msg").innerHTML = "";
		flag["form:project-level"] = true;
	}
}

locationApproval = function(){
	if(document.getElementById("form:location-head").value==""){
		document.getElementById("form:location-head-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:location-head-msg").innerHTML = "Select Location head Approval Status";
		flag["form:location-head"] = false;
	}else{
		document.getElementById("form:location-head-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:location-head-msg").innerHTML = "";
		flag["form:location-head"] = true;
	}
}

psApproval = function(){
	if(document.getElementById("form:ps-leader").value==""){
		document.getElementById("form:ps-leader-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:ps-leader-msg").innerHTML = "Select Ps Head Approval Status";
		flag["form:ps-leader"] = false;
	}else{
		document.getElementById("form:ps-leader-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:ps-leader-msg").innerHTML = "";
		flag["form:ps-leader"] = true;
	}
}

costCoveredBy = function(){
	if(document.getElementById("form:cost-coveredBy").value==""){
		document.getElementById("form:cost-coveredBy-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:cost-coveredBy-msg").innerHTML = "Select Cost Covered By";
		flag["form:cost-coveredBy"] = false;
	}else{
		document.getElementById("form:cost-coveredBy-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:cost-coveredBy-msg").innerHTML = "";
		flag["form:cost-coveredBy"] = true;
	}
}

workScope = function(){
	if(document.getElementById("form:scope-of-work").value==""){
		document.getElementById("form:scope-of-work-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:scope-of-work-msg").innerHTML = "Please Enter Scope of Work";
		flag["form:scope-of-work"] = false;
	}else{
		document.getElementById("form:scope-of-work-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:scope-of-work-msg").innerHTML = "";
		flag["form:scope-of-work"] = true;
	}
}

projectInitiationDate = function(){
	if(document.getElementById("form:project-initiation-end-date_input").value==""){
		document.getElementById("form:project-initiation-end-date-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-initiation-end-date-msg").innerHTML = "Please Project Initiation Date";
		flag["form:project-initiation-end-date_input"] = false;
	}else{
		document.getElementById("form:project-initiation-end-date-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-initiation-end-date-msg").innerHTML = "";
		flag["form:project-initiation-end-date_input"] = true;
	}
	
	var initiationDate = (document.getElementById("form:project-initiation-end-date_input").value);
	initiationDate = initiationDate.split("/").reverse().join("-");	
	var confirmationDate = (document.getElementById("form:project-confirmation-end-date_input").value);
	confirmationDate = confirmationDate.split("/").reverse().join("-");
	
	if(new Date(confirmationDate).getTime() - new Date(initiationDate).getTime() <= 0){
		document.getElementById("form:project-confirmation-end-date-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-confirmation-end-date-msg").innerHTML = "Confirmation Date should be ahead of Initiation Date";
		flag["form:project-initiation-end-date_input"] = false;
	}else{
		document.getElementById("form:project-confirmation-end-date-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-confirmation-end-date-msg").innerHTML = "";
		flag["form:project-initiation-end-date_input"] = true;
	}
}

projectDate = function(){
	
	if(document.getElementById("form:project-confirmation-end-date_input").value==""){
		document.getElementById("form:project-confirmation-end-date-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-confirmation-end-date-msg").innerHTML = "Please Project Confirmation Date";
		flag["form:project-confirmation-end-date_input"] = false;
	}else{
		document.getElementById("form:project-confirmation-end-date-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-confirmation-end-date-msg").innerHTML = "";
		flag["form:project-confirmation-end-date_input"] = true;
	}
	
	var initiationDate = (document.getElementById("form:project-initiation-end-date_input").value);
	initiationDate = initiationDate.split("/").reverse().join("-");	
	var confirmationDate = (document.getElementById("form:project-confirmation-end-date_input").value);
	confirmationDate = confirmationDate.split("/").reverse().join("-");
	
	if(new Date(confirmationDate).getTime() - new Date(initiationDate).getTime() <= 0){
		document.getElementById("form:project-confirmation-end-date-msg").classList.add("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-confirmation-end-date-msg").innerHTML = "Confirmation Date should be ahead of Initiation Date";
		flag["form:project-confirmation-end-date_input"] = false;
	}else{
		document.getElementById("form:project-confirmation-end-date-msg").classList.remove("ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:project-confirmation-end-date-msg").innerHTML = "";
		flag["form:project-confirmation-end-date_input"] = true;
	}
	
}

getFlag = function(){
	let y = 0;
	for(let x in flag)
		if(flag[x]==true)
			y++;
	if(y==11)
		return true;
	else
		return false;
}