<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Meus Lances</title>
</head>
<body>
<a href="paginaPrincipal"> Pagina Principal</a> <br />
<table border="1">
	<c:forEach var="n" items="${classiicados}">
	<tr>
		<td> ${n.titulo} </td>
		<td> ${n.preco} </td>
		<td> <a href="confirmarComprar?id=${n.id}"> Confirmar Comprar</a> </td>
	</tr>
	</c:forEach>
</table>

</body>
</html>