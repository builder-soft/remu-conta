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
	if(response != "OK"){
		alert('Ha ocurrido un error inesperado');
	}
}

function addNewRow(){
	var table = document.getElementById("voucherDetail");
	var row = table.insertRow(-1);

	var cell =	row.insertCell(-1);
	cell.innerHTML = "hola mundo";
	cell.className = "cDataTD";
}
