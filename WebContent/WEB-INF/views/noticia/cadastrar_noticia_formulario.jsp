<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java"
 pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
    
<!DOCTYPE html>

<html lang="pt-br">
	<head>
	   <meta charset="Content-Type: text/html; charset=UTF-8">
		<title>Noticia</title>
	</head>
	
	<body>
	<a href="paginaPrincipal"> Pagina Principal</a> <br />
		<form action="cadastrarNoticia" method="post" enctype="multipart/form-data">
			Titulo: <input  type="text" name="titulo" required="required"/><br/>
			Sub Titulo: <input type="text" name="subTitulo" required="required"/><br/>
			Texto:
				<textarea name="texto" rows="50" cols="100 "></textarea> <br />
			
			Seção: 
			<select name="secaoTitulo">
				<c:forEach var="s" items="${secoes}">
					<option>${s.titulo}</option>
				</c:forEach>
			</select>
			<input type="file" name="imagem"/>
		    <input type="hidden" name="login" value="${usuario_logado.login}"/>		
		    <input type="submit" value="Enviar" />
		</form>
	</body>
</html>