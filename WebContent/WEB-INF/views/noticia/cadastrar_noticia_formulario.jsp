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
	<form action="cadastrarNoticia" method="post">
	Titulo: <input  type="text" name="titulo" required="required"/><br/>
	Sub Titulo: <input type="text" name="subTitulo" required="required"/><br/>
	Texto:
		<textarea name="texto" rows="70" cols="100 "></textarea> <br />
	
	Seção: 
	<select name="secaoTitulo">
		<c:forEach var="s" items="${secoes}">
			<option>${s.titulo}</option>
		</c:forEach>
	</select>
    <input type="hidden" name="login" value="${usuario_logado.login}"/>		
    <input type="submit" value="Enviar" />
</form>
</body>
</html>