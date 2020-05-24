<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Erro - Login</title>
</head>
<body style="background-color:black;">
  <script>
    document.body.style.zoom = (window.screen.width/1536);
  </script>
	<script>
			localStorage.setItem("modoApagarMensagens","naoApagar");
	</script>
<h1 style="color:white;text-align:center;font-family:Helvetica;margin-top:300px;">Erro de autenticação no Sanhagram!</h1>
<h3 style="text-align:center;"><a href="login.jsp" style="text-decoration:none;color:red;text-align:center;font-family:Helvetica;">Voltar para a página de Login</a></h3>	
<script>
alert("Nome de usuário e/ou senha incorretos!")
</script>
</body>
</html>