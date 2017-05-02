<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>My Tasks</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
  <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">  
  <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>  
  <script src="js/bootstrap-datetimepicker.min.js"></script>
  <script src="js/custom.js"></script>
</head>
<body>
	<center><img src="css/pacemaker.png" width="300" height="300"></center>
	
	<form:form class="form-horizontal" role="form" action="/TodoList/login" method="POST">
	<center>
	<div class="form-group">
    	<div class="input-group col-sm-2">	
	      <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
	      <input type="text" class="form-control" name="username" id="username" placeholder="Username"/>
	    </div>
    </div>
	<div class="form-group">
	  <div class="input-group col-sm-2">
	    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
	    <input class="form-control" name="password" id="password" type="password" placeholder="Password"/>
	  </div>
	</div>
  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<div class="form-group">        
	    <div class="input-group col-sm-2">
	      <button type="submit" class="btn btn-info btn-block">Login</button>
	  	</div>
	</div>
    </center>
</form:form>
<center><p>
&copy 2017 karthikps
</p></center>
</body>
</html>