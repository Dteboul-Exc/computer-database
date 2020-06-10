<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Computer Database</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
<!-- <script src="./js/addComputerValidator.js"></script>-->
</head>

<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
        <div class="alert alert-danger" id="error">
  Indicates a dangerous or potentially negative action.
</div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form action="dashboard" name="form" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name="name" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="company" >
                                    <c:forEach items="${company}" var="list">
                                    	<option value=<c:out value = "${list.id}" />><c:out value = "${list.name}" /></option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" name="Add" id="Add" onclick="Validate()" class="btn btn-primary">
                            or
                            <a href="dashboard.html" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
<script>
	document.getElementById("error").hidden = true;
	function Validate() {
		  var name,introduced,discontinued;

		  // Get the value of the input field with id="numb"
		  name = document.getElementById("computerName").value;
		  introduced = document.getElementById("introduced").value;
		  discontinued = document.getElementById("discontinued").value;
		  if (name.trim() == "" || name == null) {

		    document.getElementById("error").hidden = false;
		    document.getElementById("error").innerHTML = "name of the Computer was not inserted"; 
		    event.preventDefault()
		  } else if ((introduced.trim() > discontinued.trim()) && (discontinued != null && discontinued != "") ) {
			    document.getElementById("error").hidden = false;
			    document.getElementById("error").innerHTML = "discontinued must be after introduced"; 
			    event.preventDefault()				
		  }	else if ((introduced.trim() == "" || introduced == null) && (discontinued != null && discontinued != "") ) {
			    document.getElementById("error").hidden = false;
			    document.getElementById("error").innerHTML = "introduced was not 'introduced' while discontinued was not 'discontinued'"; 
			    event.preventDefault()				
		  }
		 
		}
	
</script>
</html>