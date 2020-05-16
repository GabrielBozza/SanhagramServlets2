<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cabeçalho</title>
</head>
<body>
<br>
<a href="UsuarioControlador?acao=pagInicial" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;">&emsp;&emsp;Início</a>
<a id="MensagensRecentes" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;" href="UsuarioControlador?acao=lisamigos&destinatario=<%=request.getSession().getAttribute("usuAutenticado")%>">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Mensagens recentes</a>
<a href="UsuarioControlador?acao=lis" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Lista de Usuários</a>
<a href="UsuarioControlador?acao=cad" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Cadastro</a>
<a href="autenticador" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Sair</a><br><br>

</body>
</html>