<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
</head>
<body>
	<center><img src="css/pacemaker.png" width="300" height="300"></center>
	
	<form:form class="form-horizontal" role="form" action="/TodoList/login" method="POST">
	
<div class="form-group" style="align-items: center">
    <label class="control-label col-sm-offset-3 col-sm-2" for="username">Username:</label>
    <div class="col-sm-2">
      <input type="text" class="form-control" name="username" id="username"/>
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-offset-3 col-sm-2" for="password">Password:</label>
    <div class="col-sm-2">
      <input class="form-control" name="password" id="password" type="password"/>
    </div>
  </div>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <div class="form-group">        
      <div class="col-sm-offset-5 col-sm-2">
        <button type="submit" class="btn btn-default">Login</button>
      </div>
    </div>
    
</form:form>
	
</body>
</html>