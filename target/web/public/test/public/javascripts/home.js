/**
 * empty contact list message
 */
var NO_CONTACTS_MSG = "No contacts to display.";
/**
 * regex to validate name
 */
var NAME_REGEX = /^[A-Za-z]{2,20}$/;
/**
 * regex to validate phone number
 */
var PHONE_REGEX = /^(\+)[0-9]{12,13}$/;
/**
 * regex to validate email
 */
var EMAIL_REGEX = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
/**
 * same contact exist message
 */
var SAME_CONTACT_EXIST_MSG = "Contact with same name exists.";
/**
 * contact added message
 */
var CONTACT_ADDED_MSG = "Contact added successfully.";
/**
 * contact deleted message
 */
var CONTACT_DELETED_MSG = "Contact deleted successfully.";
/**
 * conact edited message
 */
var CONTACT_EDITED_MSG = "Contact updated successfully.";
/**
 * csv imported message
 */
var CSV_IMPORTED_MSG = "CSV file successfully imported.";
/**
 * csv exported message
 */
var CSV_EXPORTED_MSG = "CSV file successfully exported.";
/**
 * file name
 */
var FILE_NAME = "contacts.csv";
/**
 * insert request failed message
 */
var INSERT_REQUEST_FAILED_MSG = "Insert request failed.";
/**
 * update request failed message
 */
var UPDATE_REQUEST_FAILED_MSG = "Update request failed.";
/**
 * delete request failed message
 */
var DELETE_REQUEST_FAILED_MSG = "Delete request failed.";
/**
 * import failed message
 */
var IMPORT_REQUEST_FAILED_MSG = "Import request failed.";
/**
 * export request failed message
 */
var EXPORT_REQUEST_FAILED_MSG = "Export request failed.";
/**
 * new line
 */
var NEW_LINE = "\n";
/**
 * comma
 */
var COMMA = ",";
/**
 * invalid first name message
 */
var INVALID_FIRST_NAME_MSG = "Invalid First Name";
/**
 * invalid last name message
 */
var INVALID_LAST_NAME_MSG = "Invalid Last Name";
/**
 * invalid mobile number message
 */
var INVALID_MOBILE_MSG = "Invalid Mobile Number";
/**
 * invalid telephone number message
 */
var INVALID_TELEPHONE_MSG = "Invalid Telephone Number";
/**
 * invalid email message
 */
var INVALID_EMAIL_MSG = "Invalid Email address";
/**
 * invalid email msg
 */
var INVALID_ADDRESS_MSG = "Please enter address";
/**
 * confirm delete message
 */
var DELETE_CONFIRMATION_MSG = "Are you sure you want to delete contact of ";
/**
 * confirm insert message
 */
var INSERT_CONFIRMATION_MSG = "Are you sure you want to insert new contact of ";
/**
 * confirm update message
 */
var UPDATE_CONFIRMATION_MSG = "Are you sure you want to update contact of ";

/**
 * 
 * displays list of names
 */
function loadNames() {
	var nameList = "";
	if(persons.length > 0) {
		for(var index = 0; index < persons.length; index++) {
			var name = persons[index].fname + " " + persons[index].lname;
			var id = persons[index].id;
			nameList += '<li> <a class="contact" id=' + id + ' onclick = "showDetails(&quot;' + id + '&quot;)\">' + name + '</a></li>';
		}
		toggleButtons(false);
		$("#contactList").html(nameList);
		showDetails(persons[0].id);
	} else {
		toggleButtons(true);
	}
}

/**
 * toggles  export, edit, delete buttons
 */
function toggleButtons(flag){
	$("#exportFile").prop("disabled",flag);
	$("#editContact").prop("disabled",flag);
	$("#deleteContact").prop("disabled",flag);
	if(flag){
		$(".infoTable").css("display","none");
		$("#name").html(NO_CONTACTS_MSG);
		$("#contactList").html("");
		
	} else {
		$(".values").css("display","block");
		$("#name").html("");
	}
}

/**
 * search in list
 */
function search() {
	var input = $("#search").val().toLowerCase();
	$("#contactList li").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(input) > -1)
	});
}

/**
 * display details
 *
 * @param id - id of contact to display
 */
function showDetails(id) {
	var contactFound = false;
	for(var index = 0; index < persons.length && !contactFound; index++) {
		if(id == persons[index].id) {
			$("#id").val(persons[index].id)
			$("#name").html("<i class='fa fa-user'></i> " + persons[index].fname + " " + persons[index].lname);
			$("#mobileValue").html(persons[index].mobile);
			$("#telephoneValue").html(persons[index].telephone);
			$("#emailValue").html(persons[index].email);
			$("#addressValue").html(persons[index].address);
			contactFound = true;
		}
	}
	var selectedId = "#" + id;
	$(".contact").removeClass("highlight");
	$(selectedId).addClass("highlight");
}

/**
 * display add contact popup
 */
function displayPopup(popupType){
	removeValidationMsg();
	if(popupType == 'add') {
		setPopupEmpty();
	} else {
		setPopupDetails();
	}
	$(".popup").css("display","flex");
	$("#undo").prop("disabled",true);
	$("#modify").prop("disabled",true);
}

