<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">
$(document.body).on('hidden.bs.modal', function () {
    $('#taskDetail').removeData('bs.modal')
});
</script>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h4 class="modal-title">Confirmation</h4>
</div>
<div class="modal-body">
    <table class="table table-hover">
	<tr>
		<td><b>Task Summary</b></td>
		<td>${individualTask.getTaskSummary()}</td>		
	</tr>
	<tr>
		<td><b>Task Priority</b></td>
		<c:choose>
   			<c:when test="${individualTask.getPriority()==1}">
   			<td style="background-color: #FB6E6E">Very Critical</td>
   			</c:when>
   			<c:when test="${individualTask.getPriority()==2}">
   			<td style="background-color: #FB946E">Critical</td>
   			</c:when>
   			<c:when test="${individualTask.getPriority()==3}">
   			<td>Valuable</td>
   			</c:when>
   			<c:when test="${individualTask.getPriority()==4}">
   			<td class="success">Desirable</td>
   			</c:when>
   		</c:choose>
	</tr>
	<tr>
		<td><b>Creation date</b></td>
		<td>${individualTask.getDateOfCreation()}</td>
	</tr>
	<tr>
		<td><b>Task Status</b></td>
		<c:choose>
   			<c:when test="${individualTask.getTaskStatus()==1}">
   			<td>Created</td>
   			</c:when>
   			<c:when test="${individualTask.getTaskStatus()==2}">
   			<td>In Progress</td>
   			</c:when>
   			<c:when test="${individualTask.getTaskStatus()==3}">
   			<td>Completed</td>
   			</c:when>
   		</c:choose>
	</tr>
	<tr>
		<td><b>Logged Status</b></td>
		<c:choose>
   			<c:when test="${individualTask.isLogged()==false}">
   			<td>Current</td>
   			</c:when>
   			<c:when test="${individualTask.isLogged()==true}">
   			<td>Logged</td>
   			</c:when>   			
   		</c:choose>
	</tr>
	<tr>
		<td><b>Comments</b></td>
		<td>${individualTask.getComments()}</td>
	</tr>
	</table>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
    <button type="button" class="btn btn-primary">Save changes</button>
</div> 