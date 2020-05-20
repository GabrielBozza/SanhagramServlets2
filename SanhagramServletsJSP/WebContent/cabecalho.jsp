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
<a  href="UsuarioControlador?acao=pagInicial" style="font-size:20px;color:black;font-family:Helvetica;text-decoration:none;margin-left:60px;"><i class="fa fa-fw fa-home"></i> Início</a>
<a id="salvarMensagem" style="font-size:20px;color:black;font-family:Helvetica;text-decoration:none;margin-left:270px;" href="UsuarioControlador?acao=salvarMensagem"> <i class="fa fa-fw fa-save"></i> Salvar</a>
<a id="EnviarMensagensSalvas" style="font-size:20px;color:black;font-family:Helvetica;text-decoration:none;margin-left:270px;" href="UsuarioControlador?acao=lismsgmSalvas&remetente=<%=request.getSession().getAttribute("usuAutenticado") %>" > <i class="fa fa-fw fa-paper-plane"></i> Enviar</a>
<a href="autenticador" style="font-size:20px;color:black;font-family:Helvetica;text-decoration:none;margin-left:270px;"><i class="fa fa-fw fa-sign-out"></i> Sair</a><br><br>


</body>
</html>