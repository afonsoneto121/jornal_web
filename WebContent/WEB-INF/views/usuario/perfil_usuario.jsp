<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Perfil</title>
</head>
<body>
	<a href="paginaPrincipal"> Pagina Principal</a> <br />

	<img alt="${usuario.nome}"  
			src=" <c:url value="/resources/images/${usuario.login}.png " /> " /> <br />
	
	Nome: ${usuario.nome} <br />
	Email: ${usuario.email} <br />
	Login: ${usuario.login} <br />
	Papel: ${usuario.nomePapel} <br />
	
	<a href="apagarUsuario?id=${usuario.idUsuario}">Apagar Conta</a>
	<a href="editarUsuarioForm?id=${usuario.idUsuario}">Editar </a>
	<a href="alterarSenhaForm?id=${usuario.idUsuario}">Alterar Senha </a>
	
	
</body>
</html>