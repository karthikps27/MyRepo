<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
    <title>All Tasks Summary</title>
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</head>


<body>
<div class="container">
<h2>My Tasks Info</h2>
<button type="button" class="btn btn-default" value="View Current Tasks" onclick="loadAllTasks()">View Current Tasks</button>
<button type="button" class="btn btn-default" value="View Logged Tasks" onclick="loadLoggedTasks()">View Logged Tasks</button>
   <table class="table table-hover">
   	<thead>
   		<td>Selection</td>
   		<td>Task Summary</td>
   		<td>Priority</td>
   		<td>Creation date</td>
   		<td>Task Status</td>
   		<td>Logged Status</td>
   	</thead>
   <c:forEach var="o" items="${tasks}">
   <c:choose>
   			<c:when test="${o.getTaskStatus()==1}">
   			<c:set var="cssclass" value="info"></c:set>
   			</c:when>
   			<c:when test="${o.getTaskStatus()==2}">
   			<c:set var="cssclass" value="active"></c:set>
   			</c:when>
   			<c:when test="${o.getTaskStatus()==3}">
   			<c:set var="cssclass" value="success"></c:set>
   			</c:when>
   			<c:when test="${o.isLogged()==true}">
   			<c:set var="cssclass" value="info"></c:set>
   			</c:when>	   	
   	</c:choose>
   	<tr class="${cssclass}" >
   		<td><input type="radio" value="${o.getTaskId()}" id="radioButton" name="radioButtonName"/></td>
   		<td>${o.getTaskSummary()}</td>
   		<c:choose>
   			<c:when test="${o.getPriority()==1}">
   			<td style="background-color: #FB6E6E">Very Critical</td>
   			</c:when>
   			<c:when test="${o.getPriority()==2}">
   			<td style="background-color: #FB946E">Critical</td>
   			</c:when>
   			<c:when test="${o.getPriority()==3}">
   			<td>Valuable</td>
   			</c:when>
   			<c:when test="${o.getPriority()==4}">
   			<td class="success">Desirable</td>
   			</c:when>
   		</c:choose>
   		<td>${o.getDateOfCreation()}</td>
   		<c:choose>
   			<c:when test="${o.getTaskStatus()==1}">
   			<td>Created</td>
   			</c:when>
   			<c:when test="${o.getTaskStatus()==2}">
   			<td>In Progress</td>
   			</c:when>
   			<c:when test="${o.getTaskStatus()==3}">
   			<td>Completed</td>
   			</c:when>
   		</c:choose>
   		<c:choose>
   			<c:when test="${o.isLogged()==false}">
   			<td>Current</td>
   			</c:when>
   			<c:when test="${o.isLogged()==true}">
   			<td>Logged</td>
   			</c:when>   			
   		</c:choose>   		
   	</tr>
    </c:forEach>
    </table>
    
    <button type="button" class="btn btn-default" value="Delete Tasks" onclick="deleteTask()">Delete Tasks</button>
    <button type="button" class="btn btn-default" value="Mark as In-Progress" onclick="inProgressTask()">Mark as In-Progress</button>
    <button type="button" class="btn btn-default" value="Mark as Complete" onclick="completeTask()">Mark as Complete</button>
    <button type="button" class="btn btn-default" value="Move the task to log" onclick="moveTaskToLog()" >Move the task to log</button>
   </div>
</body>
</html>