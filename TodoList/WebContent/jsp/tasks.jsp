<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>My Tasks</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  
  function loadAllTasks() {
	  var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "http://localhost:8080/TodoList/getTasks", true);
	  xhttpRequest.send();
	  
	  var dt = new Date();
	  document.getElementById("creationDate").value = dt;
	  
	  setInterval(function(){ 
		  var dt = new Date();
		  document.getElementById("creationDate").value = dt;
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
	  xhttpRequest.open("GET", "http://localhost:8080/TodoList/deleteTasks?id="+id, true);
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
		  xhttpRequest.open("GET", "http://localhost:8080/TodoList/markAsInProgress?id="+id, true);
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
	  xhttpRequest.open("GET", "http://localhost:8080/TodoList/markAsComplete?id="+id, true);
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
	  xhttpRequest.open("GET", "http://localhost:8080/TodoList/moveTaskToLog?id="+id, true);
	  xhttpRequest.send();
}

function loadLoggedTasks() {
	  var xhttpRequest = new XMLHttpRequest();
	  
	  xhttpRequest.onreadystatechange = function() {
		if(xhttpRequest.readyState == 4 && xhttpRequest.status == 200) {
	      document.getElementById("taskDetails").innerHTML = xhttpRequest.responseText;
	    }  
	  };	  
	  xhttpRequest.open("GET", "http://localhost:8080/TodoList/getLoggedTasks", true);
	  xhttpRequest.send();	  	   	 
}
  
  </script>
  
</head>
<body onload="loadAllTasks()">

<div class="container">
  <h2>Tasks</h2>
<form:form class="form-horizontal" role="form" action="/TodoList/addTasks">
<div class="form-group">
    <label class="control-label col-sm-2" for="email">Task Summary:</label>
    <div class="col-sm-10">
      <form:input path="taskSummary" type="text" class="form-control" id="taskSummary" placeholder="Enter Task Summary Here"/>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="email">Task Priority:</label>
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
    <label class="control-label col-sm-2" for="email">Task Creation Date:</label>
    <div class="col-sm-10">
      <form:input path="dateOfCreation" type="text" class="form-control" id="creationDate" placeholder="Enter Date"/>
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