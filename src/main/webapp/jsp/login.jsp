<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta name="keywords" content="weddings search, weddings abroad" />
		<meta name="description" content="Weddings Search - find your dream wedding!" />
		<title>Weddings Search - Administrator login</title>

		<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />" type="image/x-icon">
		<link rel="icon" href="<c:url value="/resources/images/favicon.ico" />" type="image/x-icon">

		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

		<script src="<c:url value="/resources/js/jquery-2.1.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	</head>
    <script>
    	$(document).ready(function() {
    		if (window.location.search.indexOf('error') > -1) {
    		    $('#message-alert-error-id').show();
    		}
    		if (window.location.search.indexOf('logout') > -1) {
    		    $('#message-alert-success-id').show();
    		}
    	});
    </script>
    <body>
    	<div class="col-lg-2"></div>
        <div class="col-lg-8">      	
	        <h2>Login</h2>
        	<div id="message-alert-success-id" class="alert alert-success" style="display: none;" role="alert">Logout successful</div>
       		<div id="message-alert-error-id" class="alert alert-danger" style="display: none;" role="alert">Authentication failed</div>
	        <form method="post" action="login" id="search-form-id">
		        <div class="search panel panel-default">
		        	<div class="panel-body">
				        <div class="col-md-4">
			        		<div class="form-horizontal">
								<div class="form-group">
									<label for=username class="col-sm-3 control-label">Username</label>
									<div class="col-sm-9">
										<input id="username" name="username" type="text" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<label for=password class="col-sm-3 control-label">Password</label>
									<div class="col-sm-9">
										<input id="password" name="password" type="password" class="form-control"/>
									</div>
								</div>		        			
				        	</div>
				        	<p>
				        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				        		<input type="submit" class="btn btn-success" value="Login" />
				        	</p>
				        </div>
		        	</div>
		        </div>
			</form>
        </div>
        <div class="col-lg-2"></div>
    </body>
</html>
