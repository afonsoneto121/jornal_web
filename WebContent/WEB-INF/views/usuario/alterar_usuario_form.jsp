<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="editarUsuario" method="post">
		Nome: <input  type="text" name="nome" required="required" value="${usuario.nome }"/><br/>
		Email: <input type="email" name="email" placeholder="teste@teste.com" required="required"
								 value="${usuario.email }"/><br/>
		Login: <input type="text" name="login" required="required" value="${usuario.login }"/><br/>
		<input type="hidden" name="idUsuario" required="required" value="${usuario.idUsuario }"/>
		<input type="submit" value="Alterar"/>
	</form>
	
</body>
</html>