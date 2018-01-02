<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/begin.jsp" %>

<c:set var="project" value="${project}" />
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>${project.name}</title>
</head>
<body>
<div class="container-fluid text-center">   
	<div class="row content">
	    <div class="col-sm-2 sidenav">
	        <sec:authorize access="hasRole('ROLE_MANAGER')">	
			<h4>Free developers</h4>
			<div class="form-group col-md-4">
				<table class="table table-striped">
					<c:forEach items="${freeDevelopers}" var="developers">
						<tr>
							<form:form action="/TaskTracker/projects/${project.name}/assign" modelAttribute="developer">
								<form:hidden path="username" value="${developers.username }"/>
								<td>${developers.username}</td>
								<td><button id="assign" class="btn btn-active">Assign</button></td>
							</form:form>
						</tr>
					</c:forEach>
				</table>
			</div>
			</sec:authorize>
		</div>
		<div class="col-sm-8 text-left"> 
			<legend><h2>Project: ${project.name}</h2></legend>
			<div class="col-sm-4">
				<table style="width:80%">
					<tr>
						<td><label>Task</label></td>
						<td><label>Status</label></td>
					</tr>
					<c:forEach items="${tasks}" var="task">
						<tr>
							<td><a href="/TaskTracker/projects/${project.name}/${task.name}">${task.name}</a></td>
							<td>${task.status}</td>
						</tr>
					</c:forEach>
				
					<sec:authorize access="hasRole('ROLE_DEVELOPER')">
						<tr>
							<td><label>Current</label></td> 
						</tr>
						<c:forEach items="${userTasks}" var="task">
							<tr>
								<td><a href="/TaskTracker/projects/${project.name}/${task.name}">${task.name}</a></td>
								<td>${task.status}</td>
							</tr>
						</c:forEach>
					</sec:authorize>
				</table>
			</div>
			<div class="col-sm-4 text-left">
				<h4>Add task</h4>
				<form:form action="/TaskTracker/projects/${project.name}/addTask" modelAttribute="task">
					<label>Title: </label></br>
					<form:input path="name"/></br>
					<form:errors path="name" cssClass="error"/>
					<label>Description: </label></br>
					<form:textarea path="description" rows="6" cols="75" style="border-radius:7px; 
						border-color:black; border-width:2px"/></br>
					<form:errors path="description" cssClass="error"/>
					<td><button id="addTask" class="btn btn-active">Create</button></td>
				</form:form>
			</div>
		</div>
		<div class="col-sm-2 sidenav">
			<h4>Assigned developers</h4>
			<div class="form-group col-md-4">
				<table class="table table-striped">
					<c:forEach items="${assignedDevelopers}" var="developers">
						<tr>
							<form:form action="/TaskTracker/projects/${project.name}/unsign" modelAttribute="developer">
								<form:hidden path="username" value="${developers.username }"/>
								<td>${developers.username}</td>
								<sec:authorize access="hasRole('ROLE_MANAGER')">
									<td><button id="unsign" class="btn btn-active">Unsign</button></td>
								</sec:authorize>
							</form:form>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>

</body>

<%@ include file="include/end.jsp" %>