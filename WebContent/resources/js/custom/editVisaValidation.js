var con = [];
var idate = [];
var edate = [];

onload = function() {
	console.log("EditVisa");
	var table = PF('count').tbody[0].children.length;
	for (var i = 0; i < table; i++) {
		con[i] = true;
		idate[i] = true;
		edate[i] = true;
	}

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

country = function(i) {
	if (document.getElementById("form:visaTable:" + i + ":country-ip").value == "") {
		showError("form:visaTable:" + i + ":country-ip-msg",
				"Please Select Country");
		con[i] = false;
	} else {
		removeError("form:visaTable:" + i + ":country-ip-msg");
		con[i] = true;
	}
}

validateDateFormat = function(date, msgId) {
	var dateLength = [ 29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

	let splitString = date.split("/");
	let day = Number(splitString[0]);
	let month = Number(splitString[1]);
	let year = Number(splitString[2]);
	let isLeap = isleap(year);

	if (isLeap && month == 2) {
		if (day > dateLength[0]) {
			showError(msgId, "Invalid Date OR Format");
			return false;
		}
		removeError(msgId);
		return true;
	}

	if (month > 12) {
		showError(msgId, "Invalid Date OR Format");
		return false;
	}
	if (day > dateLength[month]) {
		showError(msgId, "Invalid Date OR Format");
		return false;
	}
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
validateDateFormat = function(date, msgId) {
	console.log(date);
	regex = /^(\d{1,2})\/(\d{1,2})\/(\d{4})$/;
	var dates = date.split("/");
	if ((date.match(regex) != null)) {
		if ((dates[0] > 0 && dates[0] < 32) && (dates[1] > 0 && dates[1] < 13)) {
			console.log("inner if");
			removeError(msgId);
			return true;
		} else {
			showError(msgId, "Invalid Date");
			return false;
		}
	} else {
		showError(msgId, "Invalid Date Format");
		return false;
	}
}
*/
IssuedDate = function(i) {
	if (document.getElementById("form:visaTable:" + i + ":issuedate-ip_input").value == "") {
		showError("form:visaTable:" + i + ":issuedate-ip-msg",
				"Please Select Issued Date");
		idate[i] = false;
	} else {
		removeError("form:visaTable:" + i + ":issuedate-ip-msg");
		idate[i] = true;
	}

	var confirmationDate = (document.getElementById("form:visaTable:" + i
			+ ":expirydate-ip_input").value);
	var initiationDate = (document.getElementById("form:visaTable:" + i
			+ ":issuedate-ip_input").value);
	if (initiationDate) {
		if (validateDateFormat(initiationDate, "form:visaTable:" + i
				+ ":issuedate-ip-msg")) {
			initiationDate = initiationDate.split("/").reverse().join("-");
			if (new Date().getTime() - new Date(initiationDate).getTime() <= 0) {
				showError("form:visaTable:" + i + ":issuedate-ip-msg",
						"Issue Date Should Be Preceded By Current Date");
				idate[i] = false;
			} else {
				if (confirmationDate && initiationDate) {

					initiationDate = initiationDate.split("/").reverse().join(
							"-");

					confirmationDate = confirmationDate.split("/").reverse()
							.join("-");

					if (new Date(confirmationDate).getTime()
							- new Date(initiationDate).getTime() <= 0) {
						showError("form:visaTable:" + i + ":issuedate-ip-msg",
								"Issue Date Should Be Preceded By Expiry Date");
						idate[i] = false;
					} else {
						removeError("form:visaTable:" + i + ":issuedate-ip-msg");
						removeError("form:visaTable:" + i
								+ ":expirydate-ip-msg");
						idate[i] = true;
					}
				}
			}
		}
	}

}

ExpiryDate = function(i) {
	if (document.getElementById("form:visaTable:" + i + ":expirydate-ip_input").value == "") {
		showError("form:visaTable:" + i + ":expirydate-ip-msg",
				"Please Select Expiry Date");
		edate[i] = false;
	} else {
		removeError("form:visaTable:" + i + ":expirydate-ip-msg");
		edate[i] = true;
	}

	var initiationDate = (document.getElementById("form:visaTable:" + i
			+ ":issuedate-ip_input").value);
	var confirmationDate = (document.getElementById("form:visaTable:" + i
			+ ":expirydate-ip_input").value);
	if (confirmationDate) {
		if (validateDateFormat(confirmationDate, "form:visaTable:" + i
				+ ":expirydate-ip-msg")) {
			confirmationDate = confirmationDate.split("/").reverse().join("-");
			if (new Date(confirmationDate).getTime() - new Date().getTime() <= 0) {
				showError("form:visaTable:" + i + ":expirydate-ip-msg",
						"Expriy Date Should Be Ahead Of Current Date");
				edate[i] = false;
			} else {
				removeError("form:visaTable:" + i + ":expirydate-ip-msg");
				if (initiationDate && confirmationDate) {
					initiationDate = initiationDate.split("/").reverse().join(
							"-");

					confirmationDate = confirmationDate.split("/").reverse()
							.join("-");

					if (new Date(confirmationDate).getTime()
							- new Date(initiationDate).getTime() <= 0) {
						showError("form:visaTable:" + i + ":expirydate-ip-msg",
								"Expiry Date should be ahead of Issued Date");
						edate[i] = false;
					} else {
						removeError("form:visaTable:" + i
								+ ":expirydate-ip-msg");
						removeError("form:visaTable:" + i + ":issuedate-ip-msg");
						edate[i] = true;
					}
				}
			}
		}
	}

}

globalCheck = function(i) {
	if (con[i] == true && idate[i] == true && edate[i] == true) {
		removeError("form:msgs");
		return true;
		// callRemoteMethod();
	} else {
		console.log(con[i] + " " + idate[i] + " " + edate[i]);
		showError("form:msgs", "Please Check All The Details");
		console.log("in else");
		return false;
	}
}

globalDelCheck = function(i) {
	return true;
}
