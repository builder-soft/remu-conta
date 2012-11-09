var currentRow = 0;
function onLoadPage() {
	return;
	// alert(voucherDetailList);

	var voucherId = null;
	var idRow = null;
	// currentRow = 1;
	for ( var i in voucherDetailList) {
		// id = voucherDetailList[i].id;
		voucherId = voucherDetailList[i].voucher;

		// appendToSelect("cCostCenter" + currentRow, id, name);
		idRow = addNewRow(voucherId, false);
		document.getElementById("detailId" + idRow).value = voucherDetailList[i].detailId;
		document.getElementById("rut" + idRow).value = voucherDetailList[i].rut;
		document.getElementById("documentType" + idRow).value = voucherDetailList[i].documentType;
		document.getElementById("documentNumber" + idRow).value = voucherDetailList[i].documentNumber;
		document.getElementById("netAmount" + idRow).value = voucherDetailList[i].netAmount;
		document.getElementById("tax" + idRow).value = voucherDetailList[i].tax;
		document.getElementById("businessArea" + idRow).value = voucherDetailList[i].businessArea;

		// currentRow++;
	}

}

function saveVoucher() {
	var dateObject = document.getElementById("cAccountingDate");
	if (dateObject.value == '' || isDate(dateObject.value)) {
		var url = contextPath + "/servlet/conta/voucher/SaveVoucher";

		var id = document.getElementById("cId").value;
		var voucherType = document.getElementById("cVoucherType").value;
		var businessArea = document.getElementById("cBusinessArea").value;
		var accountingDate = dateObject.value;

		var data = {
			cId : id,
			cVoucherType : voucherType,
			cBusinessArea : businessArea,
			cAccountingDate : accountingDate
		};

		$.ajax({
			url : url,
			type : "post",
			cache : false,
			data : data,
			error : error,
			success : success,
			async : false
		});
	} else {
		formatIfValid(dateObject, null);
	}
}

function error(jqXHR, textStatus, errorThrown) {
	alert(jqXHR.responseText);
}

function success(response) {
	if (response != "OK") {
		alert('Ha ocurrido un error inesperado');
	}
}
function addNewRow(idVoucher, save) {
	var table = document.getElementById("voucherDetail");
	var row = table.insertRow(-1);

	currentRow = table.rows.length - 2;
	var cell = row.insertCell(-1);

	var idInput = "<input type='_hidden' id='detailId" + currentRow + "'>";
	cell.innerHTML = idInput + "<input type='text' id='chartAccount" + currentRow
			+ "' size='10px' maxlength='10' onblur='javascript:submitRow(" + idVoucher + "," + currentRow + ")'>";
	cell.style.cssText = "text-align: center";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = getDocumentTypeListAsSelect();
	cell.style.cssText = "text-align: center";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='documentNumber" + currentRow + "' onfocus='javascript:integerFocus(this);' "
			+ "onblur='javascript:integerBlur(this);' size='10px' maxlength='10' value='0'>";
	cell.style.cssText = "text-align: center";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='netAmount" + currentRow + "' " + "onfocus='javascript:doubleFocus(this);' "
			+ "onblur='javascript:doubleBlur(this);' " + "size='12px' maxlength='12' value='0' " + "style='text-align:right'>";
	cell.style.cssText = "text-align: right";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<select id='costCenter" + currentRow + "'/>";
	cell.style.cssText = "text-align: left";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<img width='20px' alt='Borrar' title='Borrar' style='cursor:pointer' src='" + contextPath
			+ "/img/action/delete.png'>";
	cell.style.cssText = "text-align: center";
	cell.className = "cDataTD";

	fillCostCenter();
	return;
	if (save) {
		submitRow(idVoucher, currentRow);
	}
	return currentRow;
}

function submitRow(idVoucher, currentRow) {
	// alert("idVoucher, currentRow" + idVoucher +","+ currentRow);

	var detailId = document.getElementById("detailId" + currentRow).value;
	var chartAccount = document.getElementById("chartAccount" + currentRow).value;
	var documentType = document.getElementById("documentType" + currentRow).value;
	var documentNumber = document.getElementById("documentNumber" + currentRow).value;
	var netAmount = document.getElementById("netAmount" + currentRow).value;
	var costCenter = document.getElementById("costCenter" + currentRow).value;

	var params = null;

	if (detailId == '') {
		params = {
			cId : idVoucher,
			cChartAccount : chartAccount,
			cDocumentType : documentType,
			cDocumentNumber : documentNumber,
			cNetAmount : netAmount,
			cCostCenter : costCenter
		};
	} else {
		params = {
			cId : idVoucher,
			cDetailId : detailId,
			cChartAccount : chartAccount,
			cDocumentType : documentType,
			cDocumentNumber : documentNumber,
			cNetAmount : netAmount,
			cCostCenter : costCenter
		};
	}
	// ==''?null:document.getElementById("detailId" + currentRow).value;

	// alert("datailId="+datailId);
	$.ajax({
		url : contextPath + "/servlet/conta/voucher/SaveVoucherDetail",
		type : "post",
		cache : false,
		async : true,
		data : params,
		success : function(response) {
			if (response != "OK") {
				alert(response);
			}
		},
		error : function(response) {
			alert("Se ha producido un error, intentelo nuevamente, si el error persiste, comuniquese con su administrador.");
		}
	});
}

function fillCostCenter() {
	var businessArea = document.getElementById("cBusinessArea").value;

	clearSelect("costCenter" + currentRow);
	if (businessArea != "") {

		$.ajax({
			url : contextPath + "/servlet/config/enterprise/costCenter/ListByBusinessArea",
			type : "post",
			cache : false,
			data : {
				cBusinessArea : businessArea
			},
			success : function(response) {
				var elements = JSON.parse(response);

				appendToSelect("costCenter" + currentRow, "", "- Seleccionar -");
				for ( var i in elements) {
					id = elements[i].id;
					name = elements[i].name;
					appendToSelect("costCenter" + currentRow, id, name);
				}
			},
			error : function(response) {
				alert(response.responseText);
			},
			async : true
		});
	} else {
		appendToSelect("costCenter" + currentRow, "", "- Seleccionar -");
	}
}

function getDocumentTypeListAsSelect() {
	var out = "<select id='documentType" + currentRow + "'>";
	out += "<option value=''>- Seleccionar -</option>";

	for ( var index in documentTypeList) {
		out += "<option value='" + documentTypeList[index].id + "'>" + documentTypeList[index].name + "</option>";
	}

	out += "</select>";
	return out;
}

function getBusinessAreaListAsSelect() {
	var out = "<select id='cBusinessArea" + currentRow + "' onchange='javascript:fillCostCenter()'>";
	out += "<option value=''>- Seleccionar -</option>";
	for ( var index in businessAreaList) {
		out += "<option value='" + businessAreaList[index].id + "'>" + businessAreaList[index].name + "</option>";
	}

	out += "</select>";
	return out;
}
