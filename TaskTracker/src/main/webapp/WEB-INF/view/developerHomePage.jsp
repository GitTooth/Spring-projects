<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/begin.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<legend><h2>Home page</h2></legend>

	<!--sec:authentication var="principalName" property="principal.username" /-->
	<c:set var = "project" value = "${userProject}"/>
	<div class="col-md-12">
		<h4><label>Current projects:</label></h4>
			<c:forEach items="${userProjects}" var="project">
				<div class="col-md-2">
					<a href="/TaskTracker/projects/${project.name}">${project.name}</a>
				</div></br></br>
			</c:forEach>
	</div>
	
<%@ include file="include/end.jsp" %>
