<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="Content-Type: text/html; charset=UTF-8">
	
<title>Inicio</title>
</head>
<body>

	Usuario logado: ${usuario_logado.nome} , ${usuario_logado.nomePapel}<a href="logout">Sair</a> 
	<a href="areaDoEditor?id=${usuario_logado.nomePapel}">Area do Editor</a> 
	<a href="areaDoJornalista?id=${usuario_logado.nomePapel}">Area do Jornalista</a>  
	<a href="loginFormulario"> Efetuar Login</a>
	<a href="listarClassificados"> Classificados</a>
	<a href="pefilUsuario?id=${usuario_logado.idUsuario}"> Perfil</a> <br />
	<a href="cadastrarUsuarioFormulario"> Cadastrar Usuario</a>
	
	<br /> <br /> <br />
	
<table border="1">
	<c:forEach var="n" items="${secao_principal}">
	<td>
		<h2> <a href="listarNoticiasSecao?id=${n.idSecao}"> ${n.titulo}</a> </h2> 
	</td>
	</c:forEach>
</table>
	<br /> <br /> <br />
<table border="1">
	<c:forEach var="n" items="${noticias_principal}">
	<tr>
		<td> ${n.titulo} </td>
		<td> ${n.subTitulo} </td>
		<td> <a href="direcionarNoticia?id=${n.idNoticia}"> Ver</a> </td>
	</tr>
	</c:forEach>
</table>
	
</body>
</html>