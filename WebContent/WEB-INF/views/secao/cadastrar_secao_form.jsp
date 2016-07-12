<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Seção</title>
</head>
<body>
<a href="paginaPrincipal"> Pagina Principal</a> <br />
<h2>Inserir Seção</h2>
<form action="cadastrarSecao" method="post">
	Título da seção: <input  type="text" name="titulo" required="required"/><br/>
 	Descrição da seção: <input  type="text" name="descricao" required="required"/><br/>
				
    <input type="submit" value="Enviar" />
</form>

	
</body>
</html>