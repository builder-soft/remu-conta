function saveVoucher() {
	var url = contextPath + "/servlet/conta/voucher/SaveVoucher";

	var id = document.getElementById("cId").value;
	var voucherType = document.getElementById("cVoucherType").value;
	var number = document.getElementById("cNumber").value;

	var data = {
		cId : id,
		cVoucherType : voucherType,
		cNumber : number
	};

	$.ajax({
		url : url,
		type : "post",
		cache : false,
		data : data,
		error : error,
		success : success
	});

}

function error(jqXHR, textStatus, errorThrown) {
	alert(jqXHR.responseText);
}

function success(response) {
	if (response != "OK") {
		alert('Ha ocurrido un error inesperado');
	}
}

function addNewRow() {
	var table = document.getElementById("voucherDetail");
	var row = table.insertRow(-1);

	var cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='cRUT' size='10px' maxlength='10'>";
	cell.style.cssText = "text-align: center";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = getVoucherTypeListAsSelect();
	cell.style.cssText = "text-align: center";	
	cell.className = "cDataTD";
	
	cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='cDocumentNumber' " +
		"onfocus='javascript:integerFocus(this);' " + 
		"onblur='javascript:integerBlur(this);' " +
		"size='10px' maxlength='10' value='0'>";
	cell.style.cssText = "text-align: center";	
	cell.className = "cDataTD";
	
	cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='cNetAmount' " +
		"onfocus='javascript:doubleFocus(this);' " + 
		"onblur='javascript:doubleBlur(this);' " +
		"size='12px' maxlength='12' value='0' "+
		"style='text-align:right'>";
	cell.style.cssText = "text-align: right";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='cTax' " +
		"onfocus='javascript:doubleFocus(this);' " + 
		"onblur='javascript:doubleBlur(this);' " +
		"size='12px' maxlength='12' value='0' " +
		"style='text-align:right'>";
	cell.style.cssText = "text-align: right";
	cell.className = "cDataTD";
	
	
	cell = row.insertCell(-1);
	cell.innerHTML = "&nbsp;";
	cell.style.cssText = "text-align: left";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "&nbsp;";
	cell.style.cssText = "text-align: left";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "&nbsp;";
	cell.style.cssText = "text-align: left";
	cell.className = "cDataTD";
}

function getVoucherTypeListAsSelect() {
	var out = "<select id='cDocumentType'>";
	for ( var index in voucherTypeList) {
		out += "<option value='" + voucherTypeList[index].id + "'>" + voucherTypeList[index].name + "</option>";
	}

	out += "</select>";
	return out;
	// voucherTypeList
}