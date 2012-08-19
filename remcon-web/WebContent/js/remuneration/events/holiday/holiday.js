function acceptHoliday(){
	alert(1);
}

function calculateEndDate(){
	var url = contextPath + '/servlet/remuneration/events/holiday/ValidateHolidayParameters';
	var data = {p1:123};
	$.ajax({url:url,type:"post", data:data, error:error, success:success});
	
}

function error(){
	alert('error');
}

function success(v){
	alert('ok ' + v);
}