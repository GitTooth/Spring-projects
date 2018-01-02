<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="by.sender.tooth.model.Roles" %>
<%@ include file="include/begin.jsp"%>
<title>Registration</title>
<legend><h2>Registration</h2></legend>

<c:set var="listOfRoles" value="<%=Roles.getList()%>" />

<form:form action="http://localhost:8080/TaskTracker/registration/registerNewUser" modelAttribute="user">
	<div class="col-md-5"></div>
	<div class="col-md-2">
		<label>Username</label></br>
        <form:input cssClass="form-control" path="username"/>
        <form:errors path="username" cssClass="error"/>
    </div>
	</br></br></br></br>
	<div class="col-md-5"></div>
	<div class="col-md-2">
		<label>Password</label>
        <form:input cssClass="form-control" path="password"/>
        <form:errors path="password" cssClass="error"/>
    </div>
       </br></br></br></br>
       <div class="col-md-5"></div>
	<div class="col-md-2">
		<label>Email</label>
        <form:input cssClass="form-control" path="email"/>
        <form:errors path="email" cssClass="error"/>
    </div>
	</br></br></br></br>
	<div class="col-md-5"></div>
	<div class="col-md-2">
		<label>Role</label>
        <form:select multiple="single" path="authority" cssClass="btn btn-primary dropdown-toggle">
 					<form:option value="" />
 					<div class="dropdown-divider"></div>
 					<form:options items="${listOfRoles}"/>
		</form:select>
	</div>
	</br></br></br></br>
	<div class="col-md-6"></div>
	<button type="submit" class="btn">Register</button>
</form:form>
<%@ include file="include/end.jsp"%>