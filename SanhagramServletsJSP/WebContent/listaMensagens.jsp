<%@page import="java.util.List"%>
<%@page import="bean.Mensagem"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista mensagens</title>
</head>
<body>
<jsp:include page="cabecalho.jsp"></jsp:include>
</body>
<%

//List<Usuario> lista = usuDAO.buscarTodos(usu);
List<Mensagem> listaResultado = (List<Mensagem>)request.getAttribute("lista");
%>
<table border="1">
 <tr bgcolor="#eaeaea">
 	<th>ID</th>
 	<th>Remetente</th>
 	<th>Destinatario</th>
 	<th>Mensagem</th>
 	<th>Data de envio</th>
 	<th>Excluir</th>
 </tr>
<%
for(Mensagem m:listaResultado){
%>
<tr>
 	<th><%=m.getIdmensagem() %></th>
 	<th><%=m.getRemetente() %></th>
 	<th><%=m.getDestinatario() %></th>
 	<th><%=m.getTexto_mensagem() %></th>
 	<th><%=m.getData_envio() %></th>
 	<th><a href="UsuarioControlador?acao=exmsgm&idmensagem=<%=m.getIdmensagem() %>">Excluir</a>
 </tr>
<%
}
%>
</table>
</html>