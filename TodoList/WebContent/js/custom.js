/**
 * 
 */

function setDateInTheFormField() {
	var dt = new Date();
	  var dd = dt.getDate();
	  var mm = dt.getMonth();
	  mm++;
	  var yyyy = dt.getFullYear();
	  if(dd<10) {
		  dd='0'+dd;
	  }
	  if(mm<10) {
		  mm='0'+mm;
	  }
	  document.getElementById("creationDate").value = mm+'/'+dd+'/'+yyyy;	  	 
}

function loadAllTasks() {
	  /*var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/getTasks", true);
	  xhttpRequest.send();*/
	$.ajax({
		  url: "/TodoList/getTaskJson",
		  type: "get",
		  success: function(result) {
			  createAndPopUsingAjax(result);
		  }	  
	  })
  }
  
  function deleteTask() {
	  
	  var id=document.querySelector('input[name="radioButtonName"]:checked').value;
	  /*var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/deleteTasks?id="+id, true);
	  xhttpRequest.send();*/
	  $.ajax({
		  url: "/TodoList/deleteTasksRest",
		  type: "get",
		  data: "id="+id,
		  success: function(result) {
			  createAndPopUsingAjax(result);
		  }	  
	  })
  }
  
	function inProgressTask() {
		  
		  var id=document.querySelector('input[name="radioButtonName"]:checked').value;
		  /*var xhttpRequest = new XMLHttpRequest();
		  
		  xhttpRequest.onreadystatechange = function() {
			if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
		      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
		    }  
		  };	  
		  xhttpRequest.open("GET", "/TodoList/markAsInProgress?id="+id, true);
		  xhttpRequest.send();*/
		  $.ajax({
			  url: "/TodoList/markAsInProgressRest",
			  type: "get",
			  data: "id="+id,
			  success: function(result) {
				  createAndPopUsingAjax(result);
			  }	  
		  })
	  }
  
function completeTask() {
	  
	  var id=document.querySelector('input[name="radioButtonName"]:checked').value;
	  var comments=document.getElementById("comments").value;
	  /*var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };*/	  
	  //xhttpRequest.open("GET", "/TodoList/markAsComplete?id="+id+"&comments="+comments, true);
	  //xhttpRequest.send();
	  
	  $.ajax({
		  url: "/TodoList/markAsCompleteRest",
		  type: "get",
		  data: "id="+id+"&comments="+comments,
		  success: function(result) {
			  createAndPopUsingAjax(result);
		  }	  
	  })
}

function moveTaskToLog() {
	var id=document.querySelector('input[name="radioButtonName"]:checked').value;
	  /*var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/moveTaskToLog?id="+id, true);
	  xhttpRequest.send();*/
	$.ajax({
		  url: "/TodoList/moveTaskToLogRest",
		  type: "get",
		  data: "id="+id,
		  success: function(result) {
			  createAndPopUsingAjax(result);
		  }	  
	  })
}

function loadLoggedTasks() {
	  /*var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/getLoggedTasks", true);
	  xhttpRequest.send();*/
	$.ajax({
		  url: "/TodoList/getLoggedTasksRest",
		  type: "get",
		  success: function(result) {
			  createAndPopUsingAjax(result);
		  }	  
	  })
}

function updateTask(taskId) {
	var summary = document.getElementById("summaryInModal").value;
	var priority = document.getElementById("priorityModal").value;
	var comments = document.getElementById("commentInModal").value;
	
	$.ajax({
		url: "/TodoList/updateTask",
		type: "get",
		data: "id="+taskId+"&summary="+summary+"&priority="+priority+"&comments="+comments,
		success: function(result) {
			createAndPopUsingAjax(result);
		}
	}
	)
	
}

function getQueriedTasks() {
	var fromDate = document.getElementById("fromDate").value;
	var toDate = document.getElementById("toDate").value;
	var priority = document.getElementById("queryPriority").value;
	/*var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/getQueriedTasks?fromDate="+fromDate+"&toDate="+toDate+"&priority="+priority, true);
	  xhttpRequest.send();*/
	$.ajax({
		  url: "/TodoList/getQueriedTasksRest",
		  type: "get",
		  data: "fromDate="+fromDate+"&toDate="+toDate+"&priority="+priority,
		  success: function(result) {
			  createAndPopUsingAjax(result);
		  }	  
	  })
}



function createAndPopUsingAjax(result) {
	
	setDateInTheFormField();
	
	setInterval(function(){ 
		  var dt = new Date();
		  var dd = dt.getDate();
		  var mm = dt.getMonth();
		  var yyyy = dt.getFullYear();
		  mm++;
		  if(dd<10) {
			  dd='0'+dd;
		  }
		  if(mm<10) {
			  mm='0'+mm;
		  }
		  document.getElementById("creationDate").value = mm+'/'+dd+'/'+yyyy;
		  }, 60000);
	
	var tableWrapper = document.getElementById("tasksTable_wrapper");
	var divTag = document.getElementById("tableDiv");
	if(tableWrapper != null) {
		divTag.removeChild(document.getElementById("tasksTable_wrapper"));
	}
	var tableObject = document.createElement("table");
	tableObject.setAttribute("id","tasksTable");
	tableObject.setAttribute("class", "display");
	divTag.appendChild(tableObject);
	
	/*$.ajax({
		url: "/TodoList/getTaskJson",
		success: function(result) {
			
		}
	})*/
	var head = tableObject.createTHead();
	var columns = ["Selection", "Task Summary", "Priority", "Creation date", "Task Status", "Logged Status"];
	var priorities = ["Very Critical", "Critical", "Valuable", "Desirable"];
	var priorityColor = ["#FB6E6E", "#FB946E", "#ccccff", "#ccffcc"];
	var status = ["Created", "In-Progress", "Completed"];
	var statusColor = ["#b3b3ff", "#ffff99", "#99ffcc"];
	headrow = head.insertRow(0);
	for(column in columns) {
		columnObject = headrow.appendChild(document.createElement("th"));
		columnObject.innerHTML = columns[column];
	}			
	
	var tbody = tableObject.appendChild(document.createElement("tbody"))
	for(student in result) {
		row = tbody.insertRow(0)
		row.setAttribute("style","background-color: " + statusColor[result[student].taskStatus-1])
		cell1 = row.insertCell(0);
		cell1.innerHTML = '<input type="radio" value="' + result[student].taskId + 
		'" id="radioButton" name="radioButtonName"/>';
		cell2 = row.insertCell(1);
		cell2.innerHTML = '<a href="/TodoList/getTaskDetails?id=' + result[student].taskId +
		'" data-toggle="modal" data-target="#taskDetail">' + result[student].taskSummary + '</a>';
		cell2 = row.insertCell(2);
		cell2.innerHTML = priorities[result[student].priority-1];
		cell2.setAttribute("style","background-color: " + priorityColor[result[student].priority-1])
		cell2 = row.insertCell(3);
		cell2.innerHTML = result[student].dateOfCreation;
		cell2 = row.insertCell(4);
		cell2.innerHTML = status[result[student].taskStatus-1];
		cell2 = row.insertCell(5);
		if(result[student].logged)
			cell2.innerHTML = "Task is Logged";
		else
			cell2.innerHTML = "Not Logged";
	}
	setTimeout(transformTable, 300);
}

function transformTable() {
	$("#tasksTable").DataTable();
}