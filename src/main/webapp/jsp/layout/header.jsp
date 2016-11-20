<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <base href="/">
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
        <link href="<c:url value="/resources/css/fotorama.css" />" rel="stylesheet">

        <script src="/resources/node_modules/core-js/client/shim.min.js"></script>
        <script src="/resources/node_modules/zone.js/dist/zone.js"></script>
        <script src="/resources/node_modules/reflect-metadata/Reflect.js"></script>
        <script src="/resources/node_modules/systemjs/dist/system.src.js"></script>
        <!-- 2. Configure SystemJS -->
        <script src="/resources/systemjs.config.js"></script>
        <script>
          System.import('resources/app').catch(function(err){ console.error(err); });
        </script>

        <script src="<c:url value="/resources/js/jquery-2.1.3.min.js" />"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/js/masonry.pkgd.min.js" />"></script>
        <script src="<c:url value="/resources/js/fotorama.js" />"></script>
        <script src="<c:url value="/resources/js/scripts.js" />"></script>
    </head>
<body>