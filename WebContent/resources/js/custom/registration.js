onload = function() {
	console.log("Registration");
}
/*
 * validateDateFormat = function(id, msgId) { console.log(id); var date =
 * document.getElementById(id).value; console.log(date); if (date) { regex =
 * /^(\d{1,2})\/(\d{1,2})\/(\d{4})$/; var dates = date.split("/"); if
 * ((date.match(regex) != null)) { if ((dates[0] > 0 && dates[0] < 32) &&
 * (dates[1] > 0 && dates[1] < 13)) { console.log("inner if");
 * removeError(msgId); return true; } else { showError(msgId, "Invalid Date");
 * return false; } } else { showError(msgId, "Invalid Date Format"); return
 * false; } } }
 */

var map = {};
validateDateFormat = function(id, msgId) {
	var date = document.getElementById(id).value;
	var dateLength = [ 29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

	let splitString = date.split("/");
	let day = Number(splitString[0]);
	let month = Number(splitString[1]);
	let year = Number(splitString[2]);
	let isLeap = isleap(year);

	if (isLeap && month == 2) {
		if (day > dateLength[0]) {
			showError(msgId, "Invalid Date OR Format");
			map[id] = false;
			return false;
		}
		map[id] = true;
		removeError(msgId);
		return true;
	}

	if (month > 12) {
		map[id] = false;
		showError(msgId, "Invalid Date OR Format");
		return false;
	}
	if (day > dateLength[month]) {
		map[id] = false;
		showError(msgId, "Invalid Date OR Format");
		return false;
	}
	map[id] = true;
	removeError(msgId);
	return true;
}

function isleap() {
	var yr = arguments[0];
	if ((parseInt(yr) % 4) == 0) {
		if (parseInt(yr) % 100 == 0) {
			if (parseInt(yr) % 400 != 0)
				return false;

			if (parseInt(yr) % 400 == 0)
				return true;
		}
		if (parseInt(yr) % 100 != 0)
			return true;
	}
	if ((parseInt(yr) % 4) != 0)
		return false;
}
function showError(messageId, message) {
	document.getElementById(messageId).classList.add("ui-message-error",
			"ui-widget", "ui-corner-all");
	document.getElementById(messageId).innerHTML = message;
}

function removeError(messageId) {
	document.getElementById(messageId).classList.remove("ui-message-error",
			"ui-widget", "ui-corner-all");
	document.getElementById(messageId).innerHTML = "";
}

function visaCheck() {
	if (document.getElementById("form:panel:inputid").value == ""
			|| document.getElementById("form:panel:country").value == ""
			|| document.getElementById("form:panel:visaissue_input").value == ""
			|| document.getElementById("form:panel:visaexpiry_input").value == "") {
		document.getElementById("form:growl").classList.add("ui-message-error",
				"ui-widget", "ui-corner-all");
		document.getElementById("form:growl").innerHTML = "OracleId, Country, Issue And Expiry Dates Are Compulsory";
		console.log("done");
		window.scrollTo(0, 0);
		return false;
	} else {
		document.getElementById("form:growl").classList.remove(
				"ui-message-error", "ui-widget", "ui-corner-all");
		document.getElementById("form:growl").innerHTML = "";
		console.log(map);
		if ((map["form:panel:visaissue_input"] == false)
				|| (map["form:panel:visaexpiry_input"] == false)) {
			showError("form:growl", "Please Check Entered Dates");
			window.scrollTo(0, 0);
			return false;
		} else {
			return true;
		}
	}
}

function nomineeCheck() {
	if (document.getElementById("form:nom1").value == ""
			& document.getElementById("form:telnom1").value == ""
			& document.getElementById("form:relation").value == "") {

		// console.log("done");
		return false;
	} else {

		return true;
	}
}

function globalScope(id, msgId, type) {
	function showError(messageId, message) {
		document.getElementById(messageId).classList.add("ui-message-error",
				"ui-widget", "ui-corner-all");
		document.getElementById(messageId).innerHTML = message;
	}

	function removeError(messageId) {
		document.getElementById(messageId).classList.remove("ui-message-error",
				"ui-widget", "ui-corner-all");
		document.getElementById(messageId).innerHTML = "";
	}

	validateDateFormat = function(id, msgId) {
		var date = document.getElementById(id).value;
		var dateLength = [ 29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

		let splitString = date.split("/");
		let day = Number(splitString[0]);
		let month = Number(splitString[1]);
		let year = Number(splitString[2]);
		let isLeap = isleap(year);

		if (isLeap && month == 2) {
			if (day > dateLength[0]) {
				map[id] = false;
				showError(msgId, "Invalid Date OR Format");
				return false;
			}
			map[id] = true;
			removeError(msgId);
			return true;
		}

		if (month > 12) {
			map[id] = false;
			showError(msgId, "Invalid Date OR Format");
			return false;
		}
		if (day > dateLength[month]) {
			map[id] = false;
			showError(msgId, "Invalid Date OR Format");
			return false;
		}
		map[id] = true;
		removeError(msgId);
		return true;
	}

	function isleap() {
		var yr = arguments[0];
		if ((parseInt(yr) % 4) == 0) {
			if (parseInt(yr) % 100 == 0) {
				if (parseInt(yr) % 400 != 0)
					return false;

				if (parseInt(yr) % 400 == 0)
					return true;
			}
			if (parseInt(yr) % 100 != 0)
				return true;
		}
		if ((parseInt(yr) % 4) != 0)
			return false;
	}
	/*
	 * validateDateFormat = function(id, msgId) { console.log(id); var date =
	 * document.getElementById(id).value; console.log(date); if (date) { regex =
	 * /^(\d{1,2})\/(\d{1,2})\/(\d{4})$/; var dates = date.split("/"); if
	 * ((date.match(regex) != null)) { if ((dates[0] > 0 && dates[0] < 32) &&
	 * (dates[1] > 0 && dates[1] < 13)) { console.log("inner if");
	 * removeError(msgId); map[id] = true; return true; } else {
	 * showError(msgId, "Invalid Date"); map[id] = false; return false; } } else {
	 * showError(msgId, "Invalid Date Format"); map[id] = false; return false; } } }
	 */
	validateBirthDate = function(id, idMsg, message) {

		var BirthDate = document.getElementById(id).value;
		if (BirthDate) {
			BirthDate = BirthDate.split("/").reverse().join("-");
			if (new Date().getTime() - new Date(BirthDate).getTime() <= 0) {

				showError(idMsg, message);
				map[id] = false;
				return false;
			} else {

				removeError(idMsg);
				map[id] = true;
				return true;
			}
		}
	}

	validateExpiryDate = function(id, idMsg, message) {
		var ExpiryDate = document.getElementById(id).value;
		if (ExpiryDate) {
			ExpiryDate = ExpiryDate.split("/").reverse().join("-");
			if (new Date(ExpiryDate).getTime() - new Date().getTime() <= 0) {
				showError(idMsg, message);
				map[id] = false;
				return false;
			} else {
				removeError(idMsg);
				map[id] = true;
				return true;
			}
		}
	}

	dateValidation = function(id1, id2, idmsg1, idmsg2, message) {
		var issuedDate = document.getElementById(id1).value;
		var expiryDate = document.getElementById(id2).value;
		if (issuedDate && expiryDate) {

			issuedDate = issuedDate.split("/").reverse().join("-");

			expiryDate = expiryDate.split("/").reverse().join("-");

			if (new Date(expiryDate).getTime() - new Date(issuedDate).getTime() <= 0) {
				showError(msgId, message);
				// map[id1] = false;
				map[id] = false;
			} else {
				// removeError(idmsg1);
				removeError(msgId);
				// map[id1] = true;
				map[id] = true;
			}
		}
	}

	if (type == "Idate" || type == "Edate") {
		console.log(id);
		if (true) {
			if (validateDateFormat(id, msgId)) {
				if (type == "Idate") {
					if (validateBirthDate("form:panel:visaissue_input",
							"form:panel:visaissue-msg",
							"Issue Date Should Be Preceded By Current Date")) {
						dateValidation("form:panel:visaissue_input",
								"form:panel:visaexpiry_input",
								"form:panel:visaexpiry-msg",
								"form:panel:visaissue-msg",
								"Issued Date Should Be Preceded By Expiry Date");
					}
				} else if (type = "Edate") {
					console.log("Edate");
					if (validateExpiryDate("form:panel:visaexpiry_input",
							"form:panel:visaexpiry-msg",
							"Expiry Date Should Be Ahead Of Current Date")) {
						dateValidation("form:panel:visaissue_input",
								"form:panel:visaexpiry_input",
								"form:panel:visaissue-msg",
								"form:panel:visaexpiry-msg",
								"Issued Date Should Be Preceded By Expiry Date");
					}
				}
			}
		} else {
			document.getElementById(msgId).classList.add("ui-message-error",
					"ui-widget", "ui-corner-all");
			document.getElementById(msgId).innerHTML = "Please Select Date";
		}
	}

	if (type == "PIdate" || type == "PEdate") {
		console.log(id);
		if (true) {
			if (validateDateFormat(id, msgId)) {
				if (type == "PIdate") {
					if (validateBirthDate("form:panel:issuedate_input",
							"form:panel:issuedate-msg",
							"Issue Date Should Be Preceded By Current Date")) {
						dateValidation("form:panel:issuedate_input",
								"form:panel:expirydate_input",
								"form:panel:expirydate-msg",
								"form:panel:issuedate-msg",
								"Issued Date Should Be Preceded By Expiry Date");

					}
				} else if (type = "PEdate") {
					console.log("PEdate");
					if (validateExpiryDate("form:panel:expirydate_input",
							"form:panel:expirydate-msg",
							"Expiry Date Should Be Ahead Of Current Date")) {

						dateValidation("form:panel:issuedate_input",
								"form:panel:expirydate_input",
								"form:panel:issuedate-msg",
								"form:panel:expirydate-msg",
								"Issued Date Should Be Preceded By Expiry Date");

					}
				}
			}
		} else {
			document.getElementById(msgId).classList.add("ui-message-error",
					"ui-widget", "ui-corner-all");
			document.getElementById(msgId).innerHTML = "Please Select Date";
		}
	}

	if (type == "globalCheckValidation") {
		var mapCheck = true;
		for ( var propName in map) {
			if (map.hasOwnProperty(propName)) {
				console.log(map[propName]);
				if (!map[propName]) {
					mapCheck = false;
					break;
				}
			}
		}
		if (mapCheck) {
			console.log("in if");
			return true;
		} else {
			console.log("in else");
			document.getElementById(msgId).classList.add("ui-message-error",
					"ui-widget", "ui-corner-all");
			document.getElementById(msgId).innerHTML = "Please Check All The Details";
			window.scrollTo(0, 0);
			return false;
		}
	}
}