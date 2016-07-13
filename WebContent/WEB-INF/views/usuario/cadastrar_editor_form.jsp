<%@ page contentType="text/html; charset=ISO-8859-1" language="java"
 pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="paginaPrincipal"> Pagina Principal</a> <br />
<form action="cadastrarEditor" method="post" enctype="multipart/form-data">
	Nome: <input  type="text" name="nome" required="required"/><br/>
	Email: <input type="email" name="email" placeholder="teste@teste.com" required="required"/><br/>
	Login: <input type="text" name="login" required="required"/><br/>
	Senha: <input type="password" name="senha" required="required"/><br/>
	
	<input type="file" name="imagem"/> <br />
    <input type="submit" value="Enviar" />
</form>
</body>
</html>