function sort(col, direction) {

	var table, rows, switching, i, x, y, shouldSwitch;
  	table = document.getElementById("result_list_table");
  	switching = true;
  	while (switching) {
	    switching = false;
	    rows = table.rows;
	    for (i = 1; i < (rows.length - 1); i++) {
	    	shouldSwitch = false;
	      	x = rows[i].getElementsByTagName("TD")[col];
	      	y = rows[i + 1].getElementsByTagName("TD")[col];
			if(direction.toLowerCase() == "asc".toLowerCase()) {
				if (compare(x.innerHTML, y.innerHTML)) {
		        	shouldSwitch = true;
		        	break;
	      		}
			} else if (direction.toLowerCase() == "desc".toLowerCase()) {
				if (compare(y.innerHTML, x.innerHTML)) {
		        	shouldSwitch = true;
		        	break;
	      		}
			}
    	}
    	if (shouldSwitch) {
      		rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      		switching = true;
    	}
	}
}

function compare(a, b) {
	var res = a - b;
	if (isNaN(res)) {
		return a.toLowerCase() < b.toLowerCase();
	} else {
		return res < 0;
	}
}

