<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en-us">
<head>
	<meta charset="utf-8">
	<title>Sistema Sigbah</title>
	<meta name="description" content="">
	<meta name="author" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
	
	<tiles:insertAttribute name="regionResources" flush="true" />
</head>
<body class="animated fadeInDown">

	<tiles:insertAttribute name="regionScripts" flush="true" />

	<tiles:insertAttribute name="regionBody" />
	
</body>
</html>
