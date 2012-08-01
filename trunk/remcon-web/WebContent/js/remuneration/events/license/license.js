function changeDay(){
	var from = document.getElementById("From").selectedIndex;
	var to = document.getElementById("To").selectedIndex;

	//alert("from " + from + "\n to " + to);
	
	if(from >= to){
		document.getElementById("To").selectedIndex = from + 1;
		to = document.getElementById("To").selectedIndex;
	}
	var diff = to - from;
	document.getElementById("days").innerHTML = diff;
	
	
}

function showForm(obj){
	showTooltip('divShowDetail');
	loadFormat();
	changeDay();
//	alert(document.getElementById("From").selectedIndex);
}