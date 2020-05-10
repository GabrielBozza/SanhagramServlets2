<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cabeçalho</title>
</head>
<body>
<a href="home.jsp">Inicio</a><br>
<a href="UsuarioControlador?acao=cad">Cadastro</a><br>
<a href="UsuarioControlador?acao=lis">Lista de Usuarios</a><br>
<a href="UsuarioControlador?acao=lismsgm&remetente=<%=request.getParameter("nome") %>&destinatario=teodoro">Teodoro</a><br>
<a id="MensagensRecentes">Mensagens recentes</a><br>
<a href="autenticador">Sair</a><br>

<script>document.getElementById("MensagensRecentes").href="UsuarioControlador?acao=lisamigos&destinatario="+localStorage.getItem('nomeusuario');</script>

</body>
</html>