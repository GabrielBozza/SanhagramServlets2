<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cabe�alho</title>
</head>
<body>
<br>
<a href="UsuarioControlador?acao=pagInicial" style="margin-left:40px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;">In�cio</a>
<a id="MensagensRecentes" style="margin-left:170px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;" href="UsuarioControlador?acao=lisamigos&destinatario=<%=request.getSession().getAttribute("usuAutenticado")%>">Cadastro Grupos</a>
<a href="UsuarioControlador?acao=lis" style="margin-left:170px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;">Lista de Usu�rios</a>
<a href="UsuarioControlador?acao=cad" style="margin-left:170px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;">Cadastro</a>
<a href="autenticador" style="margin-left:170px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;">Sair</a><br><br>

</body>
</html>