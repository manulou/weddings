<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en" data-ng-app="weddingsApp">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta name="keywords" content="weddings search, weddings abroad" />
	<meta name="description" content="Weddings Search - find your dream wedding!" />
	<title>Weddings Search</title>
	
	<link rel="shortcut icon" href="<c:url value="/resources/images/favicon.ico" />" type="image/x-icon">
	<link rel="icon" href="<c:url value="/resources/images/favicon.ico" />" type="image/x-icon">
	
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />" />
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

	<script src="<c:url value="/resources/js/jquery-2.1.3.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/js/angular.min.js" />"></script>
	<script src="<c:url value="/resources/js/scripts.js" />"></script>
</head>
<body>
	<div id="urlPrefix" style="display:none"><c:url value="/" /></div>
	
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="<c:url value="/" />">Home</a></li>
            <li><a href="<c:url value="/search" />">Search</a></li>
          </ul>
        </div>
      </div>
    </nav>