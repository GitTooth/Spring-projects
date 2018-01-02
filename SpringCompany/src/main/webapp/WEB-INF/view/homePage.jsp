<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="include/begin.jsp"%>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
	<!--sec:authentication property="principal.username" /-->
	<c:set var="user" value="${user}" />
	<c:set var="role" value="${role}" />
	<form:form action = "http://localhost:8080/SpringCompany/update" modelAttribute = "user">
		<form:hidden path="id" value="${user.id}"/>
		<form:hidden path="password" value="${user.password }"/>
		<form:hidden path="enabled" value="${user.enabled}"/>
		</br>
		<div class="form-group">
			<div class="col-md-4">
				<label for="username">Username</label>
				<form:input path = "username" size="60" maxlength="100" value = "${user.username}"/>
			</div>
		</div>
		</br>
		</br>
		<div class="form-group">
			<div class="col-md-4">
				<label for="mail">Mail</label>
				</br>
				<form:input path = "mail" size="60" maxlength="100" value = "${user.mail}"/>
				<form:errors path="mail" cssClass="error"/>
			</div>
		</div>
		</br>
		</br>
		<div class="form-group">
			<div class="col-md-4">
				<label for="role">Role</label>
				<c:out value = "${role.authority}"/>
			</div>
		</div>
		</br>
		<div class="form-group">
			<input type="submit" class="btn btn-success" value="update" />
		</div>
	</form:form>
	<form action="logout" method="post">
		<input type="submit" class="btn btn-secondary" value="logout" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>



	
<%@ include file="include/end.jsp" %>
