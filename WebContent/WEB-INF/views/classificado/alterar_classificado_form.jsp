<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<a href="paginaPrincipal"> Pagina Principal</a> <br />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Classificado</title>
</head>
<body>
<table border="1">
		<c:forEach var="n" items="${secoes}">
		<td>
			<h2> <a href="listarNoticiasSecao?id=${n.idSecao}"> ${n.titulo}</a> </h2> 
		</td>
		</c:forEach>
	</table>
	<br /> <br />
	
<form action="alterarClassificado" method="post">
	Titulo: <input  type="text" name="tituloClassificado" required="required" value="${classificado.titulo}" /><br/>
	Texto: <br />
	<textarea name="textoClassificado" rows="5" cols="30" >${classificado.texto}</textarea> <br />
	
	Preço: <input name="precoClassificado" type="number" step="0.01" min="0" required="required" value="${classificado.preco}" /> <br/>
	
	Telefone: <input type="tel" name="telefoneClassificado" pattern="[0-9]{2} [0-9]{1} [0-9]{4}-[0-9]{4}"
			 placeholder="00 0 0000-0000" required="required" value="${classificado.telefone}"/><br/>
	
	<input type="hidden" name="id_classificado" value="${classificado.id}"/>
	
	<input type="submit" value="Alterar" />
</form>
</body>
</html>