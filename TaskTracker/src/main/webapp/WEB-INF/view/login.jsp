<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/begin.jsp"%>
<title>Login</title>

<legend><h2>Login</h2></legend>
<c:url value="/login" var="loginProcessingUrl" />
<form action="${loginProcessingUrl}" method="post">
	<c:if test="${param.error != null}">
		<div class="col-md-5"></div>
		<div>
			Failed to login.
			<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                 Reason: <c:out
					value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
			</c:if>
		</div>
		</br>
	</c:if>
	<c:if test="${param.logout != null}">
		<div class="col-md-5"></div>
		<div>You have been logged out.</div>
		</br>
	</c:if>
	<div class="col-md-5"></div>
	<div class="col-md-4">
		<label for="username">Username</label></br>
		<input type="text" id="username" name="username" />
	</div>
	</br></br></br>
	<div class="col-md-5"></div>
	<div class="col-md-4">
		<label for="password">Password</label></br>
		<input type="password" id="password" name="password" />
	</div>
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</br></br></br></br>
	<div class="col-md-5"></div>
	<div class="col-md-4">
		<button type="submit" class="btn">Log in</button>
	</div>
</form>
<%@ include file="include/end.jsp" %>


