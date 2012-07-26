/**
function setupMenu(){
	$('.myMenu > li').bind('mouseover', openSubMenu);
	$('.myMenu > li').bind('mouseout', closeSubMenu);
}

function openSubMenu() {
	$(this).find('ul').css('visibility', 'visible');	
};

function closeSubMenu() {
	$(this).find('ul').css('visibility', 'hidden');	
};


var menu = null;
function getSubMenu(obj, id) {
	menu = obj.id;
	$.ajax({
		url : contextPath + "/home/GetSubMenuServlet?id=" + id,
		cache : false,
		success : successFunction,
		error : errorFunction
	});

}

function successFunction(data, textStatus, jqXHR) {
//	console.log(data);
	console.log(menu);
	
	data = data.trim();
	if(data.length>0){
		$(menu).after(data);
	}
//	alert(data);
}

function errorFunction(jqXHR, textStatus, errorThrown) {
	var msg = "Lo sentimos, se ha producido un error.\n" + errorThrown;
	alert(msg);
}
*/