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
<a  href="UsuarioControlador?acao=pagInicial" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:60px;"><i class="fa fa-fw fa-home"></i> Início</a>
<a id="ExcluirMensagens" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:150px;" href="#ModoApagar" onclick="modoApagar()"> <i class="fa fa-fw fa-trash"></i> Apagar mensagens</a>
<a id="EscreverMensagens" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:150px;" href="#EscreverMensagem"> <i class="fa fa-fw fa-pencil"></i> Salvar</a>
<a id="EnviarMensagensSalvas" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:150px;" href="#EnviarMsgmSalva" > <i class="fa fa-fw fa-paper-plane"></i> Enviar</a>
<a href="autenticador" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:150px;">Sair</a><br><br>


</body>
</html>