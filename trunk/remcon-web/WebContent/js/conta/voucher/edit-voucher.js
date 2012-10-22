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
		success : success,
		async : false
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
var currentRow = 0;
function addNewRow() {
	var table = document.getElementById("voucherDetail");
	var row = table.insertRow(-1);

	currentRow = table.rows.length - 2;

	var cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='cRUT' size='10px' maxlength='10'>";
	cell.style.cssText = "text-align: center";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = getVoucherTypeListAsSelect();
	cell.style.cssText = "text-align: center";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='cDocumentNumber' " + "onfocus='javascript:integerFocus(this);' "
			+ "onblur='javascript:integerBlur(this);' " + "size='10px' maxlength='10' value='0'>";
	cell.style.cssText = "text-align: center";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='cNetAmount' " + "onfocus='javascript:doubleFocus(this);' "
			+ "onblur='javascript:doubleBlur(this);' " + "size='12px' maxlength='12' value='0' " + "style='text-align:right'>";
	cell.style.cssText = "text-align: right";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<input type='text' id='cTax' " + "onfocus='javascript:doubleFocus(this);' "
			+ "onblur='javascript:doubleBlur(this);' " + "size='12px' maxlength='12' value='0' " + "style='text-align:right'>";
	cell.style.cssText = "text-align: right";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = getBusinessAreaListAsSelect();
	cell.style.cssText = "text-align: left";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<select id='cCostCenter" + currentRow + "'/>";
	cell.style.cssText = "text-align: left";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<input size='10px' maxlength='10'/>";
	cell.style.cssText = "text-align: left";
	cell.className = "cDataTD";

	cell = row.insertCell(-1);
	cell.innerHTML = "<img width='20px' alt='Borrar' title='Borrar' src='" + contextPath + "/img/action/delete.png'>";
	cell.style.cssText = "text-align: center";
	cell.className = "cDataTD";

	fillCostCenter();
	submitRow();
}

function submitRow(){
	//SaveVoucherDetail
}

function fillCostCenter() {
	var businessArea = document.getElementById("cBusinessArea" + currentRow).value;

	$.ajax({
		url : contextPath + "/servlet/config/enterprise/costCenter/ListByBusinessArea",
		type : "post",
		cache : false,
		data : {
			cBusinessArea : businessArea
		},
		success : function(response) {
			var elements = JSON.parse(response);
			// elements = [{branch:1,businessArea:1,id:1,name:'Oficina'}];
			// alert(elements);
			for ( var i in elements) {
				id = elements[i].id;
				name = elements[i].name;
				appendToSelect("cCostCenter" + currentRow, id, name);
			}
			/*
			 * var elements = response.responseText; alert(elements); var name =
			 * null; var id = null; clearSelect("cCostCenter"); for (var i in
			 * elements) { name = elements[i].name; id = elements[i].id;
			 * 
			 * appendToSelect("cCostCenter", id, name); }
			 */
		},
		error : function(response) {
			alert(response.responseText);
		},
		async : true
	});

	/*
	 * clearSelect("cCostCenter"); appendToSelect("cCostCenter", 1, "hola mundo
	 * 1"); appendToSelect("cCostCenter", 2, "hola mundo 2");
	 * appendToSelect("cCostCenter", 3, "hola mundo 3");
	 * appendToSelect("cCostCenter", 4, "hola mundo 4");
	 */

}

function getVoucherTypeListAsSelect() {
	var out = "<select id='cDocumentType" + currentRow + "'>";
	for ( var index in voucherTypeList) {
		out += "<option value='" + voucherTypeList[index].id + "'>" + voucherTypeList[index].name + "</option>";
	}

	out += "</select>";
	return out;
}

function getBusinessAreaListAsSelect() {
	var out = "<select id='cBusinessArea" + currentRow + "'>";
	for ( var index in businessAreaList) {
		out += "<option value='" + businessAreaList[index].id + "'>" + businessAreaList[index].name + "</option>";
	}

	out += "</select>";
	return out;
}
