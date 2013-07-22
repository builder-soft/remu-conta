function newParent(){
//	alert(document.getElementById('parentForm').childNodes.length);
//	alert(document.getElementById('parentForm').elements.length);
	
	var coll = document.getElementById('parentForm').elements;
	
	for(e in coll){
		alert(e.innerHTML);
	}
	
	/**
	for(var idx = 0; idx< document.getElementById('parentForm').childNodes.length;idx++){
		alert(document.getElementById('parentForm').elements.length);
	}
	*/
	
}