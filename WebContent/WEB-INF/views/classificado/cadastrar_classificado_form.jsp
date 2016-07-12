<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Classificado</title>
</head>
<body>
<a href="paginaPrincipal"> Pagina Principal</a> <br />
<form action="cadastrarClassificado" method="post">
	Titulo: <input  type="text" name="titulo" required="required"/><br/>
	Texto: <br />
	<textarea name="texto" rows="5" cols="30"></textarea> <br />
	
	Preço: <input name="preco" type="number" step="0.01" min="0" required="required"/><br/>
	
	Telefone: <input type="tel" name="telefone" pattern="[0-9]{2} [0-9]{1} [0-9]{4}-[0-9]{4}"
			 placeholder="00 0 0000-0000" required="required"/><br/>
	
	Melhor Oferta: <input type="number" name="melhorOferta" required="required"/><br/>
	<input type="hidden" name="login" value="${usuario_logado.login}">	
	<input type="submit" value="Enviar" />
</form>

</body>
</html>