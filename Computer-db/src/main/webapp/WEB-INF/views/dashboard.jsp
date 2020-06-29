<!DOCTYPE html>

<html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="spring"%>
<head>
<title><spring:message code="CDB.projectName"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"> <spring:message code="CDB.projectName"/> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<spring:message code="CDB.computerFound"/><% out.println((String)request.getAttribute("computer"));%>
			</h1>
			<div class="alert alert-danger" id="error">${errormsg}</div>


			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">

				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a
							href="http://localhost:8080/excilys/dashboard?page=${currentplace}&recordsPerPage=${recordsPerPage}&Order=computer&currentplace=${currentplace}">
								Computer name &#8595; </a></th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th><a
							href="http://localhost:8080/excilys/dashboard?page=${currentplace}&recordsPerPage=${recordsPerPage}&Order=company&currentplace=${currentplace}">
								Company </a></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->

				<tbody id="results">
					<c:forEach items="${Computer_list}" var="cc" varStatus="loop">
						<tr>

							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${cc.id}"></td>
							<td><a href="#" onclick="GoTO(${cc.id})"><c:out
										value="${cc.name}" /></a></td>
							<td><c:out value="${cc.introduced}" /></td>
							<td><c:out value="${cc.discontinued}" /></td>
							<td><c:out value="${cc.company.getName()}" /></td>


						</tr>

					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${min_button >1}">
					<li>
					<li><a
						href="http://localhost:8080/excilys/dashboard?page=${currentplace-1}&recordsPerPage=${recordsPerPage}&Order=${Order}&currentplace=${currentplace-1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:forEach var="i" end="${max_button}" begin="${min_button}"
					varStatus="loop">
					<li><a
						href="http://localhost:8080/excilys/dashboard?page=${i}&recordsPerPage=${recordsPerPage}&Order=${Order}&currentplace=${i}">${i}
					</a></li>
				</c:forEach>

				<c:if test="${max_button != min_button + 1}">
					<li><a
						href="http://localhost:8080/excilys/dashboard?page=${currentplace+1}&recordsPerPage=${recordsPerPage}&Order=${Order}&currentplace=${currentplace+1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
				</c:if>

			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default"
					onclick="SetNumberofPage(1,10)">10</button>
				<button type="button" class="btn btn-default"
					onclick="SetNumberofPage(1,50)">50</button>
				<button type="button" class="btn btn-default"
					onclick="SetNumberofPage(1,100)">100</button>
			</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>
	<script>function GoTO(id)
{
	window.location.href = "editComputer?id=" + id;
	}
function SetNumberofPage(page,rpage)
{
	window.location.href = "dashboard?page=" + page + "&recordsPerPage=" + rpage;
	}
function Navigate(page,rpage,pos)
{
	window.location.href = "addComputer?page=" + page + "&recordsPerPage=" + rpage + "&currentplace=" + pos;
	}
	
	
</script>

</body>
<html />