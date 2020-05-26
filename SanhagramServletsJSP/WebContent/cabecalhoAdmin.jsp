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
<a href="UsuarioControlador?acao=pagInicial&dispositivo=desktop" style="margin-left:45px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-home"></i> Início</a>
<a id="MensagensRecentes" style="margin-left:115px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;" href="UsuarioControlador?acao=lis&grupo=cadgrupo&dispositivo=desktop"><i class="fa fa-fw fa-pencil"></i> Criar Grupo</a>
<a href="UsuarioControlador?acao=lis&grupo=lisusuarios&dispositivo=desktop" style="margin-left:115px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-user"></i> Usuários</a>
<a href="UsuarioControlador?acao=lis&grupo=lisgrupos&dispositivo=desktop" style="margin-left:115px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-users"></i> Grupos</a>
<a href="UsuarioControlador?acao=cad&dispositivo=desktop" style="margin-left:115px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"> <i class="fa fa-fw fa-save"></i> Cadastro</a>
<a href="autenticador" style="margin-left:115px;font-size:18px;color:black;font-family:Helvetica;text-decoration:none;"><i class="fa fa-fw fa-sign-out"></i> Sair</a><br><br>

</body>
</html>