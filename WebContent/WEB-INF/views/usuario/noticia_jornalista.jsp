<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apagar Noticia</title>
</head>
<body>
<table border="1">
	<c:forEach var="n" items="${secoes}">
	<td>
		<h2> <a href="listarNoticiasSecao?id=${n.idSecao}"> ${n.titulo}</a> </h2> 
	</td>
	</c:forEach>
</table>
	<br /> <br />
<table border="1">
	<c:forEach var="n" items="${noticias}">
	<tr>
		<td>  ${n.titulo} </td>
		<td>  <a href="apagarNoticia?id=${n.idNoticia}">Excluir</a>  </td> 
	</tr>
	</c:forEach>
</table>
</body>
</html>