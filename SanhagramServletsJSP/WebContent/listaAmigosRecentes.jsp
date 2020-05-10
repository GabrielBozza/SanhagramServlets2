<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista Amigos Recentes</title>
</head>
<body>
<jsp:include page="cabecalho.jsp"></jsp:include>
</body>
 	<script>localStorage.setItem("conversa","verconversa0");
 			localStorage.setItem("i","0");
 	</script>
<%

//List<Usuario> lista = usuDAO.buscarTodos(usu);
List<String> listaResultado = (List<String>)request.getAttribute("lista");
%>
<table border="1">
 <tr bgcolor="#eaeaea">
 	<th>Nome</th>
 	<th>Ver conversa</th>
 </tr>
<%
for(String s:listaResultado){
%>
<tr>
 	<th><%=s%></th>
 	<th><a id="verconversa">Ver</a>
 	<script>
 	localStorage.setItem("conversa",localStorage.getItem("conversa").substring(0,localStorage.getItem("conversa").length-1)+localStorage.getItem("i"));
 	document.getElementById("verconversa").id=localStorage.getItem("conversa");
 	document.getElementById(localStorage.getItem("conversa")).href="UsuarioControlador?acao=lismsgm&remetente="+localStorage.getItem('nomeusuario')+"&destinatario=<%=s%>";
 	localStorage.setItem("i",(parseInt(localStorage.getItem("i"))+1).toString());		
 	</script>
 </tr>
<%
}
%>
</table>
</html>