<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="include/begin.jsp"%>

<legend><h2>Home page</h2></legend>
<body>
	<!--sec:authentication var="principal" property="principal.username" /-->
<div class="col-md-4"></div>
<div class="col-sm-2">
	<label>Projects</label></br>
	<c:forEach items="${allProjects}" var="project">
		<div class="col-md-2">
			<a href="/TaskTracker/projects/${project.name}">${project.name}</a>
		</div></br></br>
	</c:forEach>
</div>

<form:form action="/TaskTracker/projects/createproject" modelAttribute="project">
	<label>Create project</label></br>
	<form:input path="name"/>
	<form:errors path="name" cssClass="error"/>
	<button id="create" name="create" class="btn btn-success">
        Create
    </button>
</form:form>
<%@ include file="include/end.jsp" %>
