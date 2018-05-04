onload = function() {
	console.log("edit profile");
}
var check = true;

var map = {}

function globalScope(id, msgId, type, mandatory) {
	/* All Required Functions */
	required = function(i) {
		if (mandatory) {
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
		} else {
			removeError(msgId);
			return true;
		}
	}

	alphaNumeric = function(i) {
		if (document.getElementById(i).value != "") {
			if (document.getElementById(i).value.match(/^[A-Za-z0-9]*$/) == null) {
				map[id] = false;
				showError(msgId, "Only Letters And Numbers Are Allowed");
				return false;
			} else {
				map[id] = true;
				removeError(msgId);
				return true;
			}
		} else {
			return true;
		}
	}

	string = function(i) {
		if (document.getElementById(i).value != "") {
			if (document.getElementById(i).value.match(/^[a-zA-Z][a-zA-Z\s]+$/) == null) {
				map[id] = false;
				showError(msgId, "Only Letters Are Allowed");
				return false;
			} else {
				map[id] = true;
				removeError(msgId);
				return true;
			}
		} else {
			return true;
		}
	}

	stringWithHyphen = function(i) {
		if (document.getElementById(i).value != "") {
			if (document.getElementById(i).value.match(/^[a-zA-Z][a-zA-Z-]+$/) == null) {
				map[id] = false;
				showError(msgId, "Only Letters And Hyphen Are Allowed");
				return false;
			} else {
				map[id] = true;
				removeError(msgId);
				return true;
			}
		} else {
			return true;
		}
	}

	email = function(i) {
		if (document.getElementById(i).value != "") {
			if (document.getElementById(i).value
					.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/) == null) {
				map[id] = false;
				showError(msgId, "Enter Correct EmailId");
				return false;
			} else {
				map[id] = true;
				removeError(msgId);
				return true;
			}
		} else {
			return true;
		}
	}

	contact = function(i) {
		if (document.getElementById(i).value != "") {
			var number = document.getElementById(i).value;
			if (number.match(/^\d{10}$/) == null) {
				map[id] = false;
				showError(msgId, "It Should Have 10 Digits");
				return false;
			} else {
				removeError(msgId);
				if (number[0] == "9" || number[0] == "8" || number[0] == "7") {
					map[id] = true;
				} else {
					map[id] = false;
					showError(msgId, "Enter Valid Mobile Number");
				}
				return true;
			}
		} else {
			return true;
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
				map[id] = false;
				return false;
			}
			removeError(msgId);
			map[id] = true;
			return true;
		}

		if (month > 12) {
			showError(msgId, "Invalid Date OR Format");
			map[id] = false;
			return false;
		}
		if (day > dateLength[month]) {
			showError(msgId, "Invalid Date OR Format");
			map[id] = false;
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
	 * document.getElementById(id).value; console.log(date); regex =
	 * /^(\d{1,2})\/(\d{1,2})\/(\d{4})$/; var dates = date.split("/"); if
	 * ((date.match(regex) != null) && (dates[0] > 0 && dates[0] < 32) &&
	 * (dates[1] > 0 && dates[1] < 13)) { console.log("inner if");
	 * removeError(msgId); return true; } else { showError(msgId, "Invalid
	 * Date"); return false; } }
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
				map[id1] = false;
				map[id2] = false;
			} else {
				removeError(idmsg1);
				removeError(idmsg2);
				map[id1] = true;
				map[id2] = true;
			}
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

	if (type != "date" || type != "globalCheck") {
		if (required(id)) {
			switch (type) {
			case "name":
				string(id);
				break;
			case "alphanumeric":
				alphaNumeric(id);
				break;
			case "contact":
				contact(id);
				break;
			case "email":
				email(id);
				break;
			case "relation":
				stringWithHyphen(id);
			default:
				;
			}
		}
	}

	if (type == "Idate" || type == "Bdate" || type == "Edate") {
		if (required(id)) {
			if (validateDateFormat(id, msgId)) {
				if (type == "Idate") {
					if (validateBirthDate("form:passissue_input",
							"form:passissue-msg",
							"Issue Date Should Be Preceded By Current Date")) {
						dateValidation("form:passissue_input",
								"form:passexpiry_input", "form:passissue-msg",
								"form:passexpiry-msg",
								"Issued Date Should Be Preceded By Expiry Date");
					}
				}
				if (type = "Bdate") {
					if (validateBirthDate("form:dobinput_input",
							"form:dobinput-msg",
							"Please Select Proper Birth Date")) {
						dateValidation("form:dobinput_input",
								"form:dojinput_input", "form:dobinput-msg",
								"form:dojinput-msg",
								"Birth Date Should Be Preceded By Joining Date");
					}
				}
				if (type = "Edate") {
					if (validateExpiryDate("form:passexpiry_input",
							"form:passexpiry-msg",
							"Expiry Date Should Be Ahead Of Current Date")) {
						dateValidation("form:passissue_input",
								"form:passexpiry_input", "form:passissue-msg",
								"form:passexpiry-msg",
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

	if (type == "globalCheck") {
		var mapCheck = true;
		for ( var propName in map) {
			if (map.hasOwnProperty(propName)) {
				if (!map[propName]) {
					mapCheck = false;
					break;
				}
			}
		}
		console.log(mapCheck);
		if (mapCheck) {
			return true;
		} else {
			document.getElementById(msgId).classList.add("ui-message-error",
					"ui-widget", "ui-corner-all");
			document.getElementById(msgId).innerHTML = "Please Check All The Details";
			window.scrollTo(0, 0);
			return false;
		}
	}
}
