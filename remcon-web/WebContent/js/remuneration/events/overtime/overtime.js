var selectedRadio = 1;
var action = "";
var date = null;
var percent = null;
var amount = null;

function add(lastDayMonth) {
	$("#defaultButtons").hide();
	$("#commitButtons").fadeIn(speed);

	action = "ADD";

	// $('#overtimeTable').append("<tr><td class='cDataTD'>my data</td><td
	// class='cDataTD'>more data</td></tr>");

	var row = document.getElementById('overtimeTable').insertRow(-1);

	buidInputs(row, true, lastDayMonth);

	loadFormat();

}

function commitAdd() {
	// $("#commitButtons").hide();
	// $("#defaultButtons").fadeIn(speed);

	document.getElementById("cDate").value = document.getElementById("date").value;
	document.getElementById("cPercent").value = document
			.getElementById("percent").value;
	document.getElementById("cAmount").value = document
			.getElementById("amount").value;

	document.getElementById("frm").setAttribute("action",
			contextPath + "/servlet/remuneration/events/overtime/AddOvertime");
	document.getElementById("frm").submit();
}

function cancel() {
	$("#commitButtons").hide();
	$("#defaultButtons").fadeIn(speed);

	if (action == "ADD") {
		document.getElementById('overtimeTable').deleteRow(-1);
	}
}

function selectRadio(index) {
	selectedRadio = index;
}

function editOvertime(lastDayMonth) {
	$("#defaultButtons").hide();
	$("#commitButtons").fadeIn(speed);
	action = "EDIT";

	var row = document.getElementById('overtimeTable').rows[selectedRadio];
//	row.cells[0].innerHTML = "&nbsp;-&nbsp;";

	date = row.cells[1].innerHTML;
	percent = row.cells[2].innerHTML;
	amount = row.cells[3].innerHTML;

	buidInputs(row, false, lastDayMonth);

	document.getElementById("date").value = date;
	document.getElementById("percent").value = percent;
	document.getElementById("amount").value = amount;

	loadFormat();

}

function buidInputs(row, isNew, lastDayMonth) {
	var cell = isNew ? row.insertCell(-1) : row.cells[0];	
	cell.setAttribute("class", "cDataTD");
	cell.innerHTML = "&nbsp;-&nbsp;";

	var cell = isNew ? row.insertCell(-1) : row.cells[1];
	cell.setAttribute("class", "cDataTD");
	var html = "<select id='lastDayMonth'>";
	for(var i = 1; i<=lastDayMonth; i++){
		html += "<option value='" + i + "'>" + i +"</option>";
	}
	html += "</select>";
	cell.innerHTML = html;

	var cell = isNew ? row.insertCell(-1) : row.cells[2];
	cell.setAttribute("class", "cDataTD");
	cell.innerHTML = "<input type='text' id='percent'>";

	var cell = isNew ? row.insertCell(-1) : row.cells[3];
	cell.setAttribute("class", "cDataTD");
	cell.innerHTML = "<input type='text' id='amount'>";

}