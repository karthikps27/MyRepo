<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>My Tasks</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/bootstrap-datetimepicker.min.js"></script>
  
  <script type="text/javascript">
  
  function loadAllTasks() {
	  var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/getTasks", true);
	  xhttpRequest.send();
	  
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
  }
  
  function deleteTask() {
	  
	  var id=document.querySelector('input[name="radioButtonName"]:checked').value;
	  var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/deleteTasks?id="+id, true);
	  xhttpRequest.send();
  }
  
	function inProgressTask() {
		  
		  var id=document.querySelector('input[name="radioButtonName"]:checked').value;
		  var xhttpRequest = new XMLHttpRequest();
		  
		  xhttpRequest.onreadystatechange = function() {
			if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
		      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
		    }  
		  };	  
		  xhttpRequest.open("GET", "/TodoList/markAsInProgress?id="+id, true);
		  xhttpRequest.send();
	  }
  
function completeTask() {
	  
	  var id=document.querySelector('input[name="radioButtonName"]:checked').value;
	  var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/markAsComplete?id="+id, true);
	  xhttpRequest.send();
}

function moveTaskToLog() {
	var id=document.querySelector('input[name="radioButtonName"]:checked').value;
	  var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/moveTaskToLog?id="+id, true);
	  xhttpRequest.send();
}

function loadLoggedTasks() {
	  var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/getLoggedTasks", true);
	  xhttpRequest.send();	  	   	 
}

function getQueriedTasks() {
	var fromDate = document.getElementById("fromDate").value;
	var toDate = document.getElementById("toDate").value;
	var priority = document.getElementById("priority").value;
	var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "/TodoList/getQueriedTasks?fromDate="+fromDate+"&toDate="+toDate+"&priority="+priority, true);
	  xhttpRequest.send();
}
  
  </script>
  
</head>
<body onload="loadAllTasks()">

<div class="container">
  <h2>Create Tasks</h2>
<form:form class="form-horizontal" role="form" action="/TodoList/addTasks">
<div class="form-group">
    <label class="control-label col-sm-2" for="taskSummary">Task Summary:</label>
    <div class="col-sm-10">
      <form:input path="taskSummary" type="text" class="form-control" id="taskSummary" placeholder="Enter Task Summary Here"/>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="priority">Task Priority:</label>
    <div class="col-sm-10">
      <form:select path="priority" type="text" class="form-control" id="priority" >      
      	<option value="1">Very Critical</option>
      	<option value="2">Critical</option>
      	<option value="3" selected="selected">Valuable</option>
      	<option value="4">Desirable</option>      	
      </form:select>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="creationDate">Task Creation Date:</label>
    <div class="col-sm-10">
      <form:input path="dateOfCreation" class="form-control" id="creationDate" placeholder="Enter Date" type="text"/>
    </div>
  </div>
  <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>
</form:form>
</div>

<div id="taskDetails"></div>

</body>
</html>
