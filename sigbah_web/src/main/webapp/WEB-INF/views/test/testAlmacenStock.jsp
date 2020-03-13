<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Almacen Stock</title>
	
	<style>
		table {
		    border-collapse: collapse;
		    width: 100%;
		}
		
		table, td, th {
		    border: 1px solid black;
		    text-align: center;
		}
	</style>
	
</head>
<body>
	<br>
	<c:choose>	
		<c:when test="${empty base}">
			<table>
			  	<tr>
			    	<th>nroFila</th>
			    	<th>idDdi</th>
			    	<th>idAlmacen</th>
			    	<th>idProducto</th>
			    	<th>idCategoria</th>
			    	<th>nombreDdiAlmacen</th>
			    	<th>nombreCategoria</th>
			    	<th>nombreProducto</th>
			    	<th>cantidadProducto</th>
			    	<th>cantidadToneladas</th>
			  	</tr>		
				<c:forEach var="almacenStock" items="${listarAlmacenStock}">
				    <tr>
				    	<td>${almacenStock.nroFila}</td>
				    	<td>${almacenStock.idDdi}</td>
				    	<td>${almacenStock.idAlmacen}</td>
				    	<td>${almacenStock.idProducto}</td>
				    	<td>${almacenStock.idCategoria}</td>
				    	<td>${almacenStock.nombreDdiAlmacen}</td>
				    	<td>${almacenStock.nombreCategoria}</td>
				    	<td>${almacenStock.nombreProducto}</td>
				    	<td>${almacenStock.cantidadProducto}</td>
				    	<td>${almacenStock.cantidadToneladas}</td>
				  	</tr>	
				</c:forEach>			
			</table>
		</c:when>
		
		<c:otherwise>
            ${base.mensajeRespuesta}
        </c:otherwise>
	</c:choose>

</body>
</html>