var map = {}
onload = function() {
	map["form:country"] = false;
	map["form:issuedate_input"] = false;
	map["form:expirydate_input"] = false;
}
globalScope = function(id, msgId, type) {

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
	required = function(i) {
		if (document.getElementById(i).value == "") {
			map[id] = false;
			if (type == "dropDown") {
				showError(msgId, "Please Select An Option");
			} else {
				showError(msgId, "Can Not Be Blank");
			}
			return false;
		} else {
			map[id] = true;
			removeError(msgId);
			return true;
		}
	}

	validateIssueDate = function(id, idMsg, message) {
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
		var BirthDate = document.getElementById(id).value;
		if (BirthDate) {
			BirthDate = BirthDate.split("/").reverse().join("-");
			if (new Date(BirthDate).getTime() - new Date().getTime() <= 0) {
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
				map[id1] = false;
				map[id2] = false;
			} else {
				removeError(msgId);
				map[id1] = true;
				map[id2] = true;
			}
		}
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

	/*validateDateFormat = function(id, msgId) {
		var date = document.getElementById(id).value;
		console.log(date);
		if (date) {
			regex = /^(\d{1,2})\/(\d{1,2})\/(\d{4})$/;
			var dates = date.split("/");
			if ((date.match(regex) != null)) {
				if ((dates[0] > 0 && dates[0] < 32)
						&& (dates[1] > 0 && dates[1] < 13)) {
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
	}
*/


	if (type == "dropDown") {
		required(id);
	}

	if (type != "globalCheck" && type != "dropDown") {
		if (true) {
			if (validateDateFormat(id, msgId)) {
				switch (type) {
				case "Idate":
					if (validateIssueDate("form:issuedate_input", msgId,
							"Issued Date Should Be Preceded By Current Date")) {
						dateValidation("form:issuedate_input",
								"form:expirydate_input", "form:issuedate-msg",
								"form:expirydate-msg",
								"Issued Date Should Be Preceded By Expiry Date");
					}
					break;

				case "Edate":
					if (validateExpiryDate("form:expirydate_input", msgId,
							"Expiry Date Should Be Ahead Of Current Date")) {
						dateValidation("form:issuedate_input",
								"form:expirydate_input", "form:issuedate-msg",
								"form:expirydate-msg",
								"Issued Date Should Be Preceded By Expiry Date");
					}
					break;
				default:
					;
				}
			}
		}
	}
	if (type == "globalCheck") {
		var mapCheck = false;
		for ( var propName in map) {
			if (map.hasOwnProperty(propName)) {
				if (!map[propName]) {
					mapCheck = false;
					break;
				} else {
					mapCheck = true;
				}
			}
		}
		if (mapCheck) {
			removeError(msgId);
			return true;
		} else {
			showError(msgId, "Please Check All The Details");
			window.scrollTo(0, 0);
			return false;
		}
	}
}
