function submitPeriod() {
	var elements = [ {
		name : "a",
		type : 'Double'
	}, {
		name : "b",
		type : 'Double'
	} ];
	
	for ( var i in elements) {
		alert(elements[i].name + ' ' + elements[i].type);
	}
}