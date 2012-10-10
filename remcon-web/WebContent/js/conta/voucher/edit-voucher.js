function saveVoucher() {
//	document.getElementById("frm").submit();

	var url = contextPath + "/servlet/conta/voucher/SaveVoucher";

	var id = document.getElementById("cId").value;
	var voucherType = document.getElementById("cVoucherType").value;
	var number = document.getElementById("cNumber").value;
	/**
	 * var normal = document.getElementById("cNormal").value; var creeping =
	 * document.getElementById("cCreeping").value;
	 */
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
