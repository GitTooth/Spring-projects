<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/begin.jsp"%>
<!DOCTYPE HTML>
<head>
<title>Login</title>
</head>
<legend>Login</legend>
<c:url value="/login" var="loginProcessingUrl" />
<form action="${loginProcessingUrl}" method="post">
	<fieldset>
		<!-- Form Name -->

		<c:if test="${param.error != null}">
			<div>
				Failed to login.
				<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                  Reason: <c:out
						value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</c:if>
			</div>
		</c:if>
		<c:if test="${param.logout != null}">
			<div>You have been logged out.</div>
		</c:if>
		</br>
		<!-- Text input-->
		<div class="form-group">
			<div class="col-md-4">
				<label for="username">Username</label> <input type="text"
					id="username" name="username" />
			</div>
		</div>
		</br>
		<!-- Password input-->
		<div class="form-group">
			<div class="col-md-4">
				<label for="password">Password</label> <input type="password"
					id="password" name="password" />
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> </br>
		<!-- Button -->
		<div class="form-group">
			<label class="col-md-4 control-label" for="submitbutton"></label>
			<div class="col-md-4">
				<div>
					<button type="submit" class="btn">Log in</button>
				</div>
			</div>
		</div>
	</fieldset>
</form>

<%@ include file="include/end.jsp" %>


