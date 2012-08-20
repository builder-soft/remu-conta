function acceptHoliday(){
	document.getElementById("frm").submit();
}

function calculateEndDate(){
	var url = contextPath + '/servlet/remuneration/events/holiday/ValidateHolidayParameters';
	var from = document.getElementById("cFrom").value;
	var normal = document.getElementById("cNormal").value;
	var creeping = document.getElementById("cCreeping").value;
	
	var data = {cFrom:from, cNormal:normal, cCreeping:creeping};
	$.ajax({url:url,type:"post", data:data, error:error, success:success});
	
	return true;
}

function error(jqXHR, textStatus, errorThrown){
//	alert('Error : ' + textStatus);
	document.getElementById("dateMessage").innerHTML = jqXHR.responseText; // + " - " + textStatus + " - " + errorThrown;
}

function success(response){
	document.getElementById("cTo").value = response;
}