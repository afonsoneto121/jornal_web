<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Classificados</title>
</head>
<body>
<a href="paginaPrincipal"> Pagina Principal</a> <br />
<a href="lanceIndividual?id=${usuario_logado.idUsuario }"> Meus Lances</a> <br />

	<table border="1">
		<c:forEach var="n" items="${secoes}">
		<td>
			<h2> <a href="listarNoticiasSecao?id=${n.idSecao}"> ${n.titulo}</a> </h2> 
		</td>
		</c:forEach>
	</table>
	<br /> <br />
	
	<table border="1">
	<c:forEach var="n" items="${classificados}">
	<tr>
		<td> ${n.titulo} </td>
		<td> ${n.texto} </td>
		<td> ${n.preco} </td>
		<td> ${n.telefone} </td>
		<td> ${n.usuario.nome} </td>
		<c:if test="${n.vendido == 1}">			
			<td> O produto foi vendido </td>			
		</c:if>
		<c:if test="${n.vendido == 0}">			
			<td> Produto Disponivel </td>	
		</c:if>
		
		<td> <a href="inserirOfertaForm?id=${n.id}"> Inserir Oferta</a> </td>
		  
		<c:if test="${n.idUsuario == usuario_logado.idUsuario}">
			<td> <a  href="alterarClassificadoForm?id=${n.id}">Alterar</a> </td>
			<td> <a  href="apagarClassificado?id=${n.id}">Apagar</a> </td>
		</c:if>
		
		<c:if test="${n.idUsuario == usuario_logado.idUsuario  and not empty n.melhorOferta}">
			<td><a href="venderClassificado?id=${n.id}">Vender</a> </td>
		</c:if>
		
	</tr>
	</c:forEach>
</table>

</body>
</html>
