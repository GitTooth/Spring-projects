<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
<title>Main page</title>

<style>
.error {
	color: red
}

.container-table {
	display: table;
}

.horizontal-center-row {
	display: table-cell;
	horizontal-align: middle;
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
</head>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>

<body>
	<ul class="nav nav-pills">
		<li role="presentation" class="active"><a href="/SpringCompany">Home</a></li>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li role="presentation"><a href="adminArea">Admin area</a></li>
		</sec:authorize>	
		
	</ul>
	
	
	
	
	
	
	
	
	
	
	