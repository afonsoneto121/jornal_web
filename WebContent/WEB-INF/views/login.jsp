<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h2>Efetuar Login</h2>
	<form action="login" method="post">
		Login: <input type="text" value="${usuario_login.login}" name="login"  /> <br />
		Senha: <input type="password" name="senha" /> <br />
		Papel: <select name="nomePapel">
					<option>Leitor</option>
					<option>Jornalista</option>
					<option>Editor</option>
				</select>
		<input type="submit" value="ENVIAR" /> 
	</form>
	<a href="recuperarSenha"> Esqueci minha senha</a>
</body>
</html>