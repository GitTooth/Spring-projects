<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="by.sender.boot.entities.TaskStatus"%>
<%@ include file="include/begin.jsp"%>

<sec:authentication var="principalName" property="principal.username" />
<c:set var="listOfStatuses" value="<%=TaskStatus.getList()%>" />
<c:set var="project" value="${project}" />
<c:set var="task" value="${task}" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${task.name}</title>
</head>
<body>
	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<h4>Developers in project</h4>
				<div class="form-group col-md-4">
					<table class="table table-striped">
						<c:forEach items="${developersInProject}" var="developers">
							<tr>
								<form:form
									action="/TaskTracker/projects/${project.name}/${task.name}/assign"
									modelAttribute="developer">
									<form:hidden path="id" value="${developers.id }" />
									<td>${developers.username}</td>
									<sec:authorize access="hasRole('ROLE_MANAGER')">
										<td><button id="assign" class="btn btn-active">Assign</button></td>
									</sec:authorize>
								</form:form>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div class="col-sm-8 text-left">
				<legend><h2>Task: ${task.name}</h2></legend>
				<div style="width: 50%; position: relative;">
					<h3>Description</h3>
					<form:form
						action="/TaskTracker/projects/${project.name}/${task.name}/editTask"
						modelAttribute="task">
						<form:hidden path="id" value="${task.id}" />
						<form:textarea path="description" rows="6" cols="40"
							style="border-radius:7px; 
							border-color:black; border-width:2px"
							value="${task.description}" />
						<form:errors path="description" cssClass="error" />
						</br>
						<div style="position: absolute; bottom: 10%; right: 3%;">
							<form:select multiple="single" path="status"
								cssClass="btn btn-primary dropdown-toggle">
								<form:option value="${task.status}" />
								<div class="dropdown-divider"></div>
								<form:options items="${listOfStatuses}" />
							</form:select>
							</br>
							<button id="editTask" class="btn btn-active">Save
								changes</button>
						</div>
					</form:form>
				</div>
				<h3>Comments:</h3>
				<div class="row">
					<c:forEach items="${task.commentList}" var="comment">
						<div class="col-sm-3"
							style="border: solid 1px #6b6b47; border-radius: 5px">
							<p>
								<b>${comment.user.username}</b>
							</p>
							<c:choose>
								<c:when test="${principalName == comment.user.username}">
									<form:form
										action="/TaskTracker/projects/${project.name}/${task.name}/editComment"
										modelAttribute="comment">
										<form:hidden path="id" value="${comment.id}" />
										<form:input path="text" value="${comment.text}" />
										<form:errors path="text" cssClass="error" />
										<button id="editComment" class="btn btn-active">Edit</button>
									</form:form>
									<form:form
										action="/TaskTracker/projects/${project.name}/${task.name}/removeComment"
										modelAttribute="comment">
										<form:hidden path="id" value="${comment.id}" />
										<button id="removeComment" class="btn btn-danger">Remove</button>
									</form:form>
								</c:when>
								<c:otherwise>
									<textarea readonly rows="6" cols="20" style="border-width: 0">${comment.text }</textarea>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
				</div>

				<h5>Add comment</h5>
				<form:form
					action="/TaskTracker/projects/${project.name}/${task.name}/addComment"
					modelAttribute="comment">
					<form:hidden path="user.username" value="${principalName }" />
					<form:input path="text" />
					<td><button id="addComment" class="btn btn-active">Comment</button></td>
				</form:form>
			</div>
			<div class="col-sm-2 sidenav">
				<h4>Assigned developers on task</h4>
				<div class="form-group col-md-4">
					<table class="table table-striped">
						<c:forEach items="${assignedDevelopersOnTask}" var="developers">
							<tr>
								<form:form
									action="/TaskTracker/projects/${project.name}/${task.name}/unsign"
									modelAttribute="developer">
									<form:hidden path="id" value="${developers.id }" />
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

<%@ include file="include/end.jsp"%>