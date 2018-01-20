<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<title>Main page</title>

<style>
.error {
	color: red
}

.sidenav {
    padding-top: 20px;
    background-color: #f1f1f1;
    height: 100%;
}

.row.content {height: 100%}

.container-table {
	display: table;
}

.horizontal-center-row {
	display: table-cell;
	horizontal-align: middle;
}
	
.leftform{
		position: absolute;
		left: 8%;
}

.navbar {
      margin-bottom: 0;
      border-radius: 0;
}

.footer {
	   position:fixed;
	   left:0px;
	   bottom:0px;
	   height:30px;
	   width:100%;
	   background:#999;
}
</style>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>

<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">TaskTracker</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/TaskTracker">Home</a></li>
        <li><a href="/TaskTracker/registration">Registration</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      	<sec:authorize access="hasAnyRole('ROLE_MANAGER', 'ROLE_DEVELOPER')">
      		<li><a href="#" onclick="javascript:logoutForm.submit();"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
	        <c:url var="logoutUrl" value="/logout" />
	        <form action="${logoutUrl}" method="post" id="logoutForm">
	            <input type="hidden" name="${_csrf.parameterName}"
	                value="${_csrf.token}" />
	        </form>
		</sec:authorize>
		<sec:authorize access="!hasAnyRole('ROLE_MANAGER', 'ROLE_DEVELOPER')">
        	<li><a href="/TaskTracker/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </sec:authorize>
      </ul>
    </div>
  </div>
</nav>
	
	
	
	
	
	
	
	
	
	
	
	
	