<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
                <div class="alert alert-danger" id="error">
  Indicates a dangerous or potentially negative action.
</div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: 0
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="dashboard" method="POST">
                        <input type="hidden" value="0" id="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input name="name" type="text" class="form-control" id="computerName" placeholder="Computer name" value=<c:out value = "${computerName}"/>>
                                <input type="text" class="form-control" id="id" placeholder="id" name="id" value=<c:out value = "${id}"/>>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input name="introduced" type="date" class="form-control" id="introduced" placeholder="Introduced date"value=<c:out value = "${introduced}"/>>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input name="discontinued" type="date" class="form-control" id="discontinued" placeholder="Discontinued date"value=<c:out value = "${discontinued}"/>>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="company" >
                                    <c:forEach items="${company}" var="list">
                                    	<option value=<c:out value = "${list.id}" />><c:out value = "${list.name}"/></option>
                                    </c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" name="Edit" onclick="Validate()" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
<script>
	document.getElementById("error").hidden = true;
	document.getElementById("id").hidden = true;
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