<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/custom.js"></script>
<script type="text/javascript">
$(document.body).on('hidden.bs.modal', function () {
    $('#taskDetail').removeData('bs.modal');    
});

function selectBoxValue(idOfSelectBox, value) {
	//alert(value);
	//alert(idOfSelectBox);
	var selectBox = document.getElementById(idOfSelectBox);
	var options = selectBox.options;
	for(var i = 0, length = options.length;i < length;i++) {
		if(options[i].value == value) {
			selectBox.selectedIndex = i;
			//alert(i);
			return true;
		}
	}
	return false;
}

</script>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h4 class="modal-title">Task Details</h4>
</div>
<script type="text/javascript" >
	selectBoxValue('priorityModal',${individualTask.getPriority()})
</script>
<div class="modal-body">
    <table class="table table-hover">
	<tr>
		<td><b>Task Summary</b></td>
		<td><textarea class="form-control" id="summaryInModal" >${individualTask.getTaskSummary()}</textarea></td>		
	</tr>
	<tr>
		<td><b>Task Priority</b></td>
		<%-- <c:choose>
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
   		</c:choose>--%>
   		<td>
   			<select class="form-control" id="priorityModal">
   				<option value="1">Very Critical</option>
		      	<option value="2">Critical</option>
		      	<option value="3">Valuable</option>
		      	<option value="4">Desirable</option>     
   			</select>
   		</td>
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
		<td>
			<textarea class="form-control" id="commentInModal" >${individualTask.getComments()}</textarea>
		</td>
	</tr>
	</table>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="updateTask(${individualTask.getTaskId()})">Save changes</button>
</div> 