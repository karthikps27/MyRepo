<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>My Tasks</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/bootstrap-datetimepicker.min.js"></script>
  <script src="js/custom.js"></script>
  
  <script>
$(document).ready(function(){
    $("#search").click(function(){
        $("#myModal").modal();
    });
});
</script>
  
</head>
<body onload="loadAllTasks()">
<div>
<div class="col-xs-12 text-right">
<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<button style="position:right 25px " type="button" class="btn btn-default" value="Logout" onclick="javascript:document.getElementById('logout').submit()">Logout</button>
</c:if>
</div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <img src="css/pacemaker_black.png" width="145" height="75">
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="javascript:loadAllTasks()">View Current Tasks</a></li>
      <li><a href="javascript:loadLoggedTasks()">View Logged Tasks</a></li>
      <li><a href="#" id="search">Custom Search</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="javascript:document.getElementById('logout').submit()"><span class="glyphicon glyphicon-log-in"></span>Logout</a></li>
    </ul>
  </div>
</nav>

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

<div class="container">
<h2>My Tasks Info</h2>
   
	
	<div id="tableDiv">	  
    </div>
    
    <button type="button" class="btn btn-default" value="Delete Tasks" onclick="deleteTask()">Delete Tasks</button>
    <button type="button" class="btn btn-default" value="Mark as In-Progress" onclick="inProgressTask()">Mark as In-Progress</button>
    <button type="button" class="btn btn-default" value="Mark as Complete" data-toggle="modal" data-target="#completeTask">Mark as Complete</button>
    <button type="button" class="btn btn-default" value="Move the task to log" onclick="moveTaskToLog()" >Move the task to log</button>
   </div>
   
   <div class="modal fade" id="myModal" role="dialog" >
	<div class="modal-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Narrow Your Search</h4>
			</div>
			<div class="modal-body">
				<form role="form">
					<div class="form-group">
    					<label for="email">From Date:</label>
					    <input type="text" class="form-control" id="fromDate" placeholder="dd/MM/yyyy"/>
					</div>
					<div class="form-group">
						<label for="toDate">To Date:</label>
					    <input type="text" class="form-control" id="toDate" placeholder="dd/MM/yyyy"/>
					</div>
					<div class="form-group">
					  <label for="email">Task Priority:</label>
					    <select class="form-control" id="queryPriority" >      
					    	<option value="1">Very Critical</option>
					    	<option value="2">Critical</option>
					    	<option value="3">Valuable</option>
					    	<option value="4">Desirable</option>      	
					    </select>
					</div>					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="getQueriedTasks()">Query</button>
        	</div>
		</div>
	</div>
	</div>
	
	<div class="modal fade" id="completeTask" role="dialog" >
	<div class="modal-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Mark the task as Complete</h4>
			</div>
			<div class="modal-body">
				<form role="form">
					<div class="form-group">
    					<label for="comments">Comments:</label>
					    <textarea class="form-control" id="comments" rows="5" placeholder="Enter the comments here"></textarea>
					</div>					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="completeTask()">Mark as Complete</button>
        	</div>
		</div>
	</div>
	</div>
	
	<div id="taskDetail" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
            	     
            </div>
        </div>
    </div>

</body>
</html>
