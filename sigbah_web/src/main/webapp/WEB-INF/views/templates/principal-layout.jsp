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
<body class="desktop-detected pace-done smart-style-3">

	<tiles:insertAttribute name="regionTop" flush="true" />
	
	<tiles:insertAttribute name="regionLeft" flush="true" />
	
	<tiles:insertAttribute name="regionScripts" flush="true" />
		
	<!-- #MAIN PANEL -->
	<div id="main" role="main">
	
		<tiles:insertAttribute name="regionBody" />

	</div>
	<!-- END #MAIN PANEL -->
	
	<tiles:insertAttribute name="regionBottom" flush="true" />
		
</body>
</html>