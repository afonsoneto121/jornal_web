<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Noticias Por Seção</title>
</head>
<body>
<a href="paginaPrincipal"> Pagina Principal</a> <br />
<table border="1">
	<c:forEach var="n" items="${secoes}">
	<td>
		<h2> <a href="listarNoticiasSecao?id=${n.idSecao}"> ${n.titulo}</a> </h2> 
	</td>
	</c:forEach>
	<br />
	Seção Atual: ${secao.titulo}
	<br />
	<table border="1">
	<c:forEach var="n" items="${noticias}">
	<tr>
		<td> ${n.titulo} </td>
		<td> ${n.subTitulo} </td>
		<td> <a href="direcionarNoticia?id=${n.idNoticia}"> Ver</a> </td>
	</tr>
	</c:forEach>
</table>
	
</table>
</body>
</html>