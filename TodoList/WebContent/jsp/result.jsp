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
   <table class="table table-hover">
   	<thead>
   		<td>Selection</td>
   		<td>Task Summary</td>
   		<td>Priority</td>
   		<td>Creation date</td>
   		<td>Status</td>
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
   	</c:choose>
   	<tr class="${cssclass}" >
   		<td><input type="radio" value="${o.getTaskId()}" id="radioButton" name="radioButtonName"/></td>
   		<td>${o.getTaskSummary()}</td>
   		<c:choose>
   			<c:when test="${o.getPriority()==1}">
   			<td>Very Critical</td>
   			</c:when>
   			<c:when test="${o.getPriority()==2}">
   			<td>Critical</td>
   			</c:when>
   			<c:when test="${o.getPriority()==3}">
   			<td>Valuable</td>
   			</c:when>
   			<c:when test="${o.getPriority()==4}">
   			<td>Desirable</td>
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
   	</tr>
    </c:forEach>
    </table>
    
    <input type="button" value="Delete Tasks" onclick="deleteTask()"/>
    <input type="button" value="Mark as In-Progress" onclick="inProgressTask()"/>
    <input type="button" value="Mark as Complete" onclick="completeTask()"/>
   </div>
</body>
</html>