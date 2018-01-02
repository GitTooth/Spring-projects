<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.spring.tooth.model.Roles" %>
<%@ include file="include/begin.jsp" %>

<c:set var="listOfRoles" value="<%=Roles.getList()%>" />

<table border="0" id="userTable">
<font size = "4">
  <tr>
    <th><div class=col-md-2>Username</div></th>
    <th><div class=col-md-2>Role</div></th>
  </tr>
</font>

<sec:authentication property="principal.username" var="principal"/>
<c:forEach items="${authorities}" var="authority">
	<c:if test="${authority.username != principal}">
		<tr>
			<form:form action="http://localhost:8080/SpringCompany/updateUser" modelAttribute="authority">
			    <td>
			        <div class="col-md-12">
			            <form:input cssClass="form-control" path="username"
			                   value="${authority.username}"/>
			        </div>
	       		</td>
			    <td>    
			        <div class="col-md-2">
				        <form:select multiple="single" path="authority" cssClass="btn btn-primary dropdown-toggle">
		   					<form:option value="${authority.getAuthority()}" />
		   					<div class="dropdown-divider"></div>
		   					<form:options items="${listOfRoles}"/>
						</form:select>
					</div>
		        </td>
		        <td>
			        <div class="col-md-2">
			            <button id="Update" name="Update" class="btn btn-success">
			                Save
			            </button>
			        </div>
		        </td>
			        <div class="col-md-1" style="visibility: hidden">
			            <form:input cssClass="form-control" path="id"
			                   value="${authority.id}"/>
			        </div>
			        
		        
	    	</form:form>
	    	<td>
		    	<form:form action="http://localhost:8080/SpringCompany/deleteUser" modelAttribute="authority">
			        <div class="col-md-2">
			        	<form:hidden cssClass="form-control" path="id" value="${authority.id}"/>
			        	<form:hidden cssClass="form-control" path="username" value="${authority.username}"/>
			        	<form:hidden cssClass="form-control" path="authority" value="${authority.authority}"/>
			            <button id="delete" name="delete" class="btn btn-danger" data-toggle="tooltip" 
			            	data-placement="right" title="Doesn't want to be on one row :(" onclick="return confirm('Are you sure?')">
			                Remove
			            </button>
			        </div>
		        </form:form>
	        </td>
	    </tr>
    </c:if>
</c:forEach>
</table>

</br>
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
<table border="0" id="userTable">
	<font size = "4">
	  <tr>
	    <th><div class=col-md-2></div></th>
	    <th><div class=col-md-2></div></th>
	  </tr>
	</font>
	<form:form action="http://localhost:8080/SpringCompany/createUser" modelAttribute="authority">
		<tr>
			<td>
				<form:input cssClass="form-control" path="username"/>
			</td>
			<td>
			<div class="col-md-2">
		        <form:select multiple="single" path="authority" cssClass="btn btn-primary dropdown-toggle">
	  					<form:option value="" />
	  					<div class="dropdown-divider"></div>
	  					<form:options items="${listOfRoles}"/>
				</form:select>
			</div>
			</td>
			<td>
				<button id="create" name="create" class="btn btn-danger" data-toggle="tooltip" 
			            	data-placement="right" title="Doesn't want to be on one row :(">
			                Create
			            </button>
			</td>
		</tr>
	</form:form>
	</table>
	
<%@ include file="include/end.jsp" %>