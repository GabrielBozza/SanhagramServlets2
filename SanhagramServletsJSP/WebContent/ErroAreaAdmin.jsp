<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Erro</title>
</head>
<body style="background-color:black;">
  <script>
    document.body.style.zoom = (window.screen.width/1536);
  </script>
	<script>
			localStorage.setItem("modoApagarMensagens","naoApagar");
	</script>
<h1 style="color:white;text-align:center;font-family:Helvetica;margin-top:300px;">Algum erro ocorreu!</h1>
<h3 style="text-align:center;"><a href="UsuarioControlador?acao=pagInicial" style="text-decoration:none;color:red;text-align:center;font-family:Helvetica;">Voltar para a página inicial</a></h3>	
<script>
alert("Algum erro ocorreu: Cadastro já existente, login incorreto ou outros erros!!!")
</script>
</body>
</html>