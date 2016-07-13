<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Alterar Senha</title>
	
</head>
<body>
	<form action="alterarSenha" method="post">
		Senha Antiga: <input type="text" name="antiga"/> <br /> 		
		Nova Senha: <input type="text" name="primeiro"/> <br /> 
		Repita a Senha <input type="text" name="segundo" /> <br />
		<input type="hidden" name="id" value="${usuario.idUsuario}">
		<input type="submit" value="Enviar">	
	</form>
</body>
</html>