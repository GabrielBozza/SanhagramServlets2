<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cabeçalho</title>
</head>
<body>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<br>
<a href="UsuarioControlador?acao=pagInicial" style="margin-left:40px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-home"></i> Início</a>
<a id="MensagensRecentes" style="margin-left:150px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;" href="UsuarioControlador?acao=lisamigos&destinatario=<%=request.getSession().getAttribute("usuAutenticado")%>"><i class="fa fa-fw fa-pencil"></i> Cadastro Grupos</a>
<a href="UsuarioControlador?acao=lis" style="margin-left:150px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-gear"></i> Lista de Usuários</a>
<a href="UsuarioControlador?acao=cad" style="margin-left:150px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"> <i class="fa fa-fw fa-pencil"></i> Cadastro</a>
<a href="autenticador" style="margin-left:150px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;">Sair</a><br><br>

</body>
</html>