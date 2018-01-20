<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ include file="include/begin.jsp"%>

<legend><h2>Home page</h2></legend>
<body>
<div class="col-md-4"></div>
<div class="col-sm-2">
	<label>Projects</label></br>
	<c:forEach items="${allProjects}" var="project">
		<div class="col-md-2">
			<a href="/TaskTracker/projects/${project.name}">${project.name}</a>
		</div></br></br>
	</c:forEach>
</div>
<%@ include file="include/end.jsp" %>
