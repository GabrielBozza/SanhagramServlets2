<%@page import="java.util.List"%>
<%@page import="bean.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="cabecalhoAdmin.jsp"></jsp:include>
</body>
<%
	//List<Usuario> lista = usuDAO.buscarTodos(usu);
	List<Usuario> listaResultado = (List<Usuario>) request.getAttribute("lista");
%>
<script>
	localStorage.setItem("modoApagarMensagens", "naoApagar");
</script>
<table border="1"
	style="font-family: Helvetica; font-size: 18px; margin-left: 270px; margin-top: 60px; padding: 0px;">
	<tr bgcolor="#eaeaea" style="height: 35px; padding: 5px;">
		<th style="padding: 10px;">Nome</th>
		<th style="padding: 10px;">Email</th>
		<th style="padding: 10px;">Senha</th>
		<th style="padding: 10px;">Data de Nascimento</th>
		<th style="padding: 10px;">Excluir</th>
		<th style="padding: 10px;">Alterar</th>
	</tr>
	<%
		for (Usuario u : listaResultado) {
	%>
	<tr style="height: 25px; padding: 5px;">
		<th style="padding: 10px;"><%=u.getNome()%></th>
		<th style="padding: 10px;"><%=u.getEmail()%></th>
		<th style="padding: 10px;"><%=u.getSenha()%></th>
		<th style="padding: 10px;"><%=u.getDatanasc()%></th>
		<th style="padding: 10px;"><a
			href="UsuarioControlador?acao=ex&nomeusu=<%=u.getNome()%>"
			style="color: red; text-decoration: none;">Excluir</a>
		<th style="padding: 10px;"><a
			href="UsuarioControlador?acao=alt&nomeusu=<%=u.getNome()%>"
			style="color: blue; text-decoration: none;">Alterar</a>
	</tr>
	<%
		}
	%>
</table>
</html>