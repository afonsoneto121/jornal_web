<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Noticias</title>
</head>
<body>
<a href="paginaPrincipal"> Pagina Principal</a> <br />
	<a href="paginaPrincipal"> Pagina Principal</a> <br />

	<form action="listaNoticiaTitulo" method="post">
		Titulo: <input type="text" name="titulo"/>
		<input type="submit" values="ENVIAR"/>
	</form>
	
	<table border="0">
	<c:forEach var="n" items="${noticiasDel}">
	<tr>
		<td> <h4> <a href="verNoticia?id=${n.idNoticia}"> ${n.titulo} </a></h4> </td>
		<td> <h4> <a href="apagarNoticia?id=${n.idNoticia}"> APAGAR </a></h4> </td>  
	</tr>
	</c:forEach>
</table>
	
</body>
</html>