/**
 * hides popup
 */
function closePopup() {
	$(".popup").css("display","none");
}

/**
 * undo changes
 */
function undoChanges() {
	removeValidationMsg();
	var id = $("#formId").val();
	if(id == 0) {
		setPopupEmpty();
	} else {
		setPopupDetails();
	}
}

/**
 * set textbox of popup empty
 */
function setPopupEmpty() {
	$("#fname").val('');
	$("#lname").val('');
	$("#mobile").val('');
	$("#telephone").val('');
	$("#email").val('');
	$("#address").val('');
	$("#formId").val('0');
}

/**
 * fetch details in textbox of popup
 */
function setPopupDetails() {
	var id = $("#id").val();
	var contactFound = false;
	for(var index = 0; index < persons.length && !contactFound; index++) {
		if(id == persons[index].id) {
			$("#fname").val(persons[index].fname);
			$("#lname").val(persons[index].lname);
			$("#mobile").val(persons[index].mobile);
			$("#telephone").val(persons[index].telephone);
			$("#email").val(persons[index].email);
			$("#address").val(persons[index].address);
			$("#formId").val(persons[index].id);
			contactFound = true;
		}
	}
}

/**
 * insert or update contact
 *
 * @returns {Boolean}
 */
function modifyContact() {
	removeValidationMsg();
	if(validateForm() && !sameContactExists() ) {
		if($("#formId").val() == 0) {
			insertContact();
		} else {
			updateContact();
		}
	}	
}

/**
 * remove all validation msg
 */
function removeValidationMsg() {
	$("#errfname").html("");
	$("#errlname").html("");
	$("#errmobile").html("");
	$("#errtelephone").html("");
	$("#erremail").html("");
	$("#erraddress").html("");
}

/**
 * validate form
 *
 * @returns {Boolean}
 */
function validateForm() {
	var fname = $("#fname").val();
	var lname = $("#lname").val();
	var mobile = $("#mobile").val();
	var telephone = $("#telephone").val();
	var email = $("#email").val();
	var address = $("#address").val();
	var valid = true;
	if(!NAME_REGEX.test(fname)) {
		valid = false;
		$("#errfname").html(INVALID_FIRST_NAME_MSG);
	} else if(!NAME_REGEX.test(lname)) {
		valid = false;
		$("#errlname").html(INVALID_LAST_NAME_MSG);
	} else if(!PHONE_REGEX.test(mobile)) {
		valid = false;
		$("#errmobile").html(INVALID_MOBILE_MSG);
	} else 	if(telephone != "" && !PHONE_REGEX.test(telephone)) {
		valid = false;
		$("#errtelephone").html(INVALID_TELEPHONE_MSG);
	} else 	if(email != "" && !EMAIL_REGEX.test(email)) {
		valid = false;
		$("#erremail").html(INVALID_EMAIL_MSG);
	} else if(address == "") {
		valid = false;
		$("#erraddress").html(INVALID_ADDRESS_MSG);
	}
	return valid;
}

/**
 * checks if person with same name exist
 *
 * @returns {Boolean}
 */
function sameContactExists() {
	var firstName = $("#fname").val();
	var lastName = $("#lname").val();
	var formId = $("#formId").val();
	var id = $("#id").val();
	var exists = false;
	for(var index = 0; index < persons.length && !exists; index++) {
		if(persons[index].fname == firstName && persons[index].lname == lastName && formId != id) {
			$("#errlname").html(SAME_CONTACT_EXIST_MSG);
			exists = true;
		}
	}
	return exists;
}

/**
 * insert new contact
 */
function insertContact() {
	var msg = INSERT_CONFIRMATION_MSG + $("#fname").val() + " " + $("#lname").val();
	if(confirm(msg)){
		var json = getJson()
		$.ajax({
			type : "POST",
			contentType:'application/json',
			data: json,
			url : context + "home/insert"
		}).done(function(response) {
			var id = response.id; 
			persons = response.people;
			loadNames();
			alert(CONTACT_ADDED_MSG);
			showDetails(id);
			closePopup();
		}).fail(function() {
			alert(INSERT_REQUEST_FAILED_MSG);
		});
	}
}

/**
 * update contact
 */
function updateContact() {
	var msg = UPDATE_CONFIRMATION_MSG + $("#name").text();
	if(confirm(msg)){
		var json = getJson()
		$.ajax({
			type : "POST",
			contentType:'application/json',
			data: json,
			url : context + "home/update"
		}).done(function(people) {
			persons = people;
			loadNames();
			showDetails($("#formId").val());
			alert(CONTACT_EDITED_MSG);
			closePopup();
		}).fail(function() {
			alert(UPDATE_REQUEST_FAILED_MSG);
		});
	}
}

/**
 * toggle save, undo buttons according texbox value
 */
function isValueChanged() {
	var isChanged = false;
	if($("#formId").val() == 0) {
		isChanged = checkNewContactForm();
	} else {
		isChanged = checkEditContactForm();
	}
	$("#undo").prop("disabled",!isChanged);
	$("#modify").prop("disabled",!isChanged);
}

