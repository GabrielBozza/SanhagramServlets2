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
<a  href="UsuarioControlador?acao=pagInicial" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:60px;">Início</a>
<a id="MensagensRecentes" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:250px;" href="UsuarioControlador?acao=lisamigos&destinatario=<%=request.getSession().getAttribute("usuAutenticado")%>">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Mensagens recentes</a>
<a href="autenticador" style="font-size:18px;color:black;font-family:Helvetica;text-decoration:none;margin-left:250px;">&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Sair</a><br><br>

</body>
</html>