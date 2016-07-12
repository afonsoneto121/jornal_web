<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Noticia</title>
</head>
<body>
<a href="paginaPrincipal"> Pagina Principal</a> <br />
<table border="1">
	<c:forEach var="n" items="${secoes}">
	<td>
		<h2> ${n.titulo} </h2> 
	</td>
	</c:forEach>
</table>
	<h1>${noticia.titulo}</h1> <br />
	<h3>${noticia.subTitulo}</h3> <br />
	
	${noticia.texto} <br />
	<br /><br />
	<table border="1">
	<c:forEach var="n" items="${comentario}">
	<tr>
		<td> ${n.texto} </td>
		<td> ${n.usuario.nome} </td>
	</tr>
	</c:forEach>
</table>
<br />
<c:if test="${not empty usuario_logado}">
	<form action="inserirComentario" method="post">
			<input type="text" name="texto" required="required"/> <br />
			<input type="hidden" name="login" value="${usuario_logado.login}">
			<input type="hidden" name="id" value="${noticia.idNoticia}">
			<input type="submit" value="Comentar">
	</form>
</c:if>
<c:if test="${empty usuario_logado}">
	<p class="text-center"><strong>Logue para comentar essa noticia</strong> <a href="loginFormulario">Login</a></p>
</c:if>

</body>
</html>