<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserir Oferta</title>
</head>
<body>
<a href="paginaPrincipal"> Pagina Principal</a> <br />

Titulo :${classificado.titulo} <br />
Descrição : ${classificado.texto} <br />
Preço Inicial: ${classificado.preco} <br />
Melhor Oferta: ${classificado.melhorOferta} <br />
Autor da Melhor Oferta: ${autor.nome} <br />
<form action="inserirOferta" method="post">

	<input type="hidden" name="id_classificado" value="${classificado.id}">
	<input type="hidden" name="id_usuario" value="${usuario_logado.idUsuario}"/>
	<label for="ofert">Inserir Oferta</label> <br /> 
	<input id="ofert" type="number" step="0.01" min="0" name="oferta" required="required"/>
	<input type="submit" value="Enviar"/>

</form>

<c:if test="${empty usuario_logado}">
	<h5 class="text-center"><strong>Logue para inserir uma oferta nesse classificado</strong></h5>
</c:if>
<c:if test="${classificado.vendido == 1}">
	<h5 class="text-center"><strong>O produto foi vendido</strong></h5>
</c:if>
</body>
</html>