/**
 * checks values of edit contact form
 *
 * @returns {Boolean}
 */
function checkEditContactForm() {
	var isChanged = false;
	var fname = $("#fname").val();
	var lname = $("#lname").val();
	var mobile = $("#mobile").val();
	var telephone = $("#telephone").val();
	var email = $("#email").val();
	var address = $("#address").val();
	var name = $("#name").text();
	var mobileVal = $("#mobileValue").text();
	var telephoneVal = $("#telephoneValue").text();
	var emailVal = $("#emailValue").text();
	var addressVal = $("#addressValue").text();
	if(name != fname + " " + lname) {
		isChanged = true;
	} else if(mobileVal != mobile) {
		isChanged= true;
	} else if(telephoneVal != telephone) {
		isChanged= true;
	} else if(addressVal != address) {
		isChanged= true;
	} else if(emailVal != email) {
		isChanged = true;
	}
	return isChanged;
}

/**
 * checks new contact form
 *
 * @returns {Boolean}
 */
function checkNewContactForm() {
	var isChanged = false;
	var fname = $("#fname").val();
	var lname = $("#lname").val();
	var mobile = $("#mobile").val();
	var telephone = $("#telephone").val();
	var email = $("#email").val();
	var address = $("#address").val();
	if(fname + lname + mobile + telephone + email + address != '') {
		isChanged = true;
	}
	return isChanged;
}


/**
 * returns json
 *
 * @returns json
 */
function getJson() {
	var id = $("#formId").val() == "" ? null : $("#formId").val();
	var fname = $("#fname").val() == "" ? null : $("#fname").val();
	var lname = $("#lname").val() == "" ? null : $("#lname").val();
	var mob = $("#mobile").val() == "" ? null : $("#mobile").val();
	var tele = $("#telephone").val() == "" ? null : $("#telephone").val();
	var email = $("#email").val() == "" ? null : $("#email").val();
	var addr = $("#address").val() == "" ? null : $("#address").val();
	var json = {
			"id" : id,
			"fname" : fname,
			"lname" : lname,
			"mobile" : mob,
			"telephone" : tele,
			"email" : email,
			"address" : addr
	};
	return JSON.stringify(json);
}

/**
 * delete contact
 */
function deleteContact() {
	var json = {
		id : $("#id").val()
	};
	var msg = DELETE_CONFIRMATION_MSG + $("#name").text();
	if(confirm(msg)){
		$.ajax({
			type : "POST",
			contentType:'application/json',
			data: JSON.stringify(json),
			url : context + "home/delete"
		}).done(function(people) {
			persons = people;
			loadNames();
			alert(CONTACT_DELETED_MSG);
		}).fail(function() {
			alert(DELETE_REQUEST_FAILED_MSG);
		});
	}
}

/**
 * reads csv file
 *
 * @param event
 */
function readCSVFile(event) {
	var csvFile = event.target.files[0];
	var reader = new FileReader();
	reader.onload = function(event){
		csvData = event.target.result;
		var json = convertToJson(csvData);
		
		$.ajax({
			type : "POST",
			contentType:'application/json',
			data: json,
			url : context + "home/import"
		}).done(function(json) {
			alert(CSV_IMPORTED_MSG);
			window.location.reload();
		}).fail(function() {
			alert(IMPORT_REQUEST_FAILED_MSG);
		});
	}
	reader.readAsText(csvFile);
}

/**
 * converts csv file to json
 *
 * @param csv
 * @returns {Array}
 */
function convertToJson(csv) {
	var lines = csv.split(NEW_LINE);
	var json = [];
	for (var index = 1; index < lines.length; index++) {
		var currentLine = lines[index].split(COMMA);
		var person = {
				fname: currentLine[0],
				lname : currentLine[1],
				mobile : currentLine[2],
				telephone : currentLine[3] == "" ? null : currentLine[3],
				email : currentLine[4] == "" ? null : currentLine[4],
				address : currentLine[5]
		};
		json.push(person);
	}
	return JSON.stringify(json);
}

/**
 * imports csv
 */
function importCSV() {
	$("#importFile").trigger('click');
}

/**
 * export CSV
 */
function exportCSV() {
	$.ajax({
		type : "POST",
		url : context + "home/export"
	}).done(function(data) {
		if(data) {
			download(data);
			alert(CSV_EXPORTED_MSG);
		} else {
			alert(EXPORT_REQUEST_FAILED_MSG);
		}
	}).fail(function() {
		alert(EXPORT_REQUEST_FAILED_MSG);
	});
}

/**
 * downloads data
 *
 * @param data
 */
function download(data) {
	var blob = new Blob([data], { type: 'text/csv' });
	if (window.navigator.msSaveOrOpenBlob) {
		window.navigator.msSaveBlob(blob, FILE_NAME);
	} else {
		var elem = window.document.createElement('a');
		elem.href = window.URL.createObjectURL(blob);
		elem.download = "contacts.csv";
		document.body.appendChild(elem);
		elem.click();
		document.body.removeChild(elem);
	}